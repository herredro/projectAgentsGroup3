package fileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import agentDefinitions.AgentWorld;

import com.badlogic.gdx.math.Vector2;

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

		while (scanner.hasNextLine()) {
			String obstacleDef = scanner.nextLine();
			world.addObstacle(factory.createObstacle(null, null));

		}

		return null;

	}

	public ArrayList<Vector2> extractVerticeCoords(String aString) {
		Scanner stringScanner = new Scanner(aString);
		ArrayList<Vector2> returnList = new ArrayList<Vector2>();
		Vector2 tempVertice = new Vector2();
		int counter = 1;
		while (stringScanner.hasNextInt()) {
			if (counter % 2 == 0) {
				tempVertice.y = scanner.nextInt();
				returnList.add(tempVertice);
				counter++;
			} else {
				tempVertice = new Vector2(stringScanner.nextInt(), 0);
				counter++;
			}

		}
		stringScanner.close();
		return returnList;
	}

	public String readNextLine() {
		return scanner.nextLine();

	}

}
