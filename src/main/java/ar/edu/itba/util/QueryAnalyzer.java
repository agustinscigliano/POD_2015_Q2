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
import ar.edu.itba.mapreduce.CollatorForCoupleActorsQuerie;
import ar.edu.itba.mapreduce.MapperForActorsQuerie;
import ar.edu.itba.mapreduce.MapperForCoupleActorsQuerie;
import ar.edu.itba.mapreduce.MapperForDirectorQuerie;
import ar.edu.itba.mapreduce.MapperForYearQuerie;
import ar.edu.itba.mapreduce.ReducerForActorsQuerie;
import ar.edu.itba.mapreduce.ReducerForCoupleActorsQuerie;
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
	private static final String PASS ="dev-pass";
	private static final String ADDRESSES = "127.0.0.1";
	private static final String SMALL = "res/imdb-40.json";
	private static final String MEDIUM = "res/imdb-200.json";
	private static final String LARGE = "res/imdb-20K.json";
	private static final String DEFAULT_FILE = SMALL;
	private Analyzer analyzer;
	private Job<String, Movie> job;
	public QueryAnalyzer(String[] args) {
		analyzer = new Analyzer(args);		
	}
	public void run() throws InterruptedException, ExecutionException{

		try{
			String path = (String)analyzer.get("PATH");
			path = path != null? path: DEFAULT_FILE;
			this.job = prepareJob(path);
			int qNumber = Integer.valueOf((String)analyzer.get("QUERY"));
			printTimestamp("INFO - Start map/reduce");
			switch(qNumber){
			case 1:
				int N = Integer.valueOf((String)analyzer.get("N"));
				System.out.println("PARSER - Running job 1, N = "+N);
				runJob1(N);
				break;
			case 2:
				String topeS = (String)analyzer.get("TOPE");
				Integer tope = Integer.valueOf(topeS);
				System.out.println("PARSER - Running job 2, Tope = "+ tope);
				runJob2(tope);
				break;
			case 3:
				System.out.println("PARSER - Running job 3");
				runJob3();
				break;
			case 4:
				System.out.println("PARSER - Running job 4");
				runJob4();
				break;
			}
			printTimestamp("INFO - End map/reduce");
		}
		catch(Exception e){
			e.printStackTrace();
			printHelp();
		}
	}
	private void printHelp(){
		System.out.println("PARSER - Wrong command, help:");	
		System.out.println("Invocation example");
		System.out.println("java -jar path_tojar query=[1,2,3,4] *  **path=path_to_json addresses=127.0.0.1;10.6.0.72");
		System.out.println("*Aditional Params:");
		System.out.println("for query 1: n=[1,..n]");
		System.out.println("for query 2: Tope=1993");
		
		System.out.println("Path defaults to" + DEFAULT_FILE);
		System.out.println("Name IS " + NAME);
		System.out.println("Pass IS " + PASS);
		System.out.println("Address defaults to " + ADDRESSES);
	}
	private Job<String, Movie> prepareJob(String path) throws InterruptedException, ExecutionException{

		String name= System.getProperty("name");
		name = name!=null?name:NAME;
		String pass= System.getProperty("pass");
		pass = pass!=null?pass:PASS;
		System.out.println(String.format("Connecting with cluster dev-name [%s]", NAME));

		ClientConfig ccfg= new ClientConfig();
		ccfg.getGroupConfig().setName(name).setPassword(pass);

		// no hay descubrimiento automatico, 
		// pero si no decimos nada intentarusar LOCALHOST
		String addresses= (String)analyzer.get("ADDRESSES");//System.getProperty("addresses");
		addresses = addresses != null?addresses:ADDRESSES;
		
		String[] arrayAddresses= addresses.split("[,;]");
		for (String address : arrayAddresses) {
			System.out.println("INFO - addresses: "+ address);
		}
		
		ClientNetworkConfig net= new ClientNetworkConfig();
		net.addAddress(arrayAddresses);
		ccfg.setNetworkConfig(net);
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
	private void runJob2(int tope) throws InterruptedException, ExecutionException{
				ICompletableFuture<Map<Integer, YearQuerie>> future = job 
						.mapper(new MapperForYearQuerie(tope)) 
						.reducer(new ReducerForYearQuerie())
						.submit(); 
				Map<Integer, YearQuerie> rta = future.get();
				for (Entry<Integer, YearQuerie> e : rta.entrySet()) {
					System.out.println(String.format("Year %d => %s",
							e.getKey(), e.getValue() ));
				}
	}
	private void runJob3() throws InterruptedException, ExecutionException{
		ICompletableFuture<Set<Entry<Set<String>, Set<String>>>> future = job 
				.mapper(new MapperForCoupleActorsQuerie()) 
				.reducer(new ReducerForCoupleActorsQuerie())
				.submit(new CollatorForCoupleActorsQuerie()); 
	
		Set<Entry<Set<String>, Set<String>>> rta = future.get();
		for (Entry<Set<String>, Set<String>> e : rta) 
		{
			System.out.println(String.format("Actores: %s => Peliculas %s",
					e.getKey(), e.getValue() ));
		}
	
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
