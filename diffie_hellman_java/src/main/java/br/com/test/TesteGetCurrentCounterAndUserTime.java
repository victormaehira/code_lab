package br.com.test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TesteGetCurrentCounterAndUserTime {
	public static void main(String[] args) {
		
		
		long now = new java.util.Date().getTime();
		System.out.println("now = " + now);
		
		long millisecondsSinceBeginning = (now - Long.parseLong("1512151500000"));
		System.out.println("millisecondsSinceBeginning = " + millisecondsSinceBeginning);
		
		long millisecondsToStepBy = 30 * 1000;
		long counterAsNumber = millisecondsSinceBeginning / millisecondsToStepBy;
		
		
		//byte currentCounter[] = getCurrentCounterAndUsedTime();
		//System.out.println("currentCounter = " + Arrays.toString(currentCounter));
		//byte[] testeByteToString = {0, 0, 0, 0, 0, 41, 33, -71};
	}
	
	private static byte[] getCurrentCounterAndUsedTime() {
		long now = new java.util.Date().getTime();

		long millisecondsSinceBeginning = (now - Long.parseLong("1512151500000"));
		long millisecondsToStepBy = 30 * 1000;
		long counterAsNumber = millisecondsSinceBeginning / millisecondsToStepBy;

		// transforma a data em array de 8 bytes
		ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE / 8);
		buffer.order(ByteOrder.BIG_ENDIAN);
		buffer.putLong(counterAsNumber);
		buffer.flip();
		byte[] counterAsByteArray = buffer.array();
		return counterAsByteArray;
	}
}
