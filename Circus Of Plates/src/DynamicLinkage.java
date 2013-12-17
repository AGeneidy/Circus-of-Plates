

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class DynamicLinkage {
	ArrayList<String> nameOfLoadedClasses;
	private static DynamicLinkage a ;
	public DynamicLinkage() {
		// TODO Auto-generated constructor stub
		nameOfLoadedClasses = new ArrayList<String>();
	}

	public Class<?> loadClass(URL name, String nameOfClass) {
		System.out.println(name.toString());
		URL[] my = { name };
		URLClassLoader classloader = new URLClassLoader(my);
		for (String s : nameOfLoadedClasses)
			if (nameOfClass.equals(s)) {
				return null;
			}

		try {
			System.out.println(name);
			System.out.println(nameOfClass);
			Class myClass = classloader.loadClass(nameOfClass);
			if (nameOfLoadedClasses.contains(nameOfClass)) {
				System.out.println("u already loaded such a class");
				return null;
			}
			nameOfLoadedClasses.add(nameOfClass);
			System.out.println(nameOfClass);
			return myClass;
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Cant load from this path " + name);
			// JOptionPane.showMessageDialog("u cannot load class " + str,wa);
		}
		return null;
	}

	public boolean CheckClassExist(URL name, String nameOfClass) {
		if (nameOfLoadedClasses.contains(nameOfClass))
			return false;
		URL[] my = { name };
		URLClassLoader classloader = new URLClassLoader(my);
		try {
			Class myClass = classloader.loadClass(nameOfClass);
			// nameOfLoadedClasses.add(nameOfClass);
			return true;
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Cant load from this path " + name);
			// JOptionPane.showMessageDialog("u cannot load class " + str,wa);
			return false;
		}

	}

}
