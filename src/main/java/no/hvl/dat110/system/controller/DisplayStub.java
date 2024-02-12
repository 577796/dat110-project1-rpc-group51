package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {

		super(rpcclient);
	}
	
	public void write (String message) {
		
		byte[] messageBytes = RPCUtils.marshallString(message);
		byte[] returnValue = rpcclient.call((byte)Common.WRITE_RPCID, messageBytes);
		RPCUtils.unmarshallVoid(returnValue);
		
	}
}
