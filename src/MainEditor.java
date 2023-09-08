/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editorz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author user
 */
public class MainEditor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        textframe frame = new textframe();
        frame.setVisible(true);
        frame.setSize(new Dimension(400, 400));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
    
}

class textframe extends JFrame {
    
    private textpanel panel;
    private JMenuBar menubar;
    private JMenu filemenu, editmenu, formatmenu, window;
    private JMenuItem New, Open, Save, Saveas, Page_setup, Print, Print_Review,Exit;
    private JMenuItem Cut, Copy, Paste, Undo, Redo, Delete, Find, Find_next, Replace, Go_to,Dateitem, Select_All;
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
    int flag=0;
    private SimpleDateFormat dateformat;
    @SuppressWarnings("empty-statement")
    textframe() {
        dateformat=new SimpleDateFormat("hh:ss a dd/MM/yyyy");
        date=new Date();
        filekeys=new String[]{"alt N","alt O","alt S","alt A","alt T","alt J","alt P","alt X"};
        editkeys=new String[]{"ctrl U","ctrl R","ctrl X","ctrl C","ctrl V","ctrl D","ctrl F","ctrl H","ctrl R","ctrl G","ctrl M","ctrl P"}; 
        createmenu();
        createfileitem();
        createedititem();
        createformatitem();         
        createwindowitem();
        add_to_filemenu();
        add_to_editmenu();
        add_to_formatmenu();
        add_to_windowmenu();
        makemenubar();
        editorarea = makearea();
        pane=new JScrollPane(editorarea);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel=new textpanel();
        panel.setLayout(new BorderLayout());
        panel.add(pane);
        
        fileclick();
        editclick();
        formatclick();
        KeyAccelerators();
        KeyMnemonics();
        EditKeyAccelerators();
        
        super.add(panel);
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
        area.setBounds(250, 500, 900, 500);
        area.setFont(new Font("Flower", 1, 14));
        return area;
    }

    private void makemenubar() {
        menubar = makebar();
        menubar.add(filemenu);
        menubar.add(editmenu);
        menubar.add(formatmenu);
        menubar.add(window);
    }

    public void createmenu() {
        
        filemenu = makemenu("File");
        editmenu = makemenu("Edit");
        formatmenu = makemenu("Format");
        window = makemenu("Window");

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
        Find_next = makemenuitem("Find-next");
        Replace = makemenuitem("Replace");
        Go_to = makemenuitem("Go-to");
        Dateitem=makemenuitem("Date/Time");
        Select_All = makemenuitem("Select-All");
    }

    public void createformatitem() {
        Font = makemenuitem("Font");
        Colours = makemenuitem("Colours");
        BackGround = makemenuitem("BackGround");
        Wrap_text = makemenuitem("Wrap-text");
       
    }

    public void createwindowitem() {
        window1 = makemenuitem("Window1");
        window2 = makemenuitem("Window2");
        window3 = makemenuitem("Window3");

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
        editmenu.add(Find_next);
        editmenu.addSeparator();
        editmenu.add(Replace);
        editmenu.add(Go_to);
        editmenu.addSeparator();        
        editmenu.add(Dateitem);
        editmenu.add(Select_All);
    }

    public void add_to_formatmenu() {
        formatmenu.add(Font);
        formatmenu.add(Colours);
        formatmenu.add(BackGround);
        formatmenu.addSeparator();
        formatmenu.add(Wrap_text);
    }

    public void add_to_windowmenu() {
        window.add(window1);
        window.add(window2);
        window.addSeparator();
        window.add(window3);
    }

    public void fileclick() {
        New.addActionListener(event -> editorarea.setText(""));
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(flag==0)
               {
                   try {
                       FileSaver();
                       System.exit(0);
                   } catch (IOException ex) {
                       Logger.getLogger(textframe.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            }
        });
        Open.addActionListener(event->filechooser());
        Save.addActionListener(event-> {
            try {
                FileSaver();
            } catch (IOException ex) {
                Logger.getLogger(textframe.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Saveas.addActionListener(event->{
            try {
                FileSaveAs();
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
        Dateitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                editorarea.append(dateformat.format(date));
            }
        });
        Select_All.addActionListener(event->editorarea.selectAll());
    }

    public void gettext(String str) {
        if (pasteactionlistener != null) {
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
            public void actionPerformed(ActionEvent e) {
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
      for(int j=0;j<16;j++)
    {
     if(j==2||j==6||j==10||j==13)
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
     window.setMnemonic('w');
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
             FileReader reader=new FileReader(choose.getSelectedFile());
             BufferedReader br=new BufferedReader(reader);
             while((text=br.readLine())!=null)
             {
                 editorarea.append(text); 
                 editorarea.append("\n");
             }
             br.close();
            }catch(Exception ex)
            {
                
            }
        }
    }
    public void FileSaver() throws FileNotFoundException, IOException
    {   
       choose=new JFileChooser();
       choose.setCurrentDirectory(new File(System.getProperty("user.dir")));
       int status=choose.showSaveDialog(null);
       if(status==0)
       {
       File selectedfile=choose.getSelectedFile();
       String text=editorarea.getText();
       int choice=JOptionPane.showConfirmDialog(null,"Do you want to replace "+selectedfile);
       if(choice==0)
       {
       writer = new FileWriter(selectedfile); 
       writer.write(text);
       writer.close();
       flag=1;
       }
       }
    }
        
public void FileSaveAs() throws IOException
{
       choose=new JFileChooser();
       choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       
       int status=choose.showOpenDialog(null);
       if(status==0)
       {
       File currentDirectory = choose.getSelectedFile();
       String filename=JOptionPane.showInputDialog("Enter file name");
       File file=new File(currentDirectory+"\\"+filename+".txt");
       String text=editorarea.getText();
       writer = new FileWriter(file); 
       writer.write(text);
       writer.close();
       }  
}

}

class textpanel extends JPanel
{
    textpanel()
    {
        
    }
}

