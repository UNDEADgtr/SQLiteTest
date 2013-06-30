package by.undead.sqlitetest;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import by.undead.sqlitetest.base.DBHelper;

import static android.provider.BaseColumns._ID;
import static by.undead.sqlitetest.base.BaseConstants.TABLE_NAME;
import static by.undead.sqlitetest.base.BaseConstants.TIME;
import static by.undead.sqlitetest.base.BaseConstants.TITLE;

public class ListViewActivity extends ListActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        dbHelper = new DBHelper(this);

        try {
            addEvent("Hello, Android!");
            Cursor cursor = getEvents();
            showEvents(cursor);
        } finally {
            dbHelper.close();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_view, menu);
        return true;
    }

    public void addEvent(String event) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIME, System.currentTimeMillis());
        values.put(TITLE, event);
        db.insertOrThrow(TABLE_NAME, null, values);
    }

    public static String[] FROM = {_ID, TIME, TITLE};
    public static String ORDER_BY = TIME + " DESC";

    public Cursor getEvents() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);
        startManagingCursor(cursor);
        return cursor;
    }

    private static int[] TO = { R.id.rowid, R.id.time, R.id.title, };

    public void showEvents(Cursor cursor) {
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.item, cursor, FROM, TO);
        setListAdapter(adapter);
    }
}
