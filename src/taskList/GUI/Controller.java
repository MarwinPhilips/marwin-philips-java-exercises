package taskList.GUI;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

	@FXML
	TextField txtDescription;
	@FXML
	ComboBox<RepetitionType> cbRepeat;
	@FXML
	DatePicker dtDueDate;
	@FXML
	Button btnSave;
	@FXML
	Button btnAbbrechen;
	@FXML
	TableView<PrintTask> tvTasks;
	@FXML
	TableColumn<PrintTask, String> tcDescription;
	@FXML
	TableColumn<PrintTask, Date> tcDueDate;
	@FXML
	TableColumn<PrintTask, Status> tcStatus;
	@FXML
	TextField txtSearchDescription;
	@FXML
	ComboBox<Status> cbSearchState;
	@FXML
	DatePicker dtSearchDueDate;
	@FXML
	Button btnSearch;
	
	@FXML
	ComboBox<Status> cbState;
	@FXML
	Button btnNew;
	@FXML Button btnBreakSearch;
	private TaskListModel tlm;
	private PrintTask currentTask;
	
	public void init(TaskListModel tlm) {
		this.tlm = tlm;
		resetTaskValues();
		LocalDate d = new GregorianCalendar().toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDate();
		tlm.AddTask(new SingleTaskModel("erster Termin!", d, Status.open));
		tlm.AddTask(new SingleTaskModel("zweiter Termin!", d, Status.open));
		tlm.AddTask(new SingleTaskModel("dritter Termin!", d, Status.open));
		tlm.AddTask(new SingleTaskModel("vierter Termin!", d, Status.open));
		tlm.AddTask(new SingleTaskModel("fünfter Termin!", d, Status.open));
		tcDescription
				.setCellValueFactory(new PropertyValueFactory<PrintTask, String>(
						"description"));
		tcDueDate
				.setCellValueFactory(new PropertyValueFactory<PrintTask, Date>(
						"dueDate"));
		tcStatus.setCellValueFactory(new PropertyValueFactory<PrintTask, Status>(
				"Status"));
		tvTasks.setItems(tlm.getPrintTasks());
		tvTasks.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldSelection, newSelection) -> {
					if (newSelection != null) {
						setCurrentTask();
					}
				});

		cbSearchState.getItems().addAll(Status.values());
		cbRepeat.getItems().addAll(RepetitionType.values());
		cbState.getItems().addAll(Status.values());

		btnSave.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String description = txtDescription.getText();
				RepetitionType rt = cbRepeat.getValue();
				LocalDate dueDate = dtDueDate.getValue();
				Status status = cbState.getValue();
				if (currentTask != null) {
					tlm.ChangeTask(currentTask, description, rt, dueDate,
							status);
				} else {
					tlm.addNewTask(description, rt, dueDate, status);
				}
				resetTaskValues();
			}

		});

		btnAbbrechen.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				setCurrentTask();
			}
		});
		btnNew.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				resetTaskValues();
			}
		});
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				tlm.setFilterValues(txtSearchDescription.getText(),cbSearchState.getValue(), dtSearchDueDate.getValue());
			}
		});
		btnBreakSearch.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				tlm.setFilterValues("",null, null);
				setDefaultSearchValues();
			}			
		});
	}

	private void setTaskValues(String description,
			RepetitionType repetitionType, LocalDate dueDate, Status status) {
		txtDescription.setText(description);
		cbRepeat.setValue(repetitionType);
		dtDueDate.setValue(dueDate);
		cbState.setValue(status);
	}

	private void resetTaskValues() {
		currentTask = null;
		LocalDate d = new GregorianCalendar().toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDate();
		setTaskValues("", RepetitionType.once, d, Status.open);
	}

	private void setCurrentTask() {
		currentTask = tvTasks.getSelectionModel().getSelectedItem();
		setTaskValues(currentTask.getDescription(), currentTask.getParentTask()
				.getRepetitionType(), currentTask.getDueDate(),
				currentTask.getStatus());
	}
	private void setDefaultSearchValues() {
		txtSearchDescription.setText("");
		cbSearchState.setValue(null);
		dtSearchDueDate.setValue(null);
		
	}
}
