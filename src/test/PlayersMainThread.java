package test;

import java.util.List;

import player.aution.AuctionRunner;
import pojo.Player;
import util.DBConnectUtil;
import xml.read.PlayerXmlReader;
import xml.read.impl.SAXXmlReader;
import dao.PlayerDAO;
import dao.TeamDAO;

public class PlayersMainThread {

	public static void main(String args[]) {

		try {

			// PlayersMainThread thread = new PlayersMainThread();

			// for (int i = 1; i <= 10; i++) {
			// if (i == 8)
			// continue;
			//
			// thread.insertXmlDataToDB("\\src\\xml\\res\\playerList"+i+".xml");
			// }

			// thread.insertXmlDataToDB("\\src\\xml\\res\\playerList2.xml");
			// thread.getTeamsFromDB();

			// thread.getPlayersFromDB("ENG");

			// thread.diplayPlayersFromXmlData();
			
			AuctionRunner runner = new AuctionRunner();
			runner.init();
		} finally {
			DBConnectUtil.releaseConnection();
		}

	}

	public void insertXmlDataToDB(String filepath) {
		PlayerXmlReader parser = new SAXXmlReader();
		List<Player> players = parser.getPlayers(System.getProperty("user.dir")
				+ filepath);
		// + "\\src\\xml\\res\\playerList1.xml");

		PlayerDAO dao = new PlayerDAO();
		dao.insertPlayers(players);

	}

	public void getPlayersFromDB() {
		PlayerXmlReader parser = new SAXXmlReader();

		PlayerDAO dao = new PlayerDAO();
		parser.displayPlayers(dao.getAllPlayers());

	}

	public void getTeamsFromDB() {

		TeamDAO dao = new TeamDAO();
		dao.displayTeams(dao.getAllTeams());

	}

	public void getPlayersFromDB(String countryCode) {
		PlayerXmlReader parser = new SAXXmlReader();

		PlayerDAO dao = new PlayerDAO();
		parser.displayPlayers(dao.getPlayersByTeam(countryCode));

	}

	public void diplayPlayersFromXmlData() {

		PlayerXmlReader parser = new SAXXmlReader();

		for (int i = 1; i <= 10; i++) {
			if (i == 8)
				continue;

			parser.displayPlayers(parser.getPlayers(System
					.getProperty("user.dir")
					+ "\\src\\xml\\res\\playerList"
					+ i + ".xml"));

		}
	}
}
