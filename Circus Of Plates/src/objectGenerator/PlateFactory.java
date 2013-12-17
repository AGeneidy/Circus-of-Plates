package objectGenerator;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class PlateFactory extends AbstractFactory {
	private static PlateFactory plateFactory;
	private ArrayList<Class<?>> PlateChooser;
	private DynamicLinkage link;

	private PlateFactory() {
//		System.out.println(getClass().getClassLoader().getResource("")
//				.toString());
//		link = new DynamicLinkage();
//		PlateChooser = new ArrayList<Class<?>>();
//		String u = getClass().getResource("").toString().replace("%20", " ");
//		try {
//			PlateChooser.add(link.loadClass(new URL(u), "OvalPlate"));
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// PlateChooser.add(link.loadClass(getClass().getResource(""),
//		// "OvalPlate"));
//		System.out.println(PlateChooser.size());
	}

	protected static PlateFactory getPlateFactory() {
		// TODO Auto-generated method stub
		if (plateFactory == null)
			return plateFactory = new PlateFactory();
		else
			return plateFactory;
	}

	@Override
	public Plate getRandomPlate() {
//		int i = new Random().nextInt(500) % PlateChooser.size();
//		try {
//			return (Plate) PlateChooser.get(i).newInstance();
//		} catch (InstantiationException | IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
		// }
		 return new OvalPlate();
	}

	@Override
	public Button getButton() {
		return null;
	}

	@Override
	public Player getPlayerOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayerTwo() {
		// TODO Auto-generated method stub
		return null;
	}
}
