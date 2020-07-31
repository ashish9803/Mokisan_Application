package com.farmhike.mokisan.Utils;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.farmhike.mokisan.Activities.CompleteYourProfile;
import com.farmhike.mokisan.Activities.VerifyOtp;
import com.farmhike.mokisan.Models.AppContext;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.textfield.TextInputEditText;
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
    private TextInputEditText _edittext_OTP;
    private ProgressBar _verify_PB;
    private Button _nextButton;
    private Button _verifyButton;

    public void set_nextButton(Button _nextButton) {
        this._nextButton = _nextButton;
    }

    public void set_verifyButton(Button _verifyButton) {
        this._verifyButton = _verifyButton;
    }

    public Boolean get_verified() {
        return _verified;
    }

    private Boolean _verified;

    public void set_Verify_manually(TextView _Verify_manually) {
        this._Verify_manually = _Verify_manually;
    }

    private TextView _Verify_manually;

    public void set_verify_PB(ProgressBar _verify_PB) {
        this._verify_PB = _verify_PB;
    }

    public void set_verify_IV(ImageView _verify_IV) {
        this._verify_IV = _verify_IV;
    }

    private ImageView _verify_IV;


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
                PhoneAuth.getInstance().verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.e("onVerificationFailed: ", "" + e.getMessage());
            Toast.makeText(AppContext.getInstance().getContext(), "" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
            super.onCodeAutoRetrievalTimeOut(s);
            if (PhoneAuth.getInstance().credential == null){
                _edittext_OTP.setError("Unable to fill automatically");
                _verify_PB.setVisibility(View.GONE);
                _Verify_manually.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            _verify_PB.setVisibility(View.VISIBLE);
            _Verify_manually.setVisibility(View.INVISIBLE);
            _edittext_OTP.setError(null);
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

    public void set_edittext_OTP(TextInputEditText _edittext_OTP) {
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

    public void verifyVerificationCode(String otp) {
        if(_verify_PB.getVisibility() == View.GONE)
            _verify_PB.setVisibility(View.VISIBLE);
        //creating the credential
        credential = PhoneAuthProvider.getCredential(mVerificationId, otp);
        //signing the user
        if (credential != null){
            signInWithPhoneAuthCredential(credential);
        }
    }

    public void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        PhoneAuth.getFirebaseAuth().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activity

                            _verified = true;
                            _Verify_manually.setVisibility(View.INVISIBLE);
                            _edittext_OTP.setError(null);
                            _edittext_OTP.setEnabled(false);
                            _verify_PB.setVisibility(View.GONE);
                            _verify_IV.setVisibility(View.VISIBLE);
                            _verifyButton.setVisibility(View.INVISIBLE);
                            _nextButton.setVisibility(View.VISIBLE);



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
