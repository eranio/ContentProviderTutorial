package com.eran.contentprovidertutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BooksDao {
	private SQLiteDatabase database;
	private BooksDatabaseHelper dbHelper;
	private String[] allColumns = { BooksDatabaseHelper.COLUMN_ID,
									BooksDatabaseHelper.COLUMN_TITLE, 
									BooksDatabaseHelper.COLUMN_AUTHOR};
	
	public BooksDao(Context context){
		dbHelper = new BooksDatabaseHelper(context);
	}
	
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();
	}
	public void close() {
		dbHelper.close();
	}
	
	public Book addBook(String title, String author){
		ContentValues values = new ContentValues();
		values.put(BooksDatabaseHelper.COLUMN_TITLE, title);
		values.put(BooksDatabaseHelper.COLUMN_AUTHOR, author);
		long insert_id = database.insert(BooksDatabaseHelper.TABLE_BOOKS, null, values);
		Cursor cursor = database.query(BooksDatabaseHelper.TABLE_BOOKS, allColumns, BooksDatabaseHelper.COLUMN_ID + "=" + insert_id, null, null, null, null);
		cursor.moveToFirst();
		Book book = cursorToBook(cursor);
		cursor.close();
		return book;
	}
	
	private Book cursorToBook(Cursor cursor) {
	    Book book= new Book();
	    book.setId(cursor.getLong(0));
	    book.setTitle(cursor.getString(1));
	    book.setAuthor(cursor.getString(2));
	    return book;
	  }
}
