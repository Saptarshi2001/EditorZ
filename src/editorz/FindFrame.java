
package editorz3;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FindFrame extends JFrame
{
    JTextField findtext;
    JLabel find;
    JButton findbutton,cancel;
    textframe txt;
    /*JCheckBox match;*/
 FindFrame(textframe t)
 {
    txt=t;    
    find=new JLabel("Find what you want:");
    findtext=new JTextField(20);
    findbutton=new JButton("Find");
    cancel=new JButton("Cancel");
    find.setBounds(20, 15, 120, 20);
    findtext.setBounds(150, 15, 180, 25);
    findbutton.setBounds(20, 50, 80, 25);
    cancel.setBounds(130, 50, 80, 25);
    findaction();
    cancelaction();
    super.add(find);
    super.add(findtext);
    super.add(findbutton);
    super.add(cancel);
    
 }
 public String GetText()
 {
     return findtext.getText();
 }
 public void findaction()
 {
    findbutton.addActionListener(event->txt.findtext());
}
 public void cancelaction()
 {
     cancel.addActionListener(event->this.setVisible(false));
}
 
}    


