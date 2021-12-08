package presentationLayer;

import bussinessLayer.DeliveryService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProduct extends JFrame {

    private JPanel contentPane;
    private int id;
    private JTextField textFieldProductName;
    private JTextField textFieldName;
    private JTextField textFieldRating;
    private JTextField textFieldCalorii;
    private JTextField textFieldProteine;
    private JTextField textFieldGrasimi;
    private JTextField textFieldSodium;
    private JTextField textFieldPrice;
    private DeliveryService deliveryService;

    public EditProduct(int id,DeliveryService deliveryService)
    {
        this.deliveryService=deliveryService;
        this.id=id;
        initialize();
    }    

    public EditProduct()
    {
        initialize();
    }

    public void  initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(590, 190, 240, 340);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabelModify = new JLabel("Nume produs");
        lblNewLabelModify.setBounds(20, 11, 108, 20);
        contentPane.add(lblNewLabelModify);

        textFieldProductName = new JTextField();
        textFieldProductName.setBounds(120, 11, 86, 20);
        contentPane.add(textFieldProductName);
        textFieldProductName.setColumns(10);

        JLabel lblNewLabelNewValues = new JLabel("Introduceti valorile noi:");
        lblNewLabelNewValues.setBounds(20, 38, 200, 14);
        contentPane.add(lblNewLabelNewValues);

        JLabel lblNewLabelNume = new JLabel("Name");
        lblNewLabelNume.setBounds(20, 65, 46, 14);
        contentPane.add(lblNewLabelNume);

        JLabel lblNewLabelRating = new JLabel("Rating");
        lblNewLabelRating.setBounds(20, 92, 57, 14);
        contentPane.add(lblNewLabelRating);

        JLabel lblNewLabelCalorii = new JLabel("Calories");
        lblNewLabelCalorii.setBounds(20, 119, 57, 14);
        contentPane.add(lblNewLabelCalorii);

        JLabel lblNewLabelProteine = new JLabel("Protein");
        lblNewLabelProteine.setBounds(20, 146, 57, 14);
        contentPane.add(lblNewLabelProteine);

        JLabel lblNewLabelGrasimi = new JLabel("Fat");
        lblNewLabelGrasimi.setBounds(20, 173, 46, 14);
        contentPane.add(lblNewLabelGrasimi);

        JLabel lblNewLabelSodium = new JLabel("Sodium");
        lblNewLabelSodium.setBounds(20, 200, 57, 14);
        contentPane.add(lblNewLabelSodium);

        JLabel lblNewLabelPrice = new JLabel("Price");
        lblNewLabelPrice.setBounds(20, 227, 46, 14);
        contentPane.add(lblNewLabelPrice);

        textFieldName= new JTextField();
        textFieldName.setBounds(94, 65, 86, 20);
        contentPane.add(textFieldName);
        textFieldName.setColumns(10);

        textFieldRating = new JTextField();
        textFieldRating.setBounds(94, 92, 86, 20);
        contentPane.add(textFieldRating);
        textFieldRating.setColumns(10);

        textFieldCalorii = new JTextField();
        textFieldCalorii.setBounds(94, 119, 86, 20);
        contentPane.add(textFieldCalorii);
        textFieldCalorii.setColumns(10);

        textFieldProteine = new JTextField();
        textFieldProteine.setBounds(94, 146, 86, 20);
        contentPane.add(textFieldProteine);
        textFieldProteine.setColumns(10);

        textFieldGrasimi = new JTextField();
        textFieldGrasimi.setBounds(94, 173, 86, 20);
        contentPane.add(textFieldGrasimi);
        textFieldGrasimi.setColumns(10);

        textFieldSodium = new JTextField();
        textFieldSodium.setBounds(94, 200, 86, 20);
        contentPane.add(textFieldSodium);
        textFieldSodium.setColumns(10);

        textFieldPrice = new JTextField();
        textFieldPrice.setBounds(94, 227, 86, 20);
        contentPane.add(textFieldPrice);
        textFieldPrice.setColumns(10);
       
        JButton btnNewButton = new JButton("Modify");
        btnNewButton.setBounds(20, 264, 180, 20);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textFieldProductName.getText().isBlank())
                    JOptionPane.showMessageDialog(null,"Precizati numele produsului! ","Eroare la adaugarea unui nou produs",JOptionPane.ERROR_MESSAGE) ;
                else
                {
                    deliveryService.modifyProduct(textFieldProductName.getText(), Float.parseFloat(textFieldRating.getText()), textFieldName.getText(), Integer.parseInt(textFieldCalorii.getText()), Integer.parseInt(textFieldSodium.getText()), Integer.parseInt(textFieldGrasimi.getText()), Integer.parseInt(textFieldProteine.getText()), Integer.parseInt(textFieldPrice.getText()));
                    JOptionPane.showMessageDialog(null,"Produs modificat cu succes! ","Succes",JOptionPane.CLOSED_OPTION) ;
                    dispose();
                }
            }
        });
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	EditProduct frame = new EditProduct();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}