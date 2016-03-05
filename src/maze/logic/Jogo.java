package maze.logic;

import java.util.Scanner;

import maze.logic.Dragao.ModoDragao;

public class Jogo {
	private Labirinto labirinto;
	private Heroi heroi;
	private Dragao dragao;
	private Espada espada;
	
	public Jogo(){
		this.labirinto = new Labirinto();
		this.heroi = new Heroi(new Ponto(0,0),'H');
		this.dragao = new Dragao(new Ponto(0,0),'D');
		this.espada = new Espada(new Ponto(0,0),'E');
	}
	
	public Jogo(char[][] mapa){
		this.labirinto = new Labirinto(mapa);
		this.heroi = new Heroi(new Ponto(0,0),'H');
		this.dragao = new Dragao(new Ponto(0,0),'D');
		this.espada = new Espada(new Ponto(0,0),'E');
	}
	
	public Labirinto getLabirinto(){
		return this.labirinto;
	}
	
	public void setLabirinto(Labirinto labirinto){
		this.labirinto = labirinto;
	}
	
	public Heroi getHeroi() {
		return heroi;
	}

	public void setHeroi(Heroi heroi) {
		this.heroi = heroi;
	}

	public Dragao getDragao() {
		return dragao;
	}

	public void setDragao(Dragao dragao) {
		this.dragao = dragao;
	}

	public Espada getEspada() {
		return espada;
	}

	public void setEspada(Espada espada) {
		this.espada = espada;
	}

	public void lePosicoes(){
		char[][] mapa = this.labirinto.getMapa();
		
		for(int i = 0; i < mapa.length; i++){
			for(int j = 0; j < mapa.length; j++){
				if(mapa[i][j] == 'H'){
					this.heroi.setPosicao(new Ponto(i,j));
					this.labirinto.setHeroi(heroi);
				}else if(mapa[i][j] == 'D'){
					this.dragao.setPosicao(new Ponto(i,j));
					this.labirinto.setDragao(dragao);
				}else if(mapa[i][j] == 'E'){
					this.espada.setPosicao(new Ponto(i,j));
					this.labirinto.setEspada(espada);
				}
			}
		}
	}
	
	public void setComportamentoDragao(int escolha){
		this.dragao.setComportamento(escolha);
	}
	
	public int jogada(Scanner s){
		int direcao;
		
		System.out.println("1 - Cima");
		System.out.println("2 - Baixo");
		System.out.println("3 - Esquerda");
		System.out.println("4 - Direita");
		System.out.print("Escolha a direcao: ");
		
		direcao = s.nextInt();
		System.out.println();
		
		if(this.heroi.moveHeroi(this.labirinto, direcao) == 1){
			if(this.dragao.isVivo()){
				System.out.println("Antes de sair tem de derrotar o Dragao.");
				return 0;
			}else{
				System.out.println("Parabens, ganhou o jogo!");
				return 1;
			}
		}

		this.labirinto.getHeroi().armaHeroi(this.labirinto.getEspada());
		
		if(this.dragao.isVivo()){
			if(this.dragao.getComportamento() == ModoDragao.DORME_ALEATORIO){
				this.dragao.adormeceOuAcorda();
			}
			
			if(this.dragao.getComportamento() == ModoDragao.ALEATORIO){
				this.dragao.moveDragao(this.labirinto);
				this.dragao.dragaoCimaEspada(this.espada);
			}
			
			int combate = this.heroi.enfrentaDragao(this.dragao);
			switch(combate){
			case 1:
				this.dragao.setSimbolo(' ');
				this.labirinto.setDragao(getDragao());
				this.dragao.setMorto();
				System.out.println("Derrotou o Dragao!");
				break;
			case -1:
				System.out.println("Perdeu o jogo! Foi derrotado pelo Dragao!");
				return -1;
			}
		}
		
		this.labirinto.atualizaLabirinto();
		return 0;
	}
	
	public void imprime() {
		for(int i = 0; i < this.labirinto.getMapa().length; i++){
			for(int j = 0; j < this.labirinto.getMapa()[0].length; j++){
				System.out.print(this.labirinto.getMapa()[i][j] + " ");
			}
			System.out.println();
		}
	}
}
