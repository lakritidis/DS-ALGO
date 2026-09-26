package tutorial;

import trees.*;
import lists.SinglyNodeList;
import java.util.Vector;

public class MyTraversals {
	// In-order traversal (starting from node a)
	public static void inorder(BTNode a){
		if (a == null) return;
		inorder(a.getLeft());
		System.out.print(((Item)a.getElement()).getKey() + " ");
		inorder(a.getRight());
	}

	// Pre-order traversal (starting from node a)
	public static void preorder(BTNode a) {
		if (a == null) return;
		System.out.print(((Item)a.getElement()).getKey() + " ");
		preorder(a.getLeft());
		preorder(a.getRight());
	}

	// Post-order traversal (starting from node a)
	public static void postorder(BTNode a) {
		if (a == null) return;	
		postorder(a.getLeft());
		postorder(a.getRight());
		System.out.print(((Item)a.getElement()).getKey() + " ");
	}

	// Euler traversal (starting from node a)
	public static void euler(BTNode a) {
		if (a == null) return;

		System.out.print("(");
		euler(a.getLeft());
		System.out.print(((Item)a.getElement()).getKey() + " ");
		euler(a.getRight());
		System.out.print(")");
	}

	public static void eulerC(BTNode a, Vector<BTNode> A) {
		if (a == null) return;
		for (int j = 0; j < A.size(); j++) {
			if (((BTNode)A.get(j)) == a) {
				return;
			}
		}

		A.addElement(a);
		System.out.print("(");
		eulerC(a.getLeft(), A);
		System.out.print(((Item)a.getElement()).getKey() + " ");
		eulerC(a.getRight(), A);
		System.out.print(")");
	}

	public static void eulerTrie(BTNode a){
		if (a == null) return;

		System.out.print("(");
		eulerTrie(a.getLeft());
		if (a.getElement() != null) {
			System.out.print(((Item)a.getElement()).getKey() + " ");
		} else {
			System.out.print(" O ");
		}

		eulerTrie(a.getRight());
		System.out.print(")");
	}

	/*   public static void eulerPat(BTNode a){
		
		if (a==null) return;
		System.out.print("(");
		if(a.getLeft()!=null)
		if (((PatItem) a.getElement()).getCheckDigit()<=
		((PatItem)a.getLeft().getElement()).getCheckDigit()&&a.getLeft()!=a ) 
			eulerPat(a.getLeft());
		System.out.print(((PatItem)a.getElement()).getKey()+":"+
		((PatItem)a.getElement()).getCheckDigit()+" ");
		if(a.getRight()!=null)
		if (((PatItem) a.getElement()).getCheckDigit()<
		((PatItem)a.getRight().getElement()).getCheckDigit()) 
		eulerPat(a.getRight());
		System.out.print(")");
	}

	public static void inorderPat(BTNode v, int d)
	{ 
		if (((PatItem)v.getElement()).getCheckDigit() <= d) 
		{ System.out.print(((PatItem)v.getElement()).getKey()+" "); return; }
		inorderPat(v.getLeft(),((PatItem)v.getElement()).getCheckDigit() );
		inorderPat(v.getRight(),((PatItem)v.getElement()).getCheckDigit() );
	}

		public static void eulerRB(BTNode a){
		if (a==null) return;
		
		System.out.print("(");
		eulerRB(a.getLeft());
		System.out.print(((RedBlackItem)a.getElement()).getKey()+
		(((RedBlackItem)a.getElement()).getColor()?"B ":"R "));
		eulerRB(a.getRight());
		
			System.out.print(")");
	}

	public static void eulerTreap(BTNode a){
		if (a==null) return;
		
		System.out.print("(");
		eulerTreap(a.getLeft());
		System.out.print(((treapItem)a.getElement()).getKey()+":"+
		((treapItem)a.getElement()).getPriority());
		eulerTreap(a.getRight());
		System.out.print(")");
	}*/

	public static void LevelOrder(BTNode a) {	 
	SinglyNodeList MyFiFo = new SinglyNodeList();
	BTNode w;

	MyFiFo.insertLast(a);
	while (! MyFiFo.isEmpty()){
		w = (BTNode) MyFiFo.removeFirst();
		System.out.print(((Item)w.getElement()).getKey());
		if (w.getLeft()!=null) MyFiFo.insertLast(w.getLeft());
		if (w.getRight()!=null) MyFiFo.insertLast(w.getRight());
	}
	}

	}