package com.example.appcamera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class SqlHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "mydatabase.db";
        private static final int DATABASE_VERSION = 1;

        // Tên bảng và các cột
        public static final String TABLE_NAME = "mytable";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_IMAGE_URL = "image_url";
        public static final String COLUMN_TEXT1 = "text1";
        public static final String COLUMN_TEXT2 = "text2";

        // Câu lệnh tạo bảng
        private static final String TABLE_CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_IMAGE_URL + " TEXT, " +
                        COLUMN_TEXT1 + " TEXT, " +
                        COLUMN_TEXT2 + " TEXT);";

        public SqlHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Xử lý khi nâng cấp cơ sở dữ liệu (nếu cần)
        }
    public long addData(Uri imageUrl, String text1, String text2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Chuyển đổi Uri thành chuỗi và lưu vào cột COLUMN_IMAGE_URL
        String imageUrlString = imageUrl.toString();
        values.put(COLUMN_IMAGE_URL, imageUrlString);
        values.put(COLUMN_TEXT1, text1);
        values.put(COLUMN_TEXT2, text2);

        // Thêm bản ghi vào bảng và trả về ID của bản ghi mới được thêm
        long newRowId = -1;
        try {
            newRowId = db.insert(TABLE_NAME, null, values);
        } catch (SQLException e) {
            // Xử lý lỗi khi thêm dữ liệu
            e.printStackTrace();
        } finally {
            db.close();
        }

        return newRowId;
    }
    public List<Camera> getAllCameraData() {
        List<Camera> cameraList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_IMAGE_URL,
                COLUMN_TEXT1,
                COLUMN_TEXT2
        };

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEXT2));
            String content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEXT1));
            String imageSourceUriString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_URL));
            Uri imageSource = Uri.parse(imageSourceUriString);
            Camera camera = new Camera(description, content, imageSource);
            cameraList.add(camera);
        }

        cursor.close();
        db.close();

        return cameraList;
    }
    }

