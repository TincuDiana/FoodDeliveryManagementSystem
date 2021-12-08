package presentationLayer;

import bussinessLayer.DeliveryService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProduct extends JFrame {

    private JPanel contentPane;
    private int id;
    private JTextField textField;
    private DeliveryService deliveryService;

    public DeleteProduct(int idU,DeliveryService deliveryService)
    {
        this.deliveryService=deliveryService;
        this.id=idU;
        initialize();
    }
    
    public DeleteProduct()
    {
        initialize();
    }

    public void  initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(565, 250, 300, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Introduceti numele produsului");
        lblNewLabel.setBounds(60, 15, 414, 47);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(75, 60, 137, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Delete");
        btnNewButton.setBounds(100, 97, 89, 23);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title=textField.getText();         
                    deliveryService.deleteProduct(title);
                    JOptionPane.showMessageDialog(null,"Produs sters cu succes! ","Succes",JOptionPane.CLOSED_OPTION) ;
                    dispose();
               
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DeleteProduct frame = new DeleteProduct();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}