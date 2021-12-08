package presentationLayer;

import bussinessLayer.BaseProduct;
import bussinessLayer.Clientt;
import bussinessLayer.DeliveryService;
import bussinessLayer.User;
import dataLayer.Serializator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;

public class LogIn extends JFrame {
	private int idl=-1;
	private JTextField textFieldUser;
	private JTextField textFieldParola;
	private JPanel contentPane;
	private int d=0;
	
	private DeliveryService deliveryService = new DeliveryService();
	private HashSet<BaseProduct> baseProducts= new HashSet<>();
	
	public LogIn(int id, DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
		this.idl = id;
		initialize();
	}
	public LogIn() {
		initialize();
	}

	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabelLogIn = new JLabel("Intrati in cont");
		lblNewLabelLogIn.setBounds(165, 24, 189, 26);
		contentPane.add(lblNewLabelLogIn);

		JLabel lblNewLabelUser = new JLabel("User");
		lblNewLabelUser.setBounds(70, 70, 64, 14);
		contentPane.add(lblNewLabelUser);
		
		textFieldUser = new JTextField();
		textFieldUser.setBounds(129, 70, 190, 20);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);


		JLabel lblNewLabelParola = new JLabel("Parola");
		lblNewLabelParola.setBounds(70, 100, 77, 14);
		contentPane.add(lblNewLabelParola);
		
		textFieldParola = new JTextField();
		textFieldParola.setBounds(129, 100, 190, 20);
		contentPane.add(textFieldParola);
		textFieldParola.setColumns(10);

		JLabel lblNewLabelContNou = new JLabel("Doriti sa creati un cont nou?");
		lblNewLabelContNou.setBounds(141, 175, 205, 26);
		contentPane.add(lblNewLabelContNou);

		JButton btnNewButtonCreate = new JButton("Creare");
		btnNewButtonCreate.setBounds(140, 212, 161, 23);
		contentPane.add(btnNewButtonCreate);
		btnNewButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeliveryService deliveryService1 = new DeliveryService();
				CreareCont c = new CreareCont(deliveryService1);
				c.setVisible(true);
				c.setLocationRelativeTo(null);
				dispose();
			}
		});

		JButton btnNewButtonLogIn = new JButton("Login");
		btnNewButtonLogIn.setBounds(174, 140, 89, 23);
		contentPane.add(btnNewButtonLogIn);
		btnNewButtonLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = null;
				if ( textFieldUser.getText().isBlank() || textFieldParola.getText().isBlank())
					System.out.println("Va rugam introduceti date in campurile goale!\n"); 
				else {
					List<User> users = null;
					Serializator ser = new Serializator();
					try {
						users = (List<User>) ser.deserialization("users.txt");
					} catch (ClassNotFoundException ex) {
						ex.printStackTrace();
					}
					try {
						for (User u : users)
							if (u.getPassword().equals(textFieldParola.getText()) && u.getUserName().equals(textFieldUser.getText()))
								type = u.getType();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					if (type != null) {
						if (type.equals("Client")) {
							idl++;
							Clientt cl=new Clientt();
							cl.setId(idl);
							deliveryService.setClient(cl);
							Client c = new Client(cl.getId(), deliveryService);
							c.setVisible(true);
							c.setLocationRelativeTo(null);
							dispose();
						}
						if (type.equals("Administrator")) {
							Administrator c = new Administrator(deliveryService);
							c.setVisible(true);
							c.setLocationRelativeTo(null);
							dispose();
						}
						
						if (type.equals("Employee")) {
							Employee c = new Employee(deliveryService);
							c.setVisible(true);
							c.setLocationRelativeTo(null);
							dispose();
						}
					}
				}
			}
		});
		Serializator s = new Serializator();
        try {
            baseProducts= (HashSet<BaseProduct>) s.deserialization("deliveryService.txt");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
