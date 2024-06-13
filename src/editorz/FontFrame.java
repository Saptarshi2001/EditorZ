package editorz3;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.BOLD;
import static java.awt.Font.ITALIC;
import static java.awt.Font.PLAIN;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.Document;

public class FontFrame extends JFrame
{
   private JList name,style,size;
   private JButton ok,cancel;
   private String[]fontnames,fontstyles;
   private Integer[] fontsizes;
   private JScrollPane namepane,stylepane,sizepane;
   private textframe text; 
   private JButton colorchosr;
    FontFrame(textframe txt)
    {
      text=txt;
      fontnames=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
      fontstyles=new String[]{"Bold","Plain","Italic"};
      fontsizes=new Integer[]{18,20,24,26,28,36,48,64,80};
      name=makelist(10,10,180,200);
      name.setListData(fontnames);
      style=makelist(200,10,120,200);
      style.setListData(fontstyles);
      size=makelist(340,10,80,200);
      size.setListData(fontsizes);
      namepane=new JScrollPane(name);
      namepane.setBounds(10,10,180,200);
      stylepane=new JScrollPane(style);
      stylepane.setBounds(200,10,120,200);
      stylepane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      sizepane=new JScrollPane(size);
      sizepane.setBounds(340,10,80,200);
      sizepane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      ok=makebutton("Ok",20,240,90,30);
      cancel=makebutton("Cancel",120,240,90,30);
      colorchosr=makebutton("Color choose",230,240,130,30);
      buttonaction();
      super.add(namepane);
      super.add(stylepane);
      super.add(sizepane);
    }
    public JList makelist(int x,int y,int w,int h)
    {
      JList list=new JList();
      list.setBounds(x, y, w, h);
      return list;
    }
    public JButton makebutton(String name,int x,int y,int w,int h)
    {
      JButton button=new JButton(name);
      button.setFont(new Font("Verdana",1,12));
      button.setBounds(x, y, w, h);
      button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              if(e.getActionCommand()=="Cancel")
              {
                setVisible(false);
              }
          }
      });
      super.add(button);
      return button;
    }
    public String getfont()
    {
        return (String) name.getSelectedValue();
    }
    public String getstyle()
    {
        return (String) style.getSelectedValue();
    }
     public int getsize()
    {
        return  (int) size.getSelectedValue();
    }
     public void buttonaction()
     {
        
     ok.addActionListener(event->text.fontchooser());
     colorchosr.addActionListener(event->changecolor());
     }
     
     public void changecolor()
     {
       JColorChooser pallet = new JColorChooser();
                
                 JDialog dialog=JColorChooser.createDialog(null,"PLEASE SELECT A COLOR",false, pallet, new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             Color setcolor=pallet.getColor();
                             text.settextcolor(setcolor);    
                        }
                    },null);
                  dialog.setVisible(true);  
     }
}
