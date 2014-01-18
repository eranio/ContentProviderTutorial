package com.eran.contentprovidertutorial;

import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity implements LoaderCallbacks<Cursor>{
	private SimpleCursorAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.getListView().setDividerHeight(2);
	    fillData();
//		BooksDao booksDao = new BooksDao(getApplicationContext());
//		booksDao.open();
//		Book book1 = booksDao.addBook("The Bible", "God");
//		Book book2 = booksDao.addBook("The Dog", "Doggy");
//		Book book3 = booksDao.addBook("The Cat", "Catty");
	}

	private void fillData() {
		String[] from = new String[] { BooksDatabaseHelper.COLUMN_TITLE };
	    // Fields on the UI to which we map
	    int[] to = new int[] { R.id.label };

	    getLoaderManager().initLoader(0, null, this);
	    adapter = new SimpleCursorAdapter(this, R.layout.book_row, null, from,
	        to, 0);

	    setListAdapter(adapter);
	    ContentResolver cr = getContentResolver();
	    cr.delete(BooksContentProvider.CONTENT_URI, "_id=?", new String[]{"9"});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		String[] projection = { BooksDatabaseHelper.COLUMN_ID, BooksDatabaseHelper.COLUMN_TITLE};
	    CursorLoader cursorLoader = new CursorLoader(this,
	        BooksContentProvider.CONTENT_URI, projection, null, null, null);
	    return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor data) {
		adapter.swapCursor(data);
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
		
	}

}
