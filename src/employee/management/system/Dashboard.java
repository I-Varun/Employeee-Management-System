package employee.management.system;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.*;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Frame;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import java.util.List;


public class Dashboard extends JFrame implements ActionListener {


    private JPanel contentPane;
    JTabbedPane tabbedPane;
    private JTextField pro_pid;
    private JTextField pro_name;
    private JTextField pro_eid;
    private JTextField pro_search;
    private JTextField emp_eid;
    private JTextField emp_name;
    private JTextField emp_sal;
    private JTextField emp_search;
    private JTextField emp_des;
    private JTextField dep_did;
    private JTextField dep_name;
    private JTextField dep_ad;
    private JTextField dep_search;
    static DataBase db;
    private JComboBox<String> emp_did,pro_did;
    DefaultComboBoxModel<String> emp_did2,dep_did2,pro_did2;
    String[] arr;
    String[][] e_data,d_data,p_data;
    private JLabel totalSalary;
    private JLabel subNames;
    private JLabel subordinaries;
    private JLabel protodept;
    private JLabel emptodept;
    private JLabel totalproj;
    private JLabel totalemp;
    DefaultTableModel empro_model,dept_model,pro_model;;
    private JTable emp_table,dept_table,pro_table;;
    private JScrollPane e_sp,dept_sp,pro_sp;;
    Department d;
    Employee e;
    Project p;


    public static void main(String[] args) {

        Dashboard frame = new Dashboard();
        frame.setVisible(true);


    }
    public Dashboard() {
        db = DataBase.getInstance();
        db.loadData();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setFont(new Font("Arial Black", Font.BOLD, 18));
        setTitle("DASHBOARD");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 50, 410, 535);
        contentPane = new JPanel();
        contentPane.setBackground(Color.yellow);
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.getBounds();

        JLabel logo = new JLabel("V SINGH ENTERPRISES PVT LTD.");
        logo.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 24));
        logo.setForeground(Color.blue);
        logo.setBounds(500, 0, 600, 50);
        contentPane.add(logo);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/v.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(30, 40, 220, 220);
        contentPane.add(image);

        ImageIcon depIcon = new ImageIcon(ClassLoader.getSystemResource("icons/star.jpg"));
        ImageIcon dep = new ImageIcon(depIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        JButton depbtn = new JButton("Departments", dep);
//        depbtn.setBorderPainted(true);
//        depbtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        depbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        depbtn.setFont(new Font("Sans", Font.BOLD, 25));
        depbtn.setBounds(30, 580, 240, 40);
        depbtn.setHorizontalAlignment(SwingConstants.LEFT);
        depbtn.setOpaque(false);
        depbtn.setContentAreaFilled(true);
        depbtn.setBackground(Color.BLACK);
        depbtn.setForeground(Color.black);
        contentPane.add(depbtn);

        ImageIcon empIcon = new ImageIcon(ClassLoader.getSystemResource("icons/star.jpg"));
        ImageIcon emp = new ImageIcon(depIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        JButton empbtn = new JButton("Employees", emp);
        empbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        empbtn.setFont(new Font("Sans", Font.BOLD, 25));
        empbtn.setBounds(30, 510, 240, 40);
        empbtn.setHorizontalAlignment(SwingConstants.LEFT);
        empbtn.setOpaque(false);
        empbtn.setContentAreaFilled(true);
        empbtn.setBackground(Color.BLACK);
        empbtn.setForeground(Color.black);
        contentPane.add(empbtn);

        ImageIcon projIcon = new ImageIcon(ClassLoader.getSystemResource("icons/star.jpg"));
        ImageIcon proj = new ImageIcon(depIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        ;
        JButton projbtn = new JButton("Projects", proj);
        projbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        projbtn.setFont(new Font("Sans", Font.BOLD, 25));
        projbtn.setBounds(30, 650, 240, 40);
        projbtn.setHorizontalAlignment(SwingConstants.LEFT);
        projbtn.setOpaque(false);
        projbtn.setContentAreaFilled(true);
        projbtn.setBackground(Color.BLACK);
        projbtn.setForeground(Color.black);
        contentPane.add(projbtn);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setOpaque(true);
        tabbedPane.setBackground(new Color(100, 100, 10));
        tabbedPane.setBounds(270, 40, 1354, 756);
        contentPane.add(tabbedPane);
        // EMPLOYEE PANEL
        JPanel employee_panel = new JPanel();
        employee_panel.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("Employees", null, employee_panel, null);
        tabbedPane.setEnabledAt(0, true);
        employee_panel.setLayout(null);

        JLabel EmployeeId = new JLabel("EMPLOYEE ID:");
        EmployeeId.setFont(new Font("Sans", Font.BOLD, 14));
        EmployeeId.setBounds(14, 10, 120, 30);
        employee_panel.add(EmployeeId);

        JLabel Name = new JLabel("NAME:");
        Name.setFont(new Font("Sans", Font.BOLD, 14));
        Name.setBounds(14, 50, 92, 30);
        employee_panel.add(Name);

        JLabel DepartmentId = new JLabel("DEPARTMENT ID:");
        DepartmentId.setFont(new Font("Sans", Font.BOLD, 14));
        DepartmentId.setBounds(14, 130, 130, 30);
        employee_panel.add(DepartmentId);

        JLabel lblNewLabel = new JLabel("SALARY :");
        lblNewLabel.setFont(new Font("Sans", Font.BOLD, 14));
        lblNewLabel.setBounds(14, 170, 79, 30);
        employee_panel.add(lblNewLabel);

        JLabel lblDepartmentId = new JLabel("DESIGNATION:");
        lblDepartmentId.setFont(new Font("Sans", Font.BOLD, 14));
        lblDepartmentId.setBounds(14, 90, 120, 30);
        employee_panel.add(lblDepartmentId);

        emp_eid = new JTextField();
        emp_eid.setColumns(10);
        emp_eid.setBounds(123, 14, 110, 19);
        employee_panel.add(emp_eid);

        emp_name = new JTextField();
        emp_name.setColumns(10);
        emp_name.setBounds(123, 54, 110, 19);
        employee_panel.add(emp_name);

        emp_sal = new JTextField();
        emp_sal.setColumns(10);
        emp_sal.setBounds(123, 174, 110, 19);
        employee_panel.add(emp_sal);

        emp_des = new JTextField();
        emp_des.setColumns(10);
        emp_des.setBounds(123, 94, 110, 19);
        employee_panel.add(emp_des);

        arr = db.getDepartmentNames();
        emp_did2 = new DefaultComboBoxModel<>(arr);
        emp_did = new JComboBox<>(emp_did2);
        emp_did.setBounds(143, 134, 110, 19);
        employee_panel.add(emp_did);

        emp_search = new JTextField();
        emp_search.setColumns(10);
        emp_search.setBounds(10, 223, 140, 19);
        employee_panel.add(emp_search);

        JButton e_search = new JButton("Search");
        e_search.setActionCommand("e_search");
        e_search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        e_search.setBorderPainted(false);
        e_search.setBackground(Color.lightGray);
        e_search.setForeground(Color.cyan);
        e_search.setBounds(165, 222, 85, 21);
        e_search.addActionListener(this);
        employee_panel.add(e_search);

        JButton e_create = new JButton("Create");
        e_create.setActionCommand("e_create");
        e_create.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        e_create.setBorderPainted(false);
        e_create.setBackground(Color.lightGray);
        e_create.setForeground(Color.GREEN);
        e_create.setBounds(260, 222, 85, 21);
        e_create.addActionListener(this);
        employee_panel.add(e_create);

        JButton e_update = new JButton("Update");
        e_update.setActionCommand("e_update");
        e_update.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        e_update.setBorderPainted(false);
        e_update.setBackground(Color.lightGray);
        e_update.setForeground(Color.blue);
        e_update.setBounds(360, 222, 85, 21);
        e_update.addActionListener(this);
        employee_panel.add(e_update);

        JButton e_delete = new JButton("Delete");
        e_delete.setActionCommand("e_delete");
        e_delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        e_delete.setBorderPainted(false);
        e_delete.setBackground(Color.lightGray);
        e_delete.setForeground(Color.red);
        e_delete.setBounds(465, 222, 85, 21);
        e_delete.addActionListener(this);
        employee_panel.add(e_delete);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/employee.jpg"));
        Image i5 = i4.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel img = new JLabel(i6);
        img.setBounds(300, 10, 220, 220);
        employee_panel.add(img);

        totalSalary = new JLabel();
        totalSalary.setBorder(null);
        totalSalary.setBounds(383, 38, 226, 19);
        employee_panel.add(totalSalary);

        subNames = new JLabel();
        subNames.setBorder(null);
        subNames.setBounds(383, 67, 226, 19);
        employee_panel.add(subNames);

        subordinaries = new JLabel();
        subordinaries.setBorder(null);
        subordinaries.setBounds(383, 96, 226, 19);
        employee_panel.add(subordinaries);

        totalemp = new JLabel();
        totalemp.setBorder(null);
        totalemp.setBounds(383, 134, 226, 19);
        employee_panel.add(totalemp);
        // DEPARTMENT PANEL
        JPanel dept_panel = new JPanel();
        dept_panel.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("Departments", null, dept_panel, null);
        tabbedPane.setEnabledAt(1, true);
        dept_panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("DEPARTMENT ID:");
        lblNewLabel_1.setFont(new Font("Sans", Font.BOLD, 12));
        lblNewLabel_1.setBounds(14, 13, 120, 30);
        dept_panel.add(lblNewLabel_1);

        JLabel lblName = new JLabel("NAME:");
        lblName.setFont(new Font("Sans", Font.BOLD, 14));
        lblName.setBounds(14, 60, 92, 30);
        dept_panel.add(lblName);

        JLabel lblNewLabel_2 = new JLabel("ADDRESS:");
        lblNewLabel_2.setFont(new Font("Sans", Font.BOLD, 14));
        lblNewLabel_2.setBounds(14, 100, 92, 30);
        dept_panel.add(lblNewLabel_2);

        dep_did = new JTextField();
        dep_did.setColumns(10);
        dep_did.setBounds(123, 13, 120, 19);
        dept_panel.add(dep_did);

        dep_name = new JTextField();
        dep_name.setColumns(10);
        dep_name.setBounds(123, 60, 120, 19);
        dept_panel.add(dep_name);

        dep_ad = new JTextField();
        dep_ad.setColumns(10);
        dep_ad.setBounds(123, 100, 120, 19);
        dept_panel.add(dep_ad);

        dep_search = new JTextField();
        dep_search.setColumns(5);
        dep_search.setBounds(10, 240, 140, 19);
        dept_panel.add(dep_search);

        JButton d_search = new JButton("Search");
        d_search.setActionCommand("d_search");
        d_search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        d_search.setBorderPainted(false);
        d_search.setBackground(Color.lightGray);
        d_search.setForeground(Color.cyan);
        d_search.setBounds(160, 240, 85, 21);
        d_search.addActionListener(this);
        dept_panel.add(d_search);

        JButton d_create = new JButton("Create");
        d_create.setActionCommand("d_create");
        d_create.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        d_create.setBorderPainted(false);
        d_create.setBackground(Color.lightGray);
        d_create.setForeground(Color.GREEN);
        d_create.setBounds(250, 240, 85, 21);
        d_create.addActionListener(this);
        dept_panel.add(d_create);

        JButton d_update = new JButton("Update");
        d_update.setActionCommand("d_update");
        d_update.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        d_update.setBorderPainted(false);
        d_update.setBackground(Color.lightGray);
        d_update.setForeground(Color.blue);
        d_update.setBounds(355, 240, 85, 21);
        d_update.addActionListener(this);
        dept_panel.add(d_update);

        JButton d_delete = new JButton("Delete");
        d_delete.setActionCommand("d_delete");
        d_delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        d_delete.setBorderPainted(false);
        d_delete.setBackground(Color.lightGray);
        d_delete.setForeground(Color.red);
        d_delete.setBounds(450, 240, 85, 21);
        d_delete.addActionListener(this);
        dept_panel.add(d_delete);

        protodept = new JLabel();
        protodept.setBorder(null);
        protodept.setBounds(395, 33, 201, 19);
        dept_panel.add(protodept);

        emptodept = new JLabel();
        emptodept.setBorder(null);
        emptodept.setBounds(395, 67, 201, 19);
        dept_panel.add(emptodept);

        ImageIcon i_4 = new ImageIcon(ClassLoader.getSystemResource("icons/dept.png"));
        Image i_5 = i_4.getImage().getScaledInstance(220, 220, Image.SCALE_DEFAULT);
        ImageIcon i_6 = new ImageIcon(i_5);
        JLabel img_1 = new JLabel(i_6);
        img_1.setBounds(300, 10, 180, 180);
        dept_panel.add(img_1);

// PROJECT PANEL
        JPanel pro_panel = new JPanel();
        pro_panel.setFont(new Font("Arial Black", Font.PLAIN, 11));
        pro_panel.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("Projects", null, pro_panel, null);
        tabbedPane.setEnabledAt(2, true);
        pro_panel.setLayout(null);

        JLabel lblNewLabel_3 = new JLabel("PROJECT ID:");
        lblNewLabel_3.setFont(new Font("Sans", Font.BOLD, 14));
        lblNewLabel_3.setBounds(10, 10, 100, 30);
        pro_panel.add(lblNewLabel_3);

        JLabel lblName_3 = new JLabel("NAME:");
        lblName_3.setFont(new Font("Sans", Font.BOLD, 14));
        lblName_3.setBounds(10, 50, 92, 30);
        pro_panel.add(lblName_3);

        JLabel lblDepartmentId_2 = new JLabel("DEPARTTMENT ID:");
        lblDepartmentId_2.setFont(new Font("Sans", Font.BOLD, 13));
        lblDepartmentId_2.setBounds(10, 90, 120, 30);
        pro_panel.add(lblDepartmentId_2);

        JLabel lblNewLabel_4 = new JLabel("EMPLOYEE ID:");
        lblNewLabel_4.setFont(new Font("Sans", Font.BOLD, 13));
        lblNewLabel_4.setBounds(10, 130, 100, 30);
        pro_panel.add(lblNewLabel_4);

        pro_pid = new JTextField();
        pro_pid.setBounds(119, 14, 96, 19);
        pro_panel.add(pro_pid);
        pro_pid.setColumns(10);

        pro_name = new JTextField();
        pro_name.setColumns(10);
        pro_name.setBounds(119, 54, 96, 19);
        pro_panel.add(pro_name);

        pro_eid = new JTextField();
        pro_eid.setColumns(10);
        pro_eid.setBounds(140, 134, 96, 19);
        pro_panel.add(pro_eid);

        arr = db.getDepartmentNames();
        pro_did2 = new DefaultComboBoxModel<>(arr);
        pro_did = new JComboBox<>(pro_did2);
        pro_did.setBounds(140, 94, 96, 19);
        pro_panel.add(pro_did);

        ImageIcon i_4_1 = new ImageIcon(ClassLoader.getSystemResource("icons/project.png"));
        Image i_5_1 = i_4_1.getImage().getScaledInstance(220, 220, Image.SCALE_DEFAULT);
        ImageIcon i_6_1 = new ImageIcon(i_5_1);
        JLabel img_5 = new JLabel(i_6);
        img_5.setBounds(300, 10, 130, 180);
        pro_panel.add(img_5);
        
        pro_search = new JTextField();
        pro_search.setBounds(6, 240, 140, 19);
        pro_panel.add(pro_search);
        pro_search.setColumns(10);

        JButton p_search = new JButton("Search");
        p_search.setActionCommand("p_search");
        p_search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        p_search.setBorderPainted(false);
        p_search.setBackground(Color.lightGray);
        p_search.setForeground(Color.cyan);
        p_search.setBounds(160, 240, 85, 21);
        p_search.addActionListener(this);
        pro_panel.add(p_search);

        JButton p_create = new JButton("Create");
        p_create.setActionCommand("p_create");
        p_create.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        p_create.setBorderPainted(false);
        p_create.setBackground(Color.lightGray);
        p_create.setForeground(Color.GREEN);
        p_create.setBounds(250, 240, 85, 21);
        p_create.addActionListener(this);
        pro_panel.add(p_create);

        JButton p_update = new JButton("Update");
        p_update.setActionCommand("p_update");
        p_update.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        p_update.setBorderPainted(false);
        p_update.setBackground(Color.lightGray);
        p_update.setForeground(Color.blue);
        p_update.setBounds(355, 240, 85, 21);
        p_update.addActionListener(this);
        pro_panel.add(p_update);

        JButton p_delete = new JButton("Delete");
        p_delete.setActionCommand("p_delete");
        p_delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        p_delete.setBorderPainted(false);
        p_delete.setBackground(Color.lightGray);
        p_delete.setForeground(Color.red);
        p_delete.setBounds(450, 240, 85, 21);
        p_delete.addActionListener(this);
        pro_panel.add(p_delete);

        totalproj = new JLabel();
        totalproj.setBorder(null);
        totalproj.setBounds(344, 54, 290, 36);
        pro_panel.add(totalproj);
        tabbedPane.setBackgroundAt(1, Color.WHITE);

        e_data = db.getEmployees();
        String[] e_columns = new String[]{"Employee Id", "Name", "Designation", "Department Id", "Salary"};
        empro_model = new DefaultTableModel(e_data, e_columns);
        emp_table = new JTable(empro_model);
        emp_table.setRowHeight(30);
        emp_table.getTableHeader().setBackground(new Color(5, 5, 5));
        emp_table.getTableHeader().setForeground(Color.WHITE);
        emp_table.getTableHeader().setFont(new Font("Arial", Font.CENTER_BASELINE, 25));
        e_sp = new JScrollPane(emp_table);
        e_sp.setSize(1354, 200);
        e_sp.setLocation(0, 280);
        employee_panel.add(e_sp);

        d_data = db.getDepartments();
        String[] d_columns = new String[]{"Department ID", "Name", "Address"};
        dept_model = new DefaultTableModel(d_data, d_columns);
        dept_table = new JTable(dept_model);
        dept_table.setRowHeight(30);
        dept_table.getTableHeader().setBackground(new Color(5, 5, 5));
        dept_table.getTableHeader().setForeground(Color.WHITE);
        dept_table.getTableHeader().setFont(new Font("Arial", Font.CENTER_BASELINE, 25));
        dept_sp = new JScrollPane(dept_table);
        dept_sp.setSize(1354, 200);
        dept_sp.setLocation(0, 280);
        dept_panel.add(dept_sp);

        p_data = db.getProjects();
        String[] pro_columns = new String[]{"Project ID", "Name", "Department ID", "Emp ID(Team Lead)"};
        pro_model = new DefaultTableModel(p_data, pro_columns);
        pro_table = new JTable(pro_model);
        pro_table.setRowHeight(25);
        pro_table.getTableHeader().setBackground(new Color(5, 5, 5));
        pro_table.getTableHeader().setForeground(Color.WHITE);
        pro_table.getTableHeader().setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        pro_sp = new JScrollPane(pro_table);
        pro_sp.setSize(1354, 200);
        pro_sp.setLocation(0, 280);
        pro_panel.add(pro_sp);

        JButton endbt = new JButton("EXIT");
        endbt.setBorderPainted(true);
        endbt.setBorder(new LineBorder(new Color(255, 255, 255), 3));
        endbt.setHorizontalTextPosition(SwingConstants.CENTER);
        endbt.setFont(new Font("Times New Roman", Font.BOLD, 25));
        endbt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        endbt.setBackground(new Color(0, 128, 255));
        endbt.setBounds(36, 750, 113, 26);
        contentPane.add(endbt);

        empbtn.addActionListener(e -> tabbedPane.setSelectedIndex(0));
        depbtn.addActionListener(d -> tabbedPane.setSelectedIndex(1));
        projbtn.addActionListener(p -> tabbedPane.setSelectedIndex(2));
        endbt.addActionListener(e -> dispose());

    }

        public void actionPerformed(ActionEvent event) {
            String cmd = event.getActionCommand();
            dept_table.addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent event) {
                    int row = dept_table.getSelectedRow();
                    dep_did.setText(dept_model.getValueAt(row,0).toString());
                    dep_name.setText(dept_model.getValueAt(row,1).toString());
                    dep_ad.setText(dept_model.getValueAt(row,2).toString());
                    emptodept.setText("No of Employees: "+db.getEmpinDept(db.getDepartment(dept_model.getValueAt(row,1).toString())));
                    protodept.setText("No of Projects: "+db.getProjinDept(db.getDepartment(dept_model.getValueAt(row,1).toString())));
                }
            });
            emp_table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    int row = emp_table.getSelectedRow();
                    emp_eid.setText(empro_model.getValueAt(row,0).toString());
                    e= db.getEmployee(emp_eid.getText());
                    totalSalary.setText("Total Salary: "+e.getTotalSalary());
                    subNames.setText("No of Subordinaries: "+e.getNoOfEmployees());
                    String s = "";
                    List<Employee> list = e.getAllemployees();
                    for(int i=0;i< list.size()-1;i++){
                        s+=list.get(i).name+",";
                    }
                    s+=list.get(list.size()-1).name;
                    subordinaries.setText("Subordinaries: "+s);
                    emp_name.setText(empro_model.getValueAt(row,1).toString());
                    emp_des.setText(empro_model.getValueAt(row,2).toString());
                    emp_did.setSelectedItem(empro_model.getValueAt(row,3).toString());
                    emp_sal.setText(empro_model.getValueAt(row,4).toString());
                }
            });
            pro_table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    int row = pro_table.getSelectedRow();
                    pro_pid.setText(pro_model.getValueAt(row,0).toString());
                    pro_name.setText(pro_model.getValueAt(row,1).toString());
                    pro_did.setSelectedItem(pro_model.getValueAt(row,3).toString());
                    pro_eid.setText(pro_model.getValueAt(row,3).toString());
                }
            });
            if (cmd.equals("d_search")) {
                String stId=dep_search.getText();
                d = db.getDepartment(stId);
                if(d == null){
                    JOptionPane.showMessageDialog(this,"No such department found");
                }
                else{
                    dep_did.setText(d.deptId);
                    dep_name.setText(d.name);
                    dep_ad.setText(d.address);
                    emptodept.setText("No of Employees: "+db.getEmpinDept(db.getDepartment(d.name)));
                    protodept.setText("No of Projects: "+db.getProjinDept(db.getDepartment(d.name)));
                }
            } else if (cmd.equals("d_create")) {
                Department d=new Department(dep_did.getText(),dep_name.getText(),dep_ad.getText());
                db.addDepartment(d);
                dept_model.addRow(new Object[]{d.deptId,d.name,d.address});
                dept_model.fireTableChanged(null);
                db.refresh();
                System.out.println("Department Created "+d.deptId);
            } else if (cmd.equals("d_update")) {
                int row = dept_table.getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(this,"Please select a row to update");
                }
                else{
                    d = new Department(dep_did.getText(),dep_name.getText(),dep_ad.getText());
                    db.updateDepartment(d);
                    db.refresh();
                    dept_model.setValueAt(dep_did.getText(),row,0);
                    dept_model.setValueAt(dep_name.getText(),row,1);
                    dept_model.setValueAt(dep_ad.getText(),row,2);
                    dept_model.fireTableChanged(null);
                    arr = db.getDepartmentNames();
                    emp_did.setModel(new DefaultComboBoxModel(arr));
                    pro_did.setModel(new DefaultComboBoxModel(arr));
                    System.out.println("Department Updated "+d.deptId);
                }
            } else if (cmd.equals("d_delete")) {
                int row = dept_table.getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(this,"Please select a row to delete");
                }
                else{
                    dept_model.removeRow(row);
                    db.removeDepartment(dep_did.getText());
                    db.refresh();
                    dept_model.fireTableChanged(null);
                    dep_did.setText("");
                    dep_name.setText("");
                    dep_ad.setText("");
                    arr = db.getDepartmentNames();
                    emp_did.setModel(new DefaultComboBoxModel(arr));
                    System.out.println("Department Deleted "+d.deptId);
                }
            } else if (cmd.equals("e_search")) {
                String stId=emp_search.getText();
                e = db.getEmployee(stId);
                if(e == null){
                    JOptionPane.showMessageDialog(this,"No such employee found");
                }
                else{
                    emp_eid.setText(e.empID);
                    emp_name.setText(e.name);
                    emp_des.setText(e.designation);
                    emp_did.setSelectedItem(e.dept.name);
                    emp_sal.setText(String.valueOf(e.salary));
                    totalSalary.setText("Total Salary: "+e.getTotalSalary());
                    subNames.setText("No of Subordinaries: "+e.getNoOfEmployees());
                    String s = "";
                    List<Employee> list = e.getAllemployees();
                    for(int i=0;i< list.size()-1;i++){
                        s+=list.get(i).name+",";
                    }
                    s+=list.get(list.size()-1).name;
                    subordinaries.setText("Subordinaries: "+s);
                }
            } else if (cmd.equals("e_create")) {
                if(emp_des.getText().equals("Worker")){
                    e = new Worker(emp_eid.getText(),emp_name.getText(),emp_des.getText(),db.getDepartment(emp_did.getSelectedItem().toString()),Integer.parseInt(emp_sal.getText()));
                }else {
                    e = new Leader(emp_eid.getText(),emp_name.getText(),emp_des.getText(),db.getDepartment(emp_did.getSelectedItem().toString()),Integer.parseInt(emp_sal.getText()));
                }
                db.addEmployee(e);
                empro_model.addRow(new Object[]{e.empID,e.name,e.designation,e.dept.name,e.salary});
                db.refresh();
                totalemp.setText("Total No of Employees: "+db.getEmployees().length);
                System.out.println("Employee Created "+e.empID);
            } else if (cmd.equals("e_update")) {
                int row = emp_table.getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(this,"Please select a row to update");
                }
                else{
                    if(emp_des.getText().equals("Worker")){
                        e = new Worker(emp_eid.getText(),emp_name.getText(),emp_des.getText(),db.getDepartment(emp_did.getSelectedItem().toString()),Integer.parseInt(emp_sal.getText()));
                    }else {
                        e = new Leader(emp_eid.getText(),emp_name.getText(),emp_des.getText(),db.getDepartment(emp_did.getSelectedItem().toString()),Integer.parseInt(emp_sal.getText()));
                    }
                    db.updateEmployee(e);
                    db.refresh();
                    empro_model.setValueAt(emp_eid.getText(),row,0);
                    empro_model.setValueAt(emp_name.getText(),row,1);
                    empro_model.setValueAt(emp_des.getText(),row,2);
                    empro_model.setValueAt(emp_did.getSelectedItem(),row,3);
                    empro_model.setValueAt(emp_sal.getText(),row,4);
                    empro_model.fireTableChanged(null);
                }
            } else if (cmd.equals("e_delete")) {
                int row = emp_table.getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(this,"Please select a row to delete");
                }
                else{
                    empro_model.removeRow(row);
                    db.removeEmployee(emp_eid.getText());
                    db.refresh();
                    totalemp.setText("Total No of Employees: "+db.getEmployees().length);
                    empro_model.fireTableChanged(null);
                    emp_eid.setText("");
                    emp_name.setText("");
                    emp_des.setText("");
                    emp_sal.setText("");
                }
            } else if (cmd.equals("p_search")) {
                String stId=pro_search.getText();
                p = db.getProject(stId);
                if(p == null){
                    JOptionPane.showMessageDialog(this,"No such project found");
                }
                else{
                    pro_pid.setText(p.projId);
                    pro_name.setText(p.name);
                    pro_did.setSelectedItem(p.dept.name);
                    pro_eid.setText(String.valueOf(p.empId));
                    totalproj.setText("Total no of Projects: "+db.getProjects().length);
                }
            } else if (cmd.equals("p_create")) {
                p = new Project(pro_pid.getText(),pro_name.getText(),db.getDepartment(pro_did.getSelectedItem().toString()),pro_eid.getText());
                db.addProject(p);
                db.refresh();
                totalproj.setText("Total no of Projects: "+db.getProjects().length);
                pro_model.addRow(new Object[]{p.projId,p.name,p.dept.name,p.empId});
                pro_model.fireTableChanged(null);
            } else if (cmd.equals("p_update")) {
                int row = pro_table.getSelectedRow();
                if(row == -1)
                {
                    JOptionPane.showMessageDialog(this,"Please select a row to update");
                }
                else{
                    p = new Project(pro_pid.getText(),pro_name.getText(),db.getDepartment(pro_did.getSelectedItem().toString()),pro_eid.getText());
                    db.updateProject(p);
                    db.refresh();
                    pro_model.setValueAt(pro_pid.getText(),row,0);
                    pro_model.setValueAt(pro_name.getText(),row,1);
                    pro_model.setValueAt(pro_did.getSelectedItem(),row,2);
                    pro_model.setValueAt(pro_eid.getText(),row,3);
                    pro_model.fireTableChanged(null);
                }
            } else if (cmd.equals("p_delete")) {
                int row = pro_table.getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(this,"Please select a row to delete");
                }
                else
                {
                    pro_model.removeRow(row);
                    db.removeProject(pro_pid.getText());
                    db.refresh();
                    totalproj.setText("Total no of Projects: "+db.getProjects().length);
                    pro_model.fireTableChanged(null);
                    pro_pid.setText("");
                    pro_name.setText("");
                    pro_eid.setText("");
                }
            }
        }
    }


