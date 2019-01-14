package com.an.github.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.an.github.databinding.FilterItemBinding;

import java.util.List;

public class FilterListAdapter extends RecyclerView.Adapter<FilterListAdapter.CustomViewHolder> {

    private List<String> items;
    private int lastSelectedPosition = 0;
    public FilterListAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FilterItemBinding itemBinding = FilterItemBinding.inflate(layoutInflater, parent, false);
        return new CustomViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        String item = getItem(position);
        holder.binding.btnRadio.setText(item);
        holder.binding.btnRadio.setChecked(lastSelectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public String getItem(int position) {
        return items.get(position);
    }

    public void updateSelection(int currentPosition) {
        int previousPosition = lastSelectedPosition;
        this.lastSelectedPosition = currentPosition;
        notifyItemChanged(previousPosition);
        notifyItemChanged(lastSelectedPosition);
    }

    protected class CustomViewHolder extends RecyclerView.ViewHolder {
        private FilterItemBinding binding;

        public CustomViewHolder(FilterItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.btnRadio.setEnabled(false);
        }
    }
}
