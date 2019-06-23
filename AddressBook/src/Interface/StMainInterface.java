package Interface;

import javax.swing.*;
import java.awt.*;

public class StMainInterface {

        private JFrame jFrame;
        private JTextArea textArea;
        private JTextField textField1;
        private JTextField textField2;
        private JTextField textField3;
        private JTextField textField4;
        private JButton button1;
        private JButton button2;
        private JButton button3;
        private JButton button4;


        public JFrame getjFrame() {
            return jFrame;
        }

        public void CreateStMainFrame(){
            jFrame = new JFrame("学生通讯录");
            jFrame.setLocation(400,100);
            jFrame.setSize(1050,800);
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel jPanel = new JPanel();
            jFrame.add(jPanel);
            jPanel.setLayout(null);

            Font fnt = new Font("楷体",Font.PLAIN,22);
            JLabel label1 = new JLabel("学号:");
            label1.setFont(fnt);
            label1.setBounds(40,40,60,30);
            jPanel.add(label1);

            textField1 = new JTextField();
            textField1.setFont(fnt);
            textField1.setBounds(110,40,180,35);
            jPanel.add(textField1);

            JLabel label2 = new JLabel("电话号码:");
            label2.setFont(fnt);
            label2.setBounds(340,40,100,30);
            jPanel.add(label2);

            textField2 = new JTextField();
            textField2.setFont(fnt);
            textField2.setBounds(450,40,220,35);
            jPanel.add(textField2);

            JLabel label3 = new JLabel("姓名:");
            label3.setFont(fnt);
            label3.setBounds(40,130,60,30);
            jPanel.add(label3);

            textField3 = new JTextField();
            textField3.setFont(fnt);
            textField3.setBounds(110,130,180,35);
            jPanel.add(textField3);

            JLabel label4 = new JLabel("家庭地址:");
            label4.setFont(fnt);
            label4.setBounds(340,130,100,30);
            jPanel.add(label4);

            textField4 = new JTextField();
            textField4.setFont(fnt);
            textField4.setBounds(450,130,220,35);
            jPanel.add(textField4);

            button1 = new JButton("增加");
            button1.setBackground(Color.lightGray);
            button1.setFont(fnt);
            button1.setBounds(730,40,100,40);
            jPanel.add(button1);

            button2 = new JButton("修改");
            button2.setBackground(Color.lightGray);
            button2.setFont(fnt);
            button2.setBounds(880,40,100,40);
            jPanel.add(button2);

            button3 = new JButton("删除");
            button3.setBackground(Color.lightGray);
            button3.setFont(fnt);
            button3.setBounds(730,130,100,40);
            jPanel.add(button3);

            button4 = new JButton("查询");
            button4.setBackground(Color.lightGray);
            button4.setFont(fnt);
            button4.setBounds(880,130,100,40);
            jPanel.add(button4);

            JLabel labela = new JLabel("学号");
            labela.setForeground(Color.blue);
            labela.setFont(fnt);
            labela.setBounds(125,210,60,30);
            jPanel.add(labela);

            JLabel labelb = new JLabel("姓名");
            labelb.setForeground(Color.blue);
            labelb.setFont(fnt);
            labelb.setBounds(370,210,60,30);
            jPanel.add(labelb);

            JLabel labelc = new JLabel("电话号码");
            labelc.setForeground(Color.blue);
            labelc.setFont(fnt);
            labelc.setBounds(585,210,100,30);
            jPanel.add(labelc);

            JLabel labeld = new JLabel("家庭住址");
            labeld.setForeground(Color.blue);
            labeld.setFont(fnt);
            labeld.setBounds(820,210,100,30);
            jPanel.add(labeld);

            textArea = new JTextArea();
            textArea.setFont(fnt);
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setBounds(10,250,1025,495);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            jPanel.add(scrollPane);

            jFrame.setVisible(true);
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public JButton getButton3() {
        return button3;
    }

    public JButton getButton4() {
        return button4;
    }

    public static void main(String [] args){
            StMainInterface stMainInterface = new StMainInterface();
            stMainInterface.CreateStMainFrame();
    }
}
