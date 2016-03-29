package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

import maze.logic.Jogo;
import maze.logic.Jogo.Direcao;
import maze.logic.Jogo.EstadoJogo;

import java.awt.Font;
import java.awt.TextArea;

public class GraphicMaze {

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
		frame.setBounds(100, 100, 696, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Dimens�o = new JLabel("Dimens\u00E3o");
		Dimens�o.setBounds(41, 35, 123, 14);
		frame.getContentPane().add(Dimens�o);
		
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
		
		String[] tipoDragoes= {"Est�ticos", "Movimenta��o aleat�ria", "Movimenta��o e adormecer"};
		JComboBox cbTipoDragoes = new JComboBox(tipoDragoes);
		cbTipoDragoes.setBounds(193, 105, 257, 20);
		frame.getContentPane().add(cbTipoDragoes);
		
		JLabel GameState = new JLabel("Pode gerar um novo Labirinto!");
		GameState.setBounds(41, 364, 279, 14);
		frame.getContentPane().add(GameState);
		
		JTextPane textArea = new JTextPane();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 11));
		textArea.setEditable(false);
		textArea.setBounds(41, 136, 347, 217);
		frame.getContentPane().add(textArea);
		
		JButton btnCima = new JButton("Cima");
		JButton btnEsquerda = new JButton("Esquerda");
		JButton btnDireita = new JButton("Direita");
		JButton btnBaixo = new JButton("Baixo");
		
		
		btnCima.setBounds(510, 181, 79, 33);
		frame.getContentPane().add(btnCima);
		btnCima.setEnabled(false);
		btnCima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.jogada(Direcao.CIMA);
				textArea.setText(j.toString());
				
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
		
		
		btnEsquerda.setBounds(440, 225, 93, 33);
		frame.getContentPane().add(btnEsquerda);
		btnEsquerda.setEnabled(false);
		btnEsquerda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.jogada(Direcao.ESQUERDA);
				textArea.setText(j.toString());
				
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
		
		btnDireita.setBounds(566, 225, 93, 33);
		frame.getContentPane().add(btnDireita);
		btnDireita.setEnabled(false);
		btnDireita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.jogada(Direcao.DIREITA);
				textArea.setText(j.toString());
				
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
		
		btnBaixo.setBounds(510, 269, 79, 33);
		frame.getContentPane().add(btnBaixo);
		btnBaixo.setEnabled(false);
		btnBaixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.jogada(Direcao.BAIXO);
				textArea.setText(j.toString());
				
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
				
				if(altura<0 || largura < 0 || nDragoes < 0)
					throw new IllegalArgumentException();
				}
				catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(frame, "Formato nao valido");
					return;
				}
				catch(IllegalArgumentException ex){
					JOptionPane.showMessageDialog(frame, "N�meros n�o podem ser negativos");
					return;
				}
				j = new Jogo(altura,largura,nDragoes);
				switch(opcao){
				case "Est�ticos": 
					j.setComportamentoDragao(1);
					break;
				case "Movimenta��o aleat�ria":
					j.setComportamentoDragao(2);
					break;
				case "Movimenta��o e adormecer":
					j.setComportamentoDragao(3);
					break;
				}
				
				textArea.setText(j.toString());
				GameState.setText("Pode jogar!");
				
				btnCima.setEnabled(true);
				btnBaixo.setEnabled(true);
				btnDireita.setEnabled(true);
				btnEsquerda.setEnabled(true);
			}
		});
		btnGerarLabirinto.setBounds(486, 34, 162, 33);
		frame.getContentPane().add(btnGerarLabirinto);
		
		JButton btnTerminarPrograma = new JButton("Terminar Programa");
		btnTerminarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnTerminarPrograma.setBounds(486, 78, 162, 44);
		frame.getContentPane().add(btnTerminarPrograma);
		
		fldAltura = new JTextField();
		fldAltura.setText("11");
		fldAltura.setBounds(240, 32, 32, 20);
		frame.getContentPane().add(fldAltura);
		fldAltura.setColumns(10);	
	}
}
