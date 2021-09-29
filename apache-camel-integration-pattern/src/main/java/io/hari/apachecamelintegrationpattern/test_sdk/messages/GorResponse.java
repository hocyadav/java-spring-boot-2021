package io.hari.apachecamelintegrationpattern.test_sdk.messages;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.impl.io.DefaultHttpResponseParser;
import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.impl.io.SessionInputBufferImpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class GorResponse extends GorMessage {

  private int status;

  public GorResponse(String line, byte[] gorSpec, String header, PrintWriter results) {
    super(line, gorSpec, header);
    parseResponse(httpBlock, results);
  }

  private void parseResponse(byte[] contents, PrintWriter writer) {
    SessionInputBufferImpl buffer = new SessionInputBufferImpl(new HttpTransportMetricsImpl(), contents.length);
    buffer.bind(new ByteArrayInputStream(contents));
    DefaultHttpResponseParser defaultHttpResponseParser = new DefaultHttpResponseParser(buffer);

    try {
      HttpResponse httpResponse = defaultHttpResponseParser.parse();
      status = httpResponse.getStatusLine().getStatusCode();
      parseMessage(buffer, httpResponse);

    } catch (IOException | HttpException e) {
      writer.println("Parsing response failed!");
    }
  }

  public int getStatus() {
    return status;
  }
}
