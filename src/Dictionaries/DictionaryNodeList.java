package Dictionaries;

import lists.SNode;
import lists.SinglyNodeList;

public class DictionaryNodeList extends SinglyNodeList {

	/* Επιστρέφει: 
	α) null, εάν υπάρχει item με κλειδί itemKey στην κεφαλή της λίστας,
	β) δείκτη προς τον δεξιότερο κόμβο v με κλειδί μικρότερο του itemKey, 
	γ) αλλιώς την ουρά. 
	*/
	public SNode findNode(Object itemKey) {
		SNode cursor = first();
		SNode precursor = null; // βοηθητικοί δείκτες, ο precursor είναι πάντοτε έναν κόμβο πριν τον cursor
		while (cursor != null) { // όσο δεν φθάσαμε στην ουρά…
			Item tmp = (Item)cursor.getElement();
			if ((int)tmp.getKey() >= (int)itemKey) {
				break;
			} else {
				precursor = cursor;
				cursor = cursor.getNext();
			}
		}
		return precursor;
	}

	public Object findInfo(Object key) {
		SNode found = findNode(key);
		Item checkItem;
		if (found == null) {
			checkItem = (Item)first().getElement(); //Item κεφαλής
		} else if (found == tail) {
			checkItem = (Item) found.getElement(); //Item ουράς
		} else {
			checkItem = (Item) found.getNext().getElement(); //Item επόμενου κόμβου
		}
		return (checkItem.getKey() == key) ? checkItem : null;
	}

	public boolean insertItem(Item item) {
		if (isEmpty()) { // θα γίνει το πρώτο μας στοιχείο
			insertFirst(item); // η μέθοδος κληρονομείται από την μητρική κλάση SinglyNodeList
			return true;
		}
		Object key = item.getKey();
		SNode found = findNode(key);
		Item checkItem;
		if (found == null) {
			checkItem = (Item)first().getElement(); // κεφαλή
		} else if (found == tail) {
			checkItem = (Item)found.getElement(); // ουρά
		} else {
			checkItem = (Item)found.getNext().getElement(); // επόμενος κόμβος
		}

		if (checkItem.getKey() == key) {
			return false; // υπάρχει ήδη
		}

		if (found == null) {
			insertFirst(item); // θα τοποθετηθεί στην κεφαλή
		} else {  // διαφορετικά, θα εισαχθεί μετά τον κόμβο που  διαθέτει, 
			insertAfter(found, item); // το δεξιότερο μικρότερο κλειδί εν σχέσει με  αυτό του item
		}
		return true;
	}

	// Διαγράφει το item με κλειδί key από την δομή μας, εφ’ όσον υπάρχει τέτοιο, επιστρέφοντας true.
	// Διαφορετικά,  επιστρέφει false.
	public boolean deleteItem(Object key){
		SNode found = findNode(key);
		Item checkItem;

		if (found == null) {
			checkItem = (Item)first().getElement(); // κεφαλή
		} else if (found == tail) {
			checkItem = (Item) found.getElement(); // ουρά
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

	// Τυπώνει τα περιεχόμενα της λίστας, ξεκικώντας από την κεφαλή
	public void showRecords(){ 
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
}; // Τέλος DictionaryNodeList
