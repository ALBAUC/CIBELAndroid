package es.unican.appriegospersonales.activities.activos.detail;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.HashMap;
import java.util.Map;

import es.unican.appriegospersonales.activities.activos.detail.tabs.TabCvesView;
import es.unican.appriegospersonales.activities.activos.detail.tabs.TabSostenibilidadView;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriesgospersonales.R;

public class VPAssetDetailAdapter extends FragmentStateAdapter {

    private Activo activo;

    public enum Tab {
        CVES(0, R.string.assetDetail_tabCves),
        SOSTENIBILIDAD(1, R.string.assetDetail_tabSostenibilidad);
        final int title;
        final int position;

        Tab(int position, @StringRes int title) {
            this.position = position;
            this.title = title;
        }

        private static final Map<Integer, VPAssetDetailAdapter.Tab> map;
        static {
            map = new HashMap<>();
            for (VPAssetDetailAdapter.Tab t : VPAssetDetailAdapter.Tab.values()) {
                map.put(t.position, t);
            }
        }

        public static VPAssetDetailAdapter.Tab byPosition(int position) {
            return map.get(position);
        }

        public int getTitle() {
            return title;
        }
    }

    public VPAssetDetailAdapter(FragmentManager childFragmentManager, Lifecycle lifecycle, Activo activo) {
        super(childFragmentManager, lifecycle);
        this.activo = activo;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == Tab.CVES.position) {
            return new TabCvesView(activo);
        } else if (position == Tab.SOSTENIBILIDAD.position) {
            return new TabSostenibilidadView(activo);
        } else {
            throw new IllegalArgumentException("Unkown position " + position);
        }
    }

    @Override
    public int getItemCount() {
        return Tab.values().length;
    }
}
