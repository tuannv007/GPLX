package com.tuannv007.gplxb2.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tuannv007.gplxb2.data.model.Option1;
import com.tuannv007.gplxb2.data.model.Option2;
import com.tuannv007.gplxb2.data.model.Question;
import com.tuannv007.gplxb2.data.model.Category;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the databases connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the databases connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    /**
     * Read all quotes from the databases.
     *
     * @return a List of quotes
     */
    public List<Category> getZQuestionType() {
        List<Category> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM 'categories'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category = new Category();
            category.setzId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            category.setCount(cursor.getInt(2));
            list.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<Question> getQuestion(int categoryId) {
        List<Question> list = new ArrayList<>();
        Cursor cursor =
                database.rawQuery("SELECT * FROM stories WHERE cat_id = " + categoryId, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Question question = new Question();
            question.setId(cursor.getInt(0));
            question.setName(cursor.getString(1));
            question.setContent(cursor.getString(2));
            question.setCategoryId(cursor.getInt(3));
            list.add(question);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}