import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DFS {
    private boolean visited[];
    private Dgraph graph;

    DFS(Dgraph graph, String source,int hops) {
        this.graph = graph;
        visited = new boolean[graph.size()];
        
    }
    
    

    public List<List<String>> DFShelper(int v, int hops) {
        // Set variable documentation
        //--------------------------------------------------------
        // Summary: Marks the current vertex as visited and explores paths within a hop limit.
        // Precondition: The graph, visited array, source vertex, and hop limit are properly initialized.
        // Postcondition: Updates the visited array during traversal.
        //                Returns a list of paths within the specified hop limit from the source vertex.
        visited[v] = true;
        List<List<String>> paths = new ArrayList<>();

        if (hops == 0) {
            visited[v] = false;
            List<String> path = new ArrayList<>();
            path.add(graph.getCity(v));
            paths.add(path);
            return paths;
        }

        List<Integer> neighbors = graph.adj.get(v);

        for (Integer next : neighbors) {
            if (!visited[next]) {
                List<List<String>> subPaths = DFShelper(next, hops - 1);

                if (subPaths != null) {
                    for (List<String> subPath : subPaths) {
                        List<String> newPath = new ArrayList<>();
                        newPath.add(graph.getCity(v));
                        newPath.addAll(subPath);
                        paths.add(newPath);
                    }
                }
            }
        }

        visited[v] = false;

        List<List<String>> filteredPaths = new ArrayList<>();
        for (List<String> path : paths) {
            if (path.size() == hops + 1) {
                filteredPaths.add(path);
            }
        }

        return filteredPaths.isEmpty() ? null : filteredPaths;
    }







    
    
}
