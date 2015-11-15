package ar.edu.itba.mapreduce;

import java.io.IOException;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

public class ActorsQuerie implements DataSerializable, Comparable<ActorsQuerie>{
	private String name;
	private long votes;
	
	public ActorsQuerie() {
	}
	
	public ActorsQuerie(final String name, final long votes) {
		this.name = name;
		this.votes = votes;
	}
	
	public void addVotes(final long moreVotes) {
		votes+=moreVotes;
	}
	
	public long getVotes() {
		return votes;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public void writeData(final ObjectDataOutput out) throws IOException {
		out.writeUTF(name);
		out.writeLong(votes);
	}

	@Override
	public void readData(final ObjectDataInput in) throws IOException {
		name = in.readUTF();
		votes = in.readLong();
	}
	
	@Override
	public String toString() {
		return name + " --> " + votes + " votes.";
	}

	@Override
	public int compareTo(final ActorsQuerie other) {
		if (votes == other.getVotes()) {
			return name.compareTo(other.getName());
		} else if (votes < other.getVotes()) {
			return 1;
		} else if (votes > other.getVotes()) {
			return -1;
		}
		return 0;
	}
}
