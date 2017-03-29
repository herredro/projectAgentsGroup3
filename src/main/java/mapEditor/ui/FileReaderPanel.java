package mapEditor.ui;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FileReaderPanel extends JPanel {

	private JLabel nameLabel ;
	private JLabel scaleLabel ;
	

	private JTextField mapName ;
	private JTextField mapScale ;
	

	
	
	

	public FileReaderPanel() {
		this.nameLabel =  new JLabel("MapName");
		this.scaleLabel = new JLabel("Scale Factor ");
		this.mapName = new JTextField("testA");
		this.mapScale = new JTextField("100");
		this.setLayout(new GridLayout(2, 2));
		this.add(nameLabel);
		this.add(mapName);
		this.add(scaleLabel);
		this.add(mapScale);
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}

	public String getMapName() {
		return mapName.getText();
	}

	public String getMapScale() {
		return mapScale.getText();
	}
	

	

}


