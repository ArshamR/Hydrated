package com.example.hydrated;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScheduledService extends BroadcastReceiver {
  @Override
  public void onReceive(Context c, Intent i) {
			   
	  //Starts the service the creates notifications
      Intent myService1 = new Intent(c, MyAlarmService.class);    
      c.startService(myService1);
   }   
  }