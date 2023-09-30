package es.unican.appriegospersonales.activities.riesgos.tabs;

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

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.common.adapters.RVAmenazasAdapter;
import es.unican.appriesgospersonales.R;

public class TabAmenazasDevicesView extends Fragment implements ITabAmenazasDevicesContract.View {

    private ITabAmenazasDevicesContract.Presenter presenter;
    private RecyclerView riesgosDeicesRV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TabAmenazasDevicesPresenter(this);
        presenter.init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_riesgos_devices, container, false);
        riesgosDeicesRV = layout.findViewById(R.id.riesgosDevices_rv);

        riesgosDeicesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        riesgosDeicesRV.setAdapter(new RVAmenazasAdapter(getContext(), presenter.getAmenazasDevices(), presenter.getPerfilControls()));

        DividerItemDecoration dividerC = new DividerItemDecoration(
                riesgosDeicesRV.getContext(),
                DividerItemDecoration.VERTICAL);
        riesgosDeicesRV.addItemDecoration(dividerC);
        return layout;
    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) requireActivity().getApplication();
    }
}
