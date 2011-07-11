package com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Database for PLUG Notes
 * Two tables, subject and note: a subject can contain several notes, a note can
 * only be in one subject
 * TODO Create CRUD methods
 */
public class DatabaseAdapter {

  private static final String TAG = "DBHelper";
  
  /* Columns for Subjects Table */
  public static final String KEY_TABLE_ID = "_id";
  public static final String KEY_TABLE_TITLE = "title";
  
  /* Columns for Notes Table  */
  public static final String KEY_NOTE_ID = "_id";
  public static final String KEY_NOTE_TITLE = "title";
  public static final String KEY_NOTE_CONTENT = "content";
  public static final String KEY_NOTE_SUBJECT = "subject_id";
  public static final String DATABASE_NAME = "plug_database";
  public static final String DATABASE_TABLE_NOTE = "notes";
  public static final String DATABASE_TABLE_SUBJECT = "subjects";
  private static final int DATABASE_VERSION = 1;
  
  private static final String DATABASE_CREATE_SUBJECT = 
	"CREATE TABLE subjects (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
	"title TEXT NOT NULL);";
  
  private static final String DATABASE_CREATE_NOTE = 
	"CREATE TABLE notes (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
	"title TEXT NOT NULL, content TEXT NOT NULL, subject_id INTEGER);"; 
  
  private static final String[] DATABASE_CREATE_TABLES = {
	DATABASE_CREATE_SUBJECT, DATABASE_CREATE_NOTE};
  
  
  private final Context context;
  private DatabaseHelper DBHelper;
  private SQLiteDatabase plugDB;
 
  public DatabaseAdapter(Context context) {
	this.context = context;
	DBHelper = new DatabaseHelper(this.context);
  }
  
  /*
   * Database Helper for SQLite; opening ang closing
   */
  private static class DatabaseHelper extends SQLiteOpenHelper {
	
	DatabaseHelper(Context context ) {
	  super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	  for(String sql : DATABASE_CREATE_TABLES)
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	  db.execSQL("DROP TABLE IF EXISTS subjects;");
	  db.execSQL("DROP TABLE IF EXISTS notes;");
	  onCreate(db);
	}
	
	/** SQLite for Android 2.1 and legacy does not support foreign keys */
	/*
	@Override
	public void onOpen(SQLiteDatabase db)
	{
	  super.onOpen(db);
	  if (!db.isReadOnly())
	  {
	  
	    // Enable foreign key constraints
	    db.execSQL("PRAGMA foreign_keys=ON;");
	  }
	}*/
  }
  
  public DatabaseAdapter open () throws SQLException {
	Log.i(TAG, "Opening PLUG database...");
	plugDB = DBHelper.getWritableDatabase();
	return this;
  }
  
  public void close() {
	Log.i(TAG, "Closing PLUG database...");
	DBHelper.close();
  }
  
  /* Creating a note with a subject*/
  public long createNote(String title, String content, int subject_id) {
	ContentValues cv = new ContentValues();
	cv.put(KEY_NOTE_TITLE, title);
	cv.put(KEY_NOTE_CONTENT, content);
	cv.put(KEY_NOTE_SUBJECT, subject_id);
	
	return plugDB.insert(DATABASE_TABLE_NOTE, null, cv); 
  }  
  
  /* Creating a note without a subject */
  public long createNote(String title, String content) {
	ContentValues cv = new ContentValues();
	cv.put(KEY_NOTE_TITLE, title);
	cv.put(KEY_NOTE_CONTENT, content);
	
	return plugDB.insert(DATABASE_TABLE_NOTE, null, cv); 
  } 
  
  public Cursor getNotes() {
	return plugDB.query(DATABASE_TABLE_NOTE, new String[] {
		KEY_NOTE_ID, 
		KEY_NOTE_TITLE, 
		KEY_NOTE_CONTENT, 
		KEY_NOTE_SUBJECT
		},
		null,
		null,
		null,
		null,
		null);
  }
}
