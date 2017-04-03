import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class TestTree extends JFrame {
    private JTree tree;
    private DefaultTreeModel treeModel;
    private ArrayList<Integer> courcesList=new ArrayList<>();
    private ArrayList<Integer> groupsList=new ArrayList<>();
    private ArrayList<String> surnames=new ArrayList<>();
    private ArrayList<Student> students=new ArrayList<>();
    private  DefaultMutableTreeNode root = new DefaultMutableTreeNode("BSU");
    private JButton button, button2, button3;
    private JPanel panel, mainPanel, panelForScl;
    public TestTree() {
        super("Tree Test Example");
        setSize(700, 900);
        setLocation(450,150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void init() throws FileNotFoundException {

        Scanner input= new Scanner(new File("in.txt"));
        int course=0;
        int group=0;
        Set<Integer> courses=new LinkedHashSet<>();
        Set<Integer> groups=new LinkedHashSet<>();
        String surname="";
        mainPanel=new JPanel(new BorderLayout());
        panel=new JPanel(new GridLayout(1,2));
        while(input.hasNext()){
            course=input.nextInt();
            group=input.nextInt();
            surname=input.next();
            courses.add(course);
            groups.add(group);
            surnames.add(surname);
            Student student=new Student(course, group, surname);
            students.add(student);
        }

        courcesList.addAll(courses);
        groupsList.addAll(groups);

        DefaultMutableTreeNode[] courseRoot=new DefaultMutableTreeNode[courses.size()];
        DefaultMutableTreeNode[] groupRoot = new DefaultMutableTreeNode[groupsList.size()];


        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);

        tree.setEditable(true);
        JScrollPane scl = new JScrollPane(tree);
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
        for (int i=0; i<courses.size(); i++){
            courseRoot[i]=new DefaultMutableTreeNode(courcesList.get(i));
        }
        for (int j = 0; j < courses.size(); j++) {
            for (int i=0; i<groupsList.size(); i++){
                groupRoot[i]=new DefaultMutableTreeNode(groupsList.get(i));
            }
            treeModel.insertNodeInto(courseRoot[j], root, 0);
            for (Student student : students) {
                for (int k = 0; k < groupsList.size(); k++) {
                    if (student.getCourse() == courcesList.get(j) && student.getGroup() == groupsList.get(k)) {
                        treeModel.insertNodeInto(groupRoot[k], courseRoot[j], 0);
                        treeModel.insertNodeInto(new DefaultMutableTreeNode(student.getSurname()), groupRoot[k], 0);
                    }
                }
            }
        }

        renderer.setLeafIcon  (new ImageIcon("user.png"));
        renderer.setOpenIcon  (new ImageIcon("opened.png"));
        renderer.setClosedIcon(new ImageIcon("closed.jpg"));
        button = new JButton("Remove student");
        button.addActionListener(e -> {
            TreePath selectedNode2 =tree.getSelectionPath();
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
            if (selectedNode != null && selectedNode.getParent() != null) {
                DefaultMutableTreeNode parentGroup = (DefaultMutableTreeNode) ((TreeNode)selectedNode).getParent(),
                        parentCourse = (DefaultMutableTreeNode) ((TreeNode)parentGroup).getParent();
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
            treeModel.reload();
        });
        button2 = new JButton("Add Student");
        button2.addActionListener(e -> new MyJDialog(students, treeModel, tree));

        button3 = new JButton("Edit Student");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JDialogEdit(students, treeModel, tree);
            }
        });
        mainPanel.add(scl, BorderLayout.CENTER);
        panel.add(button);
        panel.add(button2);
       // panel.add(button3);
        mainPanel.add(panel, BorderLayout.SOUTH);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        getContentPane().add(mainPanel);
    }
    public static void main(String args[]) throws FileNotFoundException {
        TestTree tt = new TestTree();
        tt.init();
        tt.setVisible(true);
    }
}




