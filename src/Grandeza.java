import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Grandeza extends JPanel
{
	public static JanelaOpcoes janelaOpcoes;
	public JTextField titulo, A1, B1;
	public String tituloReserva;

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
		tituloReserva = "GRANDEZA  " + cont;
		
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(104, 108));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		MouseListener mouseListener = new MouseAdapter()
		{
	    	public void mouseClicked(MouseEvent event)
	    	{
	    		if(event.getButton() == MouseEvent.BUTTON3)
	    		{
	    			fecharJanela();
	    			janelaOpcoes = new JanelaOpcoes(Grandeza.this, getSize());
	    		}
	    	}
		};
		
		titulo = new JTextField(tituloReserva);
		    titulo.setHorizontalAlignment(JTextField.CENTER);
		    titulo.setBorder(null);
		    titulo.setBackground(null);
		    titulo.addMouseListener(mouseListener);
		    gbc.gridy = 0;
		    gbc.insets = new Insets(0, 0, 3, 0);
		add(titulo, gbc);
		    
		A1 = new JTextField(8);
	    	A1.setPreferredSize(new Dimension(0, 35));
	    	A1.setHorizontalAlignment(JTextField.CENTER);
	    	A1.getDocument().addDocumentListener(documentListener);
	    	A1.addMouseListener(mouseListener);
	    	gbc.gridy = 1;
	    	gbc.insets = new Insets(0, 0, 8, 0);
	    add(A1, gbc);
	    	
		B1 = new JTextField(8);
	    	B1.setPreferredSize(new Dimension(0, 35));
	    	B1.setHorizontalAlignment(JTextField.CENTER);
	    	B1.getDocument().addDocumentListener(documentListener);
	    	B1.addMouseListener(mouseListener);
	    	gbc.gridy = 2;
	    	gbc.insets = new Insets(0, 0, 0, 0);
	    add(B1, gbc);
	    
	    addMouseListener(mouseListener);
	}
	
	public void inserirTitulo()
	{
		titulo.setText(tituloReserva);
	}
	
	public static void fecharJanela()
	{
		if(janelaOpcoes != null && janelaOpcoes.isDisplayable())
			janelaOpcoes.dispose();
	}
}