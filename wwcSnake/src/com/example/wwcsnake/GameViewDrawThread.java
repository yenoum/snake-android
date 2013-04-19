package com.example.wwcsnake;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
public class GameViewDrawThread extends Thread{
	private int sleepSpan = 1;//˯�ߵĺ�����
	private boolean flag = true;//ѭ�����λ
	GameView gameView;//��Ϸ���������
	SurfaceHolder surfaceHolder = null;	
	public GameViewDrawThread(GameView gameView,SurfaceHolder surfaceHolder){//������
		this.gameView = gameView;
		this.surfaceHolder = surfaceHolder;
	}
	public void run(){
		Canvas c;//����
		long startTime = System.nanoTime();
		while(flag){
			c = null;
			try {
				this.gameView.deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
	            startTime = System.nanoTime();
				// �����������������ڴ�Ҫ��Ƚϸߵ�����£����������ҪΪnull
			    c = surfaceHolder.lockCanvas(null);
			    synchronized (this.surfaceHolder) {
			    	try{
			    		gameView.myDraw(c);	
			    		
			    	}
			    	catch(Exception e){}
			    }
			} finally {
			    if (c != null) {
			    	//������Ļ��ʾ����
			        surfaceHolder.unlockCanvasAndPost(c);
			    }
			}
			try{
				Thread.sleep(sleepSpan);//˯��sleepSpan����
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
    public void setFlag(boolean flag) {//����ѭ�����
    	this.flag = flag;
    }	
}