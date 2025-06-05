import java.util.InputMismatchException;
import java.util.Scanner;
public class calculadoraR3
{
	public static void main(String[] args)
	{
		Scanner userInput = new Scanner(System.in);
		String a1, b1, a2, b2;
		int modo, cont = 0;
		double resultado = 0;
		
		System.out.println(
		"ESCOLHA O MODO DE OPERAÇÃO:"
		+ "\n0 - Diretamente proporcional "
		+ "\n1 - Inversamente proporcional");
		
		while(true)
		{
			try
			{
				modo = userInput.nextInt();		
				switch(modo)
				{
					case 0:
						System.out.println("\nGRANDEZAS DIRETAMENTE PROPORCIONAIS:");
						break;
					case 1:
						System.out.println("\nGRANDEZAS INVERSAMENTE PROPORCIONAIS:");
						break;
					default:
						System.out.println("Dígito inválido! Insira apenas 0 ou 1.");
						continue;
				}			
				break;
			}
			catch(InputMismatchException exception) 
			{
				System.out.println("Dígito inválido! Insira apenas 0 ou 1.");
				userInput.nextLine();
				continue;
			}
		}
		
		userInput.nextLine();
		System.out.print("A1: ");
			a1 = userInput.nextLine();
		System.out.print("B1: ");
			b1 = userInput.nextLine();
		System.out.print("A2: ");
			a2 = userInput.nextLine();
		System.out.print("B2: ");
			b2 = userInput.nextLine();
		
		if(a1.isEmpty())
			cont++;
		if(b1.isEmpty())
			cont++;
		if(a2.isEmpty())
			cont++;
		if(b2.isEmpty())
			cont++;
		
		try
		{
			if(cont == 1)
			{
				if(modo == 0)
				{
					if(a1.isEmpty())
						resultado = Double.parseDouble(b1) * Double.parseDouble(a2) / Double.parseDouble(b2);
					else if(b1.isEmpty())
						resultado = Double.parseDouble(a1) * Double.parseDouble(b2) / Double.parseDouble(a2);
					else if(a2.isEmpty())
						resultado = Double.parseDouble(a1) * Double.parseDouble(b2) / Double.parseDouble(b1);
					else if(b2.isEmpty())
						resultado = Double.parseDouble(b1) * Double.parseDouble(a2) / Double.parseDouble(a1);
				}
				else if(modo == 1)
				{
					if(a1.isEmpty())
						resultado = Double.parseDouble(b1) * Double.parseDouble(b2) / Double.parseDouble(a2);
					else if(b1.isEmpty())
						resultado = Double.parseDouble(a1) * Double.parseDouble(a2) / Double.parseDouble(b2);
					else if(a2.isEmpty())
						resultado = Double.parseDouble(b1) * Double.parseDouble(b2) / Double.parseDouble(a1);
					else if(b2.isEmpty())
						resultado = Double.parseDouble(a1) * Double.parseDouble(a2) / Double.parseDouble(b1);
				}
				System.out.print("\nO valor da incógnita é: " + resultado);
			}
			else 
				System.out.print("\nSão necessários 3 valores numéricos para realizar o cálculo.\nO campo da incógnita deve estar em branco.");
		} 
		catch(NumberFormatException exception)
		{
			System.out.println("\nDigito inválido! Verifique se o valores foram inseridos corretamente.");
		}
	}
}