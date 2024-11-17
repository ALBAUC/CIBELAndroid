package es.unican.cibel.activities.activos.detail.cve;

import java.util.List;

import es.unican.cibel.common.MyApplication;
import es.unican.cibel.model.Debilidad;

public interface ICveDetailContract {

    interface View {
        MyApplication getMyApplication();
    }

    interface Presenter {
        void init();

        double getCveBaseScore();

        String getCveId();

        String getCveDescripcion();

        String getCveConfidenciality();

        String getCveIntegrity();

        String getCveAvailability();

        int mapImpact(String impact);

        List<Debilidad> getCwes();

        String getIdCWE(Debilidad cwe);

        String getNombre(Debilidad cwe);

        String getDescripcion(Debilidad cwe);
    }
}
