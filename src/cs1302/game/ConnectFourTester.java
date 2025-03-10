package cs1302.game;

import cs1302.gameutil.Token;
import cs1302.gameutil.GamePhase;

/**
 * A program to test the ConnectFour implementation against the specification
 * that is mainly described through JavaDoc comments.
 */
public class ConnectFourTester {

    public static void main(String[] args) {
        // TODO: Students should add additional test methods to test individual functions
        // they implemented in ConnectFour class and call those test methods from here.

        // Example test cases to test gameplay cases. You can uncomment the two lines
        //below and run these tests once you have completed the ConnectFour class.
        testGamePlayCase01();
        testGamePlayCase02();
        testGamePlayCase03();
        testGamePlayCase04();
        testGamePlayCase05();

    } // main

    /**
     * When a player makes a connect four from their last dropped token,
     * isLastDropConnectFour should return true.
     */
    public static void testGamePlayCase01() {
        ConnectFour game = new ConnectFour(6, 7);
        game.setPlayerTokens(Token.RED, Token.BLUE);
        game.dropToken(0, 0); // player 0 drops at col 0.
        game.dropToken(1, 1); // player 1 drops at col 1.
        game.dropToken(0, 0); // player 0 drops at col 0.
        game.dropToken(1, 1); // player 1 drops at col 1.
        game.dropToken(0, 0); // player 0 drops at col 0.
        game.dropToken(1, 1); // player 1 drops at col 1.
        game.dropToken(0, 0); // player 0 drops at col 0.

        // Player 0 made a connect four with the last drop.
        if (!game.isLastDropConnectFour()) {
            throw new AssertionError("Fail: isLastDropConnectFour should return true"
                                     + " since player 0 made c4 in last drop.");
        } // if
        if (game.getPhase() != GamePhase.OVER) {
            throw new AssertionError("Fail: isLastDropConnectFour should set the game"
                                     + " phase to OVER.");
        } // if
        System.out.println("Pass: isLastDropConnectFour returned true and game phase"
                           + " is set to OVER since player 0 made c4 in the last drop.");
    } // testGamePlayCase01

    /**
     * When a player makes a connect four from their last dropped token,
     * isLastDropConnectFour should return true.
     */
    public static void testGamePlayCase02() {
        ConnectFour game = new ConnectFour(6, 7);
        game.setPlayerTokens(Token.RED, Token.BLUE);
        game.dropToken(0, 0); // player 0 drops at col 0.
        game.dropToken(1, 1); // player 1 drops at col 1.
        game.dropToken(0, 1); // player 0 drops at col 1.
        game.dropToken(1, 2); // player 1 drops at col 2.
        game.dropToken(0, 2); // player 0 drops at col 2.
        game.dropToken(1, 3); // player 1 drops at col 3.
        game.dropToken(0, 2); // player 0 drops at col 2.
        game.dropToken(1, 3); // player 1 drops at col 3.
        game.dropToken(0, 3); // player 0 drops at col 3.
        game.dropToken(1, 6); // player 1 drops at col 6.
        game.dropToken(0, 3); // player 0 drops at col 3.

        // Player 0 made a connect four with the last drop.
        if (!game.isLastDropConnectFour()) {
            throw new AssertionError("Fail: isLastDropConnectFour should return true"
                                     + " since player 0 made c4 in last drop.");
        } // if
        if (game.getPhase() != GamePhase.OVER) {
            throw new AssertionError("Fail: isLastDropConnectFour should set the game"
                                     + " phase to OVER.");
        } // if
        System.out.println("Pass: isLastDropConnectFour returned true and game phase"
                           + " is set to OVER since player 0 made c4 in the last drop.");
    } // testGamePlayCase02

    /**
     * When a player makes a connect four from their last dropped token,
     * isLastDropConnectFour should return true.
     */
    public static void testGamePlayCase03() {
        ConnectFour game = new ConnectFour(7, 8);
        game.setPlayerTokens(Token.RED, Token.BLUE);
        game.dropToken(0, 0); // player 0 drops at col 0.
        game.dropToken(1, 0); // player 1 drops at col 0.
        game.dropToken(0, 1); // player 0 drops at col 1.
        game.dropToken(1, 1); // player 1 drops at col 1.
        game.dropToken(0, 2); // player 0 drops at col 2.
        game.dropToken(1, 2); // player 1 drops at col 2.
        game.dropToken(0, 3); // player 0 drops at col 3.

        // Player 0 made a connect four with the last drop.
        if (!game.isLastDropConnectFour()) {
            throw new AssertionError("Fail: isLastDropConnectFour should return true"
                                     + " since player 0 made c4 in last drop.");
        } // if
        if (game.getPhase() != GamePhase.OVER) {
            throw new AssertionError("Fail: isLastDropConnectFour should set the game"
                                     + " phase to OVER.");
        } // if
        System.out.println("Pass: isLastDropConnectFour returned true and game phase"
                           + " is set to OVER since player 0 made c4 in the last drop.");

    }

    /**
     * When a player makes a connect four from their last dropped token,
     * isLastDropConnectFour should return true.
     */
    public static void testGamePlayCase04() {
        ConnectFour game = new ConnectFour(6, 7);
        game.setPlayerTokens(Token.RED, Token.BLUE);
        game.dropToken(0, 0); // player 0 drops at col 0.
        game.dropToken(1, 0); // player 1 drops at col 0.
        game.dropToken(0, 0); // player 0 drops at col 0.
        game.dropToken(1, 0); // player 1 drops at col 0.
        game.dropToken(0, 1); // player 0 drops at col 1.
        game.dropToken(1, 3); // player 1 drops at col 3.
        game.dropToken(0, 2); // player 0 drops at col 2.
        game.dropToken(1, 2); // player 1 drops at col 2.
        game.dropToken(0, 1); // player 0 drops at col 1.
        game.dropToken(1, 1); // player 1 drops at col 1.

        // Player 1 made a connect four with the last drop.
        if (!game.isLastDropConnectFour()) {
            throw new AssertionError("Fail: isLastDropConnectFour should return true"
                                     + " since player 1 made c4 in last drop.");
        } // if
        if (game.getPhase() != GamePhase.OVER) {
            throw new AssertionError("Fail: isLastDropConnectFour should set the game"
                                     + " phase to OVER.");
        } // if
        System.out.println("Pass: isLastDropConnectFour returned true and game phase"
                           + " is set to OVER since player 1 made c4 in the last drop.");

    }

    /**
     * When a player makes a connect four from their last dropped token,
     * isLastDropConnectFour should return true.
     */
    public static void testGamePlayCase05() {
        ConnectFour game = new ConnectFour(6, 7);
        game.setPlayerTokens(Token.RED, Token.BLUE);
        game.dropToken(0, 3); // player 0 drops at col 3.
        game.dropToken(1, 0); // player 1 drops at col 0.
        game.dropToken(0, 3); // player 0 drops at col 3.
        game.dropToken(1, 4); // player 1 drops at col 4.
        game.dropToken(0, 3); // player 0 drops at col 3.
        game.dropToken(1, 1); // player 1 drops at col 1.
        game.dropToken(0, 5); // player 0 drops at col 5.
        game.dropToken(1, 0); // player 1 drops at col 0.
        game.dropToken(0, 5); // player 0 drops at col 5.
        game.dropToken(1, 4); // player 1 drops at col 4.
        game.dropToken(0, 1); // player 1 drops at col 1.
        game.dropToken(1, 1); // player 1 drops at col 1.
        game.dropToken(0, 3); // player 1 drops at col 3.

        // Player 0 made a connect four with the last drop.
        if (!game.isLastDropConnectFour()) {
            throw new AssertionError("Fail: isLastDropConnectFour should return true"
                                     + " since player 0 made c4 in last drop.");
        } // if
        if (game.getPhase() != GamePhase.OVER) {
            throw new AssertionError("Fail: isLastDropConnectFour should set the game"
                                     + " phase to OVER.");
        } // if
        System.out.println("Pass: isLastDropConnectFour returned true and game phase"
                           + " is set to OVER since player 0 made c4 in the last drop.");

    }

} // ConnectFourTester
