package controller;

import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

public class BancoController extends Thread{
	Semaphore semaforo;
	private int cod;
	private double saldo;
	private double valor;
	DecimalFormat df = new DecimalFormat("#.##");
	
	public BancoController(int cod, Semaphore semaforo) {
		this.cod = cod;
		this.semaforo = semaforo;
	}
	
	private void saldo() {
		this.saldo = (double) ((Math.random()  * 901) + 99);
		this.valor = (double) ((Math.random()  * 801) + 99);

	} 
	
	@Override
	public void run() {
		saldo();
		depositar();
		sacar();	
	}
	
	private void sacar() {
		try {
			semaforo.acquire();
			if(valor > saldo) {
				System.err.println("Saldo insuficiente para o saque de " + df.format(valor) + "solicitado da conta " + cod);
			} else {
				System.out.println("A conta " + cod + " est� fazendo saque de R$" + df.format(valor) + ". Valor restante em conta: R$" + df.format(saldo - valor));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}
	
	private void depositar() {
		try {
			semaforo.acquire();
			this.saldo += this.valor;
			System.out.println("A conta " + cod + " Est� fazendo um deposito de R$" + df.format(valor) + ". Saldo em conta: R$" + df.format(saldo));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}
}