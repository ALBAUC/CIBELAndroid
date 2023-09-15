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

import java.util.List;
import java.util.Objects;

import es.unican.appriegospersonales.activities.riesgos.detail.RiesgoDetailView;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Riesgo;
import es.unican.appriesgospersonales.R;

public class RVRiesgosAdapter extends RecyclerView.Adapter<RVRiesgosAdapter.RiesgoViewHolder> {

    private final Context context;
    private final List<Riesgo> riesgos;
    private final List<Control> perfilControls;
    private final LayoutInflater inflater;

    public RVRiesgosAdapter(Context context, List<Riesgo> riesgos) {
        this.riesgos = riesgos;
        this.context = context;
        this.perfilControls = null;
        this.inflater = LayoutInflater.from(context);
    }

    public RVRiesgosAdapter(Context context, List<Riesgo> riesgos, List<Control> perfilControls) {
        this.riesgos = riesgos;
        this.context = context;
        this.perfilControls = perfilControls;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RiesgoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_riesgos_riesgo, parent, false);
        return new RiesgoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiesgoViewHolder holder, int position) {
        Riesgo riesgo = riesgos.get(position);
        holder.riesgo = riesgo;
        holder.riesgoName_tv.setText(riesgo.getNombre());
        int iconoId = context.getResources().getIdentifier(
                "risk" + riesgo.getIdRiesgo(),
                "drawable",
                context.getPackageName());
        if (iconoId != 0) {
            holder.riesgoIcon_iv.setImageResource(iconoId);
        }
        int numControles = 0;
        if (perfilControls != null) {
            for (Control c : riesgo.getControles()) {
                if (perfilControls.contains(c)) {
                    numControles++;
                }
            }
            holder.riesgoControlesP_tv.setVisibility(View.VISIBLE);
            holder.riesgoControlesP_tv.setText(
                    numControles + "/" + riesgo.getControles().size() + " controles aplicados");
        } else {
            holder.riesgoControlesP_tv.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return riesgos.size();
    }

    public class RiesgoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView riesgoName_tv;
        private final ImageView riesgoIcon_iv;
        private final TextView riesgoControlesP_tv;
        private Riesgo riesgo;

        public RiesgoViewHolder(@NonNull View itemView) {
            super(itemView);
            riesgoName_tv = itemView.findViewById(R.id.riesgoName_tv);
            riesgoIcon_iv = itemView.findViewById(R.id.riesgoIcon_iv);
            riesgoControlesP_tv = itemView.findViewById(R.id.riesgoControlesP_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .hide(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.container)))
                    .add(R.id.container, RiesgoDetailView.newInstance(riesgo))
                    .setReorderingAllowed(true)
                    .addToBackStack("riesgos")
                    .commit();
        }
    }
}
