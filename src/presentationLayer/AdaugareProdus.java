package presentationLayer;


import bussinessLayer.BaseProduct;
import bussinessLayer.DeliveryService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.stream.Collectors;

public class AdaugareProdus extends JFrame {

    private int id;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private DeliveryService deliveryService;
    private HashSet<BaseProduct> baseProducts = new HashSet<>();

    public  AdaugareProdus(int id,DeliveryService deliveryService)
    {
        this.deliveryService=deliveryService;
        this.id=id;
        initialize();
    }
    
    public  AdaugareProdus()
    {
        initialize();
    }
    
    public void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(565, 250, 365, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Title");
        lblNewLabel.setBounds(20, 30, 46, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Rating");
        lblNewLabel_1.setBounds(20, 60, 60, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Calories");
        lblNewLabel_2.setBounds(20, 90, 60, 14);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Protein");
        lblNewLabel_3.setBounds(20, 120, 60, 14);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Fat");
        lblNewLabel_4.setBounds(20, 150, 60, 14);
        contentPane.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Sodium");
        lblNewLabel_5.setBounds(20, 180, 60, 14);
        contentPane.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Price");
        lblNewLabel_6.setBounds(20, 210, 60, 14);
        contentPane.add(lblNewLabel_6);

        textField = new JTextField();
        textField.setBounds(79, 30, 90, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(79, 60, 90, 20);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setBounds(79, 90, 90, 20);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setBounds(79, 120, 90, 20);
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setBounds(79, 150, 90, 20);
        contentPane.add(textField_4);
        textField_4.setColumns(10);

        textField_5 = new JTextField();
        textField_5.setBounds(79, 180, 90, 20);
        contentPane.add(textField_5);
        textField_5.setColumns(10);

        textField_6 = new JTextField();
        textField_6.setBounds(79, 210, 90, 20);
        contentPane.add(textField_6);
        textField_6.setColumns(10);

        JButton btnNewButton = new JButton("Add Product");
        btnNewButton.setBounds(204, 30, 135, 200);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().isBlank() || textField_1.getText().isBlank() || textField_2.getText().isBlank() || textField_3.getText().isBlank() || textField_4.getText().isBlank() || textField_5.getText().isBlank() || textField_6.getText().isBlank())
                    System.out.println("Eroare adaugare produs! Completati toate campurile goale!");
                else
                {
                    BaseProduct bp= new BaseProduct(id, textField.getText(), Integer.parseInt(textField_6.getText()), Float.parseFloat(textField_1.getText()), Integer.parseInt(textField_2.getText()), Integer.parseInt(textField_3.getText()), Integer.parseInt(textField_4.getText()), Integer.parseInt(textField_5.getText()));
                    deliveryService.addProduct(bp);
                    System.out.println("Produs adaugat cu succes! ");
                    dispose();
                }
                	baseProducts = (HashSet<BaseProduct>) baseProducts.stream().distinct().collect(Collectors.toSet());
                }
        });
       
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	AdaugareProdus frame = new AdaugareProdus();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
