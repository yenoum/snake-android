package com.example.wwcsnake;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.KeyEvent;
import android.view.View.OnTouchListener;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	WwcSnake wwcsnake;
	GameViewDrawThread gameViewDrawThread;
	Sprite snake;
	int strX = 0;
	int strY = 0;
	int height = 96;
	int width = 64;
	int status = 0;// 记录当前在第己关
	Paint paint;
	Bitmap wall;
	Bitmap floor;
	Bitmap head;
	Map mapConstructor;
	float deltaTime = 0;
	int[][] theMap;

	public GameView(WwcSnake wwcsnake) {// 构造器
		super(wwcsnake);
		this.wwcsnake = wwcsnake;
		gameViewDrawThread = new GameViewDrawThread(this, getHolder());
		getHolder().addCallback(this);
		initBitmap();
		// this.setOnKeyListener(this.wwcsnake);
		this.setFocusableInTouchMode(true);
		this.requestFocus();
		snake = new Sprite(this);
	}

	public GameView(WwcSnake wwcsnake, int guan) {// 构造器
		super(wwcsnake);
		this.wwcsnake = wwcsnake;
		gameViewDrawThread = new GameViewDrawThread(this, getHolder());
		getHolder().addCallback(this);
		this.status = guan;
		this.mapConstructor = new Map();
		this.theMap = this.mapConstructor.getMap(guan);
		initBitmap();
		// this.setOnKeyListener(this.wwcsnake);
		this.setFocusableInTouchMode(true);
		this.requestFocus();
		snake = new Sprite(this);
	}

	public void initBitmap() {
		paint = new Paint();
		floor = BitmapFactory.decodeResource(getResources(), R.drawable.grass);// 绿色的箱子
		wall = BitmapFactory.decodeResource(getResources(), R.drawable.wall);// 墙
		head = BitmapFactory.decodeResource(getResources(), R.drawable.head);// 头

	}

	public void myDraw(Canvas canvas) {

		this.snake.update();
		canvas.drawColor(Color.WHITE);// 绘制黑背景
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				if (this.theMap[i + strX][j + strY] == 1)
					canvas.drawBitmap(wall, i * 5, j * 5, paint);
			}
		int snakelength = this.snake.body.size();
		for (int snakeindex = 0; snakeindex < snakelength; snakeindex++) {
			Coordinate c = this.snake.body.get(snakeindex);
			canvas.drawBitmap(head, (c.x - strX) * 5, (c.y - strY) * 5, paint);

		}

	}

	public boolean onKeyUp(int keyCode, KeyEvent event) {// 键盘抬起
		if (keyCode == 19) {// 上
			this.snake.direction = 0;

		}
		if (keyCode == 20) {// 
			this.snake.direction = 2;
		}
		if (keyCode == 21) {// 左
			this.snake.direction = 3;
		}
		if (keyCode == 22) {// 右
			this.snake.direction = 1;
		}

		return false;
	}

	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(event.getX()>(this.snake.head.x-this.strX)*5)
				this.snake.direction = 1;
			break;
		case MotionEvent.ACTION_MOVE:

			break;
		case MotionEvent.ACTION_CANCEL:

			break;
		case MotionEvent.ACTION_UP:

			break;
		}

		return true;
	}

	/*
	 * public boolean onKeyDown(int keyCode, KeyEvent event){//键盘按下监听 if(keyCode
	 * == 19){//上 //action = 4; } if(keyCode == 20){//下 //action = 5;
	 * 
	 * } if(keyCode == 21){//左 //action = 6; } if(keyCode == 22){//右 //action =
	 * 7; } return false; }
	 */
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	public void surfaceCreated(SurfaceHolder holder) {
		gameViewDrawThread.setFlag(true);
		
		gameViewDrawThread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		gameViewDrawThread.setFlag(false);
		while (retry) {
			try {
				gameViewDrawThread.join();
				retry = false;
			} catch (InterruptedException e) {// 不断地循环，直到刷帧线程结束
			}
		}
	}
}
