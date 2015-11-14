package ar.edu.itba.model;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

/**
 *Si el campo director tiene más de 1 director no es necesario separarlos, 
 *tomenlo como un director único. O sea si una pelicula tiene como "Director": 
 *"Kevin Smith, Woody Allen" el director a tomar "Kevin Smith, Woody Allen" que 
 *obviamente es diferente del director  "Kevin Smith" y del director  "Woody Allen"
 */
/**
 * Nuevas Aclaraciones:
"N/A" son directores  y actores válidos (no se descartan)
"N/A "en campos numéricos se deben reemplazar por 0 (no se descartan)
En la primera query (N actors) se devuelve exactamente N no más o no menos.
 Si dos o más actores empatan en la cantidad de votos se resuelve el orden 
 de los mismos por el nombre del actor alfabeticamente.
 *
 */
public class Movie  implements DataSerializable /*IdentifiedDataSerializable*/{
	private String Title = null;
	private String Year = null;
	private String rated = null;
	private String released = null;
	private String Runtime = null;
	private String Genre = null;
	private String Director = null;
	private String Writer = null;
	private String Actors = null;
	private String Plots = null;
	private String Language = null;
	private String Poster = null;
	private String Metascore = null;
	private String imdbRating = null;
	private String imdbVotes = null;
	private String imdbID = null;
	private String Type = null;
	private String tomatoMeter = null;
	private String tomatoImage = null;
	private String tomatoRating = null;
	private String tomatoReviews = null;
	private String tomatoFresh = null;
	private String tomatoRotten = null;
	private String tomatoConsensus = null;
	private String tomatoUserMeter = null;
	private String tomatoUserRating = null;
	private String tomatoUserReviews = null;
	private String DVD = null;
	private String BoxOffice = null;
	private String Production = null;
	private String Website = null;
	private String Response = null;

	public Movie() {

	}

	public String getTitle() {
		return Title;
	}

	public Integer getYear() {
		try{
			return Integer.valueOf(Year);
		}catch(Exception e){
			return 0;
		}
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

	public Integer getMetascore() {
		try{
			return Integer.valueOf(Metascore);
		}catch(Exception e){
			return 0;
		}
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

	public void fromString(String movieJSON) {
		Gson gson = new GsonBuilder().create();
		Movie m = gson.fromJson(movieJSON, Movie.class);
		this.Title = m.Title;
		this.Year = m.Year;
		this.rated = m.rated;
		this.released = m.released;
		this.Runtime = m.Runtime;
		this.Genre = m.Genre;
		this.Director = m.Director;
		this.Writer = m.Writer;
		this.Actors = m.Actors;
		this.Plots = m.Plots;
		this.Language = m.Language;
		this.Poster = m.Poster;
		this.Metascore = m.Metascore;
		this.imdbRating = m.imdbRating;
		this.imdbVotes = m.imdbVotes;
		this.imdbID = m.imdbID;
		this.Type = m.Type;
		this.tomatoMeter = m.tomatoMeter;
		this.tomatoReviews = m.tomatoReviews;
	}

	@Override
	public void readData(ObjectDataInput in) throws IOException {
		String json	= in.readUTF();
		fromString(json);
	}

	@Override
	public void writeData(ObjectDataOutput out) throws IOException {
		out.writeUTF(toString());		
	}
//	@Override
//	public int getFactoryId() {
//		System.out.println("FactoryID");
//		return MovieDataSerializableFactory.FACTORY_ID;
//	}
//	@Override
//	public int getId() {
//		System.out.println("getId");
//		return MovieDataSerializableFactory.MOVIE_TYPE;
//	}
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

