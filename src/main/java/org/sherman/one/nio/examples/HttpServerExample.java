package org.sherman.one.nio.examples;

import one.nio.config.ConfigParser;
import one.nio.http.HttpServer;
import one.nio.http.HttpServerConfig;
import one.nio.http.Path;
import one.nio.http.Response;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HttpServerExample extends HttpServer {
    public HttpServerExample(HttpServerConfig config) throws IOException {
        super(config);
    }

    public static void main(String[] args) throws IOException {
        String config = readFile(getConfigFile(args[0]));

        HttpServerConfig serverConfig = ConfigParser.parse(config, HttpServerConfig.class);
        HttpServerExample server = new HttpServerExample(serverConfig);
        server.start();
    }

    @Path("/simple")
    public Response simple() {
        return Response.ok("Response");
    }

    @Path("/blockedWorker")
    public Response blockedWorker() throws InterruptedException {
        asyncExecute(
            () -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        );

        return Response.ok("Response");
    }

    @Path("/blocked")
    public Response blocked() throws InterruptedException {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Response.ok("Response");
    }

    private static File getConfigFile(String classpathName) {
        URL config = HttpServerExample.class.getClassLoader().getResource(classpathName);
        return new File(config.getFile());
    }

    private static String readFile(File configFile) throws IOException {
        return new String(Files.readAllBytes(Paths.get(configFile.toURI())), StandardCharsets.UTF_8);
    }
}
