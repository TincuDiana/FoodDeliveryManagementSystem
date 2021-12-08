package presentationLayer;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import bussinessLayer.DeliveryService;

public class Employee extends JFrame implements Observer {
	private JLabel JLabelEmployee;
	private DeliveryService ds;
	private int idOp;

	public DeliveryService getDs() {
		return ds;
	}

	public void setDs(DeliveryService ds) {
		this.ds = ds;
	}

	public Employee() {
		initialize();
	}

	public Employee(DeliveryService deliveryService) {
		this.ds = deliveryService;
		initialize();
	}

	public void initialize() {
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setBounds(500, 150, 600, 300);
		this.getContentPane().setLayout(null);

		JLabelEmployee = new JLabel("Employee");
		JLabelEmployee.setBounds(230, 0, 450, 20);
		getContentPane().add(JLabelEmployee);

		JButton btnNewButtonBack = new JButton("Back");
		btnNewButtonBack.setBounds(0, 0, 147, 23);
		this.getContentPane().add(btnNewButtonBack);
		btnNewButtonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogIn c = new LogIn(idOp, ds);
				c.setVisible(true);
				c.setLocationRelativeTo(null);
				dispose();
			}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee frame = new Employee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Notificare angajat!");
		try {
			ds.newOrder(ds.getClient());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
