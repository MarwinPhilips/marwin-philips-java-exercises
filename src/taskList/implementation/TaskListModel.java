package taskList.implementation;

import java.util.ArrayList;

import taskList.definition.PrintTask;
import taskList.definition.Task;
import taskList.definition.TaskList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskListModel implements TaskList {
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private ObservableList<PrintTask> printTasks;
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
		tasks.forEach(x->printTasks.addAll(x.getPrintTasks()));
	}
}