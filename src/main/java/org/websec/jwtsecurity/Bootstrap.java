package org.websec.jwtsecurity;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Component
public class Bootstrap implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

	}

	public static void main(String[] args) {
		File file = new File("C:\\Users\\sk_sa\\Downloads\\Documents\\tableConvert.com_qcs7nt.csv");
		CSVParser csvParser = new CSVParserBuilder().withQuoteChar('"').withSeparator(',').build();
		try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(file)).withCSVParser(csvParser).withSkipLines(1)
				.build()) {
			List<String[]> readAll = csvReader.readAll();
			System.out.println("Size: " + readAll.size());
			readAll.stream().filter(line -> line.length != 1)
			.forEach(line -> {
//				System.out.println("Boolean: " + line.length);
//				System.out.println("Line: " + Arrays.toString(line));
				System.out.println(line[1].strip().replace("\"", ""));
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
