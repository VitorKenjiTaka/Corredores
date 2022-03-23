package Controller;

import java.util.concurrent.Semaphore;

public class Processos extends Thread{
	private int threadId;
	private Semaphore semaforo;
	static private int colo = 1;
	
	public Processos(int threadId, Semaphore semaforo) {
		this.threadId = threadId;
		this.semaforo = semaforo;
	}
	
	public void run() {
		
		Corrida();
		porta();
		
		
	}

	private void Corrida() {
		int total = 200;
		int dist = 0;
		while(dist < total){
			int pulo = (int) (Math.random() * 3) + 4 ;
			dist = dist + pulo;
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("A pessoa #" + threadId + " andou "+pulo+" metros e está a "+ (total - dist)+ " da porta!");
		}
	}

	private void porta() {
		
		try {
			semaforo.acquire();
			
			System.out.println("A pessoa #" + threadId + " está travessando a porta.");
			int temp = (int) (Math.random() * 1001) + 1000 ;
			sleep(temp);
			System.out.println("A pessoa #" + threadId + " chegou em: "+ colo + " lugar!!!");
			colo++;
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			semaforo.release();
		}
	}
}