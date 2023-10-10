package Model;

public class Season_and_Competition {
	
	private Integer seasonId;
	private Integer competitionId;
	
	public Season_and_Competition(Integer seasonId, Integer competitionId) {
		super();
		this.seasonId = seasonId;
		this.competitionId = competitionId;
	}

	public Season_and_Competition() {
		super();
	}

	public Integer getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Integer seasonId) {
		this.seasonId = seasonId;
	}

	public Integer getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(Integer competitionId) {
		this.competitionId = competitionId;
	}
}
