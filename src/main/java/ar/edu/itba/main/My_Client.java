package ar.edu.itba.main;

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
import ar.edu.itba.model.MovieLoader;
import ar.edu.itba.util.QueryAnalyzer;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;



public class My_Client
{
	private static final String MAP_NAME = "movies2015";
	private static final String NAME="51277-51141";
	private static final String SMALL = "res/imdb-40.json";
	private static final String MEDIUM = "res/imdb-200.json";
	private static final String LARGE = "res/imdb-20K.json";

	public static void main(String[] args) 
	{
		QueryAnalyzer qa = new QueryAnalyzer(args);
		qa.run();
		try {
			client();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Unexpected error while running client");
		}    

		System.exit(0);

	}
	private static void client() throws InterruptedException, ExecutionException{

		//String name= System.getProperty("name");
		String pass= System.getProperty("pass");
		if (pass == null)
		{
			pass="dev-pass";
		}
		System.out.println(String.format("Connecting with cluster dev-name [%s]", NAME));

		ClientConfig ccfg= new ClientConfig();
		ccfg.getGroupConfig().setName(NAME).setPassword(pass);

		// no hay descubrimiento automatico, 
		// pero si no decimos nada intentar· usar LOCALHOST
		String addresses= "127.0.0.1";
		//String addresses= System.getProperty("addresses");
		if (addresses != null)
		{	
			String[] arrayAddresses= addresses.split("[,;]");
			ClientNetworkConfig net= new ClientNetworkConfig();
			net.addAddress(arrayAddresses);
			ccfg.setNetworkConfig(net);
		}
		HazelcastInstance client = HazelcastClient.newHazelcastClient(ccfg);


		System.out.println(client.getCluster() );

		// Preparar la particion de datos y distribuirla en el cluster a traves del IMap
		IMap<String, Movie> myMap = client.getMap(MAP_NAME);
		try 
		{
			MovieLoader.loadMovies(SMALL,myMap);
		} 
		catch (Exception e) 
		{
			System.out.println("error");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// Ahora el JobTracker y los Workers!
		JobTracker tracker = client.getJobTracker("default");

		// Ahora el Job desde los pares(key, Value) que precisa MapReduce
		KeyValueSource<String, Movie> source = KeyValueSource.fromMap(myMap);
		Job<String, Movie> job = tracker.newJob(source);

		// Orquestacion de Jobs y lanzamiento
		ICompletableFuture<List<ActorsQuerie>> future = job 
				.mapper(new MapperForActorsQuerie()) 
				.reducer(new ReducerForActorsQuerie())
				.submit(new CollatorForActorsQuerie(5)); 

		//		ICompletableFuture<Map<String, Set<String>>> future = job 
		//				.mapper(new MapperForActorsQuerie()) 
		//				.reducer(new ReducerForActorsQuerie())
		//				.submit(); 

		// Tomar resultado e Imprimirlo
		//		Map<String, Set<String>> rta = future.get();
		//		 for (Entry<String, Set<String>> e : rta.entrySet()) 
		//		    {
		//		    	System.out.println(String.format("Director %s => Actores %s",
		//		    			e.getKey(), e.getValue() ));
		//			}
		//		 

		List<ActorsQuerie> rta = future.get();
		for (ActorsQuerie actor : rta) 
		{
			System.out.println("Actor: " + actor.getName() +" => Votos: "+ actor.getVotes());
		}

	}
}
