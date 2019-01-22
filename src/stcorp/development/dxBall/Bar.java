package stcorp.development.dxBall;

import android.graphics.Color;

public class Bar extends Drawable {
	
	private float length;
	private float width;
	private int color;
	
	public Bar(){
		color = Color.BLUE;
	}
	
	public void setLength(float length){
		this.length = length;
	}
	
	public float getLength(){
		return length;
	}
	
	public void setWidth(float width){
		this.width = width;
	}
	
	public float getWidth(){
		return width;
	}
	
	public void setColor(int color){
		this.color = color;
	}
	
	public int getColor(){
		return color;
	}
	
	public void changePosition(float xAxis, float yAxis){
		setXAxis(xAxis);
		setYAxis(yAxis);
	}
	
	public void moveBar(boolean leftPos,boolean rightPos){
        if(leftPos==true){
            if(GameCanvas.checkWidth >= xAxis + width) {
            	xAxis = xAxis + 4;
                width = width + 4;
            }


        }
        else if(rightPos==true){
            if(0<=xAxis) {
                //if()
            	xAxis = xAxis - 4;
            	width = width - 4;
            }

        }

   }
}
