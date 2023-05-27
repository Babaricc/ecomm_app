package com.example.ecomm_app;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class CustomDialog extends Dialog implements View.OnClickListener {
    Activity activity;
    Button yeBtn;
    String title, message;
    int rawRes;

    public CustomDialog(Activity activity) {
        super(activity);
        this.activity = activity;
        setCancelable(false);
        setCanceledOnTouchOutside(false);

    }

    public CustomDialog(Activity activity, String title, String message, int rawRes) {
        this(activity);
        this.title = title;
        this.message = message;
        this.rawRes = rawRes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        ((TextView) findViewById(R.id.dialog_title)).setText(title);
        ((TextView) findViewById(R.id.dialog_description)).setText(message);
        ((LottieAnimationView) findViewById(R.id.dialog_image)).setAnimation(rawRes);

        yeBtn = findViewById(R.id.dialog_btn);
        yeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.dismiss();
    }
}
