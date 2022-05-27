package RSP;


import RSP.RSPPlayable;
import java.io.*;
import java.util.*;


/**
 *
 * @author KC Lee
 */
public class RSPTester {

    private static Properties prop;

	public static int HowManyRuns = 10000;
	public static String player1fullname;
	public static String player2fullname;
    public static boolean verbose = false;
	public static float[][] gainmatrix = myutil.GenerateGainMatrix.generateGainCentered();

    public static void main(String[] args) {

        int player1Move, player2Move;
        float tempGain;
		float rspPlayer1Gain = 0.0f, rspPlayer2Gain= 0.0f;

		
		//read information of two teams and the setting
		readProperyFile();		
    
		//If config.properties is not properly set, we use the following setting
		if ( prop == null )
		{
			if ( args.length > 1) {
				player1fullname = args[0];
				player2fullname = args[1];
			}
		} else {
			if ( player1fullname == null || player2fullname == null )
			{
				player1fullname = "GroupFairtest.FairtestRSP";
				player2fullname = "GroupRandomtest.RandomtestRSP";
			}
		}

        RSPPlayable rspPlayer1;
        RSPPlayable rspPlayer2;
               
        try {
            rspPlayer1 = createRSPPlayable(player1fullname);
            rspPlayer2 = createRSPPlayable(player2fullname);
			rspPlayer1.setGain(gainmatrix);
			rspPlayer2.setGain(gainmatrix);
        } catch (Exception e) {
            System.out.println("Cannot perform the test!!!");
            e.printStackTrace();            
            return;
        }        


        
        System.out.println("Welcome to Generalized ROCK-SCISSORS-PAPER!!");
        System.out.println("This game plays "+ HowManyRuns + " rounds.");		
		System.out.println("The gain matrix for the row-side player is as follows:");

		myutil.GenerateGainMatrix.display(gainmatrix);

        // Loop once for each round of the game.
        for (int rnd = 1; rnd <= RSPTester.HowManyRuns; rnd++) {

            // Get the moves for this round.
			int player1Mode = ((rnd+1) %2);
			int player2Mode = (rnd %2);
            player1Move = rspPlayer1.getUserMove( player1Mode );
            player2Move = rspPlayer2.getUserMove( player2Mode );
            
            if (verbose) {
				System.out.println("[ROUND-" + rnd + "]");
				System.out.println("[Player 1]" + rspPlayer1.getYourGroupName() + "'s move: " + getMoveName(player1Move));
				System.out.println("[Player 2]" + rspPlayer2.getYourGroupName() + "'s move: " + getMoveName(player2Move));
            }

            // Determine the winner of this round.
			if (verbose)
			{
				System.out.println("player1Move:" + player1Move + ", player2Move:" + player2Move + ", player1Mode:" + player1Mode + ", player2Mode:" + player2Mode);
			}
            tempGain = determineRoundGain(player1Move, player2Move, player1Mode, player2Mode);

			if (player1Mode == RSPPlayable.RowPlayerMode )
			{
				rspPlayer1Gain = rspPlayer1Gain + tempGain;
				rspPlayer2Gain = rspPlayer2Gain - tempGain;
                if (verbose) {
                    System.out.println("[Player 1]" + rspPlayer1.getYourGroupName() + " earned " + tempGain);
				}
                if (verbose) {
                    System.out.println("[Player 2]" + rspPlayer2.getYourGroupName() + " earned " + (-1)*tempGain);
				}
			} else {
				rspPlayer1Gain = rspPlayer1Gain - tempGain;
				rspPlayer2Gain = rspPlayer2Gain + tempGain;
                if (verbose) {
                    System.out.println("[Player 1]" + rspPlayer1.getYourGroupName() + " earned " + (-1)*tempGain);
				}
                if (verbose) {
                    System.out.println("[Player 2]" + rspPlayer2.getYourGroupName() + " earned " + tempGain);
				}
			}

            if (verbose){
				System.out.println("--------------------");

				System.out.println("[Cumulative Score]");
				System.out.println("[Player 1]"+ rspPlayer1.getYourGroupName() +"=" + rspPlayer1Gain);
				System.out.println("[Player 2]"+ rspPlayer2.getYourGroupName() +"=" + rspPlayer2Gain);
				System.out.println();
			}
            
            rspPlayer1.rememberOpponentMove(player2Move, player1Move);
            rspPlayer2.rememberOpponentMove(player1Move, player2Move);
        }

			System.out.println("----------------------------");			
			System.out.println("Total Rounds=" + RSPTester.HowManyRuns);

            System.out.println("[Final Score]");
			System.out.println("[Player 1]"+ rspPlayer1.getYourGroupName() +"=" + rspPlayer1Gain);
			System.out.println("[Player 2]"+ rspPlayer2.getYourGroupName() +"=" + rspPlayer2Gain);
            System.out.println();

    }

	private static void readProperyFile()
	{
		prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			// get the property values
			player1fullname = prop.getProperty("player1");
			player2fullname = prop.getProperty("player2");
			HowManyRuns =  Integer.parseInt(prop.getProperty("HowManyRuns"));
			verbose = Boolean.parseBoolean(prop.getProperty("verbose"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}



    /**
	* Change move numbers to appropriate string values
	* @return The appropriate string values for the moveNumber
	*/
    public static String getMoveName(int moveNumber) {
        String moveName = "";
        switch (moveNumber) {
            case RSPPlayable.ROCK:
                moveName = "Rock";
                break;
            case RSPPlayable.Scissors:
                moveName = "Scissors";
                break;
            case RSPPlayable.Paper:
                moveName = "Paper";
                break;
        }
        return moveName;
    }




    /**
     * Compare player 1's move to player 2's move to determine the gain of
     * this round.
     *
     * @param player1Move the player 1's move.
     * @param player2Move the player 2's move.
	 * @param player1Mode the mode of player 1: RSPPlayable.RowPlayerMode or RSPPlayable.ColumnPlayerMode
     * @return 1 if the computer wins. 0 if it is a tie. -1 if the user wins.
     */
    static float determineRoundGain(int player1Move,
            int player2Move, int player1Mode, int player2Mode) {
        if (player1Mode == RSPPlayable.RowPlayerMode)
        {
			return gainmatrix[player1Move][player2Move];
        } else {
			return gainmatrix[player2Move][player1Move];
		}
    }

    
    private static RSPPlayable createRSPPlayable(String fullPackageClassname) throws Exception {
        Object object = null;
        Class classDefinition = Class.forName(fullPackageClassname);
        object = classDefinition.newInstance();
        return (RSPPlayable)object;
    }
}