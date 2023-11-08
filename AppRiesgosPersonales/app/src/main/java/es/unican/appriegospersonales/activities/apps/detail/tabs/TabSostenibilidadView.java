package es.unican.appriegospersonales.activities.apps.detail.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import es.unican.appriegospersonales.model.Activo;
import es.unican.appriesgospersonales.R;

public class TabSostenibilidadView extends Fragment implements ITabSostenibilidadContract.View {
    private TabSostenibilidadPresenter presenter;
    private Activo activo;
    public TabSostenibilidadView(Activo activo) {
        this.activo = activo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TabSostenibilidadPresenter(activo, this);
        presenter.init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_asset_detail_eco, container, false);
        return layout;
    }
}
