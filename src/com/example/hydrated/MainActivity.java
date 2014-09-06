package com.example.hydrated;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.hydrated.R;

public class MainActivity extends Activity {

	float weight;
	private String gender;
	private Spinner spinner1;
	private Button btnSubmit;
	private EditText editText;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
		addListenerOnButton();	
    }
    
    //Add a listner on the submit button.
    //Gets the weight input and gender. 
    //Starts the next activity on button press
    public void addListenerOnButton() {
    	 
    	spinner1 = (Spinner) findViewById(R.id.spinner1);
    	editText = (EditText) findViewById(R.id.weight);
    	btnSubmit = (Button) findViewById(R.id.btnSubmit);
    	
    	btnSubmit.setOnClickListener(new OnClickListener() {
  
    	  @Override
    	  public void onClick(View v) {
    		  String temp;
    		  if(editText.getText().toString().trim().equalsIgnoreCase(""))
    		  {
    			  editText.setError("Please enter something!");
    		  }
    		  else
    		  {
    	    	temp = editText.getText().toString();
    	    	weight = Float.valueOf(temp);
    	    	gender = spinner1.getSelectedItem().toString();  
//    	    Toast.makeText(MainActivity.this,  		
//              "\nWeight : " + weight,
//                    
//    			Toast.LENGTH_SHORT).show();
    	    Intent intent = new Intent (v.getContext(), secondActivity.class);
    	    intent.putExtra("var", weight);  
    		startActivity(intent);
    		  }
    	  }
    	});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}