import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Grandeza extends JPanel
{
	private Grandeza grandeza = this;
	private Dimension sizeGrandeza = new Dimension(100, 100);
	private Dimension sizeTextField = new Dimension(0, 35);
	private Dimension sizeJanelaOpcoes = new Dimension(65, 34);
	
	public static JFrame janelaOpcoes = new JFrame();
	
	public Grandeza(int cont)
	{
		MouseListener mouseListener = new MouseAdapter()
		{
	    	public void mouseClicked(MouseEvent e)
	    	{
	    		if(Janela.panel.getComponentCount() > 3 && e.getButton() == MouseEvent.BUTTON3)
	    			abrirJanelaOpcoes();
	    	}
		};
		
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		setPreferredSize(sizeGrandeza);
		addMouseListener(mouseListener);
		
		JTextField titulo = new JTextField("GRANDEZA  " + cont);
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
	
	private void abrirJanelaOpcoes()
	{
		janelaOpcoes.dispose();
		janelaOpcoes = new JFrame();
		janelaOpcoes.setAlwaysOnTop(true);
		janelaOpcoes.setUndecorated(true);
		janelaOpcoes.getRootPane().setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		janelaOpcoes.setSize(sizeJanelaOpcoes);
		janelaOpcoes.setLocation(getLocationOnScreen().x + (sizeGrandeza.width - sizeJanelaOpcoes.width), getLocationOnScreen().y - sizeJanelaOpcoes.height);
		janelaOpcoes.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton remover = new JButton();
				remover.setPreferredSize(new Dimension(25, 23));
				remover.setFocusPainted(false);
		
		JButton inverter = new JButton();
				inverter.setPreferredSize(new Dimension(25, 23));
				inverter.setFocusPainted(false);
		
		remover.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int indice = Janela.panel.getComponentZOrder(grandeza);
				
				// Remove a grandeza e o sinal de igualdade
				Janela.panel.remove(grandeza);
				if(indice > 1)
					Janela.panel.remove(indice - 1);
				else
					Janela.panel.remove(indice);
				
				janelaOpcoes.dispose();
				Janela.panel.revalidate();
				Janela.panel.repaint();
			}
		});
    	
		janelaOpcoes.add(inverter);
		janelaOpcoes.add(remover);
		janelaOpcoes.setVisible(true);
	}
}