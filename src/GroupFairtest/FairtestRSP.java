package GroupFairtest;

import RSP.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KC Lee
 */
public class FairtestRSP implements RSPPlayable {
    private String myGroupName = "Uniform Tester";

    @Override
    public String getYourGroupName() {
        return myGroupName;
    }

    @Override
    public int getUserMove(int playermode) {
        int high = 2;
        int low = 0;
        int myRandnum = (int) (Math.random() * (high - low + 1));
        return myRandnum;
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
