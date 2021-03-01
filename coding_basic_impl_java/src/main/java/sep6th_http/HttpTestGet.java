package sep6th_http;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpTestGet {
	public static void main(String[] args) throws IOException {
		String SAMPLE_URL = "http://localhost:8080/post";
		
		try (CloseableHttpClient httpclient = HttpClients.createDefault();) {
			final HttpGet httpget = new HttpGet(SAMPLE_URL);
			final CloseableHttpResponse response = httpclient.execute(httpget);
			
			HttpEntity httpEntity = response.getEntity();
            String body = EntityUtils.toString(httpEntity);
            System.out.println(body);

		}
	}

}
