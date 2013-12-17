

import java.util.ArrayList;

public class PlatePool implements Container {
	private static PlatePool pool;
	private ArrayList<Plate> inUse;
	private ArrayList<Plate> available;
	AbstractFactory plateFactory;

	private PlatePool() {
		inUse = new ArrayList<Plate>();
		available = new ArrayList<Plate>();
		plateFactory = FactoryProducer.getFactory("PLATE");
		genreate();
	}

	private void genreate() {
		// TODO Auto-generated method stub
		Plate q;
		for (int i = 0; i < 1000; i++) {
			q = plateFactory.getRandomPlate();
			q.setID(i);
			available.add(q);

		}
	}

	public Plate getPlate() {
		// TODO Auto-generated method stub
		Plate tmpPlate;
		if (available.isEmpty()) {
			tmpPlate = plateFactory.getRandomPlate();
			tmpPlate.setID(inUse.size());
			inUse.add(tmpPlate);

		} else {
			tmpPlate = available.get(0);
			inUse.add(tmpPlate);
			available.remove(0);
		}
		return tmpPlate;
	}

	public void releasePlate(Plate plate) {
		// TODO Auto-generated method stub
		inUse.remove(plate);
		available.add(plate);
	}

	public static PlatePool getPlatePool() {
		// TODO Auto-generated method stub
		if (pool == null)
			return (pool = new PlatePool());
		else
			return pool;
	}

	@Override
	public ArrayList<Plate> getIterator() {
		// TODO Auto-generated method stub
		return inUse;
	}

}
