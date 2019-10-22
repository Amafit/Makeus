package com.example.makeus.ViewModel;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.makeus.Model.Soldier;
import com.example.makeus.Model.Squad;
import com.example.makeus.Module.SoldierAdapter;
import com.example.makeus.R;

import java.util.List;

public class SoldierFragment extends Fragment {

    private SoldierViewModel mViewModel;
    private SoldierAdapter mSoldierAdapter;

    public static SoldierFragment newInstance() {
        return new SoldierFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.soldier_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SoldierViewModel.class);
        // TODO: Use the ViewModel

        if(mSoldierAdapter == null) {
            mSoldierAdapter = new SoldierAdapter(this.getContext(), mViewModel.getSoldiers().getValue(),"");
        }

        GridView gridView = getView().findViewById(R.id.grid);
        gridView.setAdapter(mSoldierAdapter);

        mViewModel.getSoldiers().observe(this, new Observer<List<Soldier>>(){
            @Override
            public void onChanged(@Nullable List<Soldier> soldier) {
                mSoldierAdapter.notifyDataSetChanged();
            }
        });

    }
}
