
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

public class JDialogEdit extends JDialog {
    private JTextField textPath = new JTextField();
    private JTextField textDescription=new JTextField();
    private JTextField textPrice=new JTextField();
    private JTextField textIsPart=new JTextField();
    public JDialogEdit(ArrayList<Student> myCountry, DefaultTreeModel treeModel, JTree tree) {
        setTitle("Add new row (enter new data)");
        setSize(450, 250);
        setLocation(600, 400);
        setResizable(false);
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JButton button = new JButton("ADD");
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Course:"));
        panel.add(textPath);
        panel.add(new JLabel("Group:"));
        panel.add(textDescription);
        panel.add(new JLabel("Surname:"));
        panel.add(textPrice);
        panel.add(new JLabel(" "));
        panel.add(button, BorderLayout.CENTER);
        add(panel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToTree(new Student(Integer.parseInt(textPath.getText()), Integer.parseInt(textDescription.getText()),
                        textPrice.getText()), treeModel, myCountry, tree);
                treeModel.reload();
                dispose();
            }
        });
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public void addToTree(Student student, DefaultTreeModel treeModel, ArrayList<Student>students,JTree tree ){
        TreePath selectedNode2 = tree.getSelectionPath();
        if(student.getCourse()==0)
            student.setCourse(Integer.parseInt(String.valueOf(selectedNode2.getPathComponent(1))));
        if(student.getGroup()==0)
            student.setGroup(Integer.parseInt(String.valueOf(selectedNode2.getPathComponent(2))));
        if(student.getSurname().equals(""))
            student.setSurname(String.valueOf(selectedNode2.getPathComponent(3)));
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        if (selectedNode != null && selectedNode.getParent() != null) {
            DefaultMutableTreeNode parentGroup = selectedNode.getPreviousNode(),
                    parentCourse = parentGroup.getPreviousNode();
            treeModel.removeNodeFromParent(selectedNode);
            if(parentGroup.getChildCount()==0) {
                treeModel.removeNodeFromParent(parentGroup);
                if(parentCourse.getChildCount()==0)
                    treeModel.removeNodeFromParent(parentCourse);
            }
        }
        students.remove(new Student(Integer.parseInt(String.valueOf(selectedNode2.getPathComponent(1))),
                Integer.parseInt(String.valueOf(selectedNode2.getPathComponent(2))),
                String.valueOf(selectedNode2.getPathComponent(3))));
        DefaultMutableTreeNode node = searchNode((DefaultMutableTreeNode) treeModel.getRoot(), String.valueOf(student.getCourse()));
        if (!students.contains(student)){
            students.add(student);
            if (node!=null && node.getDepth()!=1) {
                DefaultMutableTreeNode node2 = searchNode(node, String.valueOf(student.getGroup()));
                if (node2 != null && node2.getDepth()!=2) {
                    DefaultMutableTreeNode node3 = searchNode(node2, String.valueOf(student.getSurname()));
                    if (node3 != null && node3.getDepth()!=3) {
                    } else {
                        DefaultMutableTreeNode node33 = new DefaultMutableTreeNode(student.getSurname());
                        System.out.println(node2 + " " + node33);
                        node2.add(node33);
                        return;
                    }
                } else {
                    DefaultMutableTreeNode node22 = new DefaultMutableTreeNode(student.getGroup());
                    DefaultMutableTreeNode node34 = new DefaultMutableTreeNode(student.getSurname());
                    node.add(node22);
                    node22.add(node34);
                }
            }
            else{
                DefaultMutableTreeNode node1=new DefaultMutableTreeNode(student.getCourse());
                DefaultMutableTreeNode node2=new DefaultMutableTreeNode(student.getGroup());
                DefaultMutableTreeNode node3=new DefaultMutableTreeNode(student.getSurname());
                ((DefaultMutableTreeNode)treeModel.getRoot()).add(node1);
                node1.add(node2);
                node2.add(node3);
            }
        }
        treeModel.reload();
    }
    public DefaultMutableTreeNode searchNode(DefaultMutableTreeNode root, String nodeStr) {
        DefaultMutableTreeNode node = null;
        Enumeration e = root.breadthFirstEnumeration();
        while (e.hasMoreElements()) {
            node = (DefaultMutableTreeNode) e.nextElement();
            if (nodeStr.equals(node.getUserObject().toString())) {
                return node;
            }
        }
        return null;
    }
}
