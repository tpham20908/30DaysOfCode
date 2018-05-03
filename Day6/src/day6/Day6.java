package day6;

import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {
    
    public static Scanner input = new Scanner(System.in);
    int t;

    public int getT() {
        return t;
    }

    public void setT() {
        System.out.println("Enter t: ");
        t = input.nextInt();
        input.nextLine();
    }
    
    public void separatingStr(String str) {
        String evenStr = "";
        String oddStr = "";
        
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0)
                evenStr += str.charAt(i);
            else
                oddStr += str.charAt(i);
        }
        System.out.println(evenStr + " " + oddStr);
    }

    public static void main(String[] args) {
        Day6 day6 = new Day6();
        day6.setT();
        int t = day6.getT();
        
        String[] strs = new String[t];
        for (int i = 0; i < t; i++) {
            strs[i] = input.nextLine();
        }
        
        for (int i = 0; i < strs.length; i++) {
            day6.separatingStr(strs[i]);
        }
    }
}
