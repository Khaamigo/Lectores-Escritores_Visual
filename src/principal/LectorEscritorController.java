package principal;

import java.io.IOException;
import java.util.concurrent.Semaphore;

import ejercicioExamen.Escritor;
import ejercicioExamen.Lector;
import ejercicioExamen.LightSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LectorEscritorController extends VBox {

    @FXML
    private TextField numeroText;

    @FXML
    private TextField pobabilidadText;

    @FXML
    private Button crearButton;

    @FXML
    private ListView<String> fueraList;

    @FXML
    private ListView<String> dentroList;

    @FXML
	private VBox view;

	private Semaphore noEscritores, noLectores;
	
	private LightSwitch intercambiador1, intercambiador2;

	
	private Modelo modelo;
	
	private int idLector = 0;
	private int idEscritor = 0;

	public LectorEscritorController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LectorEscritorView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		noEscritores = new Semaphore(1);
		noLectores = new Semaphore(1);
		
		intercambiador1 = new LightSwitch();
		intercambiador2 = new LightSwitch();
		
		modelo = new Modelo();

		fueraList.itemsProperty().bind(modelo.fueraListProperty());
		
		dentroList.itemsProperty().bind(modelo.dentroListProperty());
		
	}

	@FXML
	public void onCrearButtonAction(ActionEvent event) {
		int numTareas;
		float probabiliLector;
		numTareas=Integer.parseInt(numeroText.getText());
		probabiliLector=Float.parseFloat(this.pobabilidadText.getText());
		
		Thread aux;
		for (int hilo = 0; hilo < numTareas; hilo++) {
			
			if(Math.random()<probabiliLector){
				aux=new Lector(intercambiador1, noEscritores, noLectores, modelo);
				aux.setName("Lector "+(++idLector));
			}
			else{
				aux=new Escritor(intercambiador2, noEscritores, noLectores, modelo);
				aux.setName("Escritor "+(++idEscritor));
			}
			modelo.getFueraList().add(aux.getName());
			aux.start();
		}
	}


	public VBox getView() {
		return view;
	}

}