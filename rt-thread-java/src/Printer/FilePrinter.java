package Printer;

import java.io.PrintWriter;

public class FilePrinter {

	public void fileprinter(String d, String name) {

		try {
			PrintWriter writer = new PrintWriter(
					"/home/emeraude/Dropbox/TravailStage2016/data_java_test/data_" + name + ".txt", "UTF-8");
			writer.println(d);
			writer.println("");
			writer.close();
		} catch (Exception e) {
			System.out.println("Print Eror for " + d);
		}

	}
}
