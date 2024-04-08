package com.mirea.shiyanevskayaag.mireaproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;


public class AddFileDialogFragment extends DialogFragment {
    private static final String TAG = "AddFileDialogFragment";
    private EditText editTextFileName;
    private EditText editTextFileContent;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_file, null);

        editTextFileName = dialogView.findViewById(R.id.editTextFileName);
        editTextFileContent = dialogView.findViewById(R.id.editTextFileContent);

        builder.setView(dialogView)
                .setTitle("Создать файл")
                .setPositiveButton("Создать", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String fileName = editTextFileName.getText().toString();
                        String fileContent = editTextFileContent.getText().toString();

                        try {
                            String mainKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
                            SharedPreferences secureSharedPreferences = EncryptedSharedPreferences.create(
                                    fileName,
                                    mainKeyAlias,
                                    requireContext(),
                                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                            );
                            secureSharedPreferences.edit().putString("secure", fileContent).apply();
                        } catch (GeneralSecurityException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }
}