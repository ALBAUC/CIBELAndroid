package es.unican.appriegospersonales.activities.apps.search;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.ElementoDigital;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.db.CategoriaDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.ElementoDigitalDao;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class SearchResultPresenter implements ISearchResultContract.Presenter {

    private final ISearchResultContract.View view;
    private ElementoDigitalDao elementoDigitalDao;
    private CategoriaDao categoriaDao;
    private PerfilDao perfilDao;

    public SearchResultPresenter(ISearchResultContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        elementoDigitalDao = daoSession.getElementoDigitalDao();
        categoriaDao = daoSession.getCategoriaDao();
        perfilDao = daoSession.getPerfilDao();
    }

    @Override
    public List<ElementoDigital> doSearch(String query) {
        String modifiedQuery = "%" + removeAccents(query.trim().toLowerCase()) + "%";

        List<Categoria> categorias = categoriaDao.loadAll();
        List<Long> categoriaIds = new ArrayList<>();
        for (Categoria c : categorias) {
            String nombre = removeAccents(c.getNombre().trim().toLowerCase());
            if (nombre.contains(removeAccents(query.trim().toLowerCase()))) {
                categoriaIds.add(c.getIdCategoria());
            }
        }

        return elementoDigitalDao.queryBuilder()
                .whereOr(
                        ElementoDigitalDao.Properties.Nombre.like(modifiedQuery),
                        ElementoDigitalDao.Properties.Fk_categoria.in(categoriaIds)
                ).list();
    }

    @Override
    public ElementoDigital getDElementByName(String appName) {
        return elementoDigitalDao.queryBuilder().where(ElementoDigitalDao.Properties.Nombre.like(appName)).unique();
    }

    @Override
    public List<ElementoDigital> getPerfilApps() {
        Perfil perfil = Perfil.getInstance(perfilDao);
        return perfil.getElementosDigitalesAnhadidos();
    }

    private String removeAccents(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

}
