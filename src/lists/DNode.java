package lists;

public class DNode {
	private DNode prev, next;     // οι κόμβοι πριν και μετά
	private Object element;       // το αποθηκευμένο στοιχείο

	// Constructor
	public DNode (DNode nodePrev, DNode nodeNext, Object nodeElement) {
		prev = nodePrev;
		next = nodeNext;
		element = nodeElement;
	}

	// Πρόσβαση στην Πληροφορία
	// επιστροφή του στοιχείου
	public Object getElement() {
		return element;
	}

	// Επιστροφή του προηγούμενου κόμβου
	public DNode getPrev() {
		return prev;
	}

	// Επιστροφή του επόμενου κόμβου
	public DNode getNext() {
		return next;
	}

	// Αλλαγή Πληροφορίας
	// ορισμός στοιχείου
	public void setElement(Object newElement) {
		element = newElement;
	}

	// ορισμός προηγούμενου δείκτη
	public void setPrev(DNode newPrev) {
		prev = newPrev;
	}

	// ορισμός επόμενου δείκτη
	public void setNext(DNode newNext) {
		next = newNext;
	}
}
