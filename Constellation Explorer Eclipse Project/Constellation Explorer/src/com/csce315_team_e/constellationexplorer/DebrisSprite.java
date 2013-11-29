package com.csce315_team_e.constellationexplorer;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class DebrisSprite extends Sprite {
	

	public DebrisSprite(GameView gameView, Bitmap bmp) {
		super(gameView, bmp);
		
		this.gameview = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth();
        this.height = bmp.getHeight();
	}

	public void update() {
		if (y >= (gameview.getHeight() ) ) {
			y = 0;
			// choose a new random row
			Random rand = new Random(System.currentTimeMillis());
			//int newX = rand.nextInt(690-this.width);
			int i = rand.nextInt(3);
			//x = 10 + i*160;
			x = (gameview.getWidth()/3 - this.width)/2 + i*(gameview.getWidth()/3);
			
			int j = rand.nextInt(4);
			if(j == 0){
				this.bmp = BitmapFactory.decodeResource(gameview.getResources() , R.drawable.debris1 );
				this.ySpeed = 35;
			}else if(j == 1){
				this.bmp = BitmapFactory.decodeResource(gameview.getResources(), R.drawable.debris2 );
				this.ySpeed = 50;
			}else if(j == 2){
				this.bmp = BitmapFactory.decodeResource(gameview.getResources(), R.drawable.debris3 );
				this.ySpeed = 45;
			}else {
				this.bmp = BitmapFactory.decodeResource(gameview.getResources(), R.drawable.debris4 );
				this.ySpeed = 40;
			}
		}		
		y += ySpeed; // update y coordinate
		
	}

}
