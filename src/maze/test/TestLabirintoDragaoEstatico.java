package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;
import maze.logic.Jogo.Direcao;
import maze.logic.Jogo.EstadoJogo;

public class TestLabirintoDragaoEstatico {
	private char [][]lab1 = {
			{'X','X','X','X','X'},
			{'X','E',' ','H','S'},
			{'X',' ','X',' ','X'},
			{'X',' ',' ','D','X'},
			{'X','X','X','X','X'}};
	
	private char [][]lab2 = {
			{'X','X','X','X','X'},
			{'X','E',' ','H','S'},
			{'X',' ','X',' ','X'},
			{'X','D',' ','D','X'},
			{'X','X','X','X','X'}};

	private Labirinto labirinto1 = new Labirinto(lab1);
	private Labirinto labirinto2 = new Labirinto();
	private Heroi h = new Heroi(new Ponto(1,3), 'H');
	private Dragao[] d1 = {new Dragao(new Ponto(3,3), 'D')};
	private Dragao[] d2= {new Dragao(new Ponto(3,3), 'D'), new Dragao(new Ponto(3,1),'D')};
	private Espada e = new Espada(new Ponto(1,1), 'E');
	private Jogo j;
	
	private int altura = 5, largura = 5, nDragoes = 2;
	
	public void criaJogoUmDragao(){
		labirinto1.setHeroi(h);
		labirinto1.setEspada(e);
		labirinto1.setDragao(d1);
		j = new Jogo(labirinto1);
	}
	
	public void criaJogoDoisDragoes(){
		labirinto2.setMapa(lab2);
		labirinto2.setHeroi(h);
		labirinto2.setEspada(e);
		labirinto2.setDragao(d2);
		j = new Jogo(labirinto1);
		j.setLabirinto(labirinto2);
	}
	
	@Test
	public void testGeraLabirintoMapa(){
		criaJogoUmDragao();
		
		j.getLabirinto().setMapa(lab2);
		assertSame(lab2, j.getLabirinto().getMapa());
	}
	
	@Test
	public void testMoveHeroiCelulaVazia() {
		criaJogoUmDragao();
		
		assertEquals(labirinto1, j.getLabirinto());
		assertEquals(new Ponto(1,3) , j.getLabirinto().getPosHeroi());
		
		j.jogada(Direcao.ESQUERDA);
		
		assertEquals(new Ponto(1,2), j.getLabirinto().getPosHeroi());
	}

	@Test
	public void testMoveHeroiParede(){
		criaJogoUmDragao();
		
		assertEquals(labirinto1, j.getLabirinto());
		assertEquals(new Ponto(1,3) , j.getLabirinto().getPosHeroi());
		
		j.jogada(Direcao.CIMA);
		
		assertEquals(new Ponto(1,3), j.getLabirinto().getPosHeroi());
	}

	@Test
	public void testMoveHeroiEspada(){
		criaJogoUmDragao();
		
		assertEquals(labirinto1, j.getLabirinto());
		assertEquals(new Ponto(1,3) , j.getLabirinto().getPosHeroi());
		assertFalse(j.getLabirinto().getHeroi().isArmado());
		assertEquals(new Ponto(1,1) , j.getLabirinto().getPosEspada());
		
		j.jogada(Direcao.ESQUERDA);
		j.jogada(Direcao.ESQUERDA);
		
		assertEquals(j.getLabirinto().getEspada().getPosicao() , j.getLabirinto().getPosHeroi());
		j.getLabirinto().getHeroi().armaHeroi(j.getLabirinto().getEspada(), j.getLabirinto());
		assertTrue(j.getLabirinto().getHeroi().isArmado());
		assertEquals('A', j.getLabirinto().getHeroi().getSimbolo());
	}

	@Test
	public void testMoveHeroiDragao(){
		criaJogoUmDragao();
		
		assertEquals(labirinto1, j.getLabirinto());
		assertEquals(new Ponto(1,3) , j.getLabirinto().getPosHeroi());
		assertFalse(j.getLabirinto().getHeroi().isArmado());
		assertEquals(new Ponto(1,1) , j.getLabirinto().getPosEspada());
		assertEquals(new Ponto(3,3) , j.getLabirinto().getDragao()[0].getPosicao());
		assertTrue(j.getLabirinto().getDragao()[0].isVivo());
		
		j.jogada(Direcao.BAIXO);
		assertEquals(new Ponto(2,3), j.getLabirinto().getPosHeroi());
		assertTrue(j.getLabirinto().getDragao()[0].isVivo());
		assertEquals(EstadoJogo.PERDEU, j.getEstado());
	}

	@Test
	public void testHeroiMataDragao(){
		criaJogoUmDragao();
		
		assertEquals(labirinto1, j.getLabirinto());
		assertEquals(new Ponto(1,3) , j.getLabirinto().getPosHeroi());
		assertFalse(j.getLabirinto().getHeroi().isArmado());
		assertEquals(new Ponto(1,1) , j.getLabirinto().getPosEspada());
		assertEquals(new Ponto(3,3) , j.getLabirinto().getDragao()[0].getPosicao());
		assertTrue(j.getLabirinto().getDragao()[0].isVivo());
		
		j.jogada(Direcao.ESQUERDA);
		j.jogada(Direcao.ESQUERDA);
		j.getLabirinto().getHeroi().armaHeroi(j.getLabirinto().getEspada(), j.getLabirinto());
		assertTrue(j.getLabirinto().getHeroi().isArmado());
		
		j.jogada(Direcao.DIREITA);
		j.jogada(Direcao.DIREITA);
		j.jogada(Direcao.BAIXO);
		assertFalse(j.getLabirinto().getDragao()[0].isVivo());
	}

	@Test
	public void testHeroiMataDragaoSai(){
		criaJogoUmDragao();
		
		assertEquals(labirinto1, j.getLabirinto());
		assertEquals(new Ponto(1,3) , j.getLabirinto().getPosHeroi());
		assertFalse(j.getLabirinto().getHeroi().isArmado());
		assertEquals(new Ponto(1,1) , j.getLabirinto().getPosEspada());
		assertEquals(new Ponto(3,3) , j.getLabirinto().getDragao()[0].getPosicao());
		assertTrue(j.getLabirinto().getDragao()[0].isVivo());
		
		j.jogada(Direcao.ESQUERDA);
		j.jogada(Direcao.ESQUERDA);
		j.getLabirinto().getHeroi().armaHeroi(j.getLabirinto().getEspada(), j.getLabirinto());
		assertTrue(j.getLabirinto().getHeroi().isArmado());
		
		j.jogada(Direcao.DIREITA);
		j.jogada(Direcao.DIREITA);
		j.jogada(Direcao.BAIXO);
		assertFalse(j.getLabirinto().getDragao()[0].isVivo());
		
		j.jogada(Direcao.CIMA);
		j.jogada(Direcao.DIREITA);
		assertEquals(EstadoJogo.GANHOU, j.getEstado());
	}

	@Test
	public void testHeroiSaiDesarmado(){
		criaJogoUmDragao();
	
		assertEquals(labirinto1, j.getLabirinto());
		assertEquals(new Ponto(1,3) , j.getLabirinto().getPosHeroi());
		assertFalse(j.getLabirinto().getHeroi().isArmado());
		assertEquals(new Ponto(1,1) , j.getLabirinto().getPosEspada());
		assertEquals(new Ponto(3,3) , j.getLabirinto().getDragao()[0].getPosicao());
		
		j.jogada(Direcao.DIREITA);
		assertEquals(EstadoJogo.SAIDA_FECHADA, j.getEstado());
	}

	@Test
	public void testHeroiSaiArmado(){
		criaJogoUmDragao();

		assertEquals(labirinto1, j.getLabirinto());
		assertEquals(new Ponto(1,3) , j.getLabirinto().getPosHeroi());
		assertFalse(j.getLabirinto().getHeroi().isArmado());
		assertEquals(new Ponto(1,1) , j.getLabirinto().getPosEspada());
		assertEquals(new Ponto(3,3) , j.getLabirinto().getDragao()[0].getPosicao());
		assertTrue(j.getLabirinto().getDragao()[0].isVivo());

		j.jogada(Direcao.ESQUERDA);
		j.jogada(Direcao.ESQUERDA);
		j.getLabirinto().getHeroi().armaHeroi(j.getLabirinto().getEspada(), j.getLabirinto());
		assertTrue(j.getLabirinto().getHeroi().isArmado());

		j.jogada(Direcao.DIREITA);
		j.jogada(Direcao.DIREITA);
		j.jogada(Direcao.DIREITA);
		assertEquals(EstadoJogo.SAIDA_FECHADA, j.getEstado());
	}
	
	@Test
	public void testSetPosicoes(){
		criaJogoUmDragao();

		assertEquals(new Ponto(1,1) , j.getLabirinto().getPosEspada());
		j.getLabirinto().setPosEspada(new Ponto(1,2));
		assertEquals(new Ponto(1,2) , j.getLabirinto().getPosEspada());
		
		assertEquals(new Ponto(1,3) , j.getLabirinto().getPosHeroi());
		j.getLabirinto().setPosHeroi(new Ponto(3,1));
		assertEquals(new Ponto(3,1) , j.getLabirinto().getPosHeroi());		
	}
	
	@Test
	public void testLabirintoRandom(){
		j = new Jogo(altura,largura, nDragoes);
		
		assertNotNull(j.getLabirinto());
		assertNotNull(j.getLabirinto().getHeroi());
		assertNotNull(j.getLabirinto().getEspada());
		for(int i = 0; i < j.getLabirinto().getDragao().length; i++){
			assertNotNull(j.getLabirinto().getDragao()[i]);
		}
	}
	
	@Test
	public void testLabirintoDoisDragoes(){
		criaJogoDoisDragoes();
		
		assertNotNull(j.getLabirinto());
		assertNotNull(j.getLabirinto().getHeroi());
		assertNotNull(j.getLabirinto().getEspada());
		for(int i = 0; i < j.getLabirinto().getDragao().length; i++){
			assertNotNull(j.getLabirinto().getDragao()[i]);
		}
	}
	
	@Test
	public void testImprime(){
		criaJogoUmDragao();
		String expected = "X X X X X \n"
				   		+ "X E   H S \n"
				   		+ "X   X   X \n"
				   		+ "X     D X \n"
				   		+ "X X X X X \n";
		assertEquals(expected, j.toString());
	}
	
	@Test
	public void testImprimeEstados(){
		criaJogoUmDragao();
		j.setEstado(EstadoJogo.GANHOU);
		assertEquals("Parabens, ganhou o jogo!",j.imprimeEstado());
		j.setEstado(EstadoJogo.PERDEU);
		assertEquals("Foi derrotado! Perdeu o jogo",j.imprimeEstado());
		j.setEstado(EstadoJogo.SAIDA_FECHADA);
		assertEquals("Antes de sair tem de derrotar o dragao",j.imprimeEstado());
		j.setEstado(EstadoJogo.INICIAL);
		assertEquals("",j.imprimeEstado());
	}
}
