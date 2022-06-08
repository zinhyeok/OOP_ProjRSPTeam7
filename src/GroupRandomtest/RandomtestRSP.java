package GroupRandomtest;

import RSP.*;
import java.util.Random;
/**
 *
 * @author KC Lee
 */
public class RandomtestRSP implements RSPPlayable {
    private String myGroupName = "Multinomial Tester Group";
	myutil.Multinomial mymn;

    public RandomtestRSP() {
		int casenum = 2;
		double[] initprob = new double[3];

		if (casenum == 1)
		{
			initprob[0] = 0.3d;
			initprob[1] = 0.5d;
			initprob[2] = 0.7d;			
		} else {
			Random rn = new Random(System.currentTimeMillis());
			initprob[0] = rn.nextDouble();
			initprob[1] = rn.nextDouble();
			initprob[2] = rn.nextDouble();
		}
		

		mymn = new myutil.Multinomial(initprob);
	}


    @Override
    public String getYourGroupName() {
        return myGroupName;
    }

    @Override
    public int getUserMove(int playermode) {
        int myRandnum = mymn.sample(); //following a certain Multinomial distribution
        switch (myRandnum) {
            case 0:
                return RSPPlayable.ROCK;
            case 1:
                return RSPPlayable.Scissors;
            case 2:
                return RSPPlayable.Paper;
            default:
                return RSPPlayable.ROCK;
        }                        
    }

    @Override
    public void rememberOpponentMove(int oppmove, int yourmove) {
        //so far, it does nothing -_-;;
    }

	public void setGain(float[][] gainMatrix)
	{
		//so far, it does nothing -_-;;
	}
    
}
