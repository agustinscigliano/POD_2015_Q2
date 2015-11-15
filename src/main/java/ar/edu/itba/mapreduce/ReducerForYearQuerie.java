package ar.edu.itba.mapreduce;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;


@SuppressWarnings("serial")
public class ReducerForYearQuerie implements ReducerFactory<Integer, YearQuerie, YearQuerie> 
{
    public Reducer<YearQuerie, YearQuerie> newReducer(final Integer year) 
    {
        return new Reducer<YearQuerie, YearQuerie>()
	    {
	        private YearQuerie max;
	        
	        public void beginReduce()  
	        {
	            max= new YearQuerie(-1, "");
	        }
	
	        public void reduce(YearQuerie value) 
	        {
	        	if (max.compareTo(value) < 1)
	            	max= value;
	        }
	
	        public YearQuerie finalizeReduce() 
	        {
	        	System.out.println(String.format("FinalReduce for %d = %s", year, max));
	            return max ;
	        }
	    };
	}

}
