package lists;

import items.Item;
import comparator.Comparator;

public class SearchNodeList extends SinglyNodeList {
	Comparator c;

	public SearchNodeList(Comparator com) {
		super();
		c = com;
	}

	// Επιστρέφει: 
	// α) null, εάν υπάρχει item με κλειδί itemKey στην κεφαλή της λίστας,
	// β) δείκτη προς τον δεξιότερο κόμβο v με κλειδί μικρότερο του itemKey, 
	// γ) αλλιώς την ουρά. 
	public SNode findNode(Object itemKey) {
		SNode cursor = first();
		SNode precursor = null;

		while (cursor != null) {
			if (!c.less(((Item)cursor.getElement()).getKey(), itemKey)) {
				break;
			} else {
				precursor = cursor;
				cursor = cursor.getNext();
			}
		}
		return precursor;
	}

	// Επιστρέφει την πληροφορία στην λίστα με κλειδί key, εφ'όσον
	// υπάρχει τέτοιο item. Διαφορετικά, null.
	public Item findItem(Object key) {
		SNode found = findNode(key);
		Item checkItem;

		if (found == null) {
			checkItem = (Item)first().getElement();	// Item κεφαλής
		} else if (found == tail) {
			checkItem = (Item)found.getElement();	// Item ουράς
		} else {
			checkItem = (Item)found.getNext().getElement(); // Item επόμενου κόμβου
		}
		return (c.equal(checkItem.getKey(),key) ? checkItem : null);
	}

	// Εισάγει το item στην δομή εφ' όσον δεν υπάρχει άλλο με ίδιο κλειδί,
	// επιστρέφοντας true. Διαφορετικά, επιστρέφει false
	public boolean insertItem(Item item) {
		// Αν η δομή λεξικού είναι κενή, τότε θα γίνει το πρώτο στοιχείο
		if (isEmpty()) {
			insertFirst(item);	// η μέθοδος κληρονομείται από την μητρική κλάση SinglyNodeList
			return true;
		}

		Object key = item.getKey();
		SNode found = findNode(key);
		Item checkItem;

		if (found == null) {
			checkItem = (Item)first().getElement();	// Item κεφαλής
		} else if (found == tail) {
			checkItem = (Item)found.getElement();	// Item ουράς
		} else {
			checkItem = (Item)found.getNext().getElement(); // Item επόμενου κόμβου
		}

		// Το στοιχείο υπάρχει ήδη, η εγγραφή δεν επιτρέπεται.
		if (c.equal(checkItem.getKey(), key)) {
			return false;
		}

		if (found == null) {
			insertFirst(item); // θα τοποθετηθεί στην κεφαλή
		} else { // διαφορετικά, θα εισαχθεί μετά τον κόμβο που διαθέτει
			insertAfter(found, item); // το δεξιότερο μικρότερο κλειδί εν σχέσει με αυτό του item
		}
		return true;
	}

	// Διαγράφει το item με κλειδί key από την δομή μας, εφ’ όσον υπάρχει τέτοιο, επιστρέφοντας true.
	// Διαφορετικά,  επιστρέφει false.
	public boolean deleteItem(Object key){
		SNode found = findNode(key);
		Item checkItem;

		if (found == null) {
			checkItem = (Item)first().getElement();			// κεφαλή
		} else if (found == tail) {
			checkItem = (Item) found.getElement();			// ουρά
		} else {
			checkItem = (Item)found.getNext().getElement(); // επόμενος κόμβος
		}

		if (checkItem.getKey() != key) {
			return false; // δεν υπάρχει
		}

		if (found == null) {
			removeFirst(); // είναι στην κορυφή
		} else {
			removeAfter(found); // διαφορετικά, είναι μετά τον found
		}
		return true;
	} 

	// Τυπώνει τα περιεχόμενα της λίστας ξεκικώντας από την κεφαλή
	public void showRecords() {
		SNode temp = head;
		if (isEmpty()) {
			System.out.println("List is empty. Nothing to show...");
			return ;
		}

		while (temp != null){ //όσο δεν είμαστε εκτός λίστας
			Item tmp = (Item)temp.getElement();
			System.out.print("<" + tmp.getKey() + " , " + tmp.getInfo() + "> ");
			temp = temp.getNext();
		}
		System.out.println("");
	}
}
