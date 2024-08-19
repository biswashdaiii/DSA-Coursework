//5a

import java.util.Arrays;
import java.util.Random;

public class TSPUsingHillClimbing {

    private static final Random RANDOM = new Random(42);

    public static void main(String[] args) {
        int numCities = 10; // Number of cities
        double[][] cities = generateCities(numCities); // Generate random cities
        int[] bestRoute = hillClimbing(cities); // Find the best route using hill climbing
        double bestDistance = calculateDistance(cities, bestRoute); // Calculate the distance of the best route

        System.out.println("Best Route: " + Arrays.toString(bestRoute));
        System.out.println("Best Distance: " + bestDistance);
    }

    // Generate random city coordinates within [0, 1] range
    private static double[][] generateCities(int numCities) {
        double[][] cities = new double[numCities][2];
        for (int i = 0; i < numCities; i++) {
            cities[i][0] = RANDOM.nextDouble(); // x-coordinate
            cities[i][1] = RANDOM.nextDouble(); // y-coordinate
        }
        return cities;
    }

    // Calculate the total distance of a given route
    private static double calculateDistance(double[][] cities, int[] route) {
        double totalDistance = 0.0;
        for (int i = 0; i < route.length; i++) {
            int from = route[i];
            int to = route[(i + 1) % route.length]; // Wrap around to the start
            totalDistance += Math.hypot(cities[from][0] - cities[to][0], cities[from][1] - cities[to][1]);
        }
        return totalDistance;
    }

    // Create an initial route by shuffling the cities
    private static int[] createInitialRoute(int numCities) {
        int[] route = new int[numCities];
        for (int i = 0; i < numCities; i++) {
            route[i] = i;
        }
        // Shuffle the route to create a random starting point
        for (int i = 0; i < numCities; i++) {
            int j = RANDOM.nextInt(numCities);
            int temp = route[i];
            route[i] = route[j];
            route[j] = temp;
        }
        return route;
    }

    // Hill Climbing algorithm to find the shortest route
    private static int[] hillClimbing(double[][] cities) {
        int numCities = cities.length;
        int[] currentRoute = createInitialRoute(numCities); // Initial route
        double currentDistance = calculateDistance(cities, currentRoute); // Distance of the initial route

        boolean improved;
        do {
            improved = false; // Flag to check if any improvement is made
            // Generate neighbors by swapping pairs of cities
            for (int i = 0; i < numCities - 1; i++) {
                for (int j = i + 1; j < numCities; j++) {
                    int[] neighbor = currentRoute.clone(); // Create a copy of the current route
                    // Swap the cities at positions i and j
                    int temp = neighbor[i];
                    neighbor[i] = neighbor[j];
                    neighbor[j] = temp;

                    double neighborDistance = calculateDistance(cities, neighbor); // Calculate distance of the new route
                    if (neighborDistance < currentDistance) { // Check if the new route is better
                        currentRoute = neighbor; // Update the route
                        currentDistance = neighborDistance; // Update the distance
                        improved = true; // Set the flag to true to continue the loop
                    }
                }
            }
        } while (improved); // Continue until no further improvement

        return currentRoute; // Return the best route found
    }
}
