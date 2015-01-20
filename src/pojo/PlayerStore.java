package pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="playerStore")
public class PlayerStore {

	List<Player> players;

	public List<Player> getPlayers() {
		return players;
	}

	
	@XmlElement(name="players")
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
