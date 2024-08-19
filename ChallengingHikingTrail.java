
//5b
import java.util.Scanner;

public class ChallengingHikingTrail {

    // Method to find the longest continuous hike within the elevation gain limit
    public static int longestContinuousHike(int[] trail, int k) {
        int n = trail.length;
        if (n == 0) return 0; // Handle empty trail case

        int[] dp = new int[n];
        int max = 1; // Initialize max length of hike

        // Initialize the dp array, where dp[i] will store the length of the longest valid hike ending at index i
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            // If the current point is higher than the previous point and the elevation gain is within the limit
            if (trail[i] > trail[i - 1] && (trail[i] - trail[i - 1]) <= k) {
                dp[i] = dp[i - 1] + 1; // Extend the previous valid hike
            } else {
                dp[i] = 1; // Start a new hike
            }
            max = Math.max(max, dp[i]); // Update the maximum length found
        }

        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the length of the trail: ");
        int n = scanner.nextInt();
        if (n <= 0) {
            System.out.println("Trail length must be greater than zero.");
            return;
        }

        int[] trail = new int[n];
        System.out.println("Enter the trail elements: ");
        for (int i = 0; i < n; i++) {
            trail[i] = scanner.nextInt();
        }

        System.out.print("Enter the elevation gain limit (k): ");
        int k = scanner.nextInt();

        System.out.println("Longest continuous hike length: " + longestContinuousHike(trail, k));

        scanner.close();
    }
}
