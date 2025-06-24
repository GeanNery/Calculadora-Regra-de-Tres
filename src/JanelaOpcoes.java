import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

public class JanelaOpcoes extends JFrame
{
	public JanelaOpcoes(Grandeza grandeza, Dimension sizeGrandeza)
	{
		Dimension sizeJanelaOpcoes = new Dimension(65, 34);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setSize(sizeJanelaOpcoes);
		setLocation(grandeza.getLocationOnScreen().x + (sizeGrandeza.width - sizeJanelaOpcoes.width), grandeza.getLocationOnScreen().y - sizeJanelaOpcoes.height);
		
		setAlwaysOnTop(true);
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		
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
				
				// Remove a grandeza e o sinal de igualdade adjacente
				Janela.panel.remove(grandeza);
				if(indice > 1)
					Janela.panel.remove(indice - 1);
				else
					Janela.panel.remove(indice);
				
				dispose();
				Janela.panel.revalidate();
				Janela.panel.repaint();
			}
		});
    	
		add(inverter);
		add(remover);
		setVisible(true);
	}
}