package Dictionaries;

import trees.BTNode;

public class AvlTree extends BinarySearchTree {

	AvlTree(Comparator com){
		super(com);
	}

	AvlTree(AvlItem item, Comparator com) {
		super(item, com);
	}

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

	// called only if p is internal
	private void remedyHeight(BTNode v) {
		if (v == null) {
			System.out.println("Cannot remedy null node!");
			return;
		}

		AvlItem vItem = (AvlItem)v.getElement();

		vItem.setHeight(1 + Math.max(getRightSonHeight(v),getLeftSonHeight(v)));
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

	private void rebalance(BTNode v) {
		if (v == null) {
			System.out.println("Cannot rebalance null node!");
			return ;
		}
		BTNode u, w;
		// if (isRoot(v)) System.out.println("v is root");

		while (v != null) {
			remedyHeight(v);
			// System.out.println("UP:"+((avlItem)v.getElement()).getKey()+","+((avlItem)v.getElement()).getHeight());

			if (!isBalanced(v)) { 
				// System.out.println("Root:"+((avlItem)root().getElement()).getKey());
				// System.out.println("Balance because L"+getLeftSonHeight(v)+"R"+getRightSonHeight(v));
			
				w = heigherSon(v);
				u = heigherSon(w);
				// System.out.println("w:"+((avlItem)w.getElement()).getKey()+","+((avlItem)w.getElement()).getHeight()
				// +"u"+((avlItem)u.getElement()).getKey()+","+((avlItem)u.getElement()).getHeight());

				v = reconstruct(v, w, u);
				remedyHeight(v.getLeft());
				remedyHeight(v.getRight());
				remedyHeight(v);
			}
			v = v.getParent(); 
		}
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

	/*
	// methods of the dictionary ADT

	/** Overrides the corresponding method of the parent class. */
	public BTNode insertItem(Item i) {
		BTNode insNode=super.insertItem(i); // may throw an InvalidKeyException

		//   System.out.println("RBLNC:"+((avlItem)insNode.getElement()).getKey());
		if (insNode==null) return null;
		rebalance(insNode);
		return insNode;
	}
}
