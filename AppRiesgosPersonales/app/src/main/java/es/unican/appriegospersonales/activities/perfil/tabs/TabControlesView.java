package es.unican.appriegospersonales.activities.perfil.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.common.adapters.RVControlesAdapter;
import es.unican.appriesgospersonales.R;

public class TabControlesView extends Fragment {
    private TabControlesPresenter presenter;
    private RecyclerView controlesRV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TabControlesPresenter(this);
        presenter.init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_perfil_controles, container, false);
        controlesRV = layout.findViewById(R.id.itemsAplicados_rv);

        controlesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        controlesRV.setAdapter(new RVControlesAdapter(getContext(), presenter.getControlesAnhadidos()));

        DividerItemDecoration dividerC = new DividerItemDecoration(
                controlesRV.getContext(),
                DividerItemDecoration.VERTICAL);
        controlesRV.addItemDecoration(dividerC);
        return layout;
    }

    public MyApplication getMyApplication() {
        return (MyApplication) requireActivity().getApplication();
    }
}
