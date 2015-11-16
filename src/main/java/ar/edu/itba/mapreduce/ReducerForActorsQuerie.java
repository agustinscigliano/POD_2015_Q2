package ar.edu.itba.mapreduce;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

public class ReducerForActorsQuerie implements ReducerFactory<String, ActorsQuerie, ActorsQuerie> {

	@Override
	public Reducer<ActorsQuerie, ActorsQuerie> newReducer(final String actor) {

		return new Reducer<ActorsQuerie, ActorsQuerie>() {

			private ActorsQuerie actorSum;

			@Override
				public void beginReduce() {
				actorSum = new ActorsQuerie(actor,0);
			}

			@Override
			public void reduce(final ActorsQuerie incomingActor) {
				actorSum.addVotes(incomingActor.getVotes());
			}

			@Override
			public ActorsQuerie finalizeReduce() {
				return actorSum;
			}
		};
	}


}
