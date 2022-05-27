package RSP;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KC Lee
 */
public interface RSPPlayable {
    
    public static final int ROCK = 0;
    public static final int Scissors = 1;
    public static final int Paper = 2;

	public static final int RowPlayerMode = 0;
	public static final int ColumnPlayerMode = 1;
    
    /**
     * set the gain matrix for the row-side player
     * @return 
     */
    public void setGain(float[][] gainMatrix);

	
	/**
     * Return your name or own group name
     * @return your name or own group name
     */
    public String getYourGroupName();
    
    /**
     * Return your move for the current turn
     * among ROCK (1), Scissors (2), and Paper (3).
	 * playermode is whether your position is either RowPlayerMode or ColumnPlayerMode.
     * @return your move for the current round
     */
    public int getUserMove(int playermode);
    

	/**
     * Remember the opponent's move in addition to your current move.
     * You might want to use the information to enhance the logic of deciding your move later.
     * @return 
     */
    public void rememberOpponentMove(int oppmove, int yourmove);


}
