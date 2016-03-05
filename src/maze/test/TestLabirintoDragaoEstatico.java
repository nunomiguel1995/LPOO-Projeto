package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;

public class TestLabirintoDragaoEstatico {
	char [][]lab = {{'X','X','X','X','X'},
			        {'X','E',' ','H','S'},
			        {'X',' ','X',' ','X'},
			        {'X',' ',' ','D','X'},
			        {'X','X','X','X','X'}};
	
	boolean dragaoParado = true, dragaoAdormece = false;
	
	@Test
	public void testMoveHeroiCelulaVazia() {
		Labirinto l = new Labirinto(lab,dragaoParado, dragaoAdormece);
		
		assertEquals(new Ponto(1,3), l.getPosHeroi());
		l.getHeroi().moveHeroi(l, "esquerda");
		assertEquals(new Ponto(1,2), l.getPosHeroi());
	}

	@Test
	public void testMoveHeroiParede(){
		Labirinto l = new Labirinto(lab,dragaoParado, dragaoAdormece);
		
		assertEquals(new Ponto(1,3), l.getPosHeroi());
		l.getHeroi().moveHeroi(l, "cima");
		assertEquals(new Ponto(1,3), l.getPosHeroi());
	}
	
	@Test
	public void testMoveHeroiEspada(){
		Labirinto l = new Labirinto(lab,dragaoParado, dragaoAdormece);
		
		assertEquals(new Ponto(1,3),l.getPosHeroi());
		l.getHeroi().moveHeroi(l, "esquerda");
		l.getHeroi().moveHeroi(l, "esquerda");
		l.getHeroi().armaHeroi(l.getEspada());
		assertEquals(true, l.getHeroi().getArmado());
	}
	
	@Test
	public void testMoveHeroiDragao(){
		Labirinto l = new Labirinto(lab,dragaoParado, dragaoAdormece);
		
		assertEquals(new Ponto(1,3),l.getPosHeroi());
		l.getHeroi().moveHeroi(l, "baixo");
		assertEquals(-1,l.getHeroi().enfrentaDragao(l.getDragao()));
	}
	
	@Test
	public void testHeroiMataDragao(){
		Labirinto l = new Labirinto(lab,dragaoParado, dragaoAdormece);
		
		assertEquals(new Ponto(1,3),l.getPosHeroi());
		l.getHeroi().moveHeroi(l, "esquerda");
		l.getHeroi().moveHeroi(l, "esquerda");
		l.getHeroi().moveHeroi(l, "baixo");
		l.getHeroi().moveHeroi(l, "baixo");
		l.getHeroi().armaHeroi(l.getEspada());
		l.getHeroi().moveHeroi(l, "direita");
		l.getHeroi().moveHeroi(l, "direita");
		assertEquals(0,l.getHeroi().enfrentaDragao(l.getDragao()));
	}
	
	@Test
	public void testHeroiMataDragaoSai(){
		Labirinto l = new Labirinto(lab,dragaoParado, dragaoAdormece);

		assertEquals(new Ponto(1,3),l.getPosHeroi());
		l.getHeroi().moveHeroi(l, "esquerda");
		l.getHeroi().moveHeroi(l, "esquerda");
		l.getHeroi().armaHeroi(l.getEspada());
		l.getHeroi().moveHeroi(l, "direita");
		l.getHeroi().moveHeroi(l, "direita");
		l.getHeroi().moveHeroi(l, "baixo");
		assertEquals(1, l.getHeroi().enfrentaDragao(l.getDragao()));
		l.getHeroi().moveHeroi(l, "cima");
		assertEquals(1, l.getHeroi().moveHeroi(l, "direita"));
	}
	
	@Test
	public void testHeroiSaiDesarmado(){
		Labirinto l = new Labirinto(lab,dragaoParado, dragaoAdormece);
		
		assertEquals(new Ponto(1,3),l.getPosHeroi());
		assertEquals(1, l.getHeroi().moveHeroi(l, "direita"));
	}
	
	@Test
	public void testHeroiSaiArmado(){
		Labirinto l = new Labirinto(lab,dragaoParado, dragaoAdormece);
		
		assertEquals(new Ponto(1,3),l.getPosHeroi());
		l.getHeroi().moveHeroi(l, "esquerda");
		l.getHeroi().moveHeroi(l, "esquerda");
		l.getHeroi().armaHeroi(l.getEspada());
		l.getHeroi().moveHeroi(l, "direita");
		l.getHeroi().moveHeroi(l, "direita");
		assertEquals(1, l.getHeroi().moveHeroi(l, "direita"));
	}

}
