/*package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;

public class TestLabirintoDragaoDinamico {
	char [][]lab = {{'X','X','X','X','X'},
					{'X','E',' ','H','S'},
					{'X',' ','X',' ','X'},
					{'X',' ',' ','D','X'},
					{'X','X','X','X','X'}};
	
	@Test
	public void testDragaoDorme(){
		Jogo j = new Jogo(lab);
		j.lePosicoes();
		j.getDragao().adormeceOuAcorda();
		if(j.getDragao().getSimbolo() == 'D'){
			assertFalse(j.getDragao().getAdormecido());
		}else{
			assertTrue(j.getDragao().getAdormecido());
		}
	}
	
	@Test
	public void testDragaoCimaEspada(){
		Jogo j = new Jogo(lab);
		j.lePosicoes();
		j.getDragao().setPosicao(j.getEspada().getPosicao());
		j.getDragao().dragaoCimaEspada(j.getEspada());
		assertTrue(j.getDragao().getCimaEspada());
		if(j.getDragao().getSimbolo() == 'F'){
			assertTrue(j.getDragao().getCimaEspada());
		}
	}
}*/
