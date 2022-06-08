package team7;

import RSP.*;

public class ExpectedValue implements RSPPlayable{
	
	private String myGroupName = "ExpectedValue Group";
	
	public String getYourGroupName() {
		return myGroupName;
	}
	
	public int getUserMove(int playermode) {
		if (playermode == 0) {
			return rowdetermine;
			
		}
		
		else if (playermode == 1) {
			return coldetermine;
			
		}
		return 100; //no meaning
	}
	
	public void rememberOpponentMove(int oppmove, int yourmove) {
		
	}
	
	int rowdetermine = 100;
	int coldetermine = 100;
	
	public void setGain(float[][] gainMatrix) {
		
		float row1 = gainMatrix[0][0]+gainMatrix[0][1]+gainMatrix[0][2];
		float row2 = gainMatrix[1][0]+gainMatrix[1][1]+gainMatrix[1][2];
		float row3 = gainMatrix[2][0]+gainMatrix[2][1]+gainMatrix[2][2];
		
		if (row1 > row2 && row1 > row3) {
			rowdetermine = 0;
		}else if (row2 > row1 && row2 > row3) {
			rowdetermine = 1;
		}else {
			rowdetermine = 2;
		}
		
		float col1 = gainMatrix[0][0]+gainMatrix[1][0]+gainMatrix[2][0];
		float col2 = gainMatrix[0][1]+gainMatrix[1][1]+gainMatrix[2][1];
		float col3 = gainMatrix[0][2]+gainMatrix[1][2]+gainMatrix[2][2];
		
		if (col1 < col2 && col1 < col3) {
			coldetermine = 0;
		}else if (col2 < col1 && col2 < col3) {
			coldetermine = 1;
		}else {
			coldetermine = 2;
		}
	}
}
