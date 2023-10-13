package es.unican.appriegospersonales.activities.controles;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.Nullable;

import es.unican.appriegospersonales.activities.main.MainView;
import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.common.adapters.RVControlesAdapter;
import es.unican.appriesgospersonales.R;

public class ControlesView extends Fragment implements IControlesContract.View, MainView.RefreshableFragment {
    private IControlesContract.Presenter presenter;
    private RecyclerView controlesDevicesRV;

    @Override
    public void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        presenter = new ControlesPresenter(this);
        presenter.init();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable
            Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_controles, container, false);
        controlesDevicesRV = layout.findViewById(R.id.controlesDevices_rv);

        controlesDevicesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        controlesDevicesRV.setAdapter(new RVControlesAdapter(getContext(), presenter.getControles(), presenter.getPerfilControls()));

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

    @Override
    public void refreshData() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new ControlesView())
                .commit();
    }
}
