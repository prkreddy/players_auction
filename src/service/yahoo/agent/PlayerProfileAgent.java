package service.yahoo.agent;

import java.io.File;

import pojo.Player;
import service.yahoo.parser.PlayerProfileParser;

import com.sun.jersey.api.client.ClientResponse;

public class PlayerProfileAgent {

	public Player getPlayer(String playerId) {
		Player player = null;
		try {

			ClientResponse response = WebClient
					.getResponse("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20cricket.player.profile%20where%20player_id%3D"
							+ playerId
							+ "&diagnostics=true&env=store%3A%2F%2F0TxIGQMQbObzvU4Apia0V0");


			if (response != null) {
				PlayerProfileParser agent = new PlayerProfileParser();
				player = agent.getPlayer(response.getEntityInputStream());

				player.setBasePrice(WebClient.getPrice() * 1000000);

				player.printPlayerDetails();
			} else {
				System.out.println("no response from service ");
			}
			// System.out.println("response from server \n"+output);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return player;
	}

	public Player getPlayer(File playerId) {
		Player player = null;
		try {

			PlayerProfileParser agent = new PlayerProfileParser();
			player = agent.getPlayer(playerId);

			// player.setBasePrice(WebClient.getPrice() * 1000000);

			player.printPlayerDetails();

		} catch (Exception e) {

			e.printStackTrace();

		}
		return player;
	}

	public static void main(String args[]) {

		long inital = System.currentTimeMillis();

		PlayerProfileAgent agent = new PlayerProfileAgent();

		String filepath = System.getProperty("user.dir")
				+ "\\src\\xml\\res\\sachindata.xml";


		agent.getPlayer("2962");

		System.out.println(System.currentTimeMillis() - inital);
		//
	}
}
