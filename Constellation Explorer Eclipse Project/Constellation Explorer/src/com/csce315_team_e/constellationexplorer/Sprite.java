package com.csce315_team_e.constellationexplorer;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite {
	protected int x = 0;
	protected int y = 0;
	protected int xSpeed = 20;
	protected int ySpeed = 20;
	protected GameView gameview;
	protected Bitmap bmp;
	protected int width;
	protected int height;
	
	public Sprite(GameView gameView, Bitmap bmp) {
        this.gameview = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth();
        this.height = bmp.getHeight();
        
        //Random rnd = new Random ( );
        //x = rnd.nextInt (gameView.getWidth () - this.width);
        //ySpeed = rnd.nextInt(MaxSpeed * 2) - MaxSpeed;
	}
	public void setX(int _x){
		x = _x;
	}
	public void setY(int _y){
		y = _y;
	}
	
	public void setBmp(Bitmap _bmp){
		this.bmp = _bmp;
	}
	public void update() {		
	}
	public void onDraw(Canvas canvas) {
        update();
        canvas.drawBitmap(bmp, x , y, null);
	}

}
