package stcorp.development.dxBall;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class DxBallActivity extends Activity {
	public Ball ball;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.shortbeep);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameCanvas(this, mediaPlayer));
    }
    
    @Override
    public void onStart() {
        super.onStart();
        
//        setContentView(R.layout.main);
        
        
    }
    
    @Override
    public void onResume() {
        super.onResume();
        
            	
    }

    @Override
    public void onPause() {
        super.onPause();
        
    }

    @Override
    protected void onStop() {
        super.onStop();

//        setVisible(false); 
    }

    @Override
    protected void onRestart() {
        super.onRestart();

//        setVisible(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}