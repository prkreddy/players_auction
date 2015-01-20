package service.yahoo.parser;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import pojo.Player;
import util.Constants;

public class WritePlayerXmlFile {

	public void writeXml(List<Player> players, int teamId) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document doc = builder.newDocument();

			Element rootElement = doc.createElement("playerStore");
			doc.appendChild(rootElement);

			Element playerElement = null;
			Element tempElement = null;
			Element perfElement = null;
			for (Player player : players) {

				if (player != null) {

					playerElement = doc.createElement(Constants.PLAYERS);

					tempElement = doc.createElement(Constants.NAME);
					tempElement
							.appendChild(doc.createTextNode(player.getName()));

					playerElement.appendChild(tempElement);

					tempElement = doc.createElement(Constants.COUNTRY);
					tempElement.appendChild(doc.createTextNode(player
							.getCountry()));

					playerElement.appendChild(tempElement);

					tempElement = doc.createElement(Constants.BASEPRICE);
					tempElement.appendChild(doc.createTextNode(player
							.getBasePrice() + ""));
					playerElement.appendChild(tempElement);

					// adding centuries
					perfElement = doc.createElement(Constants.CENTURIES);

					tempElement = doc.createElement(Constants.ODI);
					tempElement.appendChild(doc.createTextNode(player
							.getCenturies().getOdi() + ""));
					perfElement.appendChild(tempElement);

					tempElement = doc.createElement(Constants.TEST);
					tempElement.appendChild(doc.createTextNode(player
							.getCenturies().getTest() + ""));
					perfElement.appendChild(tempElement);

					tempElement = doc.createElement(Constants.T20);
					tempElement.appendChild(doc.createTextNode(player
							.getCenturies().getT20() + ""));
					perfElement.appendChild(tempElement);

					playerElement.appendChild(perfElement);

					// adding wickets
					perfElement = doc.createElement(Constants.WICKETS);

					tempElement = doc.createElement(Constants.ODI);
					tempElement.appendChild(doc.createTextNode(player
							.getWickets().getOdi() + ""));
					perfElement.appendChild(tempElement);
					
					tempElement = doc.createElement(Constants.TEST);
					tempElement.appendChild(doc.createTextNode(player
							.getWickets().getTest() + ""));
					perfElement.appendChild(tempElement);
					
					tempElement = doc.createElement(Constants.T20);
					tempElement.appendChild(doc.createTextNode(player
							.getWickets().getT20() + ""));
					perfElement.appendChild(tempElement);
					
					playerElement.appendChild(perfElement);

					// adding type
					tempElement = doc.createElement(Constants.TYPE);
					tempElement
							.appendChild(doc.createTextNode(player.getType()));
					playerElement.appendChild(tempElement);
					rootElement.appendChild(playerElement);

				}

			}

			TransformerFactory tfactory = TransformerFactory.newInstance();
			try {
				Transformer transformer = tfactory.newTransformer();
				DOMSource domsource = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(
						"D:\\workspace\\xmlParsers\\src\\xml\\res\\playerList"
								+ teamId + ".xml"));

				transformer.transform(domsource, result);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
