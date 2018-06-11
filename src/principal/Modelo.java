package principal;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Modelo {

	private ListProperty<String> dentroList;
	
	private ListProperty<String> fueraList;
	
	public Modelo() {

		dentroList = new SimpleListProperty<>(this,"",FXCollections.observableArrayList());
		fueraList = new SimpleListProperty<>(this,"",FXCollections.observableArrayList());
		
	}

	public final ListProperty<String> dentroListProperty() {
		return this.dentroList;
	}
	

	public final ObservableList <String> getDentroList() {
	return this.dentroListProperty().get();
	}
	

	public final  void setDentroList(final ObservableList <String> dentroList) {
	this.dentroListProperty().set(dentroList);
	}
	

	public final ListProperty<String> fueraListProperty() {
		return this.fueraList;
	}
	

	public final ObservableList <String> getFueraList() {
	return this.fueraListProperty().get();
	}
	

	public final  void setFueraList(final ObservableList <String> fueraList) {
	this.fueraListProperty().set(fueraList);
	}
	

}
