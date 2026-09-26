package trees;

// η νέα μας κλάση, στιγμιότυπα της οποίας θα αποθηκεύονται στις	
// διάφορες δομές που θα συναντήσουμε στην συνέχεια
public class Item{
	private Object key, info;		// το κλειδί και η πληροφορία αντίστοιχα…
	protected Item(Object k, Object i){	// ο constructor
		key = k;
		info = i;
	}

	public Object getKey() {	// επιστρέφει το κλειδί του αντικειμένου
		return key;
	}
	public Object getInfo() {	// επιστρέφει την πληροφορία του αντικειμένου
		return info;
	}
	public void setKey(Object k) {	// θέτει το κλειδί του αντικειμένου
		key = k;
	}
	public void setInfo(Object i) {	// θέτει την πληροφορία του αντικειμένου
		info = i;
	}
}
