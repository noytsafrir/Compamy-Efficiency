package view;

import java.io.*;
import java.time.*;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import listeners.*;
import model.*;
import model.Company.*;
import viewEvents.EventMouseEnteredMngButton;
import viewEvents.EventMouseEnteredMngButtonRemove;
import viewEvents.EventMouseExitedMngButton;
import viewEvents.EventMouseExitedMngButtonRemove;
import exceptions.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class CompanyView {
	private Vector<ViewListenable> allListeners;
	private Stage primaryStage;
	private GridPane gpAddRole;
	private GridPane gpChangeDepHours;
	private GridPane gpChangeRollHours;
	private GridPane gpAddEmployee;
	private GridPane gpLinkEmployeeToRoll;
	private GridPane gpChangeEmployeeInRoll;
	private GridPane gpEfficiency;

	private BorderPane bpRoot;
	private VBox vbCompanyMenu;
	private VBox vbChangeTitle;
	private HBox hbHeadLine;
	private HBox hbChangingHours;
	private HBox hbChangeName;

	private Label lblCompany;
	private Label lblWorkingType;
	private Label lblChosenDepartment;
	private Label lblStartHour;
	private Label lblTheEmp;
	private Label lblNewCompanyName;

	Button btnEditName = new Button("Edit Name");
	Button btnChangeTheName = new Button("Ok");

	private final double COL_SIZE_ID = 50;
	private final double COL_SIZE_NAME = 120;
	private final double COL_SIZE_EMP_NAME = 80;
	private final double COL_SIZE_TYPE = 100;
	private final double COL_SIZE_WORKING_HOURS = 100;
	private final double SPACE_BETWEEN_DETAILS_TO_BUTTONS = 20;
	private final double SPACE_BETWEEN_BUTTONS = 10;
	private final double BUTTONS_SIZE = 60;
	private final double COL_SIZE_EMPLOYEE = 150;
	private final double COL_SIZE_EMPLOYEEPREFERENCE = 110;
	private final double COL_SIZE_NUM_OF_ROLES = 120;
	private final double COL_SIZE_SALARY = 100;

	Button btnLoadFromFile = new Button("Load Data from file");
	Button btnSubMenu1 = new Button("Add Department");
	Button btnSubMenu2 = new Button("Add Role");
	Button btnSubMenu3 = new Button("Add employee");
	Button btnSubMenu4 = new Button("Show departments & roles");
	Button btnSubMenu5 = new Button("Show all employees");
	Button btnSubMenu6 = new Button("Change work hours");
	Button btnSubMenu7 = new Button("Efficiency");
	Button btnSubMenu8 = new Button("Exit");

	public static transient Background BACKGOUND_MENU = new Background(
			new BackgroundFill(Color.PLUM, CornerRadii.EMPTY, Insets.EMPTY));
	private static Color COLOR_MAIN_MENU_FILL = Color.DARKTURQUOISE;
	private static Color COLOR_MNG_BUTTON_REMOVE = Color.INDIANRED;
	private static Color COLOR_FOR_THE_TITLE = Color.THISTLE;
	private static final Color COLOR_MAIN_MENU_FILL_SELECTED = Color.TURQUOISE;
	public static final Color COLOR_MNG_BUTTON_REMOVE_SELECTED = Color.LIGHTCORAL;
	public static Background BACKGOUND_MNG_BUTTONS = new Background(
			new BackgroundFill(COLOR_MAIN_MENU_FILL, new CornerRadii(10), Insets.EMPTY));
	public static Background BACKGOUND_MNG_BUTTONS_REMOVE = new Background(
			new BackgroundFill(COLOR_MNG_BUTTON_REMOVE, new CornerRadii(10), Insets.EMPTY));
	public static Background BACKGOUND_FOR_TITLES = new Background(
			new BackgroundFill(COLOR_FOR_THE_TITLE, new CornerRadii(10), Insets.EMPTY));
	public static final Background BACKGROUND_MNG_BUTTONS_SELECTED = new Background(
			new BackgroundFill(COLOR_MAIN_MENU_FILL_SELECTED, new CornerRadii(10), Insets.EMPTY));
	public static final Background BACKGROUND_MNG_BUTTONS_REMOVE_SELECTED = new Background(
			new BackgroundFill(COLOR_MNG_BUTTON_REMOVE_SELECTED, new CornerRadii(10), Insets.EMPTY));
	public static final Background BACKGROUND_MNG_BUTTONS = new Background(
			new BackgroundFill(COLOR_MAIN_MENU_FILL, new CornerRadii(10), Insets.EMPTY));
	public static final Background BACKGROUND_MNG_BUTTONS_REMOVE = new Background(
			new BackgroundFill(COLOR_MNG_BUTTON_REMOVE, new CornerRadii(10), Insets.EMPTY));

	public CompanyView(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Company Manage");

		allListeners = new Vector<ViewListenable>();
		bpRoot = new BorderPane();
		vbCompanyMenu = new VBox();
		vbCompanyMenu.getChildren().addAll(btnLoadFromFile, btnSubMenu1, btnSubMenu2, btnSubMenu3, btnSubMenu4,
				btnSubMenu5, btnSubMenu6, btnSubMenu7, btnSubMenu8);

		vbCompanyMenu.setSpacing(10);
		vbCompanyMenu.setPadding(new Insets(20));
		vbCompanyMenu.setAlignment(Pos.CENTER_LEFT);
		vbCompanyMenu.setBackground(BACKGOUND_MENU);

		btnLoadFromFile.setMaxSize(350, 350);
		btnSubMenu1.setMaxSize(350, 350);
		btnSubMenu2.setMaxSize(350, 350);
		btnSubMenu3.setMaxSize(350, 350);
		btnSubMenu4.setMaxSize(350, 350);
		btnSubMenu5.setMaxSize(350, 350);
		btnSubMenu6.setMaxSize(350, 350);
		btnSubMenu7.setMaxSize(350, 350);
		btnSubMenu8.setMaxSize(350, 350);

		btnLoadFromFile.setOnMouseEntered(e -> btnLoadFromFile.setTextFill(Color.MEDIUMVIOLETRED));
		btnLoadFromFile.setOnMouseExited(e -> btnLoadFromFile.setTextFill(Color.BLACK));
		btnSubMenu1.setOnMouseEntered(e -> btnSubMenu1.setTextFill(Color.MEDIUMVIOLETRED));
		btnSubMenu1.setOnMouseExited(e -> btnSubMenu1.setTextFill(Color.BLACK));
		btnSubMenu2.setOnMouseEntered(e -> btnSubMenu2.setTextFill(Color.MEDIUMVIOLETRED));
		btnSubMenu2.setOnMouseExited(e -> btnSubMenu2.setTextFill(Color.BLACK));
		btnSubMenu3.setOnMouseEntered(e -> btnSubMenu3.setTextFill(Color.MEDIUMVIOLETRED));
		btnSubMenu3.setOnMouseExited(e -> btnSubMenu3.setTextFill(Color.BLACK));
		btnSubMenu4.setOnMouseEntered(e -> btnSubMenu4.setTextFill(Color.MEDIUMVIOLETRED));
		btnSubMenu4.setOnMouseExited(e -> btnSubMenu4.setTextFill(Color.BLACK));
		btnSubMenu5.setOnMouseEntered(e -> btnSubMenu5.setTextFill(Color.MEDIUMVIOLETRED));
		btnSubMenu5.setOnMouseExited(e -> btnSubMenu5.setTextFill(Color.BLACK));
		btnSubMenu6.setOnMouseEntered(e -> btnSubMenu6.setTextFill(Color.MEDIUMVIOLETRED));
		btnSubMenu6.setOnMouseExited(e -> btnSubMenu6.setTextFill(Color.BLACK));
		btnSubMenu7.setOnMouseEntered(e -> btnSubMenu7.setTextFill(Color.MEDIUMVIOLETRED));
		btnSubMenu7.setOnMouseExited(e -> btnSubMenu7.setTextFill(Color.BLACK));
		btnSubMenu8.setOnMouseEntered(e -> btnSubMenu8.setTextFill(Color.MEDIUMVIOLETRED));
		btnSubMenu8.setOnMouseExited(e -> btnSubMenu8.setTextFill(Color.BLACK));

		bpRoot.setLeft(vbCompanyMenu);

		// #################################### LOAD FROM FILE #########################################//
		btnLoadFromFile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				try {
					allListeners.get(0).readFromFile();
					showMessage("The Data load from the file successfully");
				} catch (IOException | ClassNotFoundException e1) {
					showMessage(e1.getMessage());
				}
			}
		});

		// #################################### ADD DEPARTMENT ###########################################//
		btnSubMenu1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				getDepartmentFromUser();
			}
		});

		// #################################### ADD ROLE ############################################//
		btnSubMenu2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				getRoleFromUser();
			}
		});

		// #################################### ADD EMPLOYEE ###########################################//
		btnSubMenu3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				getEmployeeFromUser();
			}
		});

		// #################################### Show all departments ##########################################//
		btnSubMenu4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				showAllDepartments();
			}
		});

		// #################################### Show all employees ##########################################//
		btnSubMenu5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				showAllEmployees();
			}
		});

		// #################################### change working hours #########################################//
		btnSubMenu6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				changeWorkingHours();
			}
		});

		// #################################### efficiency ######################################//

		btnSubMenu7.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				showEfficieny();
			}
		});

		// #################################### exit ############################################//

		btnSubMenu8.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				try {
					allListeners.get(0).saveToFile();
				} catch (IOException e1) {
					e1.getMessage();
				}
				showMessage("Goodbye, have a nice day :)");
				primaryStage.close();
			}
		});

		primaryStage.setScene(new Scene(bpRoot, 1150, 550));
		primaryStage.show();
	}

	public void registerListener(ViewListenable l) {
		allListeners.add(l);
	}

	// ##################################################################################################//
	public void getDepartmentFromUser() {
		gpChangeDepHours = new GridPane();
		gpChangeDepHours.setPadding(new Insets(10));
		gpChangeDepHours.setHgap(10);
		gpChangeDepHours.setVgap(10);

		gpChangeDepHours.add(new Label("Department Name: "), 0, 0);
		TextField tfDepName = new TextField();
		gpChangeDepHours.add(tfDepName, 1, 0);

		gpChangeDepHours.add(new Label("Department Type: "), 0, 1);
		ComboBox<eDepTypes> cmbDepTypes = new ComboBox<eDepTypes>();
		cmbDepTypes.getItems().addAll(eDepTypes.values());
		gpChangeDepHours.add(cmbDepTypes, 1, 1);

		Label lblDepWorkingType = new Label("Department Working Type: ");
		gpChangeDepHours.add(lblDepWorkingType, 0, 2);
		ComboBox<ePreferences> cmbDepWorkTypes = new ComboBox<ePreferences>();
		cmbDepWorkTypes.getItems().addAll(ePreferences.values());
		gpChangeDepHours.add(cmbDepWorkTypes, 1, 2);
		lblDepWorkingType.setVisible(false);
		cmbDepWorkTypes.setVisible(false);

		Label lblStartHourDep = new Label("Department Start Hour: ");
		gpChangeDepHours.add(lblStartHourDep, 0, 3);
		ComboBox<LocalTime> cmbHoursDep = new ComboBox<LocalTime>();

		cmbDepTypes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				lblDepWorkingType.setVisible(false);
				cmbDepWorkTypes.setVisible(false);
				lblStartHourDep.setVisible(false);
				cmbHoursDep.setVisible(false);
				if (cmbDepTypes.getValue() == eDepTypes.Syncronic) {
					lblDepWorkingType.setVisible(true);
					cmbDepWorkTypes.setVisible(true);
				} else {
					cmbDepWorkTypes.setValue(null);
					cmbHoursDep.setValue(null);
				}
			}
		});

		lblStartHourDep.setVisible(false);
		cmbHoursDep.setVisible(false);

		cmbDepWorkTypes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				lblStartHourDep.setVisible(false);
				cmbHoursDep.setVisible(false);
				cmbHoursDep.getItems().clear();

				if (cmbDepWorkTypes.getValue() != null) {
					switch (cmbDepWorkTypes.getValue()) {
					case Earlier:
						for (int i = Company.REGULAR_START_HOUR.getHour() - 3; i < Company.REGULAR_START_HOUR
								.getHour(); i++) {
							cmbHoursDep.getItems().add(LocalTime.of(i, 0));
						}
						lblStartHourDep.setVisible(true);
						cmbHoursDep.setVisible(true);
						break;
					case Later:
						for (int i = Company.REGULAR_START_HOUR.getHour() + 1; i < Company.REGULAR_START_HOUR.getHour()
								+ 5; i++) {
							cmbHoursDep.getItems().add(LocalTime.of(i, 0));
						}
						lblStartHourDep.setVisible(true);
						cmbHoursDep.setVisible(true);
					default:
						break;
					}
				}
			}
		});
		gpChangeDepHours.add(cmbHoursDep, 1, 3);

		Button btnAddNewDep = new Button("Add Department");
		gpChangeDepHours.add(btnAddNewDep, 0, 4);
		bpRoot.setCenter(gpChangeDepHours);

		btnAddNewDep.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				if (tfDepName.getText().isEmpty() || cmbDepTypes.getValue() == null
						|| (cmbDepTypes.getValue() == (eDepTypes.Syncronic) && cmbDepWorkTypes.getValue() == null
						|| ((cmbDepWorkTypes.getValue() == (ePreferences.Later)
						|| cmbDepWorkTypes.getValue() == (ePreferences.Earlier))
								&& cmbHoursDep.getValue() == null))) {
					showMessageError("All fields must be selected");
				} else {
					for (ViewListenable l : allListeners) {
						Preference thePref = null;
						ePreferences thePreference = cmbDepWorkTypes.getValue();
						if (thePreference == (ePreferences.Home))
							try {
								thePref = new Preference(thePreference);
							} catch (PreferenceTimeException e) {
								showMessageError(e.getMessage());
							}
						else
							thePref = new Preference(thePreference, cmbHoursDep.getValue());
						try {
							l.viewUpdateAddDepartment(tfDepName.getText(), cmbDepTypes.getValue(), thePref);
						} catch (AlreadyExistException e) {
							showMessageError(e.getMessage());
						}
					}
					showMessage("The department add successfully");
					tfDepName.clear();
					cmbDepTypes.setValue(null);
					cmbDepWorkTypes.setValue(null);
					cmbHoursDep.setValue(null);
				}
			}
		});
	}

	// ############################################# add role ###################################################

	public void getRoleFromUser() {
		gpAddRole = new GridPane();
		gpAddRole.setPadding(new Insets(10));
		gpAddRole.setHgap(10);
		gpAddRole.setVgap(10);

		gpAddRole.add(new Label("Role Name: "), 0, 0);
		TextField tfRoleName = new TextField();
		gpAddRole.add(tfRoleName, 1, 0);

		gpAddRole.add(new Label("Role Type: "), 0, 1);
		ComboBox<eRolesTypes> cmbRoleTypes = new ComboBox<eRolesTypes>();
		cmbRoleTypes.getItems().addAll(eRolesTypes.values());
		ComboBox<ePreferences> cmbRoleWorkTypes = new ComboBox<ePreferences>();
		ComboBox<Department> cmbRolesDepartment = new ComboBox<Department>();
		ComboBox<LocalTime> cmbHours = new ComboBox<LocalTime>();
		Set<Department> allPermanentDep = allListeners.get(0).viewUpdateGetPermanentDepartmemts();
		Set<Department> allDynamicDep = allListeners.get(0).viewUpdateGetDynamicDepartmemts();
		lblChosenDepartment = new Label("Role's Department: ");
		lblWorkingType = new Label("Role Working Type: ");
		lblStartHour = new Label("Role Start Hour: ");

		lblChosenDepartment.setVisible(false);
		cmbRolesDepartment.setVisible(false);
		lblWorkingType.setVisible(false);
		cmbRoleWorkTypes.setVisible(false);
		lblStartHour.setVisible(false);
		cmbHours.setVisible(false);

		cmbRoleTypes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				cmbRolesDepartment.getItems().clear();
				if (cmbRoleTypes.getValue() != null) {
					switch (cmbRoleTypes.getValue()) {
					case Permanent:
						cmbRolesDepartment.getItems().addAll(allPermanentDep);
						lblChosenDepartment.setVisible(true);
						cmbRolesDepartment.setVisible(true);
						lblWorkingType.setVisible(true);
						cmbRoleWorkTypes.setVisible(true);
						break;
					case Dynamic:
						cmbRolesDepartment.getItems().addAll(allDynamicDep);
						lblChosenDepartment.setVisible(true);
						cmbRolesDepartment.setVisible(true);
					default:
						break;
					}
				}
			}
		});

		cmbRolesDepartment.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {

				if (cmbRoleTypes.getValue() == eRolesTypes.Dynamic) {
					if (cmbRolesDepartment.getValue().getTheType() == eDepTypes.Syncronic) {
						lblWorkingType.setVisible(false);
						cmbRoleWorkTypes.setVisible(false);
						lblStartHour.setVisible(false);
						cmbHours.setVisible(false);
					} else {
						lblWorkingType.setVisible(true);
						cmbRoleWorkTypes.setVisible(true);
					}
				}
			}
		});
		gpAddRole.add(cmbRoleTypes, 1, 1);
		gpAddRole.add(lblChosenDepartment, 0, 2);
		gpAddRole.add(cmbRolesDepartment, 1, 2);

		gpAddRole.add(lblWorkingType, 0, 3);
		cmbRoleWorkTypes.getItems().addAll(ePreferences.values());
		gpAddRole.add(cmbRoleWorkTypes, 1, 3);

		gpAddRole.add(lblStartHour, 0, 4);
		lblStartHour.setVisible(false);
		cmbHours.setVisible(false);
		cmbRoleWorkTypes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				lblStartHour.setVisible(false);
				cmbHours.setVisible(false);
				cmbHours.getItems().clear();
				if (cmbRoleWorkTypes != null) {
					switch (cmbRoleWorkTypes.getValue()) {
					case Earlier:
						for (int i = Company.REGULAR_START_HOUR.getHour() - 3; i < Company.REGULAR_START_HOUR
								.getHour(); i++) {
							cmbHours.getItems().add(LocalTime.of(i, 0));
						}
						lblStartHour.setVisible(true);
						cmbHours.setVisible(true);
						break;
					case Later:
						for (int i = Company.REGULAR_START_HOUR.getHour() + 1; i < Company.REGULAR_START_HOUR.getHour()
								+ 5; i++) {
							cmbHours.getItems().add(LocalTime.of(i, 0));
						}
						lblStartHour.setVisible(true);
						cmbHours.setVisible(true);
					default:
						break;
					}
				}
			}
		});

		gpAddRole.add(cmbHours, 1, 4);

		Button btnÃAddNewRole = new Button("Add Role");
		gpAddRole.add(btnÃAddNewRole, 0, 5);

		bpRoot.setCenter(gpAddRole);

		btnÃAddNewRole.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				if (tfRoleName.getText().isEmpty() || cmbRoleTypes.getValue() == null
						|| cmbRolesDepartment.getValue() == null
						|| (cmbRoleWorkTypes.getValue() == null
						&& cmbRolesDepartment.getValue().getTheType() != eDepTypes.Syncronic)
						|| ((cmbRoleWorkTypes.getValue() == ePreferences.Earlier && cmbHours.getValue() == null)
								|| (cmbRoleWorkTypes.getValue() == ePreferences.Later
								&& cmbHours.getValue() == null))) {
					showMessageError("All fields must be selected");
				} else {
					for (ViewListenable l : allListeners) {
						Preference workHours = null;
						ePreferences thePreference = cmbRoleWorkTypes.getValue();
						if (thePreference == ePreferences.Home)
							try {
								workHours = new Preference(thePreference);
							} catch (PreferenceTimeException e) {
								showMessageError(e.getMessage());
							}
						else
							workHours = new Preference(thePreference, cmbHours.getValue());
						try {
							if (cmbRolesDepartment.getValue().getTheType() == eDepTypes.Syncronic) {
								l.viewUpdateAddRole(cmbRolesDepartment.getValue(), tfRoleName.getText(),
										cmbRolesDepartment.getValue().getWorkHours(), cmbRoleTypes.getValue());
							} else {
								l.viewUpdateAddRole(cmbRolesDepartment.getValue(), tfRoleName.getText(), workHours,
										cmbRoleTypes.getValue());
							}
						} catch (AlreadyExistException e) {
							showMessageError(e.getMessage());
						}

						showMessage("The role add successfully");
						tfRoleName.clear();
						cmbRoleTypes.setValue(null);
						cmbRolesDepartment.setValue(null);
						cmbHours.setValue(null);
						lblChosenDepartment.setVisible(false);
						cmbRolesDepartment.setVisible(false);
						lblWorkingType.setVisible(false);
						cmbRoleWorkTypes.setVisible(false);
						lblStartHour.setVisible(false);
						cmbHours.setVisible(false);
					}
				}
			}
		});
	}

	// ###################################### add Employee ####################################3

	public void getEmployeeFromUser() {
		gpAddEmployee = new GridPane();
		gpAddEmployee.setPadding(new Insets(10));
		gpAddEmployee.setHgap(10);
		gpAddEmployee.setVgap(10);

		Label lblBaseSalary = new Label("Base salary: ");
		Label lblSalaryPerHour = new Label("Salary per hour: ");
		ComboBox<Integer> cmbBaseSalary = new ComboBox<Integer>();
		ComboBox<Integer> cmbSalaryPerHour = new ComboBox<Integer>();

		lblBaseSalary.setVisible(false);
		cmbBaseSalary.setVisible(false);
		lblSalaryPerHour.setVisible(false);
		cmbSalaryPerHour.setVisible(false);

		gpAddEmployee.add(new Label("Employee Name: "), 0, 0);
		TextField tfEmployeeName = new TextField();
		gpAddEmployee.add(tfEmployeeName, 1, 0);

		gpAddEmployee.add(new Label("Type of Salary "), 0, 1);
		ComboBox<eSalaryType> cmbTypeOfSalary = new ComboBox<eSalaryType>();
		cmbTypeOfSalary.getItems().addAll(eSalaryType.values());

		cmbTypeOfSalary.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				lblBaseSalary.setVisible(false);
				cmbBaseSalary.setVisible(false);
				lblSalaryPerHour.setVisible(false);
				cmbSalaryPerHour.setVisible(false);
				if (cmbTypeOfSalary.getValue() != null) {
					switch (cmbTypeOfSalary.getValue()) {
					case Base:
						lblBaseSalary.setVisible(true);
						cmbBaseSalary.setVisible(true);
						break;

					case BaseNBonus:
						lblBaseSalary.setVisible(true);
						cmbBaseSalary.setVisible(true);
						break;

					case HoursSalary:
						lblSalaryPerHour.setVisible(true);
						cmbSalaryPerHour.setVisible(true);
						break;

					default:
						break;
					}
				}
			}
		});
		gpAddEmployee.add(cmbTypeOfSalary, 1, 1);

		gpAddEmployee.add(lblBaseSalary, 0, 2);
		for (int i = 5000; i < 50000; i += 1000) {
			cmbBaseSalary.getItems().add(i);
		}
		cmbBaseSalary.setValue(0);
		gpAddEmployee.add(cmbBaseSalary, 1, 2);

		gpAddEmployee.add(lblSalaryPerHour, 0, 2);
		for (int i = 30; i < 71; i += 5) {
			cmbSalaryPerHour.getItems().add(i);
		}
		cmbSalaryPerHour.setValue(0);
		gpAddEmployee.add(cmbSalaryPerHour, 1, 2);

		Label lblStartType = new Label("Prefer work hours type : ");
		gpAddEmployee.add(lblStartType, 0, 3);
		ComboBox<ePreferences> cmbPreferences = new ComboBox<ePreferences>();
		cmbPreferences.getItems().addAll(ePreferences.values());
		gpAddEmployee.add(cmbPreferences, 1, 3);

		Label lblStartHourPref = new Label("Preference start hour: ");
		gpAddEmployee.add(lblStartHourPref, 0, 4);
		ComboBox<LocalTime> cmbHourStart = new ComboBox<LocalTime>();
		lblStartHourPref.setVisible(false);
		cmbHourStart.setVisible(false);
		cmbPreferences.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				lblStartHourPref.setVisible(false);
				cmbHourStart.setVisible(false);
				cmbHourStart.getItems().clear();
				if (cmbPreferences.getValue() != null) {
					switch (cmbPreferences.getValue()) {
					case Earlier:
						for (int i = Company.REGULAR_START_HOUR.getHour() - 3; i < Company.REGULAR_START_HOUR
								.getHour(); i++) {
							cmbHourStart.getItems().add(LocalTime.of(i, 0));
						}
						lblStartHourPref.setVisible(true);
						cmbHourStart.setVisible(true);
						break;
					case Later:
						for (int i = Company.REGULAR_START_HOUR.getHour() + 1; i < Company.REGULAR_START_HOUR.getHour()
								+ 5; i++) {
							cmbHourStart.getItems().add(LocalTime.of(i, 0));
						}
						lblStartHourPref.setVisible(true);
						cmbHourStart.setVisible(true);
					default:
						break;
					}
				}
			}
		});
		gpAddEmployee.add(cmbHourStart, 1, 4);

		Button btnAddNewEmployee = new Button("Add Employee");
		gpAddEmployee.add(btnAddNewEmployee, 0, 5);

		bpRoot.setCenter(gpAddEmployee);

		btnAddNewEmployee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				if (tfEmployeeName.getText().isEmpty() || cmbPreferences.getValue() == null
						|| cmbTypeOfSalary.getValue() == null
						|| (cmbPreferences.getValue() == ePreferences.Later && cmbHourStart.getValue() == null)
						|| (cmbPreferences.getValue() == ePreferences.Earlier && cmbHourStart.getValue() == null)
						|| (cmbTypeOfSalary.getValue() == eSalaryType.HoursSalary && cmbSalaryPerHour.getValue() == 0)
						|| (!(cmbTypeOfSalary.getValue() == eSalaryType.HoursSalary)
								&& cmbBaseSalary.getValue() == 0)) {
					showMessageError("All fields must be selected");
				} else {
					for (ViewListenable l : allListeners) {
						Preference thePref = null;
						ePreferences thePreference = cmbPreferences.getValue();
						if (thePreference == ePreferences.Home)
							try {
								thePref = new Preference(thePreference);
							} catch (PreferenceTimeException e) {
								showMessageError(e.getMessage());
							}
						else
							thePref = new Preference(thePreference, cmbHourStart.getValue());
						try {
							l.viewUpdateAddEmployee(cmbTypeOfSalary.getValue(), tfEmployeeName.getText(), thePref,
									cmbSalaryPerHour.getValue(), cmbBaseSalary.getValue());
						} catch (AlreadyExistException e) {
							showMessageError(e.getMessage());
						}
					}
					showMessage("The employee add successfully");
					tfEmployeeName.clear();
					cmbBaseSalary.setValue(0);
					cmbSalaryPerHour.setValue(0);
					cmbTypeOfSalary.setValue(null);
					cmbPreferences.setValue(null);
					cmbHourStart.setValue(null);
				}
			}
		});
	}

	// ######################################### show all departments ###############################################
	public void showAllDepartments() {
		HBox hbDepHeadline = new HBox();
		Label lblTitleNumID = new Label("  ID");
		Label lblTitleDepName = new Label("Name");
		Label lblTitleDepType = new Label("Type");
		Label lblTitleDepWorkingHours = new Label("Working Hours");
		Label lblTitleDepRolesCnt = new Label("No. of Roles");
		lblTitleNumID.setPrefWidth(COL_SIZE_ID);
		lblTitleDepName.setPrefWidth(COL_SIZE_NAME);
		lblTitleDepType.setPrefWidth(COL_SIZE_TYPE);
		lblTitleDepWorkingHours.setPrefWidth(COL_SIZE_WORKING_HOURS);
		lblTitleDepRolesCnt.setPrefWidth(COL_SIZE_NUM_OF_ROLES);
		hbDepHeadline.getChildren().addAll(lblTitleNumID, lblTitleDepName, lblTitleDepType, lblTitleDepWorkingHours, lblTitleDepRolesCnt);

		ListView<HBox> lstDepartments = new ListView<HBox>();
		VBox vbDepatments = new VBox(lstDepartments);
		VBox vbManageCompany = new VBox();

		Set<Department> allDepartments = allListeners.get(0).viewUpdateGetAllDepartments();
		for (Department d : allDepartments) {
			GridPane gpDepDetails = new GridPane();
			GridPane gpDepButtons = new GridPane();
			gpDepButtons.setHgap(SPACE_BETWEEN_BUTTONS);

			Label lblNumID = new Label(d.getIdDep() + "");
			Label lblDepName = new Label(d.getName());
			Label lblDepType = new Label(d.getTheType().toString());
			Label lblDepNumOfWorkingHours = new Label();
			Label lblDepRolesCnt = new Label(d.allRoles.size() + "");

			if (d.getTheType() == eDepTypes.Syncronic) {
				lblDepNumOfWorkingHours.setText(d.getWorkHours() + "");
			} else
				lblDepNumOfWorkingHours.setText("No hours");

			lblNumID.setPrefWidth(COL_SIZE_ID);
			lblDepName.setPrefWidth(COL_SIZE_NAME);
			lblDepType.setPrefWidth(COL_SIZE_TYPE);
			lblDepNumOfWorkingHours.setPrefWidth(COL_SIZE_WORKING_HOURS);
			lblDepRolesCnt.setPrefWidth(COL_SIZE_NUM_OF_ROLES);
			gpDepDetails.add(lblNumID, 0, 0);
			gpDepDetails.add(lblDepName, 1, 0);
			gpDepDetails.add(lblDepType, 2, 0);
			gpDepDetails.add(lblDepNumOfWorkingHours, 3, 0);
			gpDepDetails.add(lblDepRolesCnt, 4, 0);


			Button btnDepShowRolls = new Button("Show Rolls");
			Button btnDepRemove = new Button("Remove");
			btnDepShowRolls.setMinWidth(BUTTONS_SIZE);
			btnDepShowRolls.setBackground(BACKGOUND_MNG_BUTTONS);
			btnDepShowRolls.setTextFill(Color.BLACK);
			btnDepShowRolls.setOnMouseEntered(new EventMouseEnteredMngButton());
			btnDepShowRolls.setOnMouseExited(new EventMouseExitedMngButton());
			btnDepRemove.setMinWidth(BUTTONS_SIZE);
			btnDepRemove.setBackground(BACKGOUND_MNG_BUTTONS_REMOVE);
			btnDepRemove.setTextFill(Color.WHITE);
			btnDepRemove.setOnMouseEntered(new EventMouseEnteredMngButtonRemove());
			btnDepRemove.setOnMouseExited(new EventMouseExitedMngButtonRemove());

			gpDepButtons.add(btnDepShowRolls, 0, 0);
			gpDepButtons.add(btnDepRemove, 1, 0);

			HBox hbDepartments = new HBox(SPACE_BETWEEN_DETAILS_TO_BUTTONS);
			hbDepartments.getChildren().addAll(gpDepDetails, gpDepButtons);
			lstDepartments.getItems().add(hbDepartments);

			btnDepShowRolls.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent ae) {
					showAllRolls(d);
				}
			});

			btnDepRemove.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent ae) {
					for (ViewListenable l : allListeners) {
						try {
							l.viewRemoveDepartment(d);
							showAllDepartments();
						} catch (EntityIsNotExist e) {
							showMessageError("The Department is not Exist");
						}
					}
				}
			});
		}

		vbManageCompany.getChildren().addAll(hbDepHeadline, new Separator(Orientation.HORIZONTAL), vbDepatments);
		vbManageCompany.setPadding(new Insets(10));
		bpRoot.setCenter(vbManageCompany);
	}

	// #################################### show all roles ##############################################

	public void showAllRolls(Department d) {
		HBox hbHeadlineDepName = new HBox();
		HBox hbRollesHeadline = new HBox();
		Button returnToShowAllDep = new Button("Return");
		returnToShowAllDep.setMaxSize(200, 200);
		Label lblTitleWhichDep = new Label(d.getName());
		hbHeadlineDepName.setPadding(new Insets(10));
		hbHeadlineDepName.setSpacing(30);
		lblTitleWhichDep.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
		lblTitleWhichDep.setTextFill(Color.BLACK);
		hbHeadlineDepName.getChildren().addAll(lblTitleWhichDep, returnToShowAllDep);
		hbHeadlineDepName.setAlignment(Pos.CENTER);
		GridPane gpRollData = new GridPane();

		Label lblTitleRoleNumID = new Label("  ID");
		Label lblTitleRoleName = new Label(" Name");
		Label lblTitleRoleType = new Label(" Type");
		Label lblTitleTheEmp = new Label("Employee");
		Label lblTitleRoleWorkingHoursType = new Label("Working Hours");

		lblTitleRoleNumID.setPrefWidth(COL_SIZE_ID);
		lblTitleRoleName.setPrefWidth(COL_SIZE_NAME);
		lblTitleRoleType.setPrefWidth(COL_SIZE_TYPE);
		lblTitleRoleWorkingHoursType.setPrefWidth(COL_SIZE_WORKING_HOURS);
		lblTitleTheEmp.setPrefWidth(COL_SIZE_EMPLOYEE);

		hbRollesHeadline.getChildren().addAll(lblTitleRoleNumID, lblTitleRoleName, lblTitleRoleType, lblTitleTheEmp,
				lblTitleRoleWorkingHoursType);

		ListView<HBox> lstRolls = new ListView<HBox>();
		VBox vbRoll = new VBox(lstRolls);
		VBox vbRollManageCompany = new VBox();

		Set<Role> allRolls = allListeners.get(0).viewUpdatGetAllRolles(d); // to do get rolls
		for (Role r : allRolls) {
			GridPane gpRollDetails = new GridPane();
			GridPane gpRollButtons = new GridPane();
			gpRollButtons.setHgap(SPACE_BETWEEN_BUTTONS);

			Label lblRollNumID = new Label(r.getIdRole() + "");
			Label lblRollName = new Label(r.getName());
			Label lblRollType = new Label(r.getTheType().toString());
			lblTheEmp = new Label();

			if (r.getTheEmployee() == null) {
				lblTheEmp.setText("No employee");
			} else
				lblTheEmp.setText(r.getTheEmployee().getName());

			Label lblRoleNumOfWorkingHours = new Label();

			if (d.getTheType() == eDepTypes.Syncronic) {
				lblRoleNumOfWorkingHours.setText(d.getWorkHours() + "");
			} else if (ePreferences.Home == r.getWorkHours().getThePreference()) {
				lblRoleNumOfWorkingHours.setText("Home");
			} else
				lblRoleNumOfWorkingHours.setText(r.getWorkHours() + "");

			lblRollNumID.setPrefWidth(COL_SIZE_ID);
			lblRollName.setPrefWidth(COL_SIZE_NAME);
			lblRollType.setPrefWidth(COL_SIZE_TYPE);
			lblTheEmp.setPrefWidth(COL_SIZE_EMPLOYEE);
			lblRoleNumOfWorkingHours.setPrefWidth(COL_SIZE_WORKING_HOURS);

			gpRollDetails.add(lblRollNumID, 0, 0);
			gpRollDetails.add(lblRollName, 1, 0);
			gpRollDetails.add(lblRollType, 2, 0);
			gpRollDetails.add(lblTheEmp, 3, 0);
			gpRollDetails.add(lblRoleNumOfWorkingHours, 4, 0);

			Button btnRollEditTheEmployee = new Button("Edit Employee");
			btnRollEditTheEmployee.setMinWidth(BUTTONS_SIZE);
			btnRollEditTheEmployee.setBackground(BACKGOUND_MNG_BUTTONS);
			btnRollEditTheEmployee.setTextFill(Color.BLACK);
			btnRollEditTheEmployee.setOnMouseEntered(new EventMouseEnteredMngButton());
			btnRollEditTheEmployee.setOnMouseExited(new EventMouseExitedMngButton());
			Button btnRollRemove = new Button("Remove");
			btnRollRemove.setMinWidth(BUTTONS_SIZE);
			btnRollRemove.setBackground(BACKGOUND_MNG_BUTTONS_REMOVE);
			btnRollRemove.setTextFill(Color.WHITE);
			btnRollRemove.setOnMouseEntered(new EventMouseEnteredMngButtonRemove());
			btnRollRemove.setOnMouseExited(new EventMouseExitedMngButtonRemove());

			gpRollButtons.add(btnRollEditTheEmployee, 0, 0);
			gpRollButtons.add(btnRollRemove, 1, 0);

			HBox hbRoll = new HBox(SPACE_BETWEEN_DETAILS_TO_BUTTONS);
			hbRoll.getChildren().addAll(gpRollDetails, gpRollButtons);
			lstRolls.getItems().add(hbRoll);

			btnRollEditTheEmployee.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent ae) {
					setEmployeeToRoll(r, d);
				}
			});

			btnRollRemove.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent ae) {
					for (ViewListenable l : allListeners) {
						l.viewRemoveRoll(d, r);
						showAllRolls(d);
					}
				}
			});
		}

		returnToShowAllDep.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				showAllDepartments();
			}
		});

		vbRollManageCompany.getChildren().addAll(hbHeadlineDepName, hbRollesHeadline, gpRollData,
				new Separator(Orientation.HORIZONTAL), vbRoll);
		vbRollManageCompany.setPadding(new Insets(10));
		bpRoot.setCenter(vbRollManageCompany);
	}

	// ######################################### show all employees ###############################
	public void showAllEmployees() {

		HBox hbHeadline = new HBox();
		HBox hbEmployeesHeadline = new HBox();
		hbHeadline.setPadding(new Insets(10));
		hbHeadline.setAlignment(Pos.CENTER);
		GridPane gpEmployeeData = new GridPane();

		Label lblTitleEmployeeNumID = new Label("  ID");
		Label lblTitleEmployeeName = new Label("Name");
		Label lblTitleEmployeePreference = new Label("Preference");
		Label lblTitleEmployeeTheDep = new Label("Department");
		Label lblTitleEmployeeTheRole = new Label("Role");
		Label lblTitleEmployeeTheHour = new Label("Working hours");
		Label lblTitleEmpSalary = new Label("Salary");

		lblTitleEmployeeNumID.setPrefWidth(COL_SIZE_ID);
		lblTitleEmployeeName.setPrefWidth(COL_SIZE_EMP_NAME);
		lblTitleEmployeePreference.setPrefWidth(COL_SIZE_EMPLOYEEPREFERENCE);
		lblTitleEmployeeTheDep.setPrefWidth(COL_SIZE_NAME);
		lblTitleEmployeeTheRole.setPrefWidth(COL_SIZE_EMPLOYEE);
		lblTitleEmployeeTheHour.setPrefWidth(COL_SIZE_EMPLOYEEPREFERENCE);
		lblTitleEmpSalary.setPrefWidth(COL_SIZE_SALARY);

		hbEmployeesHeadline.getChildren().addAll(lblTitleEmployeeNumID, lblTitleEmployeeName,
				lblTitleEmployeePreference , lblTitleEmployeeTheDep, lblTitleEmployeeTheRole, 
				lblTitleEmployeeTheHour, lblTitleEmpSalary);

		ListView<HBox> lstEmployee = new ListView<HBox>();
		VBox vbEmployee = new VBox(lstEmployee);
		VBox vbEmployeeManageCompany = new VBox();

		Set<Employee> allEmployees = allListeners.get(0).viewUpdateGetAllEmployees();
		for (Employee E : allEmployees) {
			GridPane gpEmployeeDetails = new GridPane();
			GridPane gpEmployeeButtons = new GridPane();
			gpEmployeeButtons.setHgap(SPACE_BETWEEN_BUTTONS);

			Label lblEmployeeNumID = new Label(E.getIdEmployee() + "");
			Label lblEmployeeName = new Label(E.getName());
			Label lblEmployeePreference = new Label(E.getEmployeePreference().toString());
			Label lblEmployeeTheDep = new Label();
			Label lblEmployeeTheRole = new Label(); // (E.getTheRole().toString());
			Label lblEmployeeTheHour = new Label();
			Label lblEmpSalary = new Label();
			if (E.getTheRole() == null) {
				lblEmployeeTheDep.setText("No Department");
				lblEmployeeTheRole.setText("No Role");
				lblEmployeeTheHour.setText("No hours");

			} else {
				lblEmployeeTheDep.setText(E.getTheRole().getTheDepartment().getName()+ "");
				lblEmployeeTheRole.setText(E.getTheRole().toString());
				lblEmployeeTheHour.setText(E.getTheRole().getWorkHours().toString());
			}
			if (E.getClass().getSimpleName().equals("HourEmployee")) {
				lblEmpSalary.setText(((HourEmployee)E).getSalaryPerHour() + " Hourly");
			} else if (E.getClass().getSimpleName().equals("BaseNBonusEmployee") || E.getClass().getSimpleName().equals("BaseEmployee")) {
				lblEmpSalary.setText(((BaseEmployee)E).getBaseSalary() + " Monthly");
			}

			lblEmployeeNumID.setPrefWidth(COL_SIZE_ID);
			lblEmployeeName.setPrefWidth(COL_SIZE_EMP_NAME);
			lblEmployeePreference.setPrefWidth(COL_SIZE_TYPE);
			lblEmployeeTheDep.setPrefWidth(COL_SIZE_NAME);
			lblEmployeeTheRole.setPrefWidth(COL_SIZE_EMPLOYEE);
			lblEmployeeTheHour.setPrefWidth(COL_SIZE_WORKING_HOURS);
			lblEmpSalary.setPrefWidth(COL_SIZE_SALARY);

			gpEmployeeDetails.add(lblEmployeeNumID, 0, 0);
			gpEmployeeDetails.add(lblEmployeeName, 1, 0);
			gpEmployeeDetails.add(lblEmployeePreference, 2, 0);
			gpEmployeeDetails.add(lblEmployeeTheDep, 3, 0);

			gpEmployeeDetails.add(lblEmployeeTheRole, 4, 0);
			gpEmployeeDetails.add(lblEmployeeTheHour, 5, 0);
			gpEmployeeDetails.add(lblEmpSalary, 6, 0);

			Button btnEmployeeEditTheRole = new Button("Edit Role");
			Button btnEmployeeRemove = new Button("Remove");
			btnEmployeeEditTheRole.setMinWidth(BUTTONS_SIZE);
			btnEmployeeEditTheRole.setBackground(BACKGOUND_MNG_BUTTONS);
			btnEmployeeEditTheRole.setTextFill(Color.BLACK);
			if (E.getTheRole() != null)
				btnEmployeeEditTheRole.setVisible(false);
			btnEmployeeEditTheRole.setOnMouseEntered(new EventMouseEnteredMngButton());
			btnEmployeeEditTheRole.setOnMouseExited(new EventMouseExitedMngButton());
			btnEmployeeRemove.setMinWidth(BUTTONS_SIZE);
			btnEmployeeRemove.setBackground(BACKGOUND_MNG_BUTTONS_REMOVE);
			btnEmployeeRemove.setTextFill(Color.WHITE);
			btnEmployeeRemove.setOnMouseEntered(new EventMouseEnteredMngButtonRemove());
			btnEmployeeRemove.setOnMouseExited(new EventMouseExitedMngButtonRemove());

			gpEmployeeButtons.add(btnEmployeeEditTheRole, 0, 6);
			gpEmployeeButtons.add(btnEmployeeRemove, 1, 6);

			HBox hbEmployee = new HBox(SPACE_BETWEEN_DETAILS_TO_BUTTONS);
			hbEmployee.getChildren().addAll(gpEmployeeDetails, gpEmployeeButtons);
			lstEmployee.getItems().add(hbEmployee);
			hbEmployee.setSpacing(30);

			btnEmployeeEditTheRole.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent ae) {
					setRollToEmployee(E);
				}
			});

			btnEmployeeRemove.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent ae) {
					for (ViewListenable l : allListeners) {
						try {
							l.viewRemoveEmployee(E);
							showAllEmployees();
						} catch (EntityIsNotExist e) {
							showMessageError("The Roll is not Exist");
						}
					}
				}
			});
		}

		vbEmployeeManageCompany.getChildren().addAll(hbHeadline, hbEmployeesHeadline, gpEmployeeData,
				new Separator(Orientation.HORIZONTAL), vbEmployee);
		vbEmployeeManageCompany.setPadding(new Insets(10));
		bpRoot.setCenter(vbEmployeeManageCompany);
	}

	private void setRollToEmployee(Employee e) {
		gpLinkEmployeeToRoll = new GridPane();
		gpLinkEmployeeToRoll.setPadding(new Insets(10));
		gpLinkEmployeeToRoll.setHgap(10);
		gpLinkEmployeeToRoll.setVgap(10);

		Label lblChooseDepartment = new Label("choose department to the role:");
		ComboBox<Department> cmbAllDepartments = new ComboBox<Department>();
		for (ViewListenable l : allListeners)
			cmbAllDepartments.getItems().addAll(l.viewUpdateGetAllDepartments());
		Label lblChooseRoleInDep = new Label("choose role in department:");
		lblChooseRoleInDep.setVisible(false);
		ComboBox<Role> cmbAllRolesInDepartment = new ComboBox<Role>();
		cmbAllRolesInDepartment.setVisible(false);

		gpLinkEmployeeToRoll.add(lblChooseDepartment, 0, 0);
		gpLinkEmployeeToRoll.add(cmbAllDepartments, 1, 0);
		gpLinkEmployeeToRoll.add(lblChooseRoleInDep, 0, 1);
		gpLinkEmployeeToRoll.add(cmbAllRolesInDepartment, 1, 1);

		Button btnLinkRoleAndEmployee = new Button("Confirm and return");
		gpLinkEmployeeToRoll.add(btnLinkRoleAndEmployee, 0, 2);

		bpRoot.setCenter(gpLinkEmployeeToRoll);

		cmbAllDepartments.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {

				cmbAllRolesInDepartment.getItems().clear();
				for (ViewListenable l : allListeners) {
					cmbAllRolesInDepartment.getItems()
					.addAll(l.viewUpdatGetAllEmptyRoles(cmbAllDepartments.getValue()));
					if (cmbAllRolesInDepartment.getItems().size() > 0) {
						cmbAllRolesInDepartment.setVisible(true);
						lblChooseRoleInDep.setVisible(true);
					} else {
						cmbAllRolesInDepartment.setVisible(false);
						lblChooseRoleInDep.setVisible(false);
						showMessageError("Please choose another department, there is no available role in that department");
					}
				}

			}
		});

		btnLinkRoleAndEmployee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				if (cmbAllDepartments.getValue() == null) {
					showMessageError("Please choose a department");
				} else if (cmbAllRolesInDepartment.getValue() == null) {
					showMessageError("Please choose a role");
				} else {
					for (ViewListenable l : allListeners) {
						l.updateModelAboutLinkEmployeeAndRoll(e, cmbAllRolesInDepartment.getValue());
					}
					showMessage("The linked add successfully");
					showAllEmployees();
				}
			}
		});
	}

	public void setEmployeeToRoll(Role theRole, Department theDep) {
		if (theRole.getTheEmployee() == null) { // if there is no one who doing this role
			addEmployeeToRole(theRole, theDep);
		} else {
			changeEmployeeInRoll(theRole, theDep);
		}
	}

	public void addEmployeeToRole(Role theRole, Department theDep) {
		gpLinkEmployeeToRoll = new GridPane();
		gpLinkEmployeeToRoll.setPadding(new Insets(10));
		gpLinkEmployeeToRoll.setHgap(10);
		gpLinkEmployeeToRoll.setVgap(10);

		Label lblChooseEmployee = new Label("choose employee for the roll: ");
		ComboBox<Employee> cmbAllAvailableEmployee = new ComboBox<Employee>();
		for (ViewListenable l : allListeners) {
			cmbAllAvailableEmployee.getItems().addAll(l.viewUpdateGetAllAvailableEmployees());
		}
		gpLinkEmployeeToRoll.add(lblChooseEmployee, 0, 1);
		gpLinkEmployeeToRoll.add(cmbAllAvailableEmployee, 1, 1);

		Button btnLinkEmployeeAndRoll = new Button("Confirm and return");
		gpLinkEmployeeToRoll.add(btnLinkEmployeeAndRoll, 0, 3);

		bpRoot.setCenter(gpLinkEmployeeToRoll);

		btnLinkEmployeeAndRoll.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				if (cmbAllAvailableEmployee.getValue() == null) {
					showMessageError("Please choose an employee");
				} else {
					for (ViewListenable l : allListeners) {
						l.updateModelAboutLinkEmployeeAndRoll(cmbAllAvailableEmployee.getValue(), theRole);
					}
					showMessage("The linked add successfully");
					showAllRolls(theDep);
				}
			}
		});
	}

	public void LinkEmployeeAndRoll(Employee theEmployee, Role theRole) {
		try {
			theEmployee.setRole(theRole);
		} catch (AlreadyExistException e) {
			showMessageError(e.getMessage());
		}
		theRole.setEmployee(theEmployee);
	}

	public void setEmployeeRoll(Employee theEmployee, Role theRole) {
		theRole.setEmployee(null);
		theEmployee.removeRole();
	}

	public void changeEmployeeInRoll(Role theRole, Department theDep) {
		gpChangeEmployeeInRoll = new GridPane();
		gpChangeEmployeeInRoll.setPadding(new Insets(10));
		gpChangeEmployeeInRoll.setHgap(10);
		gpChangeEmployeeInRoll.setVgap(10);

		Label lblNameOfCurrentEmployee = new Label("The current Employee is: " + theRole.getTheEmployee().getName());
		gpChangeEmployeeInRoll.add(lblNameOfCurrentEmployee, 0, 0);

		Label lblIsFireTheEmployee = new Label("Would you like the Fire the Employee? ");
		gpChangeEmployeeInRoll.add(lblIsFireTheEmployee, 0, 1);
		CheckBox ckFireTheEmployee = new CheckBox();
		gpChangeEmployeeInRoll.add(ckFireTheEmployee, 1, 1);
		Button btnChooseNewEmployee = new Button("Choose new Employee");
		gpChangeEmployeeInRoll.add(btnChooseNewEmployee, 0, 2);
		Button btnReturn = new Button("Return");
		gpChangeEmployeeInRoll.add(btnReturn, 1, 2);

		bpRoot.setCenter(gpChangeEmployeeInRoll);

		btnChooseNewEmployee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				if (ckFireTheEmployee.isSelected()) { // yes- fire the employee
					for (ViewListenable l : allListeners) {
						try {
							l.viewRemoveEmployee(theRole.getTheEmployee());
						} catch (EntityIsNotExist e) {
							showMessageError(e.getMessage());
						}
						l.clearEmployeeFromRole(theRole);
						lblTheEmp = null;
					}
				} else {
					for (ViewListenable l : allListeners) {
						l.updateModelAboutRemoveEmployeeFromRoll(theRole.getTheEmployee(), theRole);
					}
				}
				addEmployeeToRole(theRole, theDep);
			}
		});

		btnReturn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				showAllRolls(theDep);
			}
		});
	}

	public void cleanEmployeeFromARoll(Role theRole) {
		theRole.setEmployee(null);
	}

	// #######################################################################################################
	public void changeDepWorkingHours() {
		gpChangeDepHours = new GridPane();
		gpChangeDepHours.setPadding(new Insets(10));
		gpChangeDepHours.setHgap(10);
		gpChangeDepHours.setVgap(10);
		gpChangeDepHours.setPrefWidth(450);
		Label lblTitle = new Label("Change working hours for a synchronized department: ");
		gpChangeDepHours.add(lblTitle, 0, 0, 2, 1);
		lblTitle.setTextFill(Color.ROYALBLUE);
		lblTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));

		Label lblChoseDepartment = new Label("choose a department: ");
		gpChangeDepHours.add(lblChoseDepartment, 0, 1);
		Set<Department> allSynchronicDep = allListeners.get(0).viewUpdateGetSynchronicDepartments();
		ComboBox<Department> cmbTheDepartment = new ComboBox<Department>();
		cmbTheDepartment.getItems().addAll(allSynchronicDep);
		gpChangeDepHours.add(cmbTheDepartment, 1, 1);

		Label lblWorkingHoursType = new Label("choose the type: ");
		gpChangeDepHours.add(lblWorkingHoursType, 0, 2);
		ComboBox<ePreferences> cmbWorkingHoursDepType = new ComboBox<ePreferences>();
		cmbWorkingHoursDepType.getItems().addAll(ePreferences.values());
		gpChangeDepHours.add(cmbWorkingHoursDepType, 1, 2);

		Label lblStartHourDep = new Label("choose start hours: ");
		gpChangeDepHours.add(lblStartHourDep, 0, 3);
		ComboBox<LocalTime> cmbNewHoursDep = new ComboBox<LocalTime>();

		VBox vbAllEmployees = new VBox();
		vbAllEmployees.setVisible(false);
		gpChangeDepHours.add(vbAllEmployees, 0, 5);
		cmbTheDepartment.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				if(cmbTheDepartment.getValue() != null) {
					vbAllEmployees.getChildren().clear();
					Department theSelectedDep = cmbTheDepartment.getValue();
					final double COL_SIZE_ID = 50;
					final double COL_SIZE_NAME = 80;
					final double COL_SIZE_WORK_HOURS = 80;

					ListView<HBox> lstRoles = new ListView<HBox>();
					VBox vbEmployees = new VBox(lstRoles);

					HBox hbEmpHeadline = new HBox();
					Label lblEmpTitleNumID = new Label("ID");
					Label lblEmpTitleRoleName = new Label("Name");
					Label lblEmpTitleWorkHours = new Label("Work Hours");
					lblEmpTitleNumID.setPrefWidth(COL_SIZE_ID);
					lblEmpTitleRoleName.setPrefWidth(COL_SIZE_NAME);
					lblEmpTitleWorkHours.setPrefWidth(COL_SIZE_WORK_HOURS);
					hbEmpHeadline.getChildren().addAll(lblEmpTitleNumID, lblEmpTitleRoleName, lblEmpTitleWorkHours);

					Set<Role> allRoles = theSelectedDep.getAllRoles();
					if (!allRoles.isEmpty()) {
						for (Role R : allRoles) {
							if (R.getTheEmployee() != null) {
								GridPane gpEmpDetails = new GridPane();

								Label lblEmpNumID = new Label(R.getTheEmployee().getIdEmployee() + "");
								Label lblEmpName = new Label(R.getTheEmployee().getName());
								Label lblEmpWorkHours = new Label(R.getTheEmployee().getEmployeePreference().toString());

								lblEmpNumID.setPrefWidth(COL_SIZE_ID);
								lblEmpName.setPrefWidth(COL_SIZE_NAME);
								lblEmpWorkHours.setPrefWidth(COL_SIZE_WORK_HOURS);

								gpEmpDetails.addRow(0, lblEmpNumID, lblEmpName, lblEmpWorkHours);
								HBox hbEmployees = new HBox();
								hbEmployees.getChildren().add(gpEmpDetails);
								lstRoles.getItems().add(hbEmployees);
							}
						}

						vbAllEmployees.getChildren().addAll(hbEmpHeadline, new Separator(Orientation.HORIZONTAL), vbEmployees);
						vbAllEmployees.setVisible(true);
					}
				}
			}
		});


		cmbWorkingHoursDepType.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				lblStartHourDep.setVisible(false);
				cmbNewHoursDep.setVisible(false);
				cmbNewHoursDep.getItems().clear();
				if (cmbWorkingHoursDepType.getValue() != null) {
					switch (cmbWorkingHoursDepType.getValue()) {
					case Earlier:
						for (int i = Company.REGULAR_START_HOUR.getHour() - 3; i < Company.REGULAR_START_HOUR
								.getHour(); i++) {
							cmbNewHoursDep.getItems().add(LocalTime.of(i, 0));
						}
						lblStartHourDep.setVisible(true);
						cmbNewHoursDep.setVisible(true);
						break;
					case Later:
						for (int i = Company.REGULAR_START_HOUR.getHour() + 1; i < Company.REGULAR_START_HOUR.getHour()
								+ 5; i++) {
							cmbNewHoursDep.getItems().add(LocalTime.of(i, 0));
						}
						lblStartHourDep.setVisible(true);
						cmbNewHoursDep.setVisible(true);
					default:
						break;
					}
				}
			}
		});
		gpChangeDepHours.add(cmbNewHoursDep, 1, 3);

		Button btnchangeDepHours = new Button("change hours");
		gpChangeDepHours.add(btnchangeDepHours, 0, 4);

		btnchangeDepHours.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				if (cmbTheDepartment.getValue() == null || cmbWorkingHoursDepType.getValue() == null
						|| (cmbWorkingHoursDepType.getValue() == ePreferences.Earlier
						&& cmbNewHoursDep.getValue() == null)
						|| (cmbWorkingHoursDepType.getValue() == ePreferences.Later
						&& cmbNewHoursDep.getValue() == null)) {
					showMessageError("All fields must be selected");
				} else {
					for (ViewListenable l : allListeners) {
						Preference thePref = null;
						ePreferences thePreference = cmbWorkingHoursDepType.getValue();
						if (thePreference == (ePreferences.Home))
							try {
								thePref = new Preference(thePreference);
							} catch (PreferenceTimeException e) {
								showMessageError(e.getMessage());
							}
						else
							thePref = new Preference(thePreference, cmbNewHoursDep.getValue());
						try {
							l.viewUpdatechangeDepartmentHours(cmbTheDepartment.getValue(), thePref);
						} catch (WorkHoursTimeException e) {
							showMessageError(e.getMessage());
						}
					}
					showMessage("The change completed");
					cmbTheDepartment.setValue(null);
					cmbWorkingHoursDepType.setValue(null);
					cmbNewHoursDep.setValue(null);
					vbAllEmployees.setVisible(false);
				}
			}
		});
	}

	// ###################################################################################################
	public void changeRollWorkingHours() {
		gpChangeRollHours = new GridPane();
		gpChangeRollHours.setPadding(new Insets(10));
		gpChangeRollHours.setHgap(10);
		gpChangeRollHours.setVgap(10);

		Label lblTitle = new Label("Change working hours for a dynamic roll: ");
		gpChangeRollHours.add(lblTitle, 0, 0, 2, 1);
		lblTitle.setTextFill(Color.ROYALBLUE);
		lblTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));

		Label lblChooseRoll = new Label("choose a roll: ");
		gpChangeRollHours.add(lblChooseRoll, 0, 1);
		ComboBox<Role> cmbAllRolls = new ComboBox<Role>();
		Label lblNoRoles = new Label("There are no roles");
		lblNoRoles.setVisible(false);

		Set<Role> allDynamicRoles = allListeners.get(0).viewUpdateGetDynamicRolls();
		if (allDynamicRoles != null) {
			cmbAllRolls.getItems().addAll(allDynamicRoles);
			lblNoRoles.setVisible(false);

		} else {
			cmbAllRolls.setVisible(false);
			gpChangeRollHours.add(lblNoRoles, 1, 1);
			lblNoRoles.setVisible(true);
		}
		gpChangeRollHours.add(cmbAllRolls, 1, 1);

		Label lblWorkingHoursType = new Label("choose the type: ");
		gpChangeRollHours.add(lblWorkingHoursType, 0, 2);
		ComboBox<ePreferences> cmbWorkingHoursRollType = new ComboBox<ePreferences>();
		cmbWorkingHoursRollType.getItems().addAll(ePreferences.values());
		gpChangeRollHours.add(cmbWorkingHoursRollType, 1, 2);

		Label lblStartHourRoll = new Label("choose start hour: ");
		gpChangeRollHours.add(lblStartHourRoll, 0, 3);
		ComboBox<LocalTime> cmbNewHoursRoll = new ComboBox<LocalTime>();
		Label lblPrefHoursOfEmployee = new Label();
		lblPrefHoursOfEmployee.setVisible(false);
		lblPrefHoursOfEmployee.setTextFill(COLOR_MNG_BUTTON_REMOVE_SELECTED);
		gpChangeRollHours.add(lblPrefHoursOfEmployee, 0, 5);
		cmbAllRolls.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				if(cmbAllRolls.getValue() != null) {
					Role theSelectedRole = cmbAllRolls.getValue();
					if(theSelectedRole.getTheEmployee() != null) {
						lblPrefHoursOfEmployee.setText("The employee: " +theSelectedRole.getTheEmployee().getName()
								+ "\nthePreference: " + theSelectedRole.getTheEmployee().getEmployeePreference().toString());
						lblPrefHoursOfEmployee.setVisible(true);
					}
					else
						lblPrefHoursOfEmployee.setText("There is no employee");
				}
			}
		});

		cmbWorkingHoursRollType.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				lblStartHourRoll.setVisible(false);
				cmbNewHoursRoll.setVisible(false);
				cmbNewHoursRoll.getItems().clear();
				if (cmbWorkingHoursRollType.getValue() != null) {
					switch (cmbWorkingHoursRollType.getValue()) {
					case Earlier:
						for (int i = Company.REGULAR_START_HOUR.getHour() - 3; i < Company.REGULAR_START_HOUR
								.getHour(); i++) {
							cmbNewHoursRoll.getItems().add(LocalTime.of(i, 0));
						}
						lblStartHourRoll.setVisible(true);
						cmbNewHoursRoll.setVisible(true);
						break;
					case Later:
						for (int i = Company.REGULAR_START_HOUR.getHour() + 1; i < Company.REGULAR_START_HOUR.getHour()
								+ 5; i++) {
							cmbNewHoursRoll.getItems().add(LocalTime.of(i, 0));
						}
						lblStartHourRoll.setVisible(true);
						cmbNewHoursRoll.setVisible(true);
					default:
						break;
					}
				}
			}
		});
		gpChangeRollHours.add(cmbNewHoursRoll, 1, 3);

		Button btnchangeDepHours = new Button("change hours");
		gpChangeRollHours.add(btnchangeDepHours, 0, 4);

		btnchangeDepHours.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				if (cmbAllRolls.getValue() == null || cmbWorkingHoursRollType.getValue() == null
						|| (cmbWorkingHoursRollType.getValue() == ePreferences.Earlier
						&& cmbNewHoursRoll.getValue() == null)
						|| (cmbWorkingHoursRollType.getValue() == ePreferences.Later
						&& cmbNewHoursRoll.getValue() == null)) {
					showMessageError("All fields must be selected");
				} else {
					for (ViewListenable l : allListeners) {
						Preference thePref = null;
						ePreferences thePreference = cmbWorkingHoursRollType.getValue();
						if (thePreference == (ePreferences.Home))
							try {
								thePref = new Preference(thePreference);
							} catch (PreferenceTimeException e) {
								showMessageError(e.getMessage());
							}
						else
							thePref = new Preference(thePreference, cmbNewHoursRoll.getValue());
						l.viewUpdatechangeRollHours(cmbAllRolls.getValue(), thePref);
					}
					showMessage("The change completed");
					cmbAllRolls.setValue(null);
					cmbWorkingHoursRollType.setValue(null);
					cmbNewHoursRoll.setValue(null);
					lblPrefHoursOfEmployee.setVisible(false);
				}
			}
		});
	}
	// ####################################### change working hours ##############################################

	public void changeWorkingHours() {
		hbChangingHours = new HBox();
		changeDepWorkingHours();
		changeRollWorkingHours();
		hbChangingHours.getChildren().addAll(gpChangeDepHours, new Separator(Orientation.VERTICAL), gpChangeRollHours);
		bpRoot.setCenter(hbChangingHours);
	}

	// ######################################### show efficiency ###############################################

	public void showEfficieny() {
		VBox vbShowEfficiencyBoard = new VBox();
		HBox hbShowOpption = new HBox();
		gpEfficiency = new GridPane();
		Button btnCompanyEfficiency = new Button("Company");
		Button btnDepartmentEfficiency = new Button("Department");
		Button btnEmployeesEfficiency = new Button("Employees");
		btnCompanyEfficiency.setMaxSize(350, 350);
		btnDepartmentEfficiency.setMaxSize(350, 350);
		btnEmployeesEfficiency.setMaxSize(350, 350);
		hbShowOpption.setSpacing(20);
		hbShowOpption.setPadding(new Insets(20));
		hbShowOpption.setAlignment(Pos.TOP_CENTER);
		gpEfficiency.setPadding(new Insets(20));
		gpEfficiency.setAlignment(Pos.TOP_CENTER);

		hbShowOpption.getChildren().addAll(btnCompanyEfficiency, btnDepartmentEfficiency, btnEmployeesEfficiency);
		vbShowEfficiencyBoard.getChildren().addAll(hbShowOpption, gpEfficiency);

		bpRoot.setCenter(vbShowEfficiencyBoard);

		btnCompanyEfficiency.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				double companyEfficient = allListeners.get(0).viewUpdateGetCompanyEfficiency();
				double companyProfit = allListeners.get(0).viewUpdateGetCompanyProfit();
				Label lblOpeningSentence = new Label("                                           With all the dots that we connected ");
				Label lblCompanyFinalEfficiency = new Label("                           The company's efficiency is: " + 
				String.format(" %,.2f",companyEfficient)+" [hours/employee]");
				Label lblCompanyFinalProfit = new Label("                              The company's profit is: " + 
						String.format("       %,.2f",companyProfit) + " [Money/day]");
				
				if (companyEfficient < 0) {
					lblCompanyFinalEfficiency.setTextFill(Color.INDIANRED);
				} else {
					lblCompanyFinalEfficiency.setTextFill(Color.SEAGREEN);
				}
				
				if (companyEfficient < 0) {
					lblCompanyFinalProfit.setTextFill(Color.INDIANRED);
				} else {
					lblCompanyFinalProfit.setTextFill(Color.SEAGREEN);
				}
				lblOpeningSentence.setTextFill(Color.BLACK);
				lblOpeningSentence.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
				lblCompanyFinalEfficiency.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
				lblCompanyFinalProfit.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));

				lblCompanyFinalEfficiency.setPadding(new Insets(10));
				gpEfficiency.setBackground(BACKGOUND_FOR_TITLES);
				gpEfficiency.setAlignment(Pos.CENTER);
				gpEfficiency.getChildren().clear();
				gpEfficiency.add(lblOpeningSentence, 0, 0);
				gpEfficiency.add(lblCompanyFinalEfficiency, 0, 1);
				gpEfficiency.add(lblCompanyFinalProfit, 0, 2);
				setDataCompanyPane();
			}
		});

		btnDepartmentEfficiency.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				final CategoryAxis xAxis = new CategoryAxis();
				final NumberAxis yAxis = new NumberAxis();
				final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
				bc.setTitle("Department Efficiency");
				xAxis.setLabel("Department name");
				yAxis.setLabel("Efficient value");

				XYChart.Series<String, Number> series1 = new Series<String, Number>();

				for (Department theDep : allListeners.get(0).viewUpdateGetAllDepartments()) {
					series1.getData().add(new Data<String, Number>(theDep.getName(), theDep.calcDepartmentEfficient()));
				}

				bc.getData().addAll(series1);
				gpEfficiency.setBackground(BACKGOUND_FOR_TITLES);
				gpEfficiency.setAlignment(Pos.CENTER);
				gpEfficiency.getChildren().clear();
				gpEfficiency.add(bc, 0, 0);
			}
		});

		btnEmployeesEfficiency.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				final CategoryAxis xAxis = new CategoryAxis();
				final NumberAxis yAxis = new NumberAxis();
				final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
				bc.setTitle("Department Efficiency");
				xAxis.setLabel("Employee name");
				yAxis.setLabel("Efficient value");

				XYChart.Series<String, Number> series1 = new Series<String, Number>();

				for (Employee theEmp : allListeners.get(0).viewUpdateGetAllEmployeesWhoHaveRole()) {
					series1.getData().add(new Data<String, Number>(theEmp.getName(),
							theEmp.getTheRole().calcEfficient()));
				}
				bc.getData().addAll(series1);
				gpEfficiency.setBackground(BACKGOUND_FOR_TITLES);
				gpEfficiency.setAlignment(Pos.CENTER);
				gpEfficiency.getChildren().clear();
				gpEfficiency.add(bc, 0, 0);
			}
		});
	}

	public void setDataCompanyPane() {
		final double barLimitCompany = allListeners.get(0).viewUpdateGetAllEmployeesWhoHaveRole().size()
				* Role.EFFICIENCY * Company.WORKING_HOURS_PER_DAY;

		final NumberAxis xAxis = new NumberAxis(-(barLimitCompany), barLimitCompany, 1);
		final CategoryAxis yAxis = new CategoryAxis();
		final BarChart<Number, String> bcEfficiency = new BarChart<Number, String>(xAxis, yAxis);

		bcEfficiency.setTitle("Efficiency");
		xAxis.setLabel("Extra efficiency");

		XYChart.Series<Number, String> dsEfficiencyDaya = new Series<Number, String>();
		dsEfficiencyDaya.getData()
		.add(new XYChart.Data<Number, String>(allListeners.get(0).viewUpdateGetCompanyEfficiency(), ""));

		bcEfficiency.getData().addAll(dsEfficiencyDaya);
		bcEfficiency.setMaxHeight(10);

		if ((double) dsEfficiencyDaya.getData().get(0).getXValue() > 0) {
			dsEfficiencyDaya.getData().get(0).getNode().setStyle("-fx-bar-fill: green;");
		} else {
			dsEfficiencyDaya.getData().get(0).getNode().setStyle("-fx-bar-fill: red;");
		}

		gpEfficiency.add(bcEfficiency, 0, 3);
	}

	// ###########################################################################################################

	public void setHeadLineDesign() {
		vbChangeTitle = new VBox();
		hbHeadLine = new HBox();
		hbChangeName = new HBox();
		lblNewCompanyName = new Label("Company name:");
		TextField tfCompanyName = new TextField();
		lblNewCompanyName.setVisible(false);		
		tfCompanyName.setVisible(false);
		btnChangeTheName.setVisible(false);
		lblCompany = new Label(allListeners.get(0).getNameFromModel());
		lblCompany.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		lblCompany.setPadding(new Insets(10));
		lblCompany.setTextFill(Color.BLACK);
		vbChangeTitle.setBackground(BACKGOUND_MENU);
		hbHeadLine.getChildren().addAll( lblCompany , btnEditName);
		hbChangeName.getChildren().addAll(lblNewCompanyName , tfCompanyName, btnChangeTheName);
		vbChangeTitle.getChildren().addAll(hbHeadLine, hbChangeName);
		btnChangeTheName.setMaxSize(200, 200);

		hbHeadLine.setPadding(new Insets(10));
		hbHeadLine.setSpacing(30);
		hbChangeName.setPadding(new Insets(10));
		hbChangeName.setSpacing(30);

		hbHeadLine.setAlignment(Pos.CENTER);
		hbChangeName.setAlignment(Pos.CENTER);

		btnEditName.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				lblNewCompanyName.setVisible(true);
				tfCompanyName.setVisible(true);
				btnChangeTheName.setVisible(true);
			}
		});

		btnChangeTheName.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				if(tfCompanyName.getText() != null) {
					lblCompany.setText(tfCompanyName.getText());
				}
				lblNewCompanyName.setVisible(false);
				tfCompanyName.setVisible(false);
				btnChangeTheName.setVisible(false);
				tfCompanyName.setText(null);
			}
		});
		bpRoot.setTop(vbChangeTitle);
	}

	public void showMessageError(String theText) {
		ImageIcon iconSad = new ImageIcon("sad.png");
		JOptionPane.showMessageDialog(null, theText, "Error", JOptionPane.ERROR_MESSAGE, iconSad);
	}

	public void showMessage(String theText) {
		ImageIcon icon = new ImageIcon("smiley.png");
		JOptionPane.showMessageDialog(null, theText, "Succeed", JOptionPane.INFORMATION_MESSAGE, icon);
	}
}