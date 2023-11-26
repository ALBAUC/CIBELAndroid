package es.unican.appriegospersonales.activities.apps.search;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.db.ActivoDao;
import es.unican.appriegospersonales.repository.db.CategoriaDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class SearchResultPresenter implements ISearchResultContract.Presenter {

    private final ISearchResultContract.View view;
    private ActivoDao activoDao;
    private CategoriaDao categoriaDao;
    private PerfilDao perfilDao;

    public SearchResultPresenter(ISearchResultContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        activoDao = daoSession.getActivoDao();
        categoriaDao = daoSession.getCategoriaDao();
        perfilDao = daoSession.getPerfilDao();
    }

    @Override
    public List<Activo> doSearch(String query) {
        String modifiedQuery = "%" + removeAccents(query.trim().toLowerCase()) + "%";

        List<Categoria> categorias = categoriaDao.loadAll();
        List<Long> categoriaIds = new ArrayList<>();
        for (Categoria c : categorias) {
            String nombre = removeAccents(c.getNombre().trim().toLowerCase());
            if (nombre.contains(removeAccents(query.trim().toLowerCase()))) {
                categoriaIds.add(c.getIdCategoria());
            }
        }

        return activoDao.queryBuilder()
                .whereOr(
                        ActivoDao.Properties.Nombre.like(modifiedQuery),
                        ActivoDao.Properties.Fk_categoria.in(categoriaIds)
                ).list();
    }

    @Override
    public Activo getAssetByName(String appName) {
        return activoDao.queryBuilder().where(ActivoDao.Properties.Nombre.like(appName)).unique();
    }

    @Override
    public List<Activo> getPerfilAssets() {
        Perfil perfil = Perfil.getInstance(perfilDao);
        List<Activo> perfilAssets = activoDao._queryPerfil_ActivosAnhadidos(perfil.getId());
        return perfilAssets;
    }

    @Override
    public List<Activo> getActivosOrdenadosPorSeguridadDesc(String query) {
        List<Activo> activos = doSearch(query);
        Collections.sort(activos, new Comparator<Activo>() {
            @Override
            public int compare(Activo activo1, Activo activo2) {
                int gravedad1 = (int) Math.round(activo1.calcularTotalGravedad());
                int gravedad2 = (int) Math.round(activo2.calcularTotalGravedad());
                return Integer.compare(gravedad1, gravedad2);
            }
        });

        return activos;
    }

    @Override
    public List<Activo> getActivosOrdenadosPorSostAsc(String query) {
        List<Activo> activos = doSearch(query);
        Collections.sort(activos, new Comparator<Activo>() {
            @Override
            public int compare(Activo activo1, Activo activo2) {
                int gravedad1 = (int) Math.round(activo1.calcularTotalGravedad());
                int gravedad2 = (int) Math.round(activo2.calcularTotalGravedad());
                return Integer.compare(gravedad2, gravedad1);
            }
        });

        return activos;
    }

    @Override
    public List<Activo> getActivosOrdenadosPorSostDesc(String query) {
        List<Activo> activos = doSearch(query);
        Collections.sort(activos, new Comparator<Activo>() {
            @Override
            public int compare(Activo activo1, Activo activo2) {
                int gravedad1 = (int) Math.round(activo1.calcularTotalGravedad());
                int gravedad2 = (int) Math.round(activo2.calcularTotalGravedad());
                return Integer.compare(gravedad2, gravedad1);
            }
        });

        return activos;
    }

    @Override
    public List<Activo> getActivosOrdenadosPorSeguridadAsc(String query) {
        List<Activo> activos = doSearch(query);
        Collections.sort(activos, new Comparator<Activo>() {
            @Override
            public int compare(Activo activo1, Activo activo2) {
                int gravedad1 = (int) Math.round(activo1.calcularTotalGravedad());
                int gravedad2 = (int) Math.round(activo2.calcularTotalGravedad());
                return Integer.compare(gravedad2, gravedad1);
            }
        });

        return activos;
    }

    private String removeAccents(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

}
