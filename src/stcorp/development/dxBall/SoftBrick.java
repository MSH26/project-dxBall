package stcorp.development.dxBall;

import android.graphics.Color;

public class SoftBrick extends Brick {
	
	public SoftBrick(float xAxis, float yAxis, float length, float width){
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.length = length;
		this.width = width;
		color = Color.YELLOW;
		point = 1;
		hitCount = 1;
	}
}
