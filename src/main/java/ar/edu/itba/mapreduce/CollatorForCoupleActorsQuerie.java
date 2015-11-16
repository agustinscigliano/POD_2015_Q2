package ar.edu.itba.mapreduce;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.hazelcast.mapreduce.Collator;

public class CollatorForCoupleActorsQuerie implements Collator<Map.Entry<Set<String>, Set<String>>, Set<Entry<Set<String>, Set<String>>>>{
	
	
	public CollatorForCoupleActorsQuerie() {
	}
	
	@Override
	public Set<Entry<Set<String>, Set<String>>> collate(Iterable<Entry<Set<String>, Set<String>>> values) {
		int max = 0;
		final Set<Entry<Set<String>, Set<String>>> mostMoviesCouples = new HashSet<>();
		for (final Entry<Set<String>, Set<String>> entry : values) {
			int numberMovies = entry.getValue().size();
			if(numberMovies > max) {
				mostMoviesCouples.clear();
				mostMoviesCouples.add(entry);
			} else if (numberMovies == max) {
				mostMoviesCouples.add(entry);
			}
		}
		return mostMoviesCouples;
	}


}
