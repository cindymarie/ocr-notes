package com.note;

import keendy.projects.R;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.database.DatabaseAdapter;

public class NotesListActivity extends ListActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	DatabaseAdapter dbAdapter  = new DatabaseAdapter(this);
	dbAdapter.open();
	
	Cursor cursor = dbAdapter.getNote(1);
	startManagingCursor(cursor);
	
	String[] column = {DatabaseAdapter.KEY_NOTE_TITLE};
	int[] to = {R.id.notes_title_list};
	
	SimpleCursorAdapter list = new SimpleCursorAdapter(this,
		R.layout.notes_list, 
		cursor, 
		column, 
		to);
	
	this.setListAdapter(list);
	
  }
}
