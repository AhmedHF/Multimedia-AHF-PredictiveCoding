package PredictiveCoding;

import java.util.ArrayList;
import java.util.Scanner;

public class PredictiveCoding_2D {
	ArrayList<Qunatizer> qunatizer = new ArrayList<>();
	public int[][] OriginalImage;
	public int[][] Predicted;
	public int[][] Difference;
	public int[][] QunatizedDifference;
	public int[][] DeQuantizederence;
	public int[][] DecodedImage;
	public int[][] ErrorSquare;
	public int width;
	public int heigth;
	private Scanner in;

	PredictiveCoding_2D(int heigth, int width) {
		OriginalImage = new int[heigth][width];
		Predicted = new int[heigth][width];
		Difference = new int[heigth][width];
		QunatizedDifference = new int[heigth][width];
		DeQuantizederence = new int[heigth][width];
		DecodedImage = new int[heigth][width];
		ErrorSquare = new int[heigth][width];
		this.width = width;
		this.heigth = heigth;

		for (int i = 0; i < heigth; i++) {
			for (int j = 0; j < width; j++) {
				OriginalImage[i][j] = 0;
				Predicted[i][j] = 0;
				Difference[i][j] = 0;
				QunatizedDifference[i][j] = 0;
				DeQuantizederence[i][j] = 0;
				DecodedImage[i][j] = 0;
				ErrorSquare[i][j] = 0;
			}
		}
	}

	public void insert() {
		System.out.println("Enter Matriex ");
		in = new Scanner(System.in);
		for (int i = 0; i < heigth; i++) {
			for (int j = 0; j < width; j++) {
				OriginalImage[i][j] = in.nextInt();
			}
		}
	}

	public void Qunatizer(int level) {
		int step = 0;
		int start = -5;
		int end = 5;

		step = ((-1 * start) + end) / level;
		for (int i = 0; i < level; i++) {
			int endlevel = 0;
			endlevel = start + step;
			qunatizer.add(new Qunatizer(i, start, endlevel));
			start = start + step + 1;
		}
		System.out.println();
		System.out.println("------- Qunatizer Table--------- ");
		for (int i = 0; i < qunatizer.size(); i++) {
			System.out.println(qunatizer.get(i).level + " " + qunatizer.get(i).start + " -> " + qunatizer.get(i).end
					+ " " + qunatizer.get(i).average);

		}
	}

	public int max(int num1, int num2) {
		int max = 0;
		if (num1 > num2)
			max = num1;
		else
			max = num2;
		return max;
	}

	public int min(int num1, int num2) {
		int min = 0;
		if (num1 > num2)
			min = num2;
		else
			min = num1;
		return min;
	}

	public void Predictive() {
		int A, B, C;

		for (int i = 0; i < heigth; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0) {
					Predicted[i][j] = 0;
					Difference[i][j] = 0;
					QunatizedDifference[i][j] = 0;
					DeQuantizederence[i][j] = 0;
					DecodedImage[i][j] = OriginalImage[i][j];
					ErrorSquare[i][j] = 0;
				} else if (j == 0) {
					Predicted[i][j] = 0;
					Difference[i][j] = 0;
					QunatizedDifference[i][j] = 0;
					DeQuantizederence[i][j] = 0;
					DecodedImage[i][j] = OriginalImage[i][j];
					ErrorSquare[i][j] = 0;
				} else {
					// 1
					A = DecodedImage[i][j - 1];
					B = DecodedImage[i - 1][j - 1];
					C = DecodedImage[i - 1][j];
					if (B <= min(A, C)) {
						Predicted[i][j] = max(A, C);
						// DecodedImage[i][j] = max(A, C) -
						// DeQuantizederence[i][j];
					} else if (B >= max(A, C)) {
						Predicted[i][j] = min(A, C);
						// DecodedImage[i][j] = min(A, C) -
						// DeQuantizederence[i][j];
					} else {
						Predicted[i][j] = A + C - B;
						// DecodedImage[i][j] = A + C - B -
						// DeQuantizederence[i][j];
					}

					// 2
					Difference[i][j] = OriginalImage[i][j] - Predicted[i][j];

					// 3
					int num = Difference[i][j];
					for (int x = 0; x < qunatizer.size(); x++) {
						if ((num >= qunatizer.get(x).start && num <= qunatizer.get(x).end)) {

							QunatizedDifference[i][j] = qunatizer.get(x).level;

							// 4
							DeQuantizederence[i][j] = qunatizer.get(x).average;
						}
					}

					// 5
					if (B <= min(A, C)) {
						// Predicted[i][j] = max(A, C);
						DecodedImage[i][j] = max(A, C) + DeQuantizederence[i][j];
					} else if (B >= max(A, C)) {
						// Predicted[i][j] = min(A, C);
						DecodedImage[i][j] = min(A, C) + DeQuantizederence[i][j];
					} else {
						// Predicted[i][j] = A + C - B;
						DecodedImage[i][j] = A + C - B + DeQuantizederence[i][j];
					}

					// 6
					int number = 0;
					number = OriginalImage[i][j] - DecodedImage[i][j];
					ErrorSquare[i][j] = number * number;
				}
			}
		}

	}

	public void view() {
		System.out.println("-------OriginalImage--------");
		for (int i = 0; i < heigth; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(OriginalImage[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-------Predicted--------");
		for (int i = 0; i < heigth; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(Predicted[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("--------Difference--------");
		for (int i = 0; i < heigth; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(Difference[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("--------QunatizedDifference--------");
		for (int i = 0; i < heigth; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(QunatizedDifference[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("--------DeQuantizederence--------");
		for (int i = 0; i < heigth; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(DeQuantizederence[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("--------DecodedImage--------");
		for (int i = 0; i < heigth; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(DecodedImage[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("--------ErrorSquare--------");
		for (int i = 0; i < heigth; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(ErrorSquare[i][j] + " ");
			}
			System.out.println();
		}
	}

}
