package lists;

public class dynamicArray {
	private Object[] A;
		
	public dynamicArray(int size) {
		A = new Object[size];
	}

	public void set(int i, Object o) {
		if (i < A.length) {
			A[i] = o;
		} else {
			Object[] temp = A;
			A = new Object[A.length];
			for (int j = 0; i < temp.length; i++) {
				A[j] = temp[j];
			}
			A[i] = o;
		}
	}

	public Object get(int i) {
		if (i > A.length) {
			System.out.println("Out of bounds");
			return null;
		}
		return A[i];
	}

	public int size() {
		return A.length;
	}
}