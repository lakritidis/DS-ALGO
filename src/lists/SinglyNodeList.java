package lists;

public class SinglyNodeList {
	protected int nofElements;  // πλήθος κόμβων-στοιχείων
	protected SNode head, tail; // δείκτες προς κορυφή και ουρά αντίστοιχα

	// Constructor - Αρχικοποίηση λίστας
	public SinglyNodeList() {
		nofElements = 0;
		head = null;		    // γείωση κεφαλής
		tail = null;		    // γείωση ουράς
	}

	// Πλήθος στοιχείων στη λίστα
	public int size() {
		return nofElements; 
	}

	// Έλεγχος για το αν είναι άδεια η λίσττα
	public boolean isEmpty() {		
		return nofElements < 1; 
	}

	// Έλεγχος για το αν το δοθέν στοιχείο είναι η κεφαλή της λίστας
	public boolean isFirst(SNode v) {
		return v == head;
	}

	// Επιστρέφει δείκτη προς την κεφαλή
	public SNode first() {
		if (isEmpty())				
			System.out.println("Η λίστα είναι άδεια");
		return head;
	}

	// Επιστρέφει δείκτη προς την ουρά
	public SNode last() {
		if (isEmpty())
			System.out.println("Η λίστα είναι άδεια");
		return tail;
	}

	public void setEmpty(){
		head = tail = null;
		nofElements = 0;
	}

	// Εισαγωγή νέου κόμβου με στοιχείο element μετά τον κόμβο p
	public SNode insertAfter(SNode p, Object element) {
		if (p == null) {
			System.out.println ("p is null!");
			return null;
		}   

		nofElements++;
		SNode q = new SNode(p.getNext(), element);
		if (p.getNext() == null) { // η εισαγωγή γίνεται στο τέλος
			tail = q;              // άρα ενημερώνεται η ουρά
		}
		p.setNext(q);
		return q;
	}

	// Εισαγωγή νέου κόμβου με στοιχείο element στην αρχή της λίστας
	public SNode insertFirst(Object element) { 
		nofElements++;
		SNode q = new SNode(head, element);
		head = q;
		if (nofElements == 1) { // Η λίστα μόλις απέκτησε τον πρώτο της κόμβο
			tail = head;
		}
		return q;
	}

	//ένθεση νέου κόμβου με στοιχείο element στην ουρά της λίστας
	public SNode insertLast(Object element) {
		nofElements++;
		SNode q = new SNode(null, element);

		if (nofElements == 1) { // η λίστα μόλις απέκτησε τον πρώτο της κόμβο
			head = tail = q;
		} else {
			tail.setNext(q);
		}

		tail = q;
		return q;
	}

	// Συγχωνεύει την λίστα με την s
	public void catenate(SinglyNodeList s){
		if (s.isEmpty()) {
			return;
		}

		if (tail == null) {
			tail = s.first();
		} else {
			tail.setNext(s.first());
		}

		if (isEmpty()) {
			head = tail;
		}

		tail = s.last();
		nofElements += s.size();
	}

	// Διαγραφή του πρώτου κόμβου και επιστροφή του στοιχείου που φέρει
	public Object removeFirst() {
		SNode temp;

		if (isEmpty()) {
			System.out.println ("List is empty. Nothing to remove...");
			return null;
		}

		if (--nofElements == 0) {     // ενημέρωση μεγέθους
			tail = null;              // γείωση της ουράς...
		}
		temp = head;
		head = head.getNext();        // Ο δεύτερος που θα γίνει πρώτος...
		temp.setNext(null);  // Γείωση του πρώην πρώτου κόμβου
		return temp.getElement();
	}

	public Object removeLast() {
		SNode temp = head;
		if (nofElements == 1) return removeFirst();

		while (temp.getNext() != tail) {
			temp = temp.getNext();
		}
		return removeAfter(temp);
	}

	// Διαγραφή του επομένου του p κόμβου
	public Object removeAfter(SNode p) {
		if (p == tail) {
			System.out.println ("You are already at the end");
			return null;
		}
		nofElements--;
		SNode pNext = p.getNext();	        // ο επόμενος κόμβος του p που θα διαγραφεί
		p.setNext(pNext.getNext());
		Object pElem = pNext.getElement();	// το στοιχείο του pNext
		if (pNext == tail) {	            // εάν διαγράφτηκε ο τελευταίος κόμβος
			tail = p;						// πρέπει να ενημερωθεί η ουρά
		}
		pNext.setNext(null);    	// "Γείωση" του pNext 
		return pElem;
	}

	// Τυπώνει τα περιεχόμενα της λίστας ξεκινώντας από την κεφαλή
	public void showList() {
		SNode temp = head;
		
		if (isEmpty()) {
			System.out.println("List is empty. Nothing to show...");
			return ;
		}
		
		// Όσο δεν είμαστε εκτός λίστας
		while (temp != null) {
			System.out.print("<" + temp.getElement() + "> ");
			temp = temp.getNext();
		}
		System.out.println("");
	}

	// Βρίσκει τον κόμβο με το μέγιστο στοιχείο και τον τοποθετεί στην κορυφή
	public void maxElement() {
		SNode curMax = head;          // δείκτης προς το τρέχον μέγιστο
		SNode cursor = head;          // δείκτης σαρώσεως της λίστας
		SNode precursor = null;       // δείκτης μία θέση πριν τον cursor
		Object temp, maxTemp;
		
		while (cursor != tail) {
			precursor = cursor; 
			cursor = cursor.getNext();
			if (((Integer)cursor.getElement()).intValue() > ((Integer)curMax.getElement()).intValue()) {
				curMax = cursor;      // Βρήκαμε νέο μέγιστο
			}
		}

		// Δεν χρειάζεται να κάνουμε τίποτα.
		if (curMax == head) {
			return;
		}

		// εάν το μέγιστο είναι στο τέλος πρέπει να χρησιμοποιήσουμε τον precursor
		if (curMax==tail) {
			maxTemp=removeAfter(precursor);
			insertFirst(maxTemp);
			return;
		}

		// Διαφορετικά
		temp = removeAfter(curMax);     // σβήνουμε τον κόμβο μετά το μέγιστο
		maxTemp = curMax.getElement();  // σημειώνουμε το μέγιστο
		curMax.setElement(temp);        // αντικαθιστούμε το μέγιστο με το επόμενό του
		insertFirst(maxTemp);           // Ενθέτουμε το μέγιστο στην αρχή
	}

	// Βρίσκει κόμβο με το ελάχιστο στοιχείο και τον τοποθετεί στην ουρά
	public void minElement() {
		SNode curMin = head;            // δείκτης προς το τρέχον ελάχιστο
		SNode cursor = head;            // δείκτης σαρώσεως της λίστας
		Object temp, minTemp;

		while (cursor != tail) { 
			cursor = cursor.getNext();
			if (((Integer)cursor.getElement()).intValue() < ((Integer)curMin.getElement()).intValue()) {
				curMin = cursor;        // Βρήκαμε νέο ελάχιστο..
			}
		}

		// δεν χρειάζεται να κάνουμε τίποτα
		if (curMin == tail) {
			return;
		}
		temp = removeAfter(curMin);
		minTemp = curMin.getElement();
		curMin.setElement(temp);
		insertLast(minTemp);
	}

	// Αντιστρέφει την λίστα
	public void listReversal(){
		SNode lastreversed = null;      // Δείκτης προς τον τελευταίο κόμβο που αντιστράφηκε
		SNode firstnotreversed = head;  // Δείκτης προς τον πρώτο που δεν αντιστράφηκε
		SNode nextfirstnot;             // Δείκτης προς τον επόμενο του firstnotreversed
		
		tail = head;
		while (firstnotreversed != null) {
			nextfirstnot=firstnotreversed.getNext();
			firstnotreversed.setNext(lastreversed);
			lastreversed=firstnotreversed;
			firstnotreversed=nextfirstnot;
		}
		head = lastreversed;
	}
}
