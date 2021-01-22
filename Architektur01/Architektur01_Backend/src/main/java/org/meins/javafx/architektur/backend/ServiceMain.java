/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.meins.javafx.architektur.backend;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * @author robert
 */
public class ServiceMain {

  public static void main(String[] args) throws IOException {
    System.out.println("Starting Service.");
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    server.createContext("/applications/myapp", new Handler());
    server.setExecutor(null); 
    server.start();
    System.out.println("Bye.");
  }
}
