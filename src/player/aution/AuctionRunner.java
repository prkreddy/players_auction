package player.aution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import pojo.FranchiseTeam;
import pojo.Player;
import dao.PlayerDAO;
import dao.TeamDAO;

public class AuctionRunner {

	PlayerDAO playerDao;

	TeamDAO teamDao;

	List<FranchiseTeam> teams = null;

	List<Player> players = null;

	List<Player> unsoldPlayers = null;

	BufferedReader br = null;

	public void init() {

		br = new BufferedReader(new InputStreamReader(System.in));

		teamDao = new TeamDAO();
		playerDao = new PlayerDAO();

		teams = teamDao.getAllTeams();

		players = playerDao.getAllPlayerOrderbyBasePriceDesc();

		unsoldPlayers = new ArrayList<Player>();

	}

	public void runAuction() {

		int teamscount = teams.size();

		FranchiseTeam team = null;

		for (Player player : players) {

			long bidamount = player.getBasePrice();
			int teamResponseCount = teamscount;
			String response = null;

			System.out
					.println("-----------------------------------------------------------------------------------------------");

			int teamRespondedIndex = -1;
			for (int i = 0; teamResponseCount != 0; i = (i + 1) % teamscount) {

				team = teams.get(i);

				System.out.println("\n\n" + player);

				if (teamRespondedIndex == -1)
					System.out
							.println("\n -------------------------------------  StartingBidAmount: "
									+ bidamount
									+ "  -------------------------------------------------");
				else
					System.out
							.println("\n -------------------------------------  currentBidAmount: "
									+ bidamount
									+ "  by "
									+ teams.get(teamRespondedIndex).getName()
									+ "-------------------------------------------");
				System.out.println(team.getName() + " please respond");
				System.out
						.println("enter amount to increase CurrentBidAmount otherwise enter '0' to skip your turn ");

				try {
					response = br.readLine();

					if (!"0".equals(response)) {

						bidamount += Long.parseLong(response);
						teamResponseCount = teamscount;
						teamRespondedIndex = i;
					}

					--teamResponseCount;
				} catch (IOException e) {
					e.printStackTrace();
				} catch (NumberFormatException e) {
					--teamResponseCount;
				}

			}

			if (bidamount != player.getBasePrice()) {

				// update player in db

				System.out
						.println("###############################################################################");
				System.out.println("\t\tTeam "
						+ teams.get(teamRespondedIndex).getName()
						+ " Purchased \n\t\t");
				System.out.println("\t\t" + player.getName() + "  at "
						+ bidamount);
				System.out
						.println("###############################################################################\n\n");
				teamRespondedIndex = -1;
			} else {

				System.out
						.println("###############################################################################");

				System.out.println("\t\t" + player.getName() + " not sold ");
				System.out
						.println("###############################################################################\n\n");
				teamRespondedIndex = -1;
			}

		}

	}
}
