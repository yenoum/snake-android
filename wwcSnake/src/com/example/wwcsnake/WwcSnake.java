package com.example.wwcsnake;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.KeyEvent;

public class WwcSnake extends Activity {
	

	
	//int map1[][];
	GameView gameview;
	int action = 0;
	//Sprite snake;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_wwc_snake);
		WindowManager manager = getWindowManager();

		//initmap();
		
		
		
		gameview = new GameView(this,0);
		//snake = new Sprite(gameview);
		this.setContentView(gameview);
		
		
		
		
		
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wwc_snake, menu);
		return true;
	}
	
	/*public void initmap(){
		
		map1 = new int[160][160];
		for(int i = 0;i<160;i++)
			for(int j=0;j<160;j++){
				if(i==0||j==0||i==159||j==159)
					map1[i][j]=1;
				else
					map1[i][j]=0;
			}
	}*/
	
	
	
	/*public boolean onKeyUp(int keyCode, KeyEvent event) {//¼üÅÌÌ§Æð
    	if(keyCode == 19){//ÉÏ
    		action = 0;
    		this.snake.direction = 0;
    		
    	}
    	if(keyCode == 20){//ÏÂ
    		action = 1;
    		this.snake.direction = 2;
    	}    	
    	if(keyCode == 21){//×ó
    		action = 2;
    		this.snake.direction = 3;
    	}    	
    	if(keyCode == 22){//ÓÒ
    		action = 3;
    		this.snake.direction = 1;
    	}
    	if (keyCode == KeyEvent.KEYCODE_BACK ){
    		System.exit(0);
    	}

		return false;
	}
    
    public boolean onKeyDown(int keyCode, KeyEvent event){//¼üÅÌ°´ÏÂ¼àÌý
    	if(keyCode == 19){//ÉÏ
    		//action = 4;
    	}
    	if(keyCode == 20){//ÏÂ
    		//action = 5;
    		
    	}    	
    	if(keyCode == 21){//×ó
    		//action = 6;
    	}    	
    	if(keyCode == 22){//ÓÒ
    		//action = 7;
    	}
		return false;
    }	*/
}


