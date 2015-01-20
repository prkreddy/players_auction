package pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "centuries" )
public class CenturiesPerf extends Performance {

	@Override
	public void displayPerfomanceDetails() {

		System.out.println("\n\nCenturies details :\n ");
		System.out.println("Centuries in ODI : " + getOdi());
		System.out.println("Centuries in Test :" + getTest());
		System.out.println("Centuries in T20 :" + getT20());

	}

}
