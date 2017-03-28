package desktop;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import mapEditor.ui.MainFrame;

public class MapEditorSimulator {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException ex) {
			System.out.println(ex);
		}
		new MainFrame();
	}

}
