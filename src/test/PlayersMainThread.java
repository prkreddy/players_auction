package test;

import java.util.List;

import pojo.Player;
import util.DBConnectUtil;
import xml.read.PlayerXmlReader;
import xml.read.impl.SAXXmlReader;
import dao.PlayerDAO;

public class PlayersMainThread {

	public static void main(String args[]) {

		try {

			PlayersMainThread thread = new PlayersMainThread();

			//thread.insertXmlDataToDB("\\src\\xml\\res\\playerList4.xml");

			 thread.getPlayersFromDB();

			// thread.getPlayersFromDB("ENG");

			// thread.diplayPlayersFromXmlData();
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
