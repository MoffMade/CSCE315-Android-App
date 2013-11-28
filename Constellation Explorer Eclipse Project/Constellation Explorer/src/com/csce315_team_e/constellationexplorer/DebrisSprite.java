package com.csce315_team_e.constellationexplorer;

import java.util.Currency;
import java.util.Random;

import android.graphics.Bitmap;
//import com.csce315_team_e.constellationexplorer.Sprite;

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
		}		
		y += ySpeed;
		
	}

}
