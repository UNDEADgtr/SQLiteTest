package by.undead.sqlitetest;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SimpleCursorAdapter;

import by.undead.sqlitetest.base.DBHelper;

import static android.provider.BaseColumns._ID;
import static by.undead.sqlitetest.base.BaseConstants.CONTENT_URI;
import static by.undead.sqlitetest.base.BaseConstants.TABLE_NAME;
import static by.undead.sqlitetest.base.BaseConstants.TIME;
import static by.undead.sqlitetest.base.BaseConstants.TITLE;

public class ListViewContentActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        addEvent("Hello, Android!");
        Cursor cursor = getEvents();
        showEvents(cursor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_view, menu);
        return true;
    }

    public void addEvent(String event) {
        ContentValues values = new ContentValues();
        values.put(TIME, System.currentTimeMillis());
        values.put(TITLE, event);
        getContentResolver().insert(CONTENT_URI, values);
    }

    public static String[] FROM = {_ID, TIME, TITLE};
    public static String ORDER_BY = TIME + " DESC";

    public Cursor getEvents() {
        return managedQuery(CONTENT_URI, FROM, null, null, ORDER_BY);
    }

    private static int[] TO = {R.id.rowid, R.id.time, R.id.title,};

    public void showEvents(Cursor cursor) {
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.item, cursor, FROM, TO);
        setListAdapter(adapter);
    }
}
