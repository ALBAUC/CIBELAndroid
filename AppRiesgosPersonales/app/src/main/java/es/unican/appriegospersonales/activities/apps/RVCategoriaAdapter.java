package es.unican.appriegospersonales.activities.apps;

import android.content.Context;
import android.util.Log;
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
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.repository.db.ActivoDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriesgospersonales.R;

/**
 * Crea y actualiza las vistas que representan cada fila de la lista de categorias.
 */
public class RVCategoriaAdapter extends RecyclerView.Adapter<RVCategoriaAdapter.CategoriaViewHolder> {
    private Context context;
    private final List<Categoria> categorias;
    private final List<Activo> perfilActivos;
    private final LayoutInflater inflater;
    private ActivoDao activoDao;

    public RVCategoriaAdapter(Context context, List<Categoria> categorias, List<Activo> perfilActivos, MyApplication myApplication) {
        this.categorias = categorias;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.perfilActivos = perfilActivos;
        DaoSession daoSession = myApplication.getDaoSession();
        activoDao = daoSession.getActivoDao();
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_apps_categoria, parent, false);
        return new CategoriaViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    @Override
    public void onBindViewHolder(CategoriaViewHolder holder, int position) {
        List<Activo> activos = activoDao.queryBuilder().where(ActivoDao.Properties.Fk_categoria
                .like(categorias.get(position).getIdCategoria().toString())).list();
        holder.rvActivos.setAdapter(new RVCategoriaAssetsAdapter(context, activos, perfilActivos));
        holder.rvActivos.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.rvActivos.setHasFixedSize(true);
        holder.categoriaName_tv.setText(categorias.get(position).getNombre());
    }

    public class CategoriaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView categoriaName_tv;
        private final RecyclerView rvActivos;

        public CategoriaViewHolder(View itemView) {
            super(itemView);
            categoriaName_tv = itemView.findViewById(R.id.categoriaName_tv);
            rvActivos = itemView.findViewById(R.id.apps_rv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), categoriaName_tv.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
