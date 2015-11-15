package ar.edu.itba.mapreduce;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import com.hazelcast.mapreduce.Collator;

public class CollatorForActorsQuerie implements Collator<Map.Entry<String, ActorsQuerie>, List<ActorsQuerie>>{
	
	private final int top;
	
	public CollatorForActorsQuerie(final int N) {
		top = N;
	}
	
	@Override
	public List<ActorsQuerie> collate(Iterable<Entry<String, ActorsQuerie>> values) {
		final Set<ActorsQuerie> sortedActors = new TreeSet<>();
		for (final Entry<String, ActorsQuerie> entry : values) {
			final ActorsQuerie actor = entry.getValue();
			sortedActors.add(actor);
		}
		final List<ActorsQuerie> list = new ArrayList<>();
		int i = 0;
		for (final ActorsQuerie actor : sortedActors) {
			if (i == top) {
				return list;
			}
			list.add(actor);
			i++;
		}
		return list;
	}

}
