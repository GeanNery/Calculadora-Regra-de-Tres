import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class Janela extends JFrame
{
	public static JPanel painelGrandezas, painelBotoes;
	
	private static String caractere = "x";
	private static int posicao, posicaoAux;
	private static boolean estaCalculando;
	
	private Container contentPane = getContentPane();
	private JScrollPane scrollPane;
	private JButton botaoAdicionar, botaoClear, botaoCalcular;
	private int cont;
	
	public Janela()
	{	
		EmptyBorder bordaVazia = new EmptyBorder(8, 0, 0, 0);
		MatteBorder bordaPreta = new MatteBorder(1, 1, 3, 1, Color.BLACK);
		MatteBorder bordaBranca = new MatteBorder(2, 0, 0, 0, Color.WHITE);
		CompoundBorder compoundBorder = new CompoundBorder(bordaPreta, bordaBranca);
		
		Color corBackground = new Color(250, 250, 250);
		Color corBotao = new Color(147, 219, 195);
			
		// JFrame:
			contentPane.setBackground(corBackground);	
			setTitle("Regra de Três Simples e Composta");
			setSize(600, 500);
			setMinimumSize(new Dimension(400, 400));
			setLocationRelativeTo(null);
			setLayout(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// JPanels:
			painelGrandezas = new JPanel();
			painelGrandezas.setBackground(corBackground);
			painelGrandezas.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
			
			painelBotoes = new JPanel();
			painelBotoes.setSize(155, 135);
			painelBotoes.setOpaque(false);
			painelBotoes.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
		
		// JScrollPane:
			scrollPane = new JScrollPane(painelGrandezas);
			scrollPane.setBorder(null);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
			JScrollBar barraHorizontal = scrollPane.getHorizontalScrollBar();
			barraHorizontal.setPreferredSize(new Dimension(10, 10));
			barraHorizontal.setUnitIncrement(30);
			barraHorizontal.setBlockIncrement(175);
		
		// Botões:
			botaoAdicionar = new JButton("+");
			botaoAdicionar.setBackground(corBotao);
			botaoAdicionar.setBorder(BorderFactory.createCompoundBorder(compoundBorder, bordaVazia));
			botaoAdicionar.setFocusPainted(false);
			botaoAdicionar.setFont(new Font("Calibri", Font.BOLD, 24));
			botaoAdicionar.setForeground(Color.white);
			botaoAdicionar.setMargin(new Insets(8, 0, 0, 0));
			botaoAdicionar.setPreferredSize(new Dimension(60, 60));
			
			botaoClear = new JButton("C");
			botaoClear.setBackground(corBotao);
			botaoClear.setBorder(BorderFactory.createCompoundBorder(compoundBorder, bordaVazia));
			botaoClear.setFocusPainted(false);
			botaoClear.setFont(new Font("Calibri", Font.BOLD, 24));
			botaoClear.setForeground(Color.white);
			botaoClear.setMargin(new Insets(8, 0, 0, 0));
			botaoClear.setPreferredSize(new Dimension(60, 60));
			
			botaoCalcular = new JButton("=");
			botaoCalcular.setBackground(corBotao);
			botaoCalcular.setBorder(BorderFactory.createCompoundBorder(compoundBorder, bordaVazia));
			botaoCalcular.setFocusPainted(false);
			botaoCalcular.setFont(new Font("Calibri", Font.BOLD, 24));
			botaoCalcular.setForeground(Color.white);
			botaoCalcular.setPreferredSize(new Dimension(120, 60));
		
		criarGrandeza(2);
		
		barraHorizontal.addAdjustmentListener(new AdjustmentListener()
		{
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e)
			{
				Grandeza.fecharJanela();
			}
		});
		
		botaoAdicionar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				Grandeza.fecharJanela();
				criarGrandeza(1);
				
				//	Aguarda a atualização do layout e posiciona a barra de rolagem no final
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run() 
					{
						barraHorizontal.setValue(barraHorizontal.getMaximum() - barraHorizontal.getVisibleAmount()); 
					}
				});
			}
		});
		
		botaoClear.addActionListener(new ActionListener()
		{		
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Grandeza.fecharJanela();	
				painelGrandezas.removeAll();
				criarGrandeza(2);
			}
		});
		
		botaoCalcular.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{	
				JTextField incognita = null, 
						   outro = null;
				BigDecimal valorA = new BigDecimal("1"), 
						   valorB = new BigDecimal("1"), 
						   resultado;
				boolean inverterCalculo = false;
				
				estaCalculando = true;	
				for(Component component : painelGrandezas.getComponents())
				{
					if(component instanceof Grandeza)
					{
						Grandeza g = (Grandeza)component;
						
						if(g.A1.getText().equals(caractere) || g.A1.isEditable() == false)
						{
							incognita = g.A1;
							outro = g.B1;
							inverterCalculo = false;
						}
						else if(g.B1.getText().equals(caractere) || g.B1.isEditable() == false)
						{
							incognita = g.B1;
							outro = g.A1;
							inverterCalculo = true;
						}
						else
						{
							//	Multiplica os numeradores e denominadores entre si
							if(g.inverso == false)
							{
								valorA = valorA.multiply(new BigDecimal(g.A1.getText()));
								valorB = valorB.multiply(new BigDecimal(g.B1.getText()));
							}
							else
							{
								valorA = valorA.multiply(new BigDecimal(g.B1.getText()));
								valorB = valorB.multiply(new BigDecimal(g.A1.getText()));
							}
						}
					}
				}
				
				//	Garante que o cálculo será cruzado
				if(inverterCalculo == true)
					resultado = valorB.multiply(new BigDecimal(outro.getText())).divide(valorA, 8, RoundingMode.HALF_UP);  // Valor B multiplicado por Outro, dividido por valor A.
				else
					resultado = valorA.multiply(new BigDecimal(outro.getText())).divide(valorB, 8, RoundingMode.HALF_UP);  // Valor A multiplicado por Outro, dividido por valor B.
				
				Grandeza.fecharJanela();
				alterarTexto(incognita, false, String.valueOf(resultado.stripTrailingZeros().toPlainString()));
				estaCalculando = false;
			}
		});
		
		//	Método chamado quando a janela é alterada manualmente
		addComponentListener(new ComponentAdapter()
		{
			@Override 
			public void componentResized(ComponentEvent event)
			{
				Grandeza.fecharJanela();
				posicionarComponentes();
				reiniciarTitulos();
			}
				
			@Override
			public void componentMoved(ComponentEvent event)
			{
				Grandeza.fecharJanela();
				reiniciarTitulos();
			}
		});
		
		//	Método chamado quando o estado da janela é alterado
		addWindowStateListener(new WindowAdapter()
		{
			@Override
			public void windowStateChanged(WindowEvent event)
			{
				Grandeza.fecharJanela();
				posicionarComponentes();
				reiniciarTitulos();
			}
		});
		
		gbc.insets = new Insets(3, 8, 3, 8);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		painelBotoes.add(botaoClear, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		painelBotoes.add(botaoAdicionar, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		painelBotoes.add(botaoCalcular, gbc);
		
		add(scrollPane);
		add(painelBotoes);
		setVisible(true);
		
		selecionarTexto();
	}
	
	private void posicionarComponentes()
	{	
		scrollPane.setSize(contentPane.getWidth(), 150);
		scrollPane.setLocation(contentPane.getWidth()/2 - scrollPane.getWidth()/2, contentPane.getHeight()/2 - (scrollPane.getHeight()/2 + 50));
		scrollPane.revalidate();
		scrollPane.repaint();
		painelBotoes.setLocation(getWidth()/2 - (painelBotoes.getWidth()/2 + 9), scrollPane.getY() + (scrollPane.getHeight() + 6));
	}
	
	private void criarGrandeza(int quant)
	{
		JLabel igual = new JLabel("=");
		igual.setFont(new Font("Calibri", Font.BOLD, 42));
		igual.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		
		for(int i = 0; i < quant; i++)
		{
			if(painelGrandezas.getComponentCount() != 0)
				painelGrandezas.add(igual);
			else
				cont = 1;
			
			Grandeza g = new Grandeza(cont);
			painelGrandezas.add(g);
			cont++;
		}
		
		scrollPane.revalidate();
		scrollPane.repaint();
		definirIncognita();
	}
	
	private void selecionarTexto()
	{
		//  Seleciona o primeiro JTextField ao iniciar o programa
		for(Component component : painelGrandezas.getComponents())
		{
			if(component instanceof Grandeza)
			{
				((Grandeza)component).A1.requestFocusInWindow();
				break;
			}
		}
	}
	
	private void reiniciarTitulos()
	{
		for(Component component : painelGrandezas.getComponents())
		{
			if(component instanceof Grandeza)
			{
				Grandeza g = (Grandeza)component;
				
				if(g.titulo.getText().isBlank())
					g.inserirTitulo();
			}
		}
	}
	
	private static void alterarTexto(JTextField textField, Boolean bool, String texto)
	{
		if(bool == true)
		{
			textField.setFont(new Font("High Tower Text", Font.PLAIN + Font.ITALIC, 32));
			textField.setMargin(new Insets(4, 0, 0, 0));
			textField.setText(texto);
		}
		else
		{
			textField.setFont(new Font("Calibri", Font.PLAIN, 18));
			textField.setMargin(new Insets(6, 0, 0, 0));
			textField.setText(texto);
		}
	}
	
	public static void definirIncognita()
	{
		if(estaCalculando == true)
			return;
		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				ArrayList<JTextField> textFields = new ArrayList<JTextField>();
				Grandeza g = null;
				int camposPreenchidos = 0;
				
				//	Adiciona todos os JTextFields em uma lista:
				for(Component component : painelGrandezas.getComponents())
				{	
					if(component instanceof Grandeza)
					{	
						g = (Grandeza)component;
						textFields.add(g.A1);
						textFields.add(g.B1);
					}
				}
				
				// Verifica a quantidade de campos preenchidos:
				for(JTextField textfield : textFields)
				{	
					if(textfield.getText().isBlank() == false && textfield.getText().equals(caractere) == false && textfield.isEditable() == true)
						camposPreenchidos++;
				}
				
				//	Preenchimento automático:
				if(camposPreenchidos == textFields.size()-1)  // Se o penúltimo campo for preenchido, o último se torna a incógnita.
				{
					for(JTextField textfield : textFields)
					{
						if(textfield.getText().isBlank() == true || (textfield.isEditable() == false && textfield.getText().equals(caractere) == false))
						{
							alterarTexto(textfield, true, caractere);
							textfield.setEditable(false);
							posicao = textFields.indexOf(textfield);
							
							if(textfield == g.A1)
								posicaoAux = 2;
							else if(textfield == g.B1)
								posicaoAux = 1;
							
							return;
						}
					}
				}
				else if(camposPreenchidos <= textFields.size()-2)  // Se o usuário apagar o penúltimo campo, a incógnita é apagada.
				{
					for(JTextField textfield : textFields)
					{
						if(textfield.getText().equals(caractere) || textfield.isEditable() == false)
						{
							alterarTexto(textfield, false, "");
							textfield.setEditable(true);
							return;
						}
					}
				}
				else if(camposPreenchidos == textFields.size())  // Se a grandeza contendo a incógnita for deletada, o "X" vai para a próxima disponível.
				{
					while(posicao >= textFields.size())
						posicao -= posicaoAux;
					
					alterarTexto(textFields.get(posicao), true, caractere);
					textFields.get(posicao).setEditable(false);
				}
			}
		});
	}
	
	public static void main(String[] args)
	{
		new Janela();
	}
}