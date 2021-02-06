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
import bloodbank.com.bb.model.AllDonors;

/**
 * Created by Admin on 11-02-2018.
 */

public class EmergencyListAdapter extends RecyclerView.Adapter<EmergencyListAdapter.Holder> {
    Context ctx;
    ArrayList<AllDonors> datas;
    onclick onclick;
    public EmergencyListAdapter(Context applicationContext, ArrayList<AllDonors> datas,onclick onclick) {
        this.ctx=applicationContext;
        this.datas=datas;
        this.onclick=onclick;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EmergencyListAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_emergencylist, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.name.setText(datas.get(position).getName());
        holder.mobile.setText(datas.get(position).getMobile());
        holder.blood.setText(datas.get(position).getBlood());
        holder.address.setText(datas.get(position).getAddress());

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick.click(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name, mobile, blood, address;
        Button call;

        public Holder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.row_name);
            mobile = (TextView) itemView.findViewById(R.id.row_mobile);
            blood = (TextView) itemView.findViewById(R.id.row_blood);
            address = (TextView) itemView.findViewById(R.id.row_address);

            call = (Button) itemView.findViewById(R.id.call);
        }
    }

    public interface onclick{
        public void click(int pos);
    }
}
