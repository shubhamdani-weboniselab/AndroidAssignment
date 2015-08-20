package com.webonise.assignment10.basicfragmentanimationexample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by webonise on 14/8/15.
 */
public class PersonDatabaseHelper extends SQLiteOpenHelper {


    public PersonDatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DB_VERSION, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_PERSON_DETAILS + " ( " + Constants.COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
                + Constants.COLUMN_NAME + " TEXT, " + Constants.COLUMN_AGE + " INTEGER ," + Constants.COLUMN_HEIGHT + " DOUBLE , "
                + Constants.COLUMN_WEIGHT + " DOUBLE ) ";
        Log.d("", CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    public void addPersonDetailToDB(PersonDetails personDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        String INSERT_INTO = " INSERT INTO " + Constants.TABLE_PERSON_DETAILS + " VALUES ( " + null + " , ' " + personDetails.getName() + " ' , " + personDetails.getAge() + " , " + personDetails.getHeight() + " , " + personDetails.getWeight() + " )";
        db.execSQL(INSERT_INTO);
        db.close();
    }

    public void deleteFromDataBase(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_PERSON_DETAILS, Constants.COLUMN_ID + "=" + id, null);
        db.close();
    }


    public List<PersonDetails> getPersonDetailFromDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<PersonDetails> personDetailsList = new ArrayList<PersonDetails>();

        String selectCount = "SELECT COUNT(*) FROM " + Constants.TABLE_PERSON_DETAILS;
        Cursor pointer = db.rawQuery(selectCount, null);
        pointer.moveToFirst();
        if (pointer.getInt(Constants.ZERO) > Constants.ZERO) {
            String selectAll = "SELECT * FROM " + Constants.TABLE_PERSON_DETAILS;
            pointer = db.rawQuery(selectAll, null);
            pointer.moveToFirst();
            do {
                PersonDetails personDetails = new PersonDetails();
                personDetails.setId(pointer.getInt(pointer.getColumnIndex(Constants.COLUMN_ID)));
                personDetails.setName(pointer.getString(Constants.FIRST_INDEX));
                personDetails.setAge(pointer.getInt(Constants.SECOND_INDEX));
                personDetails.setHeight(pointer.getDouble(Constants.THIRD_INDEX));
                personDetails.setWeight(pointer.getDouble(Constants.FORTH_INDEX));
                personDetailsList.add(personDetails);
            }
            while (pointer.moveToNext());
        } else {
            PersonDetails personDetails = new PersonDetails();
            personDetails.setName(Constants.NO_DATA);
            personDetailsList.add(personDetails);
        }
        return personDetailsList;
    }


}
