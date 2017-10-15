package PredictiveCoding;

import java.util.Scanner;

public class Main {

	private static Scanner in;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		int wigth, heigth, level;

		System.out.println("Enter Width ");
		wigth = in.nextInt();
		System.out.println("Enter Heigth ");
		heigth = in.nextInt();

		PredictiveCoding_2D obj = new PredictiveCoding_2D(heigth, wigth);
		obj.insert();

		System.out.println("Enter Number of Levels ");
		level = in.nextInt();
		obj.Qunatizer(level);
		
		obj.Predictive();
		obj.view();

	}

}
