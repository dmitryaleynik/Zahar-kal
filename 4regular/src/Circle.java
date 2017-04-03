import com.sun.java.swing.plaf.windows.WindowsTreeUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HP on 19.03.2017.
 */
public class Circle extends JPanel {
    private boolean validation;
    public void setValidation (boolean validation){
        this.validation=validation;
    }
    public  boolean getValidation(){
        return validation;
    }
    public Circle (){
        super();
    }

    public void paint ( Graphics graphics){
        super.paint(graphics);
        int size=Math.min(getWidth(),getHeight());
        int radius=size/4;


        if (validation==true){
            graphics.setColor(Color.green);
        }
        else graphics.setColor(Color.red);
 graphics.fillOval((int)size/2-radius,(int)size/2-radius,2*radius,2*radius);



    }

}
