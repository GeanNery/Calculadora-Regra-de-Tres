import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Grandeza extends JPanel
{
	public Grandeza(int cont)
	{
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		setPreferredSize(new Dimension(100, 100));
		
		gbc.insets = new Insets(0, 0, 3, 0);
		JTextField titulo = new JTextField("GRANDEZA  " + cont);
		    titulo.setHorizontalAlignment(JTextField.CENTER);
		    titulo.setBorder(null);
		    titulo.setBackground(null);
		    gbc.gridy = 0;
		add(titulo, gbc);
		    
		gbc.insets = new Insets(0, 0, 8, 0);
		JTextField A1 = new JTextField(8);
	    	A1.setPreferredSize(new Dimension(0, 35));
	    	A1.setHorizontalAlignment(JTextField.CENTER);
	    	gbc.gridy = 1;
	    add(A1, gbc);
	    	
	    gbc.insets = new Insets(0, 0, 0, 0);
		JTextField B1 = new JTextField(8);
	    	B1.setPreferredSize(new Dimension(0, 35));
	    	B1.setHorizontalAlignment(JTextField.CENTER);
	    	gbc.gridy = 2;
	    add(B1, gbc);
	}
}