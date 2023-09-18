package es.unican.appriegospersonales.activities.apps.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.unican.appriegospersonales.activities.apps.RVCategoriaAdapter;
import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriesgospersonales.R;

public class TabHomeAppsView extends Fragment implements ITabHomeAppsContract.View {

    private ITabHomeAppsContract.Presenter presenter;
    private RecyclerView categoriasAppsRV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TabHomeAppsPresenter(this);
        presenter.init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_home_apps, container, false);
        categoriasAppsRV = layout.findViewById(R.id.categoriasApps_rv);

        categoriasAppsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        categoriasAppsRV.setAdapter(new RVCategoriaAdapter(getContext(), presenter.getCategoriasDeApps(), presenter.getPerfilDElements(), getMyApplication()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                categoriasAppsRV.getContext(),
                DividerItemDecoration.VERTICAL);
        categoriasAppsRV.addItemDecoration(dividerItemDecoration);

        return layout;
    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) requireActivity().getApplication();
    }
}
