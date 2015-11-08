package ar.edu.itba.POD_2015_Q2;

import java.util.List;

import junit.framework.TestCase;
import ar.edu.itba.model.Movie;
import ar.edu.itba.model.MovieLoader;

public class TestMovies extends TestCase {
	/**
	 * Test integrity of an object. i.e first field, number of fields
	 */
	public void testMovie()
	{	MovieLoader loader = new MovieLoader("res/imdb-200.json");
		List<Movie> movie = loader.getMovies();
		Movie firstMovie = movie.get(0);
		assertTrue( firstMovie.getTitle().equals("300: Rise of an Empire") );
		assertNotNull(firstMovie.getYear());
		assertNotNull(firstMovie.getRuntime());		
		assertNotNull(firstMovie.getGenre());
		assertNotNull(firstMovie.getDirector());
		assertNotNull(firstMovie.getWriter());
		assertNotNull(firstMovie.getActors());
		assertNotNull(firstMovie.getLanguage());
		assertNotNull(firstMovie.getPoster());
		assertNotNull(firstMovie.getMetascore());
		assertNotNull(firstMovie.getImdbID());
		assertNotNull(firstMovie.getImdbVotes());
		assertNotNull(firstMovie.getImdbID());
		assertNotNull(firstMovie.getType());
		assertNotNull(firstMovie.getTomatoMeter());
		assertNotNull(firstMovie.getTomatoRating());
		assertNotNull(firstMovie.getTomatoReviews());
		assertNotNull(firstMovie.getTomatoFresh());
		assertNotNull(firstMovie.getTomatoRotten());
		assertNotNull(firstMovie.getTomatoConsensus());
		assertNotNull(firstMovie.getTomatoUserMeter());
		assertNotNull(firstMovie.getTomatoUserRating());
		assertNotNull(firstMovie.getTomatoUserReviews());
		assertNotNull(firstMovie.getDVD());
		assertNotNull(firstMovie.getBoxOffice());
		assertNotNull(firstMovie.getProduction());
		assertNotNull(firstMovie.getWebsite());
		assertNotNull(firstMovie.getResponse());
	}
	/**
	 * Test loading all objects
	 */
	public void testQuantity()
	{	MovieLoader loader = new MovieLoader("res/imdb-200.json");
		List<Movie> movie = loader.getMovies();
		Movie firstMovie = movie.get(0);
		assertTrue( firstMovie.getTitle().equals("300: Rise of an Empire") );
	}
}
