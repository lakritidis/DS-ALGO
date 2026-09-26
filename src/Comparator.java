public class Comparator {
	boolean less(Object i, Object j) {
		return ( ((Integer) i).intValue() < ((Integer) j).intValue() );
	}

	boolean equal(Object i, Object j ) {
		return ( ((Integer) i).intValue() == ((Integer) j).intValue() );
	}
}

