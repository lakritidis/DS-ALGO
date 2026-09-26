package lists;

public class DoublyNodeList {
	protected int nofElements;		// πλήθος των αποθηκευμένων στοιχείων-κόμβων
	protected DNode head, tail;		// δείκτες προς κεφαλή και ουρά

	// Constructor; χρόνος Ο(1)
	public DoublyNodeList() {
		nofElements = 0;
		head = new DNode(null, null, null);	// δημιουργία κεφαλής
		tail = new DNode(head, null, null);	// δημιουργία ουράς
		head.setNext(tail);			// στην αρχή η κεφαλή δείχνει την ουρά…
	}

	// Απλοί μέθοδοι αντλήσεως πληροφορίας
	public int size() { // χρόνος O(1) 
  		return nofElements; 
	}

	public boolean isEmpty() { // χρόνος O(1) 
  		return nofElements < 1;
	}

	// Ελέγχει αν ο v είναι ο πρώτος κόμβος - χρόνος O(1)
	public boolean isFirst(DNode v) { 
		return (v.getPrev() == head);
	}

	// Επιστρέφει τον πρώτο κόμβο σε χρόνο O(1)
	public DNode first() {
		if (isEmpty()) {
			System.out.println("Η λίστα είναι άδεια");
			return head;
		} else {
			return head.getNext();
		}
	}

	// Επιστρέφει τον τελευταίο κόμβο σε χρόνο O(1)
	public DNode last() {
		if (isEmpty()) {
			System.out.println ("Η λίστα είναι άδεια");
			return tail;
		} else {
			return tail.getPrev();
		}
	}

	// Εισαγωγή κόμβου πριν από κόμβο - χρόνος O(1)
	public DNode insertBefore(DNode p, Object element) {
		nofElements++;
		DNode q = null;
		// Xρειάζεται λίγη προσοχή, καθώς ο q θα γίνει ο νέος πρώτος κόμβος
		if (p == head) {
			q = new DNode(head, head.getNext(), element);
			head.getNext().setPrev(q);
			head.setNext(q);
		} else {
			q = new DNode(p.getPrev(), p, element);
			p.getPrev().setNext(q);
			p.setPrev(q);
		}
		return q;
	}

	// Εισαγωγή κόμβου μετά από κόμβο - χρόνος O(1)
	public DNode insertAfter(DNode p, Object element){ // χρόνος O(1) 
		nofElements++;
		DNode q = null;
		if (p == tail) { // χρειάζεται λίγο παραπάνω προσοχή
			q = new DNode(tail.getPrev(), tail, element);
			tail.getPrev().setNext(q);
			tail.setPrev(q);
		} else {
			q = new DNode(p, p.getNext(), element);
			p.getNext().setPrev(q);
			p.setNext(q);
		}
		return q;
	}

	// Διαγραφή κόμβου - Χρόνος O(1) 
	public Object remove(DNode p) {
		if ((p == head) || (p == tail)) {
			System.out.println ("Δεν μπορείτε να σβήσετε την κεφαλή ή την ουρά");
			return null;
		}

		nofElements--;
		DNode pPrev = p.getPrev();	// Βοηθητικός δείκτης στον προηγούμενο του p
		DNode pNext = p.getNext();	// Βοηθητικός δείκτης στον επόμενο του p
		pPrev.setNext(pNext);
		pNext.setPrev(pPrev);
				
		p.setNext(null);	 // «Γείωση» των δεικτών του p, ώστε να απομονωθεί
		p.setPrev(null);
		return p.getElement();	// Το αποθηκευμένο στοιχείο του κόμβου
	}

	// Πράξη push: Εισαγωγή αντικειμένου στην κορυφή της ουράς.
	public void push(Object o) {
		DNode second = head.getNext();             // ο κορυφαίος που θα γίνει δεύτερος
		DNode first = new DNode(head, second, o);  // ο νέος κορυφαίος
		second.setPrev(first);	                   // ο νέος δεύτερος προς τον νέο πρώτο
		head.setNext(first);	                   // η κορυφή προς τον πρώτο
		nofElements++;
	} 

	// Πράξη pop: Επιστρέφει το κορυφαίο στοιχείο αφαιρώντας το.
	public Object pop() {
		if (isEmpty()) {
			System.out.println("H διπλοουρά είναι κενή");
			return null;
		}
		DNode first = head.getNext();    // Ο πρώτος κόμβος
		DNode second = first.getNext();  // Ο δεύτερος 
		head.setNext(second);	         // Ο οποίος γίνεται
		second.setPrev(head);	         // πρώτος
		first.setNext(null);
		first.setPrev(null);
		nofElements--;			         // Προσαρμογή του μεγέθους
		return first.getElement();	     // Επιστροφή της παλιάς κεφαλής
	}

	// Πράξη inject: τοποθετεί ένα νέο στοιχείο στο τέλος της ουράς.
	public void inject(Object o) {
		// Ο τελευταίος που θα γίνει προτελευταίος.
		DNode secondtolast = tail.getPrev();

		// Ο νέος τελευταίος.
		DNode last = new DNode(tail, secondtolast, o);

		// ο νέος προτελευταίος προς τον νέο τελευταίο.
		secondtolast.setNext(last);

		// Η ουρά προς τον νέο τελευταίο.
		tail.setPrev(last);

		// Προσαρμογή του μεγέθους.
		nofElements++;
	}

	// Πράξη Eject: Επιστρέφει το τελευταίο στοιχείο της διπλο-ουράς, αφαιρώντας το.
	public Object eject() {
		if (isEmpty()) {
			System.out.println("H διπλοουρά είναι κενή");
			return null;
		}

		// O τελευταίος κόμβος.
		DNode last = tail.getPrev();

		// O δεύτερος πριν το τέλος.
		DNode secondtolast = last.getPrev();

		// O οποίος γίνεται τελευταίος.
		tail.setPrev(secondtolast);
		secondtolast.setNext(tail);

		// Γείωση
		last.setPrev(null);
		last.setNext(null);
		nofElements--;		// Προσαρμογή του μεγέθους.

		// Επιστροφή του στοιχείου του παλιού τέλους.
		return last.getElement();
	}

}
