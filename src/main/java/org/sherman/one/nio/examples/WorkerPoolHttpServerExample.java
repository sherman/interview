package org.sherman.one.nio.examples;

import one.nio.config.ConfigParser;
import one.nio.http.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerPoolHttpServerExample extends HttpServer {
    private final ExecutorService executorService = Executors.newFixedThreadPool(256);

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
        executorService.submit(
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
