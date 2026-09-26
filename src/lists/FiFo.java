package lists;

public class FiFo {
	protected int nofElements;	// Πλήθος των αποθηκευμένων στοιχείων-κόμβων
	protected SNode head, rear;	// Δείκτες προς κεφαλή και ουρά

	// Constructor - Χρόνος Ο(1)
	public FiFo() {
		nofElements = 0;
		head = null;	// Δημιουργία κεφαλής.
		rear = null;	// Δημιουργία ουράς.
	}

	// Απλοί μέθοδοι αντλήσεως πληροφορίας.
	public int size() {	// Χρόνος O(1) 
		return nofElements; 
	}

	// Έλεγχος αν η FiFo είναι άδεια - Χρόνος O(1).
	public boolean isEmpty() {
		return (nofElements < 1); 
	}

	// Επιστρέφει την κεφαλή της λίστας - Χρόνος O(1).
	public SNode first() {
		if (isEmpty()) {
			System.out.println("Η λίστα είναι άδεια");
		}
		return head;
	}

	// Προσθήκη ενός νέου κόμβου στο τέλος.
	public void enqueue(Object obj) {
		SNode x = new SNode(null, obj);

		// H ουρά είναι αρχικώς άδεια.
		if (isEmpty()) {
			head = x;
		} else {        // O νέος κόμβος τοποθετείται στο τέλος
			rear.setNext(x);
		}
		rear = x;       // Ενημέρωση της νέας ουράς.
		nofElements++;  // Προσαρμογή μεγέθους.
	}

	// Αφαίρεση του πρώτου κόμβου.
	public Object dequeue() {
		SNode temp;
		if (isEmpty()) {
			System.out.println("Η ουρά είναι άδεια"); 
			return null;
		}
		temp = head;
		head = head.getNext();
		temp.setNext(null);
		nofElements--;
		if (nofElements == 0) {
			rear = null;  // η ουρά είναι πλέον άδεια
		}
		return temp.getElement();
	}

}
