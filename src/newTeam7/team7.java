package newTeam7;
import RSP.*;
/**
*
* @author zinhyeok
* @author kyutae 
*/


public class team7 implements RSPPlayable {
	 private static int moveCount = 0;
	 private String myGroupName = "team 7";
	 int[] Arr_oppmove = new int[4];
	 
	 
	 @Override
	    public String getYourGroupName() {
	        return myGroupName;
	    }

	    @Override
	    public int getUserMove(int playermode) {
	        int myMove = Mylogic.myRSP(playermode);
	        return myMove;
	    }

	    @Override
	    public void rememberOpponentMove(int oppmove, int yourmove) {
	    	Mylogic.save_moveCount(moveCount);
	        //rememberOpponentMove repeated by 3round 
	    	if(moveCount==0){
	    		Arr_oppmove[0] = oppmove;
	    		}
	    	if(moveCount==1){
	    		Arr_oppmove[1] = oppmove;
	    	}
	    	if(moveCount==2){
	    		Arr_oppmove[2] = oppmove;
	    	}
	    	if(moveCount==3){
	    		Arr_oppmove[3] = oppmove;
	    	}
	    	if(moveCount>3){
	    	Arr_oppmove[0] = Arr_oppmove[1];
	    	Arr_oppmove[1] = Arr_oppmove[2];
	    	Arr_oppmove[2] = Arr_oppmove[3];
	    	Arr_oppmove[3] = oppmove;
	    	}
//	    	System.out.println((int)Arr_oppmove[0]);
//	    	System.out.println((int)Arr_oppmove[1]);
//	    	System.out.println((int)Arr_oppmove[2]);
//	    	System.out.println((int)Arr_oppmove[3]);
	    	//System.out.println(moveCount);
	    	moveCount++;
	    	if(moveCount>=3) {
	      	Mylogic.detectOpp(Arr_oppmove);
	    	}
	    	//전략 case1 oppmove[0]==oppmove[2] 모두가 같다고 판정 -> 상대 전략을 파악
	    	//-> gainmatrix에 의한 전략인 경우 해당케이스가 많다고 가정
	 
	    }
	   

		public void setGain(float[][] gainMatrix)
		{
		//game의 gainmatrix를 받아옴 -> RSPTESTER에서 있음 
		 Mylogic.calculateGain(gainMatrix);
		 Mylogic.saveMatrix(gainMatrix);
		}
}
