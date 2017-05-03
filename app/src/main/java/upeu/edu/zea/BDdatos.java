package upeu.edu.zea;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public  class BDdatos extends SQLiteOpenHelper{

    public static class DatosTabla implements BaseColumns {
        public static final String TABLA = "producto";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NOMBRE_PRODUCTO = "nombre_producto";
        public static final String COLUMN_DESCRIPCION = "descripcion";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        private static final String CREATE_TABLA =
                "CREATE TABLE " + DatosTabla.TABLA + " (" +
                        DatosTabla.COLUMN_ID + " INTEGER PRIMARY KEY," +
                        DatosTabla.COLUMN_NOMBRE_PRODUCTO + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.COLUMN_DESCRIPCION + TEXT_TYPE + " )";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DatosTabla.TABLA;

    }

        public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Test.db";

    public BDdatos(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatosTabla.CREATE_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatosTabla.SQL_DELETE_ENTRIES);
        onCreate(db);
    }


}
