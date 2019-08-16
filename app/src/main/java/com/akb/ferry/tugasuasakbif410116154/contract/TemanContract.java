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

import java.util.List;

public interface TemanContract {

    interface Presenter extends BasePresenter {

        void addNewTeman();

        void result(int requestCode, int resultCode);

        void populatePeople();

        void openEditScreen(Teman teman);

        void openConfirmDeleteDialog(Teman teman);

        void delete(long temanId);
    }

    interface View extends BaseView<TemanContract.Presenter> {

        void showAddTeman();

        void setTemans(List<Teman> temans);

        void showEditScreen(long id);

        void showDeleteConfirmDialog(Teman teman);

        void showEmptyMessage();
    }

    interface OnItemClickListener {

        void clickItem(Teman teman);

        void clickLongItem(Teman teman);
    }

    interface DeleteListener {

        void setConfirm(boolean confirm, long temanId);

    }
}
