package taskList.implementation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import taskList.definition.PrintTask;
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
		ArrayList<PrintTask> ret = new ArrayList<PrintTask>();
		ret.add(new PrintTaskModel(this,this.getStatus(),this.getDueDate(),this.getDescription()));
		doneTasks.forEach(x->ret.add(new PrintTaskModel(this,x.getStatus(),x.getDueDate(),x.getDescription())));
		LocalDate comparer = new GregorianCalendar(getDueDate().getYear(), getDueDate().getMonthValue(), getDueDate().getDayOfMonth()).
				toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate now = new GregorianCalendar().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		while(comparer.isBefore(now))
		{
			boolean found = false;
			for(SingleTaskModel s: doneTasks){
				found=s.getDueDate().equals(comparer);
				if(found){
					ret.add(new PrintTaskModel(this,Status.open,comparer,this.getDescription()));
					break;
				}
			}
			comparer.plusDays(7l);
		}
		while(doneTasks.stream().filter(x->x.getDueDate().isAfter(comparer)).count()>0){
			comparer.plusDays(7l);
		}
		ret.add(new PrintTaskModel(this,Status.open,comparer,getDescription()));
		return ret;
	}
	public WeeklyTaskModel(){
		doneTasks= new ArrayList<SingleTaskModel>();
	}
	
	public WeeklyTaskModel(String description, LocalDate dueDate, Status state, ArrayList<SingleTaskModel> doneTasks){
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
