package com.example.wwcsnake;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
public class HelloViewDrawThread extends Thread{
	private int sleepSpan = 100;//˯�ߵĺ�����
	private boolean flag = true;//ѭ�����λ
	HelloView helloView;//��Ϸ���������
	SurfaceHolder surfaceHolder = null;	
	public HelloViewDrawThread(HelloView helloView,SurfaceHolder surfaceHolder){//������
		this.helloView = helloView;
		this.surfaceHolder = surfaceHolder;
	}
	public void run(){
		Canvas c;//����
		long startTime = System.nanoTime();
		while(flag){
			c = null;
			try {     
				// �����������������ڴ�Ҫ��Ƚϸߵ�����£����������ҪΪnull
			    c = surfaceHolder.lockCanvas(null);
			    synchronized (this.surfaceHolder) {
			    	try{
			    		helloView.myDraw(c);	
			    		
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
