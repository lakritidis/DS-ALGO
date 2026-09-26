package tutorial;

public class MybinarySearch {
	static int nofcomparisons = 0;

	static int binarySearch(Object a[], Object o, comparator c) { 
		int left = 0;
		int right = a.length - 1;

		while (right >= left) {
			int middle = (left + right) / 2;
			nofcomparisons++;
			if (c.equal(o, a[middle])) {
				return middle;
			}

			if (c.less(o, a[middle])) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		return -1; 		
	}
 
	static int binarySearchRecursive(Object a[], int left, int right, Object o, comparator c) {
		int middle = (left + right) / 2;
		System.out.println(left + "-" + middle + "-" + right + " a[middle]:" + a[middle]);

		if (right < left) {
			return -1;
		}
		if (c.equal(o, a[middle])) {
			return middle;
		}

		if (c.less(o, a[middle])) {
			return binarySearchRecursive(a, left, middle - 1, o, c);
		} else {
			return binarySearchRecursive(a, middle + 1, right, o, c);
		}
	}

	static class comparator {
		boolean less( Object i, Object j) {
			return ( ((Integer) i).intValue() < ((Integer) j).intValue());
		}

		boolean equal(Object i, Object j) {
			return (((Integer) i).intValue() == ((Integer) j).intValue());
		}
	}
}