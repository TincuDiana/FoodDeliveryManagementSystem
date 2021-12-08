package presentationLayer;

import bussinessLayer.Clientt;
import bussinessLayer.DeliveryService;
import bussinessLayer.User;
import dataLayer.Serializator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreareCont extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUserName;
	private JTextField textFieldParola;
	private int id=0;
	private List<User> users = new ArrayList<User>();
	private DeliveryService deliveryService;

	public CreareCont( DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
		initialize();
	}

	
	public CreareCont(int id, DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
		this.id = id;
		initialize();
	}

	public CreareCont() {
		initialize();
	}

	public int getIdnewAcc() {
		return id;
	}

	public void setIdnewAcc(int idnewAcc) {
		this.id = idnewAcc;
	}

	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 450, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButtonBack = new JButton("Back");
		btnNewButtonBack.setBounds(0, 0, 90, 23);
		contentPane.add(btnNewButtonBack);
		btnNewButtonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id++;
				LogIn c = new LogIn(id, deliveryService);
				c.setVisible(true);
				c.setLocationRelativeTo(null);
				dispose();
			}
		});

		JLabel lblNewLabelCreareCont = new JLabel("Creare cont");
		lblNewLabelCreareCont.setBounds(160, 50, 196, 28);
		contentPane.add(lblNewLabelCreareCont);

		JLabel lblNewLabelUsername = new JLabel(" * Username");
		lblNewLabelUsername.setBounds(70, 90, 72, 14);
		contentPane.add(lblNewLabelUsername);

		JLabel lblNewLabelParola = new JLabel(" * Parola");
		lblNewLabelParola.setBounds(70, 120, 72, 14);
		contentPane.add(lblNewLabelParola);

		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(179, 90, 95, 20);
		contentPane.add(textFieldUserName);
		textFieldUserName.setColumns(10);

		textFieldParola = new JTextField();
		textFieldParola.setBounds(179, 120, 95, 20);
		contentPane.add(textFieldParola);
		textFieldParola.setColumns(10);

		JRadioButton rdbtnNewRadioButtonAdmin = new JRadioButton("Admin");
		rdbtnNewRadioButtonAdmin.setBounds(295, 66, 109, 23);
		contentPane.add(rdbtnNewRadioButtonAdmin);

		JRadioButton rdbtnNewRadioButtonCl = new JRadioButton("Client");
		rdbtnNewRadioButtonCl.setBounds(295, 111, 109, 23);
		contentPane.add(rdbtnNewRadioButtonCl);

		JRadioButton rdbtnNewRadioButtonEmployee = new JRadioButton("Employee");
		rdbtnNewRadioButtonEmployee.setBounds(295, 156, 109, 23);
		contentPane.add(rdbtnNewRadioButtonEmployee);

		Serializator ser = new Serializator();
		try {
			users = (List<User>) ser.deserialization("users.txt");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		JButton btnNewButtonCreare = new JButton("Creare");
		btnNewButtonCreare.setBounds(146, 199, 138, 23);
		contentPane.add(btnNewButtonCreare);
		btnNewButtonCreare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = textFieldUserName.getText();
				String password = textFieldParola.getText();
				String tip ;
				if (rdbtnNewRadioButtonAdmin.isSelected())
					tip = "Administrator";
				else if (rdbtnNewRadioButtonCl.isSelected())
					tip = "Client";
				else if (rdbtnNewRadioButtonEmployee.isSelected())
					tip = "Employee";
				 else 
					id++;
					User newUser = new User(id,username, password, type);
					users.add(newUser);
					ser.serialization(users, "users.txt");
					LogIn login = new LogIn(id, deliveryService);
					login.setVisible(true);
					System.out.println("Cont creat!");
				
			}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreareCont frame = new CreareCont();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}