package es.unican.appriegospersonales.activities.riesgos;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.HashMap;
import java.util.Map;

import es.unican.appriegospersonales.activities.riesgos.tabs.TabAmenazasAppsView;
import es.unican.appriegospersonales.activities.riesgos.tabs.TabAmenazasDevicesView;
import es.unican.appriesgospersonales.R;

public class VPAmenazasAdapter extends FragmentStateAdapter {

    public enum Tab {
        APPS(1, R.string.riesgos_tabApps),
        DEVICES(0, R.string.riesgos_tabDevices);
        final int title;
        final int position;

        Tab(int position, @StringRes int title) {
            this.position = position;
            this.title = title;
        }

        private static final Map<Integer, Tab> map;
        static {
            map = new HashMap<>();
            for (Tab t : Tab.values()) {
                map.put(t.position, t);
            }
        }

        public static Tab byPosition(int position) {
            return map.get(position);
        }

        public int getTitle() {
            return title;
        }
    }

    public VPAmenazasAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == Tab.APPS.position) {
            return new TabAmenazasAppsView();
        } else if (position == Tab.DEVICES.position) {
            return new TabAmenazasDevicesView();
        } else {
            throw new IllegalArgumentException("Unkown position " + position);
        }
    }

    @Override
    public int getItemCount() {
        return Tab.values().length;
    }
}
