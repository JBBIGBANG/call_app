package com.share.call_prediction;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CallLog;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class call_log_list extends AppCompatActivity {
    private TextView textView;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log_list);
        textView = findViewById(R.id.textView);
        buttonCallLog();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void buttonCallLog(){
        ArrayList<CallInfo> callInfos = new ArrayList<CallInfo>();

        textView.setText("Call Logging Started ... ");
        String stringOutput = "";

        Uri uriCallLogs = Uri.parse("content://call_log/calls");
        Cursor cursorCallLogs = getContentResolver().query(uriCallLogs, null,null,null);
        cursorCallLogs.moveToFirst();
        do {
            String stringNumber = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.NUMBER));
            String stringName = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.CACHED_NAME));
            String duration = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.DURATION));
            String type = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.TYPE));
            String date = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.DATE));

            if(Integer.valueOf(type) == 1){
                CallInfo callInfo = new CallInfo(date,duration);
                callInfos.add(callInfo);
            }

        }while (cursorCallLogs.moveToNext());

        for (int i = 0; i < callInfos.size() ; i++){
            stringOutput = stringOutput
                    + "\nDuration: " + callInfos.get(i).getDuration()
                    + "\n Date: " + callInfos.get(i).getDate()
                    + "\n\n";
        }

        System.out.println(callInfos.size());
        textView.setText(stringOutput);
    }
}
