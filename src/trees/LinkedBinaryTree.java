package trees;

// Binary tree
public class LinkedBinaryTree {
	private BTNode Root;					// Pointer to the tree's root
	protected int size;						// The size of the tree
	protected static boolean Left = true;	// Left child
	protected static boolean Right = false;	// right child
	protected Object lastDeletedElement;

	// Constructor 1
	public LinkedBinaryTree() {
		Root = null;
		size = 0;
	}

	// Constructor 2: It sets the root
	public LinkedBinaryTree(BTNode o) {
		Root = o;
	}

	// Constructor 3: It sets the root with a new node from the element o
	public LinkedBinaryTree(Object o) {
		Root = new BTNode(o, null, null, null);
		size = 1;
	}

	// Get the size of the tree
	public int size() {
		return size; 
	}

	// Get a pointer to the tree's root
	public BTNode root() {
		return Root;
	}

	// Set the root of the tree to be v
	public void setRoot(BTNode v) {
		Root = v;
	}

	// Set the size of the tree
	public void setSize(int s) {
		size = s;
	}

	// Chech whether v is the tree's root
	public boolean isRoot(BTNode v) {
		return v == Root;
	}

	// Check whether v is a left child
	public boolean isLeft(BTNode v) {
		if (v == root()) {
			System.out.println("Eisai sthn Riza");
			return false;
		}
		return (v == (v.getParent().getLeft()));
	}

	// Check whether v is a right child
	public boolean isRight(BTNode v) {
		if (v == root()) {
			System.out.println("Eisai sthn riza");
			return false;
		}
		return (v == v.getParent().getRight());
	}

	// Add a left or right leaf. First check whether a left or rightchild exists.
	public void addLeaf(BTNode v, boolean kindofson) {
		if (kindofson == Left) {  // θα αναθέσουμε στον v ένα αριστερό παιδί
			if (v.getLeft() != null) { // Υπάρχει ήδη αριστερό παιδί μη κενό
				System.out.println("Yparxei aristero paidi");
				return;
			}
			// εισαγωγή του νέου κόμβου
			v.setLeft(new BTNode(null, v, null, null));
		} else { // θα αναθέσουμε στον v ένα δεξί παιδί
			if (v.getRight() != null) { // Υπάρχει ήδη δεξιό παιδί μη κενό
				System.out.println("Yparxei deksio paidi");
				return;
			}
			// εισαγωγή του νέου κόμβου
			v.setRight(new BTNode(null, v, null, null));
		}
		// ενημέρωση του μεγέθους του δέντρου
		size++;
	}

	// Delete a node from the tree: The node may only have left or right children. Not both.
	public void deleteNode(BTNode v) {
		// The node has a left and a right child
		if ((v.getLeft() != null) && (v.getRight() != null)) {
			System.out.println("Internal node...");
			return;
		}

		// The node is the tree's root
		if (isRoot(v)) {
			BTNode notNullSonofv = (v.getLeft() != null ? v.getLeft() : v.getRight() != null ? v.getRight():null);

			Root = notNullSonofv;
			if (Root != null) {
				Root.setParent(null);
			}
		// The node is not in the root.
		} else {
			BTNode parentOfv = v.getParent();

			// Find the not null child of v
			BTNode notNullSonofv = (v.getLeft() != null ? v.getLeft() : v.getRight() != null ? v.getRight():null);
			if (isLeft(v)) {
				parentOfv.setLeft(notNullSonofv);
			} else {
				parentOfv.setRight(notNullSonofv);
			}

			if (notNullSonofv != null) {
				notNullSonofv.setParent(parentOfv);
			}
		}
		size--;
		v.setLeft(null);
		v.setRight(null);
		v.setParent(null);

		lastDeletedElement = v.getElement();
		return;
	}

	// Delete a node from the tree: The node may only have left or right children. Not both.
	public void deleteNode_simplified(BTNode v) {
		// Δεν επιτρέπεται η διαγραφή κόμβου με δύο παιδιά
		if ((v.getLeft() != null) && (v.getRight() != null)) { 	
			System.out.println("Δύο παιδιά - Δεν επιτρέπεται διαγραφή"); 
			return;
		}
		if (isRoot(v)) {
			BTNode notNullSonofv = (v.getLeft() != null ? v.getLeft() : v.getRight());
			Root = notNullSonofv; // ο NotNullSonofv θα γίνει η νέα ρίζα
			// ο Root δεν έχει πατέρα!
			Root.setParent(null);
		} else { 				// δεν είναι ρίζα
			BTNode parentOfv = v.getParent(); 	// οπότε έχει πατέρα
			BTNode notNullSonofv = (v.getLeft() != null ? v.getLeft() : v.getRight());
			if (isLeft(v))			// ο v είναι ο αριστερός γιος
				parentOfv.setLeft(notNullSonofv);
			else				// ο v είναι ο δεξιός γιος
				parentOfv.setRight(notNullSonofv);
			notNullSonofv.setParent(parentOfv); // αλλαγή πατρός…
		}
		size--;				// ενημέρωση μεγέθους δέντρου
		v.setLeft(null);
		v.setRight(null);
		v.setParent(null);			// γείωση του v
		lastDeletedElement = v.getElement();	// αποθήκευση του σβησμένου στοιχείου
	}

	// Get the sibling of a node
	public BTNode getSibling(BTNode v){
		if ((v == null) || isRoot(v)) {
			System.out.println("Cannot find sibling");
			return null;
		}

		if (isRight(v)) {
			return v.getParent().getLeft();
		} else {
			return v.getParent().getRight();
		}
	}

	// Exchange elements v and w
	public void exchangeElements(BTNode v, BTNode w) {
		Object temp=v.getElement();
		v.setElement(w.getElement());
		w.setElement(temp);
	}
}
