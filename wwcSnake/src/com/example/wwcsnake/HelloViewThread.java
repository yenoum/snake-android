package com.example.wwcsnake;

public class HelloViewThread extends Thread{
	int sleepSpan = 200;//睡眠的毫秒数
	private boolean flag = true;
	int status = 0;
	WwcSnake wwcsnake;//activity的引用
	public HelloViewThread(WwcSnake wwcsnake){
		this.wwcsnake = wwcsnake;
	}
	public void setFlag(boolean flag){
		this.flag = flag;
	}
	public void run() {//重写的run方法
		while(flag){
			switch(status){
				case 0://木门运动
					wwcsnake.helloView.woodLeftX -= 2;
					wwcsnake.helloView.woodRightX += 2;
					if(wwcsnake.helloView.woodLeftX<-90){
						status = 1;
					}
					break;
				case 1://铁门运动
					wwcsnake.helloView.ironY -= 8;
					if(wwcsnake.helloView.ironY<-380){
						status = 2;
					}
					break;
				case 2:
					wwcsnake.helloView.wallLeftX -= 3;
					wwcsnake.helloView.wallRightX += 3;
					if(wwcsnake.helloView.wallLeftX<-90){
						status = 3;
					}
					break;
				case 3:
					this.flag = false;
					wwcsnake.myHandler.sendEmptyMessage(1);//向主activity发送Handler消息
					break;
			}
			try{
				Thread.sleep(sleepSpan);//睡眠
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
