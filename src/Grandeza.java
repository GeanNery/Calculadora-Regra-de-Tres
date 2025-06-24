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

public class Grandeza extends JPanel
{
	public static JanelaOpcoes janelaOpcoes;
	public JTextField titulo;
	
	private Dimension sizeGrandeza = new Dimension(100, 100);
	private Dimension sizeTextField = new Dimension(0, 35);
	
	public Grandeza(int cont)
	{
		MouseListener mouseListener = new MouseAdapter()
		{
	    	public void mouseClicked(MouseEvent event)
	    	{
	    		if(Janela.panel.getComponentCount() > 3 && event.getButton() == MouseEvent.BUTTON3)
	    		{
	    			if(janelaOpcoes != null && janelaOpcoes.isDisplayable())
	    				janelaOpcoes.dispose();
	    			
	    			janelaOpcoes = new JanelaOpcoes(Grandeza.this, sizeGrandeza);
	    		}
	    	}
		};
		
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		setPreferredSize(sizeGrandeza);
		addMouseListener(mouseListener);
		
		titulo = new JTextField("GRANDEZA  " + cont);
		    titulo.setHorizontalAlignment(JTextField.CENTER);
		    titulo.setBorder(null);
		    titulo.setBackground(null);
		    titulo.addMouseListener(mouseListener);
		    gbc.gridy = 0;
		    gbc.insets = new Insets(0, 0, 3, 0);
		add(titulo, gbc);
		    
		JTextField A1 = new JTextField(8);
	    	A1.setPreferredSize(sizeTextField);
	    	A1.setHorizontalAlignment(JTextField.CENTER);
	    	A1.addMouseListener(mouseListener);
	    	gbc.gridy = 1;
	    	gbc.insets = new Insets(0, 0, 8, 0);
	    add(A1, gbc);
	    	
		JTextField B1 = new JTextField(8);
	    	B1.setPreferredSize(sizeTextField);
	    	B1.setHorizontalAlignment(JTextField.CENTER);
	    	B1.addMouseListener(mouseListener);
	    	gbc.gridy = 2;
	    	gbc.insets = new Insets(0, 0, 0, 0);
	    add(B1, gbc);
	}
}