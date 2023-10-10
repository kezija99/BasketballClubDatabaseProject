package Model;

import java.time.LocalDate;

public class Game {

	private Integer gameId;
	private LocalDate GameDate;
	private int ClubPoints;
	private int OponentPoints;
	private boolean Home;
	private boolean Exist;
	private Hall hall;
	private Season_and_Competition season_and_competition;
	private Oponent oponent;
	private String Score;
	
	
	
	public Game(LocalDate gameDate, int clubPoints, int oponentPoints, boolean home, boolean exist, String score) {
		super();
		GameDate = gameDate;
		ClubPoints = clubPoints;
		OponentPoints = oponentPoints;
		Home = home;
		Exist = exist;
		Score = score;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public LocalDate getGameDate() {
		return GameDate;
	}

	public void setGameDate(LocalDate gameDate) {
		GameDate = gameDate;
	}

	public int getClubPoints() {
		return ClubPoints;
	}

	public void setClubPoints(int clubPoints) {
		ClubPoints = clubPoints;
	}

	public int getOponentPoints() {
		return OponentPoints;
	}

	public void setOponentPoints(int oponentPoints) {
		OponentPoints = oponentPoints;
	}

	public boolean isHome() {
		return Home;
	}

	public void setHome(boolean home) {
		Home = home;
	}

	public boolean isExist() {
		return Exist;
	}

	public void setExist(boolean exist) {
		Exist = exist;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Season_and_Competition getSeason_and_competition() {
		return season_and_competition;
	}

	public void setSeason_and_competition(Season_and_Competition season_and_competition) {
		this.season_and_competition = season_and_competition;
	}

	public Oponent getOponent() {
		return oponent;
	}

	public void setOponent(Oponent oponent) {
		this.oponent = oponent;
	}

	public String getScore() {
		return Score;
	}

	public void setScore(String score) {
		Score = score;
	}
	
}
