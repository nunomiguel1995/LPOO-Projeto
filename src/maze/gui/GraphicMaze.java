package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import maze.logic.Jogo;
import maze.logic.Jogo.Direcao;
import maze.logic.Jogo.EstadoJogo;


public class GraphicMaze{

	private GraphicPanel panel;
	private JFrame frame;
	private JTextField fldLargura;
	private JTextField fldNumeroDragoes;
	private JTextField fldAltura;
	private Jogo j;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicMaze window = new GraphicMaze();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GraphicMaze() {		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		frame = new JFrame();
		frame.setBounds(100, 100, 931, 732);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Dimensão = new JLabel("Dimens\u00E3o");
		Dimensão.setBounds(41, 35, 123, 14);
		frame.getContentPane().add(Dimensão);
		
		JLabel NumeroDragoes = new JLabel("N\u00FAmero de Drag\u00F5es");
		NumeroDragoes.setBounds(41, 71, 123, 14);
		frame.getContentPane().add(NumeroDragoes);
		
		fldLargura = new JTextField();
		fldLargura.setText("11");
		fldLargura.setBounds(193, 32, 33, 20);
		frame.getContentPane().add(fldLargura);
		fldLargura.setColumns(10);
		
		fldNumeroDragoes = new JTextField();
		fldNumeroDragoes.setText("1");
		fldNumeroDragoes.setBounds(193, 68, 63, 20);
		frame.getContentPane().add(fldNumeroDragoes);
		fldNumeroDragoes.setColumns(10);
		
		JLabel TipoDragoes = new JLabel("Tipo de Drag\u00F5es");
		TipoDragoes.setBounds(41, 108, 123, 14);
		frame.getContentPane().add(TipoDragoes);
		
		String[] tipoDragoes= {"Estáticos", "Movimentação aleatória", "Movimentação e adormecer"};
		final JComboBox cbTipoDragoes = new JComboBox(tipoDragoes);
		cbTipoDragoes.setBounds(193, 105, 257, 20);
		frame.getContentPane().add(cbTipoDragoes);
		
		final JLabel GameState = new JLabel("Pode gerar um novo Labirinto!");
		GameState.setBounds(41, 668, 279, 14);
		frame.getContentPane().add(GameState);
		
		final JButton btnCima = new JButton("Cima");
		final JButton btnEsquerda = new JButton("Esquerda");
		final JButton btnDireita = new JButton("Direita");
		final JButton btnBaixo = new JButton("Baixo");
		
		
		btnCima.setBounds(739, 181, 79, 33);
		frame.getContentPane().add(btnCima);
		btnCima.setEnabled(false);
		btnCima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.jogada(Direcao.ESQUERDA);
				panel.setJogo(j);
				panel.repaint();
				
				if(j.imprimeEstado()== "")
					GameState.setText("Pode jogar!");
				else
					GameState.setText(j.imprimeEstado());	
				
				if(j.getEstado() == EstadoJogo.PERDEU || j.getEstado() == EstadoJogo.GANHOU){
					btnCima.setEnabled(false);
					btnBaixo.setEnabled(false);
					btnEsquerda.setEnabled(false);
					btnDireita.setEnabled(false);
				}
			}
		});
		
		
		btnEsquerda.setBounds(681, 225, 93, 33);
		frame.getContentPane().add(btnEsquerda);
		btnEsquerda.setEnabled(false);
		btnEsquerda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.jogada(Direcao.CIMA);
				panel.setJogo(j);
				panel.repaint();
				
				if(j.imprimeEstado()== "")
					GameState.setText("Pode jogar!");
				else
					GameState.setText(j.imprimeEstado());
				
				if(j.getEstado() == EstadoJogo.PERDEU || j.getEstado() == EstadoJogo.GANHOU){
					btnCima.setEnabled(false);
					btnBaixo.setEnabled(false);
					btnEsquerda.setEnabled(false);
					btnDireita.setEnabled(false);
				}
			}
		});
		
		btnDireita.setBounds(795, 225, 93, 33);
		frame.getContentPane().add(btnDireita);
		btnDireita.setEnabled(false);
		btnDireita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.jogada(Direcao.BAIXO);
				panel.setJogo(j);
				panel.repaint();
				
				if(j.imprimeEstado()== "")
					GameState.setText("Pode jogar!");
				else
					GameState.setText(j.imprimeEstado());
				
				if(j.getEstado() == EstadoJogo.PERDEU || j.getEstado() == EstadoJogo.GANHOU){
					btnCima.setEnabled(false);
					btnBaixo.setEnabled(false);
					btnEsquerda.setEnabled(false);
					btnDireita.setEnabled(false);
				}
			}
		});
		
		btnBaixo.setBounds(739, 267, 79, 33);
		frame.getContentPane().add(btnBaixo);
		btnBaixo.setEnabled(false);
		btnBaixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.jogada(Direcao.DIREITA);
				panel.setJogo(j);
				panel.repaint();
				
				if(j.imprimeEstado()== "")
					GameState.setText("Pode jogar!");
				else
					GameState.setText(j.imprimeEstado());
				
				if(j.getEstado() == EstadoJogo.PERDEU || j.getEstado() == EstadoJogo.GANHOU){
					btnCima.setEnabled(false);
					btnBaixo.setEnabled(false);
					btnEsquerda.setEnabled(false);
					btnDireita.setEnabled(false);
				}
			}
		});
		
		JButton btnGerarLabirinto = new JButton("Gerar novo Labirinto");
		btnGerarLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int altura,largura, nDragoes;
				String opcao;
				
				try{
				altura= Integer.parseInt(fldAltura.getText());
				largura= Integer.parseInt(fldLargura.getText());
				nDragoes= Integer.parseInt(fldNumeroDragoes.getText());
				opcao= (String) cbTipoDragoes.getSelectedItem();
				
				if(altura<0 || largura < 0 || nDragoes <= 0 || altura > 21 || largura > 25)
					throw new IllegalArgumentException();
				}
				catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(frame, "Formato nao valido");
					return;
				}
				catch(IllegalArgumentException ex){
					JOptionPane.showMessageDialog(frame, "Números inválidos");
					return;
				}
				j = new Jogo(altura,largura,nDragoes);
				
				switch(opcao){
				case "Estáticos": 
					j.setComportamentoDragao(1);
					break;
				case "Movimentação aleatória":
					j.setComportamentoDragao(2);
					break;
				case "Movimentação e adormecer":
					j.setComportamentoDragao(3);
					break;
				}
				
				panel = new GraphicPanel();
				panel.setBounds(37, 133, 634, 525);
				frame.getContentPane().add(panel);
				panel.setJogo(j);
				panel.repaint();
				
				GameState.setText("Pode jogar!");
				
				btnCima.setEnabled(true);
				btnBaixo.setEnabled(true);
				btnDireita.setEnabled(true);
				btnEsquerda.setEnabled(true);
			}
		});
		btnGerarLabirinto.setBounds(698, 26, 162, 33);
		frame.getContentPane().add(btnGerarLabirinto);
		
		JButton btnTerminarPrograma = new JButton("Terminar Programa");
		btnTerminarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnTerminarPrograma.setBounds(698, 78, 162, 44);
		frame.getContentPane().add(btnTerminarPrograma);
		
		fldAltura = new JTextField();
		fldAltura.setText("11");
		fldAltura.setBounds(240, 32, 32, 20);
		frame.getContentPane().add(fldAltura);
		fldAltura.setColumns(10);	
	}
}