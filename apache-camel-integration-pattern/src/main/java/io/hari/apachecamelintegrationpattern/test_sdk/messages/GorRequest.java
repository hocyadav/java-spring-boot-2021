package io.hari.apachecamelintegrationpattern.test_sdk.messages;

import com.google.common.collect.LinkedListMultimap;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.impl.io.DefaultHttpRequestParser;
import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.impl.io.SessionInputBufferImpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class GorRequest extends GorMessage {
  private String path;
  private String method;
  private LinkedListMultimap<String, String> queryParameters = LinkedListMultimap.create();

  public GorRequest(String line, byte[] gorSpec, String header, PrintWriter results) {
    super(line, gorSpec, header);
    parseRequest(httpBlock, results);
  }

  private void parseRequest(byte[] contents, PrintWriter results) {
    SessionInputBufferImpl buffer = new SessionInputBufferImpl(new HttpTransportMetricsImpl(), contents.length);
    buffer.bind(new ByteArrayInputStream(contents));
    DefaultHttpRequestParser defaultHttpRequestParser = new DefaultHttpRequestParser(buffer);

    try {
      HttpRequest httpRequest = defaultHttpRequestParser.parse();

      method = httpRequest.getRequestLine().getMethod();
      parseUrl(httpRequest.getRequestLine().getUri());
      parseMessage(buffer, httpRequest);

    } catch (IOException | HttpException e) {
      results.println("Parsing request failed!");
    }
  }

  private void parseUrl(String url) {
    String[] urlAndQueryParameters = url.split("\\?");
    path = urlAndQueryParameters[0];
    if (urlAndQueryParameters.length > 1) {
      for (String queryParameter : urlAndQueryParameters[1].split("&")) {
        String[] parameter = queryParameter.split("=");

        queryParameters.put(parameter[0], parameter[1]);
      }
    }
  }

  public String getPath() {
    return path;
  }
}
