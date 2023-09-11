package es.unican.appriegospersonales.activities.apps.search;

import static android.content.ContentValues.TAG;

import android.app.SearchManager;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.repository.db.AplicacionDao;
import es.unican.appriegospersonales.repository.db.CategoriaDao;
import es.unican.appriegospersonales.repository.db.DaoSession;

public class AppSuggestionProvider extends SearchRecentSuggestionsProvider {
    public static final String AUTHORITY = "es.unican.appriegospersonales.activities.apps.search.AppSuggestionProvider";
    public static final int MODE = DATABASE_MODE_QUERIES;

    private AplicacionDao aplicacionDao;
    private CategoriaDao categoriaDao;

    public AppSuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String query = uri.getLastPathSegment();

        MatrixCursor cursor = new MatrixCursor(new String[]{
                BaseColumns._ID,
                SearchManager.SUGGEST_COLUMN_TEXT_1,
                SearchManager.SUGGEST_COLUMN_TEXT_2,
                SearchManager.SUGGEST_COLUMN_INTENT_DATA,
                SearchManager.SUGGEST_COLUMN_ICON_1
        });

        DaoSession daoSession = ((MyApplication) getContext().getApplicationContext()).getDaoSession();
        aplicacionDao = daoSession.getAplicacionDao();
        categoriaDao = daoSession.getCategoriaDao();

        List<Aplicacion> appSuggestions = new ArrayList<>();
        if (query != null){
            appSuggestions = getAppSuggestions(query);
        }

        // Agregar las sugerencias al cursor
        int id = 0;
        for (Aplicacion a : appSuggestions) {
            String appName = a.getNombre();
            String categoryName = a.getCategoria().getNombre();
            String intentData = "app://" + appName;
            Bitmap iconBitmap = downloadIconBitmap(a.getIcono());
            cursor.addRow(new Object[]{
                    id++,
                    appName,
                    categoryName,
                    intentData,
                    getBitmapUri(getContext(), iconBitmap, appName)
            });
        }

        return cursor;
    }

    private List<Aplicacion> getAppSuggestions(String query) {
        String modifiedQuery = removeAccents(query.trim().toLowerCase());

        List<Categoria> categorias = categoriaDao.loadAll();
        List<Long> categoriaIds = new ArrayList<>();
        for (Categoria c : categorias) {
            String nombre = removeAccents(c.getNombre().trim().toLowerCase());
            if (nombre.contains(modifiedQuery)) {
                categoriaIds.add(c.getIdCategoria());
            }
        }

        List<Aplicacion> aplicaciones = aplicacionDao.queryBuilder()
                .whereOr(
                        AplicacionDao.Properties.Nombre.like("%" + modifiedQuery + "%"),
                        AplicacionDao.Properties.Fk_categoria.in(categoriaIds)
                ).list();

        return aplicaciones;
    }

    private String removeAccents(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    private Bitmap downloadIconBitmap(String imageUrl) {
        try {
            return Picasso.get().load(imageUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Uri getBitmapUri(Context context, Bitmap bitmap, String appName) {
        if (bitmap != null) {
            try {
                File cachePath = new File(context.getCacheDir(), "images");
                cachePath.mkdirs();

                // Generar un nombre de archivo único basado en el nombre de la aplicación
                String fileName = appName + "_" + System.currentTimeMillis() + ".jpeg";

                FileOutputStream stream = new FileOutputStream(cachePath + File.separator + fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                stream.close();

                return FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", new File(cachePath, fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



}