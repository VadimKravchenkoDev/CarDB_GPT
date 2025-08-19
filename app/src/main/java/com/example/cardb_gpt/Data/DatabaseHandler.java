package com.example.cardb_gpt.Data;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.cardb_gpt.Model.Car;
import com.example.cardb_gpt.Utils.Util;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CARS_TABLE = "CREATE TABLE "
                + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY, "
                + Util.KEY_NAME + " TEXT"
                + Util.KEY_PRICE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CARS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addCar(Car car) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, car.getName());
        contentValues.put(Util.KEY_PRICE, car.getPrice());

        database.insert(Util.TABLE_NAME, null, contentValues);
        database.close();
    }

    public Car getCar(int id) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(Util.TABLE_NAME, new String[]{Util.KEY_ID,
                        Util.KEY_NAME, Util.KEY_PRICE},
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)},
                null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return new Car(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2));
    }

    public List<Car> getAllCars(){
        SQLiteDatabase database = this.getReadableDatabase();

        List<Car> carsList = new ArrayList<>();

        String selectCars = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = database.rawQuery(selectCars, null);
        if(cursor.moveToFirst()){
            do{
                Car car = new Car();
                car.setId(cursor.getInt(0));
                car.setName(cursor.getString(1));
                car.setPrice(cursor.getString(2));

                carsList.add(car);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return carsList;
    }
    public int updateCar(Car car){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_NAME, car.getName());
        contentValues.put(Util.KEY_PRICE, car.getPrice());

        return db.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "+?",
                new String[]{String.valueOf(car.getId())});
    }
    public void deleteCar(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + " =?",
                new String[]{String.valueOf(car.getId())});

        db.close();
    }
}
