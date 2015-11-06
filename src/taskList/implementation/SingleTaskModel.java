package taskList.implementation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import taskList.definition.PrintTask;
import taskList.definition.Task;
import taskList.enumerations.RepetitionType;
import taskList.enumerations.Status;

public class SingleTaskModel implements Task, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RepetitionType repetitionType = RepetitionType.once;
	private Status status;
	private LocalDate dueDate;
	private String description;
	public RepetitionType getRepetitionType() {
		return repetitionType;
	}
	public void setRepetitionType(RepetitionType repetitionType) {
		this.repetitionType = repetitionType;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public List<PrintTask> getPrintTasks() {
		ArrayList<PrintTask> ret = new ArrayList<PrintTask>();
		ret.add(new PrintTaskModel(this, status, dueDate, description));
		return ret;
	}
	
	public SingleTaskModel(){
		
	}
	public SingleTaskModel(String description, LocalDate dueDate, Status status){
		this.status=status;
		this.dueDate=dueDate;
		this.description=description;
	}
	

}
