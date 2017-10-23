package com.example.ivan.smartas.AddActivities;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.ivan.smartas.R;
import com.mindorks.paracamera.Camera;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class AddDescription extends AppCompatActivity {

    Toolbar toolbar;
    EditText description;
    Button next;
    LinearLayout photoContainer;
    ArrayList<Uri> arrayList = new ArrayList<>();
    ArrayList<String> arrayListString = new ArrayList<>();
    ArrayList<Bitmap> arrayListBitmap = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_description);

        toolbar = (Toolbar) findViewById(R.id.add_description_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Добавьте описание");

        description = (EditText) findViewById(R.id.add_description_description);
        next = (Button) findViewById(R.id.add_description_next);
        photoContainer = (LinearLayout) findViewById(R.id.add_description_photo_container);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OrderPreShow.class);
                Bundle extras = new Bundle();
                String desctiptionText = description.getText().toString();
                extras.putString("task_description", desctiptionText);
                extras.putString("task_name", "task_name");
                extras.putString("task_type", "task_type");
                extras.putString("task_cost", "task_cost");
                extras.putString("task_date", "task_date");
                extras.putString("task_limit", "task_limit");
                for(int i = 0; i < photoContainer.getChildCount(); i++) {
                    extras.putString("file_name" + i, names.get(i));
                }
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        Toast.makeText(getApplicationContext(), Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.description_menu, menu);
        return true;
    }

    public String getFilename(){
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return name + ".jpg";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.add_photo_gallery:
                Intent intentGal = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentGal, 0);
                break;
            case R.id.add_photo_camera:
                String name = "ali_" + System.currentTimeMillis();
                camera = new Camera.Builder()
                        .resetToCorrectOrientation(true)// it will rotate the camera bitmap to the correct orientation from meta data
                        .setTakePhotoRequestCode(1)
                        .setDirectory("pics")
                        .setName(name)
                        .setImageFormat(Camera.IMAGE_JPEG)
                        .setCompression(75)
                        .setImageHeight(1000)// it will try to achieve this height as close as possible maintaining the aspect ratio;
                        .build(this);
                names.add("file:///data/data/com.example.ivan.smartas/files/pics/" + name + ".jpeg");
                try {
                    camera.takePicture();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0){
            try {
                Uri uri = data.getData();
                getLayoutInflater().inflate(R.layout.photo_item, photoContainer);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                ((ImageView) photoContainer.getChildAt(photoContainer.getChildCount() - 1).findViewById(R.id.photo_item)).setImageBitmap(bitmap);
                names.add(uri.toString());
                Toast.makeText(getApplicationContext(), uri.toString(), Toast.LENGTH_LONG).show();
            }catch (IOException e){;}
        }

        if(requestCode == Camera.REQUEST_TAKE_PHOTO){
            Bitmap bitmap = camera.getCameraBitmap();
            if(bitmap != null) {
                getLayoutInflater().inflate(R.layout.photo_item, photoContainer);
                ((ImageView) photoContainer.getChildAt(photoContainer.getChildCount() - 1).findViewById(R.id.photo_item)).setImageBitmap(bitmap);
            }else{
                Toast.makeText(this.getApplicationContext(),"Picture not taken!",Toast.LENGTH_SHORT).show();
            }
        }

        return;

        /*switch (requestCode){
            case 0:
                if (resultCode == RESULT_OK) {
                    //Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    try {
                        //Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), arrayList.get(arrayList.size() - 1));
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        getLayoutInflater().inflate(R.layout.photo_item, photoContainer);
                        ((ImageView) photoContainer.getChildAt(photoContainer.getChildCount() - 1).findViewById(R.id.photo_item)).setImageBitmap(bitmap);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] bytes = byteArrayOutputStream.toByteArray();
                        Toast.makeText(getApplicationContext(), arrayList.get(arrayList.size() - 1).getPath(), Toast.LENGTH_LONG).show();
                        arrayListBitmap.add(bitmap);
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), "error == " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case 1:
                if(resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    getLayoutInflater().inflate(R.layout.photo_item, photoContainer);
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        ((ImageView) photoContainer.getChildAt(photoContainer.getChildCount() - 1).findViewById(R.id.photo_item)).setImageBitmap(bitmap);
                        arrayListBitmap.add(bitmap);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] bytes = byteArrayOutputStream.toByteArray();
                        Toast.makeText(getApplicationContext(), uri.getPath(), Toast.LENGTH_LONG).show();
                    }catch (IOException e){;}
                }
                break;
        }*/
    }
}
