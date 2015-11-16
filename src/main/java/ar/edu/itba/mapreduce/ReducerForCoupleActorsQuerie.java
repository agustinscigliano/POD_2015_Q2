package ar.edu.itba.mapreduce;

import java.util.HashSet;
import java.util.Set;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

public class ReducerForCoupleActorsQuerie implements ReducerFactory<Set<String>, String, Set<String>> 
{
    public Reducer<String, Set<String>> newReducer(final Set<String> couple) 
    {
    	return new Reducer<String, Set<String>>()
	    {
        	private Set<String> movies;
	        
	        public void beginReduce()  
	        {
	            movies = new HashSet<String>();
	        }
	
	        public void reduce(final String movie) 
	        {
	        	movies.add(movie);
	        }
	
	        public Set<String> finalizeReduce() 
	        {
	        	return movies;
	        }
	    };
	}
}
