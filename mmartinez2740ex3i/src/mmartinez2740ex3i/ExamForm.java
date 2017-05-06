package mmartinez2740ex3i;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ExamForm extends JFrame {

	private JPanel contentPane;
	private JList responsesList;
	private DefaultListModel respnsesListModel;
	private JLabel resultLabel;
	private JLabel questNumLabel;
	private JTextField inputAnswerTextField;
	private JButton prevButton;
	private JButton nextButton;
	private DriverExam exam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExamForm frame = new ExamForm();
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
	public ExamForm() {
		setTitle("Ex3I Driver Exam");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Respnses:");
		lblNewLabel.setBounds(0, 11, 82, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Result:");
		lblNewLabel_1.setBounds(104, 11, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setEnabled(false);
		list.setBounds(10, 26, 24, 182);
		contentPane.add(list);
		
		responsesList = new JList();
		responsesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				do_responsesList_valueChanged(arg0);
			}
		});
		responsesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		responsesList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		responsesList.setBounds(42, 24, 52, 182);
		contentPane.add(responsesList);
		
		resultLabel = new JLabel("");
		resultLabel.setBounds(121, 25, 153, 25);
		resultLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(resultLabel);
		
		JButton calcPassButton = new JButton("Pass");
		calcPassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_calcPassButton_actionPerformed(arg0);
			}
		});
		calcPassButton.setBounds(121, 61, 89, 23);
		contentPane.add(calcPassButton);
		
		JButton calcCorrectButton = new JButton("Correct");
		calcCorrectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_calcCorrectButton_actionPerformed(e);
			}
		});
		calcCorrectButton.setBounds(121, 91, 89, 23);
		contentPane.add(calcCorrectButton);
		
		JButton calcIncorrectButton = new JButton("Incorrect");
		calcIncorrectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_calcIncorrectButton_actionPerformed(e);
			}
		});
		calcIncorrectButton.setBounds(121, 125, 89, 23);
		contentPane.add(calcIncorrectButton);
		
		JButton listCorrectButton = new JButton("List Incorrect");
		listCorrectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_listCorrectButton_actionPerformed(e);
			}
		});
		listCorrectButton.setBounds(121, 159, 153, 23);
		contentPane.add(listCorrectButton);
		
		questNumLabel = new JLabel("#0:");
		questNumLabel.setBounds(0, 235, 38, 14);
		contentPane.add(questNumLabel);
		
		inputAnswerTextField = new JTextField();
		inputAnswerTextField.setBounds(42, 232, 52, 20);
		contentPane.add(inputAnswerTextField);
		inputAnswerTextField.setColumns(10);
		
		prevButton = new JButton("Prev");
		prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_prevButton_actionPerformed(e);
			}
		});
		prevButton.setEnabled(false);
		prevButton.setBounds(121, 197, 61, 23);
		contentPane.add(prevButton);
		
		nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_nextButton_actionPerformed(e);
			}
		});
		nextButton.setBounds(121, 231, 61, 23);
		contentPane.add(nextButton);
		
		DriverExamObjMapper mapper = new DriverExamObjMapper("DriverExam.txt");
		this.exam = mapper.getDriverExam();
		this.respnsesListModel = exam.getAnswers();
		responsesList.setModel(this.respnsesListModel);
		
	}
	protected void do_calcPassButton_actionPerformed(ActionEvent arg0) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >+ 0) {
			resultLabel.setText("Invalid response #" + Integer.toString(invalid));
			responsesList.setSelectedIndex(invalid);;
		}
		else{ 
			if (exam.passed()) resultLabel.setText("You passed");
			else resultLabel.setText("You failed");
		}
	}
	protected void do_calcCorrectButton_actionPerformed(ActionEvent e) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >+ 0) {
			resultLabel.setText("Invalid response #" + Integer.toString(invalid));
			responsesList.setSelectedIndex(invalid);;
		}
		else{ 
			resultLabel.setText(Integer.toString(exam.totalCorrect()));
		}
	}
	protected void do_calcIncorrectButton_actionPerformed(ActionEvent e) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >+ 0) {
			resultLabel.setText("Invalid response #" + Integer.toString(invalid));
			responsesList.setSelectedIndex(invalid);;
		}
		else{ 
			resultLabel.setText(Integer.toString(exam.totalIncorrect()));
		}
	}
	protected void do_listCorrectButton_actionPerformed(ActionEvent e) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >+ 0) {
			resultLabel.setText("Invalid response #" + Integer.toString(invalid));
			responsesList.setSelectedIndex(invalid);;
		}
		else{ 
			String output = "";
			int [] missed = exam.questionsMissed();
//			while (i < missed.length && missed[i] > 0) {
//				output += missed[i];
//				i++;
//			}
			for(int i = 0; i< missed.length; i++)
			{
				if(missed[i] !=0)
				{
					output += Integer.toString(missed[i]) + ", ";
				}
			}
			resultLabel.setText(output);
		}
	}
	protected void do_prevButton_actionPerformed(ActionEvent arg0) {
		this.respnsesListModel.setElementAt(
				inputAnswerTextField.getText().toUpperCase(), 
				responsesList.getSelectedIndex());
        responsesList.setSelectedIndex(responsesList.getSelectedIndex() - 1);
        questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
        inputAnswerTextField.setText((String)responsesList.getSelectedValue());    

        nextButton.setEnabled(true);
        if (responsesList.getSelectedIndex() == 0) 
            prevButton.setEnabled(false);
        inputAnswerTextField.requestFocus();
	}
	protected void do_nextButton_actionPerformed(ActionEvent e) {
		this.respnsesListModel.setElementAt(
				inputAnswerTextField.getText().toUpperCase(), 
				responsesList.getSelectedIndex());
        responsesList.setSelectedIndex(responsesList.getSelectedIndex() + 1);
        questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
        inputAnswerTextField.setText((String)responsesList.getSelectedValue());
        
        prevButton.setEnabled(true);
        if (responsesList.getSelectedIndex() == respnsesListModel.getSize() - 1)
            nextButton.setEnabled(false);
        inputAnswerTextField.requestFocus();
	}
	protected void do_responsesList_valueChanged(ListSelectionEvent arg0) {
		questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
		inputAnswerTextField.setText((String)responsesList.getSelectedValue());    
		prevButton.setEnabled(true);
		nextButton.setEnabled(true);
		if (responsesList.getSelectedIndex() == respnsesListModel.getSize() - 1)
			nextButton.setEnabled(false);
		if (responsesList.getSelectedIndex() == 0) 
			prevButton.setEnabled(false);
		inputAnswerTextField.requestFocus();        
	}
	protected void do_inputAnswerTextField_focusGained(FocusEvent arg0){
		inputAnswerTextField.selectAll();
	}
}