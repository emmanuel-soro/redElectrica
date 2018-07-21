package electrificacion;

import java.io.FileNotFoundException;

public class Main {

	public static void main(final String[] args) throws FileNotFoundException {

		Pais pais = null; 
		
		pais = new Pais("01-Enunciado.in", "01-Enunciado.out");
		pais.resolver();

		pais = new Pais("02-circulo3.in", "02-circulo3.out");
		pais.resolver();

		pais = new Pais("03-2centrales1ciudad.in", "03-2centrales1ciudad.out");
		pais.resolver();

		pais = new Pais("04-ciudadesAlineadas.in", "04-ciudadesAlineadas.out");
		pais.resolver();

		pais = new Pais("05-todasCentrales.in", "05-todasCentrales.out");
		pais.resolver();

		pais = new Pais("06-todasAunaCentral.in", "06-todasAunaCentral.out");
		pais.resolver();
		
		pais = new Pais("07-2centrales.in", "07-2centrales.out");
		pais.resolver();
		
		pais = new Pais("08-estres100conexo.in", "08-estres100conexo.out");
		pais.resolver();

		System.out.println("Finalizado!");
	}

}
