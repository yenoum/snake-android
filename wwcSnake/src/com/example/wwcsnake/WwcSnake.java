package com.example.wwcsnake;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.KeyEvent;
import android.os.Handler;
import android.os.Message;

public class WwcSnake extends Activity {
	

	
	//int map1[][];
	GameView gameview;
	HelloView helloView;
	MuneView muneView;
	HelloViewThread helloViewThread;
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
		helloView = new HelloView(this);
		//initmap();	
		/*gameview = new GameView(this,0);
		//snake = new Sprite(gameview);
		this.setContentView(gameview);*/
		this.inittoHelloView();
		
	}

	Handler myHandler = new Handler(){//用来更新UI线程中的控件
        public void handleMessage(Message msg) {
        	if(msg.what == 1){//收到WelcomeViewGoThread/Welcome发来的消息
        		helloViewThread.setFlag(false);
        		if(helloView != null){
        			helloView = null;  
        		}
        		initAndToMenuView();
        	}
        	else if(msg.what == 2){//收到MenuView发来的消息
        		
        	}   
        	else if(msg.what == 3){
        		
        	}   
        	else if(msg.what == 4){//收到GameView来的消息，进入下一关
    			
        	}
        	else
        	{}
        		
        }
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wwc_snake, menu);
		return true;
	}
		
	public boolean onKeyUp(int keyCode, KeyEvent event) {//锟斤拷锟斤拷抬锟斤拷
    	
    	if (keyCode == KeyEvent.KEYCODE_BACK ){
    		System.exit(0);
    	}

		return false;
	}
	
	public void inittoHelloView(){
		helloView = new HelloView(this);
		this.setContentView(helloView);
    	helloViewThread = new HelloViewThread(this);
    	helloViewThread.start();
	}
	 public void initAndToMenuView(){
	    	menuView = new MenuView(this);
	    	this.setContentView(menuView);
	    	menuViewGoThread = new MenuViewGoThread(this);
	    	menuViewGoThread.start();
	    }
}

  




