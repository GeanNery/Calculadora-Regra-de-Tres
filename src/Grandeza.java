import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Grandeza extends JPanel
{
	public JTextField titulo, A1, B1;
	public boolean inverso = false;
	
	private static JanelaOpcoes janelaOpcoes;
	
	private JLabel labelIcone = new JLabel();
	private String tituloReserva;
	private boolean abrirJanela = false;

	private DocumentListener documentListener = new DocumentListener()
	{
		@Override
		public void removeUpdate(DocumentEvent event)
		{
			Janela.definirIncognita();
			
			if(event.getDocument() != titulo.getDocument() && titulo.getText().isBlank())
				inserirTitulo();
		}
		
		@Override
		public void insertUpdate(DocumentEvent event)
		{
			Janela.definirIncognita();
			
			if(event.getDocument() != titulo.getDocument() && titulo.getText().isBlank())
				inserirTitulo();
		}
		
		@Override
		public void changedUpdate(DocumentEvent event)
		{
			
		}
	};
	
	public Grandeza(int cont)
	{	
		MouseListener mouseListener = new MouseAdapter()
		{
	    	public void mouseClicked(MouseEvent event)
	    	{
	    		if(event.getButton() == MouseEvent.BUTTON3 && Janela.painelGrandezas.getComponentCount() > 3)
	    		{
	    			abrirJanela = !abrirJanela;
	    			fecharJanela();
	    			
	    			if(abrirJanela == true)
	    				janelaOpcoes = new JanelaOpcoes(Grandeza.this, getSize());
	    		}
	    	}
		};
		
		tituloReserva = "GRANDEZA  " + cont;
		setBackground(new Color(140, 204, 183));
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(130, 133));
		
		JPanel painel = new JPanel();
		painel.setLayout(new BorderLayout());
		painel.setOpaque(false);
		painel.setPreferredSize(new Dimension(123, 13));
		
		ImageIcon iconeInvertido = new ImageIcon(getClass().getResource("/icones/invertido.png"));
		labelIcone = new JLabel(iconeInvertido);
		labelIcone.setToolTipText("Grandeza inversamente proporcional");
		labelIcone.setVisible(false);
		painel.add(labelIcone, BorderLayout.WEST);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, -2, 0);
		add(painel, gbc);
		
		MatteBorder contorno = new MatteBorder(1, 1, 3, 1, Color.BLACK);
		MatteBorder brilho = new MatteBorder(2, 0, 0, 0, Color.WHITE);
		setBorder(BorderFactory.createCompoundBorder(contorno, brilho));
		Insets textFieldInsets = new Insets(6, 0, 0, 0);
		
		titulo = new JTextField(tituloReserva);
			titulo.setFont(new Font("Calibri", Font.BOLD, 17));
			titulo.setForeground(Color.WHITE);
		    titulo.setHorizontalAlignment(JTextField.CENTER);
		    titulo.setBorder(null);
		    titulo.setBackground(null);
		    titulo.addMouseListener(mouseListener);
		    gbc.gridy = 1;
		    gbc.insets = new Insets(0, 0, 3, 0);
		add(titulo, gbc);
		    
		A1 = new JTextField();
			A1.setFont(new Font("Calibri", Font.PLAIN, 18));
			A1.setMargin(textFieldInsets);
	    	A1.setPreferredSize(new Dimension(100, 35));
	    	A1.setHorizontalAlignment(JTextField.CENTER);
	    	A1.getDocument().addDocumentListener(documentListener);
	    	A1.addMouseListener(mouseListener);
	    	gbc.gridy = 2;
	    	gbc.insets = new Insets(0, 0, 8, 0);
	    add(A1, gbc);
	    	
		B1 = new JTextField();
			B1.setFont(new Font("Calibri", Font.PLAIN, 18));
			B1.setMargin(textFieldInsets);
	    	B1.setPreferredSize(new Dimension(100, 35));
	    	B1.setHorizontalAlignment(JTextField.CENTER);
	    	B1.getDocument().addDocumentListener(documentListener);
	    	B1.addMouseListener(mouseListener);
	    	gbc.gridy = 3;
	    	gbc.insets = new Insets(0, 0, 8, 0);
	    add(B1, gbc);
	    
	    addMouseListener(mouseListener);
	}
	
	public static void fecharJanela()
	{
		if(janelaOpcoes != null && janelaOpcoes.isDisplayable())
			janelaOpcoes.dispose();
	}
	
	public void inserirTitulo()
	{
		titulo.setText(tituloReserva);
	}
	
	public void inverterGrandeza()
	{
		inverso = !inverso;
		
		if(inverso == false)
		{
			setBackground(new Color(140, 204, 183));  // Cor padrão
			labelIcone.setVisible(false);
		}
		else
		{
			setBackground(new Color(237, 125, 125));
			labelIcone.setVisible(true);
		}
	}
}