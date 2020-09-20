import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Model class contain all the data and logic of the game
 */
public class Model {

	private int[] stoneInPit;
	private int[] history;
	private ArrayList<ChangeListener> listeners;
	private int player=1;
	private String winner;
	private int[] playerUndoCount;
	private boolean canUndo= false;
	private int prevPlayer = 2;

    /**
     * Constructor for Model
     */
	public Model() {
		listeners = new ArrayList<ChangeListener> ();
		stoneInPit = new int[14];
		history = new int[14];
		playerUndoCount = new int[]{-1,3,3};

	}

	/**
	 * add ChangeListener into the listener ArrayList
	 * @param ChangeListener c
     */
	public void addChangeListener(ChangeListener c) {
		listeners.add(c);
	}

    /**
     * Notify all the listener
     */
	public void changeListener() {
		for (ChangeListener l : listeners)
			l.stateChanged(new ChangeEvent(this));
	}

	/**
     * Set the stone in each pits
     * @param int stoneNum
     */
	public void setStoneNumber(int stoneNum) {
		for (int i = 0; i<14;i++) {
			if (i==6 || i==13) {
				stoneInPit[i]= 0;
			}else {
			stoneInPit[i]= stoneNum;
			}
		}
		for (int i = 0; i<14;i++) {
			history[i] = stoneInPit[i];
		}
		changeListener();
    }

    /**
     * Save history of the pits
     */
	public void saveHistory() {
		for (int i=0;i<14;i++)
			history[i] = stoneInPit[i];
	}

    /**
     * Getter for stone in Pits
     * @param int pitNum
		 * @return int the stone in that particular
     */
	public int getStoneInPits(int pitNum) {
		return stoneInPit[pitNum];
    }

	/**
     * Getter for current player
		 * @return int player
     */
	public int getPlayer() {
		return player;
	}

	/**
     * Move function for player given the choosen pit
     * @param int chosenPit
     */
	public void play(int chosenPit) {

		// Set undo flag when player play only
        canUndo = true;

        // Set up variable
		int stoneNum = stoneInPit[chosenPit] ;
		int stoneNumCopy = stoneInPit[chosenPit] ;
		int chosenPitCopy = chosenPit;

		if(stoneNumCopy > 0) {
            stoneInPit[chosenPit] = 0;

                // Logic for moving the stone
				while (stoneNumCopy > 0) {
					chosenPitCopy++;
					if(player==1) {
						if (chosenPitCopy == 13) {
							chosenPitCopy = -1;
							continue;
						}
					}
					if(player==2) {
						if (chosenPitCopy == 13) {
							stoneNumCopy--;
							stoneInPit[chosenPitCopy] = stoneInPit[chosenPitCopy]+1 ;

							chosenPitCopy = -1;
							continue;

						}
						if (chosenPitCopy == 6) {
							continue;
						}
					}
					stoneInPit[chosenPitCopy] = stoneInPit[chosenPitCopy]+1 ;
					stoneNumCopy--;
                }

				if(chosenPitCopy==-1)
                    chosenPitCopy=13;

                // Check if the stone goes into the empty pit
				if (stoneInPit[chosenPitCopy] ==1 && chosenPitCopy !=6 && chosenPitCopy != 13) {
					if (player ==1 && chosenPitCopy<6) {
						stoneInPit[6] += stoneInPit[12-chosenPitCopy]+1;
						stoneInPit[chosenPitCopy]=0;
						stoneInPit[12-chosenPitCopy]=0;
					}
					if (player ==2 && chosenPitCopy>6) {
						stoneInPit[13] += stoneInPit[12-chosenPitCopy]+1;
						stoneInPit[chosenPitCopy]=0;
						stoneInPit[12-chosenPitCopy]=0;
					}
                }

                // Check if the last stone go into the mancala, set the corresponding player
				if(player == 1 ) {
					if (chosenPit+stoneNum == 19 || chosenPit+ stoneNum ==6 ) {
						prevPlayer = 1;
						player =1;
					}
					else {

						prevPlayer = 1;
						player =2;
					}
				}
				else {
					if (chosenPit+stoneNum == 26 || chosenPit+ stoneNum ==13 ) {
						prevPlayer = 2;
						player =2;
					}
					else {
						prevPlayer = 2;
						player =1;
					}
                }

                // Check if the player change update the count for undo
				if(player != prevPlayer)
                    playerUndoCount[player]=  3;

				// Notify the changelistener
				changeListener();
			}
	}

	/**
     * Check if the game is finish
     * @return boolean 
     */
	public boolean isFinish() {
		int sum1=0;
        int sum2=0;

		for(int i=0; i<6; i++ )
			sum1+= stoneInPit[i];
		for(int i=7; i<13; i++ )
            sum2+= stoneInPit[i];

        // Check winner condition and set the winner
		if (sum1==0) {
			for(int i=0;i<3;i++)
				playerUndoCount[i]=0;
			for(int i=7; i<13; i++ )
				stoneInPit[i]=0;
			stoneInPit[13]+=sum2;

			if(stoneInPit[13]>stoneInPit[6])
				winner = "the Winer is player B";
			if(stoneInPit[13]==stoneInPit[6])
				winner = "the game is tied";
			if(stoneInPit[13]<stoneInPit[6])
				winner = "the Winer is player A";
			changeListener();
			return true;
		}

		if (sum2==0) {
			for(int i=0;i<3;i++)
				playerUndoCount[i]=0;
			for(int i=0; i<6; i++ )
				stoneInPit[i]=0;
			stoneInPit[6]+=sum1;

			if(stoneInPit[13]>stoneInPit[6])
				winner = "the Winer is player B";
			if(stoneInPit[13]==stoneInPit[6])
				winner = "the game is tied";
			if(stoneInPit[13]<stoneInPit[6])
				winner = "the Winer is player A";

			changeListener();
			return true;
		}
		return false;
    }

	/**
     * Getter for the winner
     * @return winner
     */
	public String getWiner() {
		return winner;
    }

    /**
     * Undo function for undo button
     */
	public void undo() {

		if(canUndo && playerUndoCount[prevPlayer] > 0) {

			canUndo= false;
			playerUndoCount[prevPlayer] -=1;
			player = prevPlayer;

			for (int i =0; i<14; i++) {
				stoneInPit[i]=history[i];
			}
			changeListener();
		}
    }

    /**
     * Getter undo count
     * @return undo count
     */
	public int getUndoCount() {
		return playerUndoCount[player];
	}

}
