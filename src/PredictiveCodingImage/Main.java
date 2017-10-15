package PredictiveCodingImage;

import java.io.IOException;
import java.util.Scanner;


public class Main {
	private static Scanner in;

	public static void main(String[] args) throws IOException {
		in = new Scanner(System.in);
		int wigth, heigth, level;

		System.out.println("Enter Width ");
		wigth = in.nextInt();
		System.out.println("Enter Heigth ");
		heigth = in.nextInt();

		PredictiveCoding_2D_Image obj = new PredictiveCoding_2D_Image(heigth, wigth);
		//obj.insert();
		obj.readImage("lena.jpg");
		System.out.println("Enter Number of Levels ");
		level = in.nextInt();
		obj.Qunatizer(level);
		
		obj.Predictive();
		obj.view();
		obj.writeFile();
		obj.writeImage("new.jpg");

	}

}
