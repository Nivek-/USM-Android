package Clases;

public class Titular {
	private String noticia;
	private String fecha;

	public Titular(String tit, String sub){
		noticia = tit;
		fecha = sub;
	}
	
	public String getTitulo(){
		return noticia;
	}
	
	public String getSubtitulo(){
		return fecha;
	}
}
