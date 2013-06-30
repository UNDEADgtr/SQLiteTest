package by.undead.sqlitetest.base;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Lenovo on 30.06.13.
 */
public interface BaseConstants extends BaseColumns {

    public static final String TABLE_NAME = "events";

    public static final String TIME = "time" ;
    public static final String TITLE = "title" ;

    public static final String AUTHORITY = "by.undead.sqlitetest" ;
    public static final Uri CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + TABLE_NAME);
}
