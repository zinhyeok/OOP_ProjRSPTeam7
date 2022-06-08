package newTeam7;
import java.math.*;
public class Mylogic {
	
	static int rowRSP;
	static int colRSP;
	static int myCurrentplayermode;
	static float[][] mymatrix;
	static int moveCount;
	
	public static void save_moveCount(int Count){
		moveCount = Count;
	}
	public static int myRSP(int playermode){
		myCurrentplayermode = playermode;
		if (playermode == 0){
			return rowRSP;
		}
		//playermode ==1일때 즉, gainmatrix에서 -를 가져갈때 
		// gainmatrix에서 최소인 값을 챙겨야함 
		else{
			return colRSP;
		}
	}
	public static void saveMatrix(float[][] gainMatrix){
		mymatrix = gainMatrix;
	}
	
	
	//기본 전략
    public static void calculateGain(float[][] gainMatrix) {
    	//gainmatrix에서 값을 받아서 sum계산 -> 가장 높은 값을 선택
        float sumRow1 = gainMatrix[0][0] + gainMatrix[0][1] + gainMatrix[0][2];
        float sumRow2 = gainMatrix[1][0] + gainMatrix[1][1] + gainMatrix[1][2];
        float sumRow3 = gainMatrix[2][0] + gainMatrix[2][1] + gainMatrix[2][2];

        if (sumRow1 > sumRow2 && sumRow1 > sumRow3) {
        	rowRSP = 0;
        } else if (sumRow2 > sumRow1 && sumRow2 > sumRow3) {
        	rowRSP = 1;
        } else {
        	rowRSP = 2;
        }

        float sumCol1 = gainMatrix[0][0] + gainMatrix[1][0] + gainMatrix[2][0];
        float sumCol2 = gainMatrix[0][1] + gainMatrix[1][1] + gainMatrix[2][1];
        float sumCol3 = gainMatrix[0][2] + gainMatrix[1][2] + gainMatrix[2][2];

        if (sumCol1 < sumCol2 && sumCol1 < sumCol3) {
        	colRSP = 0;
        } else if (sumCol2 < sumCol1 && sumCol2 < sumCol3) {
        	colRSP = 1;
        } else {
        	colRSP = 2;
        }
    }
    public static void detectOpp(int[] Arr_oppmove){
    	//감지기준: Arr[0] == Arr[2]
    	//감지하면 handleDetect 호출
    	if(Arr_oppmove[0] == Arr_oppmove[2]){
    		handleDetect((int)Arr_oppmove[0], myCurrentplayermode);
    	}
    	else{
    		calculateGain(mymatrix);
    	}
    }
    public static void handleDetect(int oppmove, int myCurrentplayermode){
    	float maximum_gainvalue;
    	//감지해서 전략변경
    	//전략은? 상대것이 고정되었다고 판단했을때 가장 큰 gain값 가져오기 
    	if(myCurrentplayermode ==0){
    		for(int i=0; i<3; i++) {
	    		if(oppmove == i){
	    			maximum_gainvalue = Math.max(Math.max(mymatrix[0][i], mymatrix[1][i]), mymatrix[2][i]);
	    			if(mymatrix[0][i]== maximum_gainvalue){
	    				rowRSP = 0;
	    			}
	    			if(mymatrix[1][i]== maximum_gainvalue){
	    				rowRSP = 1;
	    			}
	    			if(mymatrix[2][i]== maximum_gainvalue){
	    				rowRSP = 2;
	    			}
	    		}
    		}
    	}
    	if(myCurrentplayermode ==1){
    		for(int i=0; i<3; i++) {
	    		if(oppmove == i){
	    			maximum_gainvalue = Math.min(Math.min(mymatrix[i][0], mymatrix[i][1]), mymatrix[i][2]);
	    			if(mymatrix[i][0]== maximum_gainvalue){
	    				colRSP = 0;
	    			}
	    			if(mymatrix[i][1]== maximum_gainvalue){
	    				colRSP = 1;
	    			}
	    			if(mymatrix[i][2]== maximum_gainvalue){
	    				colRSP = 2;
	    			}
	    		}
    		}
    	}
    }
}
