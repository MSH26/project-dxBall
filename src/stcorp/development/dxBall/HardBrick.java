package stcorp.development.dxBall;

import android.graphics.Color;

public class HardBrick extends Brick {

	public HardBrick(float xAxis, float yAxis, float length, float width){
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.length = length;
		this.width = width;
		color = Color.GREEN;
		point = 2;
		hitCount = 2;
	}
	
	public void changeColor(){
		setColor(Color.YELLOW);
	}
}
