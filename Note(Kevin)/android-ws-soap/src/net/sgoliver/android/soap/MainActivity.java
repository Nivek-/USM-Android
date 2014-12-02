package net.sgoliver.android.soap;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import Clases.Asignatura;
import Clases.Noticia;
import Clases.curso;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	// referencia a la clase
		
	
	public String curso;
	public String cas;
	private String txtNombre;
	
	
	//private EditText txtNombre;
	
	private TextView txtResultado;
	private Button btnEnviar;
	private Button btnEnviar2;
	private Button btnConsultar;
	private Button button1;
    private Spinner CmbCursos;
	private TextView lblMensaje;
	private Spinner CmbAsignatura;
	private EditText lblMensaje2;
	private ListView lstNoticias;
	public String[] datos2;
	public String[] fechanot;
	public Object[] descnot;
	public Noticia[] listaNoticia;
	public int puntNoticia ;
	public TextView textView2;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//txtNombre = (EditText)findViewById(R.id.txtNombre);
		txtNombre = variables_publicas.getUsuario();
		
		if (txtNombre== null){txtNombre="18564556-8";}
		
        txtResultado = (TextView)findViewById(R.id.txtResultado);
        btnEnviar = (Button)findViewById(R.id.btnBorrar);
        btnEnviar2 = (Button)findViewById(R.id.btnEnviar2);
        btnConsultar = (Button)findViewById(R.id.btnConsultar);
        CmbCursos = (Spinner)findViewById(R.id.CmbCursos);
        button1 = (Button)findViewById(R.id.button1);
        CmbAsignatura = (Spinner)findViewById(R.id.CmbAsignatura);
        lblMensaje2 = (EditText)findViewById(R.id.lblMensaje2);
        lstNoticias = (ListView)findViewById(R.id.lstNoticias);
      
        button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {		
				TareaInsertar tarea = new TareaInsertar();
		        tarea.execute();
			}
		});
        btnEnviar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {		
				TareaWSEliminar tarea = new TareaWSEliminar();
		        tarea.execute();
			}
		});
        
        btnEnviar2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TareaUPDATE tarea = new TareaUPDATE();
		        tarea.execute();		
			}
		});
        
        btnConsultar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TareaWSConsulta tarea = new TareaWSConsulta();
		        tarea.execute();		
			}
		});
	
	
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//Tarea Asíncrona para llamar al WS de consulta en segundo plano
	public class TareaWSConsulta extends AsyncTask<String,Integer,Boolean> {
		private curso[] listaCursos;
	
		 
	    protected Boolean doInBackground(String... params) {
	    	
	    	boolean resul = true;
	 
	    	final String NAMESPACE = "http://usm.net/";
			final String URL= variables_publicas.getDireccionIp();
			final String METHOD_NAME = "ObtenerCurso_Profesor";
			final String SOAP_ACTION = "http://usm.net/ObtenerCurso_Profesor";

			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			request.addProperty("cod_profesor", txtNombre); 
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;

			envelope.setOutputSoapObject(request);

			HttpTransportSE transporte = new HttpTransportSE(URL);

			try 
			{
				transporte.call(SOAP_ACTION, envelope);

				SoapObject resSoap =(SoapObject)envelope.getResponse();
				
				
				listaCursos = new curso[resSoap.getPropertyCount()];
				
				for (int i = 0; i < listaCursos.length; i++) 
				{
			           SoapObject ic = (SoapObject)resSoap.getProperty(i);
			            
			           curso cu = new curso();
			           cu.cod_curso = (ic.getProperty(0).toString());
			           
			            
			           listaCursos[i] = cu;
			    }
			} 
			catch (Exception e) 
			{
				resul = false;
			} 
	 
	        return resul;
	    }
	    
	    protected void onPostExecute(Boolean result) {
	    	
	    	if (result)
	    	{
		    	//Rellenamos la lista con los nombres de los clientes
				final String[] datos = new String[listaCursos.length];
				 
				for(int i=0; i<listaCursos.length; i++)
					 datos[i] = listaCursos[i].cod_curso;
					 
				ArrayAdapter<String> adaptador =
				    new ArrayAdapter<String>(MainActivity.this,
				        android.R.layout.simple_list_item_1, datos);
				adaptador.setDropDownViewResource(
		                android.R.layout.simple_spinner_dropdown_item);
				 
				CmbCursos.setAdapter(adaptador);
				CmbCursos.setOnItemSelectedListener(new OnItemSelectedListener() {

			    	@Override
					public void onNothingSelected(AdapterView<?> parent) {
						lblMensaje.setText("");
						
					}
			    	
			    	
			    	@Override
			    	public void onItemSelected(AdapterView<?> parent,
		                    android.view.View v, int position, long id) {
		                curso = datos[position];        
			    		
		                
			    	TareaCmbAsignatura tarea = new TareaCmbAsignatura();
			    	tarea.execute();
			    	}
		         

					
			    });
	    	    
	    	
	    	
	    	}
	    	else
	    	{
	    		txtResultado.setText("Error!");
	    	}
	    }
	
	}

//-----------------------------------------------------------------------------------------------------	
	
	
	public class TareaCmbAsignatura extends AsyncTask<String,Integer,Boolean> {
     private Asignatura[] listaAsignatura;
     boolean resultado = true;
		@Override
		protected Boolean doInBackground(String... params) {
			final String NAMESPACE = "http://usm.net/";
			final String URL=variables_publicas.getDireccionIp();
			final String METHOD_NAME = "ObtenerAsignatura";
			final String SOAP_ACTION = "http://usm.net/ObtenerAsignatura";

			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			request.addProperty("cod_profesor", txtNombre); 
			request.addProperty("cod_curso", curso.toString()); 
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;

			envelope.setOutputSoapObject(request);

			HttpTransportSE transporte = new HttpTransportSE(URL);

			try 
			{
				transporte.call(SOAP_ACTION, envelope);

				SoapObject resSoap =(SoapObject)envelope.getResponse();
				
				
				listaAsignatura = new Asignatura[resSoap.getPropertyCount()];
				
				for (int i = 0; i < listaAsignatura.length; i++) 
				{
			           SoapObject ic = (SoapObject)resSoap.getProperty(i);
			            
			           Asignatura asig = new Asignatura();
			           asig.cod_asig = (ic.getProperty(1).toString());
			           asig.nom_asig = (ic.getProperty(0).toString());
			           
			            
			           listaAsignatura[i] = asig;
			    }
			} 
			catch (Exception e) 
			{
				resultado = false;
			} 
	 
	        return resultado;
		}
	protected void onPostExecute(Boolean resultado) {
    	
    	if (resultado)
    	{
	    	//Rellenamos la lista con las noticias
			final String[] datos2 = new String[listaAsignatura.length];
			final String[] datos = new String[listaAsignatura.length];
			for(int i=0; i<listaAsignatura.length; i++){
				 datos2[i] = listaAsignatura[i].cod_asig;				 
				datos[i] = listaAsignatura[i].nom_asig;}
			ArrayAdapter<String> adaptador =
			    new ArrayAdapter<String>(MainActivity.this,
			        android.R.layout.simple_list_item_1, datos);
			adaptador.setDropDownViewResource(
	                android.R.layout.simple_spinner_dropdown_item);
			 
			CmbAsignatura.setAdapter(adaptador);
			CmbAsignatura.setOnItemSelectedListener(new OnItemSelectedListener() {

		    	@Override
				public void onNothingSelected(AdapterView<?> parent) {
					lblMensaje.setText("");
					
				}
		    	
		    	
		    	@Override
		    	public void onItemSelected(AdapterView<?> parent,
	                    android.view.View v, int position, long id) {
	                String as = datos[position];  
	                cas = datos2[position];
		    		
	                
		    		TareaListarNoticia tarea = new TareaListarNoticia();
			    	tarea.execute();
		    	}
	         

				
		    });
    	    
    	
    	
    	}
    	else
    	{
    		txtResultado.setText("Error!");
    	}
    }
	    
	}
	//-----------------------------------------------------------------------------------------------------------
	public class TareaListarNoticia extends AsyncTask<String,Integer,Boolean> {
	     
	     boolean resultado2 = true;
			@Override
			protected Boolean doInBackground(String... params) {
				final String NAMESPACE = "http://usm.net/";
				final String URL=variables_publicas.getDireccionIp();
				final String METHOD_NAME = "ObtenerNoticia";
				final String SOAP_ACTION = "http://usm.net/ObtenerNoticia";
 
				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				request.addProperty("cod_profesor", txtNombre); 
				request.addProperty("cod_curso", curso.toString()); 
				Object coda = cas;
				request.addProperty("cod_asig", coda .toString());
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;

				envelope.setOutputSoapObject(request);

				HttpTransportSE transporte = new HttpTransportSE(URL);

				try 
				{
					transporte.call(SOAP_ACTION, envelope);

					SoapObject resSoap =(SoapObject)envelope.getResponse();
					
					
					listaNoticia = new Noticia[resSoap.getPropertyCount()];
					
					for (int i = 0; i < listaNoticia.length; i++) 
					{
				           SoapObject ic = (SoapObject)resSoap.getProperty(i);
				            
				           Noticia not = new Noticia();
				           not.cod_profesor = (ic.getProperty(0).toString());
				           not.cod_curso = (ic.getProperty(1).toString());
				           not.cod_fecha = (ic.getProperty(2).toString());
				           not.cod_asig = (ic.getProperty(3).toString());
				           not.noticia = (ic.getProperty(4).toString());
				           listaNoticia[i] = not;
				    }
				} 
 				catch (Exception e) 
				{
					resultado2 = false;
				} 
		 
		        
				return resultado2;
			}
			protected void onPostExecute(Boolean resultado2) {
		    	
		    	if (resultado2)
		    	{
			    	//Rellenamos la lista con los nombres de los clientes
					
		    		
		    		
		    		final String[] fechanot = new String[listaNoticia.length];
					final String[] descnot = new String[listaNoticia.length];
					for(int i=0; i<listaNoticia.length; i++){
						fechanot[i] = listaNoticia [i].cod_fecha;				 
						descnot[i] = listaNoticia[i].noticia;}
					ArrayAdapter<String> adaptador =
					    new ArrayAdapter<String>(MainActivity.this,
					        android.R.layout.simple_list_item_1,descnot);
					
					 
					lstNoticias.setAdapter(adaptador);
					lstNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							String noti = descnot[position]; 
							lblMensaje2.setText(noti);
							Toast.makeText(getApplicationContext(),descnot[position],Toast.LENGTH_SHORT).show();
						puntNoticia = position;
						}
					});
							
					
						
						
						
						
		    	   
					
		    	
		    	}
		    	else
		    	{
		    		txtResultado.setText("Error!");
		    	}
		    	
		    	
		    	
		    }
}
	
	//----------------------------------------------------------------------------------------------
	public class TareaWSEliminar extends AsyncTask<String,Integer,Boolean> {
	     
	     boolean resultado2 = true;
			@Override
			protected Boolean doInBackground(String... params) {
				final String NAMESPACE = "http://usm.net/";
				final String URL=variables_publicas.getDireccionIp();
				final String METHOD_NAME = "EliminarNoticia";
				final String SOAP_ACTION = "http://usm.net/EliminarNoticia";

				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				
				String codpro = listaNoticia[puntNoticia].cod_profesor;
				String codasig = listaNoticia[puntNoticia].cod_asig;
				String codcur = listaNoticia[puntNoticia].cod_curso;
				String fecha = listaNoticia[puntNoticia].cod_fecha;
				
				
				request.addProperty("Cod_profesor", codcur.toString()); 
				request.addProperty("Cod_asig", fecha.toString()); 
				request.addProperty("Cod_curso", codpro.toString()); 
				request.addProperty("Cod_fecha", codasig.toString()); 
				
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;

				envelope.setOutputSoapObject(request);

				HttpTransportSE transporte = new HttpTransportSE(URL);

				try 
				{
					transporte.call(SOAP_ACTION, envelope);
					TareaListarNoticia tarea = new TareaListarNoticia();
			    	tarea.execute();
					
				} 
				catch (Exception e) 
				{
					resultado2 = false;
				} 
		 
		        
				return resultado2;
			}
			protected void onPostExecute(Boolean resultado2) {
		    	
		    	if (resultado2)
		    	{
			    			
						
		    	   			
		    	
		    	}
		    	else
		    	{
		    		txtResultado.setText("Error!");
		    	}
		    	
		    	
		    	
		    }
}
	public class TareaUPDATE extends AsyncTask<String,Integer,Boolean> {
	     
	     boolean resultado2 = true;
			@Override
			protected Boolean doInBackground(String... params) {
				final String NAMESPACE = "http://usm.net/";
				final String URL=variables_publicas.getDireccionIp();
				final String METHOD_NAME = "ModificarNoticia";
				final String SOAP_ACTION = "http://usm.net/ModificarNoticia";

				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				
				String codpro = listaNoticia[puntNoticia].cod_profesor;
				String codasig = listaNoticia[puntNoticia].cod_asig;
				String codcur = listaNoticia[puntNoticia].cod_curso;
				String fecha = listaNoticia[puntNoticia].cod_fecha;
				String noticia = listaNoticia[puntNoticia].noticia;
				String noticia2 = listaNoticia[puntNoticia].noticia;
				
				request.addProperty("Cod_profesor", codcur.toString()); 
				request.addProperty("Cod_asig", fecha.toString()); 
				request.addProperty("Cod_curso", codpro.toString()); 
				request.addProperty("Cod_fecha", codasig.toString()); 
				request.addProperty("descripcion", lblMensaje2.getText().toString()); 
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
 				envelope.dotNet = true;

				envelope.setOutputSoapObject(request);

				HttpTransportSE transporte = new HttpTransportSE(URL);

				try 
				{
					transporte.call(SOAP_ACTION, envelope);
					TareaListarNoticia tarea = new TareaListarNoticia();
			    	tarea.execute();
					
				} 
				catch (Exception e) 
				{
					resultado2 = false;
				} 
		 
		        
				return resultado2;
			}
			protected void onPostExecute(Boolean resultado2) {
		    	
		    	if (resultado2)
		    	{
			    			
						
		    	   			
		    	
		    	}
		    	else
		    	{
		    		txtResultado.setText("Error!");
		    	}
		    	
		    	
		    	
		    }
}
	//-------------------------------------------------------------------------------------------------------
	public class TareaInsertar extends AsyncTask<String,Integer,Boolean> {
	     
	     boolean resultado2 = true;
			@Override
			protected Boolean doInBackground(String... params) {
				final String NAMESPACE = "http://usm.net/";
				final String URL=variables_publicas.getDireccionIp();
				final String METHOD_NAME = "InsertarNoticia";
				final String SOAP_ACTION = "http://usm.net/InsertarNoticia";

				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				
				
				request.addProperty("Cod_profesor", txtNombre); 
				request.addProperty("Cod_asig", cas.toString()); 
				request.addProperty("Cod_curso", curso.toString()); 
				request.addProperty("descripcion", lblMensaje2.getText().toString()); 
				
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;

				envelope.setOutputSoapObject(request);

				HttpTransportSE transporte = new HttpTransportSE(URL);

				try 
				{
					transporte.call(SOAP_ACTION, envelope);
					TareaListarNoticia tarea = new TareaListarNoticia();
			    	tarea.execute();
					
				} 
				catch (Exception e) 
				{
					resultado2 = false;
				} 
		 
		        
				return resultado2;
			}
			protected void onPostExecute(Boolean resultado2) {
		    	
		    	if (resultado2)
		    	{
			    			
						
		    	   			
		    	
		    	}
		    	else
		    	{
		    		txtResultado.setText("Error!");
		    	}
		    	
		    	
		    	
		    }
}
	
}
