package Interface;

import javax.swing.*;
import java.awt.*;

public class ErrorInterface {

    JLabel jLabel = new JLabel();

    public void CreateFrame() {
        JFrame jFrame = new JFrame("提示");
        //jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(300,300);
        jFrame.setLocation(50,300);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jFrame.add(jPanel);

        Font fnt = new Font("楷体",Font.PLAIN,20);
        jLabel.setBounds(80,100,200,30);
        jLabel.setForeground(Color.blue);
        jLabel.setFont(fnt);

        jPanel.add(jLabel);

        jFrame.setVisible(true);
    }

    public JLabel getjLabel() {
        return jLabel;
    }
}
