package com.ttth.test.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ttth.adapter.MyAdapter;
import com.ttth.database.CustomerDatabase;
import com.ttth.item.Customer;

import java.util.ArrayList;

/**
 * Created by Administrator on 24/03/2016.
 */
public class InfoActivity extends Activity{
    private TextView tvIdCustomer,tvNameCustomer,tvDate,tvEmail,tvPhone,tvOrganization,tvPosition,
    tvAddress,tvType;
    private Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        initView();
    }

    private void initView() {
        tvIdCustomer = (TextView) this.findViewById(R.id.tvIdCustomer);
        tvNameCustomer = (TextView) this.findViewById(R.id.tvNameCustomer);
        tvDate = (TextView) this.findViewById(R.id.tvDate);
        tvEmail = (TextView) this.findViewById(R.id.tvEmail);
        tvPhone = (TextView) this.findViewById(R.id.tvPhone);
        tvOrganization = (TextView) this.findViewById(R.id.tvOrganization);
        tvPosition = (TextView) this.findViewById(R.id.tvPosision);
        tvAddress = (TextView) this.findViewById(R.id.tvAddress);
        tvType = (TextView) this.findViewById(R.id.tvType);

        Intent intent = getIntent();
        tvIdCustomer.setText(intent.getStringExtra(MyAdapter.KEY_ID_CUSTOMER));
        tvNameCustomer.setText(intent.getStringExtra(MyAdapter.KEY_NAME_CUSTOMER));
        tvDate.setText(intent.getStringExtra(MyAdapter.KEY_DATE_CUSTOMER));
        tvEmail.setText(intent.getStringExtra(MyAdapter.KEY_EMAIL_CUSTOMER));
        tvPhone.setText(intent.getStringExtra(MyAdapter.KEY_PHONE_CUSTOMER));
        tvOrganization.setText(intent.getStringExtra(MyAdapter.KEY_ORGANIZATION_CUSTOMER));
        tvPosition.setText(intent.getStringExtra(MyAdapter.KEY_POSITION_CUSTOMER));
        tvAddress.setText(intent.getStringExtra(MyAdapter.KEY_ADDRESS_CUSTOMER));
        tvType.setText(intent.getStringExtra(MyAdapter.KEY_TYPE_CUSTOMER));

    }
}
