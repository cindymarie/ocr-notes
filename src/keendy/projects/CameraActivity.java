package keendy.projects;

import java.io.IOException;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class CameraActivity extends Activity implements Callback { 

  private static final String TAG = "CAMERA ACTIVITY";
  
  private SurfaceView mSurfaceView;
  private SurfaceHolder mSurfaceHolder;
  
  private ImageButton mImageButton;
  
  private Camera mCamera;
  
  private boolean mPreviewRunning = false;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
    getWindow().setFormat(PixelFormat.TRANSLUCENT);
    
    setContentView(R.layout.camera);
    
    mSurfaceView = (SurfaceView) findViewById(R.id.camera_surface);
    mSurfaceHolder = mSurfaceView.getHolder();
    mSurfaceHolder.addCallback(this);
    mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
   
    mImageButton = (ImageButton) findViewById(R.id.camera_button);
    
    mImageButton.setOnClickListener(new OnClickListener() {

	  @Override
	  public void onClick(View view) {
		Log.i(TAG, "Class Name: " + view.getClass().getName());
		Log.i(TAG, "View ID: " + view.getId());
	  }
    
    });
    
   Log.i(TAG, "onCreated!");
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
	
	if(mPreviewRunning)	{
	  mCamera.stopPreview();
	}
	
	Camera.Parameters p = mCamera.getParameters();
	p.setPreviewSize(w, h);
	mCamera.setParameters(p);

	try {
	  mCamera.setPreviewDisplay(holder);
	} catch (IOException e) {
	  Log.e(TAG, "IOException lol");
	}

	mCamera.startPreview();
	mPreviewRunning = true;

  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
	mCamera = Camera.open();
	
    Log.i(TAG, "surfaceCreated!");
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
	mCamera.stopPreview();
	mPreviewRunning = false;
	mCamera.release();

    Log.i(TAG, "surfaceDestroyed!");
  }
}
