package keendy.projects;

import java.io.IOException;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.view.SurfaceHolder.Callback;

public class CameraActivity extends Activity implements Callback {

  private static final String TAG = "CAMERA ACTIVITY";
  
  private SurfaceView mSurfaceView;
  private SurfaceHolder mSurfaceHolder;
  
  private Camera mCamera;
  
  private boolean mPreviewRunning = false;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
    getWindow().setFormat(PixelFormat.TRANSLUCENT);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
    WindowManager.LayoutParams.FLAG_FULLSCREEN);
    
    setContentView(R.layout.camera);
    
    mSurfaceView = (SurfaceView) findViewById(R.id.camera_surface);
    mSurfaceHolder = mSurfaceView.getHolder();
    mSurfaceHolder.addCallback(this);
    mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    
    Log.i(TAG, "onCreated!");
  }

  /** Activity is hardcoded to display in landscape, gwapo ko */
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
  
  /** Picture Callback */
  Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
	public void onPictureTaken(byte[] imageData, Camera c) {
	  //TODO Handling of picture churva
	    Log.i(TAG, "onPictureTaken!");
	}	
  };
}
