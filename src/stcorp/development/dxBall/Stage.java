package stcorp.development.dxBall;

import java.util.ArrayList;
import java.util.List;

public class Stage {
	protected int brickQuantityPerRow;
	protected int brickPlacementRow;
	protected List<String> brickType;
	
	
	public Stage(){
		brickQuantityPerRow = 5;
		brickPlacementRow = 3;
		String brick = "";
		brickType = new ArrayList<String>();
		for(int i=0; i<brickPlacementRow; ++i){
			for(int j=0; j<brickQuantityPerRow; ++j){
				if((i==0 && j==1) || (i==1 && j==3) || (i==2 && j==2)){
					brick = "HardBrick";
				}
				else if((i==1 && j==1) || (i==2 && j==3)){
					brick = "IntervalBrick";
				}
				else{
					brick = "SoftBrick";
				}
				brickType.add(brick);
			}
		}
	}
	
	
	public int getBrickQuantityPerRow(){
		return brickQuantityPerRow;
	}
	
	public int getBrickPlacementRow(){
		return brickPlacementRow;
	}
	
	public List<String> getBrickType(){
		return brickType;
	}
}
