package test;

import java.util.List;

import pojo.Player;
import xml.read.PlayerXmlReader;
import xml.read.impl.JAXBXmlReader;

public class TestJaxB {
	private static final String PLAYERSTORE_XML = "\\src\\xml\\res\\playstore-jaxb.xml";

	public static void main(String args[]) {

		PlayerXmlReader parser = new JAXBXmlReader();
		
		List<Player> players = parser.getPlayers(System.getProperty("user.dir") + PLAYERSTORE_XML);
		
		parser.displayPlayers(players);

	}
}
