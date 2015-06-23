package com.ctrlz.game2048;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class GameView extends GridLayout {

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initGridView();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initGridView();
	}

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initGridView();
	}

	private void initGridView() {
		setColumnCount(4);
		setOnTouchListener(new OnTouchListener(){
			
			private float startX, startY, offSetX, offSetY;
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				switch(arg1.getAction())
				{
				case MotionEvent.ACTION_DOWN://手指按下时
					startX = arg1.getX();
					startY = arg1.getY();
					break;
				case MotionEvent.ACTION_UP://手指抬起时
					offSetX = startX - arg1.getX();
					offSetY = startY - arg1.getY();
					if(Math.abs(offSetX)>Math.abs(offSetY)){
						if(offSetX>5){
							Log.i("direction--->", "向左");
							swipeLeft();
						}
						if(offSetX<-5){
							Log.i("direction--->", "向右");
							swipeRight();
						}
					}else{
						if(offSetY>5){
							Log.i("direction--->", "向上");
							swipeUp();
						}
						if(offSetY<-5){
							Log.i("direction--->", "向下");
							swipeDown();
						}
					}
					break;
				}
				return true;
			}
		});
		
	}  
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {//布局宽高发生改变时执行,比如横竖屏
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		
		int cardWidth = (Math.min(w, h)-10)/4;
		
		addCards(cardWidth, cardWidth);//长宽一样
		
		startGame();
	}
	
	private void addCards(int width, int height){
		
		Card c;
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				
				c = new Card(getContext());
				c.setNum(0);
				addView(c, width, height);
				
				cardsMap[i][j] = c;
				
			}
		}
	}
	
	public void startGame(){
		
		MainActivity.getMainActivity().clearScore();
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				cardsMap[i][j].setNum(0);
			}
		}
		
		addRandomNumber();
		addRandomNumber();
		
	}
	
	//添加一个随机数
	private void addRandomNumber(){
		emptyPoints.clear();
		
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if(cardsMap[i][j].getNum() <= 0){
					emptyPoints.add(new Point(i,j));
				}
			}
		}
		
		Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
		cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
	}
	
	private void swipeUp(){
	boolean merge = false;
		
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				
				for (int x1 = x+1; x1 < 4; x1++) {
					if (cardsMap[x1][y].getNum()>0) {
						
						if (cardsMap[x][y].getNum()<=0) {
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);
							cardsMap[x1][y].getTextView().setBackgroundColor(0x33ffffff);
							x--;
							merge = true;
							
						}else if (cardsMap[x][y].getNum()==cardsMap[x1][y].getNum()) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x1][y].setNum(0);
							cardsMap[x1][y].getTextView().setBackgroundColor(0x33ffffff);
							MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
							check_history();
							merge = true;
						}
						
						break;
					}
				}
			}
		}
		
		if (merge) {
			addRandomNumber();
			checkComplete();
		}
	}
	
	private void swipeDown(){
		boolean merge = false;
		
		for (int y = 0; y < 4; y++) {
			for (int x = 3; x >=0; x--) {
				
				for (int x1 = x-1; x1 >=0; x1--) {
					if (cardsMap[x1][y].getNum()>0) {
						
						if (cardsMap[x][y].getNum()<=0) {
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);
							cardsMap[x1][y].getTextView().setBackgroundColor(0x33ffffff);
							
							x++;
							merge = true;
						}else if (cardsMap[x][y].getNum()==cardsMap[x1][y].getNum()) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x1][y].setNum(0);
							cardsMap[x1][y].getTextView().setBackgroundColor(0x33ffffff);
							MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
							check_history();
							merge = true;
						}
						break;
					}
				}
			}
		}
		
		if (merge) {
			addRandomNumber();
			checkComplete();
		}
	}
	
	private void swipeLeft(){
		boolean merge = false;
		
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				
				for (int y1 = y+1; y1 < 4; y1++) {
					if (cardsMap[x][y1].getNum()>0) {
						
						if (cardsMap[x][y].getNum()<=0) {
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);
							cardsMap[x][y1].getTextView().setBackgroundColor(0x33ffffff);
							
							y--;
							
							merge = true;
						}else if (cardsMap[x][y].getNum()==cardsMap[x][y1].getNum()) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x][y1].setNum(0);
							cardsMap[x][y1].getTextView().setBackgroundColor(0x33ffffff);
							MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
							check_history();
							merge = true;
						}
						
						break;
						
					}
				}
			}
		}
		
		if (merge) {
			addRandomNumber();
			checkComplete();
		}
	}
	
	private void swipeRight(){
		boolean merge = false;
		
		for (int x = 0; x < 4; x++) {
			for (int y = 3; y >=0; y--) {
				
				for (int y1 = y-1; y1 >=0; y1--) {
					if (cardsMap[x][y1].getNum()>0) {
						
						if (cardsMap[x][y].getNum()<=0) {
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);
							cardsMap[x][y1].getTextView().setBackgroundColor(0x33ffffff);
							y++;
							merge = true;
						}else if (cardsMap[x][y].getNum()==cardsMap[x][y1].getNum()) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x][y1].setNum(0);
							cardsMap[x][y1].getTextView().setBackgroundColor(0x33ffffff);
							MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
							check_history();
							merge = true;
						}
						break;
					}
				}
			}
		}
		
		if (merge) {
			addRandomNumber();
			checkComplete();
		}
	}
	
	private void checkComplete(){
		
		boolean complete = true;
		
		ALL:
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardsMap[x][y].getNum()==0||
						(x>0&&cardsMap[x][y].getNum()==cardsMap[x-1][y].getNum())||
						(x<3&&cardsMap[x][y].getNum()==cardsMap[x+1][y].getNum())||
						(y>0&&cardsMap[x][y].getNum()==cardsMap[x][y-1].getNum())||
						(y<3&&cardsMap[x][y].getNum()==cardsMap[x][y+1].getNum())) {
					
					complete = false;
					break ALL;
				}
			}
		}
		
		if (complete) {
			
			new AlertDialog.Builder(getContext()).setTitle("你好!").setMessage("游戏结束").setPositiveButton("重来", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					for (int x = 0; x < 4; x++) {
						for (int y = 3; y >=0; y--) {
							cardsMap[x][y].getTextView().setBackgroundColor(0x33ffffff);
						}
					}
					startGame();
					
				}
			}).show();
		}
		
	}

	private void check_history(){
		
		if(Integer.parseInt(MainActivity.getMainActivity().getScore())>Integer.parseInt(MainActivity.getMainActivity().getHistoryValue())){
			MainActivity.getMainActivity().storeHistory(MainActivity.getMainActivity().getScore());
			MainActivity.getMainActivity().showHistory(MainActivity.getMainActivity().getHistory() + "");
		}
	}
	
	private Card[][] cardsMap = new Card[4][4];
	
	private List<Point> emptyPoints = new ArrayList<Point>();//点位置
}
