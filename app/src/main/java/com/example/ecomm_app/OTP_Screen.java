package com.example.ecomm_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP_Screen extends AppCompatActivity {

    EditText emailPhone;
    Button sendOtpBtn, verifyOtpBtn;
    TextView resendLink, textViewError;

    View verify_otp_section;

    FirebaseAuth mAuth;
    String verificationId;

    CustomDialog customDialog;
    EditText editText1, editText2, editText3, editText4, editText5, editText6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);

        mAuth = FirebaseAuth.getInstance();

        sendOtpBtn = findViewById(R.id.send_otp_btn);
        verifyOtpBtn = findViewById(R.id.btnVerify);

        emailPhone = findViewById(R.id.editEmailPhone);

        verify_otp_section = findViewById(R.id.verify_otp_section);
        resendLink = findViewById(R.id.resendLink);

        editText1 = findViewById(R.id.otpB1);
        editText2 = findViewById(R.id.otpB2);
        editText3 = findViewById(R.id.otpB3);
        editText4 = findViewById(R.id.otpB4);
        editText5 = findViewById(R.id.otpB5);
        editText6 = findViewById(R.id.otpB6);

        textViewError = findViewById(R.id.textViewError);

        sendOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNumber = emailPhone.getText().toString();
                if (mobileNumber.length() != 11 && !mobileNumber.isEmpty()) {
                    textViewError.setVisibility(View.VISIBLE);
                    return;
                }

                PhoneAuthOptions options =
                        PhoneAuthOptions.newBuilder(mAuth)
                                .setPhoneNumber(mobileNumber)       // Phone number to verify
                                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                .setActivity(OTP_Screen.this).setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                                        Log.d("onVerificationCompleted", "onVerificationCompleted:" + credential);
                                        signInWithPhoneAuthCredential(credential);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String code, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                                        Log.d("", "onCodeSent:" + code);
                                        verificationId = code;
                                    }
                                })
                                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);

            }
        });

        verifyOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (
                        editText1.getText().toString().isEmpty() ||
                                editText2.getText().toString().isEmpty() ||
                                editText3.getText().toString().isEmpty() ||
                                editText4.getText().toString().isEmpty() ||
                                editText5.getText().toString().isEmpty() ||
                                editText6.getText().toString().isEmpty()
                ) {

                    return;
                } else {

                    String userEnteredOTP = editText1.getText().toString() + editText2.getText().toString() + editText3.getText().toString() + editText4.getText().toString() + editText5.getText().toString() + editText6.getText().toString();
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, userEnteredOTP);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            customDialog = new CustomDialog(OTP_Screen.this,"OTP Verification","OTP Verification Successful!",R.raw.success);
                            customDialog.show();
                        } else {
                            customDialog = new CustomDialog(OTP_Screen.this,"OTP Verification","OTP Verification Failed, try again!",R.raw.failure);

                        }
                    }
                });
    }

}