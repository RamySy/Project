package com.resto.project;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PassRes extends AppCompatActivity {

    @BindView(R.id.btn_back)
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_res);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PassRes.this.finish();
            }
        });
    }
}
