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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
