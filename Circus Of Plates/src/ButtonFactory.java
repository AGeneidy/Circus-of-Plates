

public class ButtonFactory extends AbstractFactory {
	private static ButtonFactory buttonfactory;

	private ButtonFactory(){
	}
	
	protected static ButtonFactory getButtonFactory(){
		if (buttonfactory == null)
			return buttonfactory = new ButtonFactory();
		else
			return buttonfactory;
	}
	
	@Override
	public Plate getRandomPlate() {
		return null;
	}


	@Override
	public Button getButton() {
		return new Button();
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
