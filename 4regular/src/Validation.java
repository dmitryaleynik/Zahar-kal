import javafx.application.Platform;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HP on 19.03.2017.
 */
public class Validation {
    private String  []regulars= {
            "((0[1-9]|1[0-9]|2[0-8])[./-](02)[./-][0-9]{4})" +
                    "|((0[1-9]|1[0-9]|2[0-9]|3[01])[./-]((0[13579])|11)[./-][0-9]{4})" +
                    "|((0[1-9]|1[0-9]|2[0-9]|(30))[./-]((0[468])|(1[02]))[./-][0-9]{4})",
            "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$",
            "^[0-2][0-3]:[0-5][0-9]$",
    "(-?\\d+)"
            ,"[1-9][0-9]*"
            ,"([-]?\\d+e-?\\d+)|([-]?\\d*[.]\\d+(e-?\\d+)?)|([-]?\\d+[.]\\d*(e-?\\d+)?)"};
    private int index;
    private String expression;
    public Validation(int index , String input){
        this.index=index;
        this.expression =input;
    }
    boolean check(){
        Pattern pattern=Pattern.compile(regulars[index]);
        Matcher matcher = pattern.matcher(expression);
        return matcher.matches();
    }
}
