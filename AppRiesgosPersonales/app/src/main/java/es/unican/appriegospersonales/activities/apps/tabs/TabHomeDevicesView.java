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

public class TabHomeDevicesView extends Fragment implements ITabHomeDevicesContract.View {

    private ITabHomeDevicesContract.Presenter presenter;
    private RecyclerView categoriasDevicesRV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TabHomeDevicesPresenter(this);
        presenter.init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_home_devices, container, false);
        categoriasDevicesRV = layout.findViewById(R.id.categoriasDevices_rv);

        categoriasDevicesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        categoriasDevicesRV.setAdapter(new RVCategoriaAdapter(getContext(), presenter.getCategoriasDeDispositivos(), presenter.getPerfilDElements(), getMyApplication()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                categoriasDevicesRV.getContext(),
                DividerItemDecoration.VERTICAL);
        categoriasDevicesRV.addItemDecoration(dividerItemDecoration);

        return layout;
    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) requireActivity().getApplication();
    }
}
