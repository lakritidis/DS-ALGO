import Dictionaries.DictionaryNodeList;
import Dictionaries.Item;
import lists.SNode;
import lists.SinglyNodeList;
import tutorial.Artist;
import tutorial.Painter;

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
	//// Sorting
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
		myList.insertLast( 200);
		return myList;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// DictionaryLinkedList
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void checkDictionaryLinkedList() {
		DictionaryNodeList My=new DictionaryNodeList();
		//int[] Keys={9, 3, 0, 10, 2, 5, 1, 4, 7, 6, 8};
		//String [] Info = new String [] {"Info-9", "Info-3", "Info-0","Info-10","Info-2","Info-5","Info-1","Info-4","Info-7","Info-6","Info-8"};

		int[] Keys={45, 17, 22};
		String [] Info = new String [] {"Info-45", "Info-17", "Info-22"};


		for(int i=0; i<Keys.length; i++){
			Item myItem = new Item(Keys[i], Info[i]); 
			My.insertItem(myItem); 
		}

		// εμφανίζει όλες τις εγγραφές της δομής
		System.out.println("\n\nAll records in the Dictionary");
		System.out.println("---------------------");
		My.showRecords();
		System.out.println("---------------------\n");

		// Παραδείγματα κλήσης της findNode

		// κλήση της findNode με κλειδί 16
		int itemKey=16;
		SNode tmp = My.findNode(itemKey);
		if (tmp == null){
			System.out.println("findNode("+itemKey+") returned null");
		} else{
			System.out.println("findNode("+itemKey+") returned a not null value");
			Item tempItem = (Item)tmp.getElement();
			System.out.println(" findNode returned node with key:: "+tempItem.getKey());
		}
		System.out.println("---------------------");

		// κλήση της findNode με κλειδί 17
		itemKey=17;
		tmp = My.findNode(itemKey);
		if (tmp == null){
			System.out.println("findNode("+itemKey+") returned null");
		} else{
			System.out.println("findNode("+itemKey+") returned a not null value");
			Item tempItem = (Item)tmp.getElement();
			System.out.println(" findNode returned node with key: "+tempItem.getKey());
		}
		System.out.println("---------------------");

		// κλήση της findNode με κλειδί 22
		itemKey=22;
		tmp = My.findNode(itemKey);
		if (tmp == null){
			System.out.println("findNode("+itemKey+") returned null");
		} else{
			System.out.println("findNode("+itemKey+") returned a not null value");
			Item tempItem = (Item)tmp.getElement();
			System.out.println(" findNode returned node with key: "+tempItem.getKey());
		}
		System.out.println("---------------------");

		// κλήση της findNode με κλειδί 23
		itemKey=23;
		tmp = My.findNode(itemKey);
		if (tmp == null){
			System.out.println("findNode("+itemKey+") returned null");
		} else{
			System.out.println("findNode("+itemKey+") returned a not null value");
			Item tempItem = (Item)tmp.getElement();
			System.out.println(" findNode returned node with key: "+tempItem.getKey());
		}
		System.out.println("---------------------");

		// κλήση της findNode με κλειδί 45
		itemKey=45;
		tmp = My.findNode(itemKey);
		if (tmp == null){
			System.out.println("findNode("+itemKey+") returned null");
		} else{
			System.out.println("findNode("+itemKey+") returned a not null value");
			Item tempItem = (Item)tmp.getElement();
			System.out.println(" findNode returned node with key: "+tempItem.getKey());
		}
		System.out.println("---------------------");

		// κλήση της findNode με κλειδί 50
		itemKey=50;
		tmp = My.findNode(itemKey);
		if (tmp == null){
			System.out.println("findNode("+itemKey+") returned null");
		} else{
			System.out.println("findNode("+itemKey+") returned a not null value");
			Item tempItem = (Item)tmp.getElement();
			System.out.println(" findNode returned node with key: "+tempItem.getKey());
		}
		System.out.println("---------------------\n\n");


		// Παραδείγματα κλήσης της findInfo 

		// κλήση της findIfo με κλειδί 17
		itemKey=17;
		Item temp = (Item)My.findInfo(itemKey);
		if (temp == null){
		System.out.println("findInfo("+itemKey+") returned null");
		System.out.println("There is no node with this Key");
		} else {
		System.out.println(" findInfo("+itemKey+") returned a record with key: "+temp.getKey());
		System.out.println(" and info: "+temp.getInfo());
		}
		System.out.println("---------------------");
		// κλήση της findInfo με κλειδί 45
		itemKey=45;
		temp = (Item)My.findInfo(itemKey);
		if (temp == null) {
			System.out.println("findInfo("+itemKey+") returned null");
			System.out.println("There is no node with this Key");
		} else {
			System.out.println(" findInfo("+itemKey+") returned a record with key: "+temp.getKey());
			System.out.println(" and info: "+temp.getInfo());
		}
		System.out.println("---------------------");

		// κλήση της findNode με κλειδί 50
		itemKey=50;
		temp = (Item)My.findInfo(itemKey);
		if (temp == null) {
			System.out.println("findInfo("+itemKey+") returned null");
			System.out.println("There is no node with this Key");
		} else {
			System.out.println(" findInfo("+itemKey+") returned a record with key: "+temp.getKey());
			System.out.println(" and info: "+temp.getInfo());
		}
		System.out.println("---------------------\n\n");


		// κλήση της insertItem για προσθήκη εγγραφής με κλειδί 41
		Item newItem1 = new Item(41, "Info-41");
		My.insertItem(newItem1);
		System.out.println("A new record with key: "+newItem1.getKey()+" inserted to the Dictionary\n");
		// εμφανίζει όλες τις εγγραφές της δομής
		System.out.println("All records in the Dictionary");
		System.out.println("---------------------");
		My.showRecords();
		System.out.println("---------------------\n");

		// κλήση της insertItem για προσθήκη εγγραφής με κλειδί 16
		Item newItem2 = new Item(16, "Info-16");
		My.insertItem(newItem2);
		System.out.println("A new record with key: "+newItem2.getKey()+" inserted to the Dictionary\n");
		// εμφανίζει όλες τις εγγραφές της δομής
		System.out.println("All records in the Dictionary");
		System.out.println("---------------------");
		My.showRecords();
		System.out.println("---------------------\n");

		// κλήση της insertItem για προσθήκη εγγραφής με κλειδί 50
		Item newItem3 = new Item(50, "Info-50");
		My.insertItem(newItem3);
		System.out.println("A new record with key: "+newItem3.getKey()+" inserted to the Dictionary\n");
		// εμφανίζει όλες τις εγγραφές της δομής
		System.out.println("All records in the Dictionary");
		System.out.println("---------------------");
		My.showRecords();
		System.out.println("---------------------\n");


		// κλήση της deleteItem για απόσβεση της εγγραφής με κλειδί 22
		itemKey=22;
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

	public static void test_binary_search() {
/*
		Object[] A = new Object[22];
		for (int i = 0; i < A.length; i++)
			A[i] = A[i] + 3;

		Object[] A = new Object[] {2, 4, 5, 8, 9, 13, 17, 41, 42, 48, 50, 55, 57, 59, 61, 66, 67, 72, 74, 75, 87, 88};

		for(int i = 0; i < A.length; i++)
			System.out.print(((Integer)A[i]).intValue()+" ");

	    nofcomparisons=0;
    	System.out.println("");
		//System.out.print("\n\n Search for: "+A[8]+" Position:"+binarySearch(A, A[8], new comparator())+" nofcomparisons: "+nofcomparisons+"\n"); nofcomparisons=0;
		System.out.println("\n\n Search for: "+"56"+" Position:"+binarySearchRecursive(A, 0,21, 57, new Comparator())); 
*/
	}

    public static void main(String[] args) throws Exception {
		// java Tutorial
		// JavaTutorial();

		// Sorting
		//checkSortingFunctions();

		// Linked Lists
		//checkLinkedLists();
		// checkDictionaryLinkedList();
		SinglyNodeList lst = createLinkedList();
		lst.display();
    }
}
