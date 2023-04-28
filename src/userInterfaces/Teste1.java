package userInterfaces;
import entidades.ContaCorrente;

public class Teste1 {
	public static void main(String[] args) {
		
		ContaCorrente conta1, conta2, conta3;
		
		conta1 = new ContaCorrente();
		
		conta1.depositar(1000);
		
		System.out.printf("saldo da conta1: %f", conta1.getSaldo());

		
	}
}
