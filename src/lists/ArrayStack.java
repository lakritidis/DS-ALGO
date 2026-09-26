package lists;

public class ArrayStack {
	public static final int defaultCapacity = 1000;	// η προκαθορισμένη (default) χωρητικότητα
	private int capacity;	          // η χωρητικότητα της στοίβας
	private Object STACK[];	          // ο πίνακας που θα «στεγάσει» τα στοιχεία
	private int top = -1;	          // η κορυφαία θέση, αρχικά –1 όταν η στοίβα είναι άδεια

	// Αρχικοποίηση με την ερήμην χωρητικότητα.
	// Η στοίβα μπορεί να φιλοξενήσει μέχρι defaultCapacity στοιχεία.
	public ArrayStack() {	          
		capacity = defaultCapacity;
		STACK = new Object[capacity];
	}

	// Αρχικοποίηση με την επιθυμητή χωρητικότητα cap.
	// Επομένως η στοίβα μπορεί να φιλοξενήσει μέχρι cap αντικείμενα.
	public ArrayStack(int cap) {
		capacity = cap;
		STACK = new Object[capacity];
	}

	public int size() {
		return (top + 1);
	}

	public boolean isEmpty() {
		return top < 0;
	}

	public void push(Object obj) {
		if (size() == capacity) {
			System.out.println("Υπερχείλιση στοίβας");
			return;
		}
		STACK[++top] = obj;
	}

	public Object top() {
		if (isEmpty()) {
			System.out.println("Άδεια στοίβα");
			return null;
		}
		return STACK[top];
	}

	public Object pop() {
		Object elem;
		if (isEmpty()) {
			System.out.println("Άδεια στοίβα");
			return null;
		}
		elem = STACK[top];
		STACK[top--] = null;	// «γείωση» του παλαιού κορυφαίου στοιχείου
		return elem;
	}
}
