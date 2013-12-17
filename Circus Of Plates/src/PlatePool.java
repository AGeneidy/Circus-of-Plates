
import java.util.ArrayList;

public class PlatePool implements Container, java.io.Serializable {
	private static PlatePool pool;
	private ArrayList<Plate> inUse;
	private ArrayList<Plate> available;
	AbstractFactory plateFactory;

	private PlatePool() {
		plateFactory = FactoryProducer.getFactory("PLATE");
//		genreate();
	}

	protected void genreate() {
		// TODO Auto-generated method stub
		Plate q;
		inUse = new ArrayList<Plate>();
		available = new ArrayList<Plate>();
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

	public static void setPool(PlatePool p) {
		pool = p;
	}

	public void getNewSape() {
		// TODO Auto-generated method stub
		pool.getNewSape();
		
	}

}
