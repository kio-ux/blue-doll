package com.example.bluedoll.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluedoll.repositories.DollsRepo;
import com.example.bluedoll.databinding.DollsRowBinding;

public class DollsAdapters extends ListAdapter<DollsRepo> {


    protected DollsAdapters(@NonNull DiffUtil.ItemCallback diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    class DollsViewHolder extends RecyclerView.ViewHolder{
        DollsRowBinding dollsRowBinding;
        public DollsViewHolder(DollsRowBinding binding) {
            super(binding.getRoot());
            this.dollsRowBinding = binding;
        }
    }
}
