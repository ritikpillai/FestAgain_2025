/**
 * Java code to find the largest rectangle of 1s in a binary matrix.
 * Uses stack-based histogram logic row-wise.
 * Author: Anish Mohanty
 */

import java.util.Stack;

public class MaxRectangleInBinaryMatrix {

    // Function to compute maximum area rectangle in binary matrix
    public static int maximalRectangle(int[][] matrix) {
        if (matrix.length == 0) return 0;

        int maxArea = 0;
        int[] histogram = new int[matrix[0].length];

        for (int row = 0; row < matrix.length; row++) {
            // Update histogram by adding current row values
            for (int col = 0; col < matrix[0].length; col++) {
                histogram[col] = matrix[row][col] == 0 ? 0 : histogram[col] + 1;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(histogram));
        }

        return maxArea;
    }

    // Helper function to compute max area in histogram
    private static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int currHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && currHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = (stack.isEmpty()) ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    // Sample main method for testing
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 0, 1, 0, 0},
            {1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0}
        };

        int maxRectangle = maximalRectangle(matrix);
        System.out.println("Max rectangle area of 1s: " + maxRectangle);  // Output: 6
    }
}
