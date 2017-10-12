package com.example.user.androidcv;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Object.*;

public class MainActivity extends Activity {

    private Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int CAM_REQUEST = 1;
        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.image_view);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(file));

                startActivityForResult(camera_intent,CAM_REQUEST);
            }
        });
    }

    private File getFile(){
        File folder = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");
        if(!folder.exists()){
            if(!folder.mkdir())
                return null;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File image_file = new File(folder,"CamImage.jpg");
        return image_file;
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        String path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).toString();
        imageView.setImageDrawable(Drawable.createFromPath(path));
    }
}