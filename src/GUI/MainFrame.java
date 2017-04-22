package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MainFrame extends JFrame {

    private JLabel budgetField;
    private JLabel budgetLabel;
    private JLabel chronologyLabel;
    private JButton incomeButton;
    private JButton expensesButton;
    private JTable infoTable;
    private JScrollPane infoTableScrollPane;
    private JPanel buttonPanel;
    CheckInfoTable cit;

    private int budget = 0;

    public void buildGUI(){

        //создание основного окна
        JFrame aieFrame = new JFrame("Income & Expenses");
        aieFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        cit = new CheckInfoTable();
        infoTable = new JTable(cit);
        infoTableScrollPane = new JScrollPane(infoTable);
        infoTableScrollPane.setPreferredSize(new Dimension(392,600));
        aieFrame.add(infoTableScrollPane,new GridBagConstraints(0,2,2,1,1.0,1.0,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,4,0,4),
                0,300));
//        TEST
//        String []data = new String[4];
//        data[0]="1";
//        data[1]="1";
//        data[2]="1";
//        data[3]="1";
//        cit.addDate(data);
//

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

        private JFrame infoFrame;
        private JPanel buttonPanel;
        private JPanel infoPanel;
        private JLabel enterIncomeLabel;
        private JLabel enterCommitLabel;
        private JButton okButton;
        private JButton cancelButton;
        private JTextField enterIncomeField;
        private JTextField commitTextField;

        private void buildIncomeGUI(){

            infoFrame = new JFrame("Доход");
            infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            infoFrame.setLayout(new BorderLayout());
            infoFrame.setSize(250,150);
            infoFrame.setLocationRelativeTo(null);
            infoFrame.setVisible(true);

            buttonPanel = new JPanel(new GridLayout(1,2));
            infoPanel = new JPanel(new GridLayout(4,1));

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

            commitTextField = new JTextField();

            enterIncomeLabel = new JLabel("Введите сумму :");
            enterCommitLabel = new JLabel("Комментарии :");

            infoPanel.add(enterIncomeLabel);
            infoPanel.add(enterIncomeField);
            infoPanel.add(enterCommitLabel);
            infoPanel.add(commitTextField);

            okButton = new JButton("OK");
            buttonPanel.add(okButton);
            okButton.addActionListener(new OkButtonListener());

            cancelButton = new JButton("Cancel");
            buttonPanel.add(cancelButton);
            cancelButton.addActionListener(new CancelButtonListener());

            infoFrame.add(infoPanel,BorderLayout.CENTER);
            infoFrame.add(buttonPanel,BorderLayout.SOUTH);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (infoFrame == null) {
                buildIncomeGUI();
            }else{
                infoFrame.setVisible(true);
                enterIncomeField.setText("");
            }
        }

        public class OkButtonListener implements ActionListener{

            String [] str = new String[4];

            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(enterIncomeField.getText());
                int budget = Integer.parseInt(budgetField.getText());
                int expence = budget + value;
                budgetField.setText(String.valueOf(expence));
                str[0] = "1";
                str[1] = "2";
                str[2] = enterIncomeField.getText();
                str[3] = "dfssds";
                cit.addDate(str);
                System.out.println(cit.getRowCount());
                System.out.println(cit.getColumnCount());
                infoFrame.setVisible(false);
                infoFrame = null;
            }
        }

        public class CancelButtonListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                infoFrame.setVisible(false);
                infoFrame = null;
            }
        }
    }

    private class ExpensesButtonListener implements ActionListener {

        private JFrame infoFrame;
        private JPanel buttonPanel;
        private JPanel infoPanel;
        private JLabel enterExpenceLabel;
        private JLabel enterCommitLabel;
        private JButton okButton;
        private JButton cancelButton;
        private JTextField enterExpenceField;
        private JTextField commitTextField;


        private void buildIncomeGUI(){

            infoFrame = new JFrame("Расход");
            infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            infoFrame.setLayout(new BorderLayout());
            infoFrame.setSize(250,150);
            infoFrame.setLocationRelativeTo(null);
            infoFrame.setVisible(true);

            buttonPanel = new JPanel(new GridLayout(1,2));
            infoPanel = new JPanel(new GridLayout(4,1));

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

            commitTextField = new JTextField();

            enterExpenceLabel = new JLabel("Введите сумму : ");
            enterCommitLabel = new JLabel("Комментарии :");

            infoPanel.add(enterExpenceLabel);
            infoPanel.add(enterExpenceField);
            infoPanel.add(enterCommitLabel);
            infoPanel.add(commitTextField);

            okButton = new JButton("OK");
            buttonPanel.add(okButton);
            okButton.addActionListener(new OkButtonListener());

            cancelButton = new JButton("Cancel");
            buttonPanel.add(cancelButton);
            cancelButton.addActionListener(new CancelButtonListener());

            infoFrame.add(infoPanel,BorderLayout.NORTH);
            infoFrame.add(buttonPanel,BorderLayout.SOUTH);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            buildIncomeGUI();
        }

        //Кнопка Ок
        public class OkButtonListener implements ActionListener{

            String [] str = new String[4];

            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(enterExpenceField.getText());
                int budget = Integer.parseInt(budgetField.getText());
                int expence = budget - value;
                budgetField.setText(String.valueOf(expence));
                str[0] = "1";
                str[1] = "2";
                str[2] = "sdffddf";
                str[3] = "dfsdfsfs";
                cit.addDate(str);
                infoFrame.setVisible(false);
                infoFrame = null;
            }
        }

        public class CancelButtonListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                infoFrame.setVisible(false);
                infoFrame = null;
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
