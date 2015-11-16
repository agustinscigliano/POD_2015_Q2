package ar.edu.itba.util;

import java.util.HashMap;
import java.util.Scanner;
/**
 * This class parses command line arguments settings into key value pairs.
 * 
 * @author la catedra
 *
 */
public class Analyzer
{
	private HashMap<String, Object> p= new HashMap<String, Object>();

	/**
	 * a command line argument key in UPPERCASE
	 * @param key
	 * @return assocaited object
	 */
	public Object get(String key)
	{
		return p.get(key);
	}

	public void dump()
	{
		System.out.println("Using: ");
		for (String key : p.keySet()) 
		{
			System.out.println(String.format("%s=%s", key, p.get(key)));
		}
	}

	public Analyzer(String[] args)
	{

		for(int i= 0; i < args.length; i++) 
		{
			String parameter= args[i];
			Scanner scanner = new Scanner(parameter);
			scanner.useDelimiter("=");
			String vble = scanner.next();
			if (!scanner.hasNext())
			{
				System.err.println(String.format("Parameter %s is incorrect", vble));
				continue;
			}
			String value = scanner.next();
			p.put(vble.toUpperCase(), value);
			scanner.close();

		}
	}
}

