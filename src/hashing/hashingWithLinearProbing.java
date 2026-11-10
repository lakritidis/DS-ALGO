package hashing;

import comparator.Comparator;
import items.Item;

public class hashingWithLinearProbing {
	private Comparator comparator;
	private int n; // πλήθος στοιχείων που έχουν εισαχθεί στην δομή
	private int m; // το μέγεθος του πίνακα κατακερματισμού
	private Item[] H; // ο πίνακας κατακερματισμού
	private hashOperator h; // αντικείμενο που προσφέρει την συνάρτηση κατακερματισμού
	private static final Item tombStone = new Item(null, null); // ταφόπλακα

	// constructor #1
	public hashingWithLinearProbing(int size, hashOperator hs, Comparator comp) {
		h = hs;
		comparator = comp;
		n = 0;
		H = new Item[m = size];
	}

	// constructor #2
	public hashingWithLinearProbing (hashOperator hs, Comparator comp) {
		m = 101;
		h = hs;
		comparator = comp;
		n = 0;
		H = new Item[m];
	}

	// Επιστρέφει true εάν η θέση i του πίνακα κατακερματισμού είναι κενή, αλλιώς false.
	public boolean isNull(int i) {
		return H[i] == null;
	}

	// Επιστρέφει true εάν η θέση i του πίνακα κατακερματισμού είναι ταφόπλακα, αλλιώς false.
	public boolean isTombStone(int i) {
		return H[i] == tombStone;
	}

	// Βοηθητική μέθοδος για την εύρεση της θέσεως ενός item με κλειδί key. 
	// Επιστρέφει την θέση, εάν υπάρχει. Διαφορετικά, επιστρέφει -1.
	private int findPositionItem(Object key) {
		int i, startPosition = i = h.hashTo(key);	// η αρχική θέση
		do {
			// η θέση είναι κενή το στοιχείο δεν υπάρχει
			if (isNull(i)) {
				return -1;
			}

			// Αν η τρέχουσα θέση είναι ταφόπλακα, πηγαίνουμε παρακάτω
			if (isTombStone(i)) {
				i = (i + 1) % m;		// υπολογίζει την επόμενη θέση

			// Αν το κλειδί της αναζήτησης είναι ίσο με το κλειδί στη θέση i
			} else if (comparator.equal(H[i].getKey(), key)) {
				return i;

			// Συνεχίζουμε την αναζήτηση στην επόμενη θέση
			} else { 
				i = (i + 1) % m;		// υπολογίζει την επόμενη θέση
			}
		} while (i != startPosition);	// εξετάζουμε τις θέσεις μέχρι να επιστρέψουμε στην αρχική
		return -1;						// το στοιχείο δεν υπάρχει
	}

	// Αναζήτηση στοιχείου με κλειδί key. Επιστρέφει το item, εάν υπάρχει.
	// Διαφορετικά, null
	public Item findItem(Object key) {
		int position = findPositionItem(key);
		if (position == -1) {
			return null;
		} else {
			return H[position];
		}
	}

	// Εισάγει το item επιστρέφοντας true, εάν δεν υπάρχει άλλο με ίδιο κλειδί. Διαφορετικά, επιστρέφει false
	 public boolean insertItem (Item item) {
		Object itemKey = item.getKey();
		int i = h.hashTo(itemKey);		// η αρχική θέση
		int startPosition = i;
		int tombStonePos = m;
		boolean tombStoneFlag = true;

		do {
			// Η θέση i είναι ταφόπλακα και δεν έχουμε συναντήσει άλλη ταφόπλακα
			if (isTombStone(i) && tombStoneFlag) {
				tombStonePos = i;		// διατηρεί την θέση της πρώτης ταφόπλακας 
				tombStoneFlag = false;	// που θα συναντήσουμε

			// Kενή θέση, το κλειδί δεν υπάρχει. Αν υπάρχει ταφόπλακα, το στοιχείο εισάγεται
			// στην θέση της αριστερότερης ταφόπλακας. Διαφορετικά, εισάγεται στη θέση i.
			} else if (isNull(i)) {
				H[(tombStoneFlag ? i : tombStonePos)] = item;
				n++;
				return true;

			// Το στοιχείο υπάρχει ήδη
			}  else if (comparator.equal(itemKey, H[i].getKey())) {
				System.out.println("Υπάρχει ήδη στοιχείο με ίδιο κλειδί");
				return false;
			}
			i = (i + 1) % m;		// Διαφορετικά, μεταβαίνουμε στην επόμενη θέση του πίνακα
		}
		while (i != startPosition);	// Εξετάζουμε τις θέσεις μέχρι να επιστρέψουμε στην αρχική 

		System.out.println("Ο πίνακας κατακερματισμού είναι πλήρης!");
		return false;				// έχουμε εξετάσει κυκλικά όλες τις θέσεις και δεν βρέθηκε καμία διαθέσιμη
	}

	// Διαγραφή στοιχείου με κλειδί key, τοποθετώντας ταφόπλακα στη θέση του.
	// Επιστρέφει true, εάν το στοιχείο βρέθηκε και διαγράφηκε. Διαφορετικά, επιστρέφει false.
	public boolean deleteItem (Object key) {
		// Η εξεταζόμενη θέση
		int i = findPositionItem(key);

		// Δεν βρέθηκε στοιχείο με κλειδί key
		if  (i < 0)	{
			return false;
		}

		// Το στοιχείο βρέθηκε και τοποθετούμε στην θέση αυτή ταφόπλακα
		H[i] = tombStone;
		n--;		// ενημέρωση του μεγέθους
		return true;
	}

	public void showHashTable() {
		for (int i = 0; i < m; i++) {
			if (H[i] == null) {
				System.out.print("--");
			} else if (H[i].getKey() == null) {
				System.out.print("X ");
			} else {
				System.out.print(H[i].getKey() + " ");
			}
			// System.out.print((H[i] == null ? "--" : (H[i].getKey() ==null?"X":H[i].getKey()))+" ");
		}
		System.out.println();
	}
}
