package com.ttth.test.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ttth.adapter.MyAdapter;

/**
 * Created by Administrator on 26/03/2016.
 */
public class SendSMSActivity extends Activity implements View.OnClickListener{
    private Button btnSend,btnCancel;
    private TextView tvSMSTitle,tvSMSPhone;
    private EditText edtSMS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_layout);
        initView();
    }

    private void initView() {
        btnSend = (Button) this.findViewById(R.id.btnSend);
        btnCancel = (Button) this.findViewById(R.id.btnCancel);
        tvSMSTitle  = (TextView) this.findViewById(R.id.tvSMSTitle);
        tvSMSPhone = (TextView) this.findViewById(R.id.tvSMSPhone);
        edtSMS = (EditText) this.findViewById(R.id.edtSMS);
        btnSend.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        Intent intent = getIntent();
//        Integer id  = intent.getIntExtra(MyAdapter.KEY_ID_CUSTOMER,v);
        String organization = intent.getStringExtra(MyAdapter.KEY_ORGANIZATION_CUSTOMER);
        String name = intent.getStringExtra(MyAdapter.KEY_NAME_CUSTOMER);
        tvSMSTitle.setText("-" + organization + "-" + name);
        String phonenumber = intent.getStringExtra(MyAdapter.KEY_PHONE_CUSTOMER);
        tvSMSPhone.setText(phonenumber);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSend:
                smsMessage();
                break;
            case R.id.btnCancel:
                finish();
                break;
        }
    }
    private void smsMessage(){
        String phoneNumber = tvSMSPhone.getText().toString();
        String message = edtSMS.getText().toString();
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber,null,message,null,null);
            edtSMS.getText().clear();
            Toast.makeText(this,getResources().getText(R.string.sms_success),Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this,getResources().getText(R.string.sms_fail),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        onClick(btnCancel);
    }

    @Override
    public void finish() {
        super.finish();
    }
}
