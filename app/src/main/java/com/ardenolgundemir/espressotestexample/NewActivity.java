package com.ardenolgundemir.espressotestexample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ardenolgundemir on 29.05.2018.
 */

public class NewActivity extends AppCompatActivity implements View.OnClickListener {


    // The TextView used to display the message inside the Activity.
    private TextView mTextView;

    // The EditText where the user types the message.
    private EditText mEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        // Set the listeners for the buttons.
        findViewById(R.id.changeTextBt).setOnClickListener(this);
        findViewById(R.id.activityChangeTextBtn).setOnClickListener(this);
        findViewById(R.id.btnShowDialog).setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.textToBeChanged);
        mEditText = (EditText) findViewById(R.id.editTextUserInput);
    }

    @Override
    public void onClick(View view) {

        final String text = mEditText.getText().toString();

        final int changeTextBtId = R.id.changeTextBt;
        final int activityChangeTextBtnId = R.id.activityChangeTextBtn;
        final int btnShowDialogId = R.id.btnShowDialog;

        if (view.getId() == btnShowDialogId){
            openDialog();
        }else if (view.getId() == changeTextBtId) {
            // First button's interaction: set a text in a text view.
            mTextView.setText(text);
        } else if (view.getId() == activityChangeTextBtnId) {
            // Second button's interaction: start an activity and send a message to it.
            Intent intent = ShowTextActivity.newStartIntent(this, text);
            startActivity(intent);
        }
    }

    private void openDialog() {
        final String[] strArray = new String[3];
        strArray[0] = "Test 0";
        strArray[1] = "Test 1";
        strArray[2] = "Test 2";
        AlertDialog.Builder builder = new AlertDialog.Builder(NewActivity.this);
        builder.setTitle("Test");
        builder.setItems(strArray, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //_callback.onChoiceSelected(which);
                ((Button)findViewById(R.id.btnShowDialog)).setText(strArray[which]);
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
