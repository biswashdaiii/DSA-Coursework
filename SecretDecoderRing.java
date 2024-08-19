//1b

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SecretDecoderRing {

    // Inner class to represent a rotation instruction
    static class Instruction {
        int start_disc;
        int end_disc;
        int direction;

        Instruction(int start_disc, int end_disc, int direction) {
            this.start_disc = start_disc;
            this.end_disc = end_disc;
            this.direction = direction;
        }
    }

    // Method to decipher the message based on given instructions
    public static String decipherMessage(String s, List<Instruction> instructions) {
        char[] messageArray = s.toCharArray(); // Convert the string to a char array for easier manipulation

        for (Instruction instruction : instructions) {
            int start_disc = instruction.start_disc;
            int end_disc = instruction.end_disc;
            int direction = instruction.direction;

            // Ensure indices are within bounds
            if (start_disc < 0 || end_disc >= messageArray.length || start_disc > end_disc) {
                throw new IllegalArgumentException("Invalid disc indices.");
            }

            for (int i = start_disc; i <= end_disc; i++) {
                char currentChar = messageArray[i];
                // Rotate character based on direction
                if (direction == 1) {
                    // Rotate clockwise
                    messageArray[i] = currentChar == 'z' ? 'a' : (char) (currentChar + 1);
                } else {
                    // Rotate counter-clockwise
                    messageArray[i] = currentChar == 'a' ? 'z' : (char) (currentChar - 1);
                }
            }
        }
        return new String(messageArray); // Convert char array back to string and return
    }

    public static void main(String[] args) {
        // Example usage
        String message = "hello";
        List<Instruction> shifts = Arrays.asList(
                new Instruction(0, 1, 1),
                new Instruction(2, 3, 0),
                new Instruction(0, 2, 1)
        );

        String decipheredMessage = decipherMessage(message, shifts);
        System.out.println("Deciphered Message: " + decipheredMessage);
    }
}
