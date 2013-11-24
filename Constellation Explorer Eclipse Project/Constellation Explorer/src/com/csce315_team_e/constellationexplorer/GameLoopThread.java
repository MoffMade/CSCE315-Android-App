package com.csce315_team_e.constellationexplorer;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

public class GameLoopThread extends Thread {
	static final long FPS = 10;
    private GameView view;
    private boolean running = false;
   
    public GameLoopThread(GameView view) {
          this.view = view;
    }

    public void setRunning(boolean run) {
          running = run;
    }

    @SuppressLint("WrongCall")
	@Override
	public void run() {
		long ticksPS = 1000 / FPS;  
		/*each loop has to last at least 100 millisecs
		 * which means the canvas is redrawn maximum 10 frames/sec
		 * */
		long startTime;
		long sleepTime;
		while (running) {
			Canvas c = null;
			startTime = System.currentTimeMillis();
			try {
				c = view.getHolder().lockCanvas();
				synchronized (view.getHolder()) {
					view.onDraw(c);
				}
			} finally {
				if (c != null) {
					view.getHolder().unlockCanvasAndPost(c);
				}
			}
			
			sleepTime = ticksPS-(System.currentTimeMillis() - startTime); //time have to sleep
            try {
                   if (sleepTime > 0)
                          sleep(sleepTime);
                   else
                          sleep(1);
            } catch (Exception e) {}
		}// end while running
    }
}  