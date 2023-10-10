package Model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

public class Player{
	
	private Integer personId;
	private String Name;
	private String Surname;
	private String Nacionality;
	private short Height;
	private double Weight;
	private int JerseyNumber;
	private boolean Active;
	private boolean Exist;
	private LocalDate DateOfBirth;
	
    public Player(Integer personId, String name, String surname, String nacionality, short height, double weight,
			int jerseyNumber, boolean active, boolean exist, LocalDate dateOfBirth) {
		super();
		this.personId = personId;
		Name = name;
		Surname = surname;
		Nacionality = nacionality;
		Height = height;
		Weight = weight;
		JerseyNumber = jerseyNumber;
		Active = active;
		Exist = exist;
		DateOfBirth = dateOfBirth;
	}

	public Player(Integer personId, String name, String surname) {
		super();
		this.personId = personId;
		Name = name;
		Surname = surname;
	}

	public Player() {
		super();
	}

	public static String getPlayerNameFromId(int playerId){
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String query = "call getPlayerNameFromId(?,?)";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
        	statement = connection.prepareCall(query);
            statement.setInt(1, playerId);
            statement.registerOutParameter(2, Types.VARCHAR);
            resultSet = statement.executeQuery();
            return statement.getString(2);
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
    
    public static int getIdFromPlayerName(String name, String surname){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select personId from basketballclubdb.Person where Name=? and Surname=?";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, surname);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
            	return resultSet.getInt("personId");
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

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getNacionality() {
		return Nacionality;
	}

	public void setNacionality(String nacionality) {
		Nacionality = nacionality;
	}

	public short getHeight() {
		return Height;
	}

	public void setHeight(short height) {
		Height = height;
	}

	public double getWeight() {
		return Weight;
	}

	public void setWeight(double weight) {
		Weight = weight;
	}

	public int getJerseyNumber() {
		return JerseyNumber;
	}

	public void setJerseyNumber(int jerseyNumber) {
		JerseyNumber = jerseyNumber;
	}

	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean active) {
		Active = active;
	}

	public boolean isExist() {
		return Exist;
	}

	public void setExist(boolean exist) {
		Exist = exist;
	}

	public LocalDate getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
}
