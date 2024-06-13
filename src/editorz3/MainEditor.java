
package editorz3;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MainEditor {

    
    public static void main(String[] args) {
        
        textframe frame = new textframe();
        frame.setVisible(true);
        frame.setSize(new Dimension(650, 350));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
    
}

class textframe extends JFrame {
    
    private textpanel panel;
    private JMenuBar menubar;
    private JMenu filemenu, editmenu, formatmenu, window;
    private JMenuItem New, Open, Save, Saveas, Page_setup, Print, Print_Review,Exit;
    private JMenuItem Cut, Copy, Paste, Undo, Redo, Delete, Find,Find_next, Replace, Go_to,Dateitem, Select_All;
    private JMenuItem Font, Colours, BackGround, Wrap_text;
    private JMenuItem window1, window2, window3;
    private JTextArea editorarea;
    private JDialog dialog;
    private JColorChooser pallet;
    private ActionListener cutactionlistener, copyactionlistener, pasteactionlistener;
    private String cutText, copyText, undotext;
    private Color c;
    private Date date;
    private FileWriter writer;
    private JFileChooser choose;
    private String[]filekeys;
    private String[]editkeys;
    private JScrollPane pane;
    private FindFrame framefind;
    private ReplaceFrame rep;
    private String GetText;
    private GoFrame goframe;
    private FontFrame foframe;
    private File choosefile;
    private int flag=0;
    private int open=0;
    private SimpleDateFormat dateformat;
    @SuppressWarnings("empty-statement")
    textframe() {
        dateformat=new SimpleDateFormat("hh:ss a dd/MM/yyyy");
        date=new Date();
        filekeys=new String[]{"alt N","alt O","alt S","alt A","alt T","alt J","alt P","alt X"};
        editkeys=new String[]{"ctrl U","ctrl R","ctrl X","ctrl C","ctrl V","ctrl D","ctrl N","ctrl F","ctrl H","ctrl G","ctrl M"}; 
        createmenu();
        createfileitem();
        createedititem();
        createformatitem();         
        add_to_filemenu();
        add_to_editmenu();
        add_to_formatmenu();
        makemenubar();
        editorarea = makearea();
        pane=new JScrollPane(editorarea);
        pane.setBounds(30, 40, 150, 200);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel=new textpanel();
        panel.setLayout(new BorderLayout());
        panel.add(pane);
        framefind=new FindFrame(this);
        framefind.getContentPane().setBackground(Color.YELLOW);
        framefind.setSize(new Dimension(390, 200));
        framefind.getContentPane().setLayout(new BorderLayout());
        framefind.setLocationRelativeTo(null);
        rep=new ReplaceFrame(this);
        rep.getContentPane().setBackground(new Color(255,108,104));
        rep.setSize(new Dimension(490, 200));
        rep.getContentPane().setLayout(new BorderLayout());
        rep.setLocationRelativeTo(null);
        goframe=new GoFrame(this);
        goframe.getContentPane().setBackground(Color.YELLOW);
        goframe.setSize(new Dimension(450, 350));
        goframe.getContentPane().setLayout(new BorderLayout());
        goframe.setLocationRelativeTo(null);
        foframe=new FontFrame(this);
        foframe.setTitle("Font");
        foframe.setBackground(Color.DARK_GRAY);
        foframe.setSize(new Dimension(450, 400));
        foframe.getContentPane().setLayout(new BorderLayout());
        foframe.setLocationRelativeTo(null);
      //  GetText = panelfind.GetText();
        fileclick();
        editclick();
        formatclick();
        KeyAccelerators();
        KeyMnemonics();
        EditKeyAccelerators();
        findtext();
        super.add(panel);
       // super.add(framefind);
    }

    private JMenuBar makebar() {
        JMenuBar bar;
        bar = new JMenuBar();
        super.add(bar, BorderLayout.NORTH);
        return bar;
    }

    private JMenu makemenu(String cap) {
        JMenu menu;
        menu = new JMenu(cap);
        super.add(menu);
        return menu;
    }

    private JMenuItem makemenuitem(String menuitem) {
        JMenuItem item;
        item = new JMenuItem("-" + menuitem);        
        super.add(item);
        return item;
    }

    private JTextArea makearea() {
        JTextArea area;
        area = new JTextArea();
        area.setBounds(250, 500, 900,500);
        area.setFont(new Font("Flower", 1, 14));
        return area;
    }

    private void makemenubar() {
        menubar = makebar();
        menubar.add(filemenu);
        menubar.add(editmenu);
        menubar.add(formatmenu);
    }

    public void createmenu() {
        
        filemenu = makemenu("File");
        editmenu = makemenu("Edit");
        formatmenu = makemenu("Format");
    }

    public void createfileitem() {
      
        New = makemenuitem("New");
        Open = makemenuitem("Open");
        Save = makemenuitem("Save");
        Saveas = makemenuitem("Saveas");
        Page_setup = makemenuitem("Page-setup");
        Print_Review = makemenuitem("Print-review");
        Print = makemenuitem("Print");
        Exit = makemenuitem("Exit");
        
    }

    public void createedititem() {
        Undo = makemenuitem("Undo");
        Redo = makemenuitem("Redo");
        Cut = makemenuitem("Cut");
        Copy = makemenuitem("Copy");
        Paste = makemenuitem("Paste");
        Delete = makemenuitem("Delete");
        Find = makemenuitem("Find");
        Replace = makemenuitem("Replace");
        Go_to = makemenuitem("Go-to");
        Dateitem=makemenuitem("Date/Time");
        Select_All = makemenuitem("Select-All");
    }

    public void createformatitem() {
        Font = makemenuitem("Font");
        Colours = makemenuitem("Colours");
        Wrap_text = makemenuitem("Wrap-text");
       
    }
    public void add_to_filemenu() {
           
        filemenu.add(New);
        filemenu.add(Open);
        filemenu.addSeparator();
        filemenu.add(Save);
        filemenu.add(Saveas);
        filemenu.addSeparator();
        filemenu.add(Page_setup);
        filemenu.add(Print_Review);
        filemenu.add(Print);
        filemenu.addSeparator();
        filemenu.add(Exit);
    }

    public void add_to_editmenu() {
        
        editmenu.add(Undo);
        editmenu.add(Redo);
        editmenu.addSeparator();
        editmenu.add(Cut);
        editmenu.add(Copy);
        editmenu.add(Paste);
        editmenu.addSeparator();
        editmenu.add(Delete);
        editmenu.add(Find);
      /*  editmenu.addSeparator();*/
        editmenu.add(Replace);
        editmenu.add(Go_to);
        editmenu.addSeparator();        
        editmenu.add(Dateitem);
        editmenu.add(Select_All);
    }

    public void add_to_formatmenu() {
        formatmenu.add(Font);
        formatmenu.add(Colours);
        formatmenu.addSeparator();
        formatmenu.add(Wrap_text);
    }

    public void fileclick() {
       
        New.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(editorarea.getText().isEmpty()!=true)
                {
                   int exitoption=JOptionPane.showConfirmDialog(null,"DO YOU WANT TO SAVE YOUR FILE?");
                       if(exitoption==0)
                       {
                       try {
                           FileSaver();
                           editorarea.setText("");
                       } catch (IOException ex) {
                           Logger.getLogger(textframe.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       }
                       else
                       {
                           editorarea.setText("");
                       }
                }
                else   
                {
                   editorarea.setText(""); 
                }
                
            }
        });
        
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(editorarea.getText().isEmpty()==true)
               {
                   System.exit(0);
               }
               else if(flag==0)
               {
                   try {
                       int exitoption=JOptionPane.showConfirmDialog(null,"DO YOU WANT TO SAVE YOUR FILE?");
                       if(exitoption==0)
                       {
                       FileSaver();
                       System.exit(0);
                       }
                       else if(exitoption==2)
                       {
                           JOptionPane.getRootFrame().dispose();
                       }
                       else
                       {
                       System.exit(0);
                       }
                   } catch (IOException ex) {
                       Logger.getLogger(textframe.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               else
               {
                   System.exit(0);
               }
                
            }
        });
        Open.addActionListener(event->filechooser());
        Save.addActionListener(event-> {
            try {
                FileSave();
            } catch (IOException ex) {
                Logger.getLogger(textframe.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Saveas.addActionListener(event->{
            try {
                FileSaver();
            } catch (IOException ex) {
                Logger.getLogger(textframe.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void editclick() {
        if (cutactionlistener != null) {
            Cut.removeActionListener(cutactionlistener);
        }
        if (copyactionlistener != null) {
            Copy.removeActionListener(cutactionlistener);
        }
        
        cutactionlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cutText = editorarea.getSelectedText();
                editorarea.replaceSelection("");
                gettext(cutText);
            }
        };
        Cut.addActionListener(cutactionlistener);
        copyactionlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyText = editorarea.getSelectedText();
                gettext(copyText);
            }
        };
        Copy.addActionListener(copyactionlistener);
        

        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int start = editorarea.getSelectionStart();
                int end = editorarea.getSelectionEnd();
                editorarea.replaceRange("", start, end);
            }
        });
       
        Undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undotext = editorarea.getText();
                editorarea.setText("");
            }
        });
        
        Redo.addActionListener(event -> editorarea.setText(undotext));    
        Find.addActionListener(event->framefind.setVisible(true));
       
        Replace.addActionListener(event-> rep.setVisible(true));
        Dateitem.addActionListener(event->editorarea.append(dateformat.format(date)));
        Select_All.addActionListener(event->editorarea.selectAll());
        Go_to.addActionListener(event->goframe.setVisible(true));
    }

    public void gettext(String str) {
        if (pasteactionlistener != null) 
        {
            Paste.removeActionListener(pasteactionlistener);
        }
        pasteactionlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorarea.replaceSelection(str);
            }
        };
        Paste.addActionListener(pasteactionlistener);
    }

    public void formatclick() {

        Font.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                foframe.setVisible(true);
            }
        });

        Wrap_text.addActionListener(event->editorarea.setLineWrap(true));
        Colours.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
               pallet=new JColorChooser();
                // Color col=JColorChooser.showDialog(null,"Show the colours", editorarea.getBackground());
                //  if(col!=null)
                //  {
                //     editorarea.setBackground(col);
                //  }
                 JDialog dialog=JColorChooser.createDialog(null,"PLEASE SELECT A COLOR",false, pallet, new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             Color setcolor=pallet.getColor();
                            editorarea.setBackground(setcolor);
                            
                        }
                    },null);
                  dialog.setVisible(true);                
            }
        });
    }
    public void KeyAccelerators()
    {
        int k = 0;
    for(int i=0;i<11;i++)
    {
        if(i==2||i==5||i==9)
        {
            System.out.println("\n");
        }
        else
        {
        filemenu.getItem(i).setAccelerator(KeyStroke.getKeyStroke(filekeys[k]));
        k++;
        }
        }
    }
    public void EditKeyAccelerators()
    {
      int k = 0; 
      for(int j=0;j<14;j++)
    {
     if(j==2||j==6||j==11)
     {
         System.out.println("\n");
     }
     else
     {
     editmenu.getItem(j).setAccelerator(KeyStroke.getKeyStroke(editkeys[k]));
     k++;
     }
     
    }       
    }
    public void KeyMnemonics()
    {
       
     filemenu.setMnemonic('f');
     editmenu.setMnemonic('e');
     formatmenu.setMnemonic('g');
    
    }
    public void filechooser()
    {
        String text=null;
        JFileChooser choose=new JFileChooser();
        File file=new File(System.getProperty("user.dir"));
        choose.setCurrentDirectory(file);
        FileFilter filter=new FileNameExtensionFilter("Text File (*.txt)","txt");
        choose.addChoosableFileFilter(filter);
        choose.setFileFilter(filter);
        
        int status=choose.showOpenDialog(null);
        if(status==0)
        {
            try
            {
             choosefile=choose.getSelectedFile();
             FileReader reader=new FileReader(choosefile);
             BufferedReader br=new BufferedReader(reader);
             editorarea.setText(" ");
             while((text=br.readLine())!=null)
             {
                 editorarea.append(text); 
                 editorarea.append("\n");
                 
             }
             br.close();
             reader.close();
             open=1;
            }catch(Exception ex)
            {
               ex.getMessage();
            }
        }
    }
    public void FileSaver() throws FileNotFoundException, IOException
    {   
       choose=new JFileChooser();
       choose.setCurrentDirectory(new File(System.getProperty("user.dir")));
       FileFilter filt=new FileNameExtensionFilter("Text File (*.txt)","txt");
       choose.addChoosableFileFilter(filt);
       choose.setFileFilter(filt);
       
       int status=choose.showSaveDialog(null);
       if(status==0)
       {
       File selectedfile=choose.getSelectedFile();
       if(selectedfile.exists())
       {
        int confirm=JOptionPane.showConfirmDialog(null,"Do you want to replace it");
         if(confirm==0)
         {
           String text=editorarea.getText();
           writer = new FileWriter(selectedfile); 
           writer.write(text);
           writer.close();
           return;
         }
       }
       String text=editorarea.getText();
       writer = new FileWriter(selectedfile+".txt"); 
       writer.write(text);
       writer.close();
       flag=1;
       
       }
    }
        
public void FileSave() throws IOException
{       
       choose=new JFileChooser();
       choose.setCurrentDirectory(new File(System.getProperty("user.dir")));
       int save=0;
       if(open==1)
       {
       String text=editorarea.getText();
       writer = new FileWriter(choosefile); 
       writer.write(text);
       writer.close(); 
       save=1; 
       flag=1;
       }
       else if(open==0)
       {
       int status=choose.showSaveDialog(null);
       if(status==0)
       {
       File currentDirectory = choose.getSelectedFile(); 
       if(currentDirectory.exists()==true)
       {
         int confirm=JOptionPane.showConfirmDialog(null,"Do you want to replace it");
         if(confirm==0)
         {
           File file=new File(currentDirectory+".txt"); 
           String text=editorarea.getText();
           writer = new FileWriter(file); 
           writer.write(text);
           writer.close();
           return;
         } 
       }
       File file=new File(currentDirectory+".txt");
       String text=editorarea.getText();
       writer = new FileWriter(file); 
       writer.write(text);
       writer.close();  
       }
       } 
       
              
}
public void findtext()
{ 
    String editortext=null;
    String chosentext = null;
  try
    {      
    editortext=editorarea.getText();
    chosentext=framefind.GetText(); 
    if(editortext.contains(chosentext)==true)
    {  
    int indexOf = editortext.indexOf(chosentext);
    editorarea.select(indexOf,indexOf+chosentext.length());
    }
    else
    {
    JOptionPane.showMessageDialog(null,"NOT FOUND");
    }
    }catch(Exception ex)
    {
     System.out.println(ex.getMessage());
    }  
}

public void replacetext()
{
  try
    {      
    String editortext=editorarea.getText();
    String findtext=rep.FindText();
    String replacetext=rep.ReplaceText();
    if(editortext.contains(findtext)==true)
    {
    int start=editortext.indexOf(findtext);
    editorarea.replaceRange(replacetext, start, start+replacetext.length());  
    }
    else
    {
    JOptionPane.showMessageDialog(null,"No such word found");
    }
    }catch(Exception ex)
    {
    System.out.println(ex.getMessage());
    }  
}
public void gototext()
{
    String linenumber=goframe.FindText();
    int lnumber=Integer.parseInt(linenumber);
    editorarea.setCaretPosition(lnumber);
}
public void getnext()
{
   int cartposition= editorarea.getCaretPosition();
   editorarea.setCaretPosition(cartposition+1);
}
public void fontchooser()
{
    String fontname=foframe.getfont();
    String fontstyle=foframe.getstyle();
    int fontsize=foframe.getsize();
    if(fontstyle=="Bold")
    {
    editorarea.setFont(new Font(fontname,1,fontsize));
    }
    else if(fontstyle=="Italic")
    {
    editorarea.setFont(new Font(fontname,2,fontsize));
    }
     else if(fontstyle=="Plain")
    {
    editorarea.setFont(new Font(fontname,0,fontsize));
    }
    
}
public void settextcolor(Color c)
{
    editorarea.setForeground(c);
    editorarea.setCaretColor(c);
}
}

class textpanel extends JPanel
{
    textpanel()
    {
        
    }
}