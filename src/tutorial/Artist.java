package tutorial;

public class Artist {
	public String firstName;
	public String lastName;
	protected String birthDate;
	protected String birthCity;
	protected String deathDate;
	protected String deathCity;

	public Artist() {

	}

	public Artist(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Artist(String firstName, String lastName, String birthDate, String birthCity, String deathDate, String deathCity) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.birthCity = birthCity;
		this.deathDate = deathDate;
		this.deathCity = deathCity;
	}

	public String getBirthCity(){
		return birthCity;
	}

	public void setBirthCity(String birthCity){
		this.birthCity=birthCity;
	}

	public void displayArtistBio(){
		System.out.println("Artist Name: "+firstName+" "+lastName);
		System.out.println("Born: "+birthDate+" "+birthCity);
		if (deathDate != ""){
			System.out.println("Died: "+deathDate+" "+deathCity);
		}
	}
}
