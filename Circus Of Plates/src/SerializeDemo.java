import java.io.*;
import java.util.ArrayList;

import objectGenerator.Player;

public class SerializeDemo {
	Boda a;

	public SerializeDemo(Boda Boda) {
		// TODO Auto-generated constructor stub
		a = Boda;
	}

	public void save(String name) {
		try {
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
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in /tmp/employee.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public void load(String name) {
		try {
			File file = new File("E:\\" + name + ".ser");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			a.Players = new ArrayList<Player>();
			try {
				int i = (int) in.readObject();
				for (int k = 0; k < i; k++)
					a.Players.add((Player) in.readObject());
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