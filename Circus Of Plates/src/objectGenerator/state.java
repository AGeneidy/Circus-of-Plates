package objectGenerator;

public class state implements java.io.Serializable {
	private String state;
	private String type;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public state() {
		// TODO Auto-generated constructor stub
		state = "Not Used";
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
