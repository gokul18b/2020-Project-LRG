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
import bloodbank.com.bb.model.DonorListModel;

/**
 * Created by gokul on 15-12-2017.
 */

public class PendingListAdapter  extends RecyclerView.Adapter<PendingListAdapter.Holder> {
    Context ctx;
    ArrayList<DonorListModel> datas;
    clicklistner clicklistner;

    public PendingListAdapter(Context ctx, ArrayList<DonorListModel> datas,clicklistner clicklistner) {
        this.ctx = ctx;
        this.datas = datas;
        this.clicklistner = clicklistner;
    }

    @Override
    public PendingListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PendingListAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pendinglist,parent,false));
    }

    @Override
    public void onBindViewHolder(PendingListAdapter.Holder holder, final int position) {
        holder.name.setText(datas.get(position).getName());
        holder.mobile.setText(datas.get(position).getMobile());
        holder.blood.setText(datas.get(position).getBlood());
        holder.address.setText(datas.get(position).getAddress());

        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicklistner.reject(position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name,mobile,blood,address;
        Button reject;
        public Holder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.row_name);
            mobile=(TextView)itemView.findViewById(R.id.row_mobile);
            blood=(TextView)itemView.findViewById(R.id.row_blood);
            address=(TextView)itemView.findViewById(R.id.row_address);
            reject=(Button) itemView.findViewById(R.id.reject);
        }
    }

    public interface clicklistner{
        public void reject(int pos);
    }
}
