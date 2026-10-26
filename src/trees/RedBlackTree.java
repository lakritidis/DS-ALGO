package trees;

import items.Item;
import items.RedBlackItem;
import comparator.Comparator;

public class RedBlackTree extends BinarySearchTree {
	static final boolean black = true;
	static final boolean red = false;

	public RedBlackTree(Comparator com) {
		super(com);
	}

	public RedBlackTree(RedBlackItem item, Comparator com) {
		super(item, com);
	}

	public void exchangeElements(BTNode v, BTNode w) {
		boolean colorv = ((RedBlackItem)v.getElement()).getColor();
		boolean colorw = ((RedBlackItem)w.getElement()).getColor();
		((RedBlackItem)v.getElement()).setColor(colorw);
		((RedBlackItem)w.getElement()).setColor(colorv);
		super.exchangeElements(v, w);
	}

	private boolean setBlack(BTNode v) {
		if (v == null) {
			System.out.println("Κενός κόμβος δεν μπορεί να οριστεί μαύρος");
			return false;
		}
		((RedBlackItem)v.getElement()).setColor(black);
		return true;
	}

	private boolean setRed(BTNode v) {
		if (v == null) {
			System.out.println("Κενός κόμβος δεν μπορεί να οριστεί κόκκινος");
			return false;
		}
		((RedBlackItem)v.getElement()).setColor(red);
		return true;
	}	

	// Επιστρέφει true αν ο κόμβος v είναι μαύρος, false διαφορετικά
	private boolean isBlack(BTNode v){
		if (v == null) {
			System.out.println("Κενός κόμβος -> θεωρείται μαύρος");
			return true;
		}
		return ((RedBlackItem)v.getElement()).getColor() == black;
	}

	// Επιστρέφει true αν ο κόμβος v είναι κόκκινος, false διαφορετικά
	private boolean isRed(BTNode v){
		if (v == null) {
			System.out.println("Κενός κόμβος -> θεωρείται κόκκινος");
			return false;
		}
		return ((RedBlackItem)v.getElement()).getColor() == red;
	}

	// Επιστρέφει το χρώμα του κόμβου v
	private boolean getColor(BTNode v) {
		if (v == null) {
			System.out.println("Κενός κόμβος -> θεωρείται μαύρος");
			return true;
		}
		return (((RedBlackItem)v.getElement()).getColor());
	}

	// Επιστρέφει true αν ο κόμβος v έχει ακριβώς ένα κόκκινο παιδί, false διαφορετικά
	private boolean hasOneRedSon(BTNode v) {
		if (v == null) {
			System.out.println("Κενός κόμβου δεν μπορεί να ελεγχθεί αν έχει ακριβώς ένα κόκκινο παιδί");
			return false;
		}
		return (isRed(v.getLeft())&&isBlack(v.getRight()) || isRed(v.getRight())&&isBlack(v.getLeft()));
	}

	// Επιστρέφει το κόκκινο παιδί του κόμβου v, αν υπάρχει, null διαφορετικά
	private BTNode getRedSon(BTNode v){
		if (v == null) {
			System.out.println("Κενός κόμβος δεν έχει παιδιά");
			return null;
		}
		return isRed(v.getLeft()) ? v.getLeft() : v.getRight();
	}

	private void insertionRebalance(BTNode v) {
		if (v == null) {
			System.out.println("Δεν μπορεί να γίνει επαναζύγιση σε κενό κόμβο");
			return ;
		}
		BTNode u = null, w = v;
		// 	if (isRoot(v)) System.out.println("GGGGGGGGGGGGGGGGGGGGGr");
		v = v.getParent();
		while (isRed(v)) {
			if (isBlack(getSibling(v))) { 
				u = w;
				w = v;
				v = v.getParent();
				v = reconstruct(v, w, u);
				setRed(v.getLeft());
				setRed(v.getRight());
				setBlack(v);
				// break;
			} else {
				System.out.println("CFlip");
				setBlack(v);
				setBlack(getSibling(v));

				v = v.getParent();
				if (isRoot(v)) {
					break;
				}
				setRed(v); 
				u = w;
				w = v;
				v = v.getParent();
			}
		}
	}

	private void deletionRebalance(BTNode y){
		if (y == null){
			System.out.println("Δεν μπορεί να γίνει επαναζύγιση σε κενό κόμβο");
			return;
		}

		BTNode sibling = y.getLeft() != null ? y.getLeft() : y.getRight();
		BTNode childofSibling;

		while (y != null) {
			if (isBlack(sibling)) {
				boolean parentColor = getColor(y);
				if (hasOneRedSon(sibling)) { // Case 1: trinode restructuring
					y = reconstruct(y, sibling, getRedSon(sibling));
					setBlack(y.getLeft());
					setBlack(y.getRight());
					if (parentColor == black) {
						setBlack(y);
					}else {
						setRed(y);
					}
					return;
				}
				setBlack(y);
				if (sibling != null) {
					setRed(sibling);
				}
				if ((parentColor == red) /*|| (isRoot(y))*/) {
					System.out.println("C2a");
					return;
				} else {
					System.out.println("C2b");
					if (isRoot(y)) {
						return;
					}
					sibling = getSibling(y);
					y = y.getParent();
				}
			} else { 
				boolean kindof;
				BTNode newSibling;
				if (isLeft(sibling)) {
					newSibling=sibling.getRight();
					childofSibling = sibling.getLeft();
					kindof = Left;//left
				} else {
					newSibling=sibling.getLeft();
					childofSibling = sibling.getRight();
					kindof = Right;
				}
				setBlack(reconstruct(y, sibling, childofSibling, kindof));//right
				setRed(getSibling(childofSibling));
				sibling = newSibling;
				System.out.println("C3");
			}
		}
	}

	private BTNode reconstruct(BTNode v, BTNode w, BTNode u, boolean kindofu) {
		// Right Rotation
		if (isLeft(w) && kindofu == Left) {
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
		} else if (isRight(w) && kindofu == Right) {
			if (!isRoot(v)) {
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
		} else if (isRight(w) && kindofu == Right) {
			if (!isRoot(v)) {
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
		// Left Double rotation
		} else if (isLeft(u)){
			v.setRight(u.getLeft());
			if (u.getLeft() != null) {
				u.getLeft().setParent(v);
			}
			w.setLeft(u.getRight());
			if (u.getRight() != null) {
				u.getRight().setParent(w);
			}
			if (!isRoot(v)){
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
		// Right Double rotation
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

	private BTNode reconstruct(BTNode v, BTNode w, BTNode u){
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
		} else if (isRight(w) && isRight(u)) {
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
		// Left Double rotation
		} else if (isLeft(u)) {
			v.setRight(u.getLeft());
			if (u.getLeft() != null) {
				u.getLeft().setParent(v);
			}
			w.setLeft(u.getRight());
			if (u.getRight() != null) {
				u.getRight().setParent(w);
			}
			if (!isRoot(v)){
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

			// Right Double rotation
		} else {
			v.setLeft(u.getRight());
			if (u.getRight() != null) {
				u.getRight().setParent(v);
			}
			w.setRight(u.getLeft());
			if (u.getLeft() != null) {
				u.getLeft().setParent(w);
			}
			if (!isRoot(v)){
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

	public BTNode insertItem(Item i){
		BTNode insNode = super.insertItem(i); // may throw an InvalidKeyException
		if (insNode == null) {
			return null;
		}

		if (isRoot(insNode)) {
			setBlack(insNode);
		} else {
			insertionRebalance(insNode);
		}
		return insNode;
	}

	public BTNode deleteItem(Object key) {
		BTNode delNode = super.deleteItem(key);
		// System.out.println("RBLNC:"+((avlItem)delNode.getElement()).getKey());
		if (delNode == null) {
			return null;
		}
		System.out.println(((RedBlackItem)lastDeletedElement).getColor() + "" + ((RedBlackItem)lastDeletedElement).getKey());

		//   if (isRed(delNode)&&hasOneRedSon(delNode))
		if (isRed(delNode) && isRed(delNode.getParent())) {
			setBlack(delNode);
		} else 
			if ( (((RedBlackItem)lastDeletedElement).getColor()==red)||isRoot(delNode)

			)  
			;// setBlack(delNode); 
		else deletionRebalance(delNode);
		return delNode;
	}
}
