package entidades;

public class ContaCorrente {
	private static int contadorContas=1;
	private int numero;
	private double saldo;
	private Cliente titular;

	public ContaCorrente(double saldo, Cliente titularDaConta) {
		this.numero = ContaCorrente.contadorContas++;
		this.saldo = saldo;
		this.titular = titularDaConta;
	}
	public ContaCorrente() {
		this.numero = ContaCorrente.contadorContas++;
	}
	
	public void depositar(double valor){
		this.saldo += valor;
	}

	public double sacar(double valor){
		double saldoPosSaque = this.saldo - valor;
		
		if (saldoPosSaque < 0) {
			System.out.println("Saldo insuficiente");
			return 0;
		}
		
		this.saldo = saldoPosSaque;
		return valor;
	}
	
	public double getSaldo() {
		return this.saldo;
	}
	
	public int getNumero () {
		return this.numero;
	}
	
	public void setNumero (int numero) {
		this.numero = numero;
	}
	
	public void setTitular(Cliente titular) {
		this.titular = titular;
	}
	
	public Cliente getTitular() {
		return this.titular;
	}
	
}
