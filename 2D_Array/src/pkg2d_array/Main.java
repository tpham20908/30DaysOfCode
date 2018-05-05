package pkg2d_array;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int maxSum = 0;
        Scanner in = new Scanner(System.in);
        int arr[][] = new int[6][6];
        outerloop:
        for(int i=0; i < 6; i++){
            for(int j=0; j < 6; j++){
                arr[i][j] = in.nextInt();
                if (in.hasNextLine())
                    break;
            }
        }
        
        for(int i=0; i < 6; i++){
            for(int j=0; j < 6; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
        
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                int[][] hg = createHourGlass(i, j, arr);
                int sum = sumHourGlass(hg);
                maxSum = sum > maxSum ? sum : maxSum;
            }
        }
        
        System.out.println(maxSum);
    }
    
    public static int[][] createHourGlass(int topRow, int leftCol, int[][] arr) {
        int[][] block = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                block[i][j] = arr[topRow + i][leftCol + j];
            }
        }
        block[1][0] = 0;
        block[1][2] = 0;
        return block;
    }
    
    public static int sumHourGlass(int[][] block) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sum += block[i][j];
            }
        }
        return sum;
    }
}
