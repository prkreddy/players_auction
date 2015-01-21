package pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "odi", "test", "t20" })
public abstract class Performance {

	private int odi;

	private int test;

	private int t20;

	public int getOdi() {
		return odi;
	}

	@XmlElement
	public void setOdi(int odi) {
		this.odi = odi;
	}

	public int getTest() {
		return test;
	}

	@XmlElement
	public void setTest(int test) {
		this.test = test;
	}

	public int getT20() {
		return t20;
	}

	@XmlElement
	public void setT20(int t20) {
		this.t20 = t20;
	}

	public abstract void displayPerfomanceDetails();

	public String toString() {

		return " ODI : " + getOdi() + ", Test :" + getTest() + ", T20 :"
				+ getT20();

	}

}
