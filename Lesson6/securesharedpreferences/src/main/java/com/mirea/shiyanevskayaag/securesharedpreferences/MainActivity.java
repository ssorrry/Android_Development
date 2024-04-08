package com.mirea.shiyanevskayaag.securesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);
        String text = textView.getText().toString();

        try {
            String mainKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            SharedPreferences secureSharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs",
                    mainKeyAlias,
                    getBaseContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            secureSharedPreferences.edit().putString("secure", text).apply();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}