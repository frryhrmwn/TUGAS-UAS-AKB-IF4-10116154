package com.akb.ferry.tugasuasakbif410116154.presenter;

/*
    NIM : 10116154
    Nama : Ferry Hermawan
    Kelas : IF4
    Tgl Pengerjaan : 14 Agustus 2019
*/

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;


import com.akb.ferry.tugasuasakbif410116154.contract.TemanContract;
import com.akb.ferry.tugasuasakbif410116154.model.data.db.dao.TemanDao;
import com.akb.ferry.tugasuasakbif410116154.model.data.db.entity.Teman;

import java.util.List;

public class TemanPresenter implements TemanContract.Presenter {

    private final TemanContract.View mView;
    private final TemanDao temanDao;

    public TemanPresenter(TemanContract.View view, TemanDao temanDao) {
        this.mView = view;
        this.mView.setPresenter(this);
        this.temanDao = temanDao;
    }

    @Override
    public void start() {

    }

    @Override
    public void addNewTeman() {
        mView.showAddTeman();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void populatePeople() {
        temanDao.findAllTemans().observeForever(new Observer<List<Teman>>() {
            @Override
            public void onChanged(@Nullable List<Teman> temans) {
                mView.setTemans(temans);
                if (temans == null || temans.size() < 1) {
                    mView.showEmptyMessage();
                }
            }
        });
    }

    @Override
    public void openEditScreen(Teman teman) {
        mView.showEditScreen(teman.id);
    }

    @Override
    public void openConfirmDeleteDialog(Teman teman) {
        mView.showDeleteConfirmDialog(teman);
    }

    @Override
    public void delete(long temanId) {
        Teman teman = temanDao.findTeman(temanId);
        temanDao.deleteTeman(teman);
    }
}
