package maze.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import maze.logic.Jogo;

public class GraphicPanel extends JPanel{

	private int width = 25, height = 25;
	private Image hero, armedHero, sword, dragon, wall, floor;
	private Jogo j;
	
	/**
	 * Create the application.
	 */
	public GraphicPanel() {
		hero = new ImageIcon("hero.png").getImage();
		armedHero = new ImageIcon("armedHero.png").getImage();
		sword = new ImageIcon("sword.png").getImage();
		dragon = new ImageIcon("dragon.png").getImage();
		wall = new ImageIcon("wall.jpg").getImage();
		floor = new ImageIcon("floor.jpg").getImage();
		
		hero = hero.getScaledInstance(width, height, 1);
		armedHero = armedHero.getScaledInstance(width, height, 1);
		sword = sword.getScaledInstance(width, height, 1);
		dragon = dragon.getScaledInstance(width, height, 1);
		wall = wall.getScaledInstance(width, height, 1);
		floor = wall.getScaledInstance(width, height, 1);
	}
	
	public void setJogo(Jogo j){
		this.j = j;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(hero, j.getLabirinto().getHeroi().getPosicao().getX() * width, j.getLabirinto().getHeroi().getPosicao().getY() * height, this);
		//g.drawImage(armedHero, x + width + 10, y , this);
		//g.drawImage(sword, x + width + 50, y , this);
		//g.drawImage(dragon, x + width + 100, y , this);
	}

}
