package ar.edu.itba.mapreduce;

import ar.edu.itba.model.Movie;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class MapperForDirectorQuerie implements Mapper<String, Movie, String, String> 
{
    public void map(String keyinput, Movie valueinput, Context<String, String> context)
    {    
    	
          for (String actor  : valueinput.getActors()) {
        	  context.emit(valueinput.getDirector(), actor );
		}
      } 

}
