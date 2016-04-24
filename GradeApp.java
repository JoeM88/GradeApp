import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.util.*;
/* Title: GradeApp.java
 * Abstract: Program to add students, check students by id and see a list of all students with their exam average.
 * Author: Joseph Molina
 * 4/22/16
 * ID: 3388
 */
public class GradeApp {
	
	public static void main(String[] args) {
		JFrame frame = new ApplicationFrame();
        frame.setVisible(true);
	}
}
class Student
{
	private String lastName;
	private Integer studentId;
	private double exam1;
	private double exam2;
	private double examsAverage;
	
	Student(){
		lastName = "";
		studentId = 0;
		exam1 = 0.0;
		exam2 = 0.0;
		examsAverage = 0.0;
	}
	
	Student(String lastName, Integer studentId, double exam1, double exam2){
		this.lastName =lastName;
		this.studentId = studentId;
		this.exam1 = exam1;
		this.exam2 = exam2;
	}
	
	public String toString(){
		
		return(getLastName() + "  " + getStudentID() + "       " + getExamsAverage());
	}
	
/************ Setters **************************/
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public void setStudentId(Integer studentId){
		this.studentId = studentId;
	}
	
	public void setExam1(double exam1){
		this.exam1 = exam1;
	}
	
	public void setExam2(double exam2){
		this.exam2 = exam2;
	}
	
	public void setExamAverage(double exam1, double exam2){
		this.examsAverage = (exam1 + exam2)/2;
	}
/*************** Getters *****************/
    public String getLastName(){
    	return lastName;
    }
    
    public int getStudentID(){
    	return studentId;
    }
    
    public double getExam1(){
    	return exam1;
    }
    
    public double getExam2(){
    	return exam2;
    }
    
    public double getExamsAverage(){
    	return examsAverage;
    }
	
}
//Application frame 
class ApplicationFrame extends JFrame
{
    public ApplicationFrame()
    {
        setTitle("Grade App");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new ApplicationPanel();
        this.add(panel);
        this.pack();
        centerWindow(this);
    }

    private void centerWindow(Window w)
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        setLocation((d.width-w.getWidth())/2, (d.height-w.getHeight())/2);
    }
    
}

//Application Panel
class ApplicationPanel extends JPanel implements ActionListener
{
  private JButton      
                  exitButton,
                  helpButton,
                  dataButton,
                  reportButton;
  
  public static HashMap<Integer, Student> students = new HashMap<Integer,Student>();
  
  public ApplicationPanel()
  {
  
  	setLayout(new FlowLayout());
/******************** Adding all the buttons into the panel and then into the flowlayout. ********************/
  	JPanel buttonPanel = new JPanel();
  	helpButton = new JButton("Help");
  	helpButton.addActionListener(this);
  	buttonPanel.add(helpButton);
  	
  	dataButton = new JButton("Enter Data");
  	dataButton.addActionListener(this);
  	buttonPanel.add(dataButton);
  	
  	reportButton = new JButton("Report");
  	reportButton.addActionListener(this);
  	buttonPanel.add(reportButton);
  	
  	exitButton = new JButton("Exit");
  	exitButton.addActionListener(this);
  	buttonPanel.add(exitButton);
//Adding all the buttons that are in the button panel at the end. 	
  	add(buttonPanel);      
  }

  
  // a  method for setting grid bag constraints
  private GridBagConstraints getConstraints(int gridx, int gridy,
                                            int gridwidth, int gridheight, 
                                            int anchor)
  {
      GridBagConstraints c = new GridBagConstraints();
      c.insets = new Insets(5, 5, 5, 5);
      c.ipadx = 0;
      c.ipady = 0;
      c.gridx = gridx;
      c.gridy = gridy;
      c.gridwidth = gridwidth;
      c.gridheight = gridheight;
      c.anchor = anchor;
      return c;
  }

  
  public void actionPerformed(ActionEvent e)
  {	 boolean checker = students.isEmpty();
      Object source = e.getSource();
      if (source == exitButton)
          System.exit(0);
      else if(source == helpButton){
      	HelpFrame frame1 = new HelpFrame();
      	//JFrame frame1 = new HelpFrame();
          frame1.setVisible(true);
      }
      
      else if(source == dataButton){
      	DataFrame frame2 = new DataFrame(students);
      	frame2.setVisible(true);
      }
      
      else if(source == reportButton){
    	 
    	  if(checker == true)
    	  {
    		  noStudentFrame frame4 = new  noStudentFrame();
        	  frame4.setVisible(true);
    	  }
    	  else if(checker == false){
    		  reportStudentsFrame frame5 = new reportStudentsFrame(students);
    		  frame5.setVisible(true);
    	  }
      }
     
  }
}

//Help Frame class.
class HelpFrame extends JFrame 
{
	public HelpFrame()
	{
		setTitle("Help");
		setResizable(false);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      JPanel panel = new HelpPanel();
      panel.setBorder(new EmptyBorder(10,22,10,22));
      this.add(panel);
      this.pack();
      centerWindow(this);
	}
	
	private void centerWindow(Window w)
  {
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension d = tk.getScreenSize();
      setLocation((d.width-w.getWidth())/2, (d.height-w.getHeight())/2);
  }
}
//Help Panel 
class HelpPanel extends JPanel implements ActionListener
{
	private JLabel nameLabel, versionNumber;
	
	private JButton okButton;
	
	public HelpPanel()
	{
		setLayout(new GridLayout(3,1));
		nameLabel = new JLabel("Name: Joseph Molina");
		add(nameLabel);	
		versionNumber = new JLabel("Version: 1");
		add(versionNumber);
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		add(okButton);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 Object source = e.getSource();
		if(source == okButton){
			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			 topFrame.setVisible(false);
    }
	}
}


//Data frame class.
class DataFrame extends JFrame
{
	public DataFrame(Map<Integer, Student> students)
	{
		setTitle("Data Form");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new DataPanel(students);
        this.add(panel);
        this.pack();
        centerWindow(this);
	}
	
	  private void centerWindow(Window w)
	    {
	        Toolkit tk = Toolkit.getDefaultToolkit();
	        Dimension d = tk.getScreenSize();
	        setLocation((d.width-w.getWidth())/2, (d.height-w.getHeight())/2);
	    }
}

class DataPanel extends JPanel implements ActionListener
{
	private JLabel infoLabel, lastName, iDNumber, exam1, exam2;
	
	private JButton submitButton, clearButton;
	
	private JTextField lastNameTextField,
						idNumberTextField,
						exam1TextField,
						exam2TextField;
	
	//public ArrayList<String> lastNames = new ArrayList<String>();

	//HashMap<Integer, Student> students = new HashMap<Integer, Student>();
	
	public DataPanel(Map<Integer, Student> students)
	{	//THis is the layout for the overall panel.
		setLayout(new BorderLayout());
		
		infoLabel = new JLabel ("Please enter the student's information:");
		add(infoLabel, BorderLayout.NORTH);
		
/************ Adding labels into studentInfo panel ******************/
		
		//Creating the panel for the gridlayout.
		JPanel studentInfo = new JPanel(new GridLayout(4,2));
		//Adding the lables into the gridlayout
		lastName = new JLabel("Last Name: ");
		studentInfo.add(lastName);
		lastNameTextField = new JTextField();
		studentInfo.add(lastNameTextField);
		
		//Adding the IDNumber label into the gridlayout
		iDNumber  = new JLabel("ID Number: ");
		studentInfo.add(iDNumber);
		idNumberTextField = new JTextField();
		studentInfo.add(idNumberTextField);
		
		//Adding the first exam into the gridlayout
		exam1 = new JLabel("Exam 1 Score: ");
		studentInfo.add(exam1);
		exam1TextField = new JTextField();
		studentInfo.add(exam1TextField);
		
		//Adding the second exam into the gridlayout
		exam2 = new JLabel("Exam 2 Score: ");
		studentInfo.add(exam2);
		exam2TextField = new JTextField();
		studentInfo.add(exam2TextField);
//Adding the student Info Panel to the entire panel
		add(studentInfo, BorderLayout.CENTER);
		
/*********** Adding the option buttons in the bottom of the frame ************/		
	
		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		clearButton = new JButton("Clear");
		JPanel bottomOptionsPanel = new JPanel(new FlowLayout());
		bottomOptionsPanel.add(submitButton);
		clearButton.addActionListener(this);
		bottomOptionsPanel.add(clearButton);
		add(bottomOptionsPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == submitButton){
			//HashMap<Integer, Student> students = null;
			if(!lastNameTextField.getText().isEmpty() && !idNumberTextField.getText().isEmpty() && !exam1TextField.getText().isEmpty() && !exam2TextField.getText().isEmpty()){
				
				String studentLastName = lastNameTextField.getText();
				String tempStudentId = "";
				Integer realStudentId = 0;
				String message = "";
				boolean okayToAdd = false;
				double realExam1 = 0.0;
				double realExam2 = 0.0;
				boolean checker0 = false;
				boolean checker1 = false;
				boolean checker2 = false;
				boolean checker3 = false;
				Student temp = new Student();
				
				
				if(studentLastName.matches(".*\\d+.*"))
				{
					message = "You must enter a valid name ";
					invalidInputFrame frame5 = new invalidInputFrame(message);
					frame5.setVisible(true);
					checker0 = false;
				}
				else{
					checker0 = true;
				}
					
			//Checking if the user inputs an integer value for the id.
					try{
						Integer.parseInt(idNumberTextField.getText());
						
						tempStudentId = idNumberTextField.getText();
						realStudentId = Integer.parseInt(tempStudentId);
						System.out.println("Student ID is: " + realStudentId);
						checker1 = true;
					}catch(NumberFormatException e1){
						message = "You must enter an integer value for the student Id";
						invalidInputFrame frame5 = new invalidInputFrame(message);
						frame5.setVisible(true);
					}
					
					//Checking the user enters a double value for the exams.
					try{
						Double.parseDouble(exam1TextField.getText());
						
						String tempExam1 = exam1TextField.getText();
						realExam1 = Double.parseDouble(tempExam1);
						
						Double.parseDouble(exam2TextField.getText());
						String tempExam2 = exam2TextField.getText();
						realExam2 = Double.parseDouble(tempExam2);
						
						checker2 = true;
						checker3 = true;
					}catch(NumberFormatException e2){
						message = "You must enter a number for the exams.";
						invalidInputFrame frame5 = new invalidInputFrame(message);
						frame5.setVisible(true);
					}
					
					
					//Checking if all the proper input validations were met.
					if(checker0 == true && checker1 == true && checker2 == true && checker3 == true){
						okayToAdd =true;
					}
				
					if(ApplicationPanel.students.get(realStudentId) != null && okayToAdd == true){
						takenIdFrame frame6 = new takenIdFrame();
						frame6.setVisible(true);
					}
					else if(!(ApplicationPanel.students.get(realStudentId) != null) && okayToAdd == true){
					temp = new Student(studentLastName, realStudentId, realExam1, realExam2);
					temp.setExamAverage(realExam1, realExam2);
					ApplicationPanel.students.put(realStudentId, temp);
				
					//Adding the student object into the hashmap.
					AverageFrame avgFrame = new AverageFrame(temp);
					avgFrame.setVisible(true);
				}
				
		//After submitting clearing out the screen.
				lastNameTextField.setText("");
				idNumberTextField.setText("");
				exam1TextField.setText("");
				exam2TextField.setText("");
				
				Iterator iterator = ApplicationPanel.students.keySet().iterator();
/********************************Printing out all the students information **********************************************************/
				while(iterator.hasNext()){
					Integer cursor = (Integer) iterator.next();
					System.out.println("Last Name: " + ApplicationPanel.students.get(cursor).getLastName());
					System.out.println("Student ID: " + ApplicationPanel.students.get(cursor).getStudentID());
					System.out.println("Exam 1 Score: " + ApplicationPanel.students.get(cursor).getExam1());
					System.out.println("Exam 2 Score: " + ApplicationPanel.students.get(cursor).getExam2());
			}
		}
			else if(!idNumberTextField.getText().isEmpty() && lastNameTextField.getText().isEmpty() && exam1TextField.getText().isEmpty() && exam2TextField.getText().isEmpty()){
				
				String tempStudentId = idNumberTextField.getText();
				Integer realStudentId = Integer.parseInt(tempStudentId);
	
				//Checking if the id exists in the hashmap.
				if(ApplicationPanel.students.containsKey(realStudentId)){
					//System.out.println("went in");
					StudentInfoFrame frame3 = new StudentInfoFrame(tempStudentId, ApplicationPanel.students);
					//System.out.println("Setting the visibility");
					frame3.setVisible(true);
					System.out.println("displaying the frame");
				}
				else {
					nonStudentFrame frame4 = new nonStudentFrame();
					frame4.setVisible(true);
				}
		   }
	}
		else if(source == clearButton){
			lastNameTextField.setText("");
			idNumberTextField.setText("");
			exam1TextField.setText("");
			exam2TextField.setText("");
		}
	}
}
//Frame to be displayed if the student id is already taken.
class takenIdFrame extends JFrame
{
	public takenIdFrame(){
		setTitle("Taken Id");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		takenIdPanel panel = new takenIdPanel();
		this.add(panel);
		this.pack();
		centerWindow(this);
	}
	
	
	private void centerWindow(Window w)
	 {
	     Toolkit tk = Toolkit.getDefaultToolkit();
	      Dimension d = tk.getScreenSize();
	      setLocation((d.width-w.getWidth())/2, (d.height-w.getHeight())/2);
	 }
}


class takenIdPanel extends JPanel implements ActionListener
{
	private JLabel  takenIdMessage;
	private JButton okButton;
	
	public takenIdPanel()
	{
		setLayout(new BorderLayout());
		JPanel displayMessage = new JPanel(new GridLayout(2,1));
		takenIdMessage = new JLabel("Student ID is taken");
		displayMessage.add(takenIdMessage);
		
		
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		displayMessage.add(okButton);
		
		add(displayMessage, BorderLayout.CENTER);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source == okButton)
		{
			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			 topFrame.setVisible(false);	 
		}
	}
}
//Invalid input frame 
class invalidInputFrame extends JFrame
{
	public invalidInputFrame(String message)
	{
		setTitle("Invalid Input");
	setResizable(false);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	invalidInputPanel panel = new invalidInputPanel(message);
	this.add(panel);
	this.pack();
	centerWindow(this);
		
	}
	
	private void centerWindow(Window w)
	 {
	     Toolkit tk = Toolkit.getDefaultToolkit();
	      Dimension d = tk.getScreenSize();
	      setLocation((d.width-w.getWidth())/2, (d.height-w.getHeight())/2);
	 }
}

class invalidInputPanel extends JPanel implements ActionListener
{
	private JLabel invalidMessage;
	private JButton okButton;
	
	public invalidInputPanel(String message)
	{
		setLayout(new BorderLayout());
		
		JPanel display = new JPanel(new GridLayout(2,1));
		invalidMessage = new JLabel(message);
		display.add(invalidMessage);
		
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		display.add(okButton);
		add(display, BorderLayout.CENTER);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source == okButton)
		{
			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			 topFrame.setVisible(false);	 
		}
	}
}


//None student Frame
class nonStudentFrame extends JFrame
{
	public nonStudentFrame()
	{
		setTitle("Not a student");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		nonStudentPanel panel = new nonStudentPanel();
		this.add(panel);
		this.pack();
		centerWindow(this);
	}
	private void centerWindow(Window w)
	 {
	     Toolkit tk = Toolkit.getDefaultToolkit();
	      Dimension d = tk.getScreenSize();
	      setLocation((d.width-w.getWidth())/2, (d.height-w.getHeight())/2);
	 }
	
}

class nonStudentPanel extends JPanel implements ActionListener
{
	private JLabel nonStudentLabel;
	private JButton okButton;
	
	
	public nonStudentPanel(){
		setLayout( new BorderLayout());
		JPanel info = new JPanel(new GridLayout(2,1));
		nonStudentLabel = new JLabel("Student id does not exist.");
		info.add(nonStudentLabel);
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		info.add(okButton);
		
		add(info, BorderLayout.CENTER);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source == okButton)
		{
			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			 topFrame.setVisible(false);	 
		}
		
	}
}
//Student info via id
class StudentInfoFrame extends JFrame
{
	public StudentInfoFrame(String tempStudentId, Map<Integer, Student> students)
	{	
		setTitle("Student Info");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		StudentInfoPanel panel = new StudentInfoPanel(tempStudentId, ApplicationPanel.students);
		this.add(panel);
		this.pack();
		centerWindow(this);
	}
	
	private void centerWindow(Window w)
	 {
	     Toolkit tk = Toolkit.getDefaultToolkit();
	      Dimension d = tk.getScreenSize();
	      setLocation((d.width-w.getWidth())/2, (d.height-w.getHeight())/2);
	 }
}

class StudentInfoPanel extends JPanel implements ActionListener
{
	private JLabel info,
					lastNameLabel,
					averageLabel,
					studentLastName,
					scoreAverage;
	
	private JButton okButton;
	
	public StudentInfoPanel(String tempStudentId, Map<Integer, Student>students)
	{
		setLayout(new BorderLayout());
		JPanel information  = new JPanel(new GridLayout(2,2));
		
		lastNameLabel = new JLabel("Last Name: ");
		information.add(lastNameLabel);
		int key  = Integer.parseInt(tempStudentId);
		String tempLastname  = ApplicationPanel.students.get(key).getLastName();
		studentLastName = new JLabel(tempLastname);
		information.add(studentLastName);
		
		averageLabel = new JLabel("Score Average: ");
		information.add(averageLabel);
		double tempAverage = ApplicationPanel.students.get(key).getExamsAverage();
		String realAverage = Double.toString(tempAverage);
		
		scoreAverage = new JLabel(realAverage);
		information.add(scoreAverage);
		//Adding everything together.
		add(information, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
			
	}
	
}

class reportStudentsFrame extends JFrame
{
	public reportStudentsFrame (Map<Integer, Student> students)
	{
		System.out.println("started the frame.");
		setTitle("Reports");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		System.out.println("about to pass the hashmap.");
		reportStudentsPanel panel = new reportStudentsPanel(students);
		System.out.println("Passed the heap!");
		this.add(panel);
		this.pack();
		centerWindow(this);
		System.out.println("created the frame.");
	}
	
	private void centerWindow(Window w)
	 {
	     Toolkit tk = Toolkit.getDefaultToolkit();
	      Dimension d = tk.getScreenSize();
	      setLocation((d.width-w.getWidth())/2, (d.height-w.getHeight())/2);
	 }
}

class reportStudentsPanel extends JPanel implements ActionListener
{
	private JLabel name, idNumber, average, overallAverage, numberOfStudentsLabel, numStudents;
	private JButton okButton;
	private JLabel title;
	
	private int rows, columns;
	
	private JTextArea textArea ;
	
	
	public reportStudentsPanel(Map<Integer, Student> students)
	{System.out.println("Made it into the reportStudentsPanel");
		
		setLayout(new BorderLayout());
		rows = students.size();
		JPanel reports = new JPanel(new GridLayout(rows, 2));
		JPanel title = new JPanel(new GridLayout(1,1));
	
		
		name = new JLabel("Name");
		title.add(name);
		
		idNumber = new JLabel("ID Number");
		title.add(idNumber);
		
		
		add(title, BorderLayout.NORTH);
		
		JPanel bottomInfo = new JPanel(new GridLayout(1,4));
		
		numberOfStudentsLabel = new JLabel("Number of Students: ");
		bottomInfo.add(numberOfStudentsLabel);
		
		int num = students.size();
		String realsize = String.valueOf(num);
		numStudents = new JLabel(realsize);
		bottomInfo.add(numStudents);
		
		
		Iterator iterator = ApplicationPanel.students.keySet().iterator();
	    double totalAverage = 0.0;
	    String realOverallAverage = "";
	    
		while(iterator.hasNext()){
			Integer cursor = (Integer) iterator.next();
			totalAverage += ApplicationPanel.students.get(cursor).getExamsAverage();
			totalAverage = totalAverage/ApplicationPanel.students.size();
		}
		
		realOverallAverage = String.valueOf(totalAverage);
		
		
		average = new JLabel("Average: " + realOverallAverage);
		bottomInfo.add(average);
		bottomInfo.setBorder(new EmptyBorder(10,10,10,10));
		//Adding the bottom info panel to the bottom of the frame.
		add(bottomInfo, BorderLayout.SOUTH);

		for(Integer key : students.keySet()){
			int objectKey = key;
			JLabel name = new JLabel(students.get(objectKey).getLastName());
			reports.add(name);
			
			int tempId = students.get(objectKey).getStudentID();
			String realId = String.valueOf(tempId);
			JLabel id = new JLabel(realId);
			reports.add(id);
		}
		add(reports, BorderLayout.CENTER);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class noStudentFrame extends JFrame
{
	public noStudentFrame(){
		setTitle("Student Info");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		noStudentPanel panel = new noStudentPanel();
		panel.setBorder(new EmptyBorder(10,10,10,10));
		this.add(panel);
		this.pack();
		centerWindow(this);
	}
	private void centerWindow(Window w)
	 {
	     Toolkit tk = Toolkit.getDefaultToolkit();
	      Dimension d = tk.getScreenSize();
	      setLocation((d.width-w.getWidth())/2, (d.height-w.getHeight())/2);
	 }
	
}

class noStudentPanel extends JPanel implements ActionListener
{
	private JLabel noStudentsLabel;
	
	private JButton okButton;
	
	public noStudentPanel()
	{
		setLayout(new BorderLayout());
		
		noStudentsLabel = new JLabel("There are no students.");
		add(noStudentsLabel, BorderLayout.NORTH);
		
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		add(okButton, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source == okButton)
		{
			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			 topFrame.setVisible(false);	
		}
	}
}

//Average Frame
class AverageFrame extends JFrame
{
	public AverageFrame(Student temp)
	{
		setTitle("Average");
		setResizable(false);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    JPanel panel = new AveragePanel(temp);
	    this.add(panel);
	    this.pack();
	    centerWindow(this);
	}
	
	private void centerWindow(Window w)
	 {
	     Toolkit tk = Toolkit.getDefaultToolkit();
	      Dimension d = tk.getScreenSize();
	      setLocation((d.width-w.getWidth())/2, (d.height-w.getHeight())/2);
	 }
}


class AveragePanel  extends JPanel implements ActionListener
{
	private JLabel confirmation,
					lastNameLabel,
					idNumberLabel,
					avgScoreLabel,
					name,
					number,
					averageScore;
	
	private JButton okButton;
	public AveragePanel(Student temp)
	{
		setLayout(new BorderLayout());
		confirmation = new JLabel("You entered: ");
		add(confirmation, BorderLayout.NORTH);
		
		JPanel studentConfirmationPanel = new JPanel(new GridLayout(3,2));
		lastNameLabel = new JLabel("Last Name: ");
		studentConfirmationPanel.add(lastNameLabel);
		name  = new JLabel(temp.getLastName());
		studentConfirmationPanel.add(name);
		
		idNumberLabel = new JLabel("ID #: ");
		studentConfirmationPanel.add(idNumberLabel);
		int tempName = temp.getStudentID();
		String tempLastName = String.format("%d", tempName);
		number = new JLabel(tempLastName);
		studentConfirmationPanel.add(number);
		
		avgScoreLabel = new JLabel("Average Score: ");
		studentConfirmationPanel.add(avgScoreLabel);
		double scoretemp = temp.getExamsAverage();
		String tempExamAverage = String.valueOf(scoretemp);
		averageScore = new JLabel(tempExamAverage);
		studentConfirmationPanel.add(averageScore);
		
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		add(studentConfirmationPanel, BorderLayout.CENTER);
		add(okButton, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source == okButton)
		{
			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			 topFrame.setVisible(false);
			 
		}
	}
}



