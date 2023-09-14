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

import es.unican.appriegospersonales.activities.apps.detail.DElementDetailView;
import es.unican.appriegospersonales.model.ElementoDigital;
import es.unican.appriesgospersonales.R;

public class RVDElementsPerfilAdapter extends RecyclerView.Adapter<RVDElementsPerfilAdapter.AppViewHolder> {

    private Context context;
    private final List<ElementoDigital> elementosDigitales;
    private final List<ElementoDigital> perfilDElements;
    private final LayoutInflater inflater;

    public RVDElementsPerfilAdapter(Context context, List<ElementoDigital> elementosDigitales) {
        this.elementosDigitales = elementosDigitales;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.perfilDElements = null;
    }

    public RVDElementsPerfilAdapter(Context context, List<ElementoDigital> elementosDigitales, List<ElementoDigital> perfilDElements) {
        this.elementosDigitales = elementosDigitales;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.perfilDElements = perfilDElements;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_perfil_app, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        ElementoDigital dEle = elementosDigitales.get(position);
        holder.elementoDigital = dEle;
        holder.appName_tv.setText(dEle.getNombre());
        holder.appCategory_tv.setText(dEle.getCategoria().getNombre());
        Picasso.get().load(dEle.getIcono()).into(holder.appIcon_iv);

        if (perfilDElements != null && perfilDElements.contains(dEle)) {
            holder.infoAddedIcon_iv.setVisibility(View.VISIBLE);
        } else {
            holder.infoAddedIcon_iv.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return elementosDigitales.size();
    }

    public void updateElementsList(List<ElementoDigital> updatedElements) {
        elementosDigitales.clear();
        elementosDigitales.addAll(updatedElements);
        notifyDataSetChanged();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView appName_tv;
        private final ImageView appIcon_iv;
        private final TextView appCategory_tv;
        private final ImageView infoAddedIcon_iv;
        private ElementoDigital elementoDigital;

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
                    .add(R.id.container, DElementDetailView.newInstance(elementoDigital))
                    .setReorderingAllowed(true)
                    .addToBackStack("perfil")
                    .commit();
        }
    }
}
