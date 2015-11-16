package ar.edu.itba.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import ar.edu.itba.mapreduce.ActorsQuerie;
import ar.edu.itba.mapreduce.CollatorForActorsQuerie;
import ar.edu.itba.mapreduce.MapperForActorsQuerie;
import ar.edu.itba.mapreduce.MapperForDirectorQuerie;
import ar.edu.itba.mapreduce.MapperForYearQuerie;
import ar.edu.itba.mapreduce.ReducerForActorsQuerie;
import ar.edu.itba.mapreduce.ReducerForDirectorQuerie;
import ar.edu.itba.mapreduce.ReducerForYearQuerie;
import ar.edu.itba.mapreduce.YearQuerie;
import ar.edu.itba.model.Movie;
import ar.edu.itba.model.MovieLoader;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;

public class QueryAnalyzer {
	private static final String MAP_NAME = "movies2015";
	private static final String NAME="51277-51141";
	private static final String SMALL = "res/imdb-40.json";
	private static final String MEDIUM = "res/imdb-200.json";
	private static final String LARGE = "res/imdb-20K.json";
	private Analyzer analyzer;
	private Job<String, Movie> job;
	public QueryAnalyzer(String[] args) {
		analyzer = new Analyzer(args);		
	}
	public void run() throws InterruptedException, ExecutionException{

		try{
			String path = (String)analyzer.get("query");
			path = path != null? path: SMALL;
			this.job = prepareJob(path);
			int qNumber = Integer.valueOf((String)analyzer.get("query"));
			switch(qNumber){
			case 1:
				int N = Integer.valueOf((String)analyzer.get("N"));
				runJob1(N);
				break;
			case 2:
				String tope = (String)analyzer.get("Tope");
				runJob2(tope);
				break;
			case 3:
				System.out.println("execute 3");
				break;
			case 4:
				System.out.println("execute 4");
				break;
			}
		}
		catch(Exception e){
			printHelp();
			System.out.println("no params running job 1");
			printTimestamp("INFO - Start map/reduce");
			runJob4();
			printTimestamp("INFO - End map/reduce");
		}
	}
	private void printHelp(){
		System.out.println("wrong command, help:");		
	}
	private Job<String, Movie> prepareJob(String path) throws InterruptedException, ExecutionException{

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
		// pero si no decimos nada intentarusar LOCALHOST
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
			printTimestamp("INFO - Start reading");
			MovieLoader.loadMovies(path,myMap);
			printTimestamp("INFO - End reading");
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
		return job;

	}

	private void runJob1(int N) throws InterruptedException, ExecutionException{
		ICompletableFuture<List<ActorsQuerie>> future = job 
				.mapper(new MapperForActorsQuerie()) 
				.reducer(new ReducerForActorsQuerie())
				.submit(new CollatorForActorsQuerie(N)); 
		List<ActorsQuerie> rta = future.get();
		for (ActorsQuerie actor : rta) 
		{
			System.out.println(actor);
		}
	}
	private void runJob2(String tope) throws InterruptedException, ExecutionException{
		//		ICompletableFuture<Map<Integer, YearQuerie>> future = job 
		//				.mapper(new MapperForYearQuerie(tope)) 
		//				.reducer(new ReducerForYearQuerie())
		//				.submit(); 
		//		Map<Integer, YearQuerie> rta = future.get();
		//		for (Entry<Integer, YearQuerie> e : rta.entrySet()) {
		//			System.out.println(String.format("Year %d => %s",
		//					e.getKey(), e.getValue() ));
		//		}
	}
	private void runJob4() throws InterruptedException, ExecutionException{
		ICompletableFuture<Map<String, Set<String>>> future = job 
				.mapper(new MapperForDirectorQuerie()) 
				.reducer(new ReducerForDirectorQuerie())
				.submit(); 

		Map<String, Set<String>> rta = future.get();
		for (Entry<String, Set<String>> e : rta.entrySet()) 
		{
			System.out.println(String.format("Director %s => Actores %s",
					e.getKey(), e.getValue() ));
		}

	}

	private void printTimestamp(String moment){
		java.util.Date date= new java.util.Date();
		Timestamp myTimestamp = new Timestamp(date.getTime());
		String S = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSSS").format(myTimestamp);
		System.out.println(S+" "+moment);
	}

}
