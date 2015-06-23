package com.ctrlz.game2048;

import cn.waps.AppConnect;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static MainActivity mainActivity = null;
	
	private int score = 0;
	private TextView tvScore;
	private TextView tvhistory;
	
	private SharedPreferenceAction sharePreferenceAction ;
	
	
	public MainActivity() {
		mainActivity = this;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//»•µÙ±ÍÃ‚¿∏
		setContentView(R.layout.activity_main);
		
		AppConnect.getInstance("065eb6c04be339039523ae01cc4c07e6", "91", this);
		LinearLayout adLayout = (LinearLayout)findViewById(R.id.AdLinearLayout);
		AppConnect.getInstance(this).showBannerAd(this, adLayout);
		
		init();
	}

	public void init(){
//		com.ctrlz.game2048.GameView gameView = (com.ctrlz.game2048.GameView)findViewById(R.id.myGridView);
//		gameView.initGridView();
		tvScore = (TextView)findViewById(R.id.tvScore);
		tvhistory = (TextView)findViewById(R.id.tvhistory);
		sharePreferenceAction = new SharedPreferenceAction(this, "Game2048", MODE_PRIVATE);
		
		
		if(getHistory()== null){
			storeHistory("0");
		}
		showHistory(getHistory() + "0");
	}
	
	public String getHistory(){
		return sharePreferenceAction.Sharedget("Max");
	}
	
	public String getHistoryValue(){
		return tvhistory.getText().toString().trim();
	}
	
	public void storeHistory(String _score){
		sharePreferenceAction.Sharedput("Max", _score);
	}
	
	public void showHistory(String _score){
		tvhistory.setText(_score);
	}
	
	public void clearScore(){
		score = 0;
		showScore();
	}
	
	public void showScore(){
		tvScore.setText(score+"");
	}
	
	public String getScore(){
		return tvScore.getText().toString().trim();
	}
	
	public void addScore(int s){
		score+=s;
		showScore();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public static MainActivity getMainActivity() {
		return mainActivity;
	}
	
	@Override
	protected void onDestroy() {
		AppConnect.getInstance(this).close();
		super.onDestroy();
	}

}
