package com.akb.ferry.tugasuasakbif410116154.presenter;

/*
    NIM : 10116154
    Nama : Ferry Hermawan
    Kelas : IF4
    Tgl Pengerjaan : 14 Agustus 2019
*/

import com.akb.ferry.tugasuasakbif410116154.contract.EditContract;
import com.akb.ferry.tugasuasakbif410116154.model.data.db.dao.TemanDao;
import com.akb.ferry.tugasuasakbif410116154.model.data.db.entity.Teman;
import com.akb.ferry.tugasuasakbif410116154.utils.Constants;
import com.akb.ferry.tugasuasakbif410116154.utils.Util;

public class EditPresenter implements EditContract.Presenter {

    private final EditContract.View mView;
    private final TemanDao temanDao;

    public EditPresenter(EditContract.View mMainView, TemanDao temanDao) {
        this.mView = mMainView;
        this.mView.setPresenter(this);
        this.temanDao = temanDao;
    }

    @Override
    public void start() {

    }

    @Override
    public void save(Teman teman) {
        long ids = this.temanDao.insertTeman(teman);
        mView.close();
    }

    @Override
    public boolean validate(Teman teman) {
        mView.clearPreErrors();
        if (teman.nim.isEmpty() || !Util.isValidPhone(teman.nim)) {
            mView.showErrorMessage(Constants.FIELD_NIM);
            return false;
        }
        if (teman.nama.isEmpty() || !Util.isValidName(teman.nama)) {
            mView.showErrorMessage(Constants.FIELD_NAMA);
            return false;
        }
        if (teman.kelas.isEmpty()) {
            mView.showErrorMessage(Constants.FIELD_KELAS);
            return false;
        }
        if (teman.telepon.isEmpty() || !Util.isValidPhone(teman.telepon)) {
            mView.showErrorMessage(Constants.FIELD_TELEPON);
            return false;
        }
        if (teman.email.isEmpty() || !Util.isValidEmail(teman.email)) {
            mView.showErrorMessage(Constants.FIELD_EMAIL);
            return false;
        }
        if (teman.sosmed.isEmpty()) {
            mView.showErrorMessage(Constants.FIELD_SOSMED);
            return false;
        }

        return true;
    }


    @Override
    public void getTemanAndPopulate(long id) {
        Teman teman = temanDao.findTeman(id);
        if (teman != null) {
            mView.populate(teman);
        }
    }

    @Override
    public void update(Teman teman) {
        int ids = this.temanDao.updateTeman(teman);
        mView.close();
    }
}
