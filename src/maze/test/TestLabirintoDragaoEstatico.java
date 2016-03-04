package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;

public class TestLabirintoDragaoEstatico {
	char [][]lab = {{'X','X','X','X','X'},
			        {'X',' ',' ','H','S'},
			        {'X',' ','X',' ','X'},
			        {'X','E',' ','D','X'},
			        {'X','X','X','X','X'}};
	
	@Test
	public void testMoveHeroiCelulaVazia() {
		Labirinto l = new Labirinto();
		l.setLayout(lab);
		Heroi h = new Heroi(1,3,'H',false);
		l.setHeroi(h);
		
		assertEquals(new Ponto(1,3),l.getPosHeroi());
		h.moveHeroi(l,"esquerda");
		assertEquals(new Ponto(1,2),l.getPosHeroi());
	}

	@Test
	public void testMoveHeroiParede(){
		Labirinto l = new Labirinto();
		l.setLayout(lab);
		Heroi h = new Heroi(1,3,'H',false);
		l.setHeroi(h);
		
		assertEquals(new Ponto(1,3),l.getPosHeroi());
		h.moveHeroi(l, "cima");
		assertEquals(new Ponto(1,3),l.getPosHeroi());
	}
	
	@Test
	public void testMoveHeroiEspada(){
		Labirinto l = new Labirinto();
		l.setLayout(lab);
		Heroi h = new Heroi(1,3,'H',false);
		l.setHeroi(h);
		Espada e = new Espada(3,1,'E');
		l.setEspada(e);
		
		assertEquals(new Ponto(1,3),l.getPosHeroi());
		h.moveHeroi(l, "esquerda");
		h.moveHeroi(l, "esquerda");
		h.moveHeroi(l, "baixo");
		h.moveHeroi(l, "baixo");
		h.armaHeroi(e);
		assertEquals(true, h.getArmado());
	}
	
	@Test
	public void testMoveHeroiDragao(){
		Labirinto l = new Labirinto();
		l.setLayout(lab);
		Heroi h = new Heroi(1,3,'H',false);
		l.setHeroi(h);
		Dragao d = new Dragao(3,3,'D',false,false);
		l.setDragao(d);
		
		assertEquals(new Ponto(1,3),l.getPosHeroi());
		h.moveHeroi(l, "baixo");
		assertEquals(-1,h.enfrentaDragao(d));
	}
	
	@Test
	public void testHeroiMataDragao(){
		Labirinto l = new Labirinto();
		l.setLayout(lab);
		Heroi h = new Heroi(1,3,'H',false);
		l.setHeroi(h);
		Dragao d = new Dragao(3,3,'D',false,false);
		l.setDragao(d);
		Espada e = new Espada(3,3,'E');
		l.setEspada(e);
		
		assertEquals(new Ponto(1,3),l.getPosHeroi());
		h.moveHeroi(l, "esquerda");
		h.moveHeroi(l, "esquerda");
		h.moveHeroi(l, "baixo");
		h.moveHeroi(l, "baixo");
		h.armaHeroi(e);
		h.moveHeroi(l, "direita");
		h.moveHeroi(l, "direita");
		assertEquals(0,h.enfrentaDragao(d));
	}
	
	@Test
	public void testHeroiMataDragaoSai(){
		Labirinto l = new Labirinto();
		l.setLayout(lab);
		Heroi h = new Heroi(1,3,'H',false);
		l.setHeroi(h);
		Dragao d = new Dragao(3,3,'D',false,false);
		l.setDragao(d);
		Espada e = new Espada(3,3,'E');
		l.setEspada(e);
		
		assertEquals(new Ponto(1,3),l.getPosHeroi());
		h.moveHeroi(l, "esquerda");
		h.moveHeroi(l, "esquerda");
		h.moveHeroi(l, "baixo");
		h.moveHeroi(l, "baixo");
		h.armaHeroi(e);
		h.moveHeroi(l, "direita");
		h.moveHeroi(l, "direita");
		assertEquals(0,h.enfrentaDragao(d));
		h.moveHeroi(l, "direita");
		h.moveHeroi(l, "cima");
		h.moveHeroi(l, "cima");
		h.moveHeroi(l, "direita");
		assertEquals(1, h.moveHeroi(l, "direita"));
	}
	
	@Test
	public void testHeroiSaiDesarmado(){
		Labirinto l = new Labirinto();
		l.setLayout(lab);
		Heroi h = new Heroi(1,3,'H',false);
		l.setHeroi(h);
		Espada e = new Espada(3,3,'E');
		l.setEspada(e);
		
		assertEquals(new Ponto(1,3),l.getPosHeroi());
		assertEquals(1, h.moveHeroi(l, "direita"));
	}
	
	@Test
	public void testHeroiSaiArmado(){
		Labirinto l = new Labirinto();
		l.setLayout(lab);
		Heroi h = new Heroi(1,3,'H',false);
		l.setHeroi(h);
		Espada e = new Espada(3,3,'E');
		l.setEspada(e);
		
		assertEquals(new Ponto(1,3),l.getPosHeroi());
		h.moveHeroi(l, "esquerda");
		h.moveHeroi(l, "esquerda");
		h.moveHeroi(l, "baixo");
		h.moveHeroi(l, "baixo");
		h.armaHeroi(e);
		h.moveHeroi(l, "cima");
		h.moveHeroi(l, "cima");
		h.moveHeroi(l, "direita");
		h.moveHeroi(l, "direita");
		assertEquals(1, h.moveHeroi(l, "direita"));
	}

}
