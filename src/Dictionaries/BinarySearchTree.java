package Dictionaries;

import trees.LinkedBinaryTree;
import trees.BTNode;

// Η κλάση BinarySearchTree αποτελεί επέκταση της LinkedBinaryTree (δυναμικά δυαδικά δένδρα)
public class BinarySearchTree extends LinkedBinaryTree {
	Comparator c;

	BinarySearchTree(Comparator com) {
		super(); // καλεί τον constructor της κλάσης LinkedBinaryTree
		c = com;
	}

	BinarySearchTree(Item item, Comparator com) {
		super(item); // καλεί τον constructor της κλάσης LinkedBinaryTree
		c = com;
	}

	// Επιστρέφει τον κόμβο (εάν υπάρχει τέτοιος) που έχει αποθηκευμένο ένα item με κλειδί key,
	// περιορίζοντας την αναζήτηση στο υποδένδρο Tv με ρίζα τον κόμβο v. 
	// Χρόνος O(ύψους) στην χειρότερη περίπτωση
	public BTNode findNode(Object key, BTNode v) {
		Object nodeKey = ((Item)v.getElement()).getKey();
		if (c.less(key, nodeKey)) {
			if (v.getLeft() == null) {
				return v;
			} else {
				return findNode(key, v.getLeft());
			}
		} else if (c.equal(key, nodeKey)) {
			return v;
		} else {
			if (v.getRight() == null) {
				return v;
			} else {
				return findNode(key, v.getRight());
			}
		}
	}

	// Επιστρέφει την πληροφορία του item με κλειδί key (εάν υπάρχει τέτοιο)
	// περιορίζονταςτην αναζήτηση στο υποδένδρο Tv με ρίζα τον κόμβο v. 
	// Χρόνος O(ύψους) στην χειρότερη περίπτωση.
	public Object findInfo(Object key, BTNode v) {
		BTNode node = findNode(key, v);

		if (((Item)node.getElement()).getKey() == key) {
			return ((Item)node.getElement()).getInfo();
		} else {
			return null;
		}
	}

	// Ενθέτει ένα νέο item στο δένδρο, επιστρέφοντας τον BTNode που το αποθηκεύει
	// εφ' όσον δεν υπάρχει άλλο item με ίδιο κλειδί. Εάν υπάρχει απλώς επιστρέφει
	// null. Χρόνος Ο(ύψους) στην χειρότερη περίπτωση. Λαμβάνει ως παράμετρο το
	// στοιχείο (item) που θα εισαχθεί στο δένδρο (εφόσον δεν υπάρχει item με το ίδιο κλειδί)
	public BTNode insertItem(Item i) {
		if (size() == 0) {
			setRoot(new BTNode(i, null, null, null)); // στοιχείο (δεδομένα), γονέας, αριστερό παιδί, δεξιό παιδί
			setSize(1);
			return root();
		}
		BTNode insNode = findNode(i.getKey(), root());  // αναζήτηση βάσει κλειδιού
		Object keyNode = ((Item)insNode.getElement()).getKey(); // το κλειδί του κόμβου insNode

		if (c.equal(keyNode, i.getKey())) { // υπάρχει ήδη στοιχείο με ίδιο κλειδί επομένως επιστρέφει
			return null;
		} else {     // δεν υπάρχει, οπότε θα τοποθετηθεί είτε ως αριστερό είτε ως δεξιό παιδί
			if (c.less(i.getKey(), keyNode)) { // είναι μικρότερο, πρέπει να εισαχθεί αριστερά
				addLeaf(insNode, Left);        // προσθέτουμε αριστερό παιδί
				insNode.getLeft().setElement(i); // το item i γίνεται περιεχόμενο του παιδιού
				return insNode.getLeft(); // επιστρέφει το αριστερό παιδί του insNode δηλ. τον νεοεισαχθέντα κόμβο
			} else {                      // είναι μεγαλύτερο, πρέπει να μπει δεξιά
				addLeaf(insNode, Right); // προσθέτουμε δεξί παιδί
				insNode.getRight().setElement(i); // το item i γίνεται περιεχόμενο του παιδιού
				return insNode.getRight();  // επιστρέφει το δεξί παιδί του insNode δηλ. τον νεοεισαχθέντα κόμβο
			}
		}
	}

	// Διαγράφει, εφ' όσον υπάρχει τέτοιο, το item του δένδρου με κλειδί key, επιστρέφοντας
	// τον εναπομείναντα εμπλεκόμενο κόμβο. Εάν δεν υπάρχει, δεν κάνει τίποτε επιστρέφοντας
	// null. Χρόνος Ο(n) στην χειρότερη περίπτωση
	public BTNode deleteItem(Object key) {
		if (size() == 0) {
			return null;
		}

		BTNode delNode = findNode(key, root()); // αναζήτηση βάσει κλειδιού
		Object keyNode = ((Item)delNode.getElement()).getKey();  // το κλειδί του κόμβου delNode

		if (!c.equal(keyNode, key)) { // δεν υπάρχει τέτοιο item επιστρέφει null
			return null;
		} else {  // υπάρχει
			BTNode returnNode; // ο εναπομείναντας κόμβος (που επιστρέφεται) 
			if ((delNode.getLeft() == null) || (delNode.getRight() == null)) { // έχει ένα παιδί null
				returnNode =
					(delNode.getLeft() != null ? delNode.getLeft() :  // αν έχει αριστερό παιδί το επιστρέφει
					delNode.getRight() != null ? delNode.getRight() : // αν έχει δεξί παιδί το επιστρέφει
					delNode.getParent());                  // αν δεν έχει παιδιά επιστρέφει τον πατέρα του
				deleteNode(delNode);                       // διαγραφή κόμβου
				return returnNode;                         // επιστροφή του εναπομείναντα εμπλεκόμενου κόμβου
			// και τα δύο του παιδιά είναι κανονικοί κόμβοι, οπότε πρέπει να βρούμε τον κόμβο του δεξιού
            // υποδένδρου με το μικρότερο κλειδί
			} else {
				BTNode cursor = delNode.getRight(), temp, parentDelNode;

				while((temp = cursor.getLeft()) != null) { // όσο το αριστερό παιδί του cursor δεν είναι null
					cursor = temp;
				}       // στο τέλος του βρόχου ο cursor είναι ο αριστερότερος απόγονος του δεξιού υποδένδρου
				exchangeElements(cursor, delNode);   // ανταλλαγή περιεχομένων
				parentDelNode = cursor.getParent();  // δείκτης στον γονέα του
				deleteNode(cursor);                  // διαγραφή κόμβου
				return parentDelNode;                // επιστρέφει δείκτη στον γονέα του κόμβου που διαγράφηκε
			}
		}
	}
}
