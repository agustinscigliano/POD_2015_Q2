package ar.edu.itba.POD_2015_Q2;

import java.io.IOException;

import junit.framework.TestCase;
import ar.edu.itba.model.Movie;
import ar.edu.itba.model.MovieLoader;

public class TestMovies extends TestCase {
	public void testMovie() 		
	{	
		try {
			Movie[] movie =  MovieLoader.loadMovies("res/imdb-20k.json");
			System.out.println(movie.length);
			System.out.println(movie[0]);
			for (Movie movie2 : movie) {
				System.out.println(movie2.getImdbVotes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
