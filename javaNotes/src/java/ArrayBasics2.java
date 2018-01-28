public class ArrayBasics2 {
    public static void main(String[] args) {
        int[][] pascalsTriangle; //2d array
        pascalsTriangle = new int[4][]; //create an array of int array reference. each one can point to an array of integers
        int[] rowZero = pascalsTriangle[0]; //copy bits in pascal[0] to rowZero

        pascalsTriangle[0] = new int[]{1}; //create array of size1 with val1
        pascalsTriangle[1] = new int[]{1, 1};
        pascalsTriangle[2] = new int[]{1, 2, 1};
        pascalsTriangle[3] = new int[]{1, 3, 3, 1};
        int[] rowTwo = pascalsTriangle[2];
        rowTwo[1] = -5;

        int[][] matrix;
        matrix = new int[4][]; //create 1 array. initialize every item in first dimension to be null. length 4.
        matrix = new int[4][4];
        //initialize 4 items where each points to an array of 4 items.

        int[][] pascalAgain = new int[][]{{1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}};
        // initialize an array [[1], [1,1], [1,2,3]...]

    }
}