package io.hari.apachecamelintegrationpattern.test_sdk.messages;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.primitives.Bytes;
import org.apache.http.Header;
import org.apache.http.HttpMessage;
import org.apache.http.impl.io.SessionInputBufferImpl;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.joining;

public abstract class GorMessage {
  private final String id;
  private final String time;
  private final String line;
  private final byte[] httpHeaderLine;
  protected final byte[] httpBlock;
  private byte[] body;
  private LinkedListMultimap<String, String> headers = LinkedListMultimap.create();
  private boolean wasModified = false;
  private final String headerLine;

  public static GorMessage make(String line, PrintWriter results) {
    byte[] gorSpec = hexDecode(line);
    String headerLine = new String(Arrays.copyOfRange(gorSpec, 0, Bytes.indexOf(gorSpec, (byte) '\n')));
    switch (headerLine.charAt(0)) {
      case '1':
        return new GorRequest(line, gorSpec, headerLine, results);
      case '2':
        return new GorOriginalResponse(line, gorSpec, headerLine, results);
      case '3':
        return new GorReplayedResponse(line, gorSpec, headerLine, results);
      default:
        throw new RuntimeException("Unknown type");
    }

  }

  protected GorMessage(String line, byte[] gorSpec, String headerLine) {
    String[] header = headerLine.split(" ");
    this.headerLine = headerLine;
    this.line = line;
    this.id = header[1];
    this.time = header[2];

    this.httpBlock = Arrays.copyOfRange(gorSpec, Bytes.indexOf(gorSpec, (byte) '\n') + 1, gorSpec.length);
    this.httpHeaderLine = Arrays.copyOfRange(httpBlock, 0, Bytes.indexOf(httpBlock, (byte) '\n'));
  }

  protected final void parseMessage(SessionInputBufferImpl buffer, HttpMessage httpRequest) throws IOException {
    for (Header header : httpRequest.getAllHeaders()) {
      headers.put(header.getName(), header.getValue());
    }

    if (buffer.hasBufferedData()) {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      //code copied from ByteStreams#copy because buffer does not implement inputstream
      byte[] buf = new byte[8192];
      while (true) {
        int cur = buffer.read(buf);
        if (cur == -1) {
          break;
        }
        out.write(buf, 0, cur);
      }
      body = out.toByteArray();
    } else {
      body = new byte[0];
    }
  }

  public String asLine() {
    if (!wasModified) {
      return line;
    } else {
      return hexEncode(
        headerLine.getBytes(),
        makeHttpBlock()
      );
    }
  }

  private byte[] makeHttpBlock() {
    return Bytes.concat(
      httpHeaderLine,
      new byte[]{'\n'},
      headers.entries().stream()
             .map(e -> e.getKey() + ": " + e.getValue())
             .collect(joining("\n"))
             .getBytes(),
      new byte[]{ '\n', '\n' },
      body
    );
  }

  private static String hexEncode(byte[] headerLine, byte[] statement) {
    return DatatypeConverter.printHexBinary(headerLine) + "10" + DatatypeConverter.printHexBinary(statement);
  }

  private static byte[] hexDecode(String line) {
    return DatatypeConverter.parseHexBinary(line);
  }

  public Optional<String> getHeader(String headerName) {
    if (headers.containsKey(headerName)) {
      return Optional.of(headers.get(headerName).get(0));
    } else {
      return Optional.empty();
    }
  }

  public List<Map.Entry<String, String>> getHeaderList() {
    return headers.entries();
  }

  public String getId() {
    return id;
  }

  public void replaceHeader(String headerName, Function<String, String> valueMapper) {
    if (headers.containsKey(headerName)) {
      wasModified = true;
      String orig = headers.get(headerName).get(0);
      headers.removeAll(headerName);
      headers.put(headerName, valueMapper.apply(orig));
    }
  }

  public byte[] getBody() {
    return body;
  }

  private String getType() {
    if (this instanceof GorOriginalResponse) {
      return "orig";
    } else if (this instanceof GorReplayedResponse) {
      return "replay";
    } else if (this instanceof GorRequest) {
      return "req";
    } else {
      return "unknown";
    }
  }

  public byte[] getHttpBlock(PrintWriter results) {
    if (!wasModified) {
      return httpBlock;
    } else {
      return makeHttpBlock();
    }
  }

  public void setBody(byte[] body) {
    wasModified = true;
    this.body = body;
  }

  public void removeHeader(String name) {
    wasModified = true;
    this.headers.removeAll(name);
  }
}
