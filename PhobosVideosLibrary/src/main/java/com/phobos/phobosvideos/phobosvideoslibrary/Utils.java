package com.phobos.phobosvideos.phobosvideoslibrary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by Anthony on 3/27/14.
 */
public class Utils
{
	public static void gotoLink(Context context, int resID)
	{
		context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(resID))));
	}

	public static Uri getURIbyId(Context context, int resId)
	{
		return Uri.parse("android.resource://" + context.getPackageName() + "/" + resId);
	}

	public static void rateApp(Context context)
	{
		try
		{
			context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
		}
		catch (android.content.ActivityNotFoundException anfe)
		{
			context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
		}
	}

	public static void share(Context context, String message, String title)
	{
		//String message = "Text I wan't to share."
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("text/plain");
		share.putExtra(Intent.EXTRA_TEXT, message);

		context.startActivity(Intent.createChooser(share, title));
	}

	public static void sendEmail(Context context, String subject, String body, String title)
	{
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL, new String[]{"apcsoftwares@gmail.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, subject);
		i.putExtra(Intent.EXTRA_TEXT, body);
		try
		{
			context.startActivity(Intent.createChooser(i, title));
		}
		catch (android.content.ActivityNotFoundException ex)
		{
			Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}
}
