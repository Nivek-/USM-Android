package net.sgoliver.android.soap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Button btnIngresar;
	private EditText txtUsuario;
	private EditText txtPassword;

	public final int dialogo_alert = 0;
	public String msje = "";
	public String res = "";

	// referencia a la clase
	variables_publicas variables = new variables_publicas();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_login);
		
		txtUsuario = (EditText) findViewById(R.id.txtUsuario);
		txtPassword = (EditText) findViewById(R.id.txtPassword);
		btnIngresar = (Button) findViewById(R.id.btnIngresar);

		btnIngresar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				TareaWSLog tarea = new TareaWSLog();
				tarea.execute();
			}
		});
	
	}

	// Tarea Asíncrona para llamar al WS de consulta en segundo plano
		private class TareaWSLog extends AsyncTask<String, Integer, Boolean> {

			private String exepcion;

			@Override
			protected Boolean doInBackground(String... params) {

				boolean resul = true;
				exepcion = "";

				final String NAMESPACE = "http://usm.net/";
				final String URL = variables_publicas.getDireccionIp();
				final String METHOD_NAME = "LoginUsuario";
				final String SOAP_ACTION = "http://usm.net/LoginUsuario";

				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

				request.addProperty("user", txtUsuario.getText().toString());
				request.addProperty("password", txtPassword.getText().toString());

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);

				envelope.dotNet = true;

				envelope.setOutputSoapObject(request);

				HttpTransportSE transporte = new HttpTransportSE(URL);

				try {
					transporte.call(SOAP_ACTION, envelope);

					SoapPrimitive resultado_xml = (SoapPrimitive) envelope.getResponse();
					res = resultado_xml.toString();
					msje = res;

				} catch (Exception e) {
					exepcion = e.toString();
					resul = false;
				}
				return resul;
			}

			protected void onPostExecute(Boolean result) {

				if (result) {
					// mostramos la respuesta en un toast
					Toast.makeText(getBaseContext(), res + "", Toast.LENGTH_SHORT)
							.show();

					if (res.equals("Usuario Casino")) {
						variables_publicas.setUsuario(txtUsuario.getText().toString());
						variables_publicas.setTipoUser("Casino");
						txtUsuario.setText("");
						txtPassword.setText("");
						// envia al otro activity
						Intent intent = new Intent(LoginActivity.this,CargandoActivity.class);
						startActivity(intent);
						finish();

					} else if (res.equals("Usuario Profesor")) {
						variables_publicas.setUsuario(txtUsuario.getText().toString());
						variables_publicas.setTipoUser("Profesor");
						txtUsuario.setText("");
						txtPassword.setText("");
						// envia al otro activity
						Intent intent = new Intent(LoginActivity.this,ProfesorActivity.class);
						startActivity(intent);
						finish();

					} else if (res.equals("Usuario Alumno")) {
						variables_publicas.setUsuario(txtUsuario.getText().toString());
						variables_publicas.setTipoUser("Alumno");
						txtUsuario.setText("");
						txtPassword.setText("");
						// envia al otro activity
						Intent intent = new Intent(LoginActivity.this,AlumnoActivity.class);
						startActivity(intent);
						finish();
					} else {
						Toast.makeText(getBaseContext(), exepcion,
								Toast.LENGTH_LONG).show();
					}

				}
				;
			}
		}
	//	@Override
		//public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			//getMenuInflater().inflate(R.menu.activity_main, menu);
			//return true;
		//}		
	
}
