package com.mk.oT;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class ShellCommand {

	public static void main(String[] args) {

		try {
			String url = "https://devservices.alphabroder.com/micro/media/getmediadatemodified.php";

			// String urlparams="Content-Type=application/json&{\"change_timestamp\":\"2018-04-30T15:53:00\"}";

			HttpClient httpClient = HttpClientBuilder.create().build();

			HttpPost request = new HttpPost(url);

			StringEntity params = new StringEntity("{\"change_timestamp\":\"2018-04-30T15:53:00\"}");

			request.addHeader("Content-Type", "application/json");

			request.setEntity(params);

			HttpResponse response = httpClient.execute(request);

			int responseCode = response.getStatusLine().getStatusCode();

			System.out.println("Status =" + responseCode);

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
