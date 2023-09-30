package es.unican.appriegospersonales.activities.apps.search;

import java.text.Normalizer;
import java.util.ArrayList;
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
        return perfil.getActivosAnhadidos();
    }

    private String removeAccents(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

}
