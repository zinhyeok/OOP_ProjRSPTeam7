package team7;

/**
 *
 * @author
 */

import RSP.*;

public class Team7 implements RSPPlayable {

    private String myGroupName = "team 7";

    public String getYourGroupName() {
        return myGroupName;
    }

    public int getUserMove(int playermode) {
        return Mylogic.decideRSP(playermode);
    }

    public void rememberOpponentMove(int oppmove, int yourmove) {
        Ar.setArray(oppmove);
        int a = 0, b = 0, c = 0, d = 0;
        int oppcount = 20;
        if (Ar.totalCount >= oppcount) {
            int[] temp = Ar.getRecentRSP(oppcount);
            for (int i = 2; i < oppcount; i++) {
                if (temp[i] == temp[i - 1]) {
                    a++;
                }
                else if (temp[i] == temp[i - 2]) {
                    b++;
                }
            }
            if (a == oppcount - 2) {
                c = temp[oppcount - 1];
                Mylogic.StrategyCol(RSPTester.gainmatrix, c);
                Mylogic.StrategyRow(RSPTester.gainmatrix, c);
            } else if (b == oppcount - 2) {
                int k = Ar.getEnemymode();
                if (k == 0) {
                    c = temp[0]; 
                    d = temp[1]; 
                }
                else if (k == 1) {
                    c = temp[1]; 
                    d = temp[0]; 
                }
                Mylogic.StrategyCol(RSPTester.gainmatrix, c); 
                Mylogic.StrategyRow(RSPTester.gainmatrix, d); 
            }
            else {
                Mylogic.calculateGain(RSPTester.gainmatrix);
            }
        }
    }

    public void setGain(float[][] gainMatrix) {
        Mylogic.calculateGain(gainMatrix);
    }
}