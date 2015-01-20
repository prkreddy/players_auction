package xml.read.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import pojo.CenturiesPerf;
import pojo.Performance;
import pojo.Player;
import pojo.WicketsPerf;
import util.Constants;

class SAXHandler extends DefaultHandler {

	List<Player> players;

	Player player;

	Performance perf;

	Stack<String> stack;

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	@Override
	public void startDocument() throws SAXException {

		players = new ArrayList<Player>();
		stack = new Stack<String>();

	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		stack.push(qName);
		if (Constants.PLAYERS.equals(qName)) {
			player = new Player();

		} else if (Constants.CENTURIES.equalsIgnoreCase(qName)) {

			perf = new CenturiesPerf();
		} else if (Constants.WICKETS.equalsIgnoreCase(qName)) {

			perf = new WicketsPerf();
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (Constants.PLAYERS.equals(qName)) {
			players.add(player);
		} else if (Constants.CENTURIES.equalsIgnoreCase(qName)) {

			player.setCenturies(perf);

		} else if (Constants.WICKETS.equalsIgnoreCase(qName)) {

			player.setWickets(perf);
		}

		stack.pop();

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		String top = stack.peek();

		if (Constants.NAME.equalsIgnoreCase(top)) {

			player.setName(new String(ch, start, length));

		} else if (Constants.COUNTRY.equalsIgnoreCase(top)) {

			player.setCountry(new String(ch, start, length));

		} else if (Constants.BASEPRICE.equalsIgnoreCase(top)) {

			player.setBasePrice(Long.parseLong(new String(ch, start, length)));

		} else if (Constants.TYPE.equalsIgnoreCase(top)) {
			player.setType(new String(ch, start, length));

			if ("-".equals(player.getType())) {

				if (player.getCenturies().getOdi() != 0
						|| player.getCenturies().getT20() != 0
						|| player.getCenturies().getTest() != 0) {
					player.setType("Batsman");
				} else {
					player.setType("Bowler");
				}
			}
		}

		else if (Constants.ODI.equalsIgnoreCase(top)) {

			perf.setOdi(Integer.parseInt(new String(ch, start, length)));

		} else if (Constants.TEST.equalsIgnoreCase(top)) {

			perf.setTest(Integer.parseInt(new String(ch, start, length)));

		} else if (Constants.T20.equalsIgnoreCase(top)) {

			perf.setT20(Integer.parseInt(new String(ch, start, length)));
		}

	}

	public List<Player> getPlayers() {

		return players;

	}

}