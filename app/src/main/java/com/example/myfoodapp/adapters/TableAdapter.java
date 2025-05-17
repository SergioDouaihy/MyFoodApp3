package com.example.myfoodapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfoodapp.R;
import com.example.myfoodapp.models.TableModel;
import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder> {

    private List<TableModel> tableList;

    public TableAdapter(List<TableModel> tableList) {
        this.tableList = tableList;
    }

    @Override
    public TableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_list_item, parent, false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TableViewHolder holder, int position) {
        TableModel table = tableList.get(position);
        holder.tableTitle.setText("Table " + table.getTableNumber());
        holder.tableStatus.setText(table.isOccupied() ? "Occupied" : "Available");
    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }

    static class TableViewHolder extends RecyclerView.ViewHolder {
        TextView tableTitle, tableStatus;

        TableViewHolder(View itemView) {
            super(itemView);
            tableTitle = itemView.findViewById(R.id.tableTitle);
            tableStatus = itemView.findViewById(R.id.tableStatus);
        }
    }
}