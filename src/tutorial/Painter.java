package tutorial;

public class Painter extends Artist { /* Painter παιδί της Artist */
	private int numberOfPaints;
	private String style;

	public Painter(String firstName, String lastName, String birthDate, String birthCity, String deathDate, String deathCity, int numberOfPaints, String style) {
		this.numberOfPaints = 0;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.birthCity = birthCity;
		this.deathDate = deathDate;
		this.deathCity = deathCity;
		this.numberOfPaints = numberOfPaints;
		this.style = style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStyle(){
		return style;
	}
}
