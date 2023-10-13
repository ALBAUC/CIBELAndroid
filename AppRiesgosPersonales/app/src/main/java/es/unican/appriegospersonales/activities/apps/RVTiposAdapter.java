package es.unican.appriegospersonales.activities.apps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Tipo;
import es.unican.appriegospersonales.repository.db.ActivoDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriesgospersonales.R;

/**
 * Crea y actualiza las vistas que representan cada fila de la lista de categorias.
 */
public class RVTiposAdapter extends RecyclerView.Adapter<RVTiposAdapter.TipoViewHolder> {
    private Context context;
    private final List<Tipo> tipos;
    private final List<Activo> perfilActivos;
    private final LayoutInflater inflater;
    private ActivoDao activoDao;

    public RVTiposAdapter(Context context, List<Tipo> tipos, List<Activo> perfilActivos, MyApplication myApplication) {
        this.tipos = tipos;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.perfilActivos = perfilActivos;
        DaoSession daoSession = myApplication.getDaoSession();
        activoDao = daoSession.getActivoDao();
    }

    @NonNull
    @Override
    public TipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_apps_categoria, parent, false);
        return new TipoViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return tipos.size();
    }

    @Override
    public void onBindViewHolder(TipoViewHolder holder, int position) {
        List<Activo> activos = activoDao.queryBuilder().where(ActivoDao.Properties.Fk_tipo
                .like(tipos.get(position).getIdTipo().toString())).list();
        holder.rvActivos.setAdapter(new RVAssetsAdapter(context, activos, perfilActivos));
        holder.rvActivos.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.rvActivos.setHasFixedSize(true);
        holder.tipoName_tv.setText(tipos.get(position).getNombre());
    }

    public class TipoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tipoName_tv;
        private final RecyclerView rvActivos;

        public TipoViewHolder(View itemView) {
            super(itemView);
            tipoName_tv = itemView.findViewById(R.id.categoriaName_tv);
            rvActivos = itemView.findViewById(R.id.apps_rv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), tipoName_tv.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
