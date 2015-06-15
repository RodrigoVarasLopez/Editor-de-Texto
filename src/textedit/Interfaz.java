
package textedit;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Interfaz
    extends JFrame
    implements ActionListener, FocusListener
    
{
    public Interfaz(){
        this.initInterface();
    }
    String nombre=" ";
    JMenuBar barramenu= new JMenuBar();
   
    
    JMenu archivo= new JMenu("Archivo");
    JMenuItem nuevo=new JMenuItem("Nuevo");
    JMenuItem abrir=new JMenuItem("Abrir");
    JMenuItem guardar=new JMenuItem("Guardar");
    JMenuItem cerrar=new JMenuItem("Cerrar");
    
    JMenu edicion= new JMenu("Edicion");
    JMenuItem cortar=new JMenuItem("Cortar");
    JMenuItem copiar=new JMenuItem("Copiar");
    JMenuItem pegar=new JMenuItem("Pegar");
    
    JMenu ayuda = new JMenu("Ayuda");
    JMenuItem ayudatext = new JMenuItem("Ayuda de TextEdit");
    
    JToolBar barraformato= new JToolBar("Formato");
    
    JMenu fuente= new JMenu("Fuente");
    ButtonGroup grupofuentes= new ButtonGroup();
    JRadioButton arial= new JRadioButton("Arial");
    JRadioButton tahoma= new JRadioButton("Tahoma");
    JRadioButton times= new JRadioButton("Times new Roman");
    
    JToggleButton negrita = new JToggleButton("Negrita");
    JToggleButton cursiva = new JToggleButton("Cursiva");
    
    
    JTextArea textarea= new JTextArea();
    JTextArea textarea2 = new JTextArea();
    JScrollPane scrollPane;
    JDesktopPane d = new JDesktopPane();
    JInternalFrame frmin =new JInternalFrame();
    
    
    private void initInterface()
    {
        setLayout(null);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        nuevo.addActionListener(this);
        abrir.addActionListener(this);
        guardar.addActionListener(this);
        cerrar.addActionListener(this);
        
        archivo.add(nuevo);
        archivo.add(abrir);
        archivo.add(guardar);
        archivo.add(cerrar);
        barramenu.add(archivo);
 
        cortar.addActionListener(this);
        copiar.addActionListener(this);
        pegar.addActionListener(this);
        
        edicion.add(cortar);
        edicion.add(copiar);
        edicion.add(pegar);
        barramenu.add(edicion);
        
        KeyStroke ctrlX =KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK);
        cortar.setAccelerator(ctrlX);
        KeyStroke ctrlC =KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK);
        copiar.setAccelerator(ctrlC);
        KeyStroke ctrlP =KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.CTRL_MASK);
        pegar.setAccelerator(ctrlP);
        

        barraformato.setFloatable(false);
        
        negrita.addActionListener(this);
        cursiva.addActionListener(this);
        negrita.setToolTipText("Negrita");
        //negrita.setActionCommand("Negrita");
        cursiva.setToolTipText("Cursiva");
       // cursiva.setActionCommand("Cursiva");
        
  
        
        barraformato.add(negrita);
        barraformato.add(cursiva);
        //barraformato.add(negrita2);
       // barraformato.add(negrita3);
        
        tahoma.addActionListener(this);
        times.addActionListener(this);
        arial.addActionListener(this);
        
        grupofuentes.add(arial);
        grupofuentes.add(tahoma);
        grupofuentes.add(times);
        tahoma.setSelected(true);
        fuente.add(arial);
        fuente.add(times);
        fuente.add(tahoma);
        barramenu.add(fuente);
        
        ayudatext.addActionListener(this);
        ayuda.add(ayudatext);
        barramenu.add(ayuda);
     
        
       //textarea = new JTextArea();
       // scrollPane = new JScrollPane(textarea);
        
        textarea.setLocation(0,60);
        textarea.setSize(300, 200);
        
        //scrollPane.setLocation(0,60);
        //scrollPane.setSize(620, 520);
        
        barramenu.setLocation(0, 0);
        barramenu.setSize(640, 20);
        add(barramenu);
        
        
        barraformato.setLocation(0,20);
        barraformato.setSize(640, 20);
        add(barraformato);
        d.setSize(640,540);
        d.setLocation(0,40);
        
       
       frmin.setSize(300,200);
       frmin.setVisible(true);
       frmin.setResizable(true);
       frmin.setMaximizable(true);
       
       frmin.add(textarea);
       frmin.addFocusListener(this);
       
       d.add(frmin);
       add(d);
       this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //getContentPane().add(scrollPane);
        //pack();
        
        setSize(620,580);
        setVisible(true);
        setTitle("TextEdit");
  
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cerrar){
         System.exit(0);   
        }
        if(e.getSource()==nuevo){
           
           textarea = new JTextArea();
           textarea.setLocation(0,60);
           textarea.setSize(300, 200);
           JInternalFrame frmin1 =new JInternalFrame();
           frmin1.setSize(300,200);
           frmin1.setVisible(true);
           frmin1.setResizable(true);
           frmin1.setMaximizable(true);
           frmin1.add(textarea);
           d.add(frmin1);
           this.setExtendedState(JFrame.MAXIMIZED_BOTH);
           
        }
        
        if(e.getSource()==abrir){
            JFileChooser chooser= new JFileChooser();
            chooser.setApproveButtonText("Abrir txt");
            chooser.showOpenDialog(null);
            File archivo= chooser.getSelectedFile();
            try{
                BufferedReader reader= new BufferedReader(new FileReader(archivo));
                String linea= reader.readLine();
                while(linea!=null){
                    textarea.append(linea+"\n");
                    linea= reader.readLine();
                }
 
            }catch (Exception ex) {
 
            }
        }
        if(e.getSource()==guardar){
            try{
 
                JFileChooser fc=new JFileChooser(System.getProperty("user.dir"));
                fc.showSaveDialog(this);
                File Guardar =fc.getSelectedFile();
                if(Guardar !=null){
                    FileWriter  Guardx=new FileWriter(Guardar);
                    Guardx.write(textarea.getText());
                    Guardx.close();
                }
            }
            catch(IOException ee){
                System.out.println(ee);
            }
        }
        if(e.getSource()==cortar){
            System.out.println("Cortar");
            textarea.cut();
        }
        if(e.getSource()==copiar){
            textarea.copy();
        }
        if(e.getSource()==pegar){
            textarea.paste();
        }
        
        if(e.getSource()==ayudatext){
            JDialog  dialog;
            JFrame credi;
            credi=new JFrame();
            dialog = new JDialog(credi, "", true);
            dialog.setTitle("Acerca de");
            JTextArea label = new JTextArea("Este Programa fue Diseñado por :\n" +
                    " Rodrigo Varas López\n" +
                    "Contacto: rodrigovaraslopez@gmail.com\n" +
            "FreeWord 0.1\n");
            label.setEditable(false);
            Container contentPane = dialog.getContentPane();
            contentPane.add(label, BorderLayout.CENTER);
            dialog.setSize(new Dimension(300, 150));
            dialog.setVisible(true);
        }
        
        if(e.getSource()== negrita && negrita.isSelected()){
 
            Font f=textarea.getFont();
            textarea.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
            textarea2.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        }
        if(e.getSource()== negrita && !negrita.isSelected()){
 
            Font f=textarea.getFont();
            textarea.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
        }
        if(e.getSource()==cursiva && cursiva.isSelected()){
 
            Font f=textarea.getFont();
            textarea.setFont(f.deriveFont(f.getStyle() | Font.ITALIC));
 
        }
 
        if(e.getSource()==cursiva && !cursiva.isSelected()){
 
            Font f=textarea.getFont();
            textarea.setFont(f.deriveFont(f.getStyle() ^ Font.ITALIC));
     
        } 
        if(e.getSource()==arial){
            int valNegrita = negrita.isSelected() ? Font.BOLD : Font.PLAIN;
            int valCursiva = cursiva.isSelected() ? Font.ITALIC : Font.PLAIN;
            textarea.setFont(new Font("Arial", valNegrita+valCursiva,textarea.getFont().getSize()) );
        }
 
        if(e.getSource()==tahoma){
            int valNegrita = negrita.isSelected() ? Font.BOLD : Font.PLAIN;
            int valCursiva = cursiva.isSelected() ? Font.ITALIC : Font.PLAIN;
            textarea.setFont(new Font("Tahoma", valNegrita+valCursiva,textarea.getFont().getSize()) );
        }
 
        if(e.getSource()==times){
            int valNegrita = negrita.isSelected() ? Font.BOLD : Font.PLAIN;
            int valCursiva = cursiva.isSelected() ? Font.ITALIC : Font.PLAIN;
            textarea.setFont(new Font("Times new Roman", valNegrita+valCursiva,textarea.getFont().getSize()) );
        }
        
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource()== frmin && frmin.isSelected()){
            System.out.println("Hola");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
