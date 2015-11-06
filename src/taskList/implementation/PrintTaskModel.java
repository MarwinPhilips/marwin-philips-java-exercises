package taskList.implementation;

import java.time.LocalDate;
import java.util.Date;

import taskList.definition.PrintTask;
import taskList.definition.Task;
import taskList.enumerations.Status;

public class PrintTaskModel implements PrintTask {
	private Task parentTask;
	private Status status;
	private LocalDate dueDate;
	private String description;
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public LocalDate getDueDate() {
		// TODO Auto-generated method stub
		return dueDate;
	}

	@Override
	public Status getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	public Task getParentTask() {
		return parentTask;
	}
	public PrintTaskModel(Task parentTask, Status status, LocalDate dueDate, String description){
		this.parentTask=parentTask;
		this.status=status;
		this.dueDate=dueDate;
		this.description=description;
	}
}
