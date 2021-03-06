package com.example.apurpura.lifenavhelperapi;

/**
 * Created by apurpura on 5/30/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.apurpura.lifenavhelperapi.EventResultDBHelper.EventResultStrings.Action;
import static com.example.apurpura.lifenavhelperapi.EventResultDBHelper.EventResultStrings.CalendarId;
import static com.example.apurpura.lifenavhelperapi.EventResultDBHelper.EventResultStrings.CancelTime;
import static com.example.apurpura.lifenavhelperapi.EventResultDBHelper.EventResultStrings.EndTime;
import static com.example.apurpura.lifenavhelperapi.EventResultDBHelper.EventResultStrings.EventId;
import static com.example.apurpura.lifenavhelperapi.EventResultDBHelper.EventResultStrings.Level;
import static com.example.apurpura.lifenavhelperapi.EventResultDBHelper.EventResultStrings.Score;
import static com.example.apurpura.lifenavhelperapi.EventResultDBHelper.EventResultStrings.StartTime;
import static com.example.apurpura.lifenavhelperapi.EventResultDBHelper.EventResultStrings.Trophy;
import static com.example.apurpura.lifenavhelperapi.EventResultDBHelper.EventResultStrings._ID;

/**
 * Created by apurpura on 7/19/2015.
 */
public class EventResultDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DICTIONARY_TABLE_NAME = "EventResult";
    private static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                    _ID + " integer Primary Key autoincrement not null, " +
                    EventId + " TEXT not null, " +
                    EndTime + " TEXT, " +
                    CancelTime + " TEXT, " +
                    Level + " TEXT, " +
                    Score + " TEXT, " +
                    Action + " TEXT, " +
                    StartTime + " TEXT, " +
                    CalendarId + " TEXT, " +
                    Trophy + " TEXT)";
    private static final java.lang.String SQL_DELETE_ENTRIES = "DROP TABLE EventResult";
    private Context context;

    EventResultDBHelper(Context context) {
        super(context, DICTIONARY_TABLE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int i, int n) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /* Inner class that defines the table contents */
    public static class EventResultStrings implements BaseColumns {
        public static final String _ID = "u_id";
        public static final String StartTime = "StartTime";
        public static final String EndTime = "EndTime";
        public static final String CancelTime = "CancelTime";
        public static final String Level = "Level";
        public static final String Score = "Score";
        public static final String Action = "Action";
        public static final String EventId = "EventId";
        public static final String CalendarId = "CalendarId";
        public static final String Trophy = "Trophy";
    }

    public void insertEventResult(String startTime, String endTime, String cancelTime, String level,
                                  String score, String action, String eventId, String calendarId
            , Context context, String trophy) {
        String result = null;
        try {
            result = GetEventResult(eventId, context).eventId;
        } catch (Exception e) {

        }
        // Create a new map of values, where column names are the keys
        if (result == null) {
            ContentValues values = new ContentValues();
            values.put(EventId, eventId);
            values.put(StartTime, startTime);
            values.put(EndTime, endTime);
            values.put(CancelTime, cancelTime);
            values.put(Level, level);
            values.put(Score, score);
            values.put(Action, action);
            values.put(CalendarId, calendarId);
            values.put(Trophy, trophy);

            SQLiteDatabase db = new EventResultDBHelper(context).getWritableDatabase();
            db.insert("EventResult", null, values);
            db.close();
        }
    }

    public HashMap<String, List<EventResult>> GetEventResults(Context context, String calId) {

        HashMap<String, List<EventResult>> results = new HashMap<String, List<EventResult>>();
        SQLiteDatabase db = new EventResultDBHelper(context).getReadableDatabase();

        String[] projection = {
                EventId, StartTime, EndTime, CancelTime, Level, Score, Action, CalendarId, Trophy
        };

        String selection = CalendarId + " = ?";
        String[] selectionArgs = {calId};


        Cursor c = db.query(
                "EventResult",  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        if (c != null) {
            // move cursor to first row
            if (c.moveToFirst()) {
                do {
                    boolean insertList = false;
                    List<EventResult> ls;
                    String action = c.getString(c.getColumnIndex("Action"));
                    String calendarId = c.getString(c.getColumnIndex("CalendarId"));
                    if (action != "" & action != "n/a" & calendarId != "") {
                        if (!results.containsKey(action)) {
                            ls = new ArrayList<EventResult>();
                            insertList = true;
                        } else
                            ls = results.get(action);


                        EventResult er = new EventResult();
                        // Get version from Curso
                        String startTime = c.getString(c.getColumnIndex("StartTime"));
                        if (!startTime.equals("") && !startTime.toLowerCase().equals("n/a") && startTime != null)
                            try {
                                er.startTime = new SimpleDateFormat("MMM d, yyyy hh:mm:ss a").parse(startTime);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        String endTime = c.getString(c.getColumnIndex("EndTime"));
                        if (!endTime.equals("") && !endTime.toLowerCase().equals("n/a"))
                            try {
                                er.endTime = new SimpleDateFormat("MMM d, yyyy hh:mm:ss a").parse(endTime);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        String cancelTime = c.getString(c.getColumnIndex("CancelTime"));
                        if (!cancelTime.equals("") && !cancelTime.toLowerCase().equals("n/a") && cancelTime != null)
                            try {
                                er.cancelTime = new SimpleDateFormat("MMM d, yyyy hh:mm:ss a").parse(cancelTime);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        String level = c.getString(c.getColumnIndex("Level"));
                        if (!level.equals("") && !level.toLowerCase().equals("n/a") && level != null)
                            er.level = Integer.parseInt(level);
                        String score = c.getString(c.getColumnIndex("Score"));
                        if (!score.equals("") && !score.toLowerCase().equals("n/a") && score != null)
                            er.score = Integer.parseInt(score);
                        er.action = c.getString(c.getColumnIndex("Action"));
                        er.eventId = c.getString(c.getColumnIndex("EventId"));
                        er.calendarId = c.getString(c.getColumnIndex("CalendarId"));
                        er.trophy = c.getString(c.getColumnIndex("Trophy"));

                        // add the bookName into the bookTitles ArrayList
                        ls.add(er);
                        // move to next row
                        if (insertList)
                            results.put(action, ls);
                    }
                } while (c.moveToNext());
            }
        }
        c.close();
        db.close();

        return results;
    }

    public EventResult GetEventResult(String eventId, Context context) {
        EventResult er = new EventResult();
        SQLiteDatabase db = new EventResultDBHelper(context).getReadableDatabase();

        String[] projection = {
                EventId, StartTime, EndTime, CancelTime, Level, Score, Action, CalendarId, Trophy
        };

        String selection = EventId + " = ?";
        String[] selectionArgs = {eventId};


        Cursor c = db.query(
                "EventResult",  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        if (c != null) {
            // move cursor to first row
            if (c.moveToFirst()) {
                // Get version from Curso
                String startTime = c.getString(c.getColumnIndex("StartTime"));
                if (startTime != "" & startTime.toLowerCase() != "n/a")
                    try {
                        er.startTime = new SimpleDateFormat("MMM d, yyyy HH:mm:ss a").parse(startTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                String endTime = c.getString(c.getColumnIndex("EndTime"));
                if (endTime != "" & endTime.toLowerCase() != "n/a")
                    try {
                        er.endTime = new SimpleDateFormat("MMM d, yyyy HH:mm:ss a").parse(endTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                String cancelTime = c.getString(c.getColumnIndex("CancelTime"));
                if (cancelTime != "" & cancelTime.toLowerCase() != "n/a")
                    try {
                        er.cancelTime = new SimpleDateFormat("MMM d, yyyy HH:mm:ss a").parse(cancelTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                String level = c.getString(c.getColumnIndex("Level"));
                if (!level.equals("") & !level.toLowerCase().equals("n/a") & level != null)
                    er.level = Integer.getInteger(level);
                String score = c.getString(c.getColumnIndex("Score"));
                if (!score.equals("") & !score.toLowerCase().equals("n/a") & score != null)
                    er.score = Integer.parseInt(score);
                er.action = c.getString(c.getColumnIndex("Action"));
                er.eventId = c.getString(c.getColumnIndex("EventId"));
                er.calendarId = c.getString(c.getColumnIndex("CalendarId"));
                er.trophy = c.getString(c.getColumnIndex("Trophy"));
            }
        }
        db.close();
        c.close();
        return er;
    }
}

