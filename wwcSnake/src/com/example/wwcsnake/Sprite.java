package com.example.wwcsnake;

import java.util.ArrayList;

import android.util.Log;

public class Sprite {

	WwcSnake wwcsnake;
	GameView gameview;
	Coordinate head;
	float ticktime = 0;
	ArrayList<Coordinate> body = new ArrayList<Coordinate>();
	int direction;// 上下左右，0，1，2，3
	float tick;

	public Sprite(GameView gameview) {
		
		this.gameview = gameview;
		head = new Coordinate(5, 5);
		body.add(head);
		body.add(new Coordinate(5, 4));
		body.add(new Coordinate(5, 3));
		body.add(new Coordinate(5, 2));
		direction = 1;
		tick = 0.01f;//0.5s动一次
	}

	public void moveUp() {
		Coordinate newHead = new Coordinate(head.x, head.y - 1);
		body.add(0, newHead);
		body.remove(body.size() - 1);
		head = newHead;
	}

	public void moveDown() {
		Coordinate newHead = new Coordinate(head.x, head.y + 1);
		body.add(0, newHead);
		body.remove(body.size() - 1);
		head = newHead;
	}

	public void moveLeft() {
		Coordinate newHead = new Coordinate(head.x - 1, head.y);
		body.add(0, newHead);
		body.remove(body.size() - 1);
		head = newHead;
	}

	public void moveRight() {
		Coordinate newHead = new Coordinate(head.x + 1, head.y);
		body.add(0, newHead);
		body.remove(body.size() - 1);
		head = newHead;
	}

	public void scroll() {
		// 向右向下滚屏幕
		if (head.x - gameview.strX > (this.gameview.width - 10)) {

			if ((gameview.strX + 55) >= 160 - this.gameview.width)
				gameview.strX = 160 - this.gameview.width;
			else
				gameview.strX += 55;
		}
		Log.d("TAG", "" + gameview.strX);
		if (head.y - gameview.strY > (this.gameview.height - 10)) {
			if ((gameview.strY + 55) >= 160 - this.gameview.height)
				gameview.strY = 160 - this.gameview.height;
			else
				gameview.strY += 55;
		}
		// 向左向上滚屏幕
		if ((head.x - gameview.strX) <= 3) {
			if ((gameview.strX - 55) <= 0)
				gameview.strX = 0;
			else
				gameview.strX -= 55;
		}
		if ((head.y - gameview.strY) <= 3) {
			if ((gameview.strY - 55) <= 0)
				gameview.strY = 0;
			else
				gameview.strY -= 50;
		}
	}

	public void move() {

		switch (this.direction) {
		case 0: {
			this.moveUp();
			break;
		}
		case 1: {
			this.moveRight();
			break;
		}
		case 2: {
			this.moveDown();
			break;
		}
		case 3: {
			this.moveLeft();
			break;
		}
		}
		this.scroll();
	}

	public void update() {
		ticktime += this.gameview.deltaTime;
		while (ticktime > this.tick) {
			ticktime -= this.tick;
			this.move();

		}
	}
}
