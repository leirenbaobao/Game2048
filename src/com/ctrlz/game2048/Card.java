package com.ctrlz.game2048;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {
	
	private int num;//ÓÐ±àºÅºÍtextViewÁ½¸ö³ÉÔ±±äÁ¿
	private TextView textView;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
		
		String items[] = {"ÂëÃ¤", "Âë°×", "ÂëÔü", "Âëµ°", "ÂëÐÜ", "ÂëÔ³", "ÂëÅ«", "ÂëÅ©", "Âë¿ñ", "ÂëÐÛ", "Âë³Õ", "Âë·è", "ÂëÉñ", "ÂëÏÉ", "ŒÅË¿", "ŒÅÕ¨Ìì"};
		
		
		if(num == 0)
			textView.setText("");
		else if(num == 2)
			{
				textView.setText(items[0] );
				textView.setBackgroundColor(Color.parseColor("#EEE4DA"));
			}
		else if(num == 4)
			{
				textView.setText(items[1] );
				textView.setBackgroundColor(Color.parseColor("#ECE0C8"));
			}
		else if(num == 8)
			{
				textView.setText(items[2] );
				textView.setBackgroundColor(Color.parseColor("#F2B179"));
			}
		else if(num == 16)
			{
				textView.setText(items[3] );
				textView.setBackgroundColor(Color.parseColor("#F59563"));
			}
		else if(num == 32)
			{
				textView.setText(items[4] );
				textView.setBackgroundColor(Color.parseColor("#F57C5F"));
			}
		else if(num == 64)
			{
				textView.setText(items[5] );
				textView.setBackgroundColor(Color.parseColor("#F65D3B"));
			}
		else if(num == 128)
			{
				textView.setText(items[6] );
				textView.setBackgroundColor(Color.parseColor("#666699"));
			}
		else if(num == 256)
			{
				textView.setText(items[7] );
				textView.setBackgroundColor(Color.parseColor("#FFCC99"));
			}
		else if(num == 512)
			{
				textView.setText(items[8] );
				textView.setBackgroundColor(Color.parseColor("#CCCC33"));
			}
		else if(num == 1024)
			{
				textView.setText(items[9] );
				textView.setBackgroundColor(Color.parseColor("#EEE4DA"));
			}
		else if(num == 2048)
			{
				textView.setText(items[10] );
				textView.setBackgroundColor(Color.parseColor("#C46916"));
			}
		else if(num == 4096)
			{
				textView.setText(items[11] );
				textView.setBackgroundColor(Color.parseColor("#603005"));
			}
		else if(num == 8192)
			{
				textView.setText(items[12] );
				textView.setBackgroundColor(Color.parseColor("#913C44"));
			}
		else if(num == 16384)
			{
				textView.setText(items[13] );
				textView.setBackgroundColor(Color.parseColor("#C92D3C"));
			}
		else if(num == 32768)
			{
				textView.setText(items[14] );
				textView.setBackgroundColor(Color.parseColor("#FE1E1E"));
			}
		else if(num == 65536)
			{
				textView.setText(items[15] );
				textView.setBackgroundColor(Color.parseColor("#FE1E1E"));
			}
		else
			textView.setText(num + "");
		
	}
	public TextView getTextView() {
		return textView;
	}
	public void setTextView(TextView textView) {
		this.textView = textView;
	}

	public Card(Context context) {
		super(context);
		
		textView = new TextView(getContext());
		textView.setTextSize(35);
		textView.setBackgroundColor(0x33ffffff);
		
		textView.setGravity(Gravity.CENTER);
		
		LayoutParams lp = new LayoutParams(-1, -1);
		lp.setMargins(10, 10, 0, 0);
		addView(textView, lp);
		
		setNum(0);
	}
	
	//Á½ÕÅ¿¨Æ¬ÊÇ·ñÊý×ÖÏàÍ¬
	public Boolean whetherEquals(Card o){
		return num == o.getNum();
	}
	
}
