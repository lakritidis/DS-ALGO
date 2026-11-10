package hashing;

import comparator.Comparator;
import items.Item;
import lists.SearchNodeList;


public class hashingWithChaining {
	private int n; // πλήθος στοιχείων που έχουν εισαχθεί στην δομή
	private int m; // το μέγεθος του πίνακα κατακερματισμού
	private SearchNodeList[] H; // ο πίνακας κατακερματισμού
	private hashOperator h; // αντικείμενο που προσφέρει την συνάρτηση κατακερματισμού

	// Constructor #1
	public hashingWithChaining(int size, hashOperator hs) {
		h = hs;
		n = 0;

		// Δημιουργία πίνακα κατακερματισμού m θέσεων και αρχικοποίηση
		// των θέσεων του πίνακαμε κενές λίστες.
		H = new SearchNodeList[m = size];
		for(int i = 0; i < size; i++) {
			H[i] = new SearchNodeList(new Comparator());
		}
	}

	// Constructor #2
	public hashingWithChaining(hashOperator hs) {
		m = 101;
		h = hs;
		n = 0;
		H = new SearchNodeList[m];
		for(int i = 0; i < m; i++) {
			H[i] = new SearchNodeList(new Comparator());
		}
	}

	// Αναζήτηση στοιχείου με κλειδί key. Επιστρέφει null εάν δεν υπάρχει τέτοιο item.
	public Item findItem(Object key) {
		return H[h.hashTo(key)].findItem(key);
	} 

	// Εισαγωγή ενός item, εφ'όσον δεν υπάρχει άλλο στοιχείο ήδη με το ίδιο κλειδί.
	public boolean insertItem(Item item) {
		return (H[h.hashTo(item.getKey())].insertItem(item) ? ++n > 0 : false);
	}

	// Διαγραφή του item με κλειδί key, εφ'όσον υπάρχει.
	public boolean deleteItem(Object key) {
		return (H[h.hashTo(key)].deleteItem(key) ? --n >= 0 : false);
	}

	public void showHashTable() {
		for (int i = m - 1; i >= 0; i--) {
			System.out.println("List No " + i);
			H[i].showList();
			System.out.println();
		}
	}
}
