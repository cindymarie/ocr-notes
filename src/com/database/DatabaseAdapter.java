package com.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database for PLUG Notes
 * TODO Create CRUD methods
 */
public class DatabaseAdapter {

  /* Columns for Subjects Table */
  public static final String KEY_TABLE_ID = "_id";
  public static final String KEY_TABLE_TITLE = "title";
  
  /* Columns for Notes Table  */
  public static final String KEY_NOTE_ID = "_id";
  public static final String KEY_NOTE_TITLE = "title";
  public static final String KEY_NOTE_CONTENT = "content";
  public static final String KEY_NOTE_SUBJECT = "subject_id";
  private static final String TAG = "DBHelper";
  
  public static final String DATABASE_NAME = "plug_database";
  public static final String DATABASE_TABLE_NOTE = "notes";
  public static final String DATABASE_TABLE_SUBJECT = "subjects";
  private static final int DATABASE_VERSION = 1;
  
  private static final String DATABASE_CREATE_SUBJECT = 
	"CREATE TABLE subjects (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
	"title TEXT NOT NULL);";
  
  private static final String DATABASE_CREATE_NOTE = 
	"CREATE TABLE notes (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
	"title TEXT NOT NULL, content TEXT NOT NULL)" +
	"FOREIGN KEY(subject_id) REFERENCES subjects(_id);";
  
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
	
  }
  
  public DatabaseAdapter open () throws SQLException {
	plugDB = DBHelper.getWritableDatabase();
	return this;
  }
  
  public void close() {
	DBHelper.close();
  }
}
