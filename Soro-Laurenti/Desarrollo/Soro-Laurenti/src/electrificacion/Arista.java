package electrificacion;

public class Arista {
	private int nodoOrigen;

	public int getNodoOrigen() {
		return nodoOrigen;
	}

	public int getNodoDestino() {
		return nodoDestino;
	}

	public int getPeso() {
		return peso;
	}

	private int nodoDestino;
	private int peso;

	public Arista(int origen, int destino, int costo) {
		this.nodoOrigen = origen;
		this.nodoDestino = destino;
		this.peso = costo;
	}

	public void setNodoOrigen(int nodoOrigen) {
		this.nodoOrigen = nodoOrigen;
	}

	public void setNodoDestino(int nodoDestino) {
		this.nodoDestino = nodoDestino;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
}