package stcorp.development.dxBall;

public abstract class Brick extends Drawable {
	protected float length;
	protected float width;
	protected int color;
	protected int point;
	protected int hitCount;
	
	
	public void setLength(int length){
		this.length = length;
	}
	
	public float getLength(){
		return length;
	}
	
	public void setWidth(int width){
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
	
	public void setPoint(int point){
		this.point = point;
	}
	
	public int getPoint(){
		return point;
	}
	
	public void setHitCount(int hitCount){
		this.hitCount = hitCount;
	}
	
	public int getHitCount(){
		return hitCount;
	}
	
	public void decreasePoint(){
		this.point = point - 1;
	}
	public void decreaseHitCount(){
		this.hitCount = hitCount - 1;
	}
}
