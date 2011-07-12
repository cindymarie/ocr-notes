package com.note;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

public class NoteEditorActivity {

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

	  for (int i = 0; i < count; i++) {
		int baseline = getLineBounds(i, r);
		
		canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
	  }
	  super.onDraw(canvas);
	}
  } 
}