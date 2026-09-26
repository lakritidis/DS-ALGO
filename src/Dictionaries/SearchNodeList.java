package Dictionaries;

import lists.SNode;
import lists.SinglyNodeList;

// H SearchNodeList κληρονομεί την SinglyNodeList, άρα έχει πρόσβαση στις public και protected μεθόδους και ιδιότητες της SinglyNodeList
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
	private SNode findNode(Object itemKey) {
		SNode cursor = first();
		SNode precursor = null;

		while (cursor!=null){
			if (!c.less(((Item)cursor.getElement()).getKey(), itemKey)) {
				break;
			} else {
				precursor = cursor;
				cursor = cursor.getNext();
			}
		}
		return precursor;
	}

	// Επιστρέφει την πληροφορία στην λίστα με κλειδί key, εφ' όσον υπάρχει τέτοιο item στην δομή μας. Διαφορετικά, null
	public Item findItem(Object key) {
		SNode found = findNode(key);
		Item checkItem;

		if (found == null) {
			checkItem = (Item)first().getElement();
		} else if (found == tail) {
			checkItem = (Item)found.getElement();
		} else {
			checkItem = (Item)found.getNext().getElement();
		}
		return (c.equal(checkItem.getKey(), key) ? checkItem : null);
	}

	// Εισάγει το item στην δομή εφ' όσον δεν υπάρχει άλλο με ίδιο κλειδί, επιστρέφοντας true. Διαφορετικά, επιστρέφει false
	public boolean insertItem(Item item) {
		if (isEmpty()) { // Αν η δομή λεξικού είναι κενή, τότε θα γίνει το πρώτο στοιχείο
			insertFirst(item); // η μέθοδος κληρονομείται από την μητρική κλάση SinglyNodeList
			return true;
		}
		Object key = item.getKey();
		SNode found = findNode(key);
		Item checkItem;

		if (found == null) { // κεφαλή
			checkItem = (Item)first().getElement();
		} else if (found == tail) { // ουρά
			checkItem = (Item)found.getElement();
		} else { // επόμενος κόμβος
			checkItem = (Item)found.getNext().getElement();
		}

		if (c.equal(checkItem.getKey(), key)) { // υπάρχει ήδη
			return false;
		}

		if (found == null) {  // θα τοποθετηθεί στην κεφαλή
			insertFirst(item);
		} else { // διαφορετικά, θα εισαχθεί μετά τον κόμβο που  διαθέτει
			insertAfter(found, item);// το δεξιότερο μικρότερο κλειδί εν σχέσει με  αυτό του item
		}
		return true;
	}
  
	// Διαγράφει το item με κλειδί key από την δομή μας, εφ' όσον υπάρχει τέτοιο, επιστρέφοντας true. Διαφορετικά επιστρέφει false.
	public boolean deleteItem(Object key) {
		SNode found = findNode(key);
		Item checkItem;

		if (found == null) { // κεφαλή
			checkItem = (Item)first().getElement();
		} else if (found == tail) { // ουρά
			checkItem = (Item)found.getElement();
		} else { // επόμενος κόμβος
			checkItem = (Item)found.getNext().getElement();
		}

		if (!c.equal(checkItem.getKey(), key)) { // Δεν υπάρχει
			return false;
		}

		if (found == null) { // είναι στην κορυφή
			removeFirst();
		} else {
			removeAfter(found); // διαφορετικά, είναι μετά τον found
		}
		return true;
	}	

	// Τυπώνει τα περιεχόμενα της λίστας, ξεκικώντας από την κεφαλή
	public void showList() {
		SNode temp = head;

		if (isEmpty()) {
			System.out.println("Empty list");
			return ;
		}

		// όσο δεν είμαστε εκτός λίστας
		while (temp != null) {
			System.out.print("<" + ((Item)temp.getElement()).getKey() + "> ");
			temp = temp.getNext();
		}
		System.out.println("");
	}
}
