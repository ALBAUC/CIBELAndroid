package es.unican.appriegospersonales.activities.riesgos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import es.unican.appriegospersonales.activities.main.MainView;
import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.common.adapters.RVAmenazasAdapter;
import es.unican.appriesgospersonales.R;

public class AmenazasView extends Fragment implements IAmenazasContract.View, MainView.RefreshableFragment {
    private IAmenazasContract.Presenter presenter;
    private RecyclerView riesgosDeicesRV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AmenazasPresenter(this);
        presenter.init();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_riesgos, container, false);
        riesgosDeicesRV = layout.findViewById(R.id.riesgosDevices_rv);

        riesgosDeicesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        riesgosDeicesRV.setAdapter(new RVAmenazasAdapter(getContext(), presenter.getAmenazas(), presenter.getPerfilControls()));

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

    @Override
    public void refreshData() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new AmenazasView())
                .commit();
    }
}
