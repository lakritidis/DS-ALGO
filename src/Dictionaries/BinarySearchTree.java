package Dictionaries;

import trees.LinkedBinaryTree;
import trees.BTNode;

public class BinarySearchTree extends LinkedBinaryTree {
	Comparator c;

	BinarySearchTree(Comparator com) {
		super();
		c = com;
	}

	BinarySearchTree(Item item, Comparator com){
		super(item);
		c = com;
	}

	public BTNode findNode(Object key, BTNode v){
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

	public Object findInfo(Object key, BTNode v){
		BTNode node = findNode(key, v);

		if (((Item)node.getElement()).getKey() == key) {
			return ((Item)node.getElement()).getInfo();
		} else {
			return null;
		}
	}

	public BTNode insertItem(Item i) {
		if (size() == 0) {
			setRoot(new BTNode(i, null, null, null));
			setSize(1);
			return root();
		}
		BTNode insNode = findNode(i.getKey(), root());
		Object keyNode = ((Item)insNode.getElement()).getKey();

		if (c.equal(keyNode, i.getKey())) {
			return null;
		} else {
			if (c.less(i.getKey(), keyNode)) {
				addLeaf(insNode, Left);
				insNode.getLeft().setElement(i);
				return insNode.getLeft();
			} else {
				addLeaf(insNode, Right);
				insNode.getRight().setElement(i);
				return insNode.getRight();
			}
		}
	}

	public BTNode deleteItem(Object key) {
		if (size() == 0) {
			return null;
		}

		BTNode delNode=findNode(key, root());
		Object keyNode=((Item)delNode.getElement()).getKey();

		if (!c.equal(keyNode, key)) {
			return null;
		} else {
			BTNode returnNode;
			if ((delNode.getLeft() == null) || (delNode.getRight() == null)) {
				returnNode = (delNode.getLeft() != null ? delNode.getLeft() :
					delNode.getRight() != null ? delNode.getRight() : delNode.getParent());
				deleteNode(delNode);
				return returnNode;
			} else{
				BTNode cursor = delNode.getRight(), temp, parentDelNode;

				while((temp = cursor.getLeft()) != null) {
					cursor = temp;
				}
				exchangeElements(cursor, delNode);
				parentDelNode = cursor.getParent();
				deleteNode(cursor);
				return parentDelNode;
			}
		}
	}
}
