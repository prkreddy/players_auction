package pojo;

import java.util.List;

public class FranchiseTeam {

	private int teamId;

	private String name;

	private String ownerName;

	private String city;

	private long balanceAamount;

	private String coachName;

	private int foreignPlayersCount;

	private int indianPlayersCount;

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getBalanceAamount() {
		return balanceAamount;
	}

	public void setBalanceAamount(long balanceAamount) {
		this.balanceAamount = balanceAamount;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public int getForeignPlayersCount() {
		return foreignPlayersCount;
	}

	public void setForeignPlayersCount(int foreignPlayersCount) {
		this.foreignPlayersCount = foreignPlayersCount;
	}

	public int getIndianPlayersCount() {
		return indianPlayersCount;
	}

	public void setIndianPlayersCount(int indianPlayersCount) {
		this.indianPlayersCount = indianPlayersCount;
	}

	

	public String toString() {

		return "TeamID: " + teamId + "\n TeamName :" + name + "\n OwnerName "
				+ ownerName + " \n city" + city + "\n balanceAmount :"
				+ balanceAamount + "\n coachName : " + coachName
				+ "\n foreignPlayersCount: " + foreignPlayersCount
				+ "\n indianPlayersCount : " + indianPlayersCount;

	}
}
