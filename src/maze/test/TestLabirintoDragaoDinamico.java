package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;

public class TestLabirintoDragaoDinamico {
	private char [][]lab = {
			{'X','X','X','X','X'},
			{'X','E',' ','H','S'},
			{'X','D','X',' ','X'},
			{'X',' ',' ',' ','X'},
			{'X','X','X','X','X'}};

	private char [][]labVazio = {
			{'X','X','X','X','X'},
			{'X',' ',' ',' ','S'},
			{'X',' ','D',' ','X'},
			{'X',' ',' ',' ','X'},
			{'X','X','X','X','X'}};
	
	
	private Labirinto labirinto = new Labirinto(lab);
	private Heroi h = new Heroi(new Ponto(1,3), 'H');
	private Dragao[] d = {new Dragao(new Ponto(2,1), 'D')};
	private Espada e = new Espada(new Ponto(1,1), 'E');
	private Jogo j;

	public void criaJogo(){
		labirinto.setHeroi(h);
		labirinto.setEspada(e);
		labirinto.setDragao(d);
		j = new Jogo(labirinto);
	}
	
	@Test
	public void testMoveDragaoAleatorio(){
		criaJogo();
		
		assertEquals(labirinto, j.getLabirinto());
		assertEquals(new Ponto(2,1) , j.getLabirinto().getDragao()[0].getPosicao());
		assertTrue(j.getLabirinto().getDragao()[0].isVivo());
		j.setComportamentoDragao(1); //Aleatório
		
		j.getLabirinto().getDragao()[0].moveDragao(j.getLabirinto());
		int movimento = j.getLabirinto().getDragao()[0].getMovimento();
		
		if(movimento == 3){
			assertEquals(new Ponto(3,1), j.getLabirinto().getDragao()[0].getPosicao());
		}
		
	}
	
	@Test
	public void testMoveDragaoAleatorioParede(){
		criaJogo();

		assertEquals(labirinto, j.getLabirinto());
		assertEquals(new Ponto(2,1) , j.getLabirinto().getDragao()[0].getPosicao());
		assertTrue(j.getLabirinto().getDragao()[0].isVivo());
		j.setComportamentoDragao(1); //Aleatório

		j.getLabirinto().getDragao()[0].moveDragao(j.getLabirinto());
		int movimento = j.getLabirinto().getDragao()[0].getMovimento();

		if(movimento == 4 || movimento == 5){
			assertEquals(new Ponto(2,1), j.getLabirinto().getDragao()[0].getPosicao());
		}
	}
	
	@Test
	public void testMoveDragaoAleatorioCimaEspada(){
		criaJogo();

		assertEquals(labirinto, j.getLabirinto());
		assertEquals(new Ponto(2,1) , j.getLabirinto().getDragao()[0].getPosicao());
		assertTrue(j.getLabirinto().getDragao()[0].isVivo());
		j.setComportamentoDragao(1); //Aleatório
		
		j.getLabirinto().setPosEspada(new Ponto(2,1));
		assertEquals(new Ponto(2,1) , j.getLabirinto().getPosEspada());

		j.getLabirinto().getDragao()[0].dragaoCimaEspada(j.getLabirinto().getEspada());
		assertTrue(j.getLabirinto().getDragao()[0].getCimaEspada());
		assertEquals('F', j.getLabirinto().getDragao()[0].getSimbolo());
		j.getLabirinto().getEspada().setPosicao(new Ponto(1,1));
		j.getLabirinto().getDragao()[0].dragaoCimaEspada(j.getLabirinto().getEspada());
		assertFalse(j.getLabirinto().getDragao()[0].getCimaEspada());
		assertEquals('D', j.getLabirinto().getDragao()[0].getSimbolo());
		
		j.getLabirinto().getDragao()[0].setCimaEspada(true);
		assertTrue(j.getLabirinto().getDragao()[0].getCimaEspada());
		j.getLabirinto().getDragao()[0].setCimaEspada(false);
		
		j.getLabirinto().getDragao()[0].moveDragao(j.getLabirinto());
		int movimento = j.getLabirinto().getDragao()[0].getMovimento();

		if(movimento == 2){
			assertEquals(new Ponto(1,1), j.getLabirinto().getDragao()[0].getPosicao());
			j.getLabirinto().getDragao()[0].dragaoCimaEspada(j.getLabirinto().getEspada());
			assertTrue(j.getLabirinto().getDragao()[0].getCimaEspada());
			assertEquals('F', j.getLabirinto().getDragao()[0].getSimbolo());
		}
	}
	
	@Test
	public void testDragaoAdormece(){
		criaJogo();

		assertEquals(labirinto, j.getLabirinto());
		assertEquals(new Ponto(2,1) , j.getLabirinto().getDragao()[0].getPosicao());
		assertTrue(j.getLabirinto().getDragao()[0].isVivo());
		j.setComportamentoDragao(2); //Adormece

		
		j.getLabirinto().getDragao()[0].adormeceOuAcorda();
		
		if(j.getLabirinto().getDragao()[0].getAdormecido()){
			assertEquals('d', j.getLabirinto().getDragao()[0].getSimbolo());
		}else{
			assertEquals('D', j.getLabirinto().getDragao()[0].getSimbolo());
		}
	}
}