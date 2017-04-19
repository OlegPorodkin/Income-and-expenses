package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by OlegPorodkin on 11.04.2017.
 */
public class MainFrame extends JFrame{

    private JFrame aieFrame;
    private JLabel budgetField;
    private JLabel budgetLabel;
    private JLabel chronologyLabel;
    private JButton incomeButton;
    private JButton expensesButton;
    private JTable infoTable;
    private JScrollPane infoTableScrollPane;
    private JPanel buttonPanel;

    private int budget = 0;

    public void buildGUI(){

        //создание основного окна
        aieFrame = new JFrame("Income & Expenses");
        aieFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aieFrame.setSize(400,500);
        aieFrame.setLocationRelativeTo(null);
        aieFrame.setLayout(new GridBagLayout());
        aieFrame.setResizable(false);
        aieFrame.setVisible(true);
        buttonPanel = new JPanel(new GridLayout(1,2));

        //создание и регистрация кнопок и полей

        budgetLabel = new JLabel("БЮДЖЕТ :");
        aieFrame.add(budgetLabel,new GridBagConstraints(0,0,1,1,1.0,1.0,
                GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL, new Insets(4,0,4,4),
                0,40));

        budgetField = new JLabel(String.valueOf(budget));
        aieFrame.add(budgetField,new GridBagConstraints(1,0,1,1,1.0,1.0,
                GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL, new Insets(4,4,4,0),
                0,30));

        chronologyLabel = new JLabel("ХРОНОЛОГИЯ");
        aieFrame.add(chronologyLabel,new GridBagConstraints(0,1,2,1,1.0,1.0,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0),
                0,0));

        CheckInfoTable cit = new CheckInfoTable();
        infoTable = new JTable(cit);
        infoTableScrollPane = new JScrollPane(infoTable);
        infoTableScrollPane.setPreferredSize(new Dimension(392,600));
        aieFrame.add(infoTableScrollPane,new GridBagConstraints(0,2,2,1,1.0,1.0,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,4,0,4),
                0,300));

//******************************************************************************************************************************
//******КНОПКИ**********************************
        incomeButton =new JButton("Доход");
        incomeButton.addActionListener(new IncomeButtonListener());
        buttonPanel.add(incomeButton);

        expensesButton = new JButton("Расход");
        expensesButton.addActionListener(new ExpensesButtonListener());
        buttonPanel.add(expensesButton);

        aieFrame.add(buttonPanel,new GridBagConstraints(0,3,2,1,1.0,1.0,
                GridBagConstraints.SOUTH,GridBagConstraints.HORIZONTAL, new Insets(4,4,4,4),
                0,40));

//**************************************************************************************************************************
//*******Настройки гуишки************************
        settingGUI();

    }

    private class IncomeButtonListener implements ActionListener {

        private JTextField enterIncomeField;
        private JPanel buttonPanel;
        private JLabel enterIncomeLabel;
        private JFrame infoFrame;
        private JButton okButton;
        private JButton cancelButton;

        public void buildIncomeGUI(){

            infoFrame = new JFrame("Доход");
            infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            infoFrame.setLayout(new BorderLayout());
            infoFrame.setSize(250,150);
            infoFrame.setLocationRelativeTo(null);
            infoFrame.setVisible(true);


            buttonPanel = new JPanel(new GridLayout(1,2));

            enterIncomeField =new JTextField("0");
            enterIncomeField.setHorizontalAlignment(JLabel.RIGHT);
            enterIncomeField.selectAll();

            enterIncomeField.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if ( ((c < '0') || (c > '9'))) {
                        e.consume();  // игнорим введенные буквы и пробел
                    }
                }
            });

            enterIncomeLabel = new JLabel("Введите сумму : ");

            infoFrame.add(enterIncomeField,BorderLayout.CENTER);
            enterIncomeField.setFont(new Font("Arial",Font.PLAIN,45));

            okButton = new JButton("OK");
            buttonPanel.add(okButton);
            okButton.addActionListener(new OkButtonListener());

            cancelButton = new JButton("Cancel");
            buttonPanel.add(cancelButton);
            cancelButton.addActionListener(new CancelButtonListener());

            infoFrame.add(enterIncomeLabel,BorderLayout.NORTH);
            infoFrame.add(buttonPanel,BorderLayout.SOUTH);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            buildIncomeGUI();
        }

        public class OkButtonListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(enterIncomeField.getText());
                int budget = Integer.parseInt(budgetField.getText());
                int expence = budget + value;
                budgetField.setText(String.valueOf(expence));
                infoFrame.setVisible(false);
            }
        }

        public class CancelButtonListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                infoFrame.setVisible(false);
            }
        }
    }

    private class ExpensesButtonListener implements ActionListener {

        private JFrame infoFrame;
        private JButton okButton;
        private JButton cancelButton;
        private JPanel buttonPanel;
        private JTextField enterExpenceField;
        private JLabel enterExpenceLabel;

        public void buildIncomeGUI(){

            infoFrame = new JFrame("Расход");
            infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            infoFrame.setLayout(new BorderLayout());
            infoFrame.setSize(250,150);
            infoFrame.setLocationRelativeTo(null);
            infoFrame.setVisible(true);


            buttonPanel = new JPanel(new GridLayout(1,2));

            enterExpenceField =new JTextField("0");
            enterExpenceField.setHorizontalAlignment(JLabel.RIGHT);
            enterExpenceField.selectAll();

            enterExpenceField.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if ( ((c < '0') || (c > '9'))) {
                        e.consume();  // игнорим введенные буквы и пробел
                    }
                }
            });

            infoFrame.add(enterExpenceField,BorderLayout.CENTER);
            enterExpenceField.setFont(new Font("Arial",Font.PLAIN,45));

            enterExpenceLabel = new JLabel("Введите сумму : ");

            okButton = new JButton("OK");
            buttonPanel.add(okButton);
            okButton.addActionListener(new OkButtonListener());

            cancelButton = new JButton("Cancel");
            buttonPanel.add(cancelButton);
            cancelButton.addActionListener(new CancelButtonListener());

            infoFrame.add(enterExpenceLabel,BorderLayout.NORTH);
            infoFrame.add(buttonPanel,BorderLayout.SOUTH);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            buildIncomeGUI();
        }

        //Кнопка Ок
        public class OkButtonListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(enterExpenceField.getText());
                int budget = Integer.parseInt(budgetField.getText());
                int expence = budget - value;
                budgetField.setText(String.valueOf(expence));
                infoFrame.setVisible(false);
            }
        }

        public class CancelButtonListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                infoFrame.setVisible(false);
            }
        }
    }

    private void settingGUI(){
//      Настройки внешнего вида

        budgetLabel.setFont(new Font("Arial",Font.PLAIN,30));
        budgetField.setFont(new Font("Arial", Font.PLAIN, 30));
        incomeButton.setFont(new Font("Arial", Font.PLAIN, 30));
        expensesButton.setFont(new Font("Arial", Font.PLAIN, 30));
        incomeButton.setMinimumSize(new Dimension(1,5));
        expensesButton.setMinimumSize(new Dimension(1,5));
        budgetField.setHorizontalAlignment(JLabel.RIGHT);
        chronologyLabel.setHorizontalAlignment(JLabel.CENTER);
        chronologyLabel.setForeground(new Color(102, 102, 102));
        incomeButton.setForeground(new Color(14,180, 21));
        expensesButton.setForeground(new Color(150,0,0));
        budgetField.setBackground(new Color(255,255,255));

    }

}
