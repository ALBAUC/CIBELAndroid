package es.unican.appriegospersonales.activities.apps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.unican.appriegospersonales.activities.apps.detail.AssetDetailView;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriesgospersonales.R;

public class RVAssetsAdapter extends RecyclerView.Adapter<RVAssetsAdapter.AssetViewHolder> {
    private Context context;
    private final List<Activo> activos;
    private final List<Activo> perfilActivos;
    private final LayoutInflater inflater;

    public RVAssetsAdapter(Context context, List<Activo> data, List<Activo> perfilActivos) {
        this.context = context;
        this.activos = data;
        this.perfilActivos = perfilActivos;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AssetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_apps_app, parent, false);
        return new AssetViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return activos.size();
    }

    @Override
    public void onBindViewHolder(AssetViewHolder holder, int position) {
        Activo activo = activos.get(position);
        holder.activo = activo;
        holder.assetName_tv.setText(activo.getNombre());
        Picasso.get().load(activo.getIcono())
                .resize(600, 600)
                .centerCrop()
                .into(holder.assetIcon_iv);
        if (perfilActivos.contains(activo)) {
            holder.assetAddedIcon_iv.setVisibility(View.VISIBLE);
            holder.assetAddedInfo_tv.setVisibility(View.VISIBLE);
        } else {
            holder.assetAddedIcon_iv.setVisibility(View.GONE);
            holder.assetAddedInfo_tv.setVisibility(View.GONE);
        }
        holder.assetSecurityIV.setColorFilter(ContextCompat.getColor(context, activo.getColorFromTramo(activo.calcularPuntuacionSeguridad())));
        holder.assetEcoIV.setColorFilter(ContextCompat.getColor(context, activo.getColorFromTramo(82)));
    }

    public class AssetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView assetIcon_iv;
        private final TextView assetName_tv;
        private final ImageView assetAddedIcon_iv;
        private final TextView assetAddedInfo_tv;
        private final ImageView assetSecurityIV;
        private final ImageView assetEcoIV;
        private Activo activo;

        public AssetViewHolder(View itemView) {
            super(itemView);
            assetName_tv = itemView.findViewById(R.id.appName_tv);
            assetIcon_iv = itemView.findViewById(R.id.appIcon_iv);
            assetAddedIcon_iv = itemView.findViewById(R.id.appAddedIcon_iv);
            assetAddedInfo_tv = itemView.findViewById(R.id.appAddedInfo_tv);
            assetSecurityIV = itemView.findViewById(R.id.securityIcon_iv);
            assetEcoIV = itemView.findViewById(R.id.ecoIcon_iv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            AppCompatActivity activity = (AppCompatActivity) context;
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .hide(fragmentManager.findFragmentById(R.id.container))
                    .add(R.id.container, AssetDetailView.newInstance(activo))
                    .setReorderingAllowed(true)
                    .addToBackStack("apps")
                    .commit();
        }
    }
}
