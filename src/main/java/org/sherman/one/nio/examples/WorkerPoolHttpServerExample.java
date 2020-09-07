package org.sherman.one.nio.examples;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import one.nio.config.ConfigParser;
import one.nio.http.HttpServer;
import one.nio.http.HttpServerConfig;
import one.nio.http.HttpSession;
import one.nio.http.Request;
import one.nio.http.Response;

public class WorkerPoolHttpServerExample extends HttpServer {
    public WorkerPoolHttpServerExample(HttpServerConfig config) throws IOException {
        super(config);
    }

    public static void main(String[] args) throws IOException {
        String config = readFile(getConfigFile(args[0]));

        HttpServerConfig serverConfig = ConfigParser.parse(config, HttpServerConfig.class);
        WorkerPoolHttpServerExample server = new WorkerPoolHttpServerExample(serverConfig);
        server.start();
    }

    @Override
    public void handleRequest(Request request, HttpSession session) {
        asyncExecute(
                () -> {
                    try {
                        session.sendResponse(Response.ok("Simple"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    private static File getConfigFile(String classpathName) {
        URL config = WorkerPoolHttpServerExample.class.getClassLoader().getResource(classpathName);
        return new File(config.getFile());
    }

    private static String readFile(File configFile) throws IOException {
        return new String(Files.readAllBytes(Paths.get(configFile.toURI())), StandardCharsets.UTF_8);
    }
}
