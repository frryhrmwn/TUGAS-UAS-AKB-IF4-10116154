package com.akb.ferry.tugasuasakbif410116154.fragment;

/*
    NIM : 10116154
    Nama : Ferry Hermawan
    Kelas : IF4
    Tgl Pengerjaan : 14 Agustus 2019
*/

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.akb.ferry.tugasuasakbif410116154.R;
import com.akb.ferry.tugasuasakbif410116154.contract.TemanContract;
import com.akb.ferry.tugasuasakbif410116154.utils.Constants;


public class DeleteConfirmFragment extends DialogFragment {

    private TemanContract.DeleteListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final long temanId = getArguments().getLong(Constants.TEMAN_ID);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.are_you_sure);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.setConfirm(true, temanId);
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.setConfirm(false, temanId);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (TemanContract.DeleteListener) context;
    }
}
