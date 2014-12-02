package net.sgoliver.android.soap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ProfesorActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_profesor);

		// View BtnNoticia = findViewById(R.id.BtnNoticia);
		// BtnNoticia.setOnClickListener((OnClickListener) this);

		// View BtnDisp = findViewById(R.id.BtnDisp);
		// BtnNoticia.setOnClickListener((OnClickListener) this);

		// View BtnCasino = findViewById(R.id.BtnCasino);
		// BtnNoticia.setOnClickListener((OnClickListener) this);

		final ImageButton btnNoticia = (ImageButton) findViewById(R.id.BtnNoticia);
		final ImageButton btnDisp = (ImageButton) findViewById(R.id.BtnDisp);
		final ImageButton btnCasino = (ImageButton) findViewById(R.id.BtnCasino);

		
		
		btnDisp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ProfesorActivity.this, Construccion.class);
				startActivity(i);
			}
		});
		
		btnCasino.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ProfesorActivity.this, MenuActivity.class);
				startActivity(i);
			}
		});
		
		btnNoticia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ProfesorActivity.this, MainActivity.class);
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
