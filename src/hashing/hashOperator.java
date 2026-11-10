package hashing;

// Ενδεικτικός hashOperator:
public class hashOperator {
	private int m;

	// Ο διαιρέτης: προτείνεται να είναι πρώτος αριθμός, ώστε να μειώνεται η πιθανότητα συγκρούσεων.
	public hashOperator (int divisor) {
		m = divisor;
	}

	// H συνάρτηση κατακερματισμού
	public int hashTo(Object k){
		return ((Integer)k).intValue() % m; // Επιστρέφει το ακέραιο υπόλοιπο
	}
} 