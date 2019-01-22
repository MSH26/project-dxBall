package stcorp.development.dxBall;

import android.graphics.Color;

public class IntervalBrick extends Brick {
	
	public IntervalBrick(float xAxis, float yAxis, float length, float width){
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.length = length;
		this.width = width;
		color = Color.BLACK;
		point = 0;
		hitCount = 0;
	}
}
