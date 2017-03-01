package fileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import agentDefinitions.AgentWorld;
import factorys.ObstacleFactory;

public class SaveFileReader {

	private File textFile;
	private Scanner scanner;

	public SaveFileReader(File textFile) {
		this.textFile = textFile;
		try {
			scanner = new Scanner(textFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AgentWorld createSavedWorld() {
		AgentWorld world = new AgentWorld();
		ObstacleFactory factory = new ObstacleFactory(world.getPhysicsWorld());

		return null;

	}

	public String readFirstLine() {
		return scanner.nextLine();

	}

}
