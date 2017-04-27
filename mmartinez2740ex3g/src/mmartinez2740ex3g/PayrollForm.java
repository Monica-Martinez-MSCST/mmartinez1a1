package mmartinez2740ex3g;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PayrollForm extends JFrame {
	private JList employeeList;
	private JLabel totalHoursLabel;
	private JLabel grossPayLabel;
	private JTextField hourstextField;
	private DefaultListModel employeeListModel;
	private JTextField emptextField;
	private JTextField empNametextField;
	private JTextField payRatetextField;
	private JButton addHoursButton;
	private JButton clearHoursButton; 
	private JButton updateButton; 
	private PayrollObjMapper payrollObjMapper;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
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
	public PayrollForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				do_this_windowClosing(arg0);
			}
		});
		setTitle("MMonica 2740 Ex2E Payroll");
		setBounds(100, 100, 450, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblSelectEmployee = new JLabel("Select employee:");
		lblSelectEmployee.setBounds(10, 11, 103, 14);
		getContentPane().add(lblSelectEmployee);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(37, 36, 350, 99);
		getContentPane().add(scrollPane);
		
//		employeeListModel = new DefaultListModel();
//		employeeListModel.addElement(new Payroll (101, "Mark Swanson", 10.0));
//		employeeListModel.addElement(new Payroll (102, "Patti Weigand", 20.0));
//		employeeListModel.addElement(new Payroll (103, "Lyle Stelter", 30.0));
//		employeeListModel.addElement(new Payroll (104, "Neva Burdick", 40.0));
//		employeeListModel.addElement(new Payroll (105, "Lisa Laing", 50.0));
		payrollObjMapper = new PayrollObjMapper("exercise3g.txt");
		employeeListModel = payrollObjMapper.getAllPayroll();
		
		employeeList = new JList(employeeListModel);
		employeeList.setLayoutOrientation(JList.VERTICAL_WRAP);
		employeeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				do_employeeList_mouseClicked(arg0);
			}
		});
		employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(employeeList);
		
		JLabel lblEmployeeName = new JLabel("Employee name:");
		lblEmployeeName.setBounds(10, 214, 137, 14);
		getContentPane().add(lblEmployeeName);
		
		JLabel lblNewLabel_1 = new JLabel("Pay rate (7.25-100):");
		lblNewLabel_1.setBounds(10, 247, 137, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter hours(0.1-20.0):");
		lblNewLabel_2.setBounds(10, 289, 137, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Total  hours:");
		lblNewLabel_3.setBounds(10, 328, 88, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Gross pay:");
		lblNewLabel_4.setBounds(10, 366, 88, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblEmployeeID = new JLabel("Employee ID (>100):");
		lblEmployeeID.setBounds(10, 174, 137, 14);
		getContentPane().add(lblEmployeeID);
		
		totalHoursLabel = new JLabel("0.00");
		totalHoursLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalHoursLabel.setBounds(166, 328, 46, 14);
		getContentPane().add(totalHoursLabel);
		
		grossPayLabel = new JLabel("$0.00");
		grossPayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		grossPayLabel.setBounds(166, 366, 46, 14);
		getContentPane().add(grossPayLabel);
		
		hourstextField = new JTextField();
		hourstextField.setHorizontalAlignment(SwingConstants.RIGHT);
		hourstextField.setText("0.00");
		hourstextField.setBounds(132, 286, 103, 20);
		getContentPane().add(hourstextField);
		hourstextField.setColumns(10);
		
		addHoursButton = new JButton("+");
		addHoursButton.setEnabled(false);
		addHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_addHoursButton_actionPerformed(arg0);
			}
		});
		addHoursButton.setBounds(242, 285, 46, 23);
		getContentPane().add(addHoursButton);
		
		clearHoursButton = new JButton("Clear");
		clearHoursButton.setEnabled(false);
		clearHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_clearHoursButton_actionPerformed(arg0);
			}
		});
		clearHoursButton.setBounds(298, 285, 89, 23);
		getContentPane().add(clearHoursButton);
		
		JButton btnClearForm = new JButton("Clear Form");
		btnClearForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnClearForm.setBounds(321, 366, 103, 23);
		getContentPane().add(btnClearForm);
		
		emptextField = new JTextField();
		emptextField.setHorizontalAlignment(SwingConstants.RIGHT);
		emptextField.setText("000");
		emptextField.setBounds(149, 171, 86, 20);
		getContentPane().add(emptextField);
		emptextField.setColumns(10);
		
		empNametextField = new JTextField();
		empNametextField.setHorizontalAlignment(SwingConstants.RIGHT);
		empNametextField.setBounds(149, 211, 86, 20);
		getContentPane().add(empNametextField);
		empNametextField.setColumns(10);
		
		payRatetextField = new JTextField();
		payRatetextField.setHorizontalAlignment(SwingConstants.RIGHT);
		payRatetextField.setText("$7.25");
		payRatetextField.setBounds(149, 244, 86, 20);
		getContentPane().add(payRatetextField);
		payRatetextField.setColumns(10);
		
		updateButton = new JButton("Update");
		updateButton.setEnabled(false);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_updateButton_actionPerformed(arg0);
			}
		});
		updateButton.setBounds(222, 366, 89, 23);
		getContentPane().add(updateButton);

		
	}
	protected void do_employeeList_mouseClicked(MouseEvent arg0) 
	{
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		this.emptextField.setText(Integer.toString(payroll.getId()));
		this.empNametextField.setText(payroll.getName());
		DecimalFormat dollartFmt = new DecimalFormat("$#,##0.00");
		this.payRatetextField.setText(Double.toString(payroll.getPayRate()));
		DecimalFormat hoursFmt = new DecimalFormat ("##0.00");
		totalHoursLabel.setText(hoursFmt.format(payroll.getHours()));
		grossPayLabel.setText(dollartFmt.format(payroll.calcGrossPay()));
		hourstextField.setText("0.00");
		hourstextField.requestFocus();
		this.addHoursButton.setEnabled(true);
		this.clearHoursButton.setEnabled(true);
		this.updateButton.setEnabled(true);
	}
	protected void do_addHoursButton_actionPerformed(ActionEvent arg0) {
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		if(payroll.addHours(Double.parseDouble(this.hourstextField.getText())))
		{
			DecimalFormat hoursFmt = new DecimalFormat ("##0.00");
			totalHoursLabel.setText(hoursFmt.format(payroll.getHours()));
			DecimalFormat dollarFmt = new DecimalFormat("$#,##0.00");
			grossPayLabel.setText(dollarFmt.format(payroll.calcGrossPay()));
			this.hourstextField.setText ("0.00");
			this.hourstextField.requestFocus();
			employeeList.repaint();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Invalid hours. \nMust be in range 0.1 - 20.");
		}
	}
	protected void do_clearHoursButton_actionPerformed(ActionEvent arg0) {
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		payroll.setHours(0.00);
		DecimalFormat hoursFmt = new DecimalFormat ("##0.00");
		totalHoursLabel.setText(hoursFmt.format(payroll.getHours()));
		DecimalFormat dollarFmt = new DecimalFormat("$#,##0.00");
		grossPayLabel.setText(dollarFmt.format(payroll.calcGrossPay()));
		this.hourstextField.setText ("0.00");
		this.hourstextField.requestFocus();
		employeeList.repaint();
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		emptextField.setText("0");
		empNametextField.setText("");
		this.payRatetextField.setText("$0.00");
		this.totalHoursLabel.setText("0.00");
		this.grossPayLabel.setText("$0.00");
		this.hourstextField.requestFocus();
		this.hourstextField.setText("0.00");
		this.employeeList.clearSelection();
		this.addHoursButton.setEnabled(false);
		this.clearHoursButton.setEnabled(false);
		this.updateButton.setEnabled(false);
	}
	protected void do_hourstextfield_focusGained(FocusEvent arg0) {
		hourstextField.selectAll();
	}
	protected void do_updateButton_actionPerformed(ActionEvent arg0) {
		int id = Integer.parseInt(emptextField.getText());
		double rate = Double.parseDouble(this.payRatetextField.getText()); 
		String name = this.empNametextField.getText();
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		if (!payroll.setId(id)) 
		{
			JOptionPane.showMessageDialog (null, "Invalid employee ID. \nMust be >100");
			emptextField.setText(Integer.toString(payroll.getId()));
			emptextField.requestFocus();
		}
		else if(!payroll.setPayRate(rate))
		{
			JOptionPane.showMessageDialog (null, "Invalid payrate. \nMust be in range 7.25 - 100.");
			DecimalFormat rateFmt = new DecimalFormat("#0.00");
			this.payRatetextField.setText(rateFmt.format(payroll.getPayRate()));
			payRatetextField.requestFocus();
		}
		else if(!payroll.setName(name))
		{
			JOptionPane.showMessageDialog (null, "Invalid Name. \nName is required.");
			this.empNametextField.setText(payroll.getName());
			empNametextField.requestFocus();
		}
		
		employeeList.repaint();
			
		}
	protected void do_this_windowClosing(WindowEvent arg0) {
		if (payrollObjMapper != null)
			payrollObjMapper.writeAllPayroll(employeeListModel);
		}
	}

