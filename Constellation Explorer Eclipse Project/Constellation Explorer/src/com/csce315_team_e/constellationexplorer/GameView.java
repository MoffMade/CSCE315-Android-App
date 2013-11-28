package com.csce315_team_e.constellationexplorer;


import java.util.Vector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameView extends SurfaceView {

	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;	
	private DebrisSprite debris1;	
	private ShipSprite spaceship;		
	private Bitmap background;
	private Bitmap leftBtn;
	private Bitmap rightBtn;
	private Bitmap gameover;
	private Bitmap missionComplete;
	private Bitmap gotoStarData;
	private SoundPool soundpool;
	private int exlodeSoundID;
	private int flyingSoundID;
	//Intent intent = new Intent(getContext(), StarDataActivity.class);
	private int playtime = 500; 
	//private boolean endGame = false;
	//private int WindowHeight;
	//private int WindowWidth;
	

	@SuppressLint("WrongCall")
	public GameView(Context context) {
		super(context);
		gameLoopThread = new GameLoopThread(this);
		holder = getHolder();
		holder.addCallback(new SurfaceHolder.Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				boolean retry = true;
				gameLoopThread.setRunning(false);
				while(retry){
					try{
						gameLoopThread.join();
						retry = false;
												
					}catch(InterruptedException e){
						
					}
					
				}
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				/*Canvas c = holder.lockCanvas ( null );
				onDraw(c);
                holder.unlockCanvasAndPost(c);*/
				gameLoopThread.setRunning(true);
                gameLoopThread.start();
                
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		background = BitmapFactory.decodeResource(getResources(), R.drawable.outerspace1 );
		
		spaceship = new ShipSprite(this, BitmapFactory.decodeResource(getResources(), R.drawable.spaceship ));
		
		//spaceship.setY(getHeight()-200);
		
		debris1 = new DebrisSprite(this, BitmapFactory.decodeResource(getResources(), R.drawable.debris1 ));
		debris1.setX(10);
		leftBtn = BitmapFactory.decodeResource(getResources(), R.drawable.left_btn );
		rightBtn = BitmapFactory.decodeResource(getResources(), R.drawable.right_btn );
		gameover = BitmapFactory.decodeResource(getResources(), R.drawable.gameover );
		missionComplete = BitmapFactory.decodeResource(getResources(), R.drawable.mission_acomplished );
		gotoStarData = BitmapFactory.decodeResource(getResources(), R.drawable.gotostardata );
		soundpool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
		
		soundpool.setOnLoadCompleteListener(new OnLoadCompleteListener() {		

			public void onLoadComplete(SoundPool soundpool, int sampleId, int status) {
			}
		}); // end of soundpoo.setOnLoadCompleteListener
		
		exlodeSoundID = soundpool.load(this.getContext(),R.raw.explosion,1);
		flyingSoundID = soundpool.load(this.getContext(),R.raw.flying,1);
		
		
			
		/*debrises = new Vector<Sprite>();*/
	}//end of GameView constructor

	/*protected void startactivity(Intent intent) {
		// TODO Auto-generated method stub
		startactivity(intent);
	}*/

	@SuppressLint("WrongCall")
	@Override
	protected void onDraw(Canvas canvas) {
		//soundpool.play(flyingSoundID, 5, 5, 1, -1, 1);
		//WindowHeight = getHeight(); // 1038
		//WindowWidth = getWidth(); // 768
		
		if(playtime == 500){ // do this only once at the beginning of the game
			spaceship.setX(10);		
			spaceship.setY(getHeight()- spaceship.height - leftBtn.getHeight() - 40);
			
		}
		playtime--;
		
		
		canvas.drawBitmap(background, 0,0, null);
		//canvas.drawBitmap(gotoStarData, getWidth()/2 - gotoStarData.getWidth()/2, getHeight()/8, null);
		canvas.drawBitmap(gotoStarData, getWidth()/2 - gotoStarData.getWidth()/2, 20, null);
		
		if(spaceship.timeExploded > 0){
			spaceship.onDraw(canvas);
		}		
		
		debris1.onDraw(canvas);		
		
		// simple collision detection
		if( (spaceship.y + spaceship.height) >= debris1.y && (debris1.y + debris1.height) >= spaceship.y 
				&& spaceship.x <= (debris1.x+debris1.width) && spaceship.x+spaceship.width >= debris1.x)
		{ // ship explodes
			spaceship.setBmp(BitmapFactory.decodeResource(getResources(), R.drawable.shipexploded)) ;
			
			if(!spaceship.isExploded()){
				soundpool.play(exlodeSoundID, 10, 10, 1, 0, 1);
				//soundpool.stop(flyingSoundID);
			}			
			spaceship.setExploded(true);	
			
			
		}
		//draw left button
		canvas.drawBitmap(leftBtn, 50 , getHeight() - rightBtn.getHeight() - 20  , null);
		//draw right button
		canvas.drawBitmap(rightBtn, getWidth() - rightBtn.getWidth() - 50 , getHeight() - rightBtn.getHeight() - 20 , null);
		
		
		if(spaceship.isExploded()){
			spaceship.timeExploded--;
			if(spaceship.timeExploded < -10){
				// print Game Over
				canvas.drawBitmap(gameover, getWidth()/2- gameover.getWidth()/2, getHeight()/2,null);			
			}
			if(spaceship.timeExploded == -12){						
				gameLoopThread.setRunning(false);
				
				//intent.putExtra("current_star", star);
				//startactivity(intent);
				
				//Intent intent = new Intent(getContext(), StarDataActivity.class);
				//endGame = true;				
				//startactivity(intent);
				
			}
		}else // still alive after n playing time
			if(playtime <= 0){
			//draw mission complete
				canvas.drawBitmap(missionComplete, getWidth()/2- missionComplete.getWidth()/2, getHeight()/2,null);				
				//endGame = true;
							
		}
		if(playtime == -1){			
			gameLoopThread.setRunning(false);									
			
			//startactivity(intent);
		}
		/*for(int i=0; i < debrises.size(); i++){
			debrises.get(i).onDraw(canvas);
		}*/
		
		//int w = getWidth(); = 480
		//int h = getHeight(); = 690
		
		
		
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//return super.onTouchEvent(event);
		float x = event.getX();
		float y = event.getY();
		if(!spaceship.isExploded()){
			if(x > 50 && x < (leftBtn.getWidth()+50)   &&   y > (getHeight() - leftBtn.getHeight() - 20) && y < getHeight() - 20){
				spaceship.setX(spaceship.x - 20); // move left
			}else
			if(x > (getWidth() - rightBtn.getWidth() -50) && x < getWidth()-50   &&   y > (getHeight() - rightBtn.getHeight() - 20) && y < getHeight() - 20){
				spaceship.setX(spaceship.x + 20); // move right
			}			
		}
		
		if( (x >= getWidth()/2 - gotoStarData.getWidth()/2) && (x <= getWidth()/2 + gotoStarData.getWidth()/2) && (y >= 20) && (y <= 20 + gotoStarData.getHeight() )){
			gameLoopThread.setRunning(false);
			// start StarData Activity here
			
		}
		
		return true;		
	}
	
	

}
