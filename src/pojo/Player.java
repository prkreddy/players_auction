package pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "players")
@XmlType(propOrder={"name","country","basePrice","centuries","wickets","type"})
public class Player {

	/**
	 * @param centuries
	 * @param wickets
	 */
	public Player(Performance centuries, Performance wickets) {
		super();
		this.centuries = centuries;
		this.wickets = wickets;
	}

	public Player() {
		super();
	}

	private String name;

	private String country;

	private long basePrice;

	private String type;

	public String getType() {
		return type;
	}

	@XmlElement
	public void setType(String type) {
		this.type = type;
	}

	private Performance centuries;

	private Performance wickets;

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}
	
	@XmlElement
	public void setCountry(String country) {
		this.country = country;
	}

	public long getBasePrice() {
		return basePrice;
	}

	@XmlElement
	public void setBasePrice(long basePrice) {
		this.basePrice = basePrice;
	}

	public Performance getCenturies() {
		return centuries;
	}

	@XmlElement(type=CenturiesPerf.class)
	public void setCenturies(Performance centuries) {
		this.centuries = centuries;
	}

	public Performance getWickets() {
		return wickets;
	}

	@XmlElement(type=WicketsPerf.class)
	public void setWickets(Performance wickets) {
		this.wickets = wickets;
	}

	public void printPlayerDetails() {

		System.out
				.println("**********************************************************\n");
		System.out.println("Player Details : \n");
		System.out.println("Player Name : " + getName());
		System.out.println("Player country: " + getCountry());
		System.out.println("player bestPrice: " + getBasePrice());
		System.out.println("player type: " + getType());

		centuries.displayPerfomanceDetails();

		wickets.displayPerfomanceDetails();

		System.out
				.println("**********************************************************\n");

	}

}
