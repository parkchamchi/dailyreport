package dailyreport;

import java.io.*;
import java.time.LocalDate;

import dailyreport.Report;

public class Save {
	final static String filename = "report.txt";

	static void WriteOnFile(Report report) {
		try (FileWriter fw = new FileWriter(filename)) {
			fw.write(LocalDate.now().toString() + '\n');

			for (Hours hour : Hours.values())
				fw.write(report.getTask(hour) + '\n');
		}
		catch (FileNotFoundException exc) {
			;
		}
		catch (IOException exc) {
			System.out.println(exc);
		}
		
	}

	static void ReadFromFile(Report report) {
		try (FileReader fr = new FileReader(filename)) {
			BufferedReader br = new BufferedReader(fr);
			String filedate = br.readLine();
			if (!filedate.equals(LocalDate.now().toString()))
				return;

			for (Hours hour : Hours.values()) {
				String task = br.readLine();
				report.setTask(hour, task);
			}
		}
		catch (FileNotFoundException exc) {
			;
		}
		catch (IOException exc) {
			System.out.println(exc);
		}
	}
}