package service.yahoo.parser;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import pojo.CenturiesPerf;
import pojo.Player;
import pojo.WicketsPerf;
import util.Constants;

public class PlayerProfileParser {

	public Player getPlayer(InputStream xmlInputstream) {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		PlayerProfileHandler handler = new PlayerProfileHandler();
		SAXParser parser = null;
		try {
			parser = factory.newSAXParser();

			parser.parse(xmlInputstream, handler);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return handler.getPlayer();

	}

	public Player getPlayer(File filePath) {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		PlayerProfileHandler handler = new PlayerProfileHandler();
		SAXParser parser = null;
		try {
			parser = factory.newSAXParser();

			parser.parse(filePath, handler);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return handler.getPlayer();

	}

}

class PlayerProfileHandler extends DefaultHandler {

	Player player;

	public Player getPlayer() {
		return player;
	}

	String PLAYER = "Player";

	boolean firstname, lastname, countrycode, role, matchStats, test, odi, t20,
			batting, bowling, all, hundreds, wickets;

	@Override
	public void startDocument() throws SAXException {

		player = new Player(new CenturiesPerf(), new WicketsPerf());
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (Constants.FIRSTNAME.equalsIgnoreCase(qName)) {
			firstname = true;

		} else if (Constants.LASTNAME.equalsIgnoreCase(qName)) {
			lastname = true;
		} else if (Constants.COUNTRYCODE.equalsIgnoreCase(qName)) {
			countrycode = true;
		} else if (Constants.ROLE.equalsIgnoreCase(qName)) {
			role = true;
		} else if (Constants.MATCHSTATS.equalsIgnoreCase(qName)) {
			matchStats = true;

			String matchtype = attributes.getValue(Constants.MTYPE);

			if (Constants.TEST.equalsIgnoreCase(matchtype)) {
				test = true;
			} else if (Constants.ODI.equalsIgnoreCase(matchtype)) {
				odi = true;
			} else if (Constants.IPL.equalsIgnoreCase(matchtype)) {
				t20 = true;
			}

		} else if (Constants.BATTING.equalsIgnoreCase(qName)) {
			batting = true;
			if (Constants.ALL.equalsIgnoreCase(attributes
					.getValue(Constants.VALUE))) {
				all = true;
			}
		} else if (Constants.BOWLING.equalsIgnoreCase(qName)) {

			bowling = true;
			if (Constants.ALL.equalsIgnoreCase(attributes
					.getValue(Constants.VALUE))) {
				all = true;
			}
		} else if (Constants.HUNDREDS.equalsIgnoreCase(qName)) {
			hundreds = true;
		} else if (Constants.WICKETS.equalsIgnoreCase(qName)) {
			wickets = true;
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (Constants.MATCHSTATS.equalsIgnoreCase(qName)) {
			matchStats = false;
			test = false;
			odi = false;
			t20 = false;
		} else if (Constants.HUNDREDS.equalsIgnoreCase(qName)) {
			hundreds = false;
		} else if (Constants.WICKETS.equalsIgnoreCase(qName)) {
			wickets = false;
		} else if (Constants.BATTING.equalsIgnoreCase(qName)) {
			batting = false;
		} else if (Constants.BOWLING.equalsIgnoreCase(qName)) {

			bowling = false;
		}

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		if (length > 0)

			try {

				if (firstname) {
					player.setName(new String(ch, start, length));
					firstname = false;
				} else if (lastname) {

					if (player.getName() != null) {
						player.setName(player.getName() + " "
								+ new String(ch, start, length));

					} else {
						player.setName(new String(ch, start, length));

					}
					lastname = false;
				} else if (countrycode) {
					player.setCountry(new String(ch, start, length));
					countrycode = false;
				} else if (role) {
					player.setType(new String(ch, start, length));
					role = false;
				} else if (matchStats && batting && all && hundreds) {

					if (test) {
						player.getCenturies()
								.setTest(
										Integer.parseInt(new String(ch, start,
												length)));
						all = false;
					} else if (odi) {
						player.getCenturies()
								.setOdi(Integer.parseInt(new String(ch, start,
										length)));
						all = false;
					} else if (t20) {
						player.getCenturies()
								.setT20(Integer.parseInt(new String(ch, start,
										length)));
						all = false;
					}

				} else if (matchStats && bowling && all && wickets) {
					if (test) {
						player.getWickets()
								.setTest(
										Integer.parseInt(new String(ch, start,
												length)));
						all = false;
					} else if (odi) {
						player.getWickets()
								.setOdi(Integer.parseInt(new String(ch, start,
										length)));
						all = false;
					} else if (t20) {
						player.getWickets()
								.setT20(Integer.parseInt(new String(ch, start,
										length)));
						all = false;
					}

				}
			} catch (Exception ex) {
				ex.printStackTrace();

			}
	}
}