package Team7;
import java.math.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Mylogic {
	
	static int rowRSP;
	static int colRSP;
	static int myCurrentplayermode;
	static float[][] mymatrix;
	static int moveCount;
	
	static int OddmoveCount =0;
	static int EvenmoveCount =0;
	static int[] Arr_oppOdd = new int [5];
	static int[] Arr_oppEven = new int [5];
	
	public static void save_moveCount(int Count){
		moveCount = Count;
	}
	public static int myRSP(int playermode){
		myCurrentplayermode = playermode;
//		if(moveCount%100 == 0 || moveCount%101 == 0){
//			RandomRSP();
//		}
		if (playermode == 0){
			return rowRSP;
		}
		//playermode ==1�씪�븣 利�, gainmatrix�뿉�꽌 -瑜� 媛��졇媛덈븣 
		// gainmatrix�뿉�꽌 理쒖냼�씤 媛믪쓣 梨숆꺼�빞�븿 
		else{
			return colRSP;
		}
	}
	
	public static void saveMatrix(float[][] gainMatrix){
		mymatrix = gainMatrix;
	}
	
	
	//湲곕낯 �쟾�왂
    public static void calculateGain(float[][] gainMatrix) {
    	//gainmatrix�뿉�꽌 媛믪쓣 諛쏆븘�꽌 sum怨꾩궛 -> 媛��옣 �넂�� 媛믪쓣 �꽑�깮
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
    	//媛먯�湲곗�: Arr[0] == Arr[2]
    	//媛먯��븯硫� handleDetect �샇異�
    	if(Arr_oppmove[0] == Arr_oppmove[2]){
    		handleDetect((int)Arr_oppmove[0], myCurrentplayermode);
    	}
    	else if(OddmoveCount>4){
    		detectOppOdd(Arr_oppOdd);
    	}
    	else if(EvenmoveCount>4){
    		detectOppEven(Arr_oppEven);
    	}
    	else{
    		calculateGain(mymatrix);
    	}
    }
    
    public static void CountOppOdd(int oppmove){
    	//紐뉙뙋 湲곗��쑝濡� �솗�씤�븷寃껋씤吏�
    	if(OddmoveCount==0){
    		Arr_oppOdd[0] = oppmove;
    		}
    	if(OddmoveCount==1){
    		Arr_oppOdd[1] = oppmove;
    	}
    	if(OddmoveCount==2){
    		Arr_oppOdd[2] = oppmove;
    	}
    	if(OddmoveCount==3){
    		Arr_oppOdd[3] = oppmove;
    	}
    	if(OddmoveCount==4){
    		Arr_oppOdd[4] = oppmove;
    	}
    	if(OddmoveCount>4){
    		Arr_oppOdd[0] = Arr_oppOdd[1];
    		Arr_oppOdd[1] = Arr_oppOdd[2];	
    		Arr_oppOdd[2] = Arr_oppOdd[3];
    		Arr_oppOdd[3] = Arr_oppOdd[4];
    		Arr_oppOdd[4] = oppmove;
    	}
    	if(OddmoveCount>=5) {
      	Mylogic.detectOpp(Arr_oppOdd);
    	}
    	OddmoveCount++;
    }
    
    
    public static void detectOppOdd(int[] Arr_oppOdd){
    	int rockCount = Collections.frequency(Arrays.asList(Arr_oppOdd), 0);
    	int scissorsCount = Collections.frequency(Arrays.asList(Arr_oppOdd), 1);
    	int paperCount = Collections.frequency(Arrays.asList(Arr_oppOdd), 2);
    	
    	if(rockCount >= 3){
    		handleDetect(0, myCurrentplayermode);
    	}
    	else if(scissorsCount >= 3){
    		handleDetect(1, myCurrentplayermode);
    	}
    	else if(paperCount >= 3){
    		handleDetect(2, myCurrentplayermode);
    	}
    	else{
    		calculateGain(mymatrix);
    	}
    }
    
    public static void CountOppEven(int oppmove){
    	//紐뉙뙋 湲곗��쑝濡� �솗�씤�븷寃껋씤吏�
    	if(EvenmoveCount==0){
    		Arr_oppEven[0] = oppmove;
    		}
    	if(EvenmoveCount==1){
    		Arr_oppEven[1] = oppmove;
    	}
    	if(EvenmoveCount==2){
    		Arr_oppEven[2] = oppmove;
    	}
    	if(EvenmoveCount==3){
    		Arr_oppEven[3] = oppmove;
    	}
    	if(EvenmoveCount==4){
    		Arr_oppEven[4] = oppmove;
    	}
    	if(EvenmoveCount>4){
    		Arr_oppEven[0] = Arr_oppEven[1];
    		Arr_oppEven[1] = Arr_oppEven[2];	
    		Arr_oppEven[2] = Arr_oppEven[3];
    		Arr_oppEven[3] = Arr_oppEven[4];
    		Arr_oppEven[4] = oppmove;
    	}
    	if(EvenmoveCount>=5) {
      	Mylogic.detectOpp(Arr_oppEven);
    	}
    	EvenmoveCount++;
    }
    
    
    public static void detectOppEven(int[] Arr_oppEven){
    	int rockCount = Collections.frequency(Arrays.asList(Arr_oppEven), 0);
    	int scissorsCount = Collections.frequency(Arrays.asList(Arr_oppEven), 1);
    	int paperCount = Collections.frequency(Arrays.asList(Arr_oppEven), 2);
    	
    	if(rockCount >= 3){
    		handleDetect(0, myCurrentplayermode);
    	}
    	else if(scissorsCount >= 3){
    		handleDetect(1, myCurrentplayermode);
    	}
    	else if(paperCount >= 3){
    		handleDetect(2, myCurrentplayermode);
    	}
    	else{
    		calculateGain(mymatrix);
    	}
    }
    public static void handleDetect(int oppmove, int myCurrentplayermode){
    	float maximum_gainvalue;
    	//媛먯��빐�꽌 �쟾�왂蹂�寃�
    	//�쟾�왂��? �긽��寃껋씠 怨좎젙�릺�뿀�떎怨� �뙋�떒�뻽�쓣�븣 媛��옣 �겙 gain媛� 媛��졇�삤湲� 
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
    
    public static int RandomRSP(){
    	int myRandom = (int) (Math.random() * (3));
    	return myRandom;
    }
}
