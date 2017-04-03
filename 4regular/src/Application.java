import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HP on 18.03.2017.
 */
 class InputException extends Exception{
    public InputException(){

    }
}
public class Application extends JFrame {
    boolean chosenType[]=new boolean[6];
   private String [] types= {"Choose the type","Data","E-mail","Time","Integer","Natural number", "Floating-point number"};
    private JComboBox box =new JComboBox(types);

   private  JPanel panel =new JPanel();
    private JButton button =new JButton("Ok");
   private  JTextField input=new JTextField();
    Circle circle =new Circle();

    public Application (){
        super("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        panel.setLayout(new GridLayout(3,1));
        box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int chosen = box.getSelectedIndex();
                chosenType[chosen-1]=true;
                circle.repaint();
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int chosen = box.getSelectedIndex();
                if (chosen!=0){

                    Validation validation =new Validation(chosen-1,input.getText());
                    circle.setValidation(validation.check());
                    circle.repaint();

                }
            }
        });
        panel.add(box);
        panel.add(input);
        panel.add(button);
        add(panel,BorderLayout.NORTH);


        add(circle);

        setVisible(true);

    }
    public static void main(String[] args) {
        new Application();
        double a =.2e2;
        double b =222.2e2;
        double c = 2.e2;
        double  v = 2.2222e2;
        double f = 2.;
        System.out.println(f);
        System.out.println(a);


    }
}
