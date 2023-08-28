package io.character.streams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.time.LocalDate;

public class FileWriterReaderTest {

	public static void main(String[] args) {
		Charset utf8 = Charset.forName("UTF-8");

		try (FileWriter fileWriter = new FileWriter("FileReaderTest.txt");
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				PrintWriter printWriter = new PrintWriter(bufferedWriter, true)) {
			System.out.println("Writing using encoding: " + fileWriter.getEncoding());

			printWriter.println(LocalDate.now());
			printWriter.println(Integer.MAX_VALUE);
			printWriter.println(Long.MIN_VALUE);
			printWriter.println(Math.PI);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (FileReader fileReader = new FileReader("FileReaderTest.txt", utf8);
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {

			LocalDate ld = LocalDate.parse(bufferedReader.readLine());
			int iMax = Integer.parseInt(bufferedReader.readLine());
			long lMin = Long.parseLong(bufferedReader.readLine());
			double pi = Double.parseDouble(bufferedReader.readLine());

			System.out.println("Values read:");
			System.out.println(ld);
			System.out.println(iMax);
			System.out.println(lMin);
			System.out.println(pi);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
