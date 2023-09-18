package es.unican.appriegospersonales.activities.riesgos;

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
import es.unican.appriegospersonales.activities.perfil.VPPerfilAdapter;
import es.unican.appriegospersonales.common.adapters.RVRiesgosAdapter;
import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriesgospersonales.R;

public class RiesgosView extends Fragment implements IRiesgosContract.View, MainView.RefreshableFragment {
    private IRiesgosContract.Presenter presenter;
    private ViewPager2 riesgosVP;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RiesgosPresenter(this);
        presenter.init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_riesgos, container, false);
        riesgosVP = layout.findViewById(R.id.riesgos_vp);

        riesgosVP.setAdapter(new VPRiesgosAdapter(((AppCompatActivity) getContext()).getSupportFragmentManager(), getLifecycle()));

        TabLayout tabLayout = layout.findViewById(R.id.riesgos_tl);
        new TabLayoutMediator(tabLayout, riesgosVP,
                (tab, position) -> tab.setText(VPRiesgosAdapter.Tab.byPosition(position).getTitle())
        ).attach();

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
                .replace(R.id.container, new RiesgosView())
                .commit();
    }
}
