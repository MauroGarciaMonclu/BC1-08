package dominio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Terreno {
	int Xt;
	int Yt;
	int K;
	int MAX;
	int [][] terreno;

	public Terreno(String nombre) {
		Leer_Terreno(nombre);
	}

	public Terreno(int Xt, int Yt, int K, int MAX, int C, int F, String nombre) throws IOException {
		this.Xt = Xt;
		this.Yt = Yt;
		this.K = K;
		this.MAX = MAX;
		terreno = Generar_Terreno(C, F);
		Escribir_Terreno(nombre);
	}

	private void Leer_Terreno(String nombre) {
		int C, F;
		try {
			Scanner fich = new Scanner(new FileReader(nombre + ".txt"));
			Xt = fich.nextInt();
			Yt = fich.nextInt();
			K = fich.nextInt();
			MAX = fich.nextInt();
			C = fich.nextInt();
			F = fich.nextInt();
			terreno = new int[C][F];
			fich.hasNextLine();
			for (int i = 0; i < F; i++) {
				for (int j = 0; j < C; j++) {
					terreno[i][j] = fich.nextInt();
				}
				fich.hasNextLine();
			}
			fich.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Mostrar_Terreno() {
		for (int i = 0; i < terreno.length; i++) {
			for (int j = 0; j < terreno[i].length; j++) {
				System.out.print(terreno[i][j] + " ");
			}
			System.out.println();
		}
	}

	private int[][] Generar_Terreno(int C, int F) {
		int total = C * F * K;
		int aux = 0;
		terreno = new int[F][C];
		Random random = new Random();
		while (total != aux) {
			aux = 0;
			for (int i = 0; i < terreno.length; i++) {
				for (int j = 0; j < terreno[i].length; j++) {
					terreno[i][j] = random.nextInt(MAX + 1);
					aux += terreno[i][j];
				}
			}
		}
		return terreno;
	}

	private void Escribir_Terreno(String nombre) throws IOException {
		boolean comprobar = true;
		int n = 0;
		File nomb = new File(nombre + ".txt");
		while (comprobar) {
			if (n == 0) {
				nomb = new File(nombre + ".txt");
			} else {
				nomb = new File(nombre + " (" + n + ").txt");
			}
			if (!nomb.exists()) {
				comprobar = false;
			}
			n++;
		}
		FileWriter fich = new FileWriter(nomb);
		BufferedWriter bw = new BufferedWriter(fich);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(fich);
			pw.print(Xt + " " + Yt + " " + K + " " + MAX + " " + terreno.length + " " + terreno[0].length);
			pw.println();
			for (int i = 0; i < terreno.length; i++) {
				for (int j = 0; j < terreno[i].length; j++) {
					pw.print(terreno[i][j] + " ");
				}
				if (i != terreno.length - 1) {
					pw.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fich)
					fich.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		bw.close();
	}
}