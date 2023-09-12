package com.example.appcamera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.net.Uri;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = findViewById(R.id.edit1);
        edt2 = findViewById(R.id.edit2);
        btn_add = findViewById(R.id.btn_add);
        img = findViewById(R.id.imgset);
        listView = findViewById(R.id.listView);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImagePicker();
            }
        });
    }
    private void openImagePicker(){
        ImagePicker.with(MainActivity.this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            // Thêm dữ liệu vào cơ sở dữ liệu SQLite
            long newRowId = sqlHelper.addData(uri, edt1.getText().toString(), edt2.getText().toString());

            if (newRowId != -1) {
                Toast.makeText(MainActivity.this, "Thêm dữ liệu vào SQLite thành công", Toast.LENGTH_SHORT).show();

                // Cập nhật danh sách từ cơ sở dữ liệu và cập nhật ListView
                list = sqlHelper.getAllCameraData();
                PhotoAdapter adapter = new PhotoAdapter(R.layout.custom, MainActivity.this, list);
                listView.setAdapter(adapter);
            } else {
                Toast.makeText(MainActivity.this, "Thêm dữ liệu vào SQLite thất bại", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Xử lý khi không có dữ liệu được chọn hoặc khi xảy ra lỗi
            Toast.makeText(MainActivity.this, "Không có ảnh được chọn hoặc xảy ra lỗi", Toast.LENGTH_SHORT).show();
        }

    }
    private SqlHelper sqlHelper = new SqlHelper(MainActivity.this);
    private EditText edt1;
    private EditText edt2;
    private Button btn_add;
    private ListView listView;
    private ImageView img;
    private List<Camera> list = new ArrayList<>();
    private Button add_data;
}