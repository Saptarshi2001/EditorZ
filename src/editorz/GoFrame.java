
package editorz3;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class GoFrame extends JFrame
{
    JTextField gotextfield;
    JLabel go;
    JButton GoButton,cancel;
    textframe txt;
 GoFrame(textframe t)
 {
    txt=t;    
    go=new JLabel("Line number:");
    go.setBounds(20, 15, 120, 20);
    gotextfield=new JTextField(20);
    gotextfield.setBounds(100, 15, 180, 25);
    
    GoButton=new JButton("Go");
    GoButton.setBounds(20, 50, 80, 25);
    cancel=new JButton("Cancel");
    cancel.setBounds(130, 50, 80, 25);
    GoAction();
    cancelaction();
    textfieldtype();
    super.add(go);
    super.add(gotextfield);
    super.add(GoButton);
    super.add(cancel);
 }
 public String FindText()
 {     
     return gotextfield.getText();
 }
 public void GoAction()
 {
    GoButton.addActionListener(event->txt.gototext());
 }
public void cancelaction()
{
     cancel.addActionListener(event->this.setVisible(false));
}
public void textfieldtype()
{
  gotextfield.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
          char msg=e.getKeyChar();
          if(msg<'0'||msg>'9')
          {
              e.setKeyChar('\0');
          }
      }

      @Override
      public void keyPressed(KeyEvent e) {
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }
  });
     
}
}
