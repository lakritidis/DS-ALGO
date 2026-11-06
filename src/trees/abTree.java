package trees;

import items.Item;
import comparator.Comparator;

public class abTree extends LinkedGeneralTree{
	private int a, b; // min & max nof sons
	private static Comparator c;

	public abTree(int min, int max, Comparator com) {
		setRoot(null);
		setSize(0);
		a = min;
		b = max;
		c = com;
	}

	// Επιστρέφει τον κόμβο (έαν υπάρχει τέτοιος), που έχει αποθηκευμένο ένα item με
	// κλειδί key περιορίζοντας την αναζήτηση στο υποδένδρο Tv με ρίζα τον κόμβο v.
	// Χρόνος Ο(ύψους) στη χειρότερη περίπτωση.
	public abTNode findNode(Object key, abTNode v){
		int itemPos = v.findPositionItem(key, c);

		if (itemPos < v.getCurnofSons() - 1 && c.equal(key, v.getKey(itemPos))) {
			return v;
		} else if (v.getSon(itemPos) == null) {
			return v;
		} else {
			return findNode(key, (abTNode)v.getSon(itemPos));
		}
	}

	// Επιστρέφει την πληροφορία του item με κλειδί key εάν υπάρχει τέτοιο item,
	// αλλιώς επιστρέφει null.
	public Object findInfo(Object key) {
		abTNode node = findNode(key, (abTNode)root());
		return node.findItem(key,c).getKey();
	}

	// Ενθέτει ένα νέο item στο δέντρο εφόσον δεν υπάρχει άλλο item με το ίδιο κλειδί.
	// Χρόνος Ο(ύψους) στη χειρότερη περίπτωση.
	public boolean insertItem(Item i) {
		if (size() == 0) {
			setRoot(new abTNode(new Item[b], null, b + 1));
			((abTNode)root()).setItem(0,i);
			((abTNode)root()).setCurnofSons(2);
			setSize(1);
			return true;
		}

		abTNode insNode = findNode(i.getKey(), (abTNode)root());
		if (insNode.keyExists(i.getKey(),c) >= 0) {
			System.out.println("Key exists");
			return false;
		}

		insNode.insertItem(i, c);
		if (!insNode.overflow(b)) {
			return true;
		} else {
			balanceOverflow(insNode);
		}
		return true;
	}

	// Διαγράφει το item του δένδρου με κλειδί key (εφόσον υπάρχει τέτοιο).
	// Χρόνος Ο(ύψους) στη χειρότερη περίπτωση.
	public boolean deleteItem(Object key) {
		//  Κενό δένδρο
		if (size() == 0) { 
			return false;
		}
		int itemPos;

		abTNode delNode = findNode(key, (abTNode)root()), v = delNode;
		if ((itemPos = ((abTNode)delNode).keyExists(key,c)) < 0) {
			return false;
		}
		delNode = (abTNode)delNode.getSon(0) != null ? (abTNode)delNode.getSon(itemPos) : delNode;

		// Εντοπισμός του δεξιότερου στοιχείου του αριστερού υποδένδρου
		while (delNode.getSon(0) != null) {
			delNode = (abTNode)delNode.getSon(delNode.getCurnofSons() - 1);
		}

		//  Ανταλλαγή με το δεξιότερο στοιχείο του αριστερού υποδένδρου
		if (v != delNode) {
			v.setItem(itemPos, delNode.deleteItem(delNode.getCurnofSons() - 2));
		} else {
			v.deleteItem(itemPos);
		}

		// Διαγραφή χωρίς να δημιουργείται έλλειμμα
		if (!delNode.underflow(a)) {
			return true;
		// Διαγραφή με έλλειμμα: Επαναζύγιση του δένδρου
		} else {
			balanceUnderflow(delNode);
		}

		return true;	
	}

	// Επιδιόρθωση υπερχείλισης
	private void balanceOverflow(abTNode v) {
		int pos = 0;
		while(v.overflow(b) && !isRoot(v)) {
			//split System.out.println("Split");
			abTNode buddy = v.split();
			Item splitItem = v.getItem((b + 1) / 2 - 1);
			v.setItem((b + 1) / 2 - 1,null);
			v.decCurnofSons(); 
			v = (abTNode)v.getParent();
			pos = v.insertItem(splitItem, c); 	 
			v.insertSon(pos + 1, buddy);
		}

		// Εδώ χειριζόμαστε την υπερχείλιση ρίζας – δημιουργία νέας με δύο παιδιά
		if (v == root() && v.overflow(b)) {
			abTNode buddy = v.split();
			Item splitItem = v.getItem((b + 1) / 2-1);
			v.setItem((b + 1) / 2 - 1,null);
			v.decCurnofSons();

			setRoot(new abTNode(new Item[b], null, b + 1));
			root().setSon(0,v);
			v.setParent(root());
			((abTNode) root()).setItem(0, splitItem);
			root().setSon(1, buddy);
			buddy.setParent(root());
			((abTNode)root()).setCurnofSons(2);
		}
	}

	// Επιδιόρθωση ελλείματος παιδιών στον κόμβο v.
	// Η διαδικασία συνεχίζεται μέχρι να μην υπάρχει έλλειμμα ή να φτάσουμε στη ρίζα.
	private void balanceUnderflow(abTNode v) {
		boolean underflow = true, leftFuse = false, rightFuse = false;
		abTNode sibling;
		TNode temp; 
		if (v == root()) {
			return;
		}

		while (underflow) {
			if (leftFuse = v.hasLeftSibling()) {
				if((sibling = (abTNode)v.getLeftSibling()).canBorrow(a)) {
					// Δανεισμός από τον αριστερό αδελφό
					// System.out.println("Left Sharing");
					v.insertSon(0, temp = sibling.deleteSon(sibling.getCurnofSons() - 1));
					v.insertItem(((abTNode)v.getParent()).getItem(sibling.getIndex()), c);
					((abTNode)v.getParent()).setItem(sibling.getIndex(), sibling.deleteItem(sibling.getCurnofSons() - 2));
					underflow = false;
					if (temp != null) {
						temp.setParent(v);
					}
				}
			}

			if (v.hasRightSibling() && underflow) {
				sibling = (abTNode)v.getRightSibling();
				System.out.println("Right Sharing " + v.hasRightSibling() + " " + sibling);
				if(sibling.canBorrow(a)) {
					// Δανεισμός από τον δεξιό αδελφό 
				
					v.insertSon(v.getCurnofSons(), temp = sibling.deleteSon(0));
					v.insertItem(((abTNode)sibling.getParent()).getItem(v.getIndex()), c);
					((abTNode)sibling.getParent()).setItem(v.getIndex(), sibling.deleteItem(0));
					underflow = false;
					if (temp != null) {
						temp.setParent(v);
					}
				}
			}

			if (underflow) {
				// Ένωση με τον αριστερό αδελφό
				if (leftFuse) { 
					System.out.println("Left Fuse");	
					sibling = (abTNode)v.getLeftSibling();
					sibling.setItem(a - 1, ((abTNode)v.getParent()).deleteItem(sibling.getIndex()));
					for (int j = 0; j < a - 1; j++) {
						sibling.insertSon(a + j, v.deleteSon(0));
						sibling.setItem(a + j, v.deleteItem(0));
						if (sibling.getSon(a + j) != null) {
							sibling.getSon(a + j).setParent(sibling);
						}
					}

					if (v.getCurnofSons() > 0) {
						sibling.insertSon(2 * a - 2, v.deleteSon(0));
					}

					if (sibling.getSon(2 * a - 2) != null) {
						sibling.getSon(2 * a - 2).setParent(sibling);
					}
					sibling.setCurnofSons(2 * a - 1);
					if (v.getParent() != null) {
						v.getParent().deleteSon(v);
						v.setParent(null);
						v=(abTNode)sibling.getParent();
						underflow = v.underflow(a);
					}
				// Ένωση με τον δεξιό αδελφό
				} else {
					// System.out.println("Right Fuse");
					sibling = (abTNode)v.getRightSibling();
					v.setItem(a - 2, ((abTNode)v.getParent()).deleteItem(v.getIndex()));
					for (int j = 0; j < a - 1; j++){
						v.insertSon(a - 1 + j, sibling.deleteSon(0));
						v.setItem(a - 1 + j, sibling.deleteItem(0));
						if (v.getSon(a - 1 + j) != null) {
							v.getSon(a - 1 + j).setParent(v);
						}
					}
					if (sibling.getCurnofSons() > 0) {
						v.insertSon(2 * a - 2,sibling.deleteSon(0));
					}
					if (v.getSon(2 * a - 2) != null) {
						v.getSon(2 * a - 2).setParent(v);
					}
					v.setCurnofSons(2*a-1);
					if (sibling.getParent() != null) {
						sibling.getParent().deleteSon(sibling);
						sibling.setParent(null); 
						v = (abTNode)v.getParent();
						underflow = v.underflow(a);
					}
				}
			}

			if (isRoot(v)) {
				underflow = false;
				if(v.getCurnofSons() == 1){//Root deletion System.out.println("Root Deletion");
					setRoot(v.getSon(0));
					v.setSon(0,null);
					if (root() != null) {
						root().setParent(null);
					}
				}
			}			
		}
	}

	// Διαπέραση του δένδρου σε βάθος (in-order) και εμφάνιση των στοιχείων του.
	public static void showTree(abTNode v, int level) {
		if (v == null) {
			return;
		}

		// Αριστερα
		int m = v.getCurnofSons() / 2, M = v.getCurnofSons();
		for (int j = 0; j < m; j++) {
			showTree((abTNode)v.getSon(j), level + 1);
		}

		// Print
		for (int j = 0; j < level; j++) {
			System.out.print("\t"); 
		}
		System.out.print("L-" + level + ":");
		v.showNode();  

		// Δεξιά
		for (int j = m; j < M; j++) {
			showTree((abTNode)v.getSon(j), level + 1);
		}
	}

	public static void showTreeWithInfo(abTNode v, int level) {
		if (v == null) {
			return;
		}

		// Αριστερα
		int m = v.getCurnofSons() / 2, M = v.getCurnofSons();
		for (int j = 0; j < m; j++) {
			showTreeWithInfo((abTNode)v.getSon(j), level + 1);
		}

		// Print
		for (int j = 0; j < level; j++) {
			System.out.print("\t");
		}
		v.showNodeWithInfo();

		// Δεξιά
		for (int j = m; j < M; j++) {
			showTreeWithInfo((abTNode)v.getSon(j), level + 1);
		}
	}
}