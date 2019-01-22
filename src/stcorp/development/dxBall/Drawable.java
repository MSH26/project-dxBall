package stcorp.development.dxBall;

public abstract class Drawable {
	
	protected float xAxis;
	protected float yAxis;
	
	
	public void setXAxis(float xAxis){
		this.xAxis = xAxis;
	}
	
	public float getXAxis(){
		return xAxis;
	}
	
	public void setYAxis(float yAxis){
		this.yAxis = yAxis;
	}
	
	public float getYAxis(){
		return yAxis;
	}
}
