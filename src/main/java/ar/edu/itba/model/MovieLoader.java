package ar.edu.itba.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.hazelcast.core.IMap;

public class MovieLoader {
		
	public static void loadMovies(final String jsonPath, final IMap <String, Movie> movies) throws IOException{
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(
				new FileReader(jsonPath));

		//convert the json string back to object
		Movie[] movieArray = gson.fromJson(br, Movie[].class);
		for (Movie currentMovie : movieArray) {
			movies.put("key", currentMovie);
		}
		System.out.println(movieArray[0]);//TODO: remove
	}
}
