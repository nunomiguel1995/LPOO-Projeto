/*package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;

public class TestLabirintoDragaoEstatico {
	private char [][]lab = {{'X','X','X','X','X'},
			{'X','E',' ','H','S'},
			{'X',' ','X',' ','X'},
			{'X',' ',' ','D','X'},
			{'X','X','X','X','X'}};

	private static final int CIMA = 1;
	private static final int BAIXO = 2;
	private static final int ESQUERDA = 3;
	private static final int DIREITA = 4;

	@Test
	public void testMoveHeroiCelulaVazia() {
		Jogo j = new Jogo(lab);
		j.lePosicoes();
		j.getHeroi().moveHeroi(j.getLabirinto(), ESQUERDA);
		assertEquals(new Ponto(1,2), j.getHeroi().getPosicao());
	}

	@Test
	public void testMoveHeroiParede(){
		Jogo j = new Jogo(lab);
		j.lePosicoes();
		j.getHeroi().moveHeroi(j.getLabirinto(), CIMA);
		assertEquals(new Ponto(1,3), j.getHeroi().getPosicao());
	}

	@Test
	public void testMoveHeroiEspada(){
		Jogo j = new Jogo(lab);
		j.lePosicoes();
		j.getHeroi().moveHeroi(j.getLabirinto(), ESQUERDA);
		j.getHeroi().moveHeroi(j.getLabirinto(), ESQUERDA);
		j.getHeroi().armaHeroi(j.getEspada());
		assertTrue(j.getHeroi().isArmado());
	}

	@Test
	public void testMoveHeroiDragao(){
		Jogo j = new Jogo(lab);
		j.lePosicoes();
		j.getHeroi().moveHeroi(j.getLabirinto(), BAIXO);
		assertEquals(-1,j.getHeroi().enfrentaDragao(j.getDragao()));
	}

	@Test
	public void testHeroiMataDragao(){
		Jogo j = new Jogo(lab);
		j.lePosicoes();
		j.getHeroi().moveHeroi(j.getLabirinto(), ESQUERDA);
		j.getHeroi().moveHeroi(j.getLabirinto(), ESQUERDA);
		j.getHeroi().armaHeroi(j.getEspada());
		j.getHeroi().moveHeroi(j.getLabirinto(), DIREITA);
		j.getHeroi().moveHeroi(j.getLabirinto(), DIREITA);
		j.getHeroi().moveHeroi(j.getLabirinto(), BAIXO);
		assertEquals(1,j.getHeroi().enfrentaDragao(j.getDragao()));
	}

	@Test
	public void testHeroiMataDragaoSai(){
		Jogo j = new Jogo(lab);
		j.lePosicoes();
		j.getHeroi().moveHeroi(j.getLabirinto(), ESQUERDA);
		j.getHeroi().moveHeroi(j.getLabirinto(), ESQUERDA);
		j.getHeroi().armaHeroi(j.getEspada());
		j.getHeroi().moveHeroi(j.getLabirinto(), DIREITA);
		j.getHeroi().moveHeroi(j.getLabirinto(), DIREITA);
		j.getHeroi().moveHeroi(j.getLabirinto(), BAIXO);
		assertEquals(1, j.getHeroi().enfrentaDragao(j.getDragao()));
		j.getHeroi().moveHeroi(j.getLabirinto(), CIMA);
		j.getHeroi().moveHeroi(j.getLabirinto(), DIREITA);
		assertEquals(1, j.getHeroi().moveHeroi(j.getLabirinto(), DIREITA));
	}

	@Test
	public void testHeroiSaiDesarmado(){
		Jogo j = new Jogo(lab);
		j.lePosicoes();
		j.getHeroi().moveHeroi(j.getLabirinto(), DIREITA);
		assertFalse(j.getHeroi().isArmado());
	}

	@Test
	public void testHeroiSaiArmado(){
		Jogo j = new Jogo(lab);
		j.lePosicoes();
		j.getHeroi().moveHeroi(j.getLabirinto(), ESQUERDA);
		j.getHeroi().moveHeroi(j.getLabirinto(), ESQUERDA);
		j.getHeroi().armaHeroi(j.getEspada());
		assertTrue(j.getHeroi().isArmado());
		j.getHeroi().moveHeroi(j.getLabirinto(), DIREITA);
		j.getHeroi().moveHeroi(j.getLabirinto(), DIREITA);
		j.getHeroi().moveHeroi(j.getLabirinto(), DIREITA);
		assertEquals(1, j.getHeroi().moveHeroi(j.getLabirinto(), DIREITA));
	}
}*/
