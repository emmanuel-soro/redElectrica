package electrificacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Pais extends EjercicioOIA {

	private int[] centrales;
	private Grafo grafo;
	private int cantCiudades;

	private static final String ENTRADA =
	        "/home/cx.ar/esoro/git/redElectrica/Soro-Laurenti/Preparacion de prueba/Lote de Prueba/Entrada/";
	private static final String SALIDA =
	        "/home/cx.ar/esoro/git/redElectrica/Soro-Laurenti/Ejecucion de prueba/Salida obtenida/";

	public Pais(final String entrada, final String salida) throws FileNotFoundException {

		super(new File(ENTRADA + entrada), new File(SALIDA + salida));

		Scanner sc = new Scanner(this.entrada);

		this.cantCiudades = sc.nextInt();
		int cantCentrales = sc.nextInt();

		this.centrales = new int[cantCentrales];
		for (int i = 0; i < cantCentrales; i++) {
			int nroCentral = sc.nextInt();
			this.centrales[i] = nroCentral - 1;
		}

		List listCentr = Arrays.asList(this.centrales);

		List<Arista> listaAristas = new ArrayList<Arista>();
		for (int i = 0; i < this.cantCiudades; i++) {
			for (int j = 0; j < this.cantCiudades; j++) {
				if (i != j) {
					if (!listCentr.contains(j)) {
						listaAristas.add(new Arista(i, j, sc.nextInt()));
					}
				} else {
					sc.nextInt();
				}
			}
		}
		sc.close();

		Arista[] aristas = new Arista[listaAristas.size()];

		for (int i = 0; i < aristas.length; i++) {
			aristas[i] = listaAristas.get(i);
		}

		this.grafo = new Grafo(aristas);
	}

	@Override
	public void resolver() {

		List<Arista> arbolCostoMinimo = this.grafo.resolverPrim(this.centrales);

		try {
			PrintWriter pw = new PrintWriter(this.salida);

			if (this.cantCiudades == this.centrales.length) {
				pw.println("0");
			} else {
				int costoMinimo = 0;
				for (Arista arista : arbolCostoMinimo) {
					costoMinimo += arista.getPeso();
				}

				pw.println(costoMinimo);

				for (Arista arista : arbolCostoMinimo) {
					pw.println((arista.getNodoOrigen() + 1) + " " + (arista.getNodoDestino() + 1));
				}
			}

			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
