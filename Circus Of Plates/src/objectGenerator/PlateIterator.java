package objectGenerator;

import java.util.ArrayList;

public class PlateIterator implements Iterator {
	private int index;

	public void setIndex() {
		this.index = 0;
	}

	private static PlateIterator plateIterator;
	private ArrayList<Plate> iterate;

	private PlateIterator() {
		// TODO Auto-generated constructor stub
		iterate = PlatePool.getPlatePool().getIterator();
		System.out.println("iterator size = "+ iterate.size());

	}

	@Override
	public boolean hasnext() {
		// TODO Auto-generated method stub
		if (iterate.size() > (index))
			return true;
		return false;
	}

	@Override
	public Plate next() {
		// TODO Auto-generated method stub
		if (hasnext())
			return iterate.get(index++);
		return null;
	}

	public static PlateIterator getPlateIterator() {
		if (plateIterator == null){
			plateIterator = new PlateIterator();
		}
		plateIterator.setIndex();
		return plateIterator;
	}

	public int index() {

		return index;
	}
}
