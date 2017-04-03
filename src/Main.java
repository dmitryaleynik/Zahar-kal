import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HP on 19.03.2017.
 */
public class Main extends JFrame {

    JTextArea text = new JTextArea(5,20);
    JScrollPane scrollPane = new JScrollPane(text);
     JPanel panel = new JPanel();
    Pattern pattern =Pattern.compile("(0[1-9]|1[0-9]|2[0-9]|3[01])[./-](0[1-9]|1[012])[./-][0-9]{4}");



    JButton button =new JButton("Ok");
    DefaultListModel<String> listModel =new DefaultListModel();
    JList list =new JList(listModel);



    public Main (){
        text.setEditable(false);
        text.setText("22.04.1232 3213 d 2 df fa sd 21/21/2014");
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Matcher matcher = pattern.matcher(text.getText());
                while (matcher.find()){
                    listModel.addElement(matcher.group());
                }
            }
        });

        text.setPreferredSize(new Dimension(100,100));
        panel.add( new JPanel(),BorderLayout.NORTH);
        panel.add(text,BorderLayout.CENTER);
        panel.add(new JPanel(), BorderLayout.SOUTH);
        setLayout(new GridLayout(3,1));
        add(panel);
        add(button);

        add(list);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Main();
    }
}
