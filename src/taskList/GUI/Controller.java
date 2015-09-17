package taskList.GUI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import taskList.definition.PrintTask;
import taskList.definition.Task;
import taskList.enumerations.RepetitionType;
import taskList.enumerations.Status;
import taskList.implementation.PrintTaskModel;
import taskList.implementation.SingleTaskModel;
import taskList.implementation.TaskListModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
public class Controller {

	@FXML TextField txtDescription;
	@FXML ComboBox<RepetitionType> cbRepeat;
	@FXML DatePicker dtDueDate;
	@FXML Button btnSave;
	@FXML Button btnAbbrechen;
	@FXML TableView<PrintTask> tvTasks;
	@FXML TableColumn<PrintTask, String> tcDescription;
	@FXML TableColumn<PrintTask, Date> tcDueDate;
	@FXML TableColumn<PrintTask, Status> tcStatus;
	@FXML TextField txtSearchDescription;
	@FXML ComboBox<Status> cbSearchState;
	@FXML DatePicker dtSearchDueDate;
	@FXML Button btnSearch;
	private TaskListModel tlm;
	private Task currentTask;
	@FXML ComboBox<Status> cbState;
	public void init(TaskListModel tlm) {
		this.tlm=tlm;
		tlm.AddTask(new SingleTaskModel("erster Termin!", new Date(), Status.open));
		tlm.AddTask(new SingleTaskModel("zweiter Termin!", new Date(), Status.open));
		tlm.AddTask(new SingleTaskModel("dritter Termin!", new Date(), Status.open));
		tlm.AddTask(new SingleTaskModel("vierter Termin!", new Date(), Status.open));
		tlm.AddTask(new SingleTaskModel("fünfter Termin!", new Date(), Status.open));
		tcDescription.setCellValueFactory(
                new PropertyValueFactory<PrintTask, String>("description"));
		tcDueDate.setCellValueFactory(
                new PropertyValueFactory<PrintTask, Date>("dueDate"));
		tcStatus.setCellValueFactory(
                new PropertyValueFactory<PrintTask, Status>("Status"));
		tvTasks.setItems(tlm.getPrintTasks());
		tvTasks.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		       	currentTask = (Task) newSelection;
		    }
		});
		
		cbSearchState.getItems().addAll(Status.values());
		cbRepeat.getItems().addAll(RepetitionType.values());
		cbState.getItems().addAll(Status.values());
		Date d = new Date();
		
		//setTaskValues("",RepetitionType.once,Calendar., Status.open)
	}
	private void setTaskValues(String description, RepetitionType repetitionType, LocalDate dueDate, Status status){
		txtDescription.setText(description);
		cbRepeat.setValue(repetitionType);
		dtDueDate.setValue(dueDate);
	}

}
