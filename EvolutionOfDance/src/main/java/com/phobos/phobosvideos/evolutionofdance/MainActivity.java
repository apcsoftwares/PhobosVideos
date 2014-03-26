package com.phobos.phobosvideos.evolutionofdance;

import android.os.Bundle;

import com.phobos.phobosvideos.phobosvideoslibrary.VideoPlayback;


public class MainActivity extends VideoPlayback
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setVideoID(R.raw.evolutionofdance);
	}
}
