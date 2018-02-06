/*
* Copyright 2018 The Data-Portability Project Authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* https://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.dataportabilityproject.gateway.reference;

import com.google.inject.Inject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;
import javax.inject.Named;
import javax.inject.Singleton;
import org.dataportabilityproject.gateway.Launcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contains utility functions for use by the PortabilityServer HttpHandlers
 */
public final class ReferenceApiUtils {

  /** Enumeration of http methods supported in the API reference implementation. */
  public enum HttpMethods {
    GET,
    POST
  }




  /**
   * Returns whether or not the exchange is a valid request for the provided http method and
   * resource. If not, it will close the client connection.
   */
  public static boolean validateRequest(HttpExchange exchange, HttpMethods supportedMethod,
      String resourceRegex) throws IOException {
    String path = exchange.getRequestURI().getPath();
    if (!exchange.getRequestMethod().equalsIgnoreCase(supportedMethod.name())) {
      exchange.sendResponseHeaders(404, 0);
      OutputStream writer = exchange.getResponseBody();
      writer.write("Method not supported\n".getBytes());
      writer.close();
      return false;
    } else if (!Pattern.compile(resourceRegex).matcher(path).matches()) {
      exchange.sendResponseHeaders(404, 0);
      OutputStream writer = exchange.getResponseBody();
      writer.write("Not found\n".getBytes());
      writer.close();
      return false;
    }
    return true;
  }
}
