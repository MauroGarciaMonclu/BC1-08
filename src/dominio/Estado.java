package dominio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Estado {
	private int[][] terreno;
	private Accion ac;
	private int K;
	private int Xt;
	private int Yt;
	private int MAX;

	public Estado(Accion ac, int K, int MAX, int[][]terreno,int Xt, int Yt) {
		this.Xt = ac.getDesplazamiento()[0];
		this.Yt = ac.getDesplazamiento()[1];
		this.terreno=terreno;
		int total = 0;
		for (int i = 0; i < ac.getDistribucion()[0].length; i++) {
			this.terreno[ac.getDistribucion()[1][i]][ac.getDistribucion()[2][i]] += ac.getDistribucion()[0][i];
			total += ac.getDistribucion()[0][i];
		}
		this.terreno[Xt][Yt] -= total;
		this.ac = ac;
		this.K = K;
		this.MAX = MAX;
		for(int j=0;j<this.terreno.length;j++) {
			for(int k=0;k<this.terreno[j].length;k++) {
				System.out.print(this.terreno[j][k]+" ");
			}
			System.out.println();
		}
	}

	public Estado(String nombre) {
		Leer_Terreno(nombre);
	}

	public Estado(int Xt, int Yt, int K, int MAX, int C, int F, String nombre) throws IOException {
		this.Xt = Xt;
		this.Yt = Yt;
		this.K = K;
		this.MAX = MAX;
		terreno = Generar_Terreno(C, F);
		Escribir_Terreno(nombre);
	}

	public int Costo(Accion acciones) {
		int arena = 1;
		for (int i = 0; i < acciones.getDistribucion()[0].length; i++) {
			arena += acciones.getDistribucion()[0][i];
		}
		return arena;
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
					pw.print(" " + terreno[i][j]);
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

	public int[][] getTerreno() {
		return terreno;
	}

	public void setTerreno(int[][] terreno) {
		this.terreno = terreno;
	}

	public Accion getAc() {
		return ac;
	}

	public void setAc(Accion ac) {
		this.ac = ac;
	}

	public int getK() {
		return K;
	}

	public void setK(int k) {
		K = k;
	}

	public int getXt() {
		return Xt;
	}

	public void setXt(int xt) {
		Xt = xt;
	}

	public int getYt() {
		return Yt;
	}

	public void setYt(int yt) {
		Yt = yt;
	}

	public int getMAX() {
		return MAX;
	}

	public void setMAX(int mAX) {
		MAX = mAX;
	}
}