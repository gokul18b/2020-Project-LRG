package bloodbank.com.bb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import bloodbank.com.bb.R;
import bloodbank.com.bb.model.IncommingListModel;

/**
 * Created by gokul on 15-12-2017.
 */

public class IncommingAcceptedAdapter extends RecyclerView.Adapter<IncommingAcceptedAdapter.Holder> {
    Context ctx;
    ArrayList<IncommingListModel> datas;
    onclick onclick;

    public IncommingAcceptedAdapter(Context ctx, ArrayList<IncommingListModel> datas, onclick onclick) {
        this.ctx = ctx;
        this.datas = datas;
        this.onclick = onclick;
    }

    @Override
    public IncommingAcceptedAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IncommingAcceptedAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_incomeacceptlist, parent, false));
    }

    @Override
    public void onBindViewHolder(IncommingAcceptedAdapter.Holder holder, final int position) {
        holder.name.setText(datas.get(position).getName());
        holder.mobile.setText(datas.get(position).getMobile());
        holder.blood.setText(datas.get(position).getBlood());
        holder.address.setText(datas.get(position).getAddress());

        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick.rejectListner(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name, mobile, blood, address;
        Button reject;

        public Holder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.row_name);
            mobile = (TextView) itemView.findViewById(R.id.row_mobile);
            blood = (TextView) itemView.findViewById(R.id.row_blood);
            address = (TextView) itemView.findViewById(R.id.row_address);

            reject = (Button) itemView.findViewById(R.id.reject);
        }
    }

    public interface onclick {
        public void rejectListner(int pos);
    }

}
