package NotesApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotesView extends NotesViewCallBack implements ActionListener {
    private NotesControllerCallBack notesControllerCallBack;
    private JFrame frame;
    private JPanel panel;
    private JTextArea textArea;
    private JButton save;
    private JLabel label;
    private JLabel name;
    private JTextField textField;
    private JComboBox<String> comboBox;
    private JComboBox<String> delete;
    NotesView(){
        this.notesControllerCallBack=new NotesController(this);
        frame=new JFrame("Notes");
        label=new JLabel("~~~~~~~~~~~ Feel Free To Write ~~~~~~~~~~~");
        name=new JLabel("Enter File Name :");
        textField=new JTextField();
        comboBox=new JComboBox<String>();
        delete=new JComboBox<>();
        textArea=new JTextArea(30,60);
        panel=new JPanel();
        save=new JButton("SAVE");
        frame.setVisible(true);
        textField.setColumns(20);
        comboBox.addItem("Choose");
        delete.addItem("Choose");
        name.setFont(new Font("Arial",Font.ITALIC,20));
        label.setBorder(new EmptyBorder(0,0,20,0));
        name.setBorder(new EmptyBorder(0,0,10,0));
        frame.setSize(800,1000);
        label.setFont(new Font("Arial",Font.ITALIC,30));
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20,30,10,30));
        panel.add(label);
        panel.add(name);
        panel.add(textField);
        panel.add(textArea);
        panel.add(save);
        panel.add(comboBox);
        panel.add(delete);
        frame.add(panel,BorderLayout.CENTER);

        save.addActionListener(this);
        comboBox.addActionListener(this);
        delete.addActionListener(this);
    }
    public static void main(String []args){
        NotesView notes=new NotesView();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==save){
            notesControllerCallBack.save(textField.getText(),textArea.getText());
        }
        else if(e.getSource()==comboBox){
            textField.setText(String.valueOf(comboBox.getSelectedItem()));
            notesControllerCallBack.getFile(String.valueOf(comboBox.getSelectedItem()));
        }
        else if(e.getSource()==delete){
            String item=String.valueOf(comboBox.getSelectedItem());
            comboBox.removeItem(comboBox.getSelectedItem());
            notesControllerCallBack.deleteFile(item);
        }
    }

    @Override
    public void setCombo(String[] add) {
        comboBox.removeAllItems();
        comboBox.addItem("Choose");
        for(String s:add){
            comboBox.addItem(s);
            delete.addItem(s);
        }
    }

    @Override
    public void DisplayFile(String file) {
        textArea.setText(file);
    }

    @Override
    public void setdelete(String[] del) {
        this.delete.removeAllItems();
        this.delete.addItem("Choose");
        for(String str:del){
            comboBox.addItem(str);
        }
    }
}