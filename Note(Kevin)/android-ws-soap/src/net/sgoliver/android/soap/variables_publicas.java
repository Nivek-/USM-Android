package net.sgoliver.android.soap;

public class variables_publicas {
	public static String usuario="";
	public static String tipoUser="";
	
	//public static String direccionIp="http://10.0.2.2:5303";
	private static String direccionIp="http://192.168.50.24:80/WebUTFSM/WebServiceUSM.asmx";
	
	public static String getDireccionIp() {
		return direccionIp;
	}

	public static String getUsuario() {
		return usuario;
	}

	public static void setUsuario(String usuario) {
		variables_publicas.usuario = usuario;
	}

	public static String getTipoUser() {
		return tipoUser;
	}

	public static void setTipoUser(String tipoUser) {
		variables_publicas.tipoUser = tipoUser;
	}
	
	
	
}
