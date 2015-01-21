package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import pojo.CenturiesPerf;
import pojo.Player;
import pojo.WicketsPerf;
import util.DBConnectUtil;
import util.PropertiesUtil;

public class PlayerDAO {

	public void insertPlayers(List<Player> players) {

		Connection conn = DBConnectUtil.getConnection();

		Properties props = PropertiesUtil.getProperties();

		PreparedStatement stmt1 = null;

		PreparedStatement stmt2 = null;

		PreparedStatement stmt3 = null;

		if (conn != null) {

			try {
				stmt1 = conn.prepareStatement(
						props.getProperty("player_insert_qry"),
						Statement.RETURN_GENERATED_KEYS);

				stmt2 = conn.prepareStatement(props
						.getProperty("batting_stats_insert_qry"));

				stmt3 = conn.prepareStatement(props
						.getProperty("bowling_stats_insert_qry"));

				int insertPlayerFlag = -1;
				ResultSet rs = null;

				int player_keyvalue = -1;
				int i = 0;

				conn.setAutoCommit(false);
				for (Player player : players) {

					i = 0;
					stmt1.setString(++i, player.getName());
					stmt1.setString(++i, player.getCountry());
					stmt1.setLong(++i, player.getBasePrice());
					stmt1.setString(++i, player.getType());

					insertPlayerFlag = stmt1.executeUpdate();

					if (insertPlayerFlag > 0) {
						insertPlayerFlag = -1;
						rs = stmt1.getGeneratedKeys();

						if (rs.next()) {
							player_keyvalue = rs.getInt(1);

							i = 0;
							stmt2.setInt(++i, player_keyvalue);
							stmt2.setInt(++i, player.getCenturies().getOdi());
							stmt2.setInt(++i, player.getCenturies().getTest());
							stmt2.setInt(++i, player.getCenturies().getT20());

							stmt2.executeUpdate();

							i = 0;
							stmt3.setInt(++i, player_keyvalue);
							stmt3.setInt(++i, player.getWickets().getOdi());
							stmt3.setInt(++i, player.getWickets().getTest());
							stmt3.setInt(++i, player.getWickets().getT20());

							stmt3.executeUpdate();

							conn.commit();

						}

						if (rs != null)
							rs.close();
					}

				}
				System.out.println("records inserted successfully");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("records failed in insertion");
				e.printStackTrace();
			} finally {

				try {
					if (stmt1 != null)
						stmt1.close();

					if (stmt2 != null)
						stmt2.close();

					if (stmt3 != null)
						stmt3.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	public List<Player> getAllPlayers() {

		Properties props = PropertiesUtil.getProperties();

		return getPlayersByQuery(props.getProperty("getAllPlyers_qry"));

	}

	public List<Player> getAllPlayerOrderbyBasePriceDesc() {
		Properties props = PropertiesUtil.getProperties();

		return getPlayersByQuery(props.getProperty("getAllPlyers_qry")
				+ " order by p.baseprice desc ");
	}

	private List<Player> getPlayersByQuery(String query) {

		Connection conn = DBConnectUtil.getConnection();

		List<Player> players = null;
		Statement stmt = null;
		if (conn != null) {

			try {
				stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(query);
				players = getPlayers(rs);

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return players;

	}

	public List<Player> getPlayersByTeam(String countryCode) {
		Connection conn = DBConnectUtil.getConnection();

		Properties props = PropertiesUtil.getProperties();

		List<Player> players = null;
		PreparedStatement stmt = null;
		if (conn != null) {

			try {
				stmt = conn
						.prepareStatement(props.getProperty("getAllPlyers_qry")
								+ " and p.country = ?");

				stmt.setString(1, countryCode);
				ResultSet rs = stmt.executeQuery();
				players = getPlayers(rs);

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				if (stmt != null)
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

		}

		return players;

	}

	private List<Player> getPlayers(ResultSet rs) throws SQLException {

		List<Player> players = null;

		if (rs != null) {

			players = new ArrayList<Player>();

			Player player = null;
			int i = 0;
			while (rs.next()) {

				i = 0;
				player = new Player(new CenturiesPerf(), new WicketsPerf());

				player.setId(rs.getInt(++i));
				player.setName(rs.getString(++i));
				player.setCountry(rs.getString(++i));
				player.setBasePrice(rs.getLong(++i));
				player.setType(rs.getString(++i));
				player.setSoldPrice(rs.getInt(++i));
				int teamId = rs.getInt(++i);
				if (rs.wasNull())
					player.setTeamId(null);
				else
					player.setTeamId(teamId);
				player.getCenturies().setOdi(rs.getInt(++i));
				player.getCenturies().setTest(rs.getInt(++i));
				player.getCenturies().setT20(rs.getInt(++i));
				player.getWickets().setOdi(rs.getInt(++i));
				player.getWickets().setTest(rs.getInt(++i));
				player.getWickets().setT20(rs.getInt(++i));
				players.add(player);
			}
			if (rs != null)
				rs.close();
		}
		return players;

	}

}
