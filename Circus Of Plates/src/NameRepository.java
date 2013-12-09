import java.util.ArrayList;

public class NameRepository implements Container {
	public ArrayList<Shape> names = Pool.getUsed();

	@Override
	public Iterator getIterator() {
		return new NameIterator();
	}

	private class NameIterator implements Iterator {
		int index;

		@Override
		public boolean hasNext() {
			if (index < names.size()) {
				return true;
			}
			return false;
		}

		@Override
		public Shape next() {
			if (this.hasNext()) {
				return names.get(index++);
			}
			return null;
		}
	}
}