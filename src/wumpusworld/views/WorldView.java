package wumpusworld.views;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class WorldView {
	
	private String title = "Wumpus World - Meme version";
	
	private JFrame myFrame = new JFrame();
	private JPanel myPanel = new JPanel();
	private JFileChooser fileChooser = new JFileChooser();
	private JPanel currentWorldRow = null;
	private int fcReturnVal = 0;
	private JLabel[][] dummyIconGrid;
	
	
	
	public WorldView(){		

		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setTitle(title);
		myFrame.setSize(846, 509);
		myFrame.setResizable(false);
		myFrame.setLocationRelativeTo(null);	
		
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.PAGE_AXIS));

	}
	
	public void setIconGrid(int width, int height) {
		this.dummyIconGrid = new JLabel[width][height];
	}

	public void renderWorld(){
		
		myFrame.add(myPanel);
		myFrame.setVisible(true);
		
	}
	
	public void createWorldRow(){
		currentWorldRow =  new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
	}
	
	public void createWorldIcon(String imagePath, int x, int y){
		
		try {
			
			BufferedImage iconImage = ImageIO.read(new File(imagePath));
			JLabel picLabel = new JLabel(new ImageIcon(iconImage));

			currentWorldRow.add(picLabel);
			
			myPanel.add(currentWorldRow);			
			dummyIconGrid[x][y] = picLabel;
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void refreshWorldIcon(String imagePath, int x, int y){
		try {
			BufferedImage iconImage = ImageIO.read(new File(imagePath));
			dummyIconGrid[x][y].setIcon(new ImageIcon(iconImage));
			myPanel.repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public JFileChooser getFileChooser(){
		return fileChooser;
	}
	
	public void showFileChooser(){
		fcReturnVal = fileChooser.showOpenDialog(myFrame);
	}
	
	public File getSelectedFile(){
		
		if (fcReturnVal == JFileChooser.APPROVE_OPTION){
			return fileChooser.getSelectedFile();
		}		
		return null;
	}
	
	public void noSpaceForWumpus(){
		JOptionPane.showMessageDialog(null, "There's no place for Wumpus in this map!");
	}
	
	public void gameOverByWumpus(){
		JOptionPane.showMessageDialog(null, "Ouch! You found Wumpus!");
	}
	
	public void gameOverByPit(){
		JOptionPane.showMessageDialog(null, "B-Y-E! You fell into the pit!");
	}
	
	public void gameOverWin(){
		JOptionPane.showMessageDialog(null, "OH YEAH! YOU WIN!");
	}
	
	public void dispose(){
		myFrame.dispose();
	}
}
