package principal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LectorEscritorApp extends Application {
	
	LectorEscritorController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		controller = new LectorEscritorController();
		
		Scene scene = new Scene (controller.getView(),500, 600);
		
		primaryStage.setTitle("Lanzador de Tareas Lector/Escritor");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
