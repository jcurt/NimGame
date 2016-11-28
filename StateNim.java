/*
 * For any game, the stateT structure records the current state
 * of the game.  For tic-tac-toe, the main component of the
 * state record is the board, which is an array of characters
 * using 'X' for the first player, 'O' for the second, and ' '
 * for empty squares.  Although the board array is logically
 * a two-dimensional structure, it is stored as a linear array
 * so that its indices match the numbers used by the human
 * player to refer to the squares, as follows:
 *
 *        1 | 2 | 3
 *       ---+---+---
 *        4 | 5 | 6
 *       ---+---+---
 *        7 | 8 | 9
 *
 * Note that element 0 is not used, which requires allocation
 * of an extra element.
 *
 * In addition to the board array, the code stores a player
 * value to indicate whose turn it is.  
 */


public class StateNim extends State {
	
    //public char board[] = new char [(3 * 3) + 1];
    public int num;
    
    public StateNim() {
    	
        // program written to only suit to coins such that: (coin%4) = 1, i.e. 5,9,13,17,21,25,...
        // requires subtle changes to the code (to the computer's turn) when initial coin amount changes to neither of the amounts above,
        // in which case the computer may lose if the player plays perfectly.
        // In the current state of the game, i.e. coin=13, computer can never lose. Again, requires subtle changes if coin amount changes.
        
        num = 13;
        
        player = 1;
    }
    
    // public StateNim(StateTicTacToe state) {
    	
    //     for(int i=0; i<10; i++)
    //         this.board[i] = state.board[i]; 

    public StateNim(StateNim state) {
        
        this.num = state.num;

        player = state.player;
    }
    
    public String toString() {
    	
    	String ret = "" + num;
    	return ret;
    }
}
