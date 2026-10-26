import comparator.Comparator;
import lists.DictionaryNodeList;
import lists.SNode;
import lists.SinglyNodeList;
import trees.AvlTree;
import trees.BinarySearchTree;
import tutorial.Artist;
import tutorial.Painter;
import items.*;

public class App {
	public static void JavaTutorial() {
		Artist picasso = new Artist();
		Artist dali = new Artist();

		Artist picasso2 = new Artist("Pablo","Picasso");
		Artist picasso3 = new Artist("Pablo","Picasso", "Oct 25,1881", "Malaga", "Apr 8,1973", "Mougins");
		Artist dali2 = new Artist("Salvador","Dali","May 11 1904", "Figueres","Jan 23 1989", "Figueres");

		Painter elGreco = new Painter("Dominicos","Theotokopoulos","October 1, 1541","Herakleion","April 7, 1614","Toledo",115,"Mannerism");
		elGreco.displayArtistBio();

		picasso.displayArtistBio();
		picasso2.displayArtistBio();
		picasso3.displayArtistBio();

		dali.displayArtistBio();
		dali2.displayArtistBio();
		System.out.println("Style : "+elGreco.getStyle());
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// Sorting - Binary Search
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void checkSortingFunctions() {
		Integer [] a = {56, 78, 23, 12, 7, -100, 85, 94, 77, 23, 19, 60, 100, 1, -1};
		DSA_Array dsa = new DSA_Array(a);
		Integer key = 19;
		dsa.sort("mergesort", true);
		dsa.print_array();
		System.out.println("Key " + key + " was found at index " + dsa.BinarySearch(key));
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// LinkedList
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void checkLinkedLists() {
		SinglyNodeList[] My = {new SinglyNodeList(), new SinglyNodeList(), new SinglyNodeList()};
		int[][] A={{9, 3, 0, 10, 2, 5, 1, 4, 7, 6, 8},
				{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
				{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}};

		for(int j = 0; j < 3; j++)
			for(int i = 0; i < A[j].length; i++)
				My[j].insertFirst(A[j][i]);
 
		for(int i = 0; i < 3; i++) {
			System.out.println("" + i + " list");    	
			My[i].showList();     //ερώτημα a
			My[i].maxElement();   //ερώτημα b
			My[i].showList();
			My[i].minElement();   //ερώτημα c
			My[i].showList();
			My[i].listReversal(); //ερώτημα d
			My[i].showList();
		}
		My[0].insertFirst(100);
		My[0].showList();
	}

	public static SinglyNodeList createLinkedList() {
		SinglyNodeList myList = new SinglyNodeList();
		SNode node1 = myList.insertFirst(20);
		SNode node2 = myList.insertFirst(12);
		SNode node3 = myList.insertFirst(50);
		myList.insertAfter(node2, 100);
		myList.insertAfter(node1, 105);
		myList.insertAfter(node3, 77);
		myList.insertLast( 200);
		return myList;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// DictionaryLinkedList
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void checkDictionaryLinkedList() {
		// 1. Δημιουργία της λίστας που θα χρησιμοποιηθεί ως δομή λεξικού
		DictionaryNodeList My = new DictionaryNodeList();
		int[] Keys = {45, 17, 22};
		String [] Info = new String [] {"Info-45", "Info-17", "Info-22"};

		for(int i = 0; i < Keys.length; i++) {
			Item myItem = new Item(Keys[i], Info[i]);
			My.insertItem(myItem);
		}

		// 2. Eμφανίζει όλες τις εγγραφές της δομής
		System.out.println("\n\nEμφάνιση όλων των εγγραφών της δομής λεξικού");
		System.out.println("---------------------");
		My.showRecords();
		System.out.println("---------------------\n");

		// 3. Παραδείγματα κλήσης της findNode
		System.out.println("FindNode examples");
		int[] searchKeys = {16, 17, 22, 23, 45, 50};
		// κλήση της findNode με κλειδί 16
		for (int i = 0; i < searchKeys.length; i++) {
			int itemKey = searchKeys[i];
			SNode tmp = My.findNode(itemKey);
			if (tmp == null) {
				System.out.println("Η αναζήτηση του " + itemKey + " επέστρεψε null.");
			} else {
				Item tempItem = (Item)tmp.getElement();
				System.out.println("Η αναζήτηση του " + itemKey + " επέστρεψε τον κόμβο " + tempItem.getKey());
			}
		}
		System.out.println("\n");

		// 4. Παραδείγματα κλήσης της findInfo - Αναζήτηση πληροφοριών με βάση το κλειδί
		System.out.println("FindInfo examples");
		int[] searchKeys_sec = {16, 17, 22, 23, 45, 50};
		for (int i = 0; i < searchKeys_sec.length; i++) {
			int itemKey = searchKeys_sec[i];
			Item temp = (Item)My.findInfo(itemKey);
			if (temp == null) {
				System.out.println("Δεν υπάρχει εγγραφή με κλειδί " + itemKey);
			} else {
				System.out.println("Βρέθηκε εγγραφή με κλειδί: " + itemKey + " και πληροφορίες: " + temp.getInfo());
			}
		}
		System.out.println("\n\n");

		// 5. Παραδείγματα κλήσης της insertItem - Εισαγωγή νέων εγγραφών

		Item [] insert_items = {new Item(41, "Info-41"), new Item(16, "Info-16"), new Item(50, "Info-50")};
		for (int i = 0; i < insert_items.length; i++) {
			Item newItem = insert_items[i];
			My.insertItem(newItem);
			System.out.println("Εισαγωγή εγγραφής με κλειδί: " + newItem.getKey() + " στο λεξικό\n");

			// Εμφανίζει όλες τις εγγραφές της δομής
			System.out.println("Όλες οι εγγραφές στο λεξικό");
			System.out.println("---------------------");
			My.showRecords();
			System.out.println("---------------------\n");
		}

		// κλήση της deleteItem για απόσβεση της εγγραφής με κλειδί 22
		int itemKey=22;
		if (My.deleteItem(itemKey)){
		System.out.println("The record with key: "+itemKey+" deleted\n");
		} else{
		System.out.println("An error occured trying to delete record with key "+itemKey+"\n");
		}
		// εμφανίζει όλες τις εγγραφές της δομής
		System.out.println("All records in the Dictionary");
		System.out.println("---------------------");
		My.showRecords();
		System.out.println("---------------------\n");

		// κλήση της deleteItem για απόσβεση της εγγραφής με κλειδί 20
		itemKey=20;
		if (My.deleteItem(itemKey)){
		System.out.println("The record with key: "+itemKey+" deleted\n");
		} else{
		System.out.println("An error occured trying to delete record with key "+itemKey+"\n");
		}
		// εμφανίζει όλες τις εγγραφές της δομής
		System.out.println("All records in the Dictionary");
		System.out.println("---------------------");
		My.showRecords();
		System.out.println("---------------------\n");
	}

	public static BinarySearchTree create_binary_tree() {
		BinarySearchTree bst = new BinarySearchTree(new Comparator());
		bst.insertItem(new Item(10, "payload for key 10"));
		bst.insertItem(new Item(5, "payload for key 5"));
		bst.insertItem(new Item(1, "payload for key 1"));
		bst.insertItem(new Item(18, "payload for key 18"));
		bst.insertItem(new Item(15, "payload for key 15"));
		bst.insertItem(new Item(7, "payload for key 7"));
		bst.insertItem(new Item(9, "payload for key 9"));
		return bst;
	}

	public static AvlTree create_avl_tree() {
		AvlTree avl = new AvlTree(new Comparator());
		avl.insertItem(new AvlItem(10, "payload for key 10"));
		avl.insertItem(new AvlItem(5, "payload for key 5"));
		avl.insertItem(new AvlItem(1, "payload for key 1"));
		avl.insertItem(new AvlItem(18, "payload for key 18"));
		avl.insertItem(new AvlItem(15, "payload for key 15"));
		avl.insertItem(new AvlItem(7, "payload for key 7"));
		avl.insertItem(new AvlItem(9, "payload for key 9"));
		avl.insertItem(new AvlItem(2, "payload for key 2"));
		avl.insertItem(new AvlItem(3, "payload for key 3"));
		avl.insertItem(new AvlItem(4, "payload for key 4"));
		return avl;
	}

    public static void main(String[] args) throws Exception {
		// java Tutorial
		// JavaTutorial();

		// Sorting
		//checkSortingFunctions();

		// Linked Lists
		// checkLinkedLists();
		checkDictionaryLinkedList();
/*
		// BinarySearchTree tree = create_binary_tree();
		AvlTree tree = create_avl_tree();

		System.out.println();
		System.out.print("Pre-Order Traversal: ");
		tree.PreOrder();

		System.out.println();
		System.out.print("Post-Order Traversal: ");
		tree.PostOrder();

		System.out.println();
		System.out.print("In-Order Traversal: ");
		tree.InOrder();
*/
    }
}
