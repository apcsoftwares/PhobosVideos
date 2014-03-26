package com.phobos.phobosvideos.phobosvideoslibrary;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;


public class VideoPlayback extends ActionBarActivity {

	private VideoView mVideoView = null;
	//private String videoName = null;

	private String appPackageName; // getPackageName() from Context or Activity object

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_playback);
		//initialize("");
		// Get a reference to the VideoView
		appPackageName = getPackageName();

		//getWindow().setFormat(PixelFormat.TRANSLUCENT);
		mVideoView = (VideoView) findViewById(R.id.videoViewer);

		// Add a Media controller to allow forward/reverse/pause/resume

		final MediaController mMediaController = new MediaController(
				VideoPlayback.this, true);

		mMediaController.setEnabled(false);
		//mMediaController.setAnchorView(mVideoView);

		mVideoView.setMediaController(mMediaController);

		mVideoView.requestFocus();
		// Add an OnPreparedListener to enable the MediaController once the video is ready
		mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				mMediaController.setEnabled(true);
				mVideoView.start();
			}
		});

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
		super.onPause();
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
		if (id == R.id.share)
		{
			return true;
		}
		else if (id == R.id.more_apps)
		{
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://goo.gl/ZCxoI")));
			return true;
		}
		else if (id == R.id.rate_app)
		{
			try
			{
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
			}
			catch (android.content.ActivityNotFoundException anfe)
			{
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
