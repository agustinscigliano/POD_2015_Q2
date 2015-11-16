package ar.edu.itba.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.hazelcast.core.IMap;

public class MovieLoader {
		
	public static void loadMovies(final String jsonPath, final IMap <String, Movie> movies) throws IOException{
		Movie[] movieArray = loadMovies(jsonPath);
		for (Movie currentMovie : movieArray) {
			if(currentMovie.getType().equals("movie")){
				String key = currentMovie.getTitle()+currentMovie.getYear();
				movies.put(key, currentMovie);
			}
		}
	}
	public static Movie[] loadMovies(final String jsonPath) throws IOException{
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(
				new FileReader(jsonPath));

		//convert the json string back to object
		Movie[] movieArray = gson.fromJson(br, Movie[].class);
		return movieArray;
	}
}
