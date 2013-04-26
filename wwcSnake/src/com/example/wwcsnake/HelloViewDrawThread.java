package com.example.wwcsnake;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
public class HelloViewDrawThread extends Thread{
	private int sleepSpan = 100;//睡眠的毫秒数
	private boolean flag = true;//循环标记位
	HelloView helloView;//游戏界面的引用
	SurfaceHolder surfaceHolder = null;	
	public HelloViewDrawThread(HelloView helloView,SurfaceHolder surfaceHolder){//构造器
		this.helloView = helloView;
		this.surfaceHolder = surfaceHolder;
	}
	public void run(){
		Canvas c;//画布
		long startTime = System.nanoTime();
		while(flag){
			c = null;
			try {     
				// 锁定整个画布，在内存要求比较高的情况下，建议参数不要为null
			    c = surfaceHolder.lockCanvas(null);
			    synchronized (this.surfaceHolder) {
			    	try{
			    		helloView.myDraw(c);	
			    		
			    	}
			    	catch(Exception e){}
			    }
			} finally {
			    if (c != null) {
			    	//更新屏幕显示内容
			        surfaceHolder.unlockCanvasAndPost(c);
			    }
			}
			try{
				Thread.sleep(sleepSpan);//睡眠sleepSpan毫秒
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
    public void setFlag(boolean flag) {//设置循环标记
    	this.flag = flag;
    }	
}
