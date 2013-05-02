package com.example.wwcsnake;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
public class MenuView extends SurfaceView implements SurfaceHolder.Callback{
	WwcSnake wwcSnake;//��Activity������
	Paint paint;//����
	Bitmap start1;//��ʼ��ϷͼƬ
	Bitmap sound1;//����ͼƬ
	Bitmap sound2;
	Bitmap help1;//��Ϸ˵��ͼƬ
	Bitmap exit1;//�˳���ϷͼƬ
	Bitmap menubackground;//�󱳾�ͼƬ
	Bitmap menubackground2;//С����ͼƬ
	int menubackgroudX = 0;//��Ҫ�ƶ��ı���������
	MenuViewDrawThread menuViewDrawThread;
	public MenuView(WwcSnake wwcSnake) {//������
		super(wwcSnake);
		this.wwcSnake = wwcSnake;
		menuViewDrawThread = new MenuViewDrawThread(this, getHolder());
		getHolder().addCallback(this);
		paint = new Paint() ;
		start1 = BitmapFactory.decodeResource(getResources(), R.drawable.start1);//��ʼ����ʼ
		sound1 = BitmapFactory.decodeResource(getResources(), R.drawable.sound1);//��ʼ��������
		sound2 = BitmapFactory.decodeResource(getResources(), R.drawable.sound2);//��ʼ���ر�����
		help1 = BitmapFactory.decodeResource(getResources(), R.drawable.help1);//��ʼ������
		exit1 = BitmapFactory.decodeResource(getResources(), R.drawable.exit1);//��ʼ���˳�
		menubackground = BitmapFactory.decodeResource(getResources(), R.drawable.menubackground);
		menubackground2 = BitmapFactory.decodeResource(getResources(), R.drawable.menubackground2);
	}
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(menubackground, menubackgroudX, 0, paint);//���ƴ󱳾�
		canvas.drawBitmap(menubackground2, 21, 20, paint);//����С����
		canvas.drawBitmap(start1, 60, 60, paint);
		if(wwcSnake.isSound){//����������״̬����������ťͼƬ
			canvas.drawBitmap(sound1, 60, 150, paint);
		}
		else{
			canvas.drawBitmap(sound2, 60, 150, paint);
		}
		canvas.drawBitmap(help1, 60, 240, paint);//���ư���
		canvas.drawBitmap(exit1, 60, 330, paint);//�����˳���ť
	}
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getX()>50 && event.getX()< 50+start1.getWidth()
				&& event.getY()>60 && event.getY()<60+start1.getHeight()){//����˿�ʼ��Ϸ�˵�
			if(wwcSnake.startSound.isPlaying()){
				wwcSnake.startSound.stop();
			}			
			wwcSnake.myHandler.sendEmptyMessage(2);//��Activity��Handler������Ϣ
		}
		else if(event.getX()>50 && event.getX()<50+sound1.getWidth()
				&& event.getY()>70+start1.getHeight() && event.getY()<70+start1.getHeight()+sound1.getHeight()){
			//����������˵�
			wwcSnake.isSound = !wwcSnake.isSound;//����������ȡ��
			if(!wwcSnake.isSound){
				if(wwcSnake.startSound.isPlaying()){
					wwcSnake.startSound.pause();//ֹͣ��������
				}
				if(wwcSnake.backSound.isPlaying()){		
					wwcSnake.backSound.pause();//ֹͣ��������
				}
			}
			else{
				if(!wwcSnake.startSound.isPlaying()){
					wwcSnake.startSound.start();//��ʼ��������
				}
			}
		}
		else if(event.getX()>50 && event.getX()<50+help1.getWidth()
				&& event.getY()>80+start1.getHeight()+sound1.getHeight() && event.getY()<80+start1.getHeight()+help1.getHeight()+help1.getHeight()){
			//���������Ϸ˵���˵�
		}
		else if(event.getX()>50 && event.getX()<50+exit1.getWidth()
				&& event.getY()>90+start1.getHeight()+help1.getHeight()+help1.getHeight()
				&& event.getY()<90+start1.getHeight()+help1.getHeight()+help1.getHeight()+exit1.getHeight()){
			//��������˳���Ϸ�˵�
			System.exit(0);//�˳���Ϸ
		}
		return super.onTouchEvent(event);
	}
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}
	public void surfaceCreated(SurfaceHolder holder) {

		menuViewDrawThread.setFlag(true);
		menuViewDrawThread.start();
	}
	public void surfaceDestroyed(SurfaceHolder holder) {
		 boolean retry = true;
	        menuViewDrawThread.setFlag(false);//ֹͣˢ֡�߳�
	        while (retry) {
	            try {
	            	menuViewDrawThread.join();//�ȴ�ˢ֡�߳̽���
	                retry = false;
	            } 
	            catch (InterruptedException e) {//���ϵ�ѭ����ֱ���ȴ����߳̽���
	            }
	        }
	}
}