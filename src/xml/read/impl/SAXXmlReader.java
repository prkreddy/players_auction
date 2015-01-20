package xml.read.impl;

import java.io.File;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import pojo.Player;
import util.Constants;
import xml.read.PlayerXmlReader;

public class SAXXmlReader extends PlayerXmlReader {

	@Override
	public List<Player> getPlayers(String filePath) {

		if (filePath != null && !Constants.EMPTYSTRING.equals(filePath)) {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			try {
				SAXParser parser = factory.newSAXParser();
				SAXHandler handler = new SAXHandler();

				parser.parse(new File(filePath), handler);

				return handler.getPlayers();

			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

}
