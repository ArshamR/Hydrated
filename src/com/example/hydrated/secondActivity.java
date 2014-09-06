
package com.example.hydrated;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.hydrated.R;

public class secondActivity extends MainActivity {

	private static final String Alarm_Intent = "broadcastIntent";
	float amountWater;
	private float value;
	private long intervals;
	private Button finish;
	private Button sleep;
	private Button start;
	private boolean service = false;
	private Button stop;
	PendingIntent pendingIntent;
	AlarmManager alarmManager;
	Intent intent;
		
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    ActionBar actionBar = getActionBar();
        actionBar.hide();
	    setContentView(R.layout.second_activity);
	    addListenerButton();   
	    addListenerSleep();
	    addListenerStart();
	    addListenerStop();
	    Globals.exercise = false;
	   
	    Bundle extras = getIntent().getExtras();
		if (extras != null){
		     value = extras.getFloat("var");
		     amountWater = value/2;
		}	
	}
	
	
	public void addListenerButton() {
   	 	
		//Finish is actually the calculate button
    	finish = (Button) findViewById(R.id.finish);
   	
    	finish.setOnClickListener(new OnClickListener() {
  
    	  @Override
    	  public void onClick(View v) {          	

    		  Toast.makeText(secondActivity.this,  		
		              "\nCalculated!",
		              
		    			Toast.LENGTH_SHORT).show();
  	    	 	   calc();	
  	    	 	   Globals.amountToDrink = amountWater;
  	    	 	   setAlarm();
  	    	 	   service = true;
    	  }
    	});
	}
	//This function was for somthing else but i had to change it later on.
	public void calc()
    {
    	Globals.sleep = false;
    }
	
	//Sets the alarm and sends broadcasts
	public void setAlarm()
	{			
		if(Globals.exercise == true)
		{
			//intervals = 10000;
			intervals = 900000;		
		}
		else
		{
			//intervals = 15000;
			intervals = 3600000;
		}
		intent = new Intent(this, ScheduledService.class);
		pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 234324243, intent, 0);
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    	alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
    			    SystemClock.elapsedRealtime()+ intervals, intervals, pendingIntent);	
	}
	//Adds listener on sleep and sets sleep to true
	//Also stops service
	public void addListenerSleep()
	{
		sleep = (Button) findViewById(R.id.sleep);
		sleep.setOnClickListener(new OnClickListener()
		{
			 public void onClick(View v) 
			 {		
				 if(service == true)
				 {
				 Globals.sleep = true;	
				 stopService(intent);
				 alarmManager.cancel(pendingIntent);
				 }
				 Toast.makeText(secondActivity.this,  		
	              "\nSee you soon!!",
	              
	    			Toast.LENGTH_SHORT).show();
			 }			
		});
	}		
	//Adds listener on Start button and sets global variable exercise to true
	public void addListenerStart()
	{
		start = (Button) findViewById(R.id.StartBtn);
		start.setOnClickListener(new OnClickListener()
		{
			 public void onClick(View v) 
			 {		
				 Globals.exercise = true;
				 Toast.makeText(secondActivity.this,  		
			              "\nPress calculate and enjoy your workout!!",
			              
			    			Toast.LENGTH_SHORT).show();
			 }
		});
	}	
	//Adds listener on stop will set the global variable exercise to false
	public void addListenerStop()
	{
		stop = (Button) findViewById(R.id.StopBtn);
		stop.setOnClickListener(new OnClickListener()
		{
			 public void onClick(View v) 
			 {		
				 Globals.exercise = false;
				 Toast.makeText(secondActivity.this,  		
			              "\nPress calculate to resume normal mode",
			              
			    			Toast.LENGTH_SHORT).show();
			 }
		});
	}
}
