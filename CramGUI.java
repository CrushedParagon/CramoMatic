import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class CramGUI implements ActionListener{
   private JComboBox item1;
   private JComboBox item2;
   private JComboBox item3;
   private JComboBox item4;
   private JTextArea reward;
   public void makeGUI(){
      //setting up frame
      JFrame frame=new JFrame("Cram-o-Matic");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(750,300);
      
      //left panel that holds dropdown lists
      JPanel left=new JPanel();
      left.setLayout(new BoxLayout(left,BoxLayout.PAGE_AXIS));
      
      //four dropdown lists on the left
      item1=new JComboBox(Item.giveList());
      item2=new JComboBox(Item.giveList());
      item3=new JComboBox(Item.giveList());
      item4=new JComboBox(Item.giveList());
      
      item1.setPreferredSize(new Dimension(300,50));
      item2.setPreferredSize(new Dimension(300,50));
      item3.setPreferredSize(new Dimension(300,50));
      item4.setPreferredSize(new Dimension(300,50));
      
      JButton refresh=new JButton("Refresh");
      refresh.setPreferredSize(new Dimension(300,25));
      refresh.addActionListener(this);
      
      left.add(item1);
      left.add(Box.createRigidArea(new Dimension(0,25)));
      left.add(item2);
      left.add(Box.createRigidArea(new Dimension(0,25)));
      left.add(item3);
      left.add(Box.createRigidArea(new Dimension(0,25)));
      left.add(item4);
      left.add(Box.createRigidArea(new Dimension(0,25)));
      left.add(refresh);
      
      
      reward=new JTextArea(5,25);
      reward.setEditable(false);
      reward.setLineWrap(true);
      reward.setText("Press Refresh Button to Show Reward");
      
      frame.getContentPane().add(BorderLayout.WEST,left);
      frame.getContentPane().add(BorderLayout.EAST,reward);
      frame.setVisible(true);
   }
   
   public void actionPerformed(ActionEvent e){
      Item send1=(Item)item1.getSelectedItem();
      Item send2=(Item)item2.getSelectedItem();
      Item send3=(Item)item3.getSelectedItem();
      Item send4=(Item)item4.getSelectedItem();
      
      String text=Cram.findReward(send1,send2,send3,send4);
      
      reward.setText(text);
      
      
   }
   
}