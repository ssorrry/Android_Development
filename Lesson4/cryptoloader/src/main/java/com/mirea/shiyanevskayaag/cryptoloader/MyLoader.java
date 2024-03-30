package com.mirea.shiyanevskayaag.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyLoader extends AsyncTaskLoader<String>{
    public	static	final String ARG_WORD =	"word";

    byte[] cryptText;

    byte[] key;
    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        if (args != null) {
            cryptText = args.getByteArray(ARG_WORD);
            key = args.getByteArray("key");
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        SecretKey originalKey =	new	SecretKeySpec(key,	0,	key.length,	"AES");
        String decryptText = CryptoHelper.decryptMsg(cryptText, originalKey);
        return decryptText;
    }
}
