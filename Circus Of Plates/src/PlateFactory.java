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
		PlateChooser = new ArrayList<Class<?>>();
		System.out.println(this.getClass().getResource("").toString()
				.replaceAll("%20", " "));
		link = new DynamicLinkage();
		try {
			PlateChooser.add(link.CheckClassExist(new URL(this.getClass()
					.getResource("").toString().replaceAll("%20", " ")),
					"OvalPlate"));
			PlateChooser.add(link
					.CheckClassExist(new URL(this.getClass().getResource("")
							.toString().replaceAll("%20", " ")), "Ball"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		int i = new Random().nextInt(500) % PlateChooser.size();
		try {
			return (Plate) PlateChooser.get(i).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
