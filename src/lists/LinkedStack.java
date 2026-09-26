package lists;

public class LinkedStack {
	private SNode top;		// Αναφορά προς τον κορυφαίο κόμβο.
	private int size;		// Πλήθος στοιχείων της στοίβας.
	public LinkedStack() {	// Αρχικοποιεί μία άδεια στοίβα.
		top = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return (size < 1);
	}

	public void push(Object elem) { // Χρόνος Ο(1)
		SNode x = new SNode(top, elem);
		top = x;
		size++;
	}

	// Επιστρέφει το κορυφαίο στοιχείο - Χρόνος Ο(1)
	public Object top() {
		if (isEmpty()) {
			System.out.println("Η στοίβα είναι άδεια");
			return null;
		}
		return top.getElement();
	}

	// Επιστρέφει το κορυφαίο στοιχείο αφαιρώντας το - Χρόνος Ο(1)
	public Object pop() {
		if (isEmpty()) {
		    System.out.println("Η στοίβα είναι άδεια");
		    return null;
		}
		SNode oldtop;
		oldtop = top;
		top = top.getNext(); // το νέο κορυφαίο στοιχείο
		oldtop.setNext(null);	// γείωση
		size--;
		return oldtop.getElement(); // επιστροφή του στοιχείου της παλιάς κορυφής
	}
}