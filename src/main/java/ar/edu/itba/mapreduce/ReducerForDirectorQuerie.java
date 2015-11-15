package ar.edu.itba.mapreduce;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

public class ReducerForDirectorQuerie implements ReducerFactory<String, String, Set<String>>{

	@Override
	public Reducer<String, Set<String>> newReducer(final String director) {
		return new Reducer<String, Set<String>>() {
			
			private Map<String, Integer> actorsAppearence;
			
			@Override
			public void beginReduce() {
				actorsAppearence = new HashMap<>();
			}

			@Override
			public void reduce(final String actor) {
				if (!actorsAppearence.containsKey(actor)) {
					actorsAppearence.put(actor, 0);
				}
				int appearence = actorsAppearence.get(actor);
				actorsAppearence.put(actor, appearence + 1);
			}

			@Override
			public Set<String> finalizeReduce() {
				final Set<String> actors = new HashSet<>();
				int max = 0;
				for(final Entry<String, Integer> entry : actorsAppearence.entrySet()) {
					if (entry.getValue() > 0) {
						if (entry.getValue() > max) {
							max = entry.getValue();
							actors.clear();
							actors.add(entry.getKey());
						} else if (entry.getValue() == max) {
							actors.add(entry.getKey());
						}
					}
				}
				return actors;
			}
		};
	}

}
