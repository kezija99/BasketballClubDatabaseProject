package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Hall {

	private Integer hallId;
	private String Name;
	private int Capacity;
	private String Address;
	
	public Hall(String name, int capacity, String address) {
		super();
		Name = name;
		Capacity = capacity;
		Address = address;
	}

	
	public Hall() {
		super();
	}


	public Hall(String name) {
		super();
		Name = name;
	}


	public static Hall getHallFromId(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select * from basketballclubdb.Hall where hallId=?";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
            	Hall h = new Hall(resultSet.getString("Name"), resultSet.getInt("Capacity"), resultSet.getString("Address")); 
            	return h;
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
	
	public static int getHallIdFromName(String hallName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select hallId from basketballclubdb.Hall where Name=?";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setString(1, hallName);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
            	int hallId = resultSet.getInt("hallId");
            	return hallId;
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
	
	public Integer getHallId() {
		return hallId;
	}


	public void setHallId(Integer hallId) {
		this.hallId = hallId;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public int getCapacity() {
		return Capacity;
	}


	public void setCapacity(int capacity) {
		Capacity = capacity;
	}


	public String getAddress() {
		return Address;
	}


	public void setAddress(String address) {
		Address = address;
	}


	public String toString() {
		return hallId.toString() + " | " + Name + " | " + Capacity + " | " + Address;
	}
}
