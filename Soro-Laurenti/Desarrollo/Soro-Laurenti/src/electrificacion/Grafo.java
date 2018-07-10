package electrificacion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Grafo {

	private List<List<Arista>> listaAdyacencia;

	public Grafo(final Arista[] aristas) {

		this.listaAdyacencia = new ArrayList<>();

		for (int i = 0; i < aristas.length; ++i) {
			this.listaAdyacencia.add(new ArrayList<Arista>());
		}

		for (Arista unaArista : aristas) {
			this.listaAdyacencia.get(unaArista.getNodoOrigen()).add(unaArista);
		}
	}

	public List<Arista> resolverPrim(final int[] centrales) {

		List<Arista> arbolCostoMinimo = new ArrayList<Arista>();

		PriorityQueue<Arista> colaPrioridad = new PriorityQueue<>(new Comparator<Arista>() {

			@Override
			public int compare(final Arista o1, final Arista o2) {

				Arista primero = o1;
				Arista segundo = o2;
				
				if (primero.getPeso() < segundo.getPeso()) {
					return -1;
				} else if (primero.getPeso() > segundo.getPeso()) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		boolean[] visitado = new boolean[this.listaAdyacencia.size()];

		for (int i = 0; i < centrales.length; i++) {
			for (Arista e : this.listaAdyacencia.get(centrales[i])) {
				colaPrioridad.add(e);
			}
			visitado[centrales[i]] = true;
		}

		// array con visitados
		// visitado[0] = true;

		while (!colaPrioridad.isEmpty()) {
			Arista e = colaPrioridad.peek();

			colaPrioridad.poll();

			if (visitado[e.getNodoOrigen()] && visitado[e.getNodoDestino()]) {
				continue;
			}

			visitado[e.getNodoOrigen()] = true;

			for (Arista arista : this.listaAdyacencia.get(e.getNodoDestino())) {
				if (!visitado[arista.getNodoDestino()]) {
					colaPrioridad.add(arista);
				}
			}

			visitado[e.getNodoDestino()] = true;

			arbolCostoMinimo.add(e);

		}
		return arbolCostoMinimo;
	}
}
