package mapEditor.ui.events;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import mapEditor.curves.PolyLine;

/**
 * Event to handle the refreshing of Canvas
 *
 * @author Kareem Horstink
 */
public class GuiEventsRefresh extends GuiEvents {

    /**
     * Constructor
     *
     * @param source The source of the event
     */


	public GuiEventsRefresh(Object source, String text) {
        super(source);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
new FileOutputStream("savedmaps/" + text
				+ ".txt"), "utf-8"))) {
            writer.write(PolyLine.nameObstacle);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }


    }

	public GuiEventsRefresh(Object source) {
		super(source);
		// try (Writer writer = new BufferedWriter(new OutputStreamWriter(
		// new FileOutputStream("savedmaps/" + mapName
		// + ".txt"), "utf-8"))) {
		// writer.write(PolyLine.nameObstacle);
		// }
		// catch (IOException e)
		// {
		// System.out.println(e);
		// }

	}


}
