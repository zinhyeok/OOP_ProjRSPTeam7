/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team7;

/**
 *
 * @author
 */
public class Mylogic {
    
    static int rowdetermine; 
    static int coldetermine; 
    
    public static void calculateGain(float[][] gainMatrix) {
        float row1 = gainMatrix[0][0] + gainMatrix[0][1] + gainMatrix[0][2];
        float row2 = gainMatrix[1][0] + gainMatrix[1][1] + gainMatrix[1][2];
        float row3 = gainMatrix[2][0] + gainMatrix[2][1] + gainMatrix[2][2];

        if (row1 > row2 && row1 > row3) {
            rowdetermine = 0;
        } else if (row2 > row1 && row2 > row3) {
            rowdetermine = 1;
        } else {
            rowdetermine = 2;
        }

        float col1 = gainMatrix[0][0] + gainMatrix[1][0] + gainMatrix[2][0];
        float col2 = gainMatrix[0][1] + gainMatrix[1][1] + gainMatrix[2][1];
        float col3 = gainMatrix[0][2] + gainMatrix[1][2] + gainMatrix[2][2];

        if (col1 < col2 && col1 < col3) {
            coldetermine = 0;
        } else if (col2 < col1 && col2 < col3) {
            coldetermine = 1;
        } else {
            coldetermine = 2;
        }
    }
    
    
    public static void StrategyCol(float[][] gainMatrix, int opprow) {

        if (gainMatrix[opprow][0] < gainMatrix[opprow][1] && gainMatrix[opprow][0] < gainMatrix[opprow][2]) {
            coldetermine = 0;
        } else if (gainMatrix[opprow][1] < gainMatrix[opprow][0] && gainMatrix[opprow][1] < gainMatrix[opprow][2]) {
            coldetermine = 1;
        } else if (gainMatrix[opprow][2] < gainMatrix[opprow][0] && gainMatrix[opprow][2] < gainMatrix[opprow][1]) {
            coldetermine = 2;
        }
    }
    
    
    public static void StrategyRow(float[][] gainMatrix, int oppcol) {
        
        if (gainMatrix[0][oppcol] > gainMatrix[1][oppcol] && gainMatrix[0][oppcol] > gainMatrix[2][oppcol]) {
            rowdetermine = 0;
        } else if (gainMatrix[1][oppcol] > gainMatrix[0][oppcol] && gainMatrix[1][oppcol] > gainMatrix[2][oppcol]) {
            rowdetermine = 1;
        } else if (gainMatrix[2][oppcol] > gainMatrix[0][oppcol] && gainMatrix[2][oppcol] > gainMatrix[1][oppcol]) {
            rowdetermine = 2;
        }
    }
    
    
    public static int decideRSP(int playermode) {
        Ar.rememberEnemymode(playermode);
        if (playermode == 0) {
            return rowdetermine;

        } else if (playermode == 1) {
            return coldetermine;

        }
        return -1; //no meaning
    }
}