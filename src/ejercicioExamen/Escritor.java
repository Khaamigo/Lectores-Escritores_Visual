package ejercicioExamen;

import java.util.concurrent.Semaphore;

import javafx.application.Platform;
import principal.Modelo;

public class Escritor extends Thread {
	private Semaphore noLectores;
	private LightSwitch escritoresLightSwitch;
	private Semaphore noEscritores;
	private Modelo modelo;

	public Escritor(LightSwitch leerSwitch, Semaphore noEscritores, Semaphore noLectores, Modelo modelo) {
		this.escritoresLightSwitch = leerSwitch;
		this.noEscritores = noEscritores;
		this.noLectores = noLectores;
		this.modelo = modelo;
	}

	@Override
	public void run() {
		super.run();
		try {
			escritoresLightSwitch.lock(noLectores);
			noEscritores.acquire();
			
			
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					modelo.fueraListProperty().remove(getName());
					modelo.dentroListProperty().add(getName());
				}
			});
			sleep((long) (Math.random() * 2000 + 1000));
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					modelo.dentroListProperty().remove(getName());
				}
			});
			noEscritores.release();
			escritoresLightSwitch.unlock(noLectores);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
