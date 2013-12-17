

public class FactoryProducer {
	
	public static AbstractFactory getFactory(String choise){
		if(choise.equalsIgnoreCase("PLATE"))
			return PlateFactory.getPlateFactory();
		else if(choise.equalsIgnoreCase("PLAYER"))
			return PlayerFactory.getPlayerFactory();
		else if(choise.equalsIgnoreCase("BUTTON"))
			return ButtonFactory.getButtonFactory();
		else 
			return null;
	}

}
