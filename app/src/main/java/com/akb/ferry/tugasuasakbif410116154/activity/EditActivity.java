package com.akb.ferry.tugasuasakbif410116154.activity;

/*
    NIM : 10116154
    Nama : Ferry Hermawan
    Kelas : IF4
    Tgl Pengerjaan : 14 Agustus 2019
*/

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.akb.ferry.tugasuasakbif410116154.R;
import com.akb.ferry.tugasuasakbif410116154.contract.EditContract;
import com.akb.ferry.tugasuasakbif410116154.fragment.KontakFragment;
import com.akb.ferry.tugasuasakbif410116154.fragment.ProfilFragment;
import com.akb.ferry.tugasuasakbif410116154.model.data.db.AppDatabase;
import com.akb.ferry.tugasuasakbif410116154.model.data.db.entity.Teman;
import com.akb.ferry.tugasuasakbif410116154.presenter.EditPresenter;
import com.akb.ferry.tugasuasakbif410116154.utils.Constants;


public class EditActivity extends AppCompatActivity implements EditContract.View {

    final Fragment fragment1 = new ProfilFragment();
    final Fragment fragment2 = new KontakFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;
    private TextView mTextMessage;

    private EditContract.Presenter mPresenter;

    private EditText mNimEditText;
    private EditText mNamaEditText;
    private EditText mKelasEditText;
    private EditText mEmailEditText;
    private EditText mSosmedEditText;
    private EditText mTeleponEditText;

    private TextInputLayout mNimTextInputLayout;
    private TextInputLayout mNamaTextInputLayout;
    private TextInputLayout mKelasInputLayout;
    private TextInputLayout mEmailInputLayout;
    private TextInputLayout mSosmedInputLayout;
    private TextInputLayout mTeleponTextInputLayout;

    private FloatingActionButton mFab;

    private Teman teman;
    private boolean mEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        /*BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
*/
        teman = new Teman();
        checkMode();

        AppDatabase db = AppDatabase.getDatabase(getApplication());
        mPresenter = new EditPresenter(this, db.temanModel());

        initViews();
    }

    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_back:
                    Intent a = new Intent(EditActivity.this,TemanActivity.class);
                    startActivity(a);
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;

            }
            return false;
        }
    };*/

    @Override
    protected void onStart() {
        super.onStart();
        if (mEditMode) {
            mPresenter.getTemanAndPopulate(teman.id);
        }
    }

    private void checkMode() {
        if (getIntent().getExtras() != null) {
            teman.id = getIntent().getLongExtra(Constants.TEMAN_ID, 0);
            mEditMode = true;
        }
    }

    private void initViews() {
        mNimEditText = (EditText) findViewById(R.id.nimEditText);
        mNamaEditText = (EditText) findViewById(R.id.namaEditText);
        mKelasEditText = (EditText) findViewById(R.id.kelasEditText);
        mEmailEditText = (EditText) findViewById(R.id.emailEditText);
        mSosmedEditText = (EditText) findViewById(R.id.sosmedEditText);
        mTeleponEditText = (EditText) findViewById(R.id.teleponEditText);

        mNimTextInputLayout = (TextInputLayout) findViewById(R.id.nimTextInputLayout);
        mNamaTextInputLayout = (TextInputLayout) findViewById(R.id.namaTextInputLayout);
        mKelasInputLayout = (TextInputLayout) findViewById(R.id.kelasTextInputLayout);
        mEmailInputLayout = (TextInputLayout) findViewById(R.id.emailTextInputLayout);
        mSosmedInputLayout = (TextInputLayout) findViewById(R.id.sosmedTextInputLayout);
        mTeleponTextInputLayout = (TextInputLayout) findViewById(R.id.teleponTextInputLayout);

        setTitle(mEditMode ? "Edit Data Teman" : "Tambah Data Teman");

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setImageResource(mEditMode ? R.drawable.ic_refresh : R.drawable.ic_done);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                teman.nim = mNimEditText.getText().toString();
                teman.nama = mNamaEditText.getText().toString();
                teman.kelas = mKelasEditText.getText().toString();
                teman.email = mEmailEditText.getText().toString();
                teman.telepon = mTeleponEditText.getText().toString();
                teman.sosmed = mSosmedEditText.getText().toString();

                boolean valid = mPresenter.validate(teman);

                if (!valid) return;

                if (mEditMode) {
                    mPresenter.update(teman);
                } else {
                    mPresenter.save(teman);
                }
            }
        });
    }

    @Override
    public void setPresenter(EditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showErrorMessage(int field) {
        if (field == Constants.FIELD_NAMA) {
            mNamaTextInputLayout.setError(getString(R.string.invalid_nama));
        } else if (field == Constants.FIELD_EMAIL) {
            mEmailInputLayout.setError(getString(R.string.invalid_email));
        } else if (field == Constants.FIELD_TELEPON) {
            mTeleponTextInputLayout.setError(getString(R.string.invalid_telepon));
        } else if (field == Constants.FIELD_KELAS) {
            mKelasInputLayout.setError(getString(R.string.invalid_kelas));
        } else if (field == Constants.FIELD_SOSMED) {
            mSosmedInputLayout.setError(getString(R.string.invalid_sosmed));
        } else if (field == Constants.FIELD_NIM) {
            mNimTextInputLayout.setError(getString(R.string.invalid_nim));
        }
    }

    @Override
    public void clearPreErrors() {
        mNimTextInputLayout.setErrorEnabled(false);
        mNamaTextInputLayout.setErrorEnabled(false);
        mEmailInputLayout.setErrorEnabled(false);
        mTeleponTextInputLayout.setErrorEnabled(false);
        mKelasInputLayout.setErrorEnabled(false);
        mSosmedInputLayout.setErrorEnabled(false);
    }


    @Override
    public void close() {
        finish();
    }

    @Override
    public void populate(Teman teman) {
        this.teman = teman;
        mNimEditText.setText(teman.nim);
        mNamaEditText.setText(teman.nama);
        mKelasEditText.setText(teman.kelas);
        mEmailEditText.setText(teman.email);
        mSosmedEditText.setText(teman.sosmed);
        mTeleponEditText.setText(teman.telepon);
    }

}
