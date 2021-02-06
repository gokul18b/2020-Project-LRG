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
import bloodbank.com.bb.model.IncommingListModel;

/**
 * Created by gokul on 12-12-2017.
 */

public class IncommingListAdapter extends RecyclerView.Adapter<IncommingListAdapter.Holder> {
    Context ctx;
    ArrayList<IncommingListModel> datas;
    clicklistner clicklistner;


    public IncommingListAdapter(Context ctx, ArrayList<IncommingListModel> datas,clicklistner clicklistner) {
        this.ctx = ctx;
        this.datas = datas;
        this.clicklistner=clicklistner;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_incomelist,parent,false));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.name.setText(datas.get(position).getName());
        holder.mobile.setText(datas.get(position).getMobile());
        holder.blood.setText(datas.get(position).getBlood());
        holder.address.setText(datas.get(position).getAddress());

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicklistner.acceptrequest(position);
            }
        });
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicklistner.rejectRequest(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name,mobile,blood,address;
        Button accept,reject;
        public Holder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.row_name);
            mobile=(TextView)itemView.findViewById(R.id.row_mobile);
            blood=(TextView)itemView.findViewById(R.id.row_blood);
            address=(TextView)itemView.findViewById(R.id.row_address);
            accept=(Button) itemView.findViewById(R.id.accept);
            reject=(Button) itemView.findViewById(R.id.reject);
        }
    }

    public interface clicklistner{
        public void acceptrequest(int pos);
        public void rejectRequest(int pos);
    }
}
