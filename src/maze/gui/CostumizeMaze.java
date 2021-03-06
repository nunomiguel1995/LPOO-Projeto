package maze.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import maze.logic.*;

public class CostumizeMaze extends JPanel implements MouseListener{

	private int width, height, numeroDragoes, altura, largura;
	private Image hero, armedHero, sword, dragon, wall, floor, exit, sleepDragon;
	private char[][] maze;
	private Heroi h;
	private Espada espada;
	private Dragao[] d;
	private String selection;
	private String modoJogo;
	private Ponto saida;
	private Jogo j;
	private int i=0;
	
	public CostumizeMaze(int altura, int largura, int numeroDragoes,String modoJogo) {
		maze= new char[largura][altura];	
		d= new Dragao[numeroDragoes];
		width= (int) (634/largura);
		height= (int) (525/ altura);
		this.modoJogo=modoJogo;
		this.altura=altura;
		this.largura=largura;
		selection="Parede";
		this.numeroDragoes=numeroDragoes;
		j= new Jogo(altura,largura,numeroDragoes);
		
		hero = new ImageIcon("images/hero.png").getImage();		
		armedHero = new ImageIcon("images/armedHero.png").getImage();
		sword = new ImageIcon("images/sword.png").getImage();
		dragon = new ImageIcon("images/dragon.png").getImage();
		wall = new ImageIcon("images/wall.png").getImage();
		floor = new ImageIcon("images/floor.png").getImage();
		exit = new ImageIcon("images/exit.png").getImage();
		sleepDragon= new ImageIcon("images/sleepDragon.png").getImage();
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
	
	public int getAltura(){
		return altura;
	}
	
	public int getLargura(){
		return largura;
	}

	public void setSelection(String s){
		this.selection=s;
	}
	
	public Jogo getJogo(){
		j.getLabirinto().setMapa(maze.clone());
		j.getLabirinto().setHeroi(h);
		j.getLabirinto().setEspada(espada);
		j.getLabirinto().setDragao(d.clone());		
		switch(modoJogo){
		case "Est�ticos": 
			j.setComportamentoDragao(1);
			break;
		case "Movimenta��o aleat�ria":
			j.setComportamentoDragao(2);
			break;
		case "Movimenta��o e adormecer":
			j.setComportamentoDragao(3);
			break;
		}
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
		
		if((int) coordX != largura-1 && (int) coordY != 0 && (int) coordY != altura-1 && (int) coordX !=0){
			switch(selection){
			case "Ch�o": 
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
					d[i]=dragao;
					i++;
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

