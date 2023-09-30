package es.unican.appriegospersonales.activities.controles.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriesgospersonales.R;


public class RVAmenazasCDAdapter extends RecyclerView.Adapter<RVAmenazasCDAdapter.AmenazaViewHolder> {

    private Context context;
    private final List<Amenaza> amenazas;
    private final LayoutInflater inflater;

    public RVAmenazasCDAdapter(Context context, List<Amenaza> amenazas) {
        this.context = context;
        this.amenazas = amenazas;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AmenazaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_controles_riesgo, parent, false);
        return new AmenazaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AmenazaViewHolder holder, int position) {
        Amenaza amenaza = amenazas.get(position);
        holder.riesgoName_tv.setText(amenaza.getNombre());
        int iconoId = context.getResources().getIdentifier(
                "risk" + amenaza.getIdAmenaza(),
                "drawable",
                context.getPackageName()
        );
        if (iconoId != 0) {
            holder.riesgoIcon_iv.setImageResource(iconoId);
        }

        boolean isExpanded = amenaza.isExpanded();
        holder.riesgoDescription_tv.setText(amenaza.getDescripcion());
        holder.riesgoDescription_tv.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        int arrowIcon = isExpanded ? R.drawable.arrow_up : R.drawable.arrow_down;
        holder.arrow_iv.setImageResource(arrowIcon);
    }

    @Override
    public int getItemCount() {
        return amenazas.size();
    }

    public class AmenazaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView riesgoName_tv;
        private final ImageView riesgoIcon_iv;
        private final TextView riesgoDescription_tv;
        private final ImageView arrow_iv;

        public AmenazaViewHolder(@NonNull View itemView) {
            super(itemView);
            riesgoName_tv = itemView.findViewById(R.id.riesgoName_tv);
            riesgoIcon_iv = itemView.findViewById(R.id.riesgoIcon_iv);
            riesgoDescription_tv = itemView.findViewById(R.id.riesgoDescription_tv);
            arrow_iv = itemView.findViewById(R.id.arrow_iv);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Amenaza amenaza = amenazas.get(position);
                amenaza.setExpanded(!amenaza.isExpanded());
                notifyItemChanged(position);
            }
        }
    }
}
