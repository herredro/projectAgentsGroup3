package fileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import agentDefinitions.AgentWorld;
import agentDefinitions.Obstacles;

import com.badlogic.gdx.math.Vector2;

import factorys.ObstacleFactory;

public class SaveFileReader {

	private File textFile;
	private Scanner scanner;
	private ObstacleFactory factory;
	private AgentWorld world = new AgentWorld();
	private int scaleFactor = 1;

	public SaveFileReader(File textFile, AgentWorld world) {
		this.setTextFile(textFile);
		try {
			scanner = new Scanner(textFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.world = world;
		this.factory = new ObstacleFactory(world.getPhysicsWorld(), world.getObstacleIdMap());
	}

	public void loadObstacles() {
		System.out.println("loading obst.");
		while (scanner.hasNextLine()) {
			String obstacleDef = scanner.nextLine();
			// System.out.println(obstacleDef + "scannerNext");
			ArrayList<Vector2> modelCoords = extractVerticeCoords(obstacleDef);
			scaleBoard(scaleFactor, modelCoords);
			// System.out.println(modelCoords.get(0));
			world.addObstacle(createObstacleInRelative(modelCoords));
		}
	}

	public void scaleBoard(int scaleFactor, ArrayList<Vector2> polygon) {
		for (int i = 0; i < polygon.size(); i++) {
			polygon.get(i).x = scaleFactor * polygon.get(i).x;
			polygon.get(i).y = scaleFactor * polygon.get(i).y;

		}

	}

	public Obstacles createObstacleInRelative(ArrayList<Vector2> polygonAbsolute) {
		
		Vector2[] relaticeCoords = new Vector2[polygonAbsolute.size()];
		Vector2 position= polygonAbsolute.get(0).cpy();
		for (int i = 0; i < polygonAbsolute.size(); i++) {
			relaticeCoords[i] = polygonAbsolute.get(i).cpy().sub(position.cpy());
		}

		return (factory.createObstacle(relaticeCoords, position));
	}

	public ArrayList<Vector2> extractVerticeCoords(String aString) {
		Scanner stringScanner = new Scanner(aString);

		ArrayList<Vector2> returnList = new ArrayList<Vector2>();
		Vector2 tempVertice = new Vector2();
		int counter = 1;
		System.out.println(stringScanner.hasNextDouble());
		while (stringScanner.hasNextDouble()) {
			if (counter % 2 == 0) {
				tempVertice.y = (float) stringScanner.nextDouble();
				returnList.add(tempVertice);
				counter++;
			} else {
				tempVertice = new Vector2((float) stringScanner.nextDouble(), 0);

				counter++;
			}
			// System.out.println("extract coords " + tempVertice);
		}
		stringScanner.close();
		// System.out.println(returnList.size());
		return returnList;
	}

	public void setScaleFactor(int scaleFactor) {
		this.scaleFactor = scaleFactor;
	}

	public File getTextFile() {
		return textFile;
	}

	public void setTextFile(File textFile) {
		this.textFile = textFile;
	}

	public String readNextLine() {
		return scanner.nextLine();
	
	}

}
