package br.com.barrionuevo.minhaagenda;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;



/**
 * Created by Lucas on 25/11/2017.
 */

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ViewHolder>{

    private List<Contato> mContatos;
    Context context;
    private LayoutInflater mLayoutInflater;



    public ContatoAdapter(List<Contato> mContatos, Context context) {
        this.mContatos = mContatos;
        this.context = context;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.cv_element, parent, false);
        ViewHolder mvh = new ViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNomeContato.setText(mContatos.get(position).getNome());
        holder.tvTelefone.setText(mContatos.get(position).getTelefone());
        holder.ivBanner.setImageResource(R.mipmap.ic_launcher);
        holder.btnDeleteContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deleteContato();
            }
        });

    }



    @Override
    public int getItemCount() {
        return mContatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView tvNomeContato, tvTelefone;
        final ImageView ivBanner;
        final ImageView btnDeleteContato;

        public ViewHolder(View view) {
            super(view);
            tvNomeContato = (TextView) view.findViewById(R.id.tvNomeContato);
            tvTelefone = (TextView) view.findViewById(R.id.tvTelefone);
            ivBanner = (ImageView) view.findViewById(R.id.ivBanner);
            btnDeleteContato = (ImageView) view.findViewById(R.id.btnDeleteContato);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v){
            Toast.makeText(context, "Clicou",Toast.LENGTH_SHORT).show();
        }
    }
}

