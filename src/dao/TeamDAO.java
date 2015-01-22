package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import pojo.FranchiseTeam;
import pojo.Player;
import util.DBConnectUtil;
import util.PropertiesUtil;

public class TeamDAO {

	public List<FranchiseTeam> getAllTeams() {

		List<FranchiseTeam> teams = new ArrayList<FranchiseTeam>();

		Connection conn = DBConnectUtil.getConnection();

		Properties props = PropertiesUtil.getProperties();

		Statement stmt = null;

		if (conn != null) {
			FranchiseTeam team = null;
			try {
				stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(props
						.getProperty("getAllTeams_qry"));

				int i = 0;

				while (rs.next()) {
					i = 0;
					team = new FranchiseTeam();
					team.setTeamId(rs.getInt(++i));
					team.setName(rs.getString(++i));
					team.setOwnerName(rs.getString(++i));
					team.setBalanceAamount(rs.getLong(++i));
					team.setCity(rs.getString(++i));
					team.setCoachName(rs.getString(++i));
					team.setForeignPlayersCount(rs.getInt(++i));
					team.setIndianPlayersCount(rs.getInt(++i));

					teams.add(team);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return teams;
	}

	public void displayTeams(List<FranchiseTeam> teams) {
		for (FranchiseTeam team : teams) {

			System.out.println();
			System.out.println(team);
		}
	}
	
	

}
