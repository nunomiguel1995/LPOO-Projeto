package maze.logic;

import java.util.Scanner;

public class Labirinto {
	private char layout[][];

	private Heroi heroi;
	private Espada espada;
	private Dragao dragao;
	
	private boolean dragaoParado;
	private boolean dragaoAdormece;
	
	/**
	 * Construtor vazio com layout padrão
	 */
	public Labirinto(boolean dragaoParado, boolean dragaoAdormece){
		this.layout = new char[][] {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
			};
		
		this.heroi = new Heroi(1,1,'H');
		setHeroi(heroi);
		this.espada = new Espada(1,2,'E');
		setEspada(espada);
		this.dragao = new Dragao(3,1,'D', false, false);
		setDragao(dragao);
		
		this.dragaoParado = dragaoParado;
		this.dragaoAdormece = dragaoAdormece;
	}

	/**
	 * Construtor que aceita um layout como parâmetro para definição do labirinto
	 * @param layout Layout do labirinto a definir
	 */
	public Labirinto(char layout[][], boolean dragaoParado, boolean dragaoAdormece){
		this.layout = layout;
		for(int i = 0; i < layout.length; i++){
			for(int j = 0; j < layout[0].length; j++){
				if(this.layout[i][j] == 'H'){
					this.heroi = new Heroi(i,j,'H');
				}else if(this.layout[i][j] == 'D'){
					this.dragao = new Dragao(i,j,'D', false, false);
				}else if(this.layout[i][j] == 'E'){
					this.espada = new Espada(i,j,'E');
				}
			}
		}
		this.dragaoParado = dragaoParado;
		this.dragaoAdormece = dragaoAdormece;
	}
	
	public Heroi getHeroi() {
		return heroi;
	}

	public Espada getEspada() {
		return espada;
	}

	public Dragao getDragao() {
		return dragao;
	}

	public Ponto getPosHeroi() {
		return heroi.getPosicao();
	}

	public void setPosHeroi(Ponto posHeroi) {
		this.heroi.setPosicao(posHeroi);
	}

	public Ponto getPosEspada() {
		return espada.getPosicao();
	}

	public void setPosEspada(Ponto posEspada) {
		this.espada.setPosicao(posEspada);
	}

	public Ponto getPosDragao() {
		return dragao.getPosicao();
	}

	public void setPosDragao(Ponto posDragao) {
		this.dragao.setPosicao(posDragao);
	}

	public char[][] getLayout() {
		return layout;
	}
	
	public void setLayout(char[][] layout) {
		this.layout = layout;
	}

	public void setHeroi(Heroi heroi) {
		layout[heroi.getPosicao().getX()][heroi.getPosicao().getY()] = heroi.getSimbolo();
		this.heroi = heroi;
	}
	
	public void setEspada(Espada espada){
		layout[espada.getPosicao().getX()][espada.getPosicao().getY()] = espada.getSimbolo();
		this.espada = espada;
	}
	
	public void setDragao(Dragao dragao){
		layout[dragao.getPosicao().getX()][dragao.getPosicao().getY()] = dragao.getSimbolo();
		this.dragao = dragao;
	}
	
	public char getSimbolo(Ponto p){
		return layout[p.getX()][p.getY()];
	}
	
	public void setSimbolo(Ponto p, char simb){
		layout[p.getX()][p.getY()] = simb;
	}
	
	public void print(){
		for(int i = 0; i < layout.length; i++){
			for(int j = 0; j < layout[0].length; j++){
				System.out.print(layout[i][j] + " ");
			}
			System.out.println();
		}
	}		
	
	public void atualizaLabirinto(){
		if(getDragao().getVivo())
			setDragao(getDragao());
		if(!getDragao().getCimaEspada() && !getHeroi().getArmado())
			setEspada(getEspada());
		setHeroi(getHeroi());
	}
	
	public int jogada(){
		Scanner s = new Scanner(System.in);
		String movimento;

		System.out.println("Mover herói: ");
		movimento = s.nextLine();

		if(getHeroi().moveHeroi(this, movimento)==1){
			if(!getDragao().getVivo()){
				System.out.println("Parabens, ganhou o jogo!");
				return 1;
			}
			else
				System.out.println("Antes de sair tem de derrotar o Dragao");
		}

		getHeroi().armaHeroi(getEspada());

		if(getDragao().getVivo()){
			if(dragaoAdormece)
				getDragao().adormeceOuAcorda();

			if(!getDragao().getAdormecido() && !dragaoParado){
				getDragao().moveDragao(this);
				getDragao().dragaoCimaEspada(getEspada());
			}

			int combate = getHeroi().enfrentaDragao(getDragao());
			switch(combate){
			case 1:
				getDragao().setSimbolo(' ');
				setDragao(getDragao());
				getDragao().setVivo(false);
				System.out.println("Derrotou o Dragao!");
				break;
			case -1:
				System.out.println("Game Over! Foi derrotado pelo Dragao!");
				return -1;
			}
		}
		atualizaLabirinto();
		return 0;
	}
}
