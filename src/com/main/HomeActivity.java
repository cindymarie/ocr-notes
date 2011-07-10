package com.main;

import keendy.projects.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.camera.CameraActivity;

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
    
    /* Initialize Buttons */    
    View captureNote = findViewById(R.id.home_captureNote);
    captureNote.setOnClickListener(this);
    View aboutButton = findViewById(R.id.home_about);
    aboutButton.setOnClickListener(this);
    
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
}