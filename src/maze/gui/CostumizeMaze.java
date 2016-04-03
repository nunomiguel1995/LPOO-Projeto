package maze.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import maze.logic.*;

public class CostumizeMaze extends JPanel implements MouseListener{

	private int width = 25, height = 25, numeroDragoes, altura, largura;
	private Image hero, armedHero, sword, dragon, wall, floor, exit, sleepDragon;
	private char[][] maze;
	private Heroi h;
	private Espada espada;
	private ArrayList<Dragao> d;
	private String selection;
	private String modoJogo;
	private Ponto saida;
	
	public CostumizeMaze(int altura, int largura, int numeroDragoes,String modoJogo) {
		maze= new char[largura][altura];	
		d= new ArrayList<Dragao>();
		this.modoJogo=modoJogo;
		this.altura=altura;
		this.largura=largura;
		selection="Parede";
		this.numeroDragoes=numeroDragoes;
		
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
		
		for(int i=0;i<maze.length;i++){
			for(int j=0; j< maze[i].length;j++){
				maze[i][j]='X';
			}
		}
		
		addMouseListener(this);	
	}
	
	public void setSelection(String s){
		this.selection=s;
	}
	
	public Jogo getJogo(){
		Jogo j= new Jogo(altura,largura,numeroDragoes);
		j.getLabirinto().setMapa(maze);
		j.getLabirinto().setHeroi(h);
		j.getLabirinto().setEspada(espada);
		Dragao[] nDragoes= new Dragao[numeroDragoes];
		j.getLabirinto().setDragoes(d.toArray(nDragoes));
		
		return j;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
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

	@Override
	public void mouseClicked(MouseEvent e) {		
	}

	@Override
	public void mouseEntered(MouseEvent e) {	
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		float mouseX= e.getX();
		float mouseY= e.getY();
		double coordX= ((float)(mouseX / width));
		double coordY= ((float)(mouseY/height));
		
		if((int) coordX != largura-1 && (int) coordY != 0 && (int) coordY != altura-1){
			switch(selection){
			case "Chão": 
				 maze[(int) coordX][(int)coordY]=' ';
				 break;
			case "Parede":
				 maze[(int) coordX][(int)coordY]='X';
				 break;
			case "Heroi":
				if(h!=null){
					maze[h.getPosicao().getX()][h.getPosicao().getY()]=' ';
				}
				maze[(int) coordX][(int)coordY]='H';
				h= new Heroi(new Ponto((int) coordX, (int) coordY),'H');
				break;
			case "Espada":
				if(espada!=null){
					maze[espada.getPosicao().getX()][espada.getPosicao().getY()]=' ';
				}
				maze[(int) coordX][(int)coordY]='E';
				espada= new Espada(new Ponto((int) coordX, (int) coordY),'E');
				break;
			case "Dragao":
				if(numeroDragoes>0){
					maze[(int) coordX][(int)coordY]='D';
					Dragao dragao= new Dragao(new Ponto((int) coordX, (int) coordY),'D');
					d.add(dragao);
					numeroDragoes--;
				}
				break;
			}
		}
		if(((int)coordX == 0 &&(int) coordY!=0 &&(int) coordX!= altura-1)|| 
		   ((int)coordX == largura-1 &&(int) coordY!=0 && (int)coordY!= altura-1)||
		  ((int)coordY == 0 &&(int) coordX != 0 && (int)coordX != largura-1)||
		  ((int)coordY == altura-1 &&(int) coordX != 0 && (int)coordX != largura-1)){
			
			if(selection == "Saida"){
				if(saida!=null){
					maze[saida.getX()][saida.getY()]='X';
				}
				maze[(int) coordX][(int)coordY]='S';
				saida= new Ponto((int)coordX,(int) coordY);
			}
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {	
	}
}

