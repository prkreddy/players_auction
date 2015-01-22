package player.aution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import pojo.FranchiseTeam;
import pojo.Player;
import pojo.Rule;
import dao.PlayerDAO;
import dao.RulesDAO;
import dao.TeamDAO;

public class AuctionRunner {

	PlayerDAO playerDao;

	TeamDAO teamDao;

	RulesDAO rulesDao;

	List<FranchiseTeam> teams = null;

	List<Player> players = null;

	List<Player> unsoldPlayers = null;

	Rule rule = null;

	BufferedReader br = null;

	public void init() {

		br = new BufferedReader(new InputStreamReader(System.in));

		teamDao = new TeamDAO();
		playerDao = new PlayerDAO();
		rulesDao = new RulesDAO();

		teams = teamDao.getAllTeams();

		players = playerDao.getAllPlayerOrderbyBasePriceDesc();

		rule = rulesDao.getRules();

		unsoldPlayers = new ArrayList<Player>();

		runAuction();

		if (isAuctionDataValid())
			updateTeam_PlayerDataToDB();

	}

	private void runAuction() {

		int teamSize = teams.size();

		FranchiseTeam team = null;

		List<Boolean> teamEligibilty = new ArrayList<Boolean>();

		// int playercount = 0;
		for (Player player : players) {

			// ++playercount;
			// if (playercount == 12)
			// break;

			// it represents current bid amount on the player.
			long currentBidAmount = player.getBasePrice();

			// it represents # of teams yet to respond with their willingness to
			// bid for current player
			int moreTeamsToRespond = teamSize;

			// this is to hold the team's willingness amount to increase on the
			// current bid amount.
			long responseFromTeam = 0;

			System.out
					.println("-----------------------------------------------------------------------------------------------");

			// to track the team with highest bid on the current player
			int teamsHigestBidIndex = -1;

			teamEligibilty.clear();
			// applying eligibility rules for teams on current player
			for (int i = 0; i < teamSize; i++) {

				team = teams.get(i);
				if (checkIsTeamEligible(team, player)) {
					teamEligibilty.add(true);
				} else
					teamEligibilty.add(false);

			}

			// auction process kickoff
			for (int i = 0; moreTeamsToRespond != 0; i = (i + 1) % teamSize) {

				if (teamEligibilty.get(i)) {
					team = teams.get(i);

					// sysout to show the details of player
					System.out.println("\n\n" + player);
					if (teamsHigestBidIndex == -1)
						System.out
								.println("\n -------------------------------------  StartingBidAmount: "
										+ currentBidAmount
										+ "  -------------------------------------------------");
					else
						System.out
								.println("\n -------------------------------------  currentBidAmount: "
										+ currentBidAmount
										+ "  by "
										+ teams.get(teamsHigestBidIndex)
												.getName()
										+ "-------------------------------------------");

					System.out.println(team.getName() + " please respond ");
					System.out
							.println("enter amount <= "
									+ (team.getBalanceAamount() - currentBidAmount)
									+ "to increase CurrentBidAmount otherwise enter '0' to skip your turn ");

					// end of sysouts

					try {
						responseFromTeam = Long.parseLong(br.readLine());

						if ((responseFromTeam + currentBidAmount) <= team
								.getBalanceAamount() && responseFromTeam > 0) {

							currentBidAmount += responseFromTeam;
							moreTeamsToRespond = teamSize;
							teamsHigestBidIndex = i;
						} else if (responseFromTeam + currentBidAmount > team
								.getBalanceAamount() || responseFromTeam < 0) {
							System.out.println("not a valid bid.");

						} else {
							System.out.println("player skipped by "
									+ team.getName());
						}

					} catch (IOException e) {
						e.printStackTrace();
					} catch (NumberFormatException e) {
						System.out.println("need to be enter number only :  "
								+ "not a valid bid.");
					}

				} else {
					System.out.println(teams.get(i).getName()
							+ "team not eligible for this player");
				}

				--moreTeamsToRespond;
			}

			if (currentBidAmount != player.getBasePrice()) {
				// update player in db

				System.out
						.println("###############################################################################");
				System.out.println("\t\tTeam "
						+ teams.get(teamsHigestBidIndex).getName()
						+ " Purchased \n\t\t");
				System.out.println("\t\t" + player.getName() + "  at "
						+ currentBidAmount);
				System.out
						.println("###############################################################################\n\n");

				team = teams.get(teamsHigestBidIndex);

				team.setBalanceAamount(team.getBalanceAamount()
						- currentBidAmount);
				team.getPlayers().add(player);

				if (player.getCountry().equals("IND")) {
					team.setIndianPlayersCount(team.getIndianPlayersCount() + 1);
				} else {
					team.setForeignPlayersCount(team.getForeignPlayersCount() + 1);
				}

				player.setSoldPrice(currentBidAmount);
				player.setTeamId(team.getTeamId());

			} else {

				System.out
						.println("###############################################################################");

				System.out.println("\t\t" + player.getName() + " not sold ");
				System.out
						.println("###############################################################################\n\n");
				unsoldPlayers.add(player);
			}

		}

	}

	private boolean checkIsTeamEligible(FranchiseTeam team, Player player) {

		boolean isEligible = true;

		if (team.getBalanceAamount() < player.getBasePrice()) {

			isEligible = false;
		} else if (!"IND".equals(player.getCountry())
				&& team.getForeignPlayersCount() >= rule
						.getMaxForeignPlayersCount()) {
			isEligible = false;
		}

		return isEligible;

	}

	private boolean isAuctionDataValid() {

		boolean isEveryThingok = true;

		for (FranchiseTeam team : teams) {

			if (team.getIndianPlayersCount() < rule.getMinIndianPlayersCount()) {
				isEveryThingok = false;
				break;
			} else if ((team.getIndianPlayersCount() + team
					.getForeignPlayersCount()) < rule.getMinPlayers()) {
				isEveryThingok = false;
				break;
			} else if (team.getForeignPlayersCount() > rule
					.getMaxForeignPlayersCount()) {
				isEveryThingok = false;
				break;
			}

		}

		return isEveryThingok;
	}

	private void updateTeam_PlayerDataToDB() {

		playerDao.updatePlayersTeamAndSoldPrice(players, teams);

	}
}
