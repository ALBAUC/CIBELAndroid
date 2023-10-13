package es.unican.appriegospersonales.activities.apps.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.unican.appriegospersonales.model.Vulnerabilidad;
import es.unican.appriesgospersonales.R;

public class RVCvesAdapter extends RecyclerView.Adapter<RVCvesAdapter.CveViewHolder> {

    private Context context;
    private final LayoutInflater inflater;
    private final List<Vulnerabilidad> vulnerabilidades;

    public RVCvesAdapter(Context context, List<Vulnerabilidad> vulnerabilidades) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.vulnerabilidades = vulnerabilidades;
    }

    @NonNull
    @Override
    public CveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_assets_cve, parent, false);
        return new CveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVCvesAdapter.CveViewHolder holder, int position) {
        Vulnerabilidad vulnerabilidad = vulnerabilidades.get(position);
        holder.vulnerabilidad = vulnerabilidad;
        holder.cveIdTV.setText(vulnerabilidad.getIdCVE());
        holder.cveSeverityIV.setColorFilter(getColorFromSeverity(vulnerabilidad.getBaseSeverity()));
    }

    private int getColorFromSeverity(String baseSeverity) {
        int colorResId;
        switch (baseSeverity) {
            case Vulnerabilidad.SEVERITY_L:
                colorResId = R.color.lowV;
                break;
            case Vulnerabilidad.SEVERITY_M:
                colorResId = R.color.mediumV;
                break;
            case Vulnerabilidad.SEVERITY_H:
                colorResId = R.color.highV;
                break;
            case Vulnerabilidad.SEVERITY_C:
                colorResId = R.color.criticalV;
                break;
            default:
                colorResId = R.color.black; // Si el índice no está en el rango, se usa el color por defecto
                break;
        }
        return ContextCompat.getColor(context, colorResId);
    }

    @Override
    public int getItemCount() {
        return vulnerabilidades.size();
    }

    public class CveViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView cveIdTV;
        private final ImageView cveSeverityIV;
        private Vulnerabilidad vulnerabilidad;

        public CveViewHolder(@NonNull View itemView) {
            super(itemView);
            cveIdTV = itemView.findViewById(R.id.cveId_tv);
            cveSeverityIV = itemView.findViewById(R.id.severityIcon_iv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
