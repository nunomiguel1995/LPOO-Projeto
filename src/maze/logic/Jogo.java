package maze.logic;

import java.util.Scanner;

public class Jogo {
	public enum ModoDragao {PARADO,ALEATORIO,DORME_ALEATORIO}
	public enum EstadoJogo {GANHOU,PERDEU,SAIDA_FECHADA,INICIAL} 
	public enum Direcao {CIMA, BAIXO, ESQUERDA, DIREITA}
	
	private Labirinto labirinto;
	private ModoDragao comportamentoDragao;
	private EstadoJogo estado;
		
	public Jogo(int altura, int largura,int ndragoes){
		this.labirinto = new Labirinto(altura,largura,ndragoes);
		estado=EstadoJogo.INICIAL;
	}
	
	public Jogo(Labirinto labirinto){
		this.labirinto = labirinto;
	}
	
	public Labirinto getLabirinto(){
		return this.labirinto;
	}
	
	public void setLabirinto(Labirinto labirinto){
		this.labirinto = labirinto;
	}
	
	public EstadoJogo getEstado() {
		return estado;
	}

	public void setEstado(EstadoJogo estado) {
		this.estado = estado;
	}

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

	public void jogada(Direcao direcao){	
		if(labirinto.getHeroi().moveHeroi(this.labirinto, direcao) == 1){
			if(dagroesDerrotados()){
				setEstado(EstadoJogo.GANHOU);
				return;
			}else{
				setEstado(EstadoJogo.SAIDA_FECHADA);
				return;
			}
		}

		labirinto.getHeroi().armaHeroi(labirinto.getEspada(),labirinto);

		for(int i=0;i < labirinto.getDragao().length;i++){
			if(labirinto.getDragao()[i].isVivo()){
				if(comportamentoDragao == ModoDragao.DORME_ALEATORIO){
					labirinto.getDragao()[i].adormeceOuAcorda();
					if(!labirinto.getDragao()[i].getAdormecido()){
						labirinto.getDragao()[i].moveDragao(labirinto);
						labirinto.getDragao()[i].dragaoCimaEspada(labirinto.getEspada());
					}
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
					break;
				case -1:
					setEstado(EstadoJogo.PERDEU);
					return;
				}
			}
		}

		this.labirinto.atualizaLabirinto();
		return;
	}
	
	/**
	 * Devolve o labirinto sob forma de string
	 * @return labirinto
	 */
	public String toString(){
		String str = "";
		for(int i=0; i< labirinto.getMapa().length;i++){
			for(int j=0; j< labirinto.getMapa()[i].length;j++){
				str+=labirinto.getMapa()[i][j];
				str+=" ";
			}
			str+="\n";
		}
		return str;
	}
	
	/**
	 * Devolve o estado do jogo sob forma textual
	 * @return estado do jogo
	 */
	public String imprimeEstado(){
		String str="";
		if(estado == EstadoJogo.GANHOU)
			 str="Parabens, ganhou o jogo!";
		else if(estado == EstadoJogo.PERDEU)
			 str="Foi derrotado! Perdeu o jogo";
		else if(estado == EstadoJogo.SAIDA_FECHADA)
			 str="Antes de sair tem de derrotar o dragao";
		
		return str;
	}
}
