package Main;

import controler.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Company;
import view.CompanyView;

public class CompanyMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		Company theModel = new Company("");
		CompanyView theView = new CompanyView(primaryStage);
		Controller theController = new Controller(theView, theModel);
	}
}
