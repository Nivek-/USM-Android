package net.sgoliver.android.soap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class AlumnoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_alumno);
		final ImageButton btnNoticia = (ImageButton) findViewById(R.id.BtnNoticia);
		final ImageButton btnDisp = (ImageButton) findViewById(R.id.BtnDisp);
		final ImageButton btnCasino = (ImageButton) findViewById(R.id.BtnCasino);
        final ImageButton btnHorario = (ImageButton) findViewById(R.id.BtnHorario);
		
		
		btnDisp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(AlumnoActivity.this, Construccion.class);
				startActivity(i);
			}
		});
		
		btnCasino.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(AlumnoActivity.this, Construccion.class);
				startActivity(i);
			}
		});
		
		btnNoticia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(AlumnoActivity.this, NoticiasAlumActivity.class);
				startActivity(i);
			}
		});
		
		btnHorario.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(AlumnoActivity.this, MainActivity.class);
				startActivity(i);
			}
		});

	}

	// public void OnClickListener(View v) {
	// if (v.getId() == findViewById(R.id.BtnNoticia).getId()) {
	// Intent i = new Intent(ProfesorActivity.this, MainActivity.class);
	// startActivity(i);
	// } else if (v.getId() == findViewById(R.id.BtnDisp).getId()) {
	// Intent i = new Intent(ProfesorActivity.this, MainActivity.class);
	// startActivity(i);
	// } else if (v.getId() == findViewById(R.id.BtnCasino).getId()) {
	// Intent i = new Intent(ProfesorActivity.this, MainActivity.class);
	// startActivity(i);

	// }
	// }
}


