package com.ccmedia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.URLUtil;
import android.widget.ListView;
import android.widget.TextView;


public class VideoViewActivity extends Activity implements OnSeekCompleteListener, OnErrorListener, OnBufferingUpdateListener, OnCompletionListener,
OnPreparedListener, OnVideoSizeChangedListener, SurfaceHolder.Callback{
	
	private static final int DIALOG_SERVER_ERR_EXPIRE = 1;
	
	ListView listView = null;
	
	View mVictimContainer;
	View homeview_play_pause_button;
	View bg = null;
	TextView bitrate = null;
	boolean isVisibleMenu = false;
	
	TextView subtitle = null;
	
	
    private SurfaceView mPreview;
    
    boolean isPlay = true;
    
    private static final String TAG = "HELLO_TV_PLAYER";
    private int mVideoWidth;
    private int mVideoHeight;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder holder;
    private String path;
    private Bundle extras;
    private static final String MEDIA = "media";
    private static final int LOCAL_AUDIO = 1;
    private static final int STREAM_AUDIO = 2;
    private static final int RESOURCES_AUDIO = 3;
    private static final int LOCAL_VIDEO = 4;
    private static final int STREAM_VIDEO = 5;
    private boolean mIsVideoSizeKnown = false;
    private boolean mIsVideoReadyToBePlayed = false;
    
    public static final String TEMP_FILE_PATH = "temp_dump.mp4";
    
    ProgressDialog loadingDialog = null;
    String accessUrl;
    
    View buffer_lev1;
	View buffer_lev2;
	View buffer_lev3;
	View buffer_lev4;
	
	Animation fadeInAnimation;
	Animation fadeOutAnimation;
	
	boolean isStreamClosed = false;
	
	@Override
	public void onBackPressed() {
        super.onBackPressed();
		
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate...START");
        super.onCreate(savedInstanceState);

        System.out.println("streamingUrl: "+AppObject.streamingUrl);
        accessUrl = AppObject.streamingUrl;
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.video_view);
        //AppObject.initScreenSetup(this);
        
        mVictimContainer = findViewById(R.id.menu_container);
		homeview_play_pause_button = findViewById(R.id.homeview_play_pause_button);
		homeview_play_pause_button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!isPlay){
					//change to play button and stop view
					homeview_play_pause_button.setBackgroundDrawable(getResources().getDrawable(R.drawable.homeview_pause_button));
					isPlay = true;
					loadMediaPlayer();
				}else{
					//change to pause button and play view
					homeview_play_pause_button.setBackgroundDrawable(getResources().getDrawable(R.drawable.homeview_play_button));
					isPlay = false;
					mMediaPlayer.stop();
					
					buffer_lev1.setVisibility(View.INVISIBLE);
					buffer_lev2.setVisibility(View.INVISIBLE);
					buffer_lev3.setVisibility(View.INVISIBLE);
					buffer_lev4.setVisibility(View.INVISIBLE);
					
				}
			}
		});

    	mPreview = (SurfaceView) findViewById(R.id.surface);
    	mPreview.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				System.out.println("CLICK!!");
				if(mVictimContainer.getVisibility() == View.VISIBLE ){
					//mVictimContainer.setVisibility(View.GONE);
					hideMenu();
				}else{
					//mVictimContainer.setVisibility(View.VISIBLE);
					showMenu();
				}
			}
		});
    	
    	//플레이어 설정
        holder = (mPreview).getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        extras = getIntent().getExtras();
        
        buffer_lev1 = (View)findViewById(R.id.play_top_lev1);
        buffer_lev2 = (View)findViewById(R.id.play_top_lev2);
        buffer_lev3 = (View)findViewById(R.id.play_top_lev3);
        buffer_lev4 = (View)findViewById(R.id.play_top_lev4);
        
        fadeInAnimation = AnimationUtils.loadAnimation(VideoViewActivity.this, R.anim.fade_in);
        fadeOutAnimation = AnimationUtils.loadAnimation(VideoViewActivity.this, R.anim.fade_out);
    }
    
    public void loadMediaPlayer(){
    	doCleanUp();
        try {
        	
        	Log.d(TAG, "accessURL: "+accessUrl);
        	path = accessUrl;

//        	while(!relayServiceClient.isVlcStreaming()){
//        		try {
//        			Log.d(TAG, "vlc is not loaded... wait 0.1 sec..");
//    				Thread.sleep(300);
//    			} catch (InterruptedException e) {
//    				// TODO Auto-generated catch block
//    				e.printStackTrace();
//    			}	
//        	}
        	
        	Thread.sleep(1000);
        	
        	//path = "rtsp://192.168.1.120:10000/stream.sdp";
			Log.d(TAG, "path: "+path);
    		// Create a new media player and set the listeners
            mMediaPlayer = new MediaPlayer();
            //mMediaPlayer.setDataSource(path);
            mMediaPlayer.setDisplay(holder);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnErrorListener(this);
            mMediaPlayer.setOnBufferingUpdateListener(this);
            mMediaPlayer.setOnCompletionListener(this);
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.setOnSeekCompleteListener(this);
            //mMediaPlayer.setOnVideoSizeChangedListener(this);
            
            createProgressDialog();
            
            //loadingDialog = ProgressDialog.show(
    		//				HelloTVActivity.this, "", 
    		//				getResources().getText(R.string.loading_message), true);
            Runnable r = new Runnable() {
                public void run() {
                    try {
                    	System.out.println("Data Source: "+path);
                        setDataSource(path);
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                    try {
                    	mMediaPlayer.prepare();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    //Log.v(TAG, "Duration:  ===>" + mp.getDuration());
                    //mp.start();
                }
            };
            new Thread(r).start();

        } catch (Exception e) {
            Log.e(TAG, "error: " + e.getMessage(), e);
        }
    }
    
    private void setDataSource(String path) throws IOException {
        if (!URLUtil.isNetworkUrl(path)) {
        	mMediaPlayer.setDataSource(path);
        } else {
            URL url = new URL(path);
            URLConnection cn = url.openConnection();
            cn.connect();
            InputStream stream = cn.getInputStream();
            if (stream == null)
                throw new RuntimeException("stream is null");
            File temp = File.createTempFile("mediaplayertmp", "dat");
            String tempPath = temp.getAbsolutePath();
            FileOutputStream out = new FileOutputStream(temp);
            byte buf[] = new byte[128];
            do {
                int numread = stream.read(buf);
                if (numread <= 0)
                    break;
                out.write(buf, 0, numread);
            } while (true);
            mMediaPlayer.setDataSource(tempPath);
            try {
                stream.close();
            }
            catch (IOException ex) {
                Log.e(TAG, "error: " + ex.getMessage(), ex);
                
            }
        }
    }
    
    int servrsideErrorCnt = 0;
    int maxServersideError = 5;
    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
		// TODO Auto-generated method stub
    	if(what == -4){
    		servrsideErrorCnt++;
    		
    		bufferingAddMsg = "(서버 접속장애.. "+servrsideErrorCnt+"/"+maxServersideError+")";
    		
    		//Serverside error, e.g.) settop error
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(servrsideErrorCnt == maxServersideError){
				//메인 창으로..
				servrsideErrorCnt = 0;
				
				showDialog(DIALOG_SERVER_ERR_EXPIRE);
			}else{
				loadMediaPlayer();
			}
    	}else{
    		bufferingAddMsg = "(재시도 중)";
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		loadMediaPlayer();
    	}
		return false;
	}

	public void onSeekComplete(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		Log.e(TAG, "onSeekComplete");
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			case DIALOG_SERVER_ERR_EXPIRE:
				return new AlertDialog.Builder(VideoViewActivity.this).setIcon(
						R.drawable.alert_dialog_icon).setTitle("time expired..")
						.setPositiveButton("OK", 
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										Intent intent = new Intent();
								        intent.setClass(getApplicationContext(), VideoSearchActivity.class);
										startActivity(intent);
									}
								}).create();
			}

			return null;
	}
	
	String bufferingMsg = "버퍼링 중입니다..";//getResources().getText(R.string.msg_buffering).toString();
	String bufferingAddMsg = accessUrl+"";
	public void createProgressDialog(){
		removeProgressDialog();
		
		loadingDialog = new ProgressDialog(this);
        //loadingDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		String bufferMsg = bufferingMsg;
		if(!"".equals(bufferingAddMsg))
			bufferMsg += "\n"+ bufferingAddMsg;
        loadingDialog.setMessage(bufferMsg);
        loadingDialog.setCancelable(true);
        Log.v(TAG, "show loading dialog...");
        loadingDialog.show();
	}
	
	int bufferBinPrev = -1;
	public void onBufferingUpdate(MediaPlayer arg0, int percent) {
        Log.d(TAG, "onBufferingUpdate percent:" + percent);
        
        int bufferBin = getBufferBin(percent);
        
        //이전것과 다를때만 업데이트
        if(bufferBinPrev != bufferBin){
        	if(bufferBin == 0){
        		buffer_lev1.setVisibility(View.VISIBLE);
        		buffer_lev2.setVisibility(View.INVISIBLE);
        		buffer_lev3.setVisibility(View.INVISIBLE);
        		buffer_lev4.setVisibility(View.INVISIBLE);
        	}else if(bufferBin == 1){
        		buffer_lev1.setVisibility(View.VISIBLE);
        		buffer_lev2.setVisibility(View.VISIBLE);
        		buffer_lev3.setVisibility(View.INVISIBLE);
        		buffer_lev4.setVisibility(View.INVISIBLE);
        	}else if(bufferBin == 2){
        		buffer_lev1.setVisibility(View.VISIBLE);
        		buffer_lev2.setVisibility(View.VISIBLE);
        		buffer_lev3.setVisibility(View.VISIBLE);
        		buffer_lev4.setVisibility(View.INVISIBLE);
        	}else if(bufferBin == 3){
        		buffer_lev1.setVisibility(View.VISIBLE);
        		buffer_lev2.setVisibility(View.VISIBLE);
        		buffer_lev3.setVisibility(View.VISIBLE);
        		buffer_lev4.setVisibility(View.VISIBLE);
        	}
        	
        	bufferBinPrev = bufferBin;	
        }
        
        if(loadingDialog != null)
        	loadingDialog.setProgress(percent);

    }
	
	public int getBufferBin(int percent){
    	if(percent < 25) return 0;
    	else if(percent < 50) return 1;
    	else if(percent < 75) return 2;
    	else return 3;
    }

    public void onCompletion(MediaPlayer arg0) {
        Log.d(TAG, "onCompletion called");
    }

    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        Log.v(TAG, "onVideoSizeChanged called");
        if (width == 0 || height == 0) {
            Log.e(TAG, "invalid video width(" + width + ") or height(" + height + ")");
            return;
        }
        mIsVideoSizeKnown = true;
        mVideoWidth = width;
        mVideoHeight = height;
        if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
            startVideoPlayback();
        }
    }

    public void onPrepared(MediaPlayer mediaplayer) {
        Log.d(TAG, "onPrepared called");
        //mIsVideoReadyToBePlayed = true;
        //if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
            startVideoPlayback();
        //}
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k) {
        Log.d(TAG, "surfaceChanged called");

    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder) {
        Log.d(TAG, "surfaceDestroyed called");
    }


    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated called");
        mVictimContainer.setVisibility(View.VISIBLE);	
        loadMediaPlayer();
        //playVideo1(5);
        //playVideo(5);
//        playVideo(extras.getInt(MEDIA));


    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
        doCleanUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
        doCleanUp();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private void doCleanUp() {
        mVideoWidth = 0;
        mVideoHeight = 0;
        mIsVideoReadyToBePlayed = false;
        mIsVideoSizeKnown = false;
    }

    private void startVideoPlayback() {
        Log.v(TAG, "startVideoPlayback");
        Log.v(TAG, "mVideoWidth: "+mVideoWidth+", mVideoHeight: "+mVideoHeight);
        //holder.setFixedSize(mVideoWidth, mVideoHeight);
        //holder.setFixedSize(0, 0);
        
        mMediaPlayer.start();
        if(loadingDialog != null)
        	loadingDialog.cancel();
    }
    
	public void removeProgressDialog(){
		if(loadingDialog != null)
			loadingDialog.cancel();
	}
	
	private void showMenu(){
		mVictimContainer.setVisibility(View.VISIBLE);
		mVictimContainer.setAnimation(fadeInAnimation);
		mVictimContainer.startAnimation(fadeInAnimation);
		
//		if(padeOutThread != null){
//			padeOutThread.setHidable(false);
//			padeOutThread.stop();
//		}
//		padeOutThread = new MenuPadeOutThread(handler);
			
	}
	
	private void hideMenu(){
		
		mVictimContainer.setVisibility(View.INVISIBLE);
		mVictimContainer.setAnimation(fadeOutAnimation);
		mVictimContainer.startAnimation(fadeOutAnimation);
		
//		if(padeOutThread != null){
//			padeOutThread.setHidable(false);
//			padeOutThread.stop();
//		}
	}
}
