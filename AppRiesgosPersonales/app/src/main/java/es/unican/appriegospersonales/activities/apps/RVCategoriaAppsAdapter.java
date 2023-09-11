package es.unican.appriegospersonales.activities.apps;

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

import es.unican.appriegospersonales.activities.apps.detail.AppDetailView;
import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriesgospersonales.R;

public class RVCategoriaAppsAdapter extends RecyclerView.Adapter<RVCategoriaAppsAdapter.CategoriaAppViewHolder> {
    private Context context;
    private final List<Aplicacion> aplicacions;
    private final List<Aplicacion> perfilApps;
    private final LayoutInflater inflater;

    public RVCategoriaAppsAdapter(Context context, List<Aplicacion> data, List<Aplicacion> perfilApps) {
        this.context = context;
        this.aplicacions = data;
        this.perfilApps = perfilApps;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoriaAppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_apps_app, parent, false);
        return new CategoriaAppViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return aplicacions.size();
    }

    @Override
    public void onBindViewHolder(CategoriaAppViewHolder holder, int position) {
        Aplicacion aplicacion = aplicacions.get(position);
        holder.aplicacion = aplicacion;
        holder.appName_tv.setText(aplicacion.getNombre());
        Picasso.get().load(aplicacion.getIcono()).into(holder.appIcon_iv);

        if (perfilApps.contains(aplicacion)) {
            holder.appAddedIcon_iv.setVisibility(View.VISIBLE);
            holder.appAddedInfo_tv.setVisibility(View.VISIBLE);
        } else {
            holder.appAddedIcon_iv.setVisibility(View.GONE);
            holder.appAddedInfo_tv.setVisibility(View.GONE);
        }
    }

    public class CategoriaAppViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView appIcon_iv;
        private final TextView appName_tv;
        private final ImageView appAddedIcon_iv;
        private final TextView appAddedInfo_tv;
        private Aplicacion aplicacion;

        public CategoriaAppViewHolder(View itemView) {
            super(itemView);
            appName_tv = itemView.findViewById(R.id.appName_tv);
            appIcon_iv = itemView.findViewById(R.id.appIcon_iv);
            appAddedIcon_iv = itemView.findViewById(R.id.appAddedIcon_iv);
            appAddedInfo_tv = itemView.findViewById(R.id.appAddedInfo_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            AppCompatActivity activity = (AppCompatActivity) context;
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, AppDetailView.newInstance(aplicacion))
                    .setReorderingAllowed(true)
                    .addToBackStack("apps")
                    .commit();
        }
    }
}
