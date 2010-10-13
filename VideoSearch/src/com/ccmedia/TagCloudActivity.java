package com.ccmedia;

import java.util.List;
import java.util.Random;

import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class TagCloudActivity extends Activity {
	Animation moveDownAnimation;
	
	public void createCloud(){
		PredicateLayout l = new PredicateLayout(this);
		//l.setGravity(Gravity.CENTER);
		l.setBackgroundColor(Color.WHITE);
//		for (int i = 0; i < 10; i++) {
//			TextView t = new TextView(this);
//			t.setText("Hello");
//			t.setBackgroundColor(Color.RED);
//			t.setSingleLine(true);
//			l.addView(t, new PredicateLayout.LayoutParams(2, 0));
//		}

		//YouTubeKeywordsGatherer gatherer = new YouTubeKeywordsGatherer();
		Cloud cloud = AppObject.getTagCloud();//gatherer.goSearch(30);
		List<Tag> tags = cloud.tags();
		int count = 0;
		for (Tag tag : tags) {
			count++;
			
			//Animation moveAnimation = new Animation();
			Random rand = new Random();
			float toX = (float) (rand.nextFloat()*16) - 8; 
			float toY = (float) (rand.nextFloat()*16) - 8;
			int dulation = rand.nextInt(800) + 1200;
			System.out.println("toX: "+toX+", toY:"+toY);
			TranslateAnimation moveDownAnimation = new TranslateAnimation(0, toX, 0, toY);
			moveDownAnimation.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.anim.accelerate_decelerate_interpolator));
			moveDownAnimation.setFillAfter(true);
			moveDownAnimation.setDuration(dulation);
			moveDownAnimation.setRepeatMode(Animation.REVERSE);
			moveDownAnimation.setRepeatCount(Animation.INFINITE);

//			moveDownAnimation.setAnimationListener(new AnimationListener() {
//				
//				@Override
//				public void onAnimationStart(Animation arg0) {
//					// TODO Auto-generated method stub
//					System.out.println("ani start");
//					
//				}
//				@Override
//				public void onAnimationRepeat(Animation arg0) {
//					// TODO Auto-generated method stub
//					System.out.println("ani repeat");
//					if(arg0.getInterpolator() == AnimationUtils.loadInterpolator(TagCloudActivity.this, android.R.anim.decelerate_interpolator)){
//						arg0.setInterpolator(AnimationUtils.loadInterpolator(TagCloudActivity.this, android.R.anim.decelerate_interpolator));
//					}else{
//						arg0.setInterpolator(AnimationUtils.loadInterpolator(TagCloudActivity.this, android.R.anim.));
//					}
//					
//					
//				}
//				@Override
//				public void onAnimationEnd(Animation arg0) {
//					// TODO Auto-generated method stub
//					System.out.println("ani end");
//				}
//			});
			TextView tv = new TextView(this);
			tv.setAnimation(moveDownAnimation);
			tv.startAnimation(moveDownAnimation);
			tv.setPadding(5, 0, 5 ,0);
			int shadowColor = Color.argb(255, 0, 0, 0);
			tv.setShadowLayer(3, 0, 0, shadowColor);
			System.out.println("fontsize: "+11*tag.getWeight());
			System.out.println("int: "+tag.getWeightInt());
			System.out.println("nom: "+tag.getNormScore());
			tv.setTextSize((float) (20*Math.log(Math.pow(tag.getWeight(), 2)+1)));
			//System.out.println("tagWeight: "+tag.getWeight()+", "+tag.getWeightInt());
			tv.setText(tag.getName());
			int a = (int) (255 - 0*50*Math.log(Math.pow(tag.getWeight(), 2)+1));
			int r = (int) (150 + 0*30*Math.log(Math.pow(tag.getWeight(), 2)+1));
			int g = (int) (200 + 0*30*Math.log(Math.pow(tag.getWeight(), 2)+1));
			int b = 255;
			tv.setTextColor(Color.argb(a, r, g, b));
			tv.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
			tv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					System.out.println("CLICK!!!");
					if(AppObject.main instanceof MainTabActivity){
						System.out.println("tabCHange!!");
						AppObject.reservedSearchKeyword = ((TextView)arg0).getText().toString();
						((MainTabActivity)AppObject.main).getTabHost().setCurrentTab(1);
					}
				}
			});
			
			
			
			l.addView(tv, new PredicateLayout.LayoutParams(2, 0));
		}
		System.out.println("tag size: " + cloud.tags().size());

//		TextView tv = new TextView(this);
//		tv.setPadding(5, 5, 5, 0);
//		tv.setText("111111");
//		tv.setTextSize(20);
//		tv.setGravity(0x50);
//		l.addView(tv, new PredicateLayout.LayoutParams(2, 0));
//		
//		tv = new TextView(this);
//		tv.setPadding(5, 5, 5, 0);
//		tv.setText("222222");
//		tv.setGravity(0x50);
//		l.addView(tv, new PredicateLayout.LayoutParams(2, 0));
//		
//		tv = new TextView(this);
//		tv.setPadding(5, 5, 5, 0);
//		tv.setText("33333");
//		l.addView(tv, new PredicateLayout.LayoutParams(2, 0));
//		
//		tv = new TextView(this);
//		tv.setPadding(5, 5, 5, 0);
//		tv.setText("33333");
//		l.addView(tv, new PredicateLayout.LayoutParams(2, 0));
//		
//		tv = new TextView(this);
//		tv.setPadding(5, 5, 5, 0);
//		tv.setText("33333");
//		l.addView(tv, new PredicateLayout.LayoutParams(2, 0));
//		
//		tv = new TextView(this);
//		tv.setPadding(5, 5, 5, 0);
//		tv.setText("33333");
//		l.addView(tv, new PredicateLayout.LayoutParams(2, 0));
//		
//		tv = new TextView(this);
//		tv.setPadding(5, 5, 5, 0);
//		tv.setText("33333");
//		l.addView(tv, new PredicateLayout.LayoutParams(2, 0));
//		
//		tv = new TextView(this);
//		tv.setPadding(5, 5, 5, 0);
//		tv.setText("33333");
//		l.addView(tv, new PredicateLayout.LayoutParams(2, 0));
		
		setContentView(l);
	}
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		createCloud();
	}

}