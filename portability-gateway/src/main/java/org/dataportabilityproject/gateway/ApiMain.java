package org.dataportabilityproject.gateway;

import org.dataportabilityproject.gateway.PortabilityApiFlags;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.sun.net.httpserver.HttpHandler;
import java.lang.Thread.UncaughtExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Starts the reference api server.
 */
public class ApiMain {

  private static final Logger logger = LoggerFactory.getLogger(ApiMain.class);

  /**
   * Starts the reference api server.
   */
  public static void main(String[] args) {
    logger.warn("Starting reference api server.");
    Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread thread, Throwable t) {
        logger.warn("Uncaught exception in thread: {}", thread.getName(), t);
      }
    });
    PortabilityApiFlags.parse();
    throw new UnsupportedOperationException("Implement me!");
  }

  static class Module extends AbstractModule {
    @Override
    protected void configure() {
      MapBinder<String, HttpHandler> mapbinder
          = MapBinder.newMapBinder(binder(), String.class, HttpHandler.class);

      // HttpServer does exact longest matching prefix for context matching. This means
      // /_/listServices, /_/listServicesthisshouldnotwork and /_/listServices/path/to/resource will
      // all be handled by the ListServicesHandler below. To prevent this, each handler below should
      // validate the request URI that it is getting passed in.

      /*
      mapbinder.addBinding(CopySetupHandler.PATH).to(CopySetupHandler.class);
      mapbinder.addBinding(DataTransferHandler.PATH).to(DataTransferHandler.class);
      mapbinder.addBinding(ImportSetupHandler.PATH).to(ImportSetupHandler.class);
      mapbinder.addBinding(ListDataTypesHandler.PATH).to(ListDataTypesHandler.class);
      mapbinder.addBinding(ListServicesHandler.PATH).to(ListServicesHandler.class);
      mapbinder.addBinding(SimpleLoginSubmitHandler.PATH).to(SimpleLoginSubmitHandler.class);
      mapbinder.addBinding(StartCopyHandler.PATH).to(StartCopyHandler.class);
      mapbinder.addBinding(OauthCallbackHandler.PATH).to(OauthCallbackHandler.class);
      mapbinder.addBinding(Oauth2CallbackHandler.PATH).to(Oauth2CallbackHandler.class);
      */
    }
  }
}
