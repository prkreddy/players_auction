package xml.read.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pojo.CenturiesPerf;
import pojo.Performance;
import pojo.Player;
import pojo.WicketsPerf;
import util.Constants;
import xml.read.PlayerXmlReader;

public class DOMXmlReader extends PlayerXmlReader {

	@Override
	public List<Player> getPlayers(String filePath) {
		List<Player> players = null;
		if (filePath != null && !Constants.EMPTYSTRING.equals(filePath)) {
			players = new ArrayList<Player>();

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			try {

				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(new File(filePath));

				NodeList playerNodeList = doc
						.getElementsByTagName(Constants.PLAYERS);

				Player player = null;
				int playersSize = playerNodeList.getLength();

				for (int index = 0; index < playersSize; index++) {

					player = getPlayer(playerNodeList.item(index));

					if (player != null) {

						players.add(player);
					}

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return players;
	}

	private Player getPlayer(Node node) {
		Player player = null;
		if (node != null) {

			player = new Player();

			NodeList playersChildNL = node.getChildNodes();

			for (int i = 0; i < playersChildNL.getLength(); i++) {

				Node playerCN = playersChildNL.item(i);

				if (playerCN != null) {

					String childNodeName = playerCN.getNodeName();

					if (childNodeName != null
							&& !Constants.EMPTYSTRING.equals(childNodeName)) {
						if (Constants.NAME.equalsIgnoreCase(childNodeName)) {

							player.setName(playerCN.getFirstChild()
									.getNodeValue());

						} else if (Constants.COUNTRY
								.equalsIgnoreCase(childNodeName)) {
							player.setCountry(playerCN.getFirstChild()
									.getNodeValue());

						} else if (Constants.BASEPRICE
								.equalsIgnoreCase(childNodeName)) {

							player.setBasePrice(Long.parseLong(playerCN
									.getFirstChild().getNodeValue()));

						} else if (Constants.CENTURIES
								.equalsIgnoreCase(childNodeName)) {

							player.setCenturies(getPerfomanceDetails(playerCN));

						} else if (Constants.WICKETS
								.equalsIgnoreCase(childNodeName)) {

							player.setWickets(getPerfomanceDetails(playerCN));

						} else if (Constants.TYPE
								.equalsIgnoreCase(childNodeName)) {
							player.setType(playerCN.getFirstChild()
									.getNodeValue());
						}
					}

				}

			}

		}

		return player;

	}

	private Performance getPerfomanceDetails(Node node) {
		Performance perf = null;

		if (Constants.CENTURIES.equalsIgnoreCase(node.getNodeName())) {
			perf = new CenturiesPerf();

		} else {

			perf = new WicketsPerf();
		}

		NodeList prefChildNL = node.getChildNodes();

		for (int i = 0; i < prefChildNL.getLength(); i++) {

			Node perfCN = prefChildNL.item(i);

			if (perfCN != null) {

				String childNodeName = perfCN.getNodeName();

				if (childNodeName != null
						&& !Constants.EMPTYSTRING.equals(childNodeName)) {
					if (Constants.ODI.equalsIgnoreCase(childNodeName)) {

						perf.setOdi(Integer.parseInt(perfCN.getFirstChild()
								.getNodeValue()));

					} else if (Constants.TEST.equalsIgnoreCase(childNodeName)) {
						perf.setTest(Integer.parseInt(perfCN.getFirstChild()
								.getNodeValue()));
					} else if (Constants.T20.equalsIgnoreCase(childNodeName)) {

						perf.setT20(Integer.parseInt(perfCN.getFirstChild()
								.getNodeValue()));
					}
				}

			}

		}

		return perf;

	}

}
