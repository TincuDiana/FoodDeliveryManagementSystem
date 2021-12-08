package presentationLayer;

import bussinessLayer.Clientt;
import bussinessLayer.DeliveryService;
import bussinessLayer.MenuItem;
import bussinessLayer.Order;
import dataLayer.WriteFile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GenerareRapoarte extends JFrame {

	private JPanel contentPane;
	private int id;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private DeliveryService ds = new DeliveryService();


	public GenerareRapoarte(int idU, DeliveryService deliveryService) throws IOException {
		this.ds = deliveryService;
		this.id = idU;
		initialize();
	}

	public GenerareRapoarte() throws IOException {
		initialize();
	}

	public void initialize() throws IOException {
		ds.importProducts();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabelTitlu = new JLabel("Rapoarte");
		lblNewLabelTitlu.setBounds(180, 20, 193, 14);
		contentPane.add(lblNewLabelTitlu);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Comenzile dintr-un inteval de timp");
		rdbtnNewRadioButton.setBounds(10, 59, 700, 23);
		contentPane.add(rdbtnNewRadioButton);

		JLabel lblNewLabel_1 = new JLabel("ora inceput");
		lblNewLabel_1.setBounds(40, 91, 70, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_6 = new JLabel("ora final");
		lblNewLabel_6.setBounds(200, 91, 50, 14);
		contentPane.add(lblNewLabel_6);

		textField = new JTextField();
		textField.setBounds(130, 91, 48, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(270, 91, 48, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton(
				"Produsele comandate de mai mult de un numar de ori dat");
		rdbtnNewRadioButton_1.setBounds(10, 124, 386, 23);
		contentPane.add(rdbtnNewRadioButton_1);

		JLabel lblNewLabel_2 = new JLabel("numarul minim de produse:");
		lblNewLabel_2.setBounds(40, 154, 160, 14);
		contentPane.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(220, 154, 50, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton(
				"clientii care au facut un anumit numar de comenzi");
		rdbtnNewRadioButton_2.setBounds(10, 189, 456, 23);
		contentPane.add(rdbtnNewRadioButton_2);

		JLabel lblNewLabel_3 = new JLabel("numarul de comenzi :");
		lblNewLabel_3.setBounds(40, 219, 150, 20);
		contentPane.add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setBounds(201, 219, 48, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("pret minim :");
		lblNewLabel_7.setBounds(40, 239, 150, 20);
		contentPane.add(lblNewLabel_7);

		textField_4 = new JTextField();
		textField_4.setBounds(201, 239, 48, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton(
				"Produsele comandate in ziua: ");
		rdbtnNewRadioButton_3.setBounds(10, 289, 470, 20);
		contentPane.add(rdbtnNewRadioButton_3);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(40, 320, 100, 19);
		contentPane.add(dateChooser);


		JButton btnNewButton = new JButton("Generati raport");
		btnNewButton.setBounds(180, 320, 133, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNewRadioButton.isSelected()) {
					String startHour = textField.getText();
					String endHour = textField_1.getText();
					List<Order> list = null;
					try {
						list = (List<Order>) ds.Report1(Integer.valueOf(startHour), Integer.valueOf(endHour));
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					}
					String str = "Comenzile inregistrate intre ora " + startHour + " si ora " + endHour + " sunt:\n";
					for (Order obj : list)
						str = str + "Comanda cu ID-ul : " + obj.getOrderId() + "\n";
					try {
						WriteFile wr = new WriteFile("Raport1.txt", str);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (rdbtnNewRadioButton_2.isSelected()) {
					String nr_of_times = textField_3.getText();
					String amount = textField_4.getText();

					List<Clientt> cl= null;
					try {
						cl = ds.Report3(Integer.valueOf(nr_of_times), Integer.valueOf(amount));
					} catch (NumberFormatException | IOException e1) {
						e1.printStackTrace();
					}
					FileWriter myWriter = null;
					try {
						myWriter = new FileWriter("Raport3.txt");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					String stringToWrite = "Clienti care au comandat de mai mult de " + nr_of_times
							+ "ori si valoarea comenzii este mai mare decat: " + amount + "\n";
					for(Clientt c: cl) {
						stringToWrite = stringToWrite + "Client ID " + c.getId() + " nr comenzi: " + c.getNrOrders() + "\n";}
						try {
							myWriter.write(stringToWrite);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					
					try {
						myWriter.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (rdbtnNewRadioButton_1.isSelected()) {
					String nr_of_times = textField_2.getText();
					if (nr_of_times.isBlank())
						JOptionPane.showMessageDialog(null, "Completati campurile goale", "Eroare ",
								JOptionPane.ERROR_MESSAGE);
					else {
						List<MenuItem> nrOfOrders = new ArrayList<MenuItem>();
						try {
							nrOfOrders = ds.Report2(Integer.valueOf(nr_of_times));
						} catch (NumberFormatException | IOException e1) {
							e1.printStackTrace();
						}
						FileWriter myWriter = null;
						try {
							myWriter = new FileWriter("Raport2.txt");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						String stringToWrite = "Produsele comandate de mai multe ori decat de " + nr_of_times + ":\n";
						try {
							myWriter.write(stringToWrite);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						for (MenuItem pr : nrOfOrders) {
							stringToWrite = "Nume: " + pr.getName() + " de " + pr.getNrcom()+ "\n";
							try {
								myWriter.write(stringToWrite);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						try {
							myWriter.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}

				}
				if (rdbtnNewRadioButton_3.isSelected())
					if (dateChooser.getDate().toString().isBlank())
						JOptionPane.showMessageDialog(null, "Introduceti ziua dorita ", "Eroare ",
								JOptionPane.ERROR_MESSAGE);
					else {
						HashMap<MenuItem,Integer> pr=new HashMap<MenuItem,Integer>();
						try {
							pr = ds.Report4(dateChooser.getDate());
						} catch (NumberFormatException | IOException e1) {
							e1.printStackTrace();
						}
						FileWriter myWriter = null;
						try {
							myWriter = new FileWriter("Raport4.txt");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						String stringToWrite = "Produsele comandate in data " + dateChooser.getDate()
								+ " sunt:\n";

						for(MenuItem m: pr.keySet()) {
							stringToWrite=stringToWrite + "Produsul: *" + m.getName() + "* a fost comandat de " + pr.get(m) + "\n";
						}
						try {
							myWriter.write(stringToWrite);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						try {
							myWriter.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
			}
		});

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerareRapoarte frame = new GenerareRapoarte();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
