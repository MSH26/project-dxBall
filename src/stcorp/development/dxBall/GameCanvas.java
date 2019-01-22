package stcorp.development.dxBall;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


class GameCanvas extends View implements Runnable{


	public static boolean gameOver;
    public static boolean newLife;
    public static int life;
    final MediaPlayer mp;
    Paint paint;
    Ball myBall;
    Bar myBar;


    public  static int canvasHeight, canvasWidth;
    float barWidth = 200;
    float brickX = 0, brickY=50;
    int score = 0;
    float left, right, top, bottom;
    float downX, downY, upX, upY;
    boolean leftPos, rightPos, first = true;
    int min_distance = 50;
    int ballSpeed;
    int stage = 1;
    String level = "";


    public static int checkWidth=0;
    
    ArrayList<Brick> bricks=new ArrayList<Brick>();


    public GameCanvas(Context context, MediaPlayer mp) {
        super(context);
        paint=new Paint();

        myBar = new Bar();
        life = 2;
        gameOver=false;
        newLife=true;
        this.mp = mp;
        level = "LEVEL: 1";
        ballSpeed = 5;
        stage = 1;
    }


//Draw ca
    @Override
    protected void onDraw(Canvas canvas) {

        canvasHeight=canvas.getHeight();
        canvasWidth=canvas.getWidth();

        if(first==true) {

            first = false;
        	
            if(stage == 1){
                for(int i=0; i<15; i++){
                    int color;

                    //CREATE BRICK POSITION
                    if(brickX>=canvas.getWidth()) {
                        brickX = 0;
                        brickY += 140;
                    }

                    //CHECK COLOR
                    if(i%2==0)
                        color = Color.GRAY;
                    else
                        color = Color.LTGRAY;

                    //ADD NEW BRICK
//                    bricks.add(new SoftBrick(brickX, brickY, brickY+140, brickX+canvas.getWidth()/5));
                    Brick brick = new SoftBrick(brickX, brickY, 50, canvas.getWidth()/5);
                    brick.setColor(color);
                    bricks.add(brick);

                    brickX += canvas.getWidth() / 5;
                }
            }
            else if(stage == 2){
                for(int i=0; i<25; i++){
                    int color;

                    //CREATE BRICK POSITION
                    if(brickX>=canvas.getWidth()) {
                        brickX = 0;
                        brickY += 140;
                    }

                    //CHECK COLOR
                    if(i%2==0)
                        color = Color.GRAY;
                    else
                        color = Color.LTGRAY;

                    //ADD NEW BRICK
//                    bricks.add(new SoftBrick(brickX, brickY, brickY+140, brickX+canvas.getWidth()/5));
                    Brick brick = new SoftBrick(brickX, brickY, 50, canvas.getWidth()/5);
                    brick.setColor(color);
                    bricks.add(brick);

                    brickX += canvas.getWidth() / 5;
                }
            }


            myBall=new Ball( canvas.getWidth()/2, canvas.getHeight()/2);
            myBall.bounce(canvas);
            
            myBar.setYAxis(getHeight());
            myBar.setXAxis(getWidth() / 2 - (barWidth / 2));
            myBar.setWidth(barWidth);
            myBar.setLength(20);
            checkWidth = canvas.getWidth();

            myBall.setDx(2);
            myBall.setDy(2);

            Log.d("", bricks.size() + "");

        }


        if(newLife){
            mp.start();
            newLife = false;
            //new ball

            myBall=new Ball(canvas.getWidth()/2,canvas.getHeight()-50);

            myBall.setDx( ballSpeed );
            myBall.setDy( -ballSpeed );
        }


        //LEVEL ONE
        paint.setTextSize(30);
        paint.setFakeBoldText(true);
        paint.setARGB(10, 0,0,0);
        canvas.drawText(level, canvas.getWidth() / 2 - 100 , canvas.getHeight() / 2 - 100, paint);

        //Ball
        paint.setColor(myBall.getColor());
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(myBall.getXAxis(), myBall.getYAxis(), myBall.getRadius(), paint);
        
        //Score
        paint.setTextSize(30);
        paint.setFakeBoldText(true);
        canvas.drawText("Score: "+score,10,30,paint);

        paint.setTextSize(30);
        paint.setFakeBoldText(true);
        canvas.drawText("Life: "+life,canvas.getWidth()-110,40,paint);


        //Bar
        paint.setColor(myBar.getColor());
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(myBar.getXAxis(), myBar.getYAxis() - myBar.getLength(), myBar.getXAxis() + myBar.getWidth(), myBar.getYAxis(), paint);

        //bricks
        for(int i=0;i<bricks.size();i++){
        	Brick brick = (SoftBrick)bricks.get(i);
        	paint.setColor(brick.getColor());
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(brick.getXAxis(), brick.getYAxis(), brick.getXAxis() + brick.getWidth(), brick.getYAxis() + brick.getLength(), paint);
        }



        if(gameOver){
            paint.setColor(Color.MAGENTA);
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

            paint.setColor(Color.WHITE);
            paint.setTextSize(50);
            paint.setFakeBoldText(true);
            canvas.drawText("GAME OVER",canvas.getWidth()/2-110,canvas.getHeight()/2,paint);
            canvas.drawText("FINAL SCORE: "+score,canvas.getWidth()/2-150,canvas.getHeight()/2+60,paint);
            gameOver = false;

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ((DxBallActivity)getContext()).finish();
        }


        this.ballBrickCollision(bricks,myBall,canvas);
        this.ballBarCollision(myBar,myBall, canvas);
        myBall.ballBoundaryChech(canvas);

        myBall.move();

        myBar.moveBar(leftPos,rightPos);
        this.run();


    }
    
    public void ballBarCollision(Bar myBar,Ball myBall,Canvas canvas){
        if(((myBall.getYAxis()+myBall.getRadius())>=(myBar.getYAxis()-myBar.getLength())) && ((myBall.getYAxis()+myBall.getRadius())<=myBar.getYAxis()) && (myBall.getXAxis()>=myBar.getXAxis()) && (myBall.getXAxis()<=(myBar.getXAxis()+myBar.getWidth()))) {
            myBall.setDy(-(myBall.getDy()));

        }

    }
    
    public void ballBrickCollision(ArrayList<Brick> br ,Ball myBall,Canvas canvas){
        for(int i=0;i<br.size();i++) {
        	Brick brick = br.get(i);
            if (((myBall.getYAxis() - myBall.getRadius()) <= (brick.getYAxis() + brick.getLength())) && ((myBall.getYAxis() + myBall.getRadius()) >= brick.getYAxis()) && ((myBall.getXAxis()) >= brick.getXAxis()) && ((myBall.getXAxis()) <= (brick.getXAxis() + brick.getWidth()))) {
                mp.start();
                br.remove(i);
                score+=1;
                if(br.size() == 0){
                	stage = 2;
                	newLife = true;
                	ballSpeed = ballSpeed + 1;
                	first = true;
                	level = "LEVEL: 2";
                	if(life < 5){
                    	life += 1;
                	}
                }
                myBall.setDy(-(myBall.getDy()));
            }
        }

    }


//Moving Bar
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                downX=event.getX();
                downY=event.getY();
                return true;

            }
            case MotionEvent.ACTION_UP:{
                upX=event.getX();
                upY=event.getY();

                float deltaX=downX-upX;
                float deltaY=downY-upY;

                if(Math.abs(deltaX) > Math.abs(deltaY)){
                    if(Math.abs(deltaX) > min_distance) {
                        if (deltaX < 0) {
                            //left=left+100;
                            //right=right+100;

                            leftPos=true;
                            rightPos=false;
                            myBar.moveBar(leftPos, rightPos);
                            return true;
                        }

                        if (deltaX > 0) {
                            leftPos=false;
                            rightPos=true;
                            myBar.moveBar(leftPos,rightPos);
                            //Right to left
                            return true;

                        }
                    }
                    else{
                        return  false;
                    }
                }
                else{
                    if(Math.abs(deltaY) > min_distance) {
                        if (deltaY < 0) {
                            //top to bottom
                            return true;
                        }
                        if (deltaY > 0) {
                            //bottom to top
                            return true;

                        }
                    }
                    else{
                        return  false;
                    }
                }

            }

        }
        return super.onTouchEvent(event);
    }



//    @Override
    public void run() {

        invalidate();
    }

}


