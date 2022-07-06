package Team7;
import RSP.*;
/**
*
* @author zinhyeok
* @author kyutae 
*/


public class team7 implements RSPPlayable {
	 private static int moveCount = 0;
	 private String myGroupName = "team 7";
	 private static int[] Arr_oppmove = new int[4];
	 
	 
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
	        //rememberOpponentMove repeated by 4round 
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
	    	if(moveCount>=3) {
	      	Mylogic.detectOpp(Arr_oppmove);
	    	}
	    	//�쟾�왂 case1 oppmove[0]==oppmove[2] 紐⑤몢媛� 媛숇떎怨� �뙋�젙 -> �긽�� �쟾�왂�쓣 �뙆�븙
	    	//-> gainmatrix�뿉 �쓽�븳 �쟾�왂�씤 寃쎌슦 �빐�떦耳��씠�뒪媛� 留롫떎怨� 媛��젙
	    	if(moveCount%2 == 0) {
	    		Mylogic.CountOppOdd(oppmove);
	    	} 
	    	if(moveCount%2 ==1) {
	    		Mylogic.CountOppEven(oppmove);
	    	} 
	    	moveCount++;
	    }
	   

		public void setGain(float[][] gainMatrix)
		{
		//game�쓽 gainmatrix瑜� 諛쏆븘�샂 -> RSPTESTER�뿉�꽌 �엳�쓬 
		 Mylogic.calculateGain(gainMatrix);
		 Mylogic.saveMatrix(gainMatrix);
		}
}
