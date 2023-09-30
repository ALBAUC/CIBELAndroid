package es.unican.appriegospersonales.common.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.unican.appriegospersonales.activities.apps.detail.AssetDetailView;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriesgospersonales.R;

public class RVActivosPerfilAdapter extends RecyclerView.Adapter<RVActivosPerfilAdapter.AppViewHolder> {

    private Context context;
    private final List<Activo> activos;
    private final List<Activo> perfilActivos;
    private final LayoutInflater inflater;

    public RVActivosPerfilAdapter(Context context, List<Activo> activos) {
        this.activos = activos;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.perfilActivos = null;
    }

    public RVActivosPerfilAdapter(Context context, List<Activo> activos, List<Activo> perfilActivos) {
        this.activos = activos;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.perfilActivos = perfilActivos;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_perfil_app, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        Activo dEle = activos.get(position);
        holder.activo = dEle;
        holder.appName_tv.setText(dEle.getNombre());
        holder.appCategory_tv.setText(dEle.getCategoria().getNombre());
        Picasso.get().load(dEle.getIcono()).into(holder.appIcon_iv);

        if (perfilActivos != null && perfilActivos.contains(dEle)) {
            holder.infoAddedIcon_iv.setVisibility(View.VISIBLE);
        } else {
            holder.infoAddedIcon_iv.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return activos.size();
    }

    public void updateElementsList(List<Activo> updatedElements) {
        activos.clear();
        activos.addAll(updatedElements);
        notifyDataSetChanged();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView appName_tv;
        private final ImageView appIcon_iv;
        private final TextView appCategory_tv;
        private final ImageView infoAddedIcon_iv;
        private Activo activo;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            appName_tv = itemView.findViewById(R.id.appAddedName_tv);
            appIcon_iv = itemView.findViewById(R.id.appAddedIcon_iv);
            appCategory_tv = itemView.findViewById(R.id.appAddedCategory_tv);
            infoAddedIcon_iv = itemView.findViewById(R.id.infoAddedIcon_iv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .hide(fragmentManager.findFragmentById(R.id.container))
                    .add(R.id.container, AssetDetailView.newInstance(activo))
                    .setReorderingAllowed(true)
                    .addToBackStack("perfil")
                    .commit();
        }
    }
}
