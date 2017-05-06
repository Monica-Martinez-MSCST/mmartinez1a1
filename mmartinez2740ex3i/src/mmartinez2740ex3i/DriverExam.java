package mmartinez2740ex3i;

import javax.swing.DefaultListModel;

public class DriverExam {
	private char[] answers;
	private char[] responses;
//  private char[] responses = {'B', 'D', 'A', 'A', 'C', 'A', 'B', 'A', 'C', 'D'};
//	private char[] responses = {'C', 'A', 'D', 'B', 'B', 'Z', 'B', 'A', 'C', 'D'};
	private final double requiredPct = 0.7;
	public DriverExam(char[] answers)
	{
		this.answers = new char[answers.length];
		for (int index = 0; index < answers.length; index++)
		{
			this.answers[index] = answers[index];
		}
		
			
	}
	
	public DriverExam(DefaultListModel answers)
	{
		this.answers = new char [answers.getSize()];
		for (int i = 0; i < answers.getSize(); i++) {
			String r = (String) answers.get(i);
			this.answers[i] = r.charAt(0);
		}
	}
	
	public void setResponses(DefaultListModel responses) 
	{
		this.responses = new char [responses.getSize()];
		for (int i = 0; i < responses.getSize(); i++) {
			String r = (String) responses.get(i);
			this.responses[i] = r.charAt(0);
		}
	}
	
	public DefaultListModel getAnswers() 
	{
		DefaultListModel answerListModel = new DefaultListModel();
		for (int index = 0; index < answers.length; index++)
		{
			//this.answers[index] = answers[index];
			answerListModel.addElement(Character.toString(answers[index]));
		}
		return answerListModel;
	}
	
	public int validate()
	{
		int count = 0;
		while(count < this.responses.length)
		{
			if(this.responses[count] == 'A' || 
					this.responses[count] == 'B' || 
					this.responses[count] == 'C' || 
					this.responses[count] == 'D')
			{
			}
			else
			{
				return count + 1; 
			}
			count++;
		}
		return -1;
	}
	
	public boolean passed()
	{
		int totalCorrectAns = totalCorrect();
		double percentage = ((double)totalCorrectAns) / this.answers.length;
		if(percentage >= requiredPct)
		{
			return true;
		}
		else
		{
			return false; 	
		}
		
	}
	
	public int totalCorrect()
	{
		int totalCorrectAns = 0; 
		if(this.answers.length == this.responses.length)
		{
			for(int i = 0; i < this.answers.length; i++)
			{
				if(this.answers[i] == this.responses[i])
				{
					totalCorrectAns++;
				}
			}
		}
		else
		{
			totalCorrectAns = -1;
		}
		
		return totalCorrectAns;
	}
	
	public int totalIncorrect()
	{
		int totalIncorrectAns = 0; 
		if(this.answers.length == this.responses.length)
		{
			for(int i = 0; i < this.answers.length; i++)
			{
				if(this.answers[i] != this.responses[i])
				{
					totalIncorrectAns++;
				}
			}
		}
		else
		{
			totalIncorrectAns = -1;
		}
		return totalIncorrectAns;
	}
	
	public int[] questionsMissed()
	{
		int[] missedQuestions = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		if(this.answers.length == responses.length)
		{
			for(int i = 0; i < this.answers.length; i++)
			{
				if(this.answers[i] != this.responses[i])
				{
					missedQuestions[i] = i+1;
				}
			}
		}
		return missedQuestions;
	}
}
