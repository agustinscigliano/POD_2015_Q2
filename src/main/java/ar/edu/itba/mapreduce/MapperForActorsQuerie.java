package ar.edu.itba.mapreduce;

import ar.edu.itba.model.Movie;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class MapperForActorsQuerie implements Mapper<String, Movie, String, ActorsQuerie > 
{
    public void map(String keyinput, Movie valueinput, Context<String, ActorsQuerie> context)
    {    
    	
		for(String actor : valueinput.getActors()) {
			context.emit(actor, new ActorsQuerie(actor,valueinput.getImdbVotes()));
		}

    }

}
