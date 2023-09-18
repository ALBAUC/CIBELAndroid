package es.unican.appriegospersonales.activities.apps;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.HashMap;
import java.util.Map;

import es.unican.appriegospersonales.activities.apps.tabs.TabHomeAppsView;
import es.unican.appriegospersonales.activities.apps.tabs.TabHomeDevicesView;
import es.unican.appriegospersonales.activities.riesgos.VPRiesgosAdapter;
import es.unican.appriegospersonales.activities.riesgos.tabs.TabRiesgosAppsView;
import es.unican.appriegospersonales.activities.riesgos.tabs.TabRiesgosDevicesView;
import es.unican.appriesgospersonales.R;

public class VPHomeAdapter extends FragmentStateAdapter {

    public VPHomeAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public enum Tab {
        APPS(0, R.string.riesgos_tabApps),
        DEVICES(1, R.string.riesgos_tabDevices);
        final int title;
        final int position;

        Tab(int position, @StringRes int title) {
            this.position = position;
            this.title = title;
        }

        private static final Map<Integer, VPHomeAdapter.Tab> map;
        static {
            map = new HashMap<>();
            for (VPHomeAdapter.Tab t : VPHomeAdapter.Tab.values()) {
                map.put(t.position, t);
            }
        }

        public static VPHomeAdapter.Tab byPosition(int position) {
            return map.get(position);
        }

        public int getTitle() {
            return title;
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == VPHomeAdapter.Tab.APPS.position) {
            return new TabHomeAppsView();
        } else if (position == VPHomeAdapter.Tab.DEVICES.position) {
            return new TabHomeDevicesView();
        } else {
            throw new IllegalArgumentException("Unkown position " + position);
        }
    }

    @Override
    public int getItemCount() {
        return VPHomeAdapter.Tab.values().length;
    }
}
