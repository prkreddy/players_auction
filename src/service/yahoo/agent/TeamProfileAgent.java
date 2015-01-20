package service.yahoo.agent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import pojo.Player;
import service.yahoo.parser.TeamProfileParser;
import service.yahoo.parser.WritePlayerXmlFile;

import com.sun.jersey.api.client.ClientResponse;

public class TeamProfileAgent {

	public static void main(String[] args) {
		TeamProfileAgent agent = new TeamProfileAgent();

		WritePlayerXmlFile write = new WritePlayerXmlFile();

		for (int i = 1; i <= 10; i++) {
			write.writeXml(agent.getPlayersByTeam(i), i);
		}

	}

	public List<Player> getPlayersByTeam(int teamId) {

		List<Player> players = new ArrayList<Player>();

		try {

			ClientResponse response = WebClient
					.getResponse("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20cricket.team.profile%20where%20team_id%3D"
							+ teamId
							+ "&diagnostics=true&env=store%3A%2F%2F0TxIGQMQbObzvU4Apia0V0");


			if (response != null) {
				TeamProfileParser agent = new TeamProfileParser();
				Set<Integer> playerIDs = agent.getPlayerIds(response
						.getEntityInputStream());

				// Extracting players profile from other service using playerIds.
				PlayerProfileAgent playeragent = null;
				Iterator<Integer> it = playerIDs.iterator();
				while (it.hasNext()) {
					Integer integer = (Integer) it.next();
					System.out.println("player details " + integer + "\n");

					playeragent = new PlayerProfileAgent();
					players.add(playeragent.getPlayer(integer.toString()));

				}
			} else {
				System.out.println("no response from service ");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return players;

	}
}