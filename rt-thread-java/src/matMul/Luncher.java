package matMul;

import java.util.Random;

public class Luncher {

	int size;
	int prio;
	int mat1[][];
	int mat2[][];

	public Luncher(int s) {
		size = s;
		mat1 = new int[size][size];
		mat2 = new int[size][size];
	}

	public int generator() {
		return new Random().nextInt(99 - 1 + 1) + 1;
	}

	public void initMat(int m[][]) {

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				m[i][j] = 0;
			}
		}

	}

	public void randomMat(int m[][]) {

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				m[i][j] = generator();
			}
		}

	}

	public void printMat(int m[][]) {

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print("||" + m[i][j] + "||");
			}
			System.out
					.println("------------------------------------------------------------------------------------------------------------");
		}
	}

	public int[][] matMul() {

		int m3[][] = new int[size][size];
		initMat(mat1);
		initMat(mat2);
		initMat(m3);
		randomMat(mat1);
		randomMat(mat2);

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int j2 = 0; j2 < size; j2++) {
					m3[i][j] = mat1[i][j2] * mat2[j2][j];
				}
			}
		}
		return m3;
	}

}
