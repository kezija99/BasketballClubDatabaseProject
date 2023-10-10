package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Competition {
	
	private Integer competitionId;
	private String Name;
	
	public Competition(Integer competitionId, String name) {
		super();
		this.competitionId = competitionId;
		Name = name;
	}

	public Competition() {
		super();
	}

	public static String getCompetitionNameFromId(int id) {
		String competitionName = "";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select Name from basketballclubdb.Competition where competitionId=?";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
            	competitionName = resultSet.getString("Name"); 
            	return competitionName;
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
	
	public static int getCompetitionIdFromName(String name) {
		
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select competitionId from basketballclubdb.Competition where Name=?";
        try {
            //connection = ConnectionPool.getInstance().checkOut();
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
            	int competitionId = resultSet.getInt("competitionId"); 
            	return competitionId;
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

	public Integer getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(Integer competitionId) {
		this.competitionId = competitionId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}
