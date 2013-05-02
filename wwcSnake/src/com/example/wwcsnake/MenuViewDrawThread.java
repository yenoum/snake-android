package com.example.wwcsnake;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MenuViewDrawThread extends Thread{
	private int sleepSpan = 200;//˯�ߵĺ�����
	private boolean flag = true;//ѭ�����λ
	MenuView menuView;//�˵����������
	SurfaceHolder surfaceHolder = null;
	public MenuViewDrawThread(MenuView menuView,SurfaceHolder surfaceHolder){//������
		this.menuView = menuView;
		this.surfaceHolder = surfaceHolder;
	}
	public void run(){
		Canvas c;//����
		while(flag){
			c = null;
			try {
				// �����������������ڴ�Ҫ��Ƚϸߵ�����£����������ҪΪnull
			    c = surfaceHolder.lockCanvas(null);
			    synchronized (this.surfaceHolder) {
			    	try{
			    		menuView.onDraw(c);
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