//3b
import java.util.Arrays;

public class BusBoardingOptimizer {
    public static int[] optimizeBoarding(int[] passengers, int k) {
        int[] result = new int[passengers.length];
        
        for (int i = 0; i < passengers.length; i += k) {
            int left = i;
            int right = Math.min(i + k - 1, passengers.length - 1);
            
            while (left < right) {
                int temp = passengers[left];
                result[left] = passengers[right];
                result[right] = temp;
                left++;
                right--;
            }
            
            if (left == right) {
                result[left] = passengers[left];
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] passengers = {1, 2, 3, 4, 5};
        int k = 2;
        
        int[] optimized = optimizeBoarding(passengers, k);
        System.out.println(Arrays.toString(optimized));
    }
}