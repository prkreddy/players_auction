package xml.read.impl;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import pojo.Player;
import pojo.PlayerStore;
import xml.read.PlayerXmlReader;

public class JAXBXmlReader extends PlayerXmlReader {

	@Override
	public List<Player> getPlayers(String filePath) {

		JAXBContext context = null;

		PlayerStore store = null;

		try {
			context = JAXBContext.newInstance(PlayerStore.class);

			Unmarshaller unmarshaller = context.createUnmarshaller();

			store = (PlayerStore) unmarshaller.unmarshal(new File(filePath));

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		if (store != null) {
			return store.getPlayers();
		}

		return null;
	}

}
