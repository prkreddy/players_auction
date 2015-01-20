package xml.read;

import java.util.List;

import pojo.Player;

public abstract class PlayerXmlReader {

	public abstract List<Player> getPlayers(String filePath);

	public void displayPlayers(List<Player> players) {

		if (players != null && !players.isEmpty()) {

			for (Player player : players) {
				player.printPlayerDetails();
			}
		} else {

			System.out.println("no players data to display");

		}

	}

}
