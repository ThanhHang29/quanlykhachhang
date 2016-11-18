package com.ttth.test.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import com.ttth.database.PersonnelDatabase;
import com.ttth.item.Personnel;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String KEY_USER = "key_username";
    private static final String KEY_PASS = "key_pass";
    private static final String KEY_PRE_LOGIN = "login";
    private static final String KEY_SAVE = "save";
    private Button btnLogin;
    private Animation rotateAnimation,translateAnimation;
    private LinearLayout llBg;
    private EditText edtUser,edtPass;
    private PersonnelDatabase personnelData;
    private ArrayList<Personnel>arrPersonnel;
    private CheckBox chbSave;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPreEdit;
    private boolean saveLogin;

    private Toolbar toolbar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personnelData = new PersonnelDatabase(this);
        arrPersonnel = new ArrayList<Personnel>();
        arrPersonnel.addAll(personnelData.getPersonnelData());
        initView();
        saveInfor();
        initAnimation();

//        initToolbar();
    }

//    private void initToolbar() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//
//        toolbar.inflateMenu(R.menu.search_view);
//    }

    private void initAnimation() {
        rotateAnimation = AnimationUtils.loadAnimation(this,R.anim.rotate);
        translateAnimation = AnimationUtils.loadAnimation(this,R.anim.translate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view,menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void initView() {
        btnLogin = (Button) this.findViewById(R.id.btnLogin);
        llBg = (LinearLayout) this.findViewById(R.id.llBg);
        edtUser = (EditText) this.findViewById(R.id.edtUser);
        edtPass = (EditText) this.findViewById(R.id.edtPass);
        chbSave  = (CheckBox) this.findViewById(R.id.chbSave);
        btnLogin.setOnClickListener(this);

    }
    private void saveInfor(){
        loginPreferences = getSharedPreferences(KEY_PRE_LOGIN, MODE_PRIVATE);
        loginPreEdit = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean(KEY_SAVE,false);
        if (saveLogin==true){
            edtUser.setText(loginPreferences.getString(KEY_USER,""));;
            edtPass.setText(loginPreferences.getString(KEY_PASS,""));
            chbSave.setChecked(true);
        }
    }
    @Override
    public void onClick(View v) {
        String user = edtUser.getText().toString();
        String pass = edtPass.getText().toString();
        switch (v.getId()){
            case R.id.btnLogin:
                if (user.isEmpty()||pass.isEmpty()){
                    Toast.makeText(this,getResources().getText(R.string.login_empty),Toast.LENGTH_LONG).show();
                }else {
                    for(Personnel personnel:arrPersonnel){
                        if (personnel.getUserName().toString().equals(user)&&personnel.getPass().toString().equals(pass)){
                            llBg.startAnimation(rotateAnimation);
//                            llBg.startAnimation(translateAnimation);
                            Intent intent = new Intent(this,ListActivity.class);
                            startActivity(intent);
                            Toast.makeText(this,getResources().getText(R.string.login_success),Toast.LENGTH_LONG).show();
                            if (chbSave.isChecked()){
                                loginPreEdit.putBoolean(KEY_SAVE,true);
                                loginPreEdit.putString(KEY_USER,user);
                                loginPreEdit.putString(KEY_PASS,pass);
                                loginPreEdit.commit();
                            }else {
                                loginPreEdit.clear();
                                loginPreEdit.commit();
                            }
                            return;
                        }else{

                            Toast.makeText(this,getResources().getText(R.string.login_fail),Toast.LENGTH_LONG).show();
                        }
                    }

            }
                break;
        }
    }
}
