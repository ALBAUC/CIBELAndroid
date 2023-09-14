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

import es.unican.appriegospersonales.activities.apps.detail.DElementDetailView;
import es.unican.appriegospersonales.model.ElementoDigital;
import es.unican.appriesgospersonales.R;

public class RVCategoriaDElementsAdapter extends RecyclerView.Adapter<RVCategoriaDElementsAdapter.CategoriaDElementViewHolder> {
    private Context context;
    private final List<ElementoDigital> dElements;
    private final List<ElementoDigital> perfilDElements;
    private final LayoutInflater inflater;

    public RVCategoriaDElementsAdapter(Context context, List<ElementoDigital> data, List<ElementoDigital> perfilDElements) {
        this.context = context;
        this.dElements = data;
        this.perfilDElements = perfilDElements;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoriaDElementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_apps_app, parent, false);
        return new CategoriaDElementViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return dElements.size();
    }

    @Override
    public void onBindViewHolder(CategoriaDElementViewHolder holder, int position) {
        ElementoDigital elementoDigital = dElements.get(position);
        holder.elementoDigital = elementoDigital;
        holder.dElementName_tv.setText(elementoDigital.getNombre());
        Picasso.get().load(elementoDigital.getIcono())
                .resize(600, 600)
                .centerCrop()
                .into(holder.dElementIcon_iv);

        if (perfilDElements.contains(elementoDigital)) {
            holder.dElementAddedIcon_iv.setVisibility(View.VISIBLE);
            holder.dElementAddedInfo_tv.setVisibility(View.VISIBLE);
        } else {
            holder.dElementAddedIcon_iv.setVisibility(View.GONE);
            holder.dElementAddedInfo_tv.setVisibility(View.GONE);
        }
    }

    public class CategoriaDElementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView dElementIcon_iv;
        private final TextView dElementName_tv;
        private final ImageView dElementAddedIcon_iv;
        private final TextView dElementAddedInfo_tv;
        private ElementoDigital elementoDigital;

        public CategoriaDElementViewHolder(View itemView) {
            super(itemView);
            dElementName_tv = itemView.findViewById(R.id.appName_tv);
            dElementIcon_iv = itemView.findViewById(R.id.appIcon_iv);
            dElementAddedIcon_iv = itemView.findViewById(R.id.appAddedIcon_iv);
            dElementAddedInfo_tv = itemView.findViewById(R.id.appAddedInfo_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            AppCompatActivity activity = (AppCompatActivity) context;
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, DElementDetailView.newInstance(elementoDigital))
                    .setReorderingAllowed(true)
                    .addToBackStack("apps")
                    .commit();
        }
    }
}
