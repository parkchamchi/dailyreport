package dailyreport;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import dailyreport.Hours;
import dailyreport.Report;
import dailyreport.Save;
import dailyreport.GetTitle;

public class Gui implements ActionListener {
	Report report;

	JTextField hourTextFields[];
	
	Gui() {
		report = new Report();
		Save.ReadFromFile(report);

		JFrame jfrm = new JFrame("Daily report program");
		jfrm.setLayout(new BoxLayout(jfrm.getContentPane(), BoxLayout.Y_AXIS));
		jfrm.setSize(250, 500);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTextField titleTextField = new JTextField(GetTitle.get());
		titleTextField.setEditable(false);
		jfrm.add(titleTextField);

		JLabel hourLabels[] = new JLabel[Hours.count];
		hourTextFields = new JTextField[Hours.count];
		for (Hours hour : Hours.values()) {
			int ord = hour.ordinal();

			hourLabels[ord] = new JLabel(hour.getStr());
			jfrm.add(hourLabels[ord]);

			hourTextFields[ord] = new JTextField(report.getTask(hour), 10);
			hourTextFields[ord].setActionCommand(hour.toString());
			hourTextFields[ord].addActionListener(this);
			jfrm.add(hourTextFields[ord]);
		}
		
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(this);
		jfrm.add(applyButton);

		jfrm.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Apply")) {
			for (Hours hour : Hours.values()) {
				int ord = hour.ordinal();
				String orgText = report.getTask(hour);
				String curText = hourTextFields[ord].getText();

				if (!orgText.equals(curText))
					report.setTask(hour, curText);
			}
		}
		else {
			Hours hour = Hours.valueOf(ae.getActionCommand());
			int ord = hour.ordinal();
			String orgText = report.getTask(hour);
			String curText = hourTextFields[ord].getText();

			if (!orgText.equals(curText))
				report.setTask(hour, curText);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Gui();
			}
		});
	}
}