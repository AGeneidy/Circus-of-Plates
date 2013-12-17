import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SerializeDemo {
	private View a;
	private static SerializeDemo b;

	private SerializeDemo(View Boda) {
		// TODO Auto-generated constructor stub
		a = Boda;
	}

	public static SerializeDemo getSerializeDemo(View Boda) {
		if (b != null)
			return b;
		else {
			return b = new SerializeDemo(Boda);
		}
	}

	public void save() {
		try {
			String name = JOptionPane.showInputDialog(null,
					"Enter your full name: ");
			File file = new File("E:\\" + name + ".ser");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			int i = a.Players.size();
			out.writeObject(i);
			for (int k = 0; k < i; k++)
				out.writeObject(a.Players.get(k));
//			out.writeObject(PlatePool.getPlatePool());
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public void load() {
		try {
			String name = JOptionPane.showInputDialog(null,
					"Enter your full name: ");
			File file = new File("E:\\" + name + ".ser");
			if (!file.exists()) {
				return;
			}
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			a.Players = new ArrayList<Player>();
			try {
				int i = (int) in.readObject();
				for (int k = 0; k < i; k++)
					a.Players.add((Player) in.readObject());
//				PlatePool.setPool((PlatePool) in.readObject());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}