import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        
        String fileName = input.nextLine();

        File file = new File(fileName);
        Scanner scan = new Scanner(file);

        Dgraph graph = null;
        Set<String> cities = new HashSet<>();
        List<String[]> cityData = new ArrayList<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String city[] = line.split(",");
            String start = city[0];
            String destination = city[1];

            if (!cities.contains(start) || !cities.contains(destination)) {
                cities.add(start);
                cities.add(destination);
            }

            cityData.add(city);
        }

        graph = new Dgraph(cities.size());

        for (String[] city : cityData) {
            String start = city[0];
            String destination = city[1];

            graph.addEdge(start, destination);
        }

        
        int hops = input.nextInt();

        
        input.nextLine(); // Consume the newline character
        String sourceCity = input.nextLine();

        DFS dfs = new DFS(graph, sourceCity, hops);

        List<List<String>> list = dfs.DFShelper(graph.getCityIndex(sourceCity), hops);

        System.out.println("Number of total routes: " + list.size());
        System.out.println("Routes are:");

        for (int i = list.size() - 1; i >= 0; i--) {
            List<String> route = list.get(i);
            for (int j = 0; j < route.size() - 1; j++) {
                System.out.print(route.get(j) + "-");
            }
            System.out.println(route.get(route.size() - 1));
        }
    }
}

