package Clases;

import java.io.Serializable;

public class curso implements Serializable {

public String cod_curso;

public String getCod_curso() {
	return cod_curso;
}

public void setCod_curso(String cod_curso) {
	this.cod_curso = cod_curso;
}

public curso(String cod_curso) {
	super();
	this.cod_curso = cod_curso;
}

public curso() {
	super();
} 
}
