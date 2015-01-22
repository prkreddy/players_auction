package pojo;

public class Rule {
	
	private int ruleId;
	
	private int maxForeignPlayersCount;
	
	private int minIndianPlayersCount;
	
	private int minPlayers;
	
	private int perFranchiseAmount;

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public int getMaxForeignPlayersCount() {
		return maxForeignPlayersCount;
	}

	public void setMaxForeignPlayersCount(int maxForeignPlayersCount) {
		this.maxForeignPlayersCount = maxForeignPlayersCount;
	}

	public int getMinIndianPlayersCount() {
		return minIndianPlayersCount;
	}

	public void setMinIndianPlayersCount(int minIndianPlayersCount) {
		this.minIndianPlayersCount = minIndianPlayersCount;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	public int getPerFranchiseAmount() {
		return perFranchiseAmount;
	}

	public void setPerFranchiseAmount(int perFranchiseAmount) {
		this.perFranchiseAmount = perFranchiseAmount;
	}

	
}
