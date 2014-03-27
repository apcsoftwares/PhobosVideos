package com.phobos.phobosvideos.evolutionofdance;

import android.os.Bundle;

import com.phobos.phobosvideos.phobosvideoslibrary.VideoPlayback;


public class MainActivity extends VideoPlayback
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		AD_UNIT_ID = "ca-app-pub-8010918457888868/4335067632";
		super.onCreate(savedInstanceState);
		setVideoID(R.raw.evolutionofdance);
	}
}
