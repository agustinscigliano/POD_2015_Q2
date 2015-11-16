package ar.edu.itba.model;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

/**
 *Si el campo director tiene mas de 1 director no es necesario separarlos, 
 *tomenlo como un director unico. O sea si una pelicula tiene como "Director": 
 *"Kevin Smith, Woody Allen" el director a tomar "Kevin Smith, Woody Allen" que 
 *obviamente es diferente del director  "Kevin Smith" y del director  "Woody Allen"
 */
/**
 * Nuevas Aclaraciones:
"N/A" son directores  y actores validos (no se descartan)
"N/A "en campos numericos se deben reemplazar por 0 (no se descartan)
En la primera query (N actors) se devuelve exactamente N no mas o no menos.
 Si dos o mas actores empatan en la cantidad de votos se resuelve el orden 
 de los mismos por el nombre del actor alfabeticamente.
 *
 */
public class Movie  implements DataSerializable{
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
	private transient NumberFormat format= null;
	private int puk = -1;

	public Movie() {
		 format = NumberFormat.getInstance(Locale.US);
	}
	public void setKey(int key){
		if(puk != -1)
			throw new IllegalStateException("The key was already set.");
		this.puk=key;
	}
	public int getKey() {
		return puk;
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

	public List<String> getActors() {
		try{
			return Arrays.asList(Actors.split("\\s*,\\s*"));
		}catch(Exception e){
			return null;
		}
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

	public Float getImdbRating() {
		try{
			return Float.valueOf(imdbRating);
		}catch(Exception e){
			return 0f;
		}
	}

	public Long getImdbVotes() {
		try{
			
		    Number number = format.parse(imdbVotes);
			return number.longValue();
		}catch(Exception e){
			return 0l;
		}
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
		this.tomatoFresh = m.tomatoFresh;
		this.tomatoRotten = m.tomatoRotten;
		this.tomatoConsensus = m.tomatoConsensus;
		this.tomatoUserMeter = m.tomatoUserMeter;
		this.tomatoUserRating = m.tomatoUserRating;
		this.tomatoUserReviews = m.tomatoUserReviews;
		this.DVD = m.DVD;
		this.BoxOffice = m.BoxOffice;
		this.Production = m.Production;
		this.Website = m.Website;
		this.Response = m.Response;
	}

	@Override
	public void readData(ObjectDataInput in) throws IOException {
		String json	= in.readUTF();
		fromString(json);
		this.puk = in.readInt();
	}

	@Override
	public void writeData(ObjectDataOutput out) throws IOException {
		out.writeUTF(toString());
		out.writeInt(puk);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + puk;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (puk != other.puk)
			return false;
		return true;
	}
}
