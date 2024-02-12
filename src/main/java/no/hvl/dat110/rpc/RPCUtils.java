package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import no.hvl.dat110.TODO;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		
		byte[] rpcmsg = null;
		
		// TODO - START
		int totalLength = 1 + payload.length;
		rpcmsg = new byte[totalLength];

		rpcmsg[0] = rpcid;

		System.arraycopy(payload, 0, rpcmsg, 1, payload.length);
		// Encapsulate the rpcid and payload in a byte array according to the RPC message syntax / format

		return rpcmsg;
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {
		
		byte[] payload = null;

		if(rpcmsg == null || rpcmsg.length < 1){
			throw new IllegalArgumentException("Invalid RPS message");
		}

		byte rpcid = rpcmsg[0];

		int payloadLength = rpcmsg.length - 1;

		payload = new byte[payloadLength];

		System.arraycopy(rpcmsg, 1, payload, 0, payloadLength);

		return payload;
		
	}

	// convert String to byte array
	public static byte[] marshallString(String str) {
		
		byte[] encoded = null;
		
		if(str == null){
			throw new IllegalArgumentException("Size can´t be zero");
		}

		encoded = str.getBytes(StandardCharsets.UTF_8);
		
		return encoded;
	}

	// convert byte array to a String
	public static String unmarshallString(byte[] data) {
		
		String decoded = null; 
		
		if(data == null){
			throw new IllegalArgumentException("Size can´t be zero");
		}

		decoded = new String(data, StandardCharsets.UTF_8);

		return decoded;
	}
	
	public static byte[] marshallVoid() {
		
		byte[] encoded = null;
		
		encoded = new byte[0];
		
		return encoded;
		
	}
	
	public static void unmarshallVoid(byte[] data) {

		// TODO

		if (data != null && data.length != 0) {
			throw new UnsupportedOperationException("Unexpected data received for void type");
		}

	}

	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}

	// convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
		
	}

	// integer to byte array representation
	public static byte[] marshallInteger(int x) {
		
		byte[] encoded = null;

		encoded = new byte[4];

		encoded[0] = (byte) (x>>24);
		encoded[1] = (byte) (x>>16);
		encoded[2] = (byte) (x>>8);
		encoded[3] = (byte) (x);
		
		return encoded;
	}
	
	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {
		
		int decoded = 0;

		if (data == null || data.length != 4) {
			throw new IllegalArgumentException("Invalid byte array for integer decoding");
		}

		// Decode the byte array into an integer
		decoded = ((data[0] & 0xFF) << 24) |
				((data[1] & 0xFF) << 16) |
				((data[2] & 0xFF) << 8) |
				(data[3] & 0xFF);


		return decoded;
		
	}
}
