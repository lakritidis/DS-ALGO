package trees;

public class TNode {
	protected Object element;
	protected TNode parent;
	protected TNode[] sons; //o πατέρας διαθέτει μέχρι nofSons παιδιά (σπατάλη χώρου)
	protected int nofSons;

	// Default constructor
	public TNode() { }

	// constructor2
	public TNode(Object o, TNode p, int nofsons) {
		element = o;
		parent = p;
		sons = new TNode[nofsons];
		nofSons = nofsons;
	}

	public Object element() { 	// Επιστρέφει το αποθηκευμένο στοιχείο
		return element; 
	}

	public void setElement(Object o) { // Θέτει το αποθηκευμένο στοιχείο
		element = o; 
	}

	public TNode getSon(int i) { // Επιστρέφει τον δείκτη προς το i-στο παιδί
		if (i > nofSons - 1) {
			System.out.println("No such son...");
			return null;
		}
		return sons[i]; 
	}
		
	public boolean setSon(int i, TNode v) { // Θέτει τον δείκτη προς το i-στο παιδί ίσο με v
		if (i > nofSons - 1) {
			System.out.println("No such son...");
			return false;
		}
		sons[i] = v; 
		return true;
	}

	public TNode getParent() { 	// Επιστρέφει τον δείκτη προς τον πατέρα
		return parent; 
	}

	public void setParent(TNode v) { // Θέτει τον δείκτη προς τον πατέρα ίσο με v
		parent = v; 
	}

	public boolean insertSon(int i, TNode v){
		if (i > nofSons - 1) {
			System.out.println("No such son...");
			return false;
		}
		for (int j = nofSons - 2; j >= i; j--) {
			sons[j + 1] = sons[j];
		}
		sons[i] = v;
		return true;
	}

	public boolean deleteSon(TNode v){
		if (v == null) {
			return false;
		}

		int i = 0;
		for (; i < nofSons; i++) {
			if (sons[i] == v) {
				break;
			}
		}

		if (i == nofSons) {
			return false;
		}
		for (; i < nofSons - 1; i++) {
			sons[i] = sons[i + 1];
		}
		return true;
	}

	public TNode deleteSon(int i) {
		TNode temp = sons[i];
		for (int j = i; j < nofSons - 1; j++) {
			sons[i] = sons[i + 1];
		}
		return temp;
	}

	public int getIndexofSon(TNode v) {
		if (v == null) {
			return -1;
		}
		int i = 0;
		for (; i < nofSons; i++) {
			if (sons[i] == v) {
				break;
			}
		}

		if (i == nofSons) {
			return -1;
		} else {
			return i;
		}
	}

	public int getIndex() { // epistrefei to index tou
		return getParent().getIndexofSon(this);
	}

	public TNode getLeftSibling() {
		int i = getIndex();
		if (i == 0) {
			return null;
		} else {
			return getParent().getSon(i - 1);
		}
	}

	public TNode getRightSibling(){
		int i = getIndex();
		if (i == nofSons) {
			return null;
		} else {
			return getParent().getSon(i + 1);
		}   
	}
}
