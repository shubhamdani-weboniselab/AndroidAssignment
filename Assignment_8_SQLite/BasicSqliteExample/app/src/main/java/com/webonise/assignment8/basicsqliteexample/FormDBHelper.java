package com.webonise.assignment8.basicsqliteexample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by webonise on 13/8/15.
 */
public class FormDBHelper extends SQLiteOpenHelper {

    public FormDBHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_USER_DETAILS + "(" + Constants.COLUMN_ID + " INTEGER  PRIMARY KEY AUTO-INCREMENT, "
                + Constants.COLUMN_NAME + " TEXT, " + Constants.COLUMN_AGE + " DOUBLE ," + Constants.COLUMN_HEIGHT + " DOUBLE , "
                + Constants.COLUMN_WEIGHT + " DOUBLE ) ";
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    public void addDetail(FormDB form) {
        SQLiteDatabase db = this.getWritableDatabase();
        String INSERT_INTO = " INSERT INTO " + Constants.TABLE_USER_DETAILS + " VALUES ( " + null + " , ' " + form.getName() + " ' , " + form.getAge() + " , " + form.getHeight() + " , " + form.getWeight() + " )";
        db.execSQL(INSERT_INTO);
        db.close();
    }

    public List<FormDB> getDetail() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<FormDB> formDBList = new ArrayList<FormDB>();
        String selectAll = "SELECT * FROM " + Constants.TABLE_USER_DETAILS;
        Cursor pointer = db.rawQuery(selectAll, null);
        pointer.moveToFirst();
        do {
            FormDB formDB = new FormDB();
            formDB.setId(pointer.getInt(Constants.ZERO_INDEX));
            formDB.setName(pointer.getString(Constants.FIRST_INDEX));
            formDB.setAge(pointer.getInt(Constants.SECOND_INDEX));
            formDB.setHeight(pointer.getDouble(Constants.THIRD_INDEX));
            formDB.setWeight(pointer.getDouble(Constants.FORTH_INDEX));
            formDBList.add(formDB);
        }
        while (pointer.moveToNext());
        return formDBList;
    }
}
