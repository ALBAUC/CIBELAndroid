package es.unican.appriegospersonales.activities.perfil;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.HashMap;
import java.util.Map;

import es.unican.appriegospersonales.activities.perfil.tabs.TabRisksView;
import es.unican.appriegospersonales.activities.perfil.tabs.TabControlesView;
import es.unican.appriesgospersonales.R;

public class VPPerfilAdapter extends FragmentStateAdapter {

    public enum Tab {
        RISKS(0, R.string.perfil_tabRiesgos),
        CONTROLS(1, R.string.perfil_tabControles);
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

    public VPPerfilAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int p) {
        if (p == Tab.RISKS.position) {
            return new TabRisksView();
        } else if (p == Tab.CONTROLS.position) {
            return new TabControlesView();
        } else {
            throw new IllegalArgumentException("Unkown position " + p);
        }
    }

    @Override
    public int getItemCount() {
        return Tab.values().length;
    }
}
