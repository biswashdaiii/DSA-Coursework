

//1a
import java.util.*;

public class ClassroomScheduler {

    static class Class {
        int start;
        int end;
        int size;

        Class(int start, int end) {
            this.start = start;
            this.end = end;
            this.size = end - start; // Use the duration as a proxy for size
        }
    }

    public static int mostUtilizedClassroom(int n, int[][] classes) {
        // Convert input to Class objects and sort
        List<Class> classList = new ArrayList<>();
        for (int[] c : classes) {
            classList.add(new Class(c[0], c[1]));
        }

        // Sort classes by start time, and by size (duration) in descending order if start times are equal
        classList.sort((a, b) -> a.start != b.start ? Integer.compare(a.start, b.start) : Integer.compare(b.end - b.start, a.end - a.start));

        // Priority queue to track end times of classrooms
        PriorityQueue<int[]> freeRooms = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] classCount = new int[n];

        // Initialize all rooms as free
        for (int i = 0; i < n; i++) {
            freeRooms.offer(new int[]{i, 0}); // Room index and end time
        }

        for (Class c : classList) {
            // Find a room that is free or becomes free before the class starts
            while (!freeRooms.isEmpty() && freeRooms.peek()[1] > c.start) {
                // Delay until a room becomes available
                int[] room = freeRooms.poll();
                // Update the room end time to the maximum of its current end time and the class end time
                freeRooms.offer(new int[]{room[0], Math.max(room[1], c.end)});
                classCount[room[0]]++;
                break;
            }
        }

        // Find the classroom with the maximum number of classes
        int maxClasses = 0;
        int mostUtilizedRoom = 0;
        for (int i = 0; i < n; i++) {
            if (classCount[i] > maxClasses) {
                maxClasses = classCount[i];
                mostUtilizedRoom = i;
            }
        }

        return mostUtilizedRoom;
    }

    public static void main(String[] args) {
        int n1 = 2;
        int[][] classes1 = {{0, 10}, {1, 5}, {2, 7}, {3, 4}};
        System.out.println("Classroom with most classes: " + mostUtilizedClassroom(n1, classes1)); // Output: 0

        int n2 = 3;
        int[][] classes2 = {{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}};
        System.out.println("Classroom with most classes: " + mostUtilizedClassroom(n2, classes2)); // Output: 1 (Example Output)
    }
}
