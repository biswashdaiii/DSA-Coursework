// Definition for a binary tree node
//4b
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class MagicalGrove {

    // Helper class to store information about each subtree
    static class GroveInfo {
        boolean isBST;  // Indicates if the current subtree is a valid BST or not
        int sum;        // Sum of all node values in the current subtree
        int minVal;     // Minimum value in the current subtree
        int maxVal;     // Maximum value in the current subtree

        GroveInfo(boolean isBST, int sum, int minVal, int maxVal) {
            this.isBST = isBST;
            this.sum = sum;
            this.minVal = minVal;
            this.maxVal = maxVal;
        }
    }

    private int maxSum = 0;  

    // Main function to find the maximum sum of a magical grove (BST subtree)
    public int findMaxMagicalGrove(TreeNode root) {
        traverse(root);  
        return maxSum;   
    }

    // Function to traverse the tree in post-order (left, right, root)
    private GroveInfo traverse(TreeNode node) {
        // Base case: if the node is null, return a default GroveInfo object
        if (node == null) {
            return new GroveInfo(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        // Recursively traverse the left and right subtrees
        GroveInfo leftInfo = traverse(node.left);
        GroveInfo rightInfo = traverse(node.right);

        // Check if the current subtree rooted at 'node' is a valid BST
        if (leftInfo.isBST && rightInfo.isBST && node.val > leftInfo.maxVal && node.val < rightInfo.minVal) {
            // If valid, calculate the sum of the current BST subtree
            int currentSum = node.val + leftInfo.sum + rightInfo.sum;
            // Update maxSum if the current subtree's sum is greater
            maxSum = Math.max(maxSum, currentSum);
            // Determine the minimum and maximum values in the current subtree
            int currentMin = Math.min(node.val, leftInfo.minVal);
            int currentMax = Math.max(node.val, rightInfo.maxVal);

            // Return the GroveInfo object with updated information
            return new GroveInfo(true, currentSum, currentMin, currentMax);
        } else {
            // If the current subtree is not a valid BST, return a GroveInfo object indicating it
            return new GroveInfo(false, 0, 0, 0);
        }
    }

    public static void main(String[] args) {
        // Example to test the solution
        MagicalGrove mg = new MagicalGrove();

        // Constructing the binary tree from the example
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(5);
        root.right.right.left = new TreeNode(4);
        root.right.right.right = new TreeNode(6);

        // Find and print the maximum sum of the magical grove (BST subtree)
        System.out.println(mg.findMaxMagicalGrove(root)); // Output should be 20
    }
}
