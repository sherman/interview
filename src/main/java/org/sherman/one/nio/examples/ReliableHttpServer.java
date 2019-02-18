package org.sherman.one.nio.examples;

import one.nio.config.ConfigParser;
import one.nio.http.*;
import one.nio.net.Session;
import one.nio.net.Socket;
import one.nio.server.RejectedSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author Denis M. Gabaydulin
 * @since 17.02.19
 */
public class ReliableHttpServer extends HttpServer {
    private static final Logger log = LoggerFactory.getLogger(ReliableHttpServer.class);

    private final HttpServerConfig config;

    public ReliableHttpServer(HttpServerConfig config) throws IOException {
        super(config);
        this.config  = config;
    }

    public static void main(String[] args) throws IOException {
        String config = readFile(getConfigFile(args[0]));

        HttpServerConfig serverConfig = ConfigParser.parse(config, HttpServerConfig.class);
        ReliableHttpServer server = new ReliableHttpServer(serverConfig);
        server.start();
    }

    /**
     * Reject a new request in case of all workers are busy.
     * @return
     */
    @Path("/simple")
    public Response simple() {
        try {
            asyncExecute(
                () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            );
        } catch (RejectedExecutionException e) {
            return new Response(Response.SERVICE_UNAVAILABLE);
        }

        return Response.ok("Response");
    }

    @Override
    public HttpSession createSession(Socket socket) throws RejectedSessionException {
        if (getWorkersActive() >= this.config.maxWorkers) {
            socket.close();
            throw new RejectedSessionException("Too many worker!");
        } else {
            return new HttpSession(socket, this);
        }
    }

    private static File getConfigFile(String classpathName) {
        URL config = HttpServerExample.class.getClassLoader().getResource(classpathName);
        return new File(config.getFile());
    }

    private static String readFile(File configFile) throws IOException {
        return new String(Files.readAllBytes(Paths.get(configFile.toURI())), StandardCharsets.UTF_8);
    }
}
