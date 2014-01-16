package com.eran.contentprovidertutorial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BooksDatabaseHelper extends SQLiteOpenHelper{
	public static final String TABLE_BOOKS = "t_books";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_AUTHOR = "author";
	public static final String DATABASE_NAME = "books.db";
	
	public static final int DATABASE_VERSION= 1;


	
	private static final String SQL_CREATE_MAIN = "CREATE TABLE " +
		    TABLE_BOOKS +                       // Table's name
		    "(" +                           // The columns in the table
		    COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " +
		    COLUMN_TITLE +" TEXT not null, " +
		    COLUMN_AUTHOR +" TEXT not null);";
	
	public BooksDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_MAIN);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(BooksDatabaseHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
		    onCreate(db);
		
	}

}
