package com.retroarcade.wordle.app;

/*
public class WordleClient extends Screens {
    private final static boolean DEBUG_MODE = true; // This DEBUG_MODE will display the answer for testing purposes.
    private final static String HISTORY_PATH = "history.txt"; // This contains the path to the history text file.
    private static String input;
    private static Board board;


    public static Timer Timer = new Timer();
    public static TimerTask menuTimer = new TimerTask() {
        public void run(){
            System.out.println("");
            try {
                getUserInput();
                chooseScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }

        };
    };





    public static void chooseScreen() throws IOException {
        I = Files.readString(Path.of("lib/resources/Banners/instructions/instructions.txt")).trim();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter: ");
        input = scan.next();

        if(input.equals("I")){
            System.out.println(ANSI_BLUE+I);
            java.util.Timer t = new Timer();
            Timer.scheduleAtFixedRate(menuTimer, 4000, 1000000);

        } if(input.equals("P")){
            Display.print(board);
        } if(input.equals("S")){
            System.out.println("stats to be printed here");
            Timer.scheduleAtFixedRate(menuTimer, 4000, 1000000);
            chooseScreen();
        } else {
            System.out.println("Please enter a valid option: [P]lay game, [I]nstructions, [S]tats");

        }

    }



    public static void main(String[] args) throws Exception {
        // initalize the WordleBoard and Scanner for user input.
        board = new Board("words.txt", 6);





            mainMain();
            chooseScreen();


//        while (!board.isGameOver()) {
//
//          //  Display.clear();
//            //Display.print(board);
//
//            if (DEBUG_MODE) {
//                Display.printAnswer(board.getAnswer());
//            }
//
//            Display.promptForWord();
//            board.guess(input.nextLine().toLowerCase());
//        }

       // Display.clear();
       // WelcomeBanner();
        //getUserInput();
        //Display.clear();

       // Display.print(board);
        //Display.printAnswer(board.getAnswer());
        //recordGame(board);


    }





    // ARGS: (WordleBoard) Takes a board to record it's game.
    // The games played counter at the HISTORY_PATH is incremented. If the board was
    // completed successfully that is also recorded along with the winning word.
    public static void recordGame(Board board) throws Exception {
        Scanner reader = new Scanner(new File(HISTORY_PATH));
        String gamesPlayed = reader.nextLine();
        String gamesWon = reader.nextLine();
        String winningWord = "";
        while (reader.hasNextLine()) {
            winningWord += reader.nextLine() + '\n';
        }
        reader.close();

        int numGamesPlayed = Integer.parseInt(gamesPlayed
                .substring(gamesPlayed.indexOf(": ") + 2).trim()) + 1;
        int numGamesWon = Integer.parseInt(gamesWon
                .substring(gamesWon.indexOf(": ") + 2).trim());

        if (board.hasWon()) {
            numGamesWon += 1;
            winningWord += board.getAnswer();
        }

        Files.writeString(Path.of(HISTORY_PATH),
                String.format("GamesPlayed: %d\nGamesWon: %d\n%s"
                        , numGamesPlayed, numGamesWon, winningWord));
    }
}
 */

class Temp {

}
