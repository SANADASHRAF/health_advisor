package com.example.health_advisor.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.health_advisor.objects.meals;
import com.example.health_advisor.objects.user;
import com.example.health_advisor.objects.meals;
import com.example.health_advisor.objects.user;

import java.util.ArrayList;

public class my_database extends SQLiteOpenHelper {
    public static final String DB_Name = "Aklny_database";
    public static final int DB_Verion = 1;
    public static final String meal_tb_name = "meal";
    public static final String meal_cln_name = "name";
    public static final String meal_cln_rate = "rate";
    public static final String meal_cln_details = "details";
    public static final String meal_cln_color = "color";
    public static final String meal_cln_id = "id";
    public static final String meal_cln_image = "image";
    public static final String meal_cln_categorie = "categorie";
    public static final String meal_cln_favourite = "favourite";
    public static final String static_tb_name = "static";
    public static final String static_cl_name_pk = "static_px";

    public static final String user_tb_name = "user";
    public static final String user_cln_username = "username";
    public static final String user_cln_password = "password";
    public static final String user_cln_location = "location";
    public static final String user_cln_img = "img";
    public static final String user_cln_phone = "phone";

    public my_database(@Nullable Context context) {
        super(context, DB_Name, null, DB_Verion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT ,%s TEXT,%s INT,%s TEXT,%s INT,%s TEXT,%s TEXT,%s TEXT)", meal_tb_name, meal_cln_name,meal_cln_image, meal_cln_details, meal_cln_rate, meal_cln_categorie,meal_cln_color ,meal_cln_favourite));
        sqLiteDatabase.execSQL(String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY,%s TEXT,%s TEXT,%s TEXT,%s TEXT)", user_tb_name, user_cln_username,user_cln_password,  user_cln_phone, user_cln_location,user_cln_img));

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(String.format("DROP Table %s if exist", meal_tb_name));
        sqLiteDatabase.execSQL(String.format("DROP Table %s if exist", user_tb_name));
        onCreate(sqLiteDatabase);
    }

    //handle insert new Meal
    public boolean insertNewMeal(meals meal) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(meal_cln_name, meal.getName());
        cv.put(meal_cln_image, meal.getImg());
        cv.put(meal_cln_details, meal.getDetails());
        cv.put(meal_cln_rate, meal.getRate());
        cv.put(meal_cln_categorie, meal.getCategorie_name());
        cv.put(meal_cln_favourite, meal.getFavourite());
        cv.put(meal_cln_color, meal.getColor());
        long l = db.insert(meal_tb_name, null, cv);
        return l != -1;
    }


    public boolean getStatic(){
        SQLiteDatabase db = getReadableDatabase();
        String p="no";
        Cursor cursorMeals = db.rawQuery("SELECT * FROM " + static_tb_name, null);
        if (cursorMeals.moveToFirst()) {
            do {
                p=cursorMeals.getString(1);
            } while (cursorMeals.moveToNext());
        }
        cursorMeals.close();
        return p!="no";
    }

    //handle insert new user
    public boolean insertNewUser(user user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(user_cln_username, user.getUsername());
        cv.put(user_cln_password, user.getPassword());
        cv.put(user_cln_location, user.getLocation());
        cv.put(user_cln_phone, user.getPhone());
        cv.put(user_cln_img, user.getImg_uri());
        long l = db.insert(user_tb_name, null, cv);
        return l != -1;
    }

    //select all meals from database
    public ArrayList<meals> getMeals() throws SQLException {
        ArrayList<meals> m_list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorMeals = db.rawQuery("SELECT * FROM " + meal_tb_name, null);
        if (cursorMeals.moveToFirst()) {
            do {
                m_list.add(new meals(cursorMeals.getInt(0),cursorMeals.getString(1),
                        cursorMeals.getInt(2),cursorMeals.getString(3),cursorMeals.getInt(4),
                        cursorMeals.getString(5),cursorMeals.getInt(6),cursorMeals.getString(7)));
            } while (cursorMeals.moveToNext());
        }
        cursorMeals.close();
        return m_list;
    }

    //select specifc meals from database
    public ArrayList<meals> getMeals(String regin) throws SQLException {
        ArrayList<meals> m_list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorMeals = db.rawQuery(String.format("SELECT * FROM %s WHERE %s LIKE %s", meal_tb_name, meal_cln_name, regin), null);
        if (cursorMeals.moveToFirst()) {
            do {
                m_list.add(new meals(cursorMeals.getInt(0),cursorMeals.getString(1),
                        cursorMeals.getInt(2),cursorMeals.getString(3),cursorMeals.getInt(4),
                        cursorMeals.getString(5),cursorMeals.getInt(6),cursorMeals.getString(7)));
            } while (cursorMeals.moveToNext());
        }
        cursorMeals.close();
        return m_list;
    }

    //select specifq meals from database
    public ArrayList<meals> getMealsWithid(int num1, int num2) throws SQLException {
        ArrayList<meals> m_list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorMeals = db.rawQuery("SELECT * FROM " + meal_tb_name + " WHERE " + meal_cln_id + " BETWEEN " + num1 + " AND " + num2, null);
        if (cursorMeals.moveToFirst()) {
            do {
                m_list.add(new meals(cursorMeals.getInt(0),cursorMeals.getString(1),
                        cursorMeals.getInt(2),cursorMeals.getString(3),cursorMeals.getInt(4),
                        cursorMeals.getString(5),cursorMeals.getInt(6),cursorMeals.getString(7)));
            } while (cursorMeals.moveToNext());
        }
        cursorMeals.close();
        return m_list;
    }

    //select row data with id
    public meals getMealsWithid(int id) throws SQLException {
        meals m = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorMeals = db.rawQuery("SELECT * FROM " + meal_tb_name + " WHERE " + meal_cln_id + " = " + id, null);
        if (cursorMeals.moveToFirst()) {
            do {
                m=new meals(cursorMeals.getInt(0),cursorMeals.getString(1),
                        cursorMeals.getInt(2),cursorMeals.getString(3),cursorMeals.getInt(4),
                        cursorMeals.getString(5),cursorMeals.getInt(6),cursorMeals.getString(7));
            } while (cursorMeals.moveToNext());
        }
        cursorMeals.close();
        return m;
    }


    //select all meals from database
    public ArrayList<meals> getFavMeals() throws SQLException {
        ArrayList<meals> m_list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorMeals = db.rawQuery("SELECT * FROM " + meal_tb_name +" WHERE "+meal_cln_favourite + " = 'yes'", null);
        if (cursorMeals.moveToFirst()) {
            do {
                m_list.add(new meals(cursorMeals.getInt(0),cursorMeals.getString(1),
                        cursorMeals.getInt(2),cursorMeals.getString(3),cursorMeals.getInt(4),
                        cursorMeals.getString(5),cursorMeals.getInt(6),cursorMeals.getString(7)));
            } while (cursorMeals.moveToNext());
        }
        cursorMeals.close();
        return m_list;
    }
    public meals getFavMealsWithid(int id) throws SQLException {
        meals m = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorMeals = db.rawQuery("SELECT * FROM " + meal_tb_name +" WHERE "+meal_cln_favourite + " = 'yes'", null);
        do {
            m=new meals(cursorMeals.getInt(0),cursorMeals.getString(1),
                    cursorMeals.getInt(2),cursorMeals.getString(3),cursorMeals.getInt(4),
                    cursorMeals.getString(5),cursorMeals.getInt(6),cursorMeals.getString(7));
        } while (cursorMeals.moveToNext());
        cursorMeals.close();
        return m;
    }
    public boolean setFavMealWithID(meals meal) throws SQLException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(meal_cln_favourite, meal.getFavourite());
        String args[]={meal.getId()+""};
        long b =db.update(meal_tb_name,cv,"id=?",args);
        return b !=-1;
    }
    public meals getMealWithName(String name){
        meals m = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorMeals = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = '%s'", meal_tb_name, meal_cln_name, name), null);
        if (cursorMeals.moveToFirst()) {
            do {
                m=new meals(cursorMeals.getInt(0),cursorMeals.getString(1),
                        cursorMeals.getInt(2),cursorMeals.getString(3),cursorMeals.getInt(4),
                        cursorMeals.getString(5),cursorMeals.getInt(6),cursorMeals.getString(7));
            } while (cursorMeals.moveToNext());
        }
        cursorMeals.close();
        return m;
    }
    //search meals
    public ArrayList<meals> searchMealsWithname(String name){
        ArrayList newMeals = new ArrayList();
        ArrayList<meals> m_list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorMeals = db.rawQuery("SELECT * FROM " + meal_tb_name +" WHERE "+meal_cln_name + " like '"+ name+"%'" ,null);
        if (cursorMeals.moveToFirst()) {
            do {
                m_list.add(new meals(cursorMeals.getInt(0),cursorMeals.getString(1),
                        cursorMeals.getInt(2),cursorMeals.getString(3),cursorMeals.getInt(4),
                        cursorMeals.getString(5),cursorMeals.getInt(6),cursorMeals.getString(7)));
            } while (cursorMeals.moveToNext());
        }
        cursorMeals.close();
        return m_list;
    }
    //delete meal
    public boolean delteMealWithid(int id){
        String args[]={String.valueOf(id)};
        SQLiteDatabase db = getWritableDatabase();
        long b=db.delete(meal_tb_name,"id=?",args);
        return b!=-1;
    }



    public user getUsernWithUsername(String username) throws SQLException {
        user m = new user("none","none","none","none","none");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorUSers = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = '%s'", user_tb_name, user_cln_username, username), null);
        if (cursorUSers.moveToFirst()) {
            do {
                m = new user(cursorUSers.getString(0),cursorUSers.getString(1),cursorUSers.getString(2),cursorUSers.getString(3),cursorUSers.getString(4));
            } while (cursorUSers.moveToNext());
        }
        cursorUSers.close();
        return m;
    }
}


