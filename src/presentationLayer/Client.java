package presentationLayer;

import bussinessLayer.BaseProduct;
import bussinessLayer.DeliveryService;
import dataLayer.WriteFile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Observable;

public class Client extends JFrame {

	private JPanel contentPane;
	private int id;
	private JTextField textField;
	private DeliveryService deliveryService = new DeliveryService();
	//private int id = 0;
	//Observable o= new Observable();
	
	public Client(int idU, DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
		this.id = idU;
		initialize();
	}

	public Client(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
		initialize();
	}
	
	public Client() {
		initialize();
	}

	public void initialize() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 180, 470, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButtonBack = new JButton("Back");
		btnNewButtonBack.setBounds(0, 0, 90, 23);
		contentPane.add(btnNewButtonBack);
		btnNewButtonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogIn c = new LogIn(id, deliveryService);
				c.setVisible(true);
				c.setLocationRelativeTo(null);
				dispose();
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Introduceti pretul cautat: ");
		lblNewLabel_1.setBounds(10, 30, 144, 14);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(10, 60, 144, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButtonSearch = new JButton("Cauta");
		btnNewButtonSearch.setBounds(170, 58, 77, 27);
		contentPane.add(btnNewButtonSearch);
		btnNewButtonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String value = textField.getText();
				HashSet<BaseProduct> list = new HashSet<>();
				list = deliveryService.findProductsByPrice(Integer.parseInt(value));
				String str = "Rezultatele cautarii: \n";
				for (BaseProduct product : list) {
					str = str + "*" + product.getName() + "*" + " rating: " + product.getRating() + " calorii: "
							+ product.getCalorii() + " proteine: " + product.getProteine() + " grasimi: "
							+ product.getGrasimi() + " pret: " + product.getPrice() + "\n\n";
				}
				try {
					WriteFile wr = new WriteFile("SearchResult.txt", str);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JLabel lblNewLabelOrder = new JLabel("Selectati produsele dorite din primul tabel");
		lblNewLabelOrder.setBounds(10, 101, 255, 14);
		contentPane.add(lblNewLabelOrder);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[15000][8],
				new String[] { "ID", "Name", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price" }));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 134, 400, 200);
		contentPane.add(scrollPane);
		HashSet<BaseProduct> baseProducts = new HashSet<>();
		if (deliveryService != null) {
			baseProducts = deliveryService.getBaseProducts();
		}
		int cnt = 0;
		for (BaseProduct obj : baseProducts) {
			table.getModel().setValueAt(obj.getId(), cnt, 0);
			table.getModel().setValueAt(obj.getName(), cnt, 1);
			table.getModel().setValueAt(obj.getRating(), cnt, 2);
			table.getModel().setValueAt(obj.getCalorii(), cnt, 3);
			table.getModel().setValueAt(obj.getProteine(), cnt, 4);
			table.getModel().setValueAt(obj.getGrasimi(), cnt, 5);
			table.getModel().setValueAt(obj.getSodium(), cnt, 6);
			table.getModel().setValueAt(obj.getPrice(), cnt, 7);
			cnt++;
		}

		table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(), 50));
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(35);
		scrollPane.setViewportView(table);

		JButton btnNewButtonComanda = new JButton("Comanda!");
		btnNewButtonComanda.setBounds(267, 97, 114, 23);
		contentPane.add(btnNewButtonComanda);
		btnNewButtonComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRows().length != 0) {
					for (int item : table.getSelectedRows())
						deliveryService.addMenuItems(item);
					try {
						WriteFile f = new WriteFile("bill.txt", deliveryService.newOrder(deliveryService.getClient()));
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
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
