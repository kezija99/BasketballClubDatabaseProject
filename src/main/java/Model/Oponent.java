package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Oponent {
	
	private Integer oponentId;
	private String Name;
	private Hall hall;
	private boolean Exist;
	
	public Oponent(String name, boolean exist) {
		super();
		Name = name;
		Exist = exist;
	}

	public Oponent() {
		super();
	}

	public static Oponent getOponentFromId(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select * from basketballclubdb.Oponent where oponentId=?";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
            	Oponent p = new Oponent(resultSet.getString("Name"), resultSet.getBoolean("Exist")); 
            	return p;
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
        return null;
	}
	
	public static int getOponentIdFromName(String oponentName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select oponentId from basketballclubdb.Oponent where Name=?";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setString(1, oponentName);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
            	int oponentId = resultSet.getInt("oponentId");
            	return oponentId;
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
	
	public Integer getOponentId() {
		return oponentId;
	}

	public void setOponentId(Integer oponentId) {
		this.oponentId = oponentId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public boolean isExist() {
		return Exist;
	}

	public void setExist(boolean exist) {
		Exist = exist;
	}

	public String toString() {
		return oponentId.toString() + " | " + Name + " | " + hall.toString();
	}
}
