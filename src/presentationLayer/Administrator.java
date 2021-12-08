package presentationLayer;

import bussinessLayer.BaseProduct;
import bussinessLayer.CompositeProduct;
import bussinessLayer.DeliveryService;
import bussinessLayer.MenuItem;
import dataLayer.WriteFile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Administrator extends JFrame {

	private JPanel contentPane;
	private DeliveryService deliveryService = new DeliveryService();
	int id = 0;
	private JTextField textField;
	protected JTextField textFieldd;

	public Administrator(int id, DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
		this.id = id;
		initialize();
	}

	public Administrator(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
		initialize();
	}

	public Administrator() {
		initialize();
	}

	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButtonAddProduct = new JButton("Add Product");
		btnNewButtonAddProduct.setBounds(580, 22, 147, 23);
		contentPane.add(btnNewButtonAddProduct);
		btnNewButtonAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id++;
				AdaugareProdus c = new AdaugareProdus(id, deliveryService);
				c.setVisible(true);
				c.setLocationRelativeTo(null);
			}
		});

		JButton btnNewButtonDeleteProduct = new JButton("Delete Product");
		btnNewButtonDeleteProduct.setBounds(580, 62, 147, 23);
		contentPane.add(btnNewButtonDeleteProduct);
		btnNewButtonDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteProduct c = new DeleteProduct(id, deliveryService);
				c.setVisible(true);
				c.setLocationRelativeTo(null);
			}
		});

		JButton btnNewButtonEditProduct = new JButton("Edit Product");
		btnNewButtonEditProduct.setBounds(580, 102, 147, 23);
		contentPane.add(btnNewButtonEditProduct);
		btnNewButtonEditProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditProduct c = new EditProduct(id, deliveryService);
				c.setVisible(true);
				c.setLocationRelativeTo(null);
			}
		});

		JButton btnNewButtonImport = new JButton("Import Products");
		btnNewButtonImport.setBounds(580, 142, 147, 23);
		contentPane.add(btnNewButtonImport);
		btnNewButtonImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (deliveryService != null) {
					try {
						deliveryService.importProducts();
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
				}
			}
		});

		JButton btnNewButtonGenerareRaport = new JButton("Raport");
		btnNewButtonGenerareRaport.setBounds(580, 182, 147, 23);
		contentPane.add(btnNewButtonGenerareRaport);
		btnNewButtonGenerareRaport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerareRapoarte c = null;
				try {
					c = new GenerareRapoarte(id, deliveryService);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				c.setVisible(true);
				c.setLocationRelativeTo(null);
			}
		});

		JButton btnNewButtonBack = new JButton("Back");
		btnNewButtonBack.setBounds(580, 222, 147, 23);
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

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[15000][8],
				new String[] { "ID", "Name", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price" }));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(40, 22, 460, 260);
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

		JLabel lblNewLabel = new JLabel("Numele produsului compus:");
		lblNewLabel.setBounds(40, 340, 300, 14);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(260, 340, 200, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabelcp = new JLabel("Produsele de adaugat:");
		lblNewLabelcp.setBounds(40, 370, 300, 14);
		contentPane.add(lblNewLabelcp);
		textFieldd = new JTextField();
		textFieldd.setBounds(260, 370, 200, 20);
		contentPane.add(textFieldd);
		textFieldd.setColumns(10);

		JButton btnNewButtonAddCompPr = new JButton("Add composite product");
		btnNewButtonAddCompPr.setBounds(570, 340, 170, 23);
		contentPane.add(btnNewButtonAddCompPr);
		btnNewButtonAddCompPr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<BaseProduct> bpList = null;
					String productName = textField.getText();
					if (textFieldd.getText().length() > 1) {
						bpList = deliveryService.extractFromCompositeProduct(textFieldd.getText());
						if (bpList.size() != 0) {
							int price = 0;
							for (BaseProduct item : bpList) {
								price += item.getPrice();
							}
							MenuItem newProduct = new CompositeProduct(productName, bpList);
							newProduct.setPrice(price);
							if (deliveryService.SearchProductByName(newProduct.getName()) == null) {
								newProduct.setName(productName);
								deliveryService.getCompPr().add((CompositeProduct) newProduct);
							}
					}
					for(CompositeProduct cp: deliveryService.getCompPr()) {
					System.out.println("\nProdusul " + cp.getName() + " contine: ");
					for(BaseProduct p: cp.getCompPr())
						System.out.println("* " + p.getName() + " ");
					}}}
				catch (Exception except) {
					JOptionPane.showMessageDialog(null, "Verificati daca ati scris corect produsele! ",
							"Eroare la adaugarea unui nou produs", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrator frame = new Administrator();
					frame.setName("Administrator");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
