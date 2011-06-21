package keendy.projects;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class HomeActivity extends Activity implements OnClickListener {
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /** Initialize Buttons */
    View snapButton = findViewById(R.id.button1);
    snapButton.setOnClickListener(this);
    View searchButton = findViewById(R.id.button2);
    searchButton.setOnClickListener(this);
    View aboutButton = findViewById(R.id.button3);
    aboutButton.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
	switch(v.getId()) {
	  case R.id.button1:
		HomeActivity.this.startActivity(new Intent(HomeActivity.this, CameraActivity.class));
		break;
	  case R.id.button2:
		break;
	  case R.id.button3:
		break;
	}
  }
}