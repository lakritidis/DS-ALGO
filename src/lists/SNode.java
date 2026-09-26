package lists;

// Ο κόμβος μιας απλής λίστας
public class SNode {
	private SNode next; 	  // δείκτης προς το επόμενο στοιχείο 
	private Object element;   // το αποθηκευμένο στοιχείο

	// Constructor
	public SNode(SNode nodeNext, Object nodeElement){
		next = nodeNext;
		element = nodeElement;
	}

	// Απλές μέθοδοι ανακτήσεως πληροφορίας για τον κόμβο 
	// Επιστρέφει το στοιχείο
	public Object getElement() {
		return element;
	}

	// Επιστρέφει τον δείκτη προς επόμενο στοιχείο
	public SNode getNext() {
		return next;
	}

	// Απλές μέθοδοι αλλαγής του κόμβου
	// θέτει το στοιχείο
	public void setElement(Object newElement) {
		element = newElement;
	}

	// θέτει τον επόμενο κόμβο
	public void setNext(SNode newNext) {
		next = newNext;
	}
}