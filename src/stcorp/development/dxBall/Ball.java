package stcorp.development.dxBall;

import android.graphics.Canvas;
import android.graphics.Color;

public class Ball extends Drawable {
	
	private int radius;
	private int color;
	private int dx;
	private int dy;
	private int gameOver=0;
	
	
	public Ball(float xAxis, float yAxis){
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		radius = 30;
		color = Color.RED;
		dx = 0;
		dy = 0;
	}
	
	public void setRadius(int radius){
		this.radius = radius;
	}
	
	public int getRadius(){
		return radius;
	}
	
	public void setColor(int color){
		this.color = color;
	}
	
	public int getColor(){
		return color;
	}
	
	public void setDx(int dx){
		this.dx = dx;
	}
	
	public int getDx(){
		return dx;
	}
	
	public void setDy(int dy){
		this.dy = dy;
	}
	
	public int getDy(){
		return dy;
	}
	
	public int getGameOver(){
        return gameOver;
    }

    public void ballBoundaryChech(Canvas canvas) {

        if((this.yAxis-this.radius)>=canvas.getHeight()){

            GameCanvas.life-=1;
            GameCanvas.newLife=true;
            this.gameOver=1;
            /////////////****************//////////////
        }

        if(GameCanvas.life==0){
        	GameCanvas.gameOver = true;
        }

        if((this.xAxis+this.radius)>=canvas.getWidth() || (this.xAxis-this.radius)<=0){
            this.dx = -this.dx;
        }
        if( (this.yAxis-this.radius)<=0){
            this.dy = -this.dy;
        }
        
    }

    public void move(){
    	xAxis = xAxis + dx;
    	yAxis = yAxis + dy;
    }
    
    public void bounce(Canvas canvas){
        move();
        if(xAxis == canvas.getWidth()|| xAxis < 0){
        	xAxis=0;
        	yAxis=0;
        }
        if(yAxis == canvas.getWidth() || yAxis < 0){

        	xAxis=0;
        	xAxis=0;
        	////**************************//////////
        }
    }
}
