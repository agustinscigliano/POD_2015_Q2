package ar.edu.itba.mapreduce;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.itba.model.Movie;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class MapperForCoupleActorsQuerie implements Mapper<String, Movie, Set<String>, String>{

	@Override
	public void map(String keyinput, Movie valueinput, Context<Set<String>, String> context) {
		final List<String> actors = valueinput.getActors();
		for (int i = 0; i < actors.size(); i++) {
			for (int j = i + 1; j < actors.size(); j++) {
				final String actor1 = actors.get(i);
				final String actor2 = actors.get(j);
				final Set<String> couple = new HashSet<>();
				couple.add(actor1);
				couple.add(actor2);
				context.emit(couple, valueinput.getTitle());
			}
		}
		
	}
}
