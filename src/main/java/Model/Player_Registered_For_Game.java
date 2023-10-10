package Model;


public class Player_Registered_For_Game {
	
	private Integer personId;
	private Integer gameId;
	
	private int Points;
	private int Assists;
	private int Rebounds;
	private int Steals;
	private int Blocks;
	private double MinutesPlayed;
	private int Rating;
	
	public Player_Registered_For_Game(Integer personId, Integer gameId, int points, int assists, int rebounds,
			int steals, int blocks, double minutesPlayed, int rating) {
		super();
		this.personId = personId;
		this.gameId = gameId;
		Points = points;
		Assists = assists;
		Rebounds = rebounds;
		Steals = steals;
		Blocks = blocks;
		MinutesPlayed = minutesPlayed;
		Rating = rating;
	}

	public Player_Registered_For_Game() {
		super();
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public int getPoints() {
		return Points;
	}

	public void setPoints(int points) {
		Points = points;
	}

	public int getAssists() {
		return Assists;
	}

	public void setAssists(int assists) {
		Assists = assists;
	}

	public int getRebounds() {
		return Rebounds;
	}

	public void setRebounds(int rebounds) {
		Rebounds = rebounds;
	}

	public int getSteals() {
		return Steals;
	}

	public void setSteals(int steals) {
		Steals = steals;
	}

	public int getBlocks() {
		return Blocks;
	}

	public void setBlocks(int blocks) {
		Blocks = blocks;
	}

	public double getMinutesPlayed() {
		return MinutesPlayed;
	}

	public void setMinutesPlayed(double minutesPlayed) {
		MinutesPlayed = minutesPlayed;
	}

	public int getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}
	
}
