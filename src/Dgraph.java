import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Dgraph  {
	
	private int V;
	public List<List<Integer>> adj;
	private Map<String, Integer> stringToIndex;
    private Map<Integer, String> indexToString;
	
	Dgraph(int V) {
        this.V = V;
        stringToIndex = new HashMap<>();
        indexToString = new HashMap<>();
        adj = new ArrayList<>(V);

        for (int v = 0; v < V; v++) {
            adj.add(new ArrayList<>());
        }
    }
	
	public void printGraph() {
		
	    for (int i = 0; i < V; i++) {
	        System.out.print(getCity(i) + " -> ");
	        for (Integer neighbor : adj.get(i)) {
	            System.out.print(getCity(neighbor) + " ");
	        }
	        System.out.println();
	    }
	}

	
	

	void addEdge(String v, String w) {
	    if (!stringToIndex.containsKey(v)) {
	        stringToIndex.put(v, stringToIndex.size());
	        indexToString.put(stringToIndex.size() - 1, v);
	    }
	    if (!stringToIndex.containsKey(w)) {
	        stringToIndex.put(w, stringToIndex.size());
	        indexToString.put(stringToIndex.size() - 1, w);
	    }
	    adj.get(stringToIndex.get(v)).add(stringToIndex.get(w));
	}
	
	

    
	int getCityIndex(String city) {
	    Integer index = stringToIndex.get(city);
	    if (index == null) {
	        // Handle the case where the city is not found, maybe return -1 or throw an exception
	        throw new IllegalArgumentException("City not found: " + city);
	    }
	    return index;
	}

    
    String getCity(int index) {
    	String city = indexToString.get(index);
    	return city;
    }
    
    int size() {
    	return V;
    }
    
    
	
	
}
