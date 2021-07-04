package com.example.lab5;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openMap = (Button)findViewById(R.id.openMap);
        openMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText teamaddress = (EditText)findViewById(R.id.address);
                Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q=" + teamaddress.getText());

                Intent mapIntent = new Intent(Intent.ACTION_VIEW,gmmIntentUri);

                mapIntent.setPackage("com.google.android.apps.maps");
                System.out.println(gmmIntentUri);
                startActivity(mapIntent);
            }
        });
    }
    ActivityResultLauncher<Intent> profileActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        ImageView avatarImage = (ImageView)findViewById(R.id.avatarImage);

                        String drawableName = "flag";
                        switch (data.getIntExtra("imageID",R.id.ca)){
                            case R.id.ca:
                                drawableName = "ca.png";
                                break;
                            case R.id.cn:
                                drawableName = "cn.png";
                                break;
                            case R.id.fr:
                                drawableName = "fr.png";
                                break;
                            case R.id.it:
                                drawableName = "it.png";
                                break;
                            case R.id.ja:
                                drawableName = "ja.png";
                                break;
                            case R.id.sp:
                                drawableName = "sp.png";
                                break;
                            case R.id.sw:
                                drawableName = "sw.png";
                                break;
                            case R.id.uk:
                                drawableName = "uk.png";
                                break;
                            case R.id.us:
                                drawableName = "us.png";
                                break;
                        }
                        int resID = getResources().getIdentifier(drawableName,"drawable",getPackageName());
                        avatarImage.setImageResource(resID);
                    }
                }
            }
    );

    public void OnSetAvatarButton(View view){
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        profileActivityResultLauncher.launch(intent);
    }

}