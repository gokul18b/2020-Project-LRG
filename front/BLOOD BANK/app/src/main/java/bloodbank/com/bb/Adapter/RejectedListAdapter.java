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
import bloodbank.com.bb.model.RejectedListModel;

/**
 * Created by gokul on 15-12-2017.
 */

public class RejectedListAdapter  extends RecyclerView.Adapter<RejectedListAdapter.Holder> {
    Context ctx;
    ArrayList<RejectedListModel> datas;
    onclickListner onclickListner;

    public RejectedListAdapter(Context ctx, ArrayList<RejectedListModel> datas,onclickListner onclickListner) {
        this.ctx = ctx;
        this.datas = datas;
        this.onclickListner=onclickListner;
    }

    @Override
    public RejectedListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RejectedListAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_rejectedlist,parent,false));
    }

    @Override
    public void onBindViewHolder(RejectedListAdapter.Holder holder, final int position) {
        holder.name.setText(datas.get(position).getName());
        holder.mobile.setText(datas.get(position).getMobile());
        holder.blood.setText(datas.get(position).getBlood());
        holder.address.setText(datas.get(position).getAddress());

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickListner.call(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name,mobile,blood,address;
        Button call;
        public Holder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.row_name);
            mobile=(TextView)itemView.findViewById(R.id.row_mobile);
            blood=(TextView)itemView.findViewById(R.id.row_blood);
            address=(TextView)itemView.findViewById(R.id.row_address);

            call=(Button)itemView.findViewById(R.id.call);
        }
    }

    public interface onclickListner{
        public void call(int pos);
    }
}
