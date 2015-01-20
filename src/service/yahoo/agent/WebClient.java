package service.yahoo.agent;

import java.util.Random;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class WebClient {
	private static Client client;
	private static Random rand = new Random();

	public static Client getClient() {

		if (client == null) {

			client = Client.create();
		}

		return client;

	}


	public static ClientResponse getResponse(String uri) {
		WebResource webResource = WebClient.getClient().resource(uri);

		ClientResponse response = webResource.accept(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);

		if (response != null && response.getStatus() == 200) {
			return response;

		} else {
			return null;
		}

	}

	public static long getPrice() {

		return rand.nextInt(10) + 1;
	}
}
