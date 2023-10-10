package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Season {
	
	private Integer seasonId;
	private String SeasonMark;
	
	public Season(String seasonMark) {
		super();
		SeasonMark = seasonMark;
	}

	public Season() {
		super();
	}

	public static String getSeasonNameFromId(int id) {
		String seasonName = "";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select SeasonMark from basketballclubdb.season where seasonId=?";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
            	seasonName = resultSet.getString("SeasonMark"); 
            	return seasonName;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        }
        return "";
	}
	
	public static int getSeasonIdFromName(String name) {
		
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select seasonId from basketballclubdb.season where SeasonMark=?";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
            	int seasonId = resultSet.getInt("seasonId"); 
            	return seasonId;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        }
        return 0;
	}

	public Integer getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Integer seasonId) {
		this.seasonId = seasonId;
	}

	public String getSeasonMark() {
		return SeasonMark;
	}

	public void setSeasonMark(String seasonMark) {
		SeasonMark = seasonMark;
	}
}
