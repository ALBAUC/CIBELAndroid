package es.unican.appriegospersonales.activities.apps.tipo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.unican.appriegospersonales.activities.main.MainView;
import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.common.adapters.RVActivosPerfilAdapter;
import es.unican.appriegospersonales.model.Tipo;
import es.unican.appriesgospersonales.R;

public class CatalogoTipoView extends Fragment implements ICatalogoTipoContract.View, MainView.RefreshableFragment {

    public static final String FRAGMENT_TIPO = "tipo";
    private ICatalogoTipoContract.Presenter presenter;

    public static CatalogoTipoView newInstance(Tipo tipo) {
        CatalogoTipoView fragment = new CatalogoTipoView();
        Bundle args = new Bundle();
        args.putParcelable(FRAGMENT_TIPO, tipo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_catalogo_categoria, container, false);

        Bundle args = getArguments();
        if (args != null) {
            // Obtener el tipo del fragmento que lanzo este fragmento
            Tipo tipo = args.getParcelable(FRAGMENT_TIPO);
            presenter = new CatalogoTipoPresenter(tipo, this);
            presenter.init();

            // Link a los elemntos de la vista
            RecyclerView assetsTipoRV = layout.findViewById(R.id.assetsTipo_rv);

            // Asignar valores
            assetsTipoRV.setLayoutManager(new LinearLayoutManager(getContext()));
            assetsTipoRV.setAdapter(new RVActivosPerfilAdapter(getContext(), presenter.getActivosDeTipo(), getMyApplication()));

            requireActivity().setTitle(presenter.getTipoName());
        }

        return layout;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                requireActivity().getSupportFragmentManager().popBackStack();
                requireActivity().setTitle(R.string.bottom_nav_home);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) requireActivity().getApplication();
    }

    @Override
    public void refreshData() {
        // No hay nada que actualizar
    }
}
