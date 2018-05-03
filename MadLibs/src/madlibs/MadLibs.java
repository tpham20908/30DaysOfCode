package madlibs;

import java.util.Random;
import java.util.Scanner;

public class MadLibs {

    Scanner scanner = new Scanner(System.in);
    String story, name, adj1, adj2, noun1, noun2, noun3, adv, randNum;
    Random rand = new Random();

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdj1() {
        return adj1;
    }

    public void setAdj1(String adj1) {
        this.adj1 = adj1;
    }

    public String getAdj2() {
        return adj2;
    }

    public void setAdj2(String adj2) {
        this.adj2 = adj2;
    }

    public String getNoun1() {
        return noun1;
    }

    public void setNoun1(String noun1) {
        this.noun1 = noun1;
    }

    public String getNoun2() {
        return noun2;
    }

    public void setNoun2(String noun2) {
        this.noun2 = noun2;
    }

    public String getNoun3() {
        return noun3;
    }

    public void setNoun3(String noun3) {
        this.noun3 = noun3;
    }

    public String getAdv() {
        return adv;
    }

    public void setAdv(String adv) {
        this.adv = adv;
    }

    public String getRandNum() {
        return randNum;
    }

    public void setRandNum() {
        int num = Math.abs(rand.nextInt()) % 100;
        int[] numberHolder = new int[3];
        for (int i = 0; i < 3; i++) {
            numberHolder[i] = num + i;
        }
        randNum = String.format("not %d, not %d, but %d", 
                numberHolder[0], numberHolder[1], numberHolder[2]);
    }
    
    public void printInstruction() {
        System.out.println("Welcome to the MadLibs game. If you type in word"
                + ", we'll give you a story. Start by typing a game.");
    }
    
    public void putTogetherTheStory() {
        String story = String.format("Jesse and her best friend %s went to Disney World today!"
                + "\nThey saw a %s in a show at the Magic Kingdom and ate a %s feast "
                + "for dinner. \nThe next day, they ran %s to meet Mickey mouse in his %s "
                + "\nand then that night they gazed at the %s %s fireworks shooting from "
                + "the %s.\n", 
                getName(), getNoun1(), getAdj1(), getAdv(), getNoun2(), getRandNum(),
                getAdj2(), getNoun3());
        setStory(story);
    }
    
    public void enterName() {
        System.out.print("\nEnter name: ");
        setName(scanner.nextLine());
    }
    
    public void enterNoun1() {
        System.out.print("\nEnter noun1: ");
        setNoun1(scanner.nextLine());
    }
    
    public void enterNoun2() {
        System.out.print("\nEnter noun2: ");
        setNoun2(scanner.nextLine());
    }
    
    public void enterNoun3() {
        System.out.print("\nEnter noun3: ");
        setNoun3(scanner.nextLine());
    }
    
    public void enterAdj1() {
        System.out.print("\nEnter adj1: ");
        setAdj1(scanner.nextLine());
    }
    
    public void enterAdj2() {
        System.out.print("\nEnter adj2: ");
        setAdj2(scanner.nextLine());
    }
    
    public void enterAdv() {
        System.out.print("\nEnter adv: ");
        setAdv(scanner.nextLine());
    }
    
    public void play() {
        enterName();
        enterNoun1();
        enterNoun2();
        enterNoun3();
        enterAdj1();
        enterAdj2();
        enterAdv();
        setRandNum();
        putTogetherTheStory();
        System.out.println(getStory());
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        MadLibs game = new MadLibs();
        game.printInstruction();
        game.play();
    }
}
