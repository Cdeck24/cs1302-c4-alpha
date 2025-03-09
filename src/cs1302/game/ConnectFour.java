package cs1302.game;

import cs1302.gameutil.GamePhase;
import cs1302.gameutil.Token;
import cs1302.gameutil.TokenGrid;

/**
 * {@code ConnectFour} represents a two-player connection game involving a two-dimensional grid of
 * {@linkplain cs1302.gameutil.Token tokens}. When a {@code ConnectFour} game object is
 * constructed, several instance variables representing the game's state are initialized and
 * subsequently accessible, either directly or indirectly, via "getter" methods. Over time, the
 * values assigned to these instance variables should change so that they always reflect the
 * latest information about the state of the game. Most of these changes are described in the
 * project's <a href="https://github.com/cs1302uga/cs1302-c4-alpha#functional-requirements">
 * functional requirements</a>.
 */
public class ConnectFour {

    //----------------------------------------------------------------------------------------------
    // INSTANCE VARIABLES: You should NOT modify the instance variable declarations below.
    // You should also NOT add any additional instance variables. Static variables should
    // also NOT be added.
    //----------------------------------------------------------------------------------------------

    private int rows;        // number of grid rows
    private int cols;        // number of grid columns
    private Token[][] grid;  // 2D array of tokens in the grid
    private Token[] player;  // 1D array of player tokens (length 2)
    private int numDropped;  // number of tokens dropped so far
    private int lastDropRow; // row index of the most recent drop
    private int lastDropCol; // column index of the most recent drop
    private GamePhase phase; // current game phase

    //----------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    //----------------------------------------------------------------------------------------------

    /**
     * Constructs a {@link cs1302.game.ConnectFour} game with a grid that has {@code rows}-many
     * rows and {@code cols}-many columns. All of the game's instance variables are expected to
     * be initialized by this constructor as described in the project's
     * <a href="https://github.com/cs1302uga/cs1302-c4-alpha#functional-requirements">functional
     * requirements</a>.
     *
     * @param rows the number of grid rows
     * @param cols the number of grid columns
     * @throws IllegalArgumentException if the value supplied for {@code rows} or {@code cols} is
     *     not supported. The following values are supported: {@code 6 <= rows <= 9} and
     *     {@code 7 <= cols <= 9}.
     */
    public ConnectFour(int rows, int cols)  {

        if (rows < 6 || rows > 9) {
            throw new IllegalArgumentException ("Rows must be between 6 and 9 inclusive.");
        }

        if (cols < 7 || cols > 9) {
            throw new IllegalArgumentException ("Columns must be between 7 and 9 inclusive.");
        }

        this.rows = rows;
        this.cols = cols;
        grid = new Token[rows][cols];
        player = new Token[2];
        numDropped = 0;
        lastDropRow = -1;
        lastDropCol = -1;
        this.phase = GamePhase.NEW;
    } // ConnectFour

    //----------------------------------------------------------------------------------------------
    // INSTANCE METHODS
    //----------------------------------------------------------------------------------------------

    /**
     * Return the number of rows in the game's grid.
     *
     * @return the number of rows
     */
    public int getRows() {
        return this.rows;
    } // getRows

    /**
     * Return the number of columns in the game's grid.
     *
     * @return the number of columns
     */
    public int getCols() {
        return this.cols;
    } // getCols

    /**
     * Return whether {@code row} and {@code col} specify a location inside this game's grid.
     *
     * @param row the position's row index
     * @param col the positions's column index
     * @return {@code true} if {@code row} and {@code col} specify a location inside this game's
     *     grid and {@code false} otherwise
     */
    public boolean isInBounds(int row, int col) {
        return (row >= 0 && row < this.rows) && (col >= 0 && col < this.cols);
    } // isInBounds

    /**
     * Return the grid {@linkplain cs1302.gameutil.Token token} located at the specified position
     * or {@code null} if no token has been dropped into that position.
     *
     * @param row the token's row index
     * @param col the token's column index
     * @return the grid token located in row {@code row} and column {@code col}, if it exists;
     *     otherwise, the value {@code null}
     * @throws IndexOutOfBoundsException if {@code row} and {@code col} specify a position that is
     *     not inside this game's grid.
     */
    public Token getTokenAt(int row, int col) {
        if (!isInBounds(row, col)) {
            throw new IndexOutOfBoundsException("Position (" + row + ", " + col +
                                                ") is out of bounds.");
        }
        return this.grid[row][col];
    } // getTokenAt

    /**
     * Set the first player token and second player token to {@code token0} and {@code token1},
     * respectively. If the current game phase is {@link cs1302.gameutil.GamePhase#NEW}, then
     * this method changes the game phase to {@link cs1302.gameutil.GamePhase#READY}, but only
     * if no exceptions are thrown.
     *.
     * @param token0 token for first player
     * @param token1 token for second player
     * @throws NullPointerException if {@code token0} or {@code token1} is {@code null}.
     * @throws IllegalArgumentException if {@code token0 == token1}.
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#PLAYABLE} or {@link cs1302.gameutil.GamePhase#OVER}.
     */
    public void setPlayerTokens(Token token0, Token token1) {
        if (token0 == null || token1 == null) {
            throw new NullPointerException ("Player tokens cannot be null.");
        }
        if (token0.equals(token1)) {
            throw new IllegalArgumentException ("Player tokens must be different.");
        }
        if (this.phase == GamePhase.PLAYABLE || this.phase == GamePhase.OVER) {
            throw new IllegalStateException ("The game has already started/ended.");
        }

        player[0] = token0;
        player[1] = token1;

        if (this.phase == GamePhase.NEW) {
            this.phase = GamePhase.READY;
        }
    } // setPlayerTokens

    /**
     * Return a player's token.
     *
     * @param player the player ({@code 0} for first player and {@code 1} for second player)
     * @return the token for the specified player
     * @throws IllegalArgumentException if {@code player} is neither {@code 0} nor {@code 1}
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW}.
     */
    public Token getPlayerToken(int player) {
        if (player != 0 && player != 1) {
            throw new IllegalArgumentException ("The player doesn't exist.");
        }
        if (this.phase == GamePhase.NEW) {
            throw new IllegalStateException ("The game hasn't been created.");
        }

        return this.player[player];

    } // getPlayerToken

    /**
     * Return the number of tokens that have been dropped into this game's grid so far.
     *
     * @return the number of dropped tokens
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW} or {@link cs1302.gameutil.GamePhase#READY}.
     */
    public int getNumDropped() {

        if (this.phase == GamePhase.NEW || this.phase == GamePhase.READY) {
            throw new IllegalStateException ("No tokens have been dropped yet.");
        }

        return this.numDropped;
    } // getNumDropped

    /**
     * Return the row index of the last (i.e., the most recent) token dropped into this
     * game's grid.
     *
     * @return the row index of the last drop
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW} or {@link cs1302.gameutil.GamePhase#READY}.
     */
    public int getLastDropRow() {

        if (this.phase == GamePhase.NEW || this.phase == GamePhase.READY) {
            throw new IllegalStateException ("No tokens have been dropped yet.");
        }

        return this.lastDropRow;
    } // getLastDropRow

    /**
     * Return the col index of the last (i.e., the most recent) token dropped into this
     * game's grid.
     *
     * @return the column index of the last drop
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW} or {@link cs1302.gameutil.GamePhase#READY}.
     */
    public int getLastDropCol() {

        if (this.phase == GamePhase.NEW || this.phase == GamePhase.READY) {
            throw new IllegalStateException ("No tokens have been dropped yet.");
        }

        return this.lastDropCol;
    } // getLastDropCol

    /**
     * Return the current game phase.
     *
     * @return current game phase
     */
    public GamePhase getPhase() {
        return this.phase;
    } // getPhase

    /**
     * Drop a player's token into a specific column in the grid. This method should not enforce turn
     * order -- that is the players' responsibility should they desire an polite and honest game.
     *
     * @param player the player ({@code 0} for first player and {@code 1} for second player)
     * @param col the grid column where the token will be dropped
     * @throws IndexOutOfBoundsException if {@code col} is not a valid column index
     * @throws IllegalArgumentException if {@code player} is neither {@code 0} nor {@code 1}
     * @throws IllegalStateException if {@link #getPhase getPhase()} does not return
     *    {@link cs1302.gameutil.GamePhase#READY} or {@link cs1302.gameutil.GamePhase#PLAYABLE}
     * @throws IllegalStateException if the specified column in the grid is full
     */
    public void dropToken(int player, int col) {
        if (col < 0 || col >= this.cols) {
            throw new IndexOutOfBoundsException ("Column index out of bounds: " + col);
        }
        if (player != 0 && player != 1) {
            throw new IllegalArgumentException ("The player doesn't exist.");
        }
        if (this.phase != GamePhase.READY && this.phase != GamePhase.PLAYABLE) {
            throw new IllegalStateException ("Cannot drop token while game is in "
                                             +  "READY or PLAYABLE phase");
        }
        if (this.grid[0][col] != null) {
            throw new IllegalStateException ("Column " + col + " is full.");
        }

        int row = this.rows - 1;
        while (row >= 0 && this.grid[row][col] != null) {
            row--;
        }

        this.grid[row][col] = this.player[player];
        this.numDropped++;
        this.lastDropRow = row;
        this.lastDropCol = col;

        if (this.phase == GamePhase.READY) {
            this.phase = GamePhase.PLAYABLE;
        }
    } // dropToken

    /**
     * Return {@code true} if the last token dropped via {@link #dropToken} created a
     * <em>connect four</em>. A <em>connect four</em> is a sequence of four equal tokens (i.e., they
     * have the same color) -- this sequence can occur horizontally, vertically, or diagonally.
     * If the grid is full or the last drop created a <em>connect four</em>, then this method
     * changes the game's phase to {@link cs1302.gameutil.GamePhase#OVER}.
     *
     * <p>
     * <strong>NOTE:</strong> The only instance variable that this method might change, if
     * applicable, is ``phase``.
     *
     * <p>
     * <strong>NOTE:</strong> If you want to use this method to determine a winner, then you must
     * call it after each call to {@link #dropToken}.
     *
     * @return {@code true} if the last token dropped created a <em>connect four</em>, else
     *     {@code false}
     */
    public boolean isLastDropConnectFour() {
        Token lastToken = this.grid[this.lastDropRow][this.lastDropCol];

        if (lastToken == null) {
            return false;
        }

        boolean win = false;

        //Horizontal check
        int count = 1;
        count += countTokensInDirection(lastToken, this.lastDropRow, this.lastDropCol, 0, -1);
        count += countTokensInDirection(lastToken, this.lastDropRow, this.lastDropCol, 0, 1);
        if (count >= 4) {
            win = true;
        } else {
            //Vertical check
            count = 1;
            count += countTokensInDirection(lastToken, this.lastDropRow, this.lastDropCol, -1, 0);
            count += countTokensInDirection(lastToken, this.lastDropRow, this.lastDropCol, 1, 0);
            if (count >= 4) {
                win = true;
            } else {
                //Diagonal check 1
                count = 1;
                count += countTokensInDirection(lastToken, this.lastDropRow, this.lastDropCol, -1,
                                                -1);
                count += countTokensInDirection(lastToken, this.lastDropRow, this.lastDropCol, 1,
                                                1);
                if (count >= 4) {
                    win = true;
                } else {
                    //Diagonal check 2
                    count = 1;
                    count += countTokensInDirection(lastToken, this.lastDropRow, this.lastDropCol,
                                                    -1, 1);
                    count += countTokensInDirection(lastToken, this.lastDropRow, this.lastDropCol,
                                                    1, -1);
                    if (count >= 4) {
                        win = true;
                    }
                }
            }
        }

        if (win || this.numDropped == this.rows * this.cols) {
            this.phase = GamePhase.OVER;
        }

        return win;
    } // isLastDropConnectFour

    //----------------------------------------------------------------------------------------------
    // ADDITIONAL METHODS: If you create any additional methods, then they should be placed in the
    // space provided below.
    //----------------------------------------------------------------------------------------------

    /**
     * Helper method that examines the dropped tokens in the forward and backward directions from
     * the starting point, determined by the dRow and dCol parameters that specify the steps to
     * check.
     *
     * @param token the token of the player that is being counted
     * @param startRow the starting row index in the grid
     * @param startCol the starting column index in the grid
     * @param dRow the change in row step used to check consecutive tokens
     * @param dCol the change in column step used to check consecutive tokens
     * @return {@code true} if four or more equal tokens are found in the given direction
     * else {@code false}
     */
    private int countTokensInDirection(Token token, int startRow, int startCol, int dRow,
                                       int dCol) {
        int count = 0;
        int row = startRow + dRow;
        int col = startCol + dCol;

        while (isInBounds(row, col) && this.grid[row][col] == token) {
            count++;
            row += dRow;
            col += dCol;
        }
        return count;
    }


    //----------------------------------------------------------------------------------------------
    // DO NOT MODIFY THE METHODS BELOW!
    //----------------------------------------------------------------------------------------------

    /**
     * <strong>DO NOT MODIFY:</strong>
     * Print the game grid to standard output. This method assumes that the constructor
     * is implemented correctly.
     *
     * <p>
     * <strong>NOTE:</strong> This method should not be modified!
     */
    public void printGrid() {
        TokenGrid.println(this.grid);
    } // printGrid

} // ConnectFour
