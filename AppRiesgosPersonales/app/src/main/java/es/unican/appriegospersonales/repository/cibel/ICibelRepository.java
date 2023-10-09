package es.unican.appriegospersonales.repository.cibel;

import es.unican.appriegospersonales.common.Callback;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Control;

/**
 * Un Repository para acceder a los recursos de CIBELService
 */
public interface ICibelRepository {

    /**
     * Solicita activos de forma asincrona.
     * Una vez que los activos han sido recuperadas de la fuente,
     * el callback indicado es llamado. Persiste los activos
     * en la base de datos local.
     * @param cb callback que procesa la respuesta de forma asíncrona
     * @param categoria se utiliza para filtrar los activos por categoría
     *                  (opcional, se puede dejar a null)
     */
    void requestActivos(Callback<Activo[]> cb, String categoria);

    /**
     * Solicita elementos digitales de forma sincrona.
     * Este metodo retorna una lista de elementos digitales directamente. Persiste los
     * elementos digitales en la base de datos local.
     * @param categoria
     * @return la lista de elementos digitales
     *          null si ocurre un error
     */
    Activo[] getActivos(String categoria);

    /**
     * Solicita riesgos de forma asincrona.
     * Una vez que los riesgos han sido recuperadas de la fuente,
     * el callback indicado es llamado. Persiste los riesgos
     * en la base de datos local.
     * @param cb callback que procesa la respuesta de forma asíncrona
     */
    void requestAmenazas(Callback<Amenaza[]> cb);

    void requestAmenazasDeApps(Callback<Amenaza[]> cb);

    void requestAmenazasDeDispositivos(Callback<Amenaza[]> cb);

    /**
     * Solicita riesgos de forma sincrona.
     * Este metodo retorna una lista de riesgos directamente. Persiste los riesgos
     * en la base de datos local.
     * @return la lista de riesgos
     *          null si ocurre un error
     */
    Amenaza[] getAmenazas();

    Amenaza[] getAmenazasDeApps();

    Amenaza[] getAmenazasDeDispositivos();

    /**
     * Solicita controles de forma asincrona.
     * Una vez que los controles han sido recuperadas de la fuente,
     * el callback indicado es llamado. Persiste los controles
     * en la base de datos local.
     * @param cb callback que procesa la respuesta de forma asíncrona
     */
    void requestControles(Callback<Control[]> cb);

    /**
     * Solicita controles de forma sincrona.
     * Este metodo retorna una lista de controles directamente. Persiste los controles
     * en la base de datos local.
     * @return la lista de controles
     *          null si ocurre un error
     */
    Control[] getControles();

    Control[] getControlesDeApps();

    Control[] getControlesDeDispositivos();

    /**
     * Solicita categorias de forma asincrona.
     * Una vez que las categorias han sido recuperadas de la fuente,
     * el callback indicado es llamado. Persiste las categorias
     * en la base de datos local.
     * @param cb callback que procesa la respuesta de forma asíncrona
     */
    void requestCategorias(Callback<Categoria[]> cb);

    /**
     * Solicita categorias de forma sincrona.
     * Este metodo retorna una lista de categorias directamente. Persiste las categorias
     * en la base de datos local.
     * @return la lista de categorias
     *          null si ocurre un error
     */
    Categoria[] getCategorias();

    Categoria[] getCategoriasDeApps();

    Categoria[] getCategoriasDeDispositivos();

    boolean lastDownloadOlderThan(int minutes, String resourceName);
}
