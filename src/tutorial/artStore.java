package tutorial;

public class artStore {
	public static void main(String args[]) {
		Artist piccaso = new Artist();
		piccaso.firstName = "Pablo";
		piccaso.lastName = "Piscasso";

		Artist dali = new Artist("Salvador","Dali","May 11 1904","Figueres", "Jan 23 1989","Figueres");
		System.out.println(" ");
		dali.displayArtistBio();
		System.out.println(" ");
		Painter elGreco = new Painter("Dominicos","Theotokopoulos","October 1, 1541","Herakleion","April 7, 1614","Toledo",115,"Mannerism");
		elGreco.displayArtistBio();
		System.out.println("Style : "+elGreco.getStyle());
		System.out.println(" ");
	}
}

