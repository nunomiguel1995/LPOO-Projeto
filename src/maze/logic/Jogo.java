package maze.logic;

import java.util.Scanner;

public class Jogo {
	public enum ModoDragao {PARADO,ALEATORIO,DORME_ALEATORIO}
	
	private Labirinto labirinto;
	private ModoDragao comportamentoDragao;

	
/*	public Jogo(){
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
	}*/
	
	public Jogo(int altura, int largura,int ndragoes){
		this.labirinto = new Labirinto(altura,largura,ndragoes);
	}
	
	public Labirinto getLabirinto(){
		return this.labirinto;
	}
	
	public void setLabirinto(Labirinto labirinto){
		this.labirinto = labirinto;
	}
	
	
/*
	public void lePosicoes(){
		char[][] mapa = this.labirinto.getMapa();
		
		for(int i = 0; i < mapa.length; i++){
			for(int j = 0; j < mapa[i].length; j++){
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
	}*/
	
	public void setComportamentoDragao(int escolha){
		if(escolha == 1)
			comportamentoDragao=ModoDragao.PARADO;
		else if(escolha == 2)
			comportamentoDragao=ModoDragao.ALEATORIO;
		else if(escolha==3)
			comportamentoDragao=ModoDragao.DORME_ALEATORIO;
		else 
			comportamentoDragao=ModoDragao.PARADO;
	}
	
	public boolean dagroesDerrotados(){
		boolean b=true;
		Dragao d[]= labirinto.getDragao();
		
		for(int i=0;i<d.length;i++){
			if(d[i].isVivo()){
				b=false;
				break;
			}
		}
		return b;
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
		
		if(labirinto.getHeroi().moveHeroi(this.labirinto, direcao) == 1){
			if(dagroesDerrotados()){
				System.out.println("Parabens, ganhou o jogo!");
				return 1;
			}else{
				System.out.println("Antes de sair tem de derrotar o Dragao.");
				return 0;
			}
		}

		labirinto.getHeroi().armaHeroi(labirinto.getEspada(),labirinto);

		for(int i=0;i < labirinto.getDragao().length;i++){
			if(labirinto.getDragao()[i].isVivo()){
				if(comportamentoDragao == ModoDragao.DORME_ALEATORIO){
					labirinto.getDragao()[i].adormeceOuAcorda();
				}

				if(comportamentoDragao == ModoDragao.ALEATORIO){
					labirinto.getDragao()[i].moveDragao(labirinto);
					labirinto.getDragao()[i].dragaoCimaEspada(labirinto.getEspada());
				}

				int combate = labirinto.getHeroi().enfrentaDragao(labirinto.getDragao()[i]);
				switch(combate){
				case 1:
					labirinto.getDragao()[i].setSimbolo(' ');
					labirinto.getDragao()[i].setMorto();
					System.out.println("Derrotou o Dragao!");
					break;
				case -1:
					System.out.println("Perdeu o jogo! Foi derrotado pelo Dragao!");
					return -1;
				}
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
