package service.yahoo.parser;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TeamProfileParser {

	public Set<Integer> getPlayerIds(InputStream xmlInputstream) {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		TeamPlayerHandler handler = new TeamPlayerHandler();
		SAXParser parser = null;
		try {
			parser = factory.newSAXParser();

			parser.parse(xmlInputstream, handler);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return handler.getPlayerIds();

	}
}

class TeamPlayerHandler extends DefaultHandler {

	Set<Integer> playerIds;

	public Set<Integer> getPlayerIds() {
		return playerIds;
	}

	public void setPlayerIds(Set<Integer> playerIds) {
		this.playerIds = playerIds;
	}

	String PLAYER = "Player";

	boolean playerTagOn;

	@Override
	public void startDocument() throws SAXException {

		playerIds = new HashSet<Integer>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (PLAYER.equalsIgnoreCase(qName)) {

			String playerId = attributes.getValue("personid");

			if (playerId != null && !"".equals(playerId)){
				playerIds.add(Integer.parseInt(playerId));
			}
				
		}
	}

	

}