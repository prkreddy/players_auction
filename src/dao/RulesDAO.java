package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import pojo.Rule;
import util.DBConnectUtil;
import util.PropertiesUtil;

public class RulesDAO {

	public Rule getRules() {

		Connection conn = DBConnectUtil.getConnection();
		Properties props = PropertiesUtil.getProperties();

		Rule rule = null;
		Statement stmt = null;
		if (conn != null) {

			try {
				stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(props
						.getProperty("getRules_qry"));
				rule = getRule(rs);

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

		return rule;

	}

	private Rule getRule(ResultSet rs) throws SQLException {

		Rule rule = null;

		if (rs != null) {

			int i = 0;
			if (rs.next()) {

				rule = new Rule();

				rule.setRuleId(rs.getInt(++i));
				rule.setMaxForeignPlayersCount(rs.getInt(++i));
				rule.setMinIndianPlayersCount(rs.getInt(++i));
				rule.setMinPlayers(rs.getInt(++i));
				rule.setPerFranchiseAmount(rs.getInt(++i));

			}
			if (rs != null)
				rs.close();
		}
		return rule;

	}

}
