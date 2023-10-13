package es.unican.appriegospersonales.activities.perfil;

import java.util.LinkedHashSet;
import java.util.Set;

import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
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
        int totalControles = 0;
        int controlesAplicados = 0;

        Set<Categoria> categoriasPerfil = new LinkedHashSet<>();
        for (Activo a : perfil.getActivosAnhadidos()) {
            categoriasPerfil.add(a.getCategoria());
        }

        for (Categoria c : categoriasPerfil){
            for (Control control : c.getControles()) {
                totalControles++;
                if (perfil.getControlesAnhadidos().contains(control)) {
                    controlesAplicados++;
                }
            }
        }

        if (totalControles == 0) {
            return 0; // No hay controles que aplicar, nivel de riesgo mínimo
        }

        double porcentajeMitigado = (controlesAplicados * 100.0) / totalControles;
        int nivelRiesgo = 100 - (int) porcentajeMitigado;
        return nivelRiesgo ;
    }

}
