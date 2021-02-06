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
import bloodbank.com.bb.model.AcceptedListModel;
import bloodbank.com.bb.model.DonorListModel;

/**
 * Created by gokul on 15-12-2017.
 */

public class AcceptedListAdapter  extends RecyclerView.Adapter<AcceptedListAdapter.Holder> {
    Context ctx;
    ArrayList<AcceptedListModel> datas;
    onclick onclick;

    public AcceptedListAdapter(Context ctx, ArrayList<AcceptedListModel> datas,onclick onclick) {
        this.ctx = ctx;
        this.datas = datas;
        this.onclick=onclick;
    }

    @Override
    public AcceptedListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AcceptedListAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_acceptedlist,parent,false));
    }

    @Override
    public void onBindViewHolder(AcceptedListAdapter.Holder holder, final int position) {
        holder.name.setText(datas.get(position).getName());
        holder.mobile.setText(datas.get(position).getMobile());
        holder.blood.setText(datas.get(position).getBlood());
        holder.address.setText(datas.get(position).getAddress());

        holder.donated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick.donated(position);
            }
        });
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick.reject(position);
            }
        });

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick.call(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name,mobile,blood,address;
        Button donated,call,reject;
        public Holder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.row_name);
            mobile=(TextView)itemView.findViewById(R.id.row_mobile);
            blood=(TextView)itemView.findViewById(R.id.row_blood);
            address=(TextView)itemView.findViewById(R.id.row_address);

            donated=(Button)itemView.findViewById(R.id.donated);
            call=(Button)itemView.findViewById(R.id.call);
            reject=(Button)itemView.findViewById(R.id.reject);
        }
    }

    public interface onclick{
        public void donated(int pos);
        public void reject(int pos);
        public void call(int pos);
    }
}
