package electrificacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Pais extends EjercicioOIA {

	private int[] centrales;
	private Grafo grafo;
	private int cantCiudades;

	private static final String ENTRADA = "././Preparacion de prueba/Lote de Prueba/Entrada/";
	private static final String SALIDA = "././Ejecucion de prueba/Salida obtenida/";

	public Pais(String entrada, String salida) throws FileNotFoundException {

		super(new File(ENTRADA + entrada), new File(SALIDA + salida));

		Scanner sc = new Scanner(this.entrada);

		this.cantCiudades = sc.nextInt();
		int cantCentrales = sc.nextInt();

		this.centrales = new int[cantCentrales];
		for (int i = 0; i < cantCentrales; i++) {
			int nroCentral = sc.nextInt();
			centrales[i] = nroCentral - 1;
		}

		List listCentr = Arrays.asList(centrales);

		List<Arista> listaAristas = new ArrayList<Arista>();

		for (int i = 0; i < cantCiudades; i++) {
			for (int j = 0; j < cantCiudades; j++) {

				if (i != j) {
					if (!listCentr.contains(j)) {
						int costo = sc.nextInt();
						if (costo == 0) {
							continue;
						}

						listaAristas.add(new Arista(i, j, costo));
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

		grafo = new Grafo(aristas);
	}

	@Override
	public void resolver() {

		List<Arista> arbolCostoMinimo = grafo.resolverPrim(centrales);

		for (Arista arista : arbolCostoMinimo) {
			int nodoAux;

			if (arista.getNodoDestino() < arista.getNodoOrigen()) {
				nodoAux = arista.getNodoOrigen();
				arista.setNodoOrigen(arista.getNodoDestino());
				arista.setNodoDestino(nodoAux);
			}

		}

		Collections.sort(arbolCostoMinimo, new Comparator<Arista>() {

			@Override
			public int compare(Arista o1, Arista o2) {

				return o1.getNodoOrigen() - o2.getNodoOrigen();
			}
		});

		try {
			PrintWriter pw = new PrintWriter(salida);

			if (cantCiudades == centrales.length) {
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
