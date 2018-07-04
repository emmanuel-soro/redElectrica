package electrificacion;

import java.io.File;

public abstract class EjercicioOIA {
	
	File entrada;
	File salida;
	
	public EjercicioOIA(File entrada, File salida) {
		this.entrada = entrada;
		this.salida = salida;
	}
	
	public abstract void resolver();
}
