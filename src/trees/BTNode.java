package trees;

// Binary Tree Node
public class BTNode { 
	private Object element;             // The element that is stored in the node
	private BTNode left, right, parent; // Pointers to the parent and the left and right children.

	// Empty constructor
	public BTNode() { }

	// Constructor
	public BTNode(Object o, BTNode u, BTNode v, BTNode w) {
		setElement(o);
		setParent(u);       
		setLeft(v);
		setRight(w);
	}

	// Returns the element that is stored in the node.
	public Object getElement() { return element; }

	// Sets the element to be stored in the node.
	public void setElement(Object o) {
		element = o;
	}

	// Returns a pointer to the left child
	public BTNode getLeft() {
		return left;
	}

	// Sets the pointer to the left child
	public void setLeft(BTNode v) {
		left = v;
	}

	// Returns a pointer to the right child
	public BTNode getRight() {
		return right; 
	}

	// Sets the pointer to the right child
	public void setRight(BTNode v) {
		right = v;
	}

	// Returns a pointer to the parent node
	public BTNode getParent() {
		return parent;
	}

	// Sets the pointer to the parent node
	public void setParent(BTNode v) {
		parent = v;
	}
}
