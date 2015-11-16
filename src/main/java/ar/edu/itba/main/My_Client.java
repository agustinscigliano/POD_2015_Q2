package ar.edu.itba.main;

import ar.edu.itba.util.QueryAnalyzer;



public class My_Client
{

	public static void main(String[] args) 
	{
		QueryAnalyzer qa = new QueryAnalyzer(args);		
		try {
			qa.run();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Unexpected error while running client");
		}    

		System.exit(0);

	}
	
}
