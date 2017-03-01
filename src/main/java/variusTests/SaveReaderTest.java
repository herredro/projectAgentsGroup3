package variusTests;

import java.io.File;

import fileReader.SaveFileReader;

public class SaveReaderTest {

	public static void main(String[] args) {
		SaveFileReader reader = new SaveFileReader(new File("savedmaps/testSave.txt"));

		System.out.println(reader.readFirstLine());
	}
}
