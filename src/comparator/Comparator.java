package comparator;

public class Comparator {
	public boolean less(Object i, Object j) {
		return ( ((Integer) i).intValue() < ((Integer) j).intValue() );
	}

	public boolean equal(Object i, Object j ) {
		return ( ((Integer) i).intValue() == ((Integer) j).intValue() );
	}
}

