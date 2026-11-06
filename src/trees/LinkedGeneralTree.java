package trees;

// Γενική κλάση δυαδικού δένδρου
public class LinkedGeneralTree {
	private TNode Root;		// δείκτης προς την ρίζα του δένδρου
	private int size;		// μέγεθος δένδρου --δεν θα το χρειαστούμε στην άσκηση

	// Constructor 1
	public LinkedGeneralTree() {
		Root = null;
		size = 0;
	}

	// Constructor 2: θέτει την ρίζα ίση με τον κόμβο o του ορίσματος
	public LinkedGeneralTree(TNode o) {
		Root = o;
	}

	// Επιστρέφει το μέγεθος του δέντρου
	public int size() {
		return size; 
	}

	// Επιστρέφει δείκτη προς την ρίζα του δέντρου
	public TNode root() {
		return Root;
	}

	public void setRoot(TNode v) {
		Root = v;
	}

	public void setSize(int s) {
		size = s;
	}

	// Εξετάζει εάν ο κόμβος είναι και ρίζα
	public boolean isRoot(TNode v) {
		return v == Root;
	}
}