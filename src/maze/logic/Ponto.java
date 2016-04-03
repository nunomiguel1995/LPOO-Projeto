package maze.logic;

public class Ponto{
	private int x, y;
	
	/**
	 * Constrói um ponto com abcissa x e ordenada y
	 * @param x abcissa x
	 * @param y ordenada y
	 */
	public Ponto(int x, int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * Devolve a abcissa do ponto.
	 * @return abcissa do ponto
	 */
	public int getX() {
		return x;
	}

	/**
	 * Altera a abcissa do ponto.
	 * @param x nova abcissa do ponto
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Devolve a ordenada do ponto
	 * @return ordenada do ponto
	 */
	public int getY() {
		return y;
	}

	/**
	 * Altera a ordenada do ponto.
	 * @param y nova ordenada do ponto
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Verifica a igualdade entre dois pontos.
	 * @return true se iguais e false se diferentes
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ponto other = (Ponto) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}
