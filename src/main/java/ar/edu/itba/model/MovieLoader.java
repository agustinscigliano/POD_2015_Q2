package ar.edu.itba.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

public class MovieLoader {
	private List<Movie> movies;
	private final String jsonPath;
	public MovieLoader(final String jsonPath){
		this.movies = new LinkedList<Movie>();
		this.jsonPath = jsonPath;
	}
	
	public List<Movie> getMovies(){
		if(movies.size()>0){
			return movies;
		}
		try{
			loadMovies();
		}catch(IOException e){
			System.err.println("Error while parsing movies");
			return null;
		}
		return movies;
	}
	
	private void loadMovies() throws IOException{
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(
				new FileReader(jsonPath));

		//convert the json string back to object
		Movie[] movieArray = gson.fromJson(br, Movie[].class);
		for (Movie currentMovie : movieArray) {
			movies.add(currentMovie);
		}
		System.out.println(movieArray[0]);
	}
}
