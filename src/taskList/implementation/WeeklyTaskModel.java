package taskList.implementation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import taskList.definition.PrintTask;
import taskList.definition.Task;
import taskList.enumerations.RepetitionType;
import taskList.enumerations.Status;

public class WeeklyTaskModel extends SingleTaskModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ArrayList<SingleTaskModel> doneTasks;

	public ArrayList<SingleTaskModel> getDoneTasks() {
		return doneTasks;
	}
	@Override
	public List<PrintTask> getPrintTasks() {
		// TODO Auto-generated method stub
		return null;
	}
	public WeeklyTaskModel(){
		doneTasks= new ArrayList<SingleTaskModel>();
	}
	
	public WeeklyTaskModel(String description, Date dueDate, Status state, ArrayList<SingleTaskModel> doneTasks){
		super.setStatus(state);
		setDueDate(dueDate);
		setDescription(description);
		doneTasks = new ArrayList<SingleTaskModel>(doneTasks);
	}
	
	public void addDoneTask(SingleTaskModel doneTask){
		this.doneTasks.add(doneTask);
	}
	public void removeDoneTask(SingleTaskModel doneTask){
		this.doneTasks.remove(doneTask);
	}
}
