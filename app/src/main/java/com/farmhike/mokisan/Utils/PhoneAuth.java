package com.farmhike.mokisan.Utils;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.farmhike.mokisan.Activities.CompleteYourProfile;
import com.farmhike.mokisan.Models.AppContext;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneAuth {

    private static PhoneAuth obj;
    private static FirebaseAuth firebaseAuth;
    private static PhoneAuthProvider.ForceResendingToken mResendToken;
    private static String mVerificationId;
    PhoneAuthCredential credential;
    /***************/
    private EditText _edittext_OTP;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                _edittext_OTP.setText(code);
                // editTextCode.setText(code);
                //verifying the code
                //PhoneAuth.getInstance().verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.e("onVerificationFailed: ", "" + e.getMessage());
            Toast.makeText(AppContext.getInstance().getContext(), "" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            mVerificationId = s;
            mResendToken = forceResendingToken;
            Toast.makeText(AppContext.getInstance().getContext(), "OTP Sent", Toast.LENGTH_SHORT).show();
        }
    };

    private PhoneAuth() {
    }

    public static PhoneAuth getInstance() {
        if (obj == null) {
            obj = new PhoneAuth();
            return obj;
        } else
            return obj;
    }

    public static FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public static void setFirebaseAuth(FirebaseAuth firebaseAuth) {
        PhoneAuth.firebaseAuth = firebaseAuth;
    }

    public EditText get_edittext_OTP() {
        return _edittext_OTP;
    }

    public void set_edittext_OTP(EditText _edittext_OTP) {
        this._edittext_OTP = _edittext_OTP;
    }

    /****************/

    public void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    public PhoneAuthCredential getCredential() {
        return credential;
    }

    private void verifyVerificationCode(String otp) {
        //creating the credential
        credential = PhoneAuthProvider.getCredential(mVerificationId, otp);

        //signing the user
        //AppContext.getInstance().getContext().

    }

    public void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        PhoneAuth.getFirebaseAuth().signInWithCredential(credential)
                .addOnCompleteListener((Activity) AppContext.getInstance().getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activity
                            Intent intent = new Intent(AppContext.getInstance().getContext(), CompleteYourProfile.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            AppContext.getInstance().getContext().startActivity(intent);

                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }
                            Toast.makeText(AppContext.getInstance().getContext(), message, Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }


}
