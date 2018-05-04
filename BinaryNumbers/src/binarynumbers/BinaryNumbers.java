package binarynumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinaryNumbers {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        List<Integer> result = new ArrayList<>();
        int count = 0;
        int maxCount = 0;
        int decNumber  = sc.nextInt();
        
        while (decNumber > 0) {
            int remainder =decNumber % 2;
           decNumber =decNumber / 2;
            result.add(0, remainder);
        }
        
        for (int i = 0, l = result.size(); i < l; i++) {
            if (result.get(i) == 1) {
                count++;
            } else {
                count = 0;
            }
            maxCount = count > maxCount ? count : maxCount;
        }
        System.out.println(maxCount);
    }
}
