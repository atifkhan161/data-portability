/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dataportabilityproject.gateway.reference;

import static com.google.common.net.HttpHeaders.CONTENT_TYPE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.dataportabilityproject.gateway.action.DataTransferAction;
import org.dataportabilityproject.gateway.reference.ReferenceApiUtils.HttpMethods;
import org.dataportabilityproject.types.client.transfer.DataTransferRequest;
import org.dataportabilityproject.types.client.transfer.DataTransferResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpHandler for the {@link DataTransferAction}.
 */
final class DataTransferHandler implements HttpHandler {

  private static final Logger logger = LoggerFactory.getLogger(DataTransferHandler.class);
  public static final String PATH = "/_/DataTransfer";
  private final DataTransferAction action;
  private final ObjectMapper objectMapper;

  @Inject
  DataTransferHandler(DataTransferAction action) {
    this.action = action;
    this.objectMapper = new ObjectMapper();
  }

  /**
   * Services the {@link DataTransferAction} via the {@link HttpExchange}.
   */
  public void handle(HttpExchange exchange) throws IOException {
    Preconditions.checkArgument(
        ReferenceApiUtils.validateRequest(exchange, HttpMethods.POST, PATH),
        PATH + " only supports POST.");
    logger.debug("received request: {}", exchange.getRequestURI());
    String redirect = "/error";
    DataTransferRequest request = objectMapper
        .readValue(exchange.getRequestBody(), DataTransferRequest.class);
    DataTransferResponse dataTransferResponse = action.handle(request);
    logger.debug("redirecting to: {}", dataTransferResponse.getNextUrl());

    // Mark the response as type Json and send
    exchange.getResponseHeaders()
        .set(CONTENT_TYPE, "application/json; charset=" + StandardCharsets.UTF_8.name());
    exchange.sendResponseHeaders(200, 0);
    objectMapper.writeValue(exchange.getResponseBody(), dataTransferResponse);
  }
}
