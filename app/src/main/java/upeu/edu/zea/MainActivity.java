package upeu.edu.zea;

import android.content.ContentValues;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnGuardar,btnBuscar,btnEliminar,btnEditar;
    EditText etId,etNombre,etDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnEditar = (Button) findViewById(R.id.btnEditar);

        etId = (EditText) findViewById(R.id.etid);
        etNombre= (EditText) findViewById(R.id.etnombre);
        etDescripcion= (EditText) findViewById(R.id.etdescripcion);

        final BDdatos Ddatos=new BDdatos(getApplicationContext());

        btnGuardar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                SQLiteDatabase db = Ddatos.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put(BDdatos.DatosTabla.COLUMN_ID,etId.getText().toString());
                values.put(BDdatos.DatosTabla.COLUMN_NOMBRE_PRODUCTO,etNombre.getText().toString());
                values.put(BDdatos.DatosTabla.COLUMN_DESCRIPCION,etDescripcion.getText().toString());
                Long IdGuardado= db.insert(BDdatos.DatosTabla.TABLA, BDdatos.DatosTabla.COLUMN_ID,values);
                Toast.makeText(getApplicationContext(),"Se guardo el registro"+IdGuardado,Toast.LENGTH_LONG).show();

            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                SQLiteDatabase db = Ddatos.getReadableDatabase();
                String [] ss={etId.getText().toString()};
                String [] pro ={BDdatos.DatosTabla.COLUMN_NOMBRE_PRODUCTO, BDdatos.DatosTabla.COLUMN_DESCRIPCION};
                Cursor c= db.query(BDdatos.DatosTabla.TABLA,pro, BDdatos.DatosTabla.COLUMN_ID+"=?",ss,null,null,null);
                c.moveToFirst();
                etNombre.setText(c.getString(0));
                etDescripcion.setText(c.getString(1));
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                SQLiteDatabase db = Ddatos.getWritableDatabase();
                String sel= BDdatos.DatosTabla.COLUMN_ID+"=?";
                String [] ar={etId.getText().toString()};
                db.delete(BDdatos.DatosTabla.TABLA,sel,ar);

            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                SQLiteDatabase db = Ddatos.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put(BDdatos.DatosTabla.COLUMN_NOMBRE_PRODUCTO, etNombre.getText().toString());
                values.put(BDdatos.DatosTabla.COLUMN_DESCRIPCION, etDescripcion.getText().toString());
                String [] ar={etId.getText().toString()};
                String sel= BDdatos.DatosTabla.COLUMN_ID+"=?";

                int count = db.update(BDdatos.DatosTabla.TABLA,values,sel,ar);
            }
        });

    }
}
