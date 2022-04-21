import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;


public class Notepad extends JFrame {

 private JPanel contentPane;
 JTextArea editortxt;

 /**
  * Launch the application.
  */
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     Notepad frame = new Notepad();
     frame.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }

 /**
  * Create the frame.
  */
 
 void open() {
  
  JFileChooser fc = new JFileChooser();
  
  int i =fc.showOpenDialog(this);
  
  System.out.println(i);
  
  if(i ==fc.APPROVE_OPTION) {
   
    File f =fc.getSelectedFile();
    
    FileInputStream fis;
    
    try {
     
     fis = new FileInputStream(f);
     
     byte b[] = new byte[fis.available()];
     
     fis.read(b);
     
     String str = new String(b);
     
     editortxt.setText(str);;
     
     setTitle(f.getAbsolutePath());
     
    } catch (FileNotFoundException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
    } catch (IOException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
    }
    
    
   
  }
 }
 
 void saveas() {
  
  JFileChooser fc = new JFileChooser();
  
  int i =fc.showSaveDialog(this);
  
  System.out.println(i);
  
  if(i ==fc.APPROVE_OPTION) {
   
    File f =fc.getSelectedFile();
    PrintStream ps;
    try {
     
     ps = new PrintStream(f);
     ps.print(editortxt.getText());
     ps.close();
     
     setTitle(f.getAbsolutePath());
     
    } catch (FileNotFoundException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
    }
    
  }
 }
 public Notepad() {
  setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg"));
  setTitle("Untitled");
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(100, 100, 450, 300);
  
  int width = Toolkit.getDefaultToolkit().getScreenSize().width;
  int height = Toolkit.getDefaultToolkit().getScreenSize().height-50;
  
  setSize(width,height);
  setLocation(0,0);
  
  JMenuBar menuBar = new JMenuBar();
  setJMenuBar(menuBar);
  
  JMenu mnFile = new JMenu("File");
  mnFile.setMnemonic('F');
  menuBar.add(mnFile);
  
  JMenuItem mntmNew = new JMenuItem("New");
  mntmNew.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    setTitle("Untitled");
    editortxt.setText("");
   }
  });
  mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
  mntmNew.setMnemonic('N');
  
  mnFile.add(mntmNew);
  
  JMenuItem mntmOpen = new JMenuItem("Open...");
  mntmOpen.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    
    open();
    
   }
  });
  mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
  mnFile.add(mntmOpen);
  
  JMenuItem mntmSave = new JMenuItem("Save");
  mntmSave.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
     
   }
  });
 
   
  mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
  mnFile.add(mntmSave);
  
  JMenuItem mntmSaveAs = new JMenuItem("Save As...");
  mntmSaveAs.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    saveas();
   }
  });
  mnFile.add(mntmSaveAs);
  
  JSeparator separator = new JSeparator();
  mnFile.add(separator);
  
  JMenuItem mntmExit = new JMenuItem("Exit");
  mntmExit.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
   
    System.exit(0);
   }
  });
  mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
  mnFile.add(mntmExit);
  
  JMenu mnEdit = new JMenu("Edit");
  menuBar.add(mnEdit);
  
  JMenuItem mntmCut = new JMenuItem("Cut");
  mntmCut.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    
    editortxt.cut();
   }
  });
  mnEdit.add(mntmCut);
  
  JMenuItem mntmCopy = new JMenuItem("Copy");
  mntmCopy.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    
    editortxt.copy();
   }
  });
  mnEdit.add(mntmCopy);
  
  JMenuItem mntmPaste = new JMenuItem("Paste");
  mntmPaste.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    editortxt.paste();
   }
  });
  mnEdit.add(mntmPaste);
  
  JSeparator separator_1 = new JSeparator();
  mnEdit.add(separator_1);
  
  JMenuItem mntmSelectAll = new JMenuItem("Select All");
  mntmSelectAll.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    editortxt.selectAll();
   }
  });
  mnEdit.add(mntmSelectAll);
  contentPane = new JPanel();
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  contentPane.setLayout(new BorderLayout(0, 0));
  setContentPane(contentPane);
  
  JScrollPane scrollPane = new JScrollPane();
  contentPane.add(scrollPane, BorderLayout.CENTER);
  
 editortxt = new JTextArea();
  scrollPane.setViewportView(editortxt);
 }

  
 
 

}
