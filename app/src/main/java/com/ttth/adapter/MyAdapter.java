package com.ttth.adapter;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ttth.item.Customer;
import com.ttth.test.test.InfoActivity;
import com.ttth.test.test.ListActivity;
import com.ttth.test.test.MainActivity;
import com.ttth.test.test.R;
import com.ttth.test.test.SendSMSActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 24/03/2016.
 */
public class MyAdapter extends ArrayAdapter<Customer> {
    public static final String KEY_TYPE_CUSTOMER = "key_type";
    public static final String KEY_ADDRESS_CUSTOMER = "key_address";
    public static final String KEY_POSITION_CUSTOMER = "key_position";
    public static final String KEY_ORGANIZATION_CUSTOMER = "key_organization";
    public static final String KEY_DATE_CUSTOMER = "key_date";
    public static final String KEY_PHONE_CUSTOMER = "key_phone";
    public static final String KEY_ID_CUSTOMER = "key_id";
    public static final String KEY_NAME_CUSTOMER = "key_name";
    public static final String KEY_EMAIL_CUSTOMER = "key_email";
    private ArrayList<Customer> arrCustomer;
    private LayoutInflater inflater;
    private Context context;

    //    private Dialog dialog;
    public MyAdapter(Context context, int resource, ArrayList<Customer> data) {
        super(context, resource, data);
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//        inflater = LayoutInflater.from(context);
        this.arrCustomer = data;
        this.context = context;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item, parent, false);
            TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            TextView tvPhoneNumber = (TextView) view.findViewById(R.id.tvPhoneNumber);
            Button btnCall = (Button) view.findViewById(R.id.btnCall);
            Button btnSMS = (Button) view.findViewById(R.id.btnSMS);
            Button btnInfor = (Button) view.findViewById(R.id.btnInfo);

            viewHolder = new ViewHolder();
            viewHolder.tvTitle = tvTitle;
            viewHolder.tvPhoneNumber = tvPhoneNumber;
            viewHolder.btnCall = btnCall;
            viewHolder.btnSMS = btnSMS;
            viewHolder.btnInfo = btnInfor;
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) view.getTag();
        viewHolder.tvTitle.setText(arrCustomer.get(position).getId() + "-" + arrCustomer.get(position).getOrganization()
                + "-" + arrCustomer.get(position).getName());
        viewHolder.tvPhoneNumber.setText(arrCustomer.get(position).getPhoneNumber());
        viewHolder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + arrCustomer.get(position).getPhoneNumber()));
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                context.startActivity(callIntent);
            }
        });
        viewHolder.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,InfoActivity.class);
                intent.putExtra(KEY_ID_CUSTOMER, arrCustomer.get(position).getId());
                intent.putExtra(KEY_NAME_CUSTOMER, arrCustomer.get(position).getName());
                intent.putExtra(KEY_DATE_CUSTOMER, arrCustomer.get(position).getBirthday());
                intent.putExtra(KEY_EMAIL_CUSTOMER, arrCustomer.get(position).getEmail());
                intent.putExtra(KEY_PHONE_CUSTOMER, arrCustomer.get(position).getPhoneNumber());
                intent.putExtra(KEY_ORGANIZATION_CUSTOMER, arrCustomer.get(position).getOrganization());
                intent.putExtra(KEY_POSITION_CUSTOMER, arrCustomer.get(position).getPositional());
                intent.putExtra(KEY_ADDRESS_CUSTOMER, arrCustomer.get(position).getAddress());
                intent.putExtra(KEY_TYPE_CUSTOMER, arrCustomer.get(position).getType());
                context.startActivity(intent);
            }
        });
        viewHolder.btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(context, SendSMSActivity.class);
                smsIntent.putExtra(KEY_ID_CUSTOMER, arrCustomer.get(position).getId());
                smsIntent.putExtra(KEY_ORGANIZATION_CUSTOMER, arrCustomer.get(position).getOrganization());
                smsIntent.putExtra(KEY_NAME_CUSTOMER, arrCustomer.get(position).getName());
                smsIntent.putExtra(KEY_PHONE_CUSTOMER, arrCustomer.get(position).getPhoneNumber());
                context.startActivity(smsIntent);
            }
        });
        return view;
    }
    class ViewHolder{
        private TextView tvTitle,tvPhoneNumber;
        private Button btnCall,btnSMS,btnInfo;
    }
}
