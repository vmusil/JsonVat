package com.app;

import com.app.server.EmbeddedServer;

/**
 * Created by vmusil on 27-Sep-2018.
 */
public class Main {


    public static void main(String[] args) throws Exception {
        new Main().startServer();
    }

    private void startServer() throws Exception {
        EmbeddedServer.startJetty();
    }
}
