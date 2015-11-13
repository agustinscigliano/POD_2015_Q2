package ar.edu.itba.util;

public class QueryAnalyzer {
	private Analyzer analyzer;
	public QueryAnalyzer(String[] args) {
		analyzer = new Analyzer(args);		
	}
	public void run(){
		try{
			int qNumber = Integer.valueOf((String)analyzer.get("query"));
			switch(qNumber){
			//				1. Los N​actores (de películas) más populares (popularidad se da por la cantidad de
			//						votos que recibieron en IMDB). Donde N​lo provee el usuario.
			case 1:

				int N = Integer.valueOf((String)analyzer.get("N"));
				System.out.println(N);

				break;
				//				2. Por cada año, mayor al año Tope​, ​las películas más aclamadas por la crítica (todas
				//					las que tienen el valor mayor de Metascore). Donde Tope​lo provee el usuario.
			case 2:
				String tope = (String)analyzer.get("Tope");
				System.out.println(tope);
				break;
				//				3. Las parejas de actores que más veces actuaron juntos y para cada una de ellas
				//				cuáles fueron las películas en las que actuaron.
			case 3:
				
				System.out.println("execute 3");
				break;
				//				4. Por cada director cuál es su actor (o actores) fetiche, o sea los que actuaron en más
				//				películas del director.
			case 4:
				System.out.println("execute 4");
				break;
			}
		}
		catch(Exception e){
			printHelp();
		}
	}
	private void printHelp(){
		System.out.println("wrong command, help:");
	}


}
