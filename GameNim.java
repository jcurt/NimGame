import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class GameNim extends Game {
	
	//char marks[] = {'O', 'X'}; //'O' for computer, 'X' for human
	
    //int winningLines[][] = {
    int winningCoin = 1; 
    
    int WinningScore = 10;
    int LosingScore = -10;
    int NeutralScore = 0;    
    
    public GameNim() {
    	currentState = new StateNim();
    }
    
    public boolean isWinState(State state)
    {
        StateNim tstate = (StateNim) state;
        //player who did the last move
        int previous_player = (state.player==0 ? 1 : 0);  
        //char mark = marks[previous_player];
        
        // for (int i = 0; i < winningLines.length; i++) {
        	
        //     if (mark == tstate.board[winningLines[i][0]]
        //      && mark == tstate.board[winningLines[i][1]]
        //      && mark == tstate.board[winningLines[i][2]]) {
                
        //     	return true;
        //     }
        // }
        if (tstate.num == 1) {
            return true;
        }

        return false;
    }
    
    public boolean isStuckState(State state) {
    	
        if (isWinState(state)) 
            return false;
        
        StateNim tstate = (StateNim) state;
        
        // for (int i=1; i<=9; i++) 
        //     if ( tstate.board[i] == ' ' ) 
        //         return false;
        
        if (tstate.num > 0) {
            return false;
        }

        return true;
    }
	
	
	public Set<State> getSuccessors(State state)
    {
		if(isWinState(state) || isStuckState(state))
			return null;
		
		Set<State> successors = new HashSet<State>();
        StateNim tstate = (StateNim) state;
        
        StateNim successor_state;
        
        // char mark = 'O';
        // if (tstate.player == 1) //human
        //     mark = 'X';
        
        // for (int i = 1; i <= 9; i++) {
        //     if (tstate.board[i] == ' ') {
        //         successor_state = new StateTicTacToe(tstate);
        //         successor_state.board[i] = mark;
        //         successor_state.player = (state.player==0 ? 1 : 0); 
                
        //         successors.add(successor_state);
        //     }
        // }

        if (tstate.num >= 4) {
            successor_state = new StateNim(tstate);
            successor_state.num-=1;
            successor_state.player = (state.player==0 ? 1 : 0); 
            successors.add(successor_state);

            successor_state = new StateNim(tstate);
            successor_state.num-=2;
            successor_state.player = (state.player==0 ? 1 : 0); 
            successors.add(successor_state);

            successor_state = new StateNim(tstate);
            successor_state.num-=3;
            successor_state.player = (state.player==0 ? 1 : 0); 
            successors.add(successor_state);

        } else if (tstate.num == 3) {

            successor_state = new StateNim(tstate);
            successor_state.num-=1;
            successor_state.player = (state.player==0 ? 1 : 0); 
            successors.add(successor_state);

            successor_state = new StateNim(tstate);
            successor_state.num-=2;
            successor_state.player = (state.player==0 ? 1 : 0); 
            successors.add(successor_state);

        } else if (tstate.num == 2) {
            successor_state = new StateNim(tstate);
            successor_state.num-=1;
            successor_state.player = (state.player==0 ? 1 : 0); 
            successors.add(successor_state);
        }



    
        return successors;
    }	
    
    public double eval(State state) 
    {   
    	if(isWinState(state)) {
    		//player who made last move
    		int previous_player = (state.player==0 ? 1 : 0);
    	
	    	if (previous_player==0) //computer wins
	            return WinningScore;
	    	else //human wins
	            return LosingScore;
    	}

        return NeutralScore;
    }
    
    
    public static void main(String[] args) throws Exception {
        
        Game game = new GameNim(); 
        Search search = new Search(game);
        int depth = 6;
        
        //needed to get human's move
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
        	
        	StateNim 	nextState = null;
        	
            switch ( game.currentState.player ) {
              case 1: //Human
                  
            	  //get human's move
                  System.out.print("Enter your *valid* move (1,2,3)> ");
                  int pos = Integer.parseInt( in.readLine() );
            	  
                  nextState = new StateNim((StateNim)game.currentState);
                  nextState.player = 1;
                  nextState.num -= pos;
                  System.out.println("You took " +pos+". Coins Left: " + nextState);
                  break;
                  
              case 0: //Computer
            	  
            	  nextState = (StateNim)search.bestSuccessorState(depth);
            	  nextState.player = 0;
            	  System.out.println("Computer took however many to reach " + nextState + " coins.");
                  break;
            }
                        
            game.currentState = nextState;
            //change player
            game.currentState.player = (game.currentState.player==0 ? 1 : 0);
            
            //Who wins?
            if ( game.isWinState(game.currentState) ) {
            
            	if (game.currentState.player == 1) //i.e. last move was by the computer
            		System.out.println("You are left with the last coin, which you must take in your turn! Computer wins!");
            	else
            		System.out.println("You win!");
            	
            	break;
            }
            
            if ( game.isStuckState(game.currentState) ) { 
            	System.out.println("Cat's game!");
            	break;
            }
        }
    }
}