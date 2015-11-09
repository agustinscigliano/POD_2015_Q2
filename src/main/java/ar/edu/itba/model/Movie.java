package ar.edu.itba.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *Si el campo director tiene más de 1 director no es necesario separarlos, 
 *tomenlo como un director único. O sea si una pelicula tiene como "Director": 
 *"Kevin Smith, Woody Allen" el director a tomar "Kevin Smith, Woody Allen" que 
 *obviamente es diferente del director  "Kevin Smith" y del director  "Woody Allen"
 */

public class Movie {
	private final String Title = null;
	private final Integer Year = null;
	private final String rated = null;
	private final String released = null;
	private final String Runtime = null;
	private final String Genre = null;
	private final String Director = null;
	private final String Writer = null;
	private final String Actors = null;
	private final String Plots = null;
	private final String Language = null;
	private final String Poster = null;
	private final Long Metascore = null;
	private final String imdbRating = null;
	private final String imdbVotes = null;
	private final String imdbID = null;
	private final String Type = null;
	private final String tomatoMeter = null;
	private final String tomatoImage = null;
	private final String tomatoRating = null;
	private final String tomatoReviews = null;
	private final String tomatoFresh = null;
	private final String tomatoRotten = null;
	private final String tomatoConsensus = null;
	private final String tomatoUserMeter = null;
	private final String tomatoUserRating = null;
	private final String tomatoUserReviews = null;
	private final String DVD = null;
	private final String BoxOffice = null;
	private final String Production = null;
	private final String Website = null;
	private final String Response = null;
	
	public String getTitle() {
		return Title;
	}

	public Integer getYear() {
		return Year;
	}

	public String getRated() {
		return rated;
	}

	public String getReleased() {
		return released;
	}

	public String getRuntime() {
		return Runtime;
	}

	public String getGenre() {
		return Genre;
	}

	public String getDirector() {
		return Director;
	}

	public String getWriter() {
		return Writer;
	}

	public String getActors() {
		return Actors;
	}

	public String getPlots() {
		return Plots;
	}

	public String getLanguage() {
		return Language;
	}

	public String getPoster() {
		return Poster;
	}

	public Long getMetascore() {
		return Metascore;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public String getImdbVotes() {
		return imdbVotes;
	}

	public String getImdbID() {
		return imdbID;
	}

	public String getType() {
		return Type;
	}

	public String getTomatoMeter() {
		return tomatoMeter;
	}

	public String getTomatoImage() {
		return tomatoImage;
	}

	public String getTomatoRating() {
		return tomatoRating;
	}

	public String getTomatoReviews() {
		return tomatoReviews;
	}

	public String getTomatoFresh() {
		return tomatoFresh;
	}

	public String getTomatoRotten() {
		return tomatoRotten;
	}

	public String getTomatoConsensus() {
		return tomatoConsensus;
	}

	public String getTomatoUserMeter() {
		return tomatoUserMeter;
	}

	public String getTomatoUserRating() {
		return tomatoUserRating;
	}

	public String getTomatoUserReviews() {
		return tomatoUserReviews;
	}

	public String getDVD() {
		return DVD;
	}

	public String getBoxOffice() {
		return BoxOffice;
	}

	public String getProduction() {
		return Production;
	}

	public String getWebsite() {
		return Website;
	}

	public String getResponse() {
		return Response;
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);
		return json;
	}
}
//{"Title":"300: Rise of an Empire",
//"Year":"2014",
//"Rated":"R","Released":"07 Mar 2014","Runtime":"102 min",
//"Genre":"Action, Drama, Fantasy",
//"Director":"Noam Murro",
//"Writer":"Zack Snyder (screenplay), Kurt Johnstad (screenplay), Frank Miller (graphic novel \"Xerxes\")",
//"Actors":"Sullivan Stapleton, Eva Green, Lena Headey, Hans Matheson",
//"Plot":"Greek general Themistokles leads the charge against invading Persian forces led by mortal-turned-god Xerxes and Artemisia, vengeful commander of the Persian navy.",
//"Language":"English","Country":"USA","Awards":"6 nominations.",
//"Poster":"http://ia.media-imdb.com/images/M/MV5BMTEwNTU2MjAwMDdeQTJeQWpwZ15BbWU3MDk2Njc2Njk@._V1_SX300.jpg","Metascore":"48",
//"imdbRating":"6.3","imdbVotes":"204,132","imdbID":"tt1253863",
//"Type":"movie","tomatoMeter":"42",
//"tomatoImage":"rotten","tomatoRating":"4.9","tomatoReviews":"171",
//"tomatoFresh":"72","tomatoRotten":"99",
//"tomatoConsensus":"It's bound to hit some viewers as an empty exercise in stylish gore, and despite a gonzo starring performance from Eva Green, 300: Rise of an Empire is a step down from its predecessor.",
//"tomatoUserMeter":"52",
//"tomatoUserRating":"3.3","tomatoUserReviews":"139820",
//"DVD":"24 Jun 2014","BoxOffice":"$106.6M",
//"Production":"Warner Bros. Pictures",
//"Website":"http://www.300themovie.com/","Response":"True"},

