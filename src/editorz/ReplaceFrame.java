
package editorz3;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ReplaceFrame extends JFrame
{
    JTextField findtext,replacetext;
    JLabel find,replace;
    JButton replacebutton,cancel;
    textframe txt;
 ReplaceFrame(textframe t)
 {
    txt=t;    
    find=new JLabel("Find what:");
    findtext=new JTextField(20);
    replace=new JLabel("Replace with:");
    replacetext=new JTextField(20);
    replacebutton=new JButton("Replace");
    cancel=new JButton("Cancel");
    find.setBounds(20, 15, 120, 20);
    findtext.setBounds(150, 15, 180, 25);
    replace.setBounds(20, 55, 120, 20);
    replacetext.setBounds(150, 55, 180, 25);
    replacebutton.setBounds(20, 90, 80, 25);
    cancel.setBounds(130, 90, 80, 25);
    findaction();
    cancelaction();
    super.add(find);
    super.add(findtext);
    super.add(replace);
    super.add(replacetext);
    super.add(replacebutton);
    super.add(cancel);
 }
 public String FindText()
 {
     return findtext.getText();
 }
 public String ReplaceText()
 {
     return replacetext.getText();
 }
 public void findaction()
 {
    replacebutton.addActionListener(event->txt.replacetext());
}
 public void cancelaction()
 {
     cancel.addActionListener(event->this.setVisible(false));
}
 
}
