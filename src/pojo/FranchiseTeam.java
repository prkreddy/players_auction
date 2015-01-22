package pojo;

import java.util.ArrayList;
import java.util.List;

public class FranchiseTeam {

	public FranchiseTeam() {
		// TODO Auto-generated constructor stub
		players = new ArrayList<Player>();
	}

	private int teamId;

	private String name;

	private String ownerName;

	private String city;

	private long balanceAamount;

	private String coachName;

	private int foreignPlayersCount;

	private int indianPlayersCount;

	private List<Player> players;

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

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
