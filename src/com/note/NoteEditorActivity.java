package com.note;

import keendy.projects.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.database.DatabaseAdapter;

public class NoteEditorActivity extends Activity {

  private static String TAG = "Note Editor";
  
  private EditText noteView;
  
  public static class PLUGEditText extends EditText {

	private Rect mRect;
	private Paint mPaint;

	/* Custom EditText with line underneath the next */
	public PLUGEditText(Context context, AttributeSet attrs) {
	  super(context, attrs);
	  
	  mRect = new Rect();
	  mPaint = new Paint();
	  mPaint.setStyle(Paint.Style.STROKE);
	  mPaint.setColor(Color.YELLOW);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
	  int count = getLineCount();
	  Rect r = mRect;
	  Paint paint = mPaint;

	  /* Drawing a line underneath the rows */
	  for (int i = 0; i < count; i++) {
		int baseline = getLineBounds(i, r);
		canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
	  }
	  super.onDraw(canvas);
	}
  } 
  
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	setContentView(R.layout.note_editor);
	
	noteView = (EditText) findViewById(R.id.note);
  }
  
  @Override
  public void onDestroy() {
	super.onDestroy();
 }
  
  private void save() {
	if(!noteView.getText().toString().equals(""))
	{
	  DatabaseAdapter database = new DatabaseAdapter(this);
	  database.open();
	  database.createNote("Untitled", noteView.getText().toString());
	  Log.i(TAG, "Note created, it contained " + noteView.getText().toString());
	  database.close();
	}
  }
}