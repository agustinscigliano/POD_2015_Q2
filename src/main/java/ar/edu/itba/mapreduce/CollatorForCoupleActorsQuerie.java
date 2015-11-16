package ar.edu.itba.mapreduce;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import com.hazelcast.mapreduce.Collator;

public class CollatorForCoupleActorsQuerie implements Collator<Map.Entry<Set<String>, Set<String>>, List<ActorsQuerie>>{
	
	
	public CollatorForCoupleActorsQuerie() {
	}
	
	@Override
	public List<ActorsQuerie> collate(Iterable<Entry<Set<String>, Set<String>>> values) {
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
