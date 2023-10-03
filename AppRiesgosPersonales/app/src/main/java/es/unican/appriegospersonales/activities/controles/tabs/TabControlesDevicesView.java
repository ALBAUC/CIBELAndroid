package es.unican.appriegospersonales.activities.controles.tabs;

import android.os.Bundle;
import android.util.Log;
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
import es.unican.appriegospersonales.common.adapters.RVControlesAdapter;
import es.unican.appriesgospersonales.R;

public class TabControlesDevicesView extends Fragment implements ITabControlesDevicesContract.View {

    private ITabControlesDevicesContract.Presenter presenter;
    private RecyclerView controlesDevicesRV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TabControlesDevicesPresenter(this);
        presenter.init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_controles_devices, container, false);
        controlesDevicesRV = layout.findViewById(R.id.controlesDevices_rv);

        controlesDevicesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        controlesDevicesRV.setAdapter(new RVControlesAdapter(getContext(), presenter.getControlesDevices(), presenter.getPerfilControls()));

        DividerItemDecoration dividerC = new DividerItemDecoration(
                controlesDevicesRV.getContext(),
                DividerItemDecoration.VERTICAL);
        controlesDevicesRV.addItemDecoration(dividerC);
        return layout;
    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) requireActivity().getApplication();
    }
}
