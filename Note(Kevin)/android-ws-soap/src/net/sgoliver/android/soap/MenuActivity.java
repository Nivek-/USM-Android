
package net.sgoliver.android.soap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends Activity {

	
	private Button lun;
	private Button mar;
	private Button mie;
	private Button jue;
	private Button vie;
	

	private TextView entrada1;
	private TextView entrada2;
	private TextView fondo1;
	private TextView fondo2;
	private TextView postre1;
	private TextView postre2;
	
	public static String dia;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		lun = (Button) findViewById(R.id.button1);
		mar = (Button) findViewById(R.id.button2);
		mie = (Button) findViewById(R.id.button3);
		jue = (Button) findViewById(R.id.button4);
		vie = (Button) findViewById(R.id.button5);
		
		entrada1 = (TextView) findViewById(R.id.textView2);
		entrada2 = (TextView) findViewById(R.id.textView8);
		
		fondo1 = (TextView) findViewById(R.id.textView4);
		fondo2 = (TextView) findViewById(R.id.textView10);
		
		postre1 = (TextView) findViewById(R.id.textView6);
		postre2 = (TextView) findViewById(R.id.textView12);
		
		dia = obtenerDiaSemana();
		
		Toast.makeText(getBaseContext(), dia,
				Toast.LENGTH_LONG).show();
		
		
		//Date fecha2 = "05/11/2014";
		
		
		
	}
	
	private static String obtenerDiaSemana(){
	      String[] dias={"Domingo","Lunes","Martes", "Miércoles","Jueves","Viernes","Sábado"};
	        Date hoy=new Date();
	      int numeroDia=0;
	      Calendar cal= Calendar.getInstance();
	      cal.setTime(hoy);
	      numeroDia=cal.get(Calendar.DAY_OF_WEEK);
	      System.out.println("hoy es "+ dias[numeroDia - 1]);
	      String dia = dias[numeroDia - 1];
	      return (dia);
	    }
	
	private static String obtenerPrimerDia(){

		Date ahora = new Date();
	        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
	      String fecha = formateador.format(ahora);
		
		if (dia == "Lunes"){
			return fecha;
		}else if (dia == "Martes"){
			
			return dia;
		}else if (dia == "Miercoles"){
			
			return dia;
		}else if (dia == "Jueves"){
			
			return dia;
		}else if (dia == "Viernes"){
			
			return dia;
		}else 
		
		return dia;
	}
	
}
