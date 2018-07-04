package electrificacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GeneradorMatriz {

	public static void main(String[] args) throws FileNotFoundException {
		
		PrintWriter pw = new PrintWriter(new File("MatrizEstres.in"));
		
		pw.println("100 1");
		pw.println("1");
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(i == j) {
					pw.print("0 ");
				} else if (j == 99) {
					pw.println("1");
				} else {
					pw.print("1 ");
				}
			}
		}
		pw.close();
	}

}
