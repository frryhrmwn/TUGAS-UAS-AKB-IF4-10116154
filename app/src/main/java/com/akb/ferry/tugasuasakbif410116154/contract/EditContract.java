package com.akb.ferry.tugasuasakbif410116154.contract;

/*
    NIM : 10116154
    Nama : Ferry Hermawan
    Kelas : IF4
    Tgl Pengerjaan : 14 Agustus 2019
*/

import com.akb.ferry.tugasuasakbif410116154.model.data.db.entity.Teman;
import com.akb.ferry.tugasuasakbif410116154.presenter.BasePresenter;
import com.akb.ferry.tugasuasakbif410116154.view.BaseView;


public interface EditContract {

    interface Presenter extends BasePresenter {
        void save(Teman teman);

        boolean validate(Teman teman);

        void getTemanAndPopulate(long id);

        void update(Teman teman);
    }

    interface View extends BaseView<EditContract.Presenter> {

        void showErrorMessage(int field);

        void clearPreErrors();

        void close();

        void populate(Teman teman);
    }
    
}
