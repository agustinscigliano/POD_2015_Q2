package ar.edu.itba.model;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

public class MovieDataSerializableFactory implements DataSerializableFactory{

	public static final int FACTORY_ID = 1;

	public static final int MOVIE_TYPE = 1;

	@Override
	public IdentifiedDataSerializable create(int typeId) {
		if ( typeId == MOVIE_TYPE ) { 
			//return new Movie();
		} else {
			System.out.println("error on create");
			 
		}
		return null;
	}
}
