package view;

import java.util.concurrent.Semaphore;
import controller.BancoController;

public class Main {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		
		for(int i = 1; i < 21; i++) {
			Thread t = new BancoController(i, semaforo);
			t.start();
		}
	}

}
