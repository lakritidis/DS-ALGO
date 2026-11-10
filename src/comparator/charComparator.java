package comparator;

public class charComparator {
	protected String Sigma;	
	protected int radix;

	public charComparator() {
		Sigma = "0123456789";
		radix = 10;
	} 

	public charComparator(String s, int r){
		Sigma = s;
		radix = r;
	}

	int getDigit(char c) {
		return (Sigma.indexOf(c));
	}

	int getDigit(String s, int position) {
		return Sigma.indexOf(s.charAt(position));
	}

	char getLetter(int i) {
		return Sigma.charAt(i);
	}

	int getRadix() {
		return radix;
	}

}
