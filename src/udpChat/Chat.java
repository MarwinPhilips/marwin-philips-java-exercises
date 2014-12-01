package udpChat;

import java.io.IOException;

public interface Chat {
    public byte[] receive() throws IOException;
    public void send(byte[] message) throws IOException;
}