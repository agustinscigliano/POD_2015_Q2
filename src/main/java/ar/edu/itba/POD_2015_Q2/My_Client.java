package ar.edu.itba.POD_2015_Q2;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import ar.edu.itba.model.MapperForYearQuerie;
import ar.edu.itba.model.Movie;
import ar.edu.itba.model.MovieLoader;
import ar.edu.itba.model.ReducerForYearQuerie;
import ar.edu.itba.model.YearQuerie;

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



	public static void main(String[] args) throws InterruptedException, ExecutionException 
	{
		String name="51277-51141";
		//String name= System.getProperty("name");
		String pass= System.getProperty("pass");
		if (pass == null)
		{
			pass="dev-pass";
		}
		System.out.println(String.format("Connecting with cluster dev-name [%s]", name));
 
		ClientConfig ccfg= new ClientConfig();
		ccfg.getGroupConfig().setName(name).setPassword(pass);
		
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
	  
	 		

	 
	    // Preparar la particion de datos y distribuirla en el cluster a travÈs del IMap
		IMap<String, Movie> myMap = client.getMap(MAP_NAME);
		try 
		{
			MovieLoader.loadMovies("res/imdb-200.json",myMap);
		} 
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}

	    
	 	// Ahora el JobTracker y los Workers!
	    JobTracker tracker = client.getJobTracker("default");
	
	    // Ahora el Job desde los pares(key, Value) que precisa MapReduce
	    KeyValueSource<String, Movie> source = KeyValueSource.fromMap(myMap);
	    Job<String, Movie> job = tracker.newJob(source);
	
	    // Orquestacion de Jobs y lanzamiento
	    ICompletableFuture<Map<Integer, YearQuerie>> future = job 
	            .mapper(new MapperForYearQuerie()) 
	            .reducer(new ReducerForYearQuerie())
	            .submit(); 
	    
	    // Tomar resultado e Imprimirlo
	    Map<Integer, YearQuerie> rta = future.get();
	
	    
	    
	    System.exit(0);

	}
}
