package es.unican.appriegospersonales.common.adapters;

import android.content.Context;
import android.graphics.Typeface;
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
import java.util.Random;

import es.unican.appriegospersonales.activities.apps.detail.AssetDetailView;
import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.JoinActivosWithVulnerabilidades;
import es.unican.appriegospersonales.model.Vulnerabilidad;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.JoinActivosWithVulnerabilidadesDao;
import es.unican.appriegospersonales.repository.db.VulnerabilidadDao;
import es.unican.appriesgospersonales.R;

public class RVActivosPerfilAdapter extends RecyclerView.Adapter<RVActivosPerfilAdapter.AssetViewHolder> {

    private Context context;
    private final List<Activo> activos;
    private final List<Activo> perfilActivos;
    private final LayoutInflater inflater;
    private VulnerabilidadDao vulnerabilidadDao;

    // Para pestaña perfil
    public RVActivosPerfilAdapter(Context context, List<Activo> activos, MyApplication myApplication) {
        this.activos = activos;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.perfilActivos = null;
        DaoSession daoSession = myApplication.getDaoSession();
        vulnerabilidadDao = daoSession.getVulnerabilidadDao();
    }

    // Para buscador
    public RVActivosPerfilAdapter(Context context, List<Activo> activos, List<Activo> perfilActivos, MyApplication myApplication) {
        this.activos = activos;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.perfilActivos = perfilActivos;
        DaoSession daoSession = myApplication.getDaoSession();
        vulnerabilidadDao = daoSession.getVulnerabilidadDao();
    }

    @NonNull
    @Override
    public AssetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_perfil_app, parent, false);
        return new AssetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssetViewHolder holder, int position) {
        Activo activo = activos.get(position);
        holder.activo = activo;

        // Nombre activo
        holder.assetName_tv.setText(activo.getNombre());

        // Numero vulnerabilidades
        List<Vulnerabilidad> activoVuln = vulnerabilidadDao._queryActivo_Vulnerabilidades(activo.getIdActivo());
        if (activoVuln.size() == 1) {
            holder.assetAddedVuln_tv.setText(activoVuln.size() + " vulnerabilidad");
        } else {
            holder.assetAddedVuln_tv.setText(activoVuln.size() + " vulnerabilidades");
        }

        // Color circulo según el índice de riesgo
        holder.riesgoIcon_iv.setColorFilter(ContextCompat.getColor(context, activo.getColorFromIndiceRiesgo(activo.calcularIndiceRiesgo())));

        Picasso.get().load(activo.getIcono())
                .resize(600, 600)
                .centerCrop()
                .into(holder.assetIcon_iv);

        if (perfilActivos != null && perfilActivos.contains(activo)) {
            holder.infoAddedIcon_iv.setVisibility(View.VISIBLE);
        } else {
            holder.infoAddedIcon_iv.setVisibility(View.GONE);
        }

        // Sostenibilidad
        Random random = new Random();
        int calificacionEco = random.nextInt(66) + 30; // numero aleatorio entre 30 y 95
        holder.assetAddedEcoTV.setText(calificacionEco + "/100 eco");
        holder.ecoIconIV.setColorFilter(ContextCompat.getColor(context, activo.getColorFromEcoRating(calificacionEco)));

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

    public class AssetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView assetName_tv;
        private final ImageView assetIcon_iv;
        private final TextView assetAddedVuln_tv;
        private final ImageView infoAddedIcon_iv;
        private final ImageView riesgoIcon_iv;
        private final ImageView ecoIconIV;
        private final TextView assetAddedEcoTV;
        private Activo activo;

        public AssetViewHolder(@NonNull View itemView) {
            super(itemView);
            assetName_tv = itemView.findViewById(R.id.appAddedName_tv);
            assetIcon_iv = itemView.findViewById(R.id.appAddedIcon_iv);
            assetAddedVuln_tv = itemView.findViewById(R.id.assetAddedVuln_tv);
            infoAddedIcon_iv = itemView.findViewById(R.id.infoAddedIcon_iv);
            riesgoIcon_iv = itemView.findViewById(R.id.riesgoIcon_iv);
            ecoIconIV = itemView.findViewById(R.id.ecoIcon_iv);
            assetAddedEcoTV = itemView.findViewById(R.id.assetAddedEco_tv);
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
