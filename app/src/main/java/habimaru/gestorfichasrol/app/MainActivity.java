package habimaru.gestorfichasrol.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

/**
 * Esta pequeña aplicación es un buen ejemplo de cómo cargar y guardar archivos .xml para mantener
 * fichas de usuario
 */
public class MainActivity extends Activity {
    Jugador jug;
    private static final int ABRIRFICHERO_RESULT_CODE = 1, GUARDARFICHERO_RESULT_CODE=2;
    private String rutaActiva="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jug = new Jugador();
        imprJugador(jug);
    }

    /**
     * Este método imprime el jugador determinado por pantalla en función de sus parámetros
     * @param jug
     */
    public void imprJugador(Jugador jug){
        EditText e = (EditText) findViewById(R.id.campoNombre);
        e.setText(jug.nombre);
        e = (EditText) findViewById(R.id.campoJugador);
        e.setText(jug.jugador);
        e = (EditText) findViewById(R.id.campoVit);
        e.setText(String.valueOf(jug.vit));
        e = (EditText) findViewById(R.id.campoExp);
        e.setText(jug.exp);
        e = (EditText) findViewById(R.id.campoMision);
        e.setText(jug.mision);
        e = (EditText) findViewById(R.id.campoRaza);
        e.setText(jug.raza);
        e = (EditText) findViewById(R.id.campoSexo);
        e.setText(jug.sexo);
        e = (EditText) findViewById(R.id.campoRama);
        e.setText(jug.rama);
        TextView t = (TextView) findViewById(R.id.campoHabilidades);
        t.setText(jug.habilidades);
        t = (TextView) findViewById(R.id.campoInventario);
        t.setText(jug.inventario);
        t = (TextView) findViewById(R.id.campoArmas);
        t.setText(jug.armas);
        t = (TextView) findViewById(R.id.campoHechizos);
        t.setText(jug.hechizos);
        t = (TextView) findViewById(R.id.campoHistoria);
        t.setText(jug.historia);

    }

    /**
     * El método cargarJugador, dada una ruta del xml, lo carga
     * @param ruta
     */
    public void cargarJugador(String ruta){
        jug = new Jugador(ruta);
        imprJugador(jug);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Selección de opciones. La opción guardar serializa el objeto en un xml y la de cargar
     * se hace con uno.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_guardar) {

            EditText e = (EditText) findViewById(R.id.campoNombre);
            jug.nombre = e.getText().toString();
            e = (EditText) findViewById(R.id.campoJugador);
            jug.jugador = e.getText().toString();
            e = (EditText) findViewById(R.id.campoVit);
            jug.vit = e.getText().toString();
            e = (EditText) findViewById(R.id.campoExp);
            jug.exp = e.getText().toString();
            e = (EditText) findViewById(R.id.campoDinero);
            jug.dinero = e.getText().toString();
            e = (EditText) findViewById(R.id.campoMision);
            jug.mision = e.getText().toString();
            e = (EditText) findViewById(R.id.campoRaza);
            jug.raza = e.getText().toString();
            e = (EditText) findViewById(R.id.campoSexo);
            jug.sexo = e.getText().toString();
            e = (EditText) findViewById(R.id.campoRama);
            jug.rama = e.getText().toString();
            TextView t = (TextView) findViewById(R.id.campoArmas);
            jug.armas = t.getText().toString();
            t = (TextView) findViewById(R.id.campoHechizos);
            jug.hechizos = t.getText().toString();
            t = (TextView) findViewById(R.id.campoHabilidades);
            jug.habilidades = t.getText().toString();
            t = (TextView) findViewById(R.id.campoInventario);
            jug.inventario = t.getText().toString();
            t = (TextView) findViewById(R.id.campoHistoria);
            jug.historia = t.getText().toString();

            this.jug.toXML();

            Context context = getApplicationContext();
            CharSequence text = "¡Ficha guardada!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();



            return true;
        }
        if (id == R.id.action_cargar) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("file/*.xml");
            startActivityForResult(intent, ABRIRFICHERO_RESULT_CODE);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ABRIRFICHERO_RESULT_CODE:
                if (resultCode == RESULT_OK) {
                    // Mostramos por pantalla la ruta del archivo seleccionado.
                    String ruta = data.getData().getPath();
                    rutaActiva=ruta;
                    cargarJugador(rutaActiva);
                }
        }
    }


}
