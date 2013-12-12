package objectGenerator;

public class FactoryProducer {
	
	public static AbstractFactory getFactory(String choise){
		if(choise.equalsIgnoreCase("PLATE"))
			return PlateFactory.getPlateFactory();
		else if(choise.equalsIgnoreCase("PLAYER"))
			return PlayerFactory.getPlayerFactory();
		else 
			return null;
	}

}