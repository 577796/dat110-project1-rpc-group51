package no.hvl.dat110.system.display;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessageConnection;
import no.hvl.dat110.messaging.MessagingServer;
import no.hvl.dat110.rpc.*;
import no.hvl.dat110.system.controller.Common;

import java.util.HashMap;


public class DisplayDevice {

    public static void main(String[] args) {

        System.out.println("Display server starting ...");


        // TODO - START
        RPCServer displayRPCServer = new RPCServer(Common.DISPLAYPORT);


        // Register the display implementation with the services hashmap using the assigned identifier
        DisplayImpl displayImpl = new DisplayImpl((byte) Common.WRITE_RPCID, displayRPCServer);


        displayRPCServer.run();

        // TODO - END

        System.out.println("Display server stopping ...");
    }
}
