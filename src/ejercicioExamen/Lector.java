package ejercicioExamen;

import java.util.concurrent.Semaphore;

import javafx.application.Platform;
import principal.Modelo;

public class Lector extends Thread {
	private Semaphore noLectores;
	private LightSwitch lectoresLightSwitch;
	private Semaphore noEscritores;
	private Modelo modelo;

	public Lector(LightSwitch leerSwitch, Semaphore noEscritores, Semaphore noLectores, Modelo modelo) {
		this.lectoresLightSwitch = leerSwitch;
		this.noEscritores = noEscritores;
		this.noLectores = noLectores;
		this.modelo = modelo;
	}

	@Override
	public void run() {
		super.run();
		try {
			noLectores.acquire();
			lectoresLightSwitch.lock(noEscritores);
			noLectores.release();
			
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
		
			lectoresLightSwitch.unlock(noEscritores);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
