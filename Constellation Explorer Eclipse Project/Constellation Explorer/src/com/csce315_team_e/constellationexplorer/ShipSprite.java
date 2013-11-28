package com.csce315_team_e.constellationexplorer;

import android.graphics.Bitmap;

public class ShipSprite extends Sprite {
	private boolean isExploded = false;
	public int timeExploded = 10;
	
	public ShipSprite(GameView gameView, Bitmap bmp) {
		super(gameView, bmp);

		this.gameview = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth();
        this.height = bmp.getHeight();
	}

	public void update() {
		//int w = gameview.getWidth(); // 768
		
		if (x > (gameview.getWidth() - this.width ) ) {
			x = gameview.getWidth() - this.width;
		}		
		if(x < 0){
			x = 0;
		}
		
	}
	
	public void setExploded(boolean _exploded){
		isExploded = _exploded;
	}
	public boolean isExploded(){
		return isExploded;
	}
	

}
