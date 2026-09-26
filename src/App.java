import Dictionaries.SearchNodeList;
import Dictionaries.Comparator;
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

	public static void checkSortingFunctions() {
		Integer [] a = {56, 78, 23, 12, 7, -100, 85, 94, 77, 23, 19, 60, 100, 1, -1};
		DSA_Array dsa = new DSA_Array(a);
		Integer key = 19;
		dsa.sort("mergesort", true);
		dsa.print_array();
		System.out.println("Key " + key + " was found at index " + dsa.BinarySearch(key));
	}

	public static void checkLinkedLists() {
		SinglyNodeList[] My = {new SinglyNodeList(), new SinglyNodeList(), new SinglyNodeList()};
		int[][] A={{9, 3, 0, 10, 2, 5, 1, 4, 7, 6, 8},
				{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
				{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}};

		for(int j = 0; j < 3; j++)
			for(int i = 0; i < A[j].length; i++)
				My[j].insertFirst(A[j][i]);
 
		for(int i=0; i<3; i++) {
			System.out.println(""+i+" list");    	
			My[i].showList();     //ερώτημα a
			My[i].maxElement();   //ερώτημα b
			My[i].showList();
			My[i].minElement();   //ερώτημα c
			My[i].showList();
			My[i].listReversal(); //ερώτημα d
			My[i].showList();
		}
	}

	public static void checkDictionaryLinkedList() {
		Comparator c;
		SearchNodeList[] My = { new SearchNodeList(c), new SearchNodeList(c), new SearchNodeList(c) };
		int[][] A={{9, 3, 0, 10, 2, 5, 1, 4, 7, 6, 8},
				{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
				{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}};

		for(int j = 0; j < 3; j++)
			for(int i = 0; i < A[j].length; i++)
				My[j].insertFirst(A[j][i]); 

		SNode Result1 = My[0].findNode(7);
		if (Result1 != null) {
			System.out.println("Found " + ((Integer)Result1.getElement()).intValue());
		} else {
			System.out.println("Not Found ");
		}
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
		checkDictionaryLinkedList();
    }
}
