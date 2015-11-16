package ar.edu.itba.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import ar.edu.itba.mapreduce.ActorsQuerie;
import ar.edu.itba.mapreduce.CollatorForActorsQuerie;
import ar.edu.itba.mapreduce.MapperForActorsQuerie;
import ar.edu.itba.mapreduce.ReducerForActorsQuerie;
import ar.edu.itba.model.Movie;

import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.mapreduce.Job;

public class QueryAnalyzer {
	private Analyzer analyzer;
	private Job<String, Movie> job;
	public QueryAnalyzer(String[] args) {
		analyzer = new Analyzer(args);		
	}
	public void run(Job<String, Movie> job) throws InterruptedException, ExecutionException{
		this.job=job;
		try{
			int qNumber = Integer.valueOf((String)analyzer.get("query"));
			switch(qNumber){
			//				1. Los N​actores (de películas) más populares (popularidad se da por la cantidad de
			//						votos que recibieron en IMDB). Donde N​lo provee el usuario.
			case 1:

				int N = Integer.valueOf((String)analyzer.get("N"));
				System.out.println(N);
				runJob1(N);
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
			System.out.println("no params running job 1");
			runJob1(5);
		}
	}
	private void printHelp(){
		System.out.println("wrong command, help:");		
	}
	private void runJob1(int N) throws InterruptedException, ExecutionException{
		ICompletableFuture<List<ActorsQuerie>> future = job 
				.mapper(new MapperForActorsQuerie()) 
				.reducer(new ReducerForActorsQuerie())
				.submit(new CollatorForActorsQuerie(N)); 
		List<ActorsQuerie> rta = future.get();
		for (ActorsQuerie actor : rta) 
		{
			System.out.println("Actor: " + actor.getName() +" => Votos: "+ actor.getVotes());
		}
	}
	private void runJob2(){
//		ICompletableFuture<Map<String, Set<String>>> future = job 
//				.mapper(new MapperForActorsQuerie()) 
//				.reducer(new ReducerForActorsQuerie())
//				.submit(); 
//
//		Map<String, Set<String>> rta = future.get();
//		for (Entry<String, Set<String>> e : rta.entrySet()) 
//		{
//			System.out.println(String.format("Director %s => Actores %s",
//					e.getKey(), e.getValue() ));
//		}

	}

}
