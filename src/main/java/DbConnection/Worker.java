package DbConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Model.Administrator;
import Model.Hall;
import Model.Player;
import Model.Oponent;
import Model.Season_and_Competition;
import Model.Game;

public class Worker {
	
	
	public static List<Game> getAllGames(){
		List<Game> allGames = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select * from basketballclubdb.game where game.exist=?";
        try{
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setBoolean(1, true);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
            	int gameId = resultSet.getInt("gameId");
                LocalDate date = resultSet.getDate("GameDate").toLocalDate();
                int clubPoints = resultSet.getInt("ClubPoints");
                int oponentPoints = resultSet.getInt("OponentPoints");
                boolean home = resultSet.getBoolean("Home");
                boolean active = resultSet.getBoolean("Exist");
                String outcome = resultSet.getString("Score");
                Game u = new Game(date, clubPoints, oponentPoints, home, active, outcome);
                u.setSeason_and_competition(new Season_and_Competition(resultSet.getInt("seasonId"), resultSet.getInt("competitionId")));
                u.setOponent(Oponent.getOponentFromId(resultSet.getInt("oponentId")));
                u.setHall(Hall.getHallFromId(resultSet.getInt("hallId")));
                u.setGameId(gameId);
                allGames.add(u);
            }
        } catch (Exception ex) {
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
        return allGames;
	}
	
    public static void addNewGame(LocalDate date, int clubPoints, int oponentPoints, boolean home, int oponenentId, int seasonId, 
    		int competitionId, int hallId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "insert into Game (GameDate, ClubPoints, OponentPoints, Home, oponentId," +
                "seasonId, competitionId, hallId, Exist)VALUES(?,?,?,?,?,?,?,?,?)";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setDate(1, Date.valueOf(date));
            statement.setInt(2, clubPoints);
            statement.setInt(3, oponentPoints);
            statement.setBoolean(4, home);
            statement.setInt(5, oponenentId);
            statement.setInt(6, seasonId);
            statement.setInt(7, competitionId);
            statement.setInt(8, hallId);
            statement.setBoolean(9, true);
            statement.executeUpdate();
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
    }
    
    public static void updateGame(int gameId, LocalDate date, int clubPoints, int oponentPoints){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "call updateGame(?,?,?,?)";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setDate(2, Date.valueOf(date));
            statement.setInt(3, clubPoints);
            statement.setInt(4, oponentPoints);
            statement.setInt(1, gameId);
            statement.execute();
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
    }
    
    public static void deleteGame(int gameId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "update Game set Exist=? where basketballclubdb.Game.gameId=?";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setBoolean(1, false);
            statement.setInt(2, gameId);
            statement.execute();
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
    }
    
	public static List<Game> getFilteredGames(List<Game> gamesObject, int seasonId, int competitionId) {
		List<Game> filteredGames = new ArrayList<>();
		
		for(Game u : gamesObject) {
			if(u.getSeason_and_competition().getSeasonId() == seasonId && u.getSeason_and_competition().getCompetitionId() == competitionId && u.isExist())
				filteredGames.add(u);
		}
		
		return filteredGames;
	}
	
    public static List<String> getSeasonNames(){
    	List<String> seasonNames = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "select SeasonMark from basketballclubdb.Season";
        try{
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String seasonName = resultSet.getString("SeasonMark");
                seasonNames.add(seasonName);
            }
        } catch (Exception ex) {
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
        return seasonNames;
    }
    
    public static List<String> getCompetitionNames(){
    	List<String> competitionNames = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "select Name from basketballclubdb.Competition";
        try{
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String competitionName = resultSet.getString("Name");
                competitionNames.add(competitionName);
            }
        } catch (Exception ex) {
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
        return competitionNames;
    }
    
    public static List<String> getHallNames(){
    	List<String> hallNames = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "select Name from basketballclubdb.Hall";
        try{
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String hallName = resultSet.getString("Name");
                hallNames.add(hallName);
            }
        } catch (Exception ex) {
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
        return hallNames;
    }
    
    public static List<String> getOponentNames(){
    	List<String> oponentNames = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "select Name from basketballclubdb.Oponent";
        try{
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String oponentName = resultSet.getString("Name");
                oponentNames.add(oponentName);
            }
        } catch (Exception ex) {
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
        return oponentNames;
    }
    
    public static List<Season_and_Competition> getSeasonsAndCompetitions(){
    	List<Season_and_Competition> SeasonsAndCompetitions = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "select * from basketballclubdb.Season_and_Competition";
        try{
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                Season_and_Competition SeasonAndCompetition = new Season_and_Competition(resultSet.getInt("seasonId"), resultSet.getInt("competitionId"));
                SeasonsAndCompetitions.add(SeasonAndCompetition);
            }
        } catch (Exception ex) {
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
        return SeasonsAndCompetitions;
    }
    
    public static List<Integer> getRegisteredPlayersIds(int gameId){
    	List<Integer> registeredPlayersIds = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select * from basketballclubdb.player_registered_for_game where gameId=?";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setInt(1, gameId);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
            	registeredPlayersIds.add(resultSet.getInt("personId"));
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
        return registeredPlayersIds;
    }
    
    public static List<Integer> getActivePlayersIds(){
    	List<Integer> activePlayersIds = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select personId from basketballclubdb.Player where Active=?";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setBoolean(1, true);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
            	activePlayersIds.add(resultSet.getInt("personId"));
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
        return activePlayersIds;
    }
    
    public static List<Player> getAllPlayers(){
    	List<Player> allPlayers = new ArrayList<>();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String query = "call getAllPlayers()";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
        	statement = connection.prepareCall(query);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
            	int playerId = resultSet.getInt("personId");
            	String name = resultSet.getString("Name");
            	String surname = resultSet.getString("Surname");
            	String nacionality = resultSet.getString("Nacionality");
            	short height = resultSet.getShort("Height");
            	double weight = resultSet.getDouble("Weight");
            	int jerseyNumber = resultSet.getInt("JerseyNumber");
            	boolean active = resultSet.getBoolean("Active");
            	LocalDate birthDate = resultSet.getDate("DateOfBirth").toLocalDate();
            	allPlayers.add(new Player(playerId, name, surname, nacionality, height, weight, jerseyNumber, active, true, birthDate));
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
        return allPlayers;
    }
    
    public static void registerPlayer(int gameId, int personId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "insert into player_registered_for_game (gameId, personId)VALUES(?,?)";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setInt(1, gameId);
            statement.setInt(2, personId);
            statement.executeUpdate();
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
    }
    
    public static void addNewPlayer(String name, String surname, LocalDate date, String nacionality, short height, double weight,
			int jerseyNumber){
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String query = "call addNewPlayer(?,?,?,?,?,?,?)";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
        	statement = connection.prepareCall(query);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, nacionality);
            statement.setDate(4, Date.valueOf(date));
            statement.setShort(5, height);
            statement.setDouble(6, weight);
            statement.setInt(7, jerseyNumber);
            statement.executeUpdate();
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
    }
    
    public static void updatePlayer(int personId, short height, double weight, int jerseyNumber, boolean act){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "update Player set Height=?, Weight=?, JerseyNumber=?, Active=? where basketballclubdb.Player.personId=?";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setShort(1, height);
            statement.setDouble(2, weight);
            statement.setInt(3, jerseyNumber);
            statement.setBoolean(4, act);
            statement.setInt(5, personId);
            statement.execute();
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
    }
    
    public static void updatePerson(int personId, String newName, String newSurname, short height, double weight, int jerseyNumber, boolean act){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "update Person set Name=?, Surname=? where basketballclubdb.Person.personId=?";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setString(1, newName);
            statement.setString(2, newSurname);
            statement.setInt(3, personId);
            statement.execute();
            updatePlayer(personId, height, weight, jerseyNumber, act);
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
    }
    
    public static void deletePlayer(int playerId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "update Player set Exist=? where basketballclubdb.Player.personId=?";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setBoolean(1, false);
            statement.setInt(2, playerId);
            statement.execute();
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
    }
    
    public static List<Oponent> getAllOponents(){
    	List<Oponent> allOponents = new ArrayList<>();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String query = "call getAllOponents()";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
        	statement = connection.prepareCall(query);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
            	String oponentName = resultSet.getString("p.Name");
            	String hallName = resultSet.getString("h.Name");
            	Oponent p = new Oponent(oponentName, true);
            	p.setOponentId(resultSet.getInt("oponentId"));
            	p.setHall(new Hall(hallName));
            	allOponents.add(p);
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
        return allOponents;
    }
    
    public static void addNewOponent(String oponentName, int hallId, boolean exists){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "insert into Oponent (Name, hallId, Exist)VALUES(?,?,?)";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setString(1, oponentName);
            statement.setInt(2, hallId);
            statement.setBoolean(3, exists);
            statement.executeUpdate();
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
    }
    
    public static void updateOponent(int oponentId, String name, int hallId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "update Oponent set Name=?, hallId=? where basketballclubdb.Oponent.oponentId=?";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, hallId);
            statement.setInt(3, oponentId);
            statement.execute();
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
    }
    
    public static void deleteOponent(int oponentId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "update Oponent set Exist=? where basketballclubdb.Oponent.oponentId=?";
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketballclubdb", "root", "root");
            statement = connection.prepareStatement(query);
            statement.setBoolean(1, false);
            statement.setInt(2, oponentId);
            statement.execute();
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
    }
    
    public static boolean login(String username, String password){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "select * from basketballclubdb.administrator";
        ArrayList<Administrator> adminList = new ArrayList<>();
        try{
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                Administrator admin = new Administrator();
                admin.setPassword(resultSet.getString("Username"));
                admin.setUsername(resultSet.getString("Password"));
                adminList.add(admin);
            }
        } catch (Exception ex) {
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
            ConnectionPool.getInstance().checkIn(connection);
        }
        for(Administrator administrator : adminList){
            if(administrator.getUsername().equals(username) && administrator.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    
}
