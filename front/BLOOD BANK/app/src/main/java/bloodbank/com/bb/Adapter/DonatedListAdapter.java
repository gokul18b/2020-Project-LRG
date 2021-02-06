package bloodbank.com.bb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import bloodbank.com.bb.R;
import bloodbank.com.bb.model.AcceptedListModel;
import bloodbank.com.bb.model.DonatedListModel;

/**
 * Created by gokul on 15-12-2017.
 */

public class DonatedListAdapter  extends RecyclerView.Adapter<DonatedListAdapter.Holder> {
    Context ctx;
    ArrayList<DonatedListModel> datas;

    public DonatedListAdapter(Context ctx, ArrayList<DonatedListModel> datas) {
        this.ctx = ctx;
        this.datas = datas;
    }

    @Override
    public DonatedListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DonatedListAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_history,parent,false));
    }

    @Override
    public void onBindViewHolder(DonatedListAdapter.Holder holder, int position) {
        holder.name.setText(datas.get(position).getName());
        holder.mobile.setText(datas.get(position).getMobile());
        holder.blood.setText(datas.get(position).getBlood());
        holder.address.setText(datas.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name,mobile,blood,address;
        public Holder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.row_name);
            mobile=(TextView)itemView.findViewById(R.id.row_mobile);
            blood=(TextView)itemView.findViewById(R.id.row_blood);
            address=(TextView)itemView.findViewById(R.id.row_address);
        }
    }
}
