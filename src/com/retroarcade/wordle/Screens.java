package com.retroarcade.wordle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Screens {




    ;
   // String P = Files.readString(Path.of("lib/resources/Banners/instructions/instructions.txt"));


    public static String getInput() {
        return input;
    }

    public static void setInput(String input) {
        Screens.input = input;
    }


//    public void setI(String i) throws IOException {
//        i = I;
//    }



//    public static java.util.Timer getTimer() {
//        return timer;
//    }
//
//    public static void setTimer(java.util.Timer timer) {
//        Screens.timer = timer;
//    }
//
//    public static TimerTask getTt() {
//        return menuTimer;
//    }
//
//    public static void setTt(TimerTask tt) {
//        Screens.menuTimer = tt;
//    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private static String input;

    public static String I;



//    private static Timer timer;
//
//   public static Timer Timer = new Timer();
//    public static TimerTask menuTimer = new TimerTask() {
//        public void run(){
//            System.out.println("");
//          //  System.out.println("Enter [M] to return to the Main Menu");
//            try {
//                getUserInput();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        };
//    };

//    public static void chooseScreen() throws IOException {
//        if(input.equals("I")){
//            System.out.println(ANSI_BLUE+getI());
//
//
//
//            //getUserInput();
//        }
//        if(input.equals("P")){
//            Display.print(board);
//        }
//
//    }

    public static void getUserInput() throws IOException {

        // String S = Files.readString(Path.of("lib/resources/Banners/instructions/welcome.txt"));
        //String I = Files.readString(Path.of("lib/resources/Banners/instructions/instructions.txt"));

        System.out.println(ANSI_PURPLE+"                                             Enter an option from below");
        System.out.println(ANSI_PURPLE+"                             Enter: [P]: Play Game |  [L]: Leader board] |  [I]: Instructions");
        //user input
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter: ");
//        input = scan.next();



    }


        public static void mainMain() throws IOException {
        WelcomeBanner();
        getUserInput();
    }

//        public void chooseScreen(){
//        if(input.equals("I")){
//            System.out.println(ANSI_BLUE+I.trim());
//            Timer.scheduleAtFixedRate(tt, 6000, 1000000);
//
//
//            //getUserInput();
//        }
//        if(input.equals("P")){
//            Display.print(board);
//        }
//
//    }



    //    public static void mainMain() throws IOException {
//        WelcomeBanner();
//        getUserInput();
//    }


//    public static void changeBanner() throws IOException {
//        String I = Files.readString(Path.of("lib/resources/Banners/instructions/instructions.txt"));
//        //String P = Files.readString(Path.of("lib/resources/Banners/instructions/instructions.txt"));
//
//        if(input.equals("I")){
//
//            System.out.println(ANSI_BLUE+I);
//        } if ( input.equals("P")){
//            //start game
//            Board board;
//
//            System.out.println("");
//        }
//        if(input.equals("L")){
//            //show stats
//        }
//    }





//        public static void changeBanner() throws IOException {
//        String I = Files.readString(Path.of("lib/resources/Banners/instructions/instructions.txt"));
//        //String P = Files.readString(Path.of("lib/resources/Banners/instructions/instructions.txt"));
//
//        if(input.equals("I")){
//
//            System.out.println(ANSI_BLUE+I);
//        } if ( input.equals("P")){
//            //start game
//
//            System.out.println("");
//        }
//        if(input.equals("L")){
//            //show stats
//        }
//    }

    public static void WelcomeBanner() throws IOException {
        String welcome = Files.readString(Path.of("lib/resources/Banners/MainBanner/welcome.txt"));
        String worlde = Files.readString(Path.of("lib/resources/Banners/MainBanner/b1.txt"));
        System.out.print(welcome + ANSI_GREEN+worlde);
        System.out.println("");

    }


    //win screen
    //lose screen


}
//        sentences = new ArrayList<>();
//        Files.lines(Path.of("/Users/Ebb/Desktop/StudentWork/IntmJ/workspace/MiniProject/blackjack/lib/resources/box11.txt"))
//
//                .forEach(line -> {
//                    sentences.add(line);
//
//                    // System.out.println("                     " + line);
//                });
//        Files.lines(Path.of("/Users/Ebb/Desktop/StudentWork/IntmJ/workspace/MiniProject/blackjack/lib/resources/box11.txt"))
//                .forEach(line -> {
//sout
//                    System.out.println("                     " + ANSI_GREEN +line);
//                });








//        String word = "hey";
//        System.out.println(ANSI_PURPLE + word);





