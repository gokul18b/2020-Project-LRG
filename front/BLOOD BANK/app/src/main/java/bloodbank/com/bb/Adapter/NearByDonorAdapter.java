package bloodbank.com.bb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import bloodbank.com.bb.R;
import bloodbank.com.bb.model.DonatedListModel;
import bloodbank.com.bb.model.NearByDonorsModel;

/**
 * Created by Admin on 09-02-2018.
 */

public class NearByDonorAdapter extends RecyclerView.Adapter<NearByDonorAdapter.Holder> {
    Context ctx;
    ArrayList<NearByDonorsModel> datas;
    clickListner clickListner;

    public NearByDonorAdapter(Context ctx, ArrayList<NearByDonorsModel> datas,clickListner clickListner) {
        this.ctx = ctx;
        this.datas = datas;
        this.clickListner=clickListner;
    }

    @Override
    public NearByDonorAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NearByDonorAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_nearbydonors,parent,false));
    }

    @Override
    public void onBindViewHolder(NearByDonorAdapter.Holder holder, final int position) {
        holder.name.setText(datas.get(position).getName());
        holder.mobile.setText(datas.get(position).getMobile());
        holder.blood.setText(datas.get(position).getBlood());
        holder.address.setText(datas.get(position).getAddress());
        holder.lastupdate.setText(datas.get(position).getLastupdate());
        holder.distance.setText(datas.get(position).getDis()+" KM");

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.call(position);
            }
        });
        holder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.sendRequest(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name,mobile,blood,address,lastupdate,distance;
        LinearLayout row;
        Button call,send;
        public Holder(View itemView) {
            super(itemView);

            distance=(TextView) itemView.findViewById(R.id.distance);
            call=(Button) itemView.findViewById(R.id.call);
            send=(Button) itemView.findViewById(R.id.send);
            name=(TextView)itemView.findViewById(R.id.row_name);
            mobile=(TextView)itemView.findViewById(R.id.row_mobile);
            blood=(TextView)itemView.findViewById(R.id.row_blood);
            address=(TextView)itemView.findViewById(R.id.row_address);
            lastupdate=(TextView)itemView.findViewById(R.id.row_lastupdate);
        }
    }

    public interface clickListner{
        public void sendRequest(int pos);
        public void call(int pos);


    }
}
