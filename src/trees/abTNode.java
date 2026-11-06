package trees;

import items.Item;
import comparator.Comparator;

public class abTNode extends TNode {
	private int curnofSons; //current nof sons  

	// Constructor
	public abTNode(Item[] items, abTNode parent, int nofsons) {
		super(items, parent, nofsons);
		curnofSons = 1;
	}

	// Επιστρέφει true αν υπάρχει υπερχείλιση.
	public boolean overflow(int b) {
		return curnofSons == (b + 1);
	}

	// Επιστρέφει true αν υπάρχει έλλειμμα.
	public boolean underflow(int a) {
		return curnofSons == (a - 1);
	}

	// Επιστρέφει true αν υπάρχει δυνατότητα δανεισμού παιδιού.
	public boolean canBorrow(int a) {
		return curnofSons > a;
	}

	// Επιστρέφει το τρέχον πλήθος των παιδιών του κόμβου.
	public int getCurnofSons() {
		return curnofSons;
	}

	// Θέτει το τρέχον πλήθος παιδιών του κόμβου.
	public void setCurnofSons(int s) {
		curnofSons = s;
	}

	// Επιστρέφει το i-στο κλειδί του κόμβου.
	public Object getKey(int i){
		return ((Item[])element())[i].getKey();
	}

	 // Εύρεση της θέσης του key.
	public int findPositionItem(Object key, Comparator c) {
		if (curnofSons == 1) {
			return 0;
		}
		int j = 0;
		while (j < curnofSons - 1 && c.less(getKey(j), key)) {
			j++;
		}
		return j;
	}

	// Θέτει το i-στο item.
	protected void setItem(int i, Item item) {
		((Item[])element())[i] = item;
	}

	// Επιστρέφει το i-στο item.
	protected Item getItem(int i) {
		return ((Item[])element())[i];
	}	

	protected void incCurnofSons() {
		curnofSons = curnofSons + 1 <= nofSons ? curnofSons + 1 : curnofSons;
	}

	protected void decCurnofSons() {
		curnofSons = curnofSons - 1 >= 0 ? curnofSons - 1 : curnofSons;
	}	

	// Αναζήτηση στοιχείου με κλειδί key.
	public Item findItem(Object key, Comparator c) {
		int itemPos = findPositionItem(key, c);
		if (itemPos < 0) {
			return null;
		}
		return c.equal(getKey(itemPos), key) ? getItem(itemPos) : null;
	}

	// Διαγραφή και επιστροφή του i-στου στοιχείου.
	public Item deleteItem(int i) {
		if (i < 0 || i > curnofSons - 1) {
			System.out.println("Out of Bounds"); 
			return null;
		}
		Item temp = ((Item[])element())[i];

		for( ; i < curnofSons - 2 ; i++) {
			((Item[])element())[i] = ((Item[])element())[i + 1];
		}
		decCurnofSons();
		return temp;
	}

	// Overloaded: Διαγραφή του στοιχείου με κλειδί key.
	public int deleteItem(Object key, Comparator c) {
		int itemPos = findPositionItem(key, c);

		if (itemPos < 0) {
			return -1;
		}
		for (int i = itemPos; i <curnofSons - 2; i++) {
			((Item[])element())[i] = ((Item[])element())[i + 1];
		}
		decCurnofSons();
		return itemPos;
	}

	// Εισαγωγή στοιχείου item στη θέση itemPos.
	// Επιστρέφει τη θέση εισαγωγής ή -1 εάν δεν έγινε εισαγωγή.
	public int insertItem(Item item, Comparator c) {
		int itemPos = findPositionItem(item.getKey(), c);
		if (itemPos < 0) {
			return -1;
		}
		if (itemPos < curnofSons) {
			for (int i = curnofSons - 2; i >= itemPos; i--) {
				((Item[])element())[i + 1] = ((Item[])element())[i];
			}
		}
		((Item[])element())[itemPos] = item;
		incCurnofSons();
		return itemPos;
	}

	// Ελέγχει αν υπάρχει στοιχείο με κλειδί key.
	// Επιστρέφει τη θέση του στοιχείου ή -1 εάν δεν υπάρχει.
	public int keyExists(Object key, Comparator c) {
		int itemPos = findPositionItem(key, c);
		if (itemPos < 0 || itemPos == curnofSons - 1) {
			return -1;
		}

		if (c.equal(key,getKey(itemPos))) {
			return itemPos;
		} else {
			return -1;
		}
	}

	// Διαχωρισμός του κόμβου σε δύο κόμβους. Διασπά τον τρέχοντα κόμβο και μεταφέρει τα μισά
	// μεγαλύτερα στοιχεία σε νέο κόμβο. Επιστρέφει τον νέο κόμβο που δημιουργείται.
	public abTNode split() {
		abTNode buddy = new abTNode(new Item[nofSons - 1], (abTNode)this.getParent(), nofSons);
		int middle = nofSons / 2;
		for (int j = 0; j < nofSons - 1 - middle; j++){
			buddy.setItem(j, deleteItem(middle));
			buddy.setSon(j, deleteSon(middle));
			if (buddy.getSon(j) != null) {
				buddy.getSon(j).setParent(buddy);
			}
		}
		buddy.setSon(nofSons - 1 - middle, deleteSon(middle));
		if (buddy.getSon(nofSons - 1 - middle) != null) {
			buddy.getSon(nofSons - 1 - middle).setParent(buddy);
		}
		buddy.setCurnofSons((nofSons - 1) / 2 + 1);
		return buddy; 
	}	

	public boolean hasLeftSibling() {
		return getIndex() > 0;
	}	

	public boolean hasRightSibling() {
		int temp = getIndex();
		return (temp < curnofSons - 1 && temp >= 0);
	}

	public void showNode() {
		System.out.print("<");
		for (int i = 0; i < curnofSons - 1; i++) {
			System.out.print(" " + ((Item[])element())[i].getKey());
		}
		System.out.println(">");
	}  

	public void showNodeWithInfo() {
		System.out.print("<");
		for (int i = 0; i < curnofSons - 1; i++) {
			System.out.print(" " + ((Item[])element())[i].getKey() + ":" + ((Item[])element())[i].getInfo());
		}
		System.out.println(">");  
	} 
}
