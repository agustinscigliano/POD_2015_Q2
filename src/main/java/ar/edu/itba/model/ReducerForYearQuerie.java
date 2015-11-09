package ar.edu.itba.model;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;


public class ReducerForYearQuerie implements ReducerFactory<Integer, YearQuerie, YearQuerie> 
{
    public Reducer<YearQuerie, YearQuerie> newReducer(final Integer year) 
    {
        return new Reducer<YearQuerie, YearQuerie>()
	    {
	        private YearQuerie max;
	        
	        public void beginReduce()  // una sola vez en cada instancia
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
	            return max ;
	        }
	    };
	}

}
