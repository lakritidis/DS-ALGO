package items;

public class RedBlackItem extends Item {
	private boolean color;

	protected RedBlackItem(Object k, Object i, boolean c) {
		super(k, i);
		color = c;
	}

	public boolean getColor() {
		return color;
	}

	public void setColor(boolean c) {
		color = c;
	}
}