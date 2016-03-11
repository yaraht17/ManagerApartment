package com.ezbms.building.buildingapp.db;

/**
 * Created by Hoang on 2/27/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;


import com.ezbms.building.buildingapp.util.AppUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SQLiteAdapter {

    // Others
    /** The Constant AND_CONDITION. */
    public static final String AND = " and ";

    /** The Constant OR_CONDITION. */
    public static final String OR = " or ";

    /** The Constant ASC. */
    public static final String ASC = " ASC ";

    /** The Constant DESC. */
    public static final String DESC = " DESC ";



    /** The sq lite helper. */
    private SQLiteHelper sqLiteHelper;

    /** The sq lite database. */
    private SQLiteDatabase sqLiteDatabase;

    /** The context. */
    private Context context;

    /** The db. */
    private static SQLiteAdapter db;

    /**
     * Instantiates a new SQlite adapter.
     *
     * @param context
     *            the context
     */
    private SQLiteAdapter(Context context) {
        this.context = context;
    }

    /**
     * Gets the single instance of SQLiteAdapter.
     *
     * @param context
     *            the context
     * @return single instance of SQLiteAdapter
     */
    public static SQLiteAdapter getInstance(Context context) {
        if (db == null) {
            db = new SQLiteAdapter(context);
        }
        db.open();
        return db;
    }

    /**
     * Gets the count.
     *
     * @param table
     *            the table
     * @return the count
     */
    public long getCount(String table) {
        long count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM " + table;
            SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
            count = statement.simpleQueryForLong();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            count = 0;
        }
        return count;
    }

    /**
     * Open.
     *
     * @return the SQ lite adapter
     * @throws SQLException
     *             the SQL exception
     */
    private SQLiteAdapter open() throws android.database.SQLException {
        if (!isReady()) {
            int dbVersion = AppUtil.getVersionCode(context);
            sqLiteHelper = new SQLiteHelper(context, AppUtil.TAG, null,
                    dbVersion);
            sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        }
        return this;
    }

    /**
     * Checks if is ready.
     *
     * @return true, if is ready
     */
    public boolean isReady() {
        return (sqLiteDatabase != null && sqLiteDatabase.isOpen());
    }

    /**
     * Close.
     */
    public void close() {
        try {
            sqLiteHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*//

         private static final String SCRIPT_CREATE_TABLE_BOARD = "create table if not exists "
            + TABLE_BOARD
            + " ("
            + KEY_ID
            + " integer primary key autoincrement, "
            + KEY_NAME
            + " text, "
            + KEY_USER
            + " text, "
            + KEY_JSON
            + " text);";

    private static final String[] COLUMN_MESSAGE = { KEY_ID,KEY_NAME,KEY_USER,KEY_JSON };

       public static final String TABLE_BOARD = "TABLE_BOARD";
       public static final String KEY_ID = "_id";

     add Notification
    //SQLiteAdapter.getInstance(activity).addNotification(notificationEntity);
    public long addNotification(NotificaitonEntity notificationEntity) {
        Cursor cursor = sqLiteDatabase.query(TABLE_BOARD, COLUMN_MESSAGE,
                KEY_NAME + "=?"+ AND +
                        KEY_USER + "=?",
                new String[]{notificationEntity.getTitle(),sessionManager.get_user()},
                null, null, null);
        if(cursor != null){
            if(cursor.getCount() == 0){
                return sqLiteDatabase.insert(TABLE_BOARD, null,
                        getNotificationValues(notificationEntity));
            }
            cursor.close();
        }
        return 0;
    }

    private ContentValues getNotificationValues(NotificaitonEntity notificationEntity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, notificationEntity.getTitle());
        contentValues.put(KEY_USER, sessionManager.get_user());
        contentValues.put(KEY_JSON, notificationEntity.getJson());
        return contentValues;
    }

    public boolean checkExistNotification(String title) {
        Cursor cursor = sqLiteDatabase.query(TABLE_BOARD, COLUMN_MESSAGE,
                KEY_NAME + "=?",
                new String[] { title },
                null, null,
                null);
        if (cursor.getCount() > 0)
            return true;
        return false;
    }

    //lay NotificaitonEntity dung voi sessionManager
    public ArrayList<NotificaitonEntity> getAllNotifications() {
        ArrayList<NotificaitonEntity> list = new ArrayList<NotificaitonEntity>();
        Cursor cursor = sqLiteDatabase.query(TABLE_BOARD, COLUMN_MESSAGE,
                KEY_USER + "=?",
                new String[]{sessionManager.get_user()},
                null, null,
                null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                list.add(getNotification(cursor));
            }
            cursor.close();
        }
        return list;
    }

    public NotificaitonEntity getNotification(Cursor cursor) {
        NotificaitonEntity obj = null;
        try {
            obj = new NotificaitonEntity(new JSONObject(cursor.getString(cursor.getColumnIndex(KEY_JSON))));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public int deleteBoard(NotificaitonEntity obj) {
        return sqLiteDatabase.delete(TABLE_BOARD,
                KEY_NAME + "=?",
                new String[] {obj.getTitle()});
    }

    Trong SQLiteHelper:
     @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SCRIPT_CREATE_TABLE_BOARD);
        }
    */

    /**
     * The Class SQLiteHelper.
     */
    public class SQLiteHelper extends SQLiteOpenHelper {

        /**
         * Instantiates a new SQ lite helper.
         *
         * @param context
         *            the context
         * @param name
         *            the name
         * @param factory
         *            the factory
         * @param version
         *            the version
         */
        public SQLiteHelper(Context context, String name,
                            CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        /*
         * (non-Javadoc)
         *
         * @see
         * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database
         * .sqlite.SQLiteDatabase)
         */
        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        /*
         * (non-Javadoc)
         *
         * @see
         * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database
         * .sqlite.SQLiteDatabase, int, int)
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onCreate(db);
        }
    }
}
