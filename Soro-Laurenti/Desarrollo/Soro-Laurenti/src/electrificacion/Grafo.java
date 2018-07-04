package electrificacion;

import java.util.*;

public class Grafo {

	private List<List<Arista>> listaAdyacencia;

	public Grafo(Arista[] aristas) {

		listaAdyacencia = new ArrayList<>();

		for (int i = 0; i < aristas.length; ++i) {
			listaAdyacencia.add(new ArrayList<>());
		}

		for (Arista unaArista : aristas) {
			listaAdyacencia.get(unaArista.getNodoOrigen()).add(unaArista);
		}
	}

	public List<Arista> resolverPrim(int[] centrales) {

		List<Arista> arbolCostoMinimo = new ArrayList<Arista>();

		PriorityQueue<Arista> colaPrioridad = new PriorityQueue<>(new Comparator<Arista>() {

			@Override
			public int compare(Arista o1, Arista o2) {
				Arista primero = o1;
				Arista segundo = o2;

				if (primero.getPeso() < segundo.getPeso())
					return -1;
				else if (primero.getPeso() > segundo.getPeso())
					return 1;
				else
					return 0;
			}
		});

		for (int i = 0; i < centrales.length; i++) {
			for (Arista e : listaAdyacencia.get(centrales[i])) {
				colaPrioridad.add(e);
			}
		}

		// array con visitados
		boolean[] visitado = new boolean[listaAdyacencia.size()];
		visitado[0] = true;

		while (!colaPrioridad.isEmpty()) {
			Arista e = colaPrioridad.peek();

			colaPrioridad.poll();

			if (visitado[e.getNodoOrigen()] && visitado[e.getNodoDestino()]) {
				continue;
			}

			visitado[e.getNodoOrigen()] = true;

			for (Arista arista : listaAdyacencia.get(e.getNodoDestino())) {
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