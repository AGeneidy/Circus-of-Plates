package objectGenerator;

public class FactoryProducer {
	
	public static AbstractFactory getFactory(String choise){
		if(choise.equalsIgnoreCase("PLATE"))
			return new PlateFactory();
		else if(choise.equalsIgnoreCase("PLAYER"))
			return new PlayerFactory();
		else 
			return null;
	}

}
