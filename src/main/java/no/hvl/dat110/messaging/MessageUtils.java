package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageUtils {

    public static final int SEGMENTSIZE = 128;

    public static int MESSAGINGPORT = 8080;
    public static String MESSAGINGHOST = "localhost";

    public static byte[] encapsulate(Message message) {

        byte[] segment = null;
        byte[] data = message.getData();
        // TODO - START
        int dataLength = data.length;
        // encapulate/encode the payload data of the message and form a segment
        // according to the segment format for the messaging layer
        if (dataLength > 127)
            throw new IllegalArgumentException("Message to long");

        // TODO - END
        segment = new byte[128];

        segment[0] = (byte) dataLength;

        System.arraycopy(data, 0, segment, 1, dataLength);
        return segment;

    }

    public static Message decapsulate(byte[] segment) {

        Message message = null;

        // TODO - START
        // decapsulate segment and put received payload data into a message

        if (segment == null || segment.length != SEGMENTSIZE) {
            throw new IllegalArgumentException("invalid segment");
        }
        // TODO - END
        int dataLength = segment[0] & 0xFF;

        byte[] data = new byte[dataLength];
        System.arraycopy(segment, 1, data, 0, dataLength);

        message = new Message(data);

        return message;


    }
}
