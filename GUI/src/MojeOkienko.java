import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MojeOkienko extends JFrame implements ActionListener
{
	JButton bPodajDate, bWyjscie;
	/////Arek

	public MojeOkienko()
	{
		setSize(300, 200);
		setSize(300, 220);
		setTitle("Pierwsze okienko");
		setLayout(null);
		bPodajDate = new JButton("Podaj Dat�");
		bPodajDate.setBounds(50, 75, 100, 20);
		add(bPodajDate);
		bPodajDate.addActionListener(this); // button jest zr�d�em zdarze� a
											// ramka jest s�uchaczem

		bWyjscie = new JButton("Wyjs�ie");
		bWyjscie.setBounds(150, 75, 100, 20);
		add(bWyjscie);
		bWyjscie.addActionListener(this);
		System.out.println("Test");
	}

	public static void main(String[] args)
	{
		MojeOkienko okienko = new MojeOkienko();
		okienko.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okienko.setVisible(true);
		

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object zrodlo = e.getSource();
		if (zrodlo == bPodajDate)
		{
			System.out.println(new Date());
		} else if (zrodlo == bWyjscie)
		{
			dispose();
		}

	}

}
