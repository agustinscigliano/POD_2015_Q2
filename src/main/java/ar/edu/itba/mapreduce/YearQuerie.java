package ar.edu.itba.mapreduce;


import java.io.IOException;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

public class YearQuerie implements DataSerializable, Comparable<YearQuerie>
{
	private int metaScore ;
	private String movie;

	public YearQuerie() {
	}

	public YearQuerie(int metaScore, String movie) {
		this.metaScore = metaScore;
		this.movie = movie;
	}

	public long getMetaScore() {
		return metaScore;
	}

	public void setMetaScore(int metaScore) {
		this.metaScore = metaScore;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public void writeData(ObjectDataOutput out)
			throws IOException {

		out.writeInt(metaScore);
		out.writeUTF(movie);
	}

	public void readData(ObjectDataInput in)
			throws IOException {

		metaScore = in.readInt();
		movie = in.readUTF();
	}

	public String toString() {

		return String.format("Movie: %s with MetaScore %d", movie, metaScore);
	}

	public int compareTo(YearQuerie other) {
		return (int) (getMetaScore() - other.getMetaScore());
	}
}
