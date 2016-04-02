package maze.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import maze.logic.Jogo;

public class GraphicPanel extends JPanel{

	private int width = 25, height = 25;
	private Image hero, armedHero, sword, dragon, wall, floor, exit, sleepDragon;
	private Jogo j;
	
	/**
	 * Create the application.
	 */
	public GraphicPanel() {
		hero = new ImageIcon("hero.png").getImage();
		armedHero = new ImageIcon("armedHero.png").getImage();
		sword = new ImageIcon("sword.png").getImage();
		dragon = new ImageIcon("dragon.png").getImage();
		wall = new ImageIcon("wall.png").getImage();
		floor = new ImageIcon("floor.png").getImage();
		exit = new ImageIcon("exit.png").getImage();
		sleepDragon= new ImageIcon("sleepDragon.png").getImage();
		
		hero = hero.getScaledInstance(width, height, 1);
		armedHero = armedHero.getScaledInstance(width, height, 1);
		sword = sword.getScaledInstance(width, height, 1);
		dragon = dragon.getScaledInstance(width, height, 1);
		wall = wall.getScaledInstance(width, height, 1);
		floor = floor.getScaledInstance(width, height, 1);
		exit = exit.getScaledInstance(width, height, 1);
		sleepDragon = sleepDragon.getScaledInstance(width, height, 1);
	}
	
	public void setJogo(Jogo j){
		this.j = j;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		char[][] maze =  j.getLabirinto().getMapa();
		
		for(int i=0; i< maze.length;i++ ){
			for(int j=0;j<maze[i].length;j++){
				if(maze[i][j]=='X')
					g.drawImage(wall, i*width, j*height, this);
				if(maze[i][j]==' ')
					g.drawImage(floor, i*width, j*height, this);
				if(maze[i][j]=='H')
					g.drawImage(hero, i*width, j*height, this);
				if(maze[i][j]=='A')
					g.drawImage(armedHero, i*width, j*height, this);
				if(maze[i][j]=='D')
					g.drawImage(dragon, i*width, j*height, this);
				if(maze[i][j]=='E')
					g.drawImage(sword, i*width, j*height, this);
				if(maze[i][j]=='S')
					g.drawImage(exit, i*width, j*height, this);
				if(maze[i][j]=='d')
					g.drawImage(sleepDragon, i*width, j*height, this);
				if(maze[i][j]=='F')
					g.drawImage(dragon, i*width, j*height, this);
			}
		}
	}

}
