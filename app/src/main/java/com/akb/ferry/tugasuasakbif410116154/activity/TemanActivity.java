package com.akb.ferry.tugasuasakbif410116154.activity;

/*
    NIM : 10116154
    Nama : Ferry Hermawan
    Kelas : IF4
    Tgl Pengerjaan : 14 Agustus 2019
*/

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.akb.ferry.tugasuasakbif410116154.R;
import com.akb.ferry.tugasuasakbif410116154.adapter.TemanAdapter;
import com.akb.ferry.tugasuasakbif410116154.contract.TemanContract;
import com.akb.ferry.tugasuasakbif410116154.fragment.DeleteConfirmFragment;
import com.akb.ferry.tugasuasakbif410116154.fragment.KontakFragment;
import com.akb.ferry.tugasuasakbif410116154.fragment.ProfilFragment;
import com.akb.ferry.tugasuasakbif410116154.model.data.db.AppDatabase;
import com.akb.ferry.tugasuasakbif410116154.model.data.db.entity.Teman;
import com.akb.ferry.tugasuasakbif410116154.presenter.Preferences;
import com.akb.ferry.tugasuasakbif410116154.presenter.TemanPresenter;
import com.akb.ferry.tugasuasakbif410116154.utils.Constants;

import java.util.List;

public class TemanActivity extends AppCompatActivity implements TemanContract.View, TemanContract.OnItemClickListener, TemanContract.DeleteListener {

    final Fragment fragment1 = new ProfilFragment();
    final Fragment fragment2 = new KontakFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;
    private TextView mTextMessage;

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    private TemanContract.Presenter mPresenter;
    private TemanAdapter mTemanAdapter;

    private TextView mEmptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        /*BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addNewTeman();
            }
        });

        mEmptyTextView = (TextView) findViewById(R.id.emptyTextView);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTemanAdapter = new TemanAdapter(this);
        recyclerView.setAdapter(mTemanAdapter);

        AppDatabase db = AppDatabase.getDatabase(getApplication());
        mPresenter = new TemanPresenter(this, db.temanModel());
    }

    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_back:
                    Intent a = new Intent(TemanActivity.this,MainActivity.class);
                    startActivity(a);
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;

            }
            return false;
        }
    };*/

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.populatePeople();
    }

    @Override
    public void showAddTeman() {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    @Override
    public void setTemans(List<Teman> temans) {
        mEmptyTextView.setVisibility(View.GONE);
        mTemanAdapter.setValues(temans);
    }

    @Override
    public void showEditScreen(long id) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(Constants.TEMAN_ID, id);
        startActivity(intent);
    }

    @Override
    public void showDeleteConfirmDialog(Teman teman) {
        DeleteConfirmFragment fragment = new DeleteConfirmFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.TEMAN_ID, teman.id);
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), "confirmDialog");
    }

    @Override
    public void showEmptyMessage() {
        mEmptyTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(TemanContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void clickItem(Teman teman) {
        mPresenter.openEditScreen(teman);
    }

    @Override
    public void clickLongItem(Teman teman) {
        mPresenter.openConfirmDeleteDialog(teman);
    }

    @Override
    public void setConfirm(boolean confirm, long temanId) {
        if (confirm) {
            mPresenter.delete(temanId);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        /*if (id == R.id.action_settings) {
            startActivity(new Intent(TemanActivity.this, ViewPagerActivity.class));
            return true;
        }*/

        if (id == R.id.logout) {
            Preferences.clearLoggedInUser(getBaseContext());
            startActivity(new Intent(getBaseContext(), LoginActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Tekan tombol kembali lagi untuk keluar", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }*/
}
