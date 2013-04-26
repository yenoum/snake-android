package com.example.wwcsnake;

public class HelloViewThread extends Thread{
	int sleepSpan = 200;//˯�ߵĺ�����
	private boolean flag = true;
	int status = 0;
	WwcSnake wwcsnake;//activity������
	public HelloViewThread(WwcSnake wwcsnake){
		this.wwcsnake = wwcsnake;
	}
	public void setFlag(boolean flag){
		this.flag = flag;
	}
	public void run() {//��д��run����
		while(flag){
			switch(status){
				case 0://ľ���˶�
					wwcsnake.helloView.woodLeftX -= 2;
					wwcsnake.helloView.woodRightX += 2;
					if(wwcsnake.helloView.woodLeftX<-90){
						status = 1;
					}
					break;
				case 1://�����˶�
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
					wwcsnake.myHandler.sendEmptyMessage(1);//����activity����Handler��Ϣ
					break;
			}
			try{
				Thread.sleep(sleepSpan);//˯��
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
