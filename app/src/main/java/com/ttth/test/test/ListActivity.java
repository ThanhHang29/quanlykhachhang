package com.ttth.test.test;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.ttth.adapter.MyAdapter;
import com.ttth.database.CustomerDatabase;
import com.ttth.item.Customer;

import java.util.ArrayList;

/**
 * Created by Administrator on 22/03/2016.
 */
public class ListActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {
    private ArrayList<Customer>arrCustomer;
    private MyAdapter myAdapter;
    private ListView lvList;
    private CustomerDatabase cutomerData;
    private Toolbar toolbar;
    private SearchView mSearchView;
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        cutomerData = new CustomerDatabase(this);
        arrCustomer = new ArrayList<Customer>();
        arrCustomer.addAll(cutomerData.getCustomerData());
        initView();
    }

    private void initView() {
//        toolbar = (Toolbar) this.findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        lvList = (ListView) this.findViewById(R.id.lvList);
        myAdapter = new MyAdapter(this,android.R.layout.simple_list_item_1,arrCustomer);
        lvList.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view,menu);
        MenuItem itemSearch = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) itemSearch.getActionView();
//        mSearchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            myAdapter.getFilter().filter("");
            lvList.clearTextFilter();
        }else {
            myAdapter.getFilter().filter(newText.toString());
        }
        return true;
    }
}
