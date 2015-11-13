package ar.edu.itba.mapreduce;

import ar.edu.itba.model.Movie;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;


// Parametrizar con los tipos de keyInput, ,valueInput, keyoutput, valueOutput
@SuppressWarnings("serial")
public class MapperForYearQuerie implements Mapper<String, Movie, Integer, YearQuerie> 
{
    public void map(String keyinput, Movie valueinput, Context<Integer, YearQuerie> context)
    {    
    	
          YearQuerie valueoutput = new YearQuerie(valueinput.getMetascore(), valueinput.getTitle());
         context.emit(valueinput.getYear(), valueoutput );

         System.out.println(String.format("Se emite (%s, %s)", 
           		 valueinput.getYear(), valueoutput));
      }
}