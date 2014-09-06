package com.example.hydrated;


import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;
import com.hydrated.R;

//Service creates notifications
public class MyAlarmService extends Service
{
	private float amountToDrink;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate()  {

	   super.onCreate();
	   
	   //If button sleep is pressed the service will stop
	   if(Globals.sleep == true)
		    {
		    this.stopSelf();
		    Toast.makeText(MyAlarmService.this,  		
		              "\nService Stopped",
		                   
		    			Toast.LENGTH_SHORT).show();
	}

	}
	@SuppressWarnings("deprecation")
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	    super.onStart(intent, startId);       
	    
	    amountToDrink = Globals.amountToDrink;
	    
	    amountToDrink = amountToDrink/10;

	    displayNotification();
		return START_NOT_STICKY;
	}

	public void displayNotification() {

		//If they are exercising 
		if(Globals.exercise == true)
		{
		NotificationCompat.Builder mBuilder =
    	    new NotificationCompat.Builder(this)
    	    .setSmallIcon(R.drawable.ic_launcher)
    	    .setContentTitle("My notification")
    	    .setContentText("Water break! Drink up!(6-8oz)");
	    mBuilder.setAutoCancel(true);
	    mBuilder.setVibrate(new long[] { 1000, 300, 200, 300, 1000 });
	   // mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
	    
	    // Sets an ID for the notification
	    int mNotificationId = 001;
	    // Gets an instance of the NotificationManager service
	    NotificationManager mNotifyMgr = 
	            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	    // Builds the notification and issues it.
	    mNotifyMgr.notify(mNotificationId, mBuilder.build());
		}
		
		//If they aren't exercising
		if(Globals.exercise == false)
		{
		NotificationCompat.Builder mBuilder =
		    new NotificationCompat.Builder(this)
		    .setSmallIcon(R.drawable.ic_launcher)
		    .setContentTitle("My notification")
		    .setContentText("Drink " + amountToDrink + "oz of water!");
	    mBuilder.setAutoCancel(true);
	    mBuilder.setVibrate(new long[] { 1000, 300, 200, 300, 1000 });
	    // Sets an ID for the notification
	    int mNotificationId = 001;
	    // Gets an instance of the NotificationManager service
	    NotificationManager mNotifyMgr = 
	            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	    // Builds the notification and issues it.
	    mNotifyMgr.notify(mNotificationId, mBuilder.build());
		}
	}    
	@Override
	public void onDestroy() 
	{
	    super.onDestroy();
	}
	
	}
