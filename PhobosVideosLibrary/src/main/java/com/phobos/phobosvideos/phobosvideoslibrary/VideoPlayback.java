package com.phobos.phobosvideos.phobosvideoslibrary;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


public class VideoPlayback extends ActionBarActivity {


	public static final String MOTO_G = "21865E81A08194C4F1FD1FB87CF75E6E";
	//public static final String AD_UNIT_ID = "ca-app-pub-8010918457888868/4335067632";
	public static String AD_UNIT_ID;
	private AdView adView;
	private VideoView mVideoView = null;
	private MediaController mMediaController = null;
	//private String videoName = null;

	private String appPackageName; // getPackageName() from Context or Activity object

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_playback);
		//initialize("");
		// Get a reference to the VideoView
		appPackageName = getPackageName();

		final ImageView imageView = (ImageView) findViewById(R.id.splash_image);

		imageView.setImageResource(R.drawable.play_image);


		//getWindow().setFormat(PixelFormat.TRANSLUCENT);
		mVideoView = (VideoView) findViewById(R.id.videoViewer);
		mVideoView.setVisibility(View.GONE);

		// Add a Media controller to allow forward/reverse/pause/resume

		mMediaController = new MediaController(VideoPlayback.this, true);

		mMediaController.setEnabled(false);
		//mMediaController.setAnchorView(mVideoView);

		mVideoView.setMediaController(mMediaController);

		mVideoView.requestFocus();
		// Add an OnPreparedListener to enable the MediaController once the video is ready
		mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				mMediaController.setEnabled(true);
				//mVideoView.start();
			}
		});


		imageView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				imageView.setVisibility(View.GONE);
				mVideoView.setVisibility(View.VISIBLE);
				mVideoView.start();
			}
		});

		prepareAds();

	}

	private void prepareAds()
	{
		// Create an ad.
		adView = new AdView(this);
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId(AD_UNIT_ID);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativeLayout);
		layout.addView(adView);

		// Create an ad request. Check logcat output for the hashed device ID to
		// get test ads on a physical device.
		AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice(MOTO_G).build();

		// Start loading the ad in the background.
		adView.loadAd(adRequest);
	}

	public void setVideoID(int videoID)
	{
		mVideoView
				.setVideoURI(Uri.parse("android.resource://" + getPackageName() +
						"/" + videoID));
	}

	@Override
	protected void onPause()
	{
		if (mVideoView != null && mVideoView.isPlaying()) {
			mVideoView.stopPlayback();
			mVideoView = null;
		}
		if (adView != null)
		{
			adView.pause();
		}
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		if (adView != null)
		{
			adView.resume();
		}
	}

	@Override
	protected void onDestroy()
	{
		// Destroy the AdView.
		if (adView != null)
		{
			adView.destroy();
		}
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.video_playback, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

		if (id == R.id.menu_share_app)
		{
			Utils.share(this, getString(R.string.share_app) +
							" http://play.google.com/store/apps/details?id=" + getPackageName(), getString(R.string.share)
			);
			//Utils.sendEmail(this, getString(R.string.em));
			return true;
		}
		else if (id == R.id.menu_more_apps)
		{
			Utils.gotoLink(this, R.string.link_more_apps);
			return true;
		}
		else if (id == R.id.menu_rate_app)
		{

			Utils.rateApp(this);
			return true;
		}
		else if (id == R.id.menu_report_problem)
		{
			Utils.sendEmail(this, getString(R.string.email_report_subject), getString(R.string.email_report_body), getString(R.string.email_report_title));
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
