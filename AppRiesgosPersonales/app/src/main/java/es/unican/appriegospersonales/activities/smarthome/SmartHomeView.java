package es.unican.appriegospersonales.activities.smarthome;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.unican.appriegospersonales.common.TextThumbSeekBar;
import es.unican.appriegospersonales.activities.main.MainView;
import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.common.adapters.RVActivosPerfilAdapter;
import es.unican.appriesgospersonales.R;

public class SmartHomeView extends Fragment implements ISmartHomeContract.View, MainView.RefreshableFragment {

    private ISmartHomeContract.Presenter presenter;
    private RecyclerView devicesRV;
    private TextThumbSeekBar ecoRatingBar;
    private TextThumbSeekBar securityBar;
    private TextView pobreTV;
    private TextView justoTV;
    private TextView buenoTV;
    private TextView excelenteTV;
    private TextView sInseguroTV;
    private TextView sMejorableTV;
    private TextView sBuenoTV;
    private TextView sExcelenteTV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SmartHomePresenter(this);
        presenter.init();
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_smart_home, container, false);
        devicesRV = layout.findViewById(R.id.devicesPerfil_rv);

        ecoRatingBar = layout.findViewById(R.id.eco_sb);
        pobreTV = layout.findViewById(R.id.pobre_tv);
        justoTV = layout.findViewById(R.id.justo_tv);
        buenoTV = layout.findViewById(R.id.bueno_tv);
        excelenteTV = layout.findViewById(R.id.excelente_tv);

        securityBar = layout.findViewById(R.id.security_sb);
        sInseguroTV = layout.findViewById(R.id.sInseguro_tv);
        sMejorableTV = layout.findViewById(R.id.sMejorable_tv);
        sBuenoTV = layout.findViewById(R.id.sBueno_tv);
        sExcelenteTV = layout.findViewById(R.id.sExcelente_tv);

        devicesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        devicesRV.setAdapter(new RVActivosPerfilAdapter(getContext(), presenter.getActivosPerfilOrdenadosPorRiesgo(), getMyApplication()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                devicesRV.getContext(),
                DividerItemDecoration.VERTICAL);
        devicesRV.addItemDecoration(dividerItemDecoration);

        TextView numDevicesTV = layout.findViewById(R.id.numDevices_tv);
        numDevicesTV.setText(String.valueOf(presenter.getActivosPerfil().size()));
        TextView numVulnerabilitiesTV = layout.findViewById(R.id.numVulnerabilities_tv);
        numVulnerabilitiesTV.setText(String.valueOf(presenter.getVulnerabilidadesPerfil().size()));

        ecoRatingBar.setProgress(presenter.getEcoRatingHome());
        ecoRatingBar.setEnabled(false);
        configurarSeekBar(ecoRatingBar, pobreTV, justoTV, buenoTV, excelenteTV);

        securityBar.setProgress(presenter.getSecurityRatingHome());
        securityBar.setEnabled(false);
        configurarSeekBar(securityBar, sInseguroTV, sMejorableTV, sBuenoTV, sExcelenteTV);

        return layout;
    }

    private void configurarSeekBar(TextThumbSeekBar seekBar, TextView tv0, TextView tv1, TextView tv2, TextView tv3) {
        int progress = seekBar.getProgress();

        Drawable thumbDrawable = ContextCompat.getDrawable(getContext(), R.drawable.shape_seek_bar_text_thumb);
        LayerDrawable layerDrawable = (LayerDrawable) thumbDrawable;
        GradientDrawable circleDrawable = (GradientDrawable) layerDrawable.getDrawable(1);

        if (progress < 25) {
            tv0.setTextColor(ContextCompat.getColor(getContext(), R.color.ecoPoor));
            tv0.setTypeface(Typeface.DEFAULT_BOLD);
            tv0.setTextSize(13f);
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.ecoPoor));
        } else if (progress < 50) {
            tv1.setTextColor(ContextCompat.getColor(getContext(), R.color.ecoFair));
            tv1.setTypeface(Typeface.DEFAULT_BOLD);
            tv1.setTextSize(13f);
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.ecoFair));
        } else if (progress < 75) {
            tv2.setTextColor(ContextCompat.getColor(getContext(), R.color.ecoGood));
            tv2.setTypeface(Typeface.DEFAULT_BOLD);
            tv2.setTextSize(13f);
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.ecoGood));
        } else {
            tv3.setTextColor(ContextCompat.getColor(getContext(), R.color.ecoExcelent));
            tv3.setTypeface(Typeface.DEFAULT_BOLD);
            tv3.setTextSize(13f);
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.ecoExcelent));
        }
        seekBar.setThumb(thumbDrawable);
    }


    @Override
    public void refreshData() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new SmartHomeView())
                .commit();
    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) requireActivity().getApplication();
    }
}
