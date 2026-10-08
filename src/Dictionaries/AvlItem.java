package Dictionaries;


// Η κλάση των στοιχείων που αποθηκεύει ένας κόμβος ενός δένδρου ΑVL
public class AvlItem extends Item {
	private int height; // Το ύψος του στοιχείου

	// Constructor
	protected AvlItem(Object k, Object i){
		super(k, i);
		height = 1;
	}

	// Επιστρέφει το ύψος
	public int getHeight() {
		return height;
	}

	// Θέτει το ύψος
	public void setHeight(int h) {
		height = h;
	}
}