package com.ashutosh;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(
                new InetSocketAddress(8081), 0);

        // Home Page
        server.createContext("/", exchange -> {

            if (!exchange.getRequestMethod().equals("GET")) {
                sendResponse(exchange, 405, "Method Not Allowed");
                return;
            }

            InputStream input = App.class.getResourceAsStream("/index.html");

            if (input == null) {
                sendResponse(exchange, 404, "HTML file not found");
                return;
            }

            byte[] html = input.readAllBytes();
            input.close();

            exchange.getResponseHeaders().set(
                    "Content-Type", "text/html; charset=UTF-8");

            exchange.sendResponseHeaders(200, html.length);

            try (OutputStream output = exchange.getResponseBody()) {
                output.write(html);
            }
        });

        // Registration Endpoint
        server.createContext("/register", exchange -> {

            if (!exchange.getRequestMethod().equals("POST")) {
                sendResponse(exchange, 405, "Method Not Allowed");
                return;
            }

            String body = new String(
                    exchange.getRequestBody().readAllBytes(),
                    StandardCharsets.UTF_8);

            Map<String, String> data = new HashMap<>();

            for (String pair : body.split("&")) {

                String[] parts = pair.split("=", 2);

                String key = URLDecoder.decode(
                        parts[0], StandardCharsets.UTF_8);

                String value = parts.length > 1
                        ? URLDecoder.decode(parts[1], StandardCharsets.UTF_8)
                        : "";

                data.put(key, value);
            }

            String name = data.getOrDefault("studentName", "").trim();
            String roll = data.getOrDefault("rollNumber", "").trim();
            String email = data.getOrDefault("email", "").trim();
            String course = data.getOrDefault("course", "").trim();

            if (name.isEmpty() || roll.isEmpty()
                    || email.isEmpty() || course.isEmpty()) {

                sendResponse(exchange, 400, "All fields are required.");
                return;
            }

            System.out.println("\n========== STUDENT REGISTRATION ==========");
            System.out.println("Student Name: " + name);
            System.out.println("Roll Number: " + roll);
            System.out.println("Email: " + email);
            System.out.println("Course: " + course);
            System.out.println("==========================================");

            String response = """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <title>Registration Successful</title>
                        <style>
                            body {
                                font-family: Arial;
                                text-align: center;
                                background: #243b55;
                                color: white;
                                padding-top: 100px;
                            }
                            .box {
                                background: white;
                                color: #243b55;
                                padding: 40px;
                                margin: auto;
                                width: 350px;
                                border-radius: 15px;
                            }
                        </style>
                    </head>
                    <body>
                        <div class="box">
                            <h1>Registration Successful!</h1>
                            <p>Your registration has been received.</p>
                            <a href="/">Register Another Student</a>
                        </div>
                    </body>
                    </html>
                    """;

            sendResponse(exchange, 200, response);
        });

        // Health Check Endpoint
        server.createContext("/health", exchange -> {
            sendResponse(exchange, 200,
                    "Application is running successfully.");
        });

        server.setExecutor(null);
        server.start();

        System.out.println("========================================");
        System.out.println("STUDENT REGISTRATION APPLICATION");
        System.out.println("Server running at: http://localhost:8081");
        System.out.println("Application Status: RUNNING");
        System.out.println("========================================");
    }

    // Common Response Method
    private static void sendResponse(
            HttpExchange exchange, int status, String response)
            throws IOException {

        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set(
                "Content-Type", "text/html; charset=UTF-8");

        exchange.sendResponseHeaders(status, bytes.length);

        try (OutputStream output = exchange.getResponseBody()) {
            output.write(bytes);
        }
    }
}