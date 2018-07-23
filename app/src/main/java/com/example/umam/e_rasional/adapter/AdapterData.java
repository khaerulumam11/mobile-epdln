package com.example.umam.e_rasional.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.umam.e_rasional.DetailPerjadin;
import com.example.umam.e_rasional.R;
import com.example.umam.e_rasional.model.ApprovalModel;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<ApprovalModel> mList ;
    private Context ctx;


    public  AdapterData (Context ctx, List<ApprovalModel> mList)
    {
        this.ctx = ctx;
        this.mList = mList;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_approval,parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ApprovalModel dm = mList.get(position);
        holder.id.setText(dm.getId());
        holder.namakeg.setText(dm.getNamakegiatan());
        holder.nopeng.setText(dm.getNo());
        holder.jumdel.setText(dm.getId_delegasi());
        holder.status.setText(dm.getStatus());
        holder.alasan.setText(dm.getAlasan());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder{
        TextView namakeg, nopeng, jumdel,status,alasan,id;
        ApprovalModel dm;
        public HolderData (View v)
        {
            super(v);

            namakeg  = (TextView) v.findViewById(R.id.tvNamaKeg);
            nopeng = (TextView) v.findViewById(R.id.tvNoPen);
            jumdel = (TextView) v.findViewById(R.id.tvJumDeleg);
            status = (TextView) v.findViewById(R.id.tvStatus);
            alasan = (TextView) v.findViewById(R.id.tvAlasan);
            id = (TextView) v.findViewById(R.id.idperjadin);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(ctx,DetailPerjadin.class);
                    goInput.putExtra("id","http://10.2.7.149/e-pdln/detailperjadin.php?id=" +dm.getId());
                    goInput.putExtra("id_perjadin",dm.getId());

                    ctx.startActivity(goInput);
                }
            });
        }
    }
}
