package Dictionaries;

import trees.BTNode;
import items.AvlItem;
import items.Item;
import comparator.Comparator;

public class AvlTree extends BinarySearchTree {
	// Constructor #1
	public AvlTree(Comparator com) {
		super(com);
	}

	// Constructor #2
	public AvlTree(AvlItem item, Comparator com) {
		super(item, com);
	}

	// Επιστρέφει το ύψος του δεξιού γιου
	private int getRightSonHeight(BTNode v) {
		if (v == null) {
			System.out.println("Null node has no right son!");
			return -1;
		}

		if (v.getRight() == null) {
			return 0;
		} else {
			return ((AvlItem)v.getRight().getElement()).getHeight();
		}
		// return (v.getRight() != null ? ((AvlItem)v.getRight().getElement()).getHeight() : 0);
	}

	// Επιστρέφει το ύψος του αριστερού γιου 
	private int getLeftSonHeight(BTNode v){
		if (v == null) {
			System.out.println("Null node has no left son!");
			return -1;
		}

		if (v.getLeft() == null) {
			return 0;
		} else {
			return ((AvlItem)v.getLeft().getElement()).getHeight();
		}
		// return (v.getLeft()!=null?((avlItem) v.getLeft().getElement()).getHeight():0);
	}

	 // Επιστρέφει την πληροφορία του item με κλειδί key εάν υπάρχει τέτοιο
	public Object findInfo(Object key) {
		return super.findInfo(key, root());
	}

	 // Ένθεση του i χρησιμοποιώντας των ομώνυμη κληρονομημένη μέθοδο από τα δυαδικά
	 // δένδρα αναζητήσεως
	public BTNode insertItem(Item i) {
		BTNode insNode = super.insertItem(i);  // Χρήση της ομώνυμης μεθόδου
		if (insNode == null) {			       // Δεν έγινε ένθεση γιατί υπάρχει ήδη
			return null;
		}
		rebalance(insNode);                    // Επαναζύγιση ξεκινώντας από το νέο κόμβο
		return insNode;                        // τον οποίο επιστρέφει
	}

	 // Διαγραφή του στοιχείου με κλειδί key χρησιμοποιώντας των ομώνυμη κληρονομημένη
	 // μέθοδο από τα δυαδικά δένδρα αναζητήσεως
	public BTNode deleteItem(Object key) {
		BTNode delNode = super.deleteItem(key);	 // Χρήση της ομώνυμης μεθόδου
		if (delNode == null) {                   // Δεν έγινε διαγραφή γιατί δεν υπάρχει
			return null;
		}
		rebalance(delNode);                      // Επαναζύγιση ξεκινώντας από τον κόμβο που απώλεσε γιο
		return delNode;                          // και επιστροφή του
	}

	// called only if p is internal
	private void remedyHeight(BTNode v) {
		if (v == null) {
			System.out.println("Cannot remedy null node!");
			return;
		}

		AvlItem vItem = (AvlItem)v.getElement();

		vItem.setHeight(1 + Math.max(getRightSonHeight(v), getLeftSonHeight(v)));
		//  System.out.println("HHH:"+vItem.getHeight());
	}

	// test whether node p has balance factor between -1 and 1
	private boolean isBalanced(BTNode v)  {
		if (v == null) {
			System.out.println("Null node has no balance!");
			return false;
		}

		int balance = getLeftSonHeight(v) - getRightSonHeight(v);
		return ((-1 <= balance) && (balance <= 1));
	}

	// return a child of p with height no smaller than that of the other child
	private BTNode heigherSon(BTNode v)  {
		if (v == null) {
			System.out.println("Null node has no sons at all!");
			return null;
		}

		if(getLeftSonHeight(v) >= getRightSonHeight(v)) {
			return v.getLeft();
		} else {
			return v.getRight();
		}
	}
/*
	private void rebalance_s(BTNode v) {	
		if (v == null) {
			System.out.println("Αδύνατη η επαναζύγιση κενού κόμβου");
			return;
		}
		BTNode u, w;
		while (v != null) {				// όσο δεν ξεπεράσαμε την ρίζα
			remedyHeight(v);			// διορθώνουμε το ύψος του v
			if (!isBalanced(v)) {		      
				w = rebalanceSon(v);	     	//  ο γιος
				u = rebalanceSon(w);	      	// ο εγγονός που θα συμμετάσχουν στην
				v = reconstruct(v, w, u);	// δομική αλλαγή
				remedyHeight(v.getLeft());	// διόρθωση υψών των
				remedyHeight(v.getRight());	// εμπλεκόμενων κόμβων
				remedyHeight(v);
			}
			v = v.getParent();			// ανεβαίνουμε στον πατέρα
		}
	}
*/
	// επαναζυγίζει το δένδρο, από κάτω προς τα πάνω ξεκινώντας από τον κόμβο v
	private void rebalance(BTNode v) {
		if (v == null) {
			System.out.println("Αδύνατη η επαναζύγιση κενού κόμβου");
			return ;
		}
		BTNode u, w;

		while (v != null) {         // όσο δεν ξεπεράσαμε την ρίζα
			remedyHeight(v);        // διορθώνουμε το ύψος του v
			if (!isBalanced(v)) { 
				// System.out.println("Root:"+((avlItem)root().getElement()).getKey());
				// System.out.println("Balance because L"+getLeftSonHeight(v)+"R"+getRightSonHeight(v));
			
				w = heigherSon(v);  // ο γιος
				u = heigherSon(w);  // ο εγγονός που θα συμμετάσχουν στην
				// System.out.println("w:"+((avlItem)w.getElement()).getKey()+","+((avlItem)w.getElement()).getHeight()
				// +"u"+((avlItem)u.getElement()).getKey()+","+((avlItem)u.getElement()).getHeight());

				v = reconstruct(v, w, u);     // δομική αλλαγή
				remedyHeight(v.getLeft());    // διόρθωση υψών των
				remedyHeight(v.getRight());   // εμπλεκόμενων κόμβων
				remedyHeight(v);
			}
			v = v.getParent();                // ανεβαίνουμε στον πατέρα
		}
	}

		// Αναδομεί τοπικά το δένδρο κάνοντας απλή ή διπλή περιστροφή. 
	private BTNode reconstruct_slides(BTNode v, BTNode w, BTNode u) {
		if (isLeft(w) && isLeft(u)) return rotateRight(v, w, u);	// Απλή δεξιά περιστροφή
		else if (isRight(w) && isRight(u)) return rotateLeft(v, w, u);	 // Απλή αριστερή περιστροφή
		else if (isLeft(u)) return rotateDoubleLeft(v, w, u);	 	// Διπλή αριστερή περιστροφή
		else return rotateDoubleRight(v, w, u);			 // Διπλή δεξιά περιστροφή
	}

	private BTNode reconstruct(BTNode v, BTNode w, BTNode u) {
		// Right Rotation
		if (isLeft(w) && isLeft(u)) {
			if (!isRoot(v)) {
				if (isLeft(v)) {
					v.getParent().setLeft(w);
				} else {
					v.getParent().setRight(w);
				}
				w.setParent(v.getParent());
			}
			v.setLeft(w.getRight());
			if (w.getRight() != null) {
				w.getRight().setParent(v);
			}
			w.setRight(v);
			v.setParent(w);
			if (isRoot(v)) {
				setRoot(w);
				w.setParent(null);
			}
			return w;

		// Left Rotation
		} else if (isRight(w)&& isRight(u)) {
			if (!isRoot(v)){
				if (isRight(v)) {
					v.getParent().setRight(w);
				} else {
					v.getParent().setLeft(w);
				}
				w.setParent(v.getParent());
			}
			v.setRight(w.getLeft());
			if (w.getLeft() != null) {
				w.getLeft().setParent(v);
			}
			w.setLeft(v);
			v.setParent(w);
			if (isRoot(v)) {
				setRoot(w);
				w.setParent(null);
			}
			return w;

		// Double Left Rotation
		} else if (isLeft(u)) {
			v.setRight(u.getLeft());
			if (u.getLeft() != null) {
				u.getLeft().setParent(v);
			}
			w.setLeft(u.getRight());
			if (u.getRight() != null) {
				u.getRight().setParent(w);
			}
			if (!isRoot(v)) {
				if (isRight(v)) {
					v.getParent().setRight(u);
				} else {
					v.getParent().setLeft(u);
				}
				u.setParent(v.getParent());
			}
			v.setParent(u);
			w.setParent(u);
			u.setLeft(v);
			u.setRight(w);
			if (isRoot(v)) {
				setRoot(u);
				u.setParent(null);
			}
			return u;

		// Double Right Rotation
		} else {
			v.setLeft(u.getRight());
			if (u.getRight() != null) {
				u.getRight().setParent(v);
			}
			w.setRight(u.getLeft());
			if (u.getLeft() != null) {
				u.getLeft().setParent(w);
			}
			if (!isRoot(v)) {
				if (isLeft(v)) {
					v.getParent().setLeft(u);
				} else {
					v.getParent().setRight(u);
				}
				u.setParent(v.getParent());
			}
			v.setParent(u);
			w.setParent(u);
			u.setLeft(w);
			u.setRight(v); 
			if (isRoot(v)) {
				setRoot(u);
				u.setParent(null);
			}
			return u;
		}
	}

	private BTNode rotateLeft(BTNode v, BTNode w, BTNode u) {		// Απλή αριστερή περιστροφή (rot)
		if (!isRoot(v)) {					// Εάν ο v ; έχει πατέρα
			// Αντιμετάθεση του v με w
			if (isRight(v)) {
				v.getParent().setRight(w);
			} else {
				v.getParent().setLeft(w);
			}
			w.setParent(v.getParent());
		}
		v.setRight(w.getLeft());		                       // Νέος δεξιός γιος του v γίνεται ο αριστερός του w
		if (w.getLeft() != null) {
			w.getLeft().setParent(v); // Ο αριστερός γιος του w απoκτά ως πατέρα τον v
		}
		w.setLeft(v); 			 	  // Νέος αριστερός γιος του w γίνεται ο v
		v.setParent(w);			 	  // Νέος πατέρας του v γίνεται ο w
		if (isRoot(v)) {
			setRoot(w);
			w.setParent(null);
		}
		return w;
	}

	private BTNode rotateRight(BTNode v, BTNode w, BTNode u) { // Απλή δεξιά περιστροφή (Rot)
	    if (!isRoot(v)) {				       // Εάν ο v ;έχει πατέρα
			// Αντιμετάθεση του v με w
		    if (isLeft(v)) {
				v.getParent().setLeft(w);
			} else {
				v.getParent().setRight(w);
			}
			w.setParent(v.getParent());
	    }
	    v.setLeft(w.getRight());	               // Νέος αριστερός γιος του v γίνεται ο δεξιός του w
	    if (w.getRight() != null) {
			w.getRight().setParent(v); // Ο δεξιός γιος του w απoκτά ως πατέρα τον v
		}
	    w.setRight(v); 			 // Νέος δεξιός γιος του w γίνεται ο v
	    v.setParent(w);			 // Νέος πατέρας του v γίνεται ο w
	    if (isRoot(v)) {
			setRoot(w);
			w.setParent(null);
		}
	    return w;
	}

	private BTNode rotateDoubleLeft(BTNode v, BTNode w, BTNode u) { // Διπλή αριστ.περιστροφή (Drot)
		v.setRight(u.getLeft());			    // Νέος δεξιός γιος του v γίνεται ο αριστερός του u
		if (u.getLeft() != null) {
			u.getLeft().setParent(v);    // Ο v γίνεται πατέρας του αριστερού γιου του u
		}
		w.setLeft(u.getRight());			    // Νέος αριστερός γιος του w γίνεται ο δεξιός του u
		if (u.getRight() != null) {
			u.getRight().setParent(w);  // Ο w γίνεται πατέρας του δεξιού γιου του u
		}
		if (!isRoot(v)) {
			if (isRight(v)) {
				v.getParent().setRight(u); // Ο πατέρας του v αποκτά δεξιό γιο τον u   (av  v δεξιός)
			} else {
				v.getParent().setLeft(u);  // Ο πατέρας του v αποκτά αριστερό γιο τον u (αν v  αριστερός)
			}
			u.setParent(v.getParent());		         // Πατέρας του u γίνεται ο πρώην πατέρας του v
		}
		v.setParent(u); w.setParent(u); u.setLeft(v); u.setRight(w); // Νέες αναθέσεις
		if (isRoot(v)) {
			setRoot(u);
			u.setParent(null);
		}
		return u;
	}

	private BTNode rotateDoubleRight(BTNode v, BTNode w, BTNode u) {     // Διπλή δεξιά περιστροφή (DRot)
		v.setLeft(u.getRight());			        // Νέος αριστερός γιος του v γίνεται ο δεξιός του u
		if (u.getRight() != null) {
			u.getRight().setParent(v); // Ο v γίνεται πατέρας του δεξιού γιου του u
		}
		w.setRight(u.getLeft());			        // Νέος δεξιός γιος του w γίνεται ο αριστερός του u
		if (u.getLeft() != null) {
			u.getLeft().setParent(w);        // Ο w γίνεται πατέρας του αριστερού γιου του u
		}

		if (!isRoot(v)) {
			if (isLeft(v)) {
				v.getParent().setLeft(u); 	 // Ο πατέρας του v αποκτά αριστερό γιο τον u
			} else {
				v.getParent().setRight(u);	   	 // Ο πατέρας του v αποκτά δεξιό γιο τον u
			}
			u.setParent(v.getParent());		 	  // Πατέρας του u γίνεται ο πρώην πατέρας του v
		}
		v.setParent(u); w.setParent(u); u.setRight(v); u.setLeft(w); // Νέες αναθέσεις
		if (isRoot(v)) {
			setRoot(u);
			u.setParent(null);
		}
		return u;
	}
}
