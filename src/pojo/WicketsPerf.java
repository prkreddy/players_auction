package pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="wickets")
public class WicketsPerf extends Performance {

	@Override
	public void displayPerfomanceDetails() {
		System.out.println("\n\nWickets details : \n");
		System.out.println("Wickets in ODI : " + getOdi());
		System.out.println("Wickets in Test :" + getTest());
		System.out.println("Wickets in T20 :" + getT20());

	}

}
