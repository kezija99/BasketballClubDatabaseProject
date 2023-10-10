package Model;

public class City {
	
	private Integer cityId;
	private String Name;
	private Integer countryId = null;
	
	public City(String name) {
		super();
		Name = name;
	}
	
	public City() {
		super();
	}

	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
}
