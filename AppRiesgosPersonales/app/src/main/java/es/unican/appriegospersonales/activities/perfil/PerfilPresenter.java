package es.unican.appriegospersonales.activities.perfil;

import java.util.List;

import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.ElementoDigital;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.model.Riesgo;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class PerfilPresenter implements IPerfilContract.Presenter {
    private final IPerfilContract.View view;
    private PerfilDao perfilDao;
    private Perfil perfil;

    public  PerfilPresenter(IPerfilContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        perfilDao = daoSession.getPerfilDao();
        perfil = Perfil.getInstance(perfilDao);
    }

    @Override
    public int getNivelRiesgo() {
        int totalRiesgos = 0;
        int riesgosMitigados = 0;

        for (ElementoDigital elementoDigital : perfil.getElementosDigitalesAnhadidos()) {
            Categoria categoria = elementoDigital.getCategoria();
            for (Riesgo riesgo : categoria.getRiesgos()) {
                totalRiesgos++;
                if (riesgoMitigadoEnPerfil(riesgo)) {
                    riesgosMitigados++;
                }
            }
        }

        if (totalRiesgos == 0) {
            return 0; // No hay riesgos para evaluar, nivel de riesgo mínimo
        }

        double porcentajeMitigado = (riesgosMitigados * 100.0) / totalRiesgos;
        int nivelRiesgo = 100 - (int) porcentajeMitigado;
        return nivelRiesgo ;
    }

    private boolean riesgoMitigadoEnPerfil(Riesgo riesgo) {
        List<Control> controlesMitigantes = riesgo.getControles();
        int controlesMitigados = 0;

        for (Control control : controlesMitigantes) {
            if (perfil.getControlesAnhadidos().contains(control)) {
                controlesMitigados++;
            }
        }

        double porcentajeMitigado = (controlesMitigados * 100.0) / controlesMitigantes.size();
        return porcentajeMitigado >= 50.0; // Riesgo se considera mitigado si está al menos al 50%
    }

}
