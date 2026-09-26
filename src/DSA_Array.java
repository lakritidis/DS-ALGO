import lists.SinglyNodeList;

public class DSA_Array {
	private Object [] a;
	private Comparator c;

	public DSA_Array(Object [] in) {
		a = in;
		c = new Comparator();
	}

	// Mη αναδρομική υλοποίηση της διαδικασίας δυαδικού ψαξίματος. Η κλάση comparator
	// παρέχει τις μεθόδους συγκρίσεως μεταξύ των αντικειμένων, βάσει της υποκείμενης 
	// ολικής διάταξης
	public int BinarySearch(Object o) {
		int left = 0;                        // αριστερό όριο
		int right = a.length - 1;            // δεξιό όριο
		while (right >= left) {		         // όσο δεν ταυτίστηκαν τα όρια
			int middle = (left + right) / 2; // το μεσαίο όριο...
			if  (c.equal(o, a[middle])) {    // το βρήκαμε..
				return middle;		         // επιστρέφουμε την θέση του αντικειμένου
			}
			if (c.less(o, a[middle])) {      // θα ψάξουμε στο αριστερό τμήμα, 
				right = middle - 1;          // οπότε προσαρμόζουμε το δεξιό όριο
			} else {                         // θα ψάξουμε στο δεξιό τμήμα, 
				left = middle + 1;           // οπότε προσαρμόζουμε το αριστερό όριο
			}
		}
		return -1;
	}

	public Object [] sort(String algo, boolean in_place) {
		Object ar[];
		if (in_place) {
			ar = a;
		} else {
			ar = a.clone();
		}

		switch (algo) {
			case "insertion":
				System.out.println("Running insertion sort");
				insertionSort(ar, 0, ar.length - 1);
				break;
			case "selection":
				System.out.println("Running selection sort");
				selectionSort(ar, 0, ar.length - 1);
				break;
			case "bubble":
				System.out.println("Running bubble sort");
				bubbleSort(ar, 0, ar.length - 1);
				break;
			case "shaker":
				System.out.println("Running shaker sort");
				shakerSort(ar, 0, ar.length - 1);
				break;
			case "qsort":
				System.out.println("Running Quick sort (recursive)");
				quickSort(ar, 0, ar.length - 1);
				break;
			case "stack-qsort":
				System.out.println("Running Quick sort (non-recursive)");
				stackQuickSort(ar, 0, ar.length - 1);
				break;
			case "mergesort":
				System.out.println("Running Merge sort");
				mergeSort(ar, 0, ar.length - 1, new Object[a.length]);
				break;

			default:
				insertionSort(ar, 0, ar.length - 1);
		}
		return ar;
	}

	// InsertionSort
	private void insertionSort(Object [] ar, int left, int right) {
		for (int i = right; i > left; i--) {    // κάθοδος του μικρότερου στοιχείου
			if ( c.less(ar[i], ar[i - 1]) ) {   // στην αριστερότερη θέση με ανταλλαγές
				Object temp = ar[i];
				ar[i] = ar[i - 1];
				ar[i - 1] = temp;
			}
		}
		
		for (int i = left + 2; i <= right; i++) {      
			int j = i;                      // το στοιχείο i πρέπει να μπει στην σωστή θέση 
			Object v = ar[i];               // το i-στο στοιχείο...    
			while (c.less(v, ar[j - 1])) {  // ανακαλύπτει την θέση που πρέπει να μπει       
				ar[j] = ar[j - 1];
				j--;
			}
			ar[j] = v;                      // τοποθέτηση στην σωστή του θέση...
		}
	}

	// SelectionSort: διατάσσει τον πίνακα μεταξύ του left και right χρησιμοποιώντας την κλάση c για τις συγκρίσεις
	private void selectionSort(Object[] ar, int left, int right) {
		Object temp;
		for (int unorderedleft = left; unorderedleft < right; unorderedleft++) {
			int min = unorderedleft;
			for (int j = unorderedleft + 1; j <= right; j++) {	// εύρεση του ελαχίστου
				if (c.less(ar[j], ar[min])) {
					min = j;
				}
			}

			// ανταλλαγή του ελαχίστου με το αριστερό όριο του αταξινόμητου τμήματος
			temp = ar[unorderedleft];
			ar[unorderedleft] = ar[min];
			ar[min] = temp;
		}
	}

	// BubbleSort: Διατάσσει τον πίνακα μεταξύ του left και right χρησιμοποιώντας την κλάση c για τις συγκρίσεις
	private void bubbleSort(Object ar[], int left, int right) {
		Object temp;
		for (int i = left; i < right; i++) {	// η θέση i αποτελεί το αριστερό όριο του
			for (int j = right; j > i; j--) {	// αταξινόμητου τμήματος
				if (c.less(ar[j], ar[j - 1])) {	// είναι εκτός διατάξεως, οπότε τα ανταλλάσσουμε...
					temp = ar[j - 1];
					ar[j - 1] = ar[j];
					ar[j] = temp;
				}
			}
		}
	}

	// ShakerSort: Διατάσσει τον πίνακα μεταξύ του left και right χρησιμοποιώντας την κλάση c για τις  συγκρίσεις.
	// Χρησιμοποιεί την απλή εκδοχή του bubble sort.
	private void shakerSort(Object ar[], int left, int right) {
		Object temp;
		boolean leftdirection = true;                // σημαία κατευθύνσεως αλγορίθμου
		while (left < right) {                       // όσο ο πίνακας δεν έχει διαταχθεί πλήρως...
			if (leftdirection) {                     // "ανάμειξη" προς τα αριστερά...
				leftdirection = !leftdirection;      // αλλαγή της σημαίας 
				for (int j = right; j > left; j--) { // BubbleSort προς τα αριστερά
					if (c.less(ar[j], ar[j - 1])) {
						temp = ar[j - 1];
						ar[j - 1] = ar[j];
						ar[j] = temp;
					}
				}
				left++;		                         // ενημέρωση του αριστερού ορίου
			} else {		                         // "ανάμειξη" προς τα δεξιά...
				leftdirection = !leftdirection; 	 // αλλαγή της σημαίας
				for (int j = left; j < right; j++) { // bubblesort προς τα δεξιά
					if (c.less(ar[j + 1], ar[j])) {
						temp = ar[j + 1];
						ar[j + 1] = ar[j];
						ar[j] = temp;
					}
				}
				right--;	                         // ενημέρωση του δεξιού ορίου
			}
		}
	}

	// Αναδρομική υλοποίηση QuickSort: Διατάσσει τον πίνακα μεταξύ του left και right,
	// χρησιμοποιώντας την κλάση c για τις συγκρίσεις
	private void quickSort(Object a[], int left, int right) {
		int i = left - 1, j = right;
		Object o = a[right], temp;
		while (true) {                   // εύρεση θέσεως i όπου θα τοποθετηθεί το στοιχείο διαχωρισμού a[right]
			while (c.less(a[++i], o))    // ανεβαίνει ο δείκτης i‑θα σταματήσει είτε όταν ανακαλύψει
				;                        // στοιχείο μεγαλύτερο του a[right] είτε όταν φθάσει στην θέση right
			while (c.less(o, a[--j]))    // κατεβαίνει ο δείκτης j‑θα σταματήσει είτε όταν ανακαλύψει
				if  (j == left)          // στοιχείο μεγαλύτερο του a[right] είτε όταν φθάσει στην θέση left
					break;
			if (i >= j)                  // οι δείκτες συναντήθηκαν ή πέρασαν στο άλλο τμήμα
				break;
			temp = a[i];                 // ανταλλαγή των αντικειμένων στις θέσεις  i, j
			a[i] = a[j];
			a[j] = temp;
		}
		temp = a[i];                     // το στοιχείο διαχωρισμού a[right] μεταφέρεται στην θέση i
		a[i] = a[right];
		a[right] = temp;                 // αναδρομική ταξινόμηση των δύο τμημάτων εκατέρωθεν του i
		if (left < i - 1)                // έχει νόημα η κλήση
			quickSort(a, left, i - 1);
		if (i + 1 < right)               // έχει νόημα η κλήση
			quickSort(a, i + 1, right);
	}

	// Μη αναδρομική υλοποίηση QuickSort με χρήση στοίβας
	// Διατάσσει τον πίνακα μεταξύ του left και right χρησιμοποιώντας την κλάση c για τις συγκρίσεις.
	private void stackQuickSort(Object a[], int left, int right){
		SinglyNodeList Mystack=new SinglyNodeList();

		Mystack.insertFirst(right);
		Mystack.insertFirst(left);

		while (!Mystack.isEmpty()) {
			left = ((Integer) Mystack.removeFirst()).intValue(); 
			right = ((Integer) Mystack.removeFirst()).intValue(); 

			if (right <= left) {
				continue;
			}

			int i = left - 1, j = right; 
			Object o = a[right], temp;

			// Εύρεση θέσεως i όπου θα τοποθετηθεί το στοιχείο a[right]
			while (true) {
				while (c.less(a[++i], o)) ; // ανεβαίνει ο δείκτης i
				while (c.less(o, a[--j])) {
					if (j == left) { break; } //κατεβαίνει ο δείκτης j
				}
				if (i >= j) { break; } // οι δείκτες συναντήθηκαν ή πέρασαν στην άλλη πλευρά
				temp = a[i]; //ανταλλαγή αντικειμένων θέσεων i,j
				a[i] = a[j];
				a[j] = temp;
			}

			temp = a[i]; // τοποθέτηση στοιχείου a[right]στην θέση i
			a[i] = a[right];
			a[right] = temp;
    
			if (i - left > right - i) {
				Mystack.insertFirst(i - 1);
				Mystack.insertFirst(left);
				Mystack.insertFirst(right);
				Mystack.insertFirst(i + 1);
			} else {
				Mystack.insertFirst(right);
				Mystack.insertFirst(i + 1);
				Mystack.insertFirst(i - 1);
				Mystack.insertFirst(left);
			}
		}
	}

	// Ταξινομεί τον πίνακα μεταξύ του left και right χρησιμοποιώντας την κλάση c για τις συγκρίσεις.
	// Χρησιμοποιείται ένας βοηθητικός πίνακας b για την συγχώνευση
	public void mergeSort(Object a[], int left, int right, Object b[]) {
		int l, r;
		int middle = (right + left) / 2;

		// Divide phase
		if (left < middle) {
			mergeSort(a, left, middle, b);
		}

		if (middle + 1 < right) {
			mergeSort(a, middle+1, right,b);
		}
    
		// Conquer phase -> Merge
		for (l = left; l <middle + 1; l++) {
			b[l] = a[l];
		}

		l = left;

		for (r = middle; r < right; r++) {
			b[right + middle - r] = a[r+1];
		}

		for (int k = left; k <= right; k++) {
			if (c.less(b[r], b[l])) {
				a[k] = b[r--];
			} else {
				a[k] = b[l++];
			}
		}
	}

	public Object [] get_array() { return a; }
	public void print_array() {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
}

