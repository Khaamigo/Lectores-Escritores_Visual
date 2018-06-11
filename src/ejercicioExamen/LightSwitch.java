package ejercicioExamen;

import java.util.concurrent.Semaphore;

public class LightSwitch {

	private int cont;
	private Semaphore mutex;
	
	public LightSwitch() {
		
		this.cont = 0;
		this.mutex = new Semaphore(1);

	}
	
	 public void lock (Semaphore semaphore){
	 	try {
	 		mutex.acquire();
		 	cont ++;
	 		if (cont == 1){
				semaphore.acquire();
	 		}
		} catch (InterruptedException e) {
				e.printStackTrace();
			}
	 		mutex.release();
	 }
	 
	 public void unlock (Semaphore semaphore){
		 try {
		 		mutex.acquire();
			 	cont --;
		 		if (cont == 0){
					semaphore.release();
		 		}
			} catch (InterruptedException e) {
					e.printStackTrace();
				}
		 		mutex.release();
	 }

}
