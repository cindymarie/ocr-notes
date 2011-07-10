package com.main;

import keendy.projects.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.camera.CameraActivity;
import com.database.DatabaseAdapter;

/**
 * HomeActivity that handles all key presses during home view 
 * TODO Implement all onClick features
 */
public class HomeActivity extends Activity implements OnClickListener {
  
  /* Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.home);
    
     /* Initialize database */
    DatabaseAdapter database = new DatabaseAdapter(this);
    
    /* Initialize Buttons */    
    View captureNote = findViewById(R.id.home_captureNote);
    captureNote.setOnClickListener(this);
    View aboutButton = findViewById(R.id.home_about);
    aboutButton.setOnClickListener(this);
    
    database.open();
    long id;
    database.createNote("Android Pro 2", "This is a book for pros like me");
    Cursor c = database.getNotes();
    if(c.moveToFirst())
      sampleRetrieve(c);
  }

  @Override
  public void onClick(View v) {
	switch(v.getId()) {
	  case R.id.home_captureNote:
		startActivity(new Intent(HomeActivity.this, CameraActivity.class));
		break;
	  case R.id.home_about:
		startActivity(new Intent(HomeActivity.this, AboutActivity.class));
		break;
	}
  }
  
  public void sampleRetrieve(Cursor c) {
	Toast.makeText(this, c.getString(1) + "\n" + c.getString(2),
		Toast.LENGTH_LONG).show();
  }
  
}