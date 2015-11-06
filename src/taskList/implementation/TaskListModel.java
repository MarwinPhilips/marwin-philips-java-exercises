package taskList.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import taskList.definition.PrintTask;
import taskList.definition.Task;
import taskList.definition.TaskList;
import taskList.enumerations.RepetitionType;
import taskList.enumerations.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskListModel implements TaskList {
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private ObservableList<PrintTask> printTasks;
	private LocalDate dateMax = null;
	private RepetitionType rtFilter = null;
	private Status stateFilter = null;
	private String descriptionFilter = "";
	
	@Override
	public ObservableList<PrintTask> getPrintTasks() {
		return printTasks;
	}
	@Override
	public void AddTask(Task task) {
		tasks.add(task);	
		fillPrintTasks();
	}
	@Override
	public void removeTask(Task task) {
		tasks.remove(task);
		fillPrintTasks();
		
	}
	public TaskListModel(){
		this.printTasks = FXCollections.observableArrayList();
		fillPrintTasks();
	}
	
	private void fillPrintTasks(){
		printTasks.clear();
		ArrayList<PrintTask> notFilteredTasks = new ArrayList<PrintTask>();
		tasks.forEach(x->notFilteredTasks.addAll(x.getPrintTasks()));
		Stream<PrintTask> printTaskStream = notFilteredTasks.stream();
		if(descriptionFilter!="")
		{
			printTaskStream.filter(x->x.getDescription().contains(descriptionFilter));
		}
		if(rtFilter!=null){
			printTaskStream.filter(x->x.getParentTask().getRepetitionType().equals(rtFilter));
		}
		if(stateFilter!=null){
			printTaskStream.filter(x->x.getStatus().equals(stateFilter));
		}
		if(dateMax!=null){
			printTaskStream.filter(x->x.getDueDate().isBefore(dateMax));
		}
		notFilteredTasks.forEach(x->printTasks.add(x));		
	}
	
	public void ChangeTask(PrintTask currentTask, String description,
			RepetitionType rt, LocalDate dueDate, Status status) {		
		Task taskToChange = currentTask.getParentTask();
		//if(taskToChange.getRepetitionType()==RepetitionType.weekly && rt==RepetitionType.weekly){
			
		//}
		fillPrintTasks();
	}
	public void addNewTask(String description, RepetitionType rt,
			LocalDate dueDate, Status status) {
		if(rt==RepetitionType.once){
			tasks.add(new SingleTaskModel(description,dueDate,status));
		}else{
			tasks.add(new WeeklyTaskModel(description, dueDate, status, null));
		}		
		fillPrintTasks();
	}
	public void setFilterValues(String text, Status status, LocalDate dueDate) {
		descriptionFilter=text;
		stateFilter=status;
		dateMax=dueDate;
		fillPrintTasks();
		
	}
}