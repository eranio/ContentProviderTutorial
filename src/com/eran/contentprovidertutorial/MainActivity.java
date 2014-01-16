package com.eran.contentprovidertutorial;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BooksDao booksDao = new BooksDao(getApplicationContext());
		booksDao.open();
		Book book1 = booksDao.addBook("The Bible", "God");
		Book book2 = booksDao.addBook("The Dog", "Doggy");
		Book book3 = booksDao.addBook("The Cat", "Catty");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
