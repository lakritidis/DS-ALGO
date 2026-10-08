package Dictionaries;

public class Item {
	private Object key;
	private Object info;
	
	public Item(Object k, Object i) {
		key = k;
		info = i;
	}

	public Object getKey() {
		return key;
	}

	public Object getInfo() {
		return info;
	}

	public void setKey(Object k) {
		key = k;
	}

	public void setInfo(Object i) {
		info = i;
	}
}