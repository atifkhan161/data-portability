package org.dataportabilityproject.transfer;

import java.util.List;
import java.util.Map;
import org.dataportabilityproject.spi.transfer.provider.Exporter;
import org.dataportabilityproject.spi.transfer.provider.Importer;
import org.dataportabilityproject.spi.transfer.provider.TransferServiceProvider;
import org.dataportabilityproject.spi.transfer.provider.TransferServiceProviderRegistry;
import org.dataportabilityproject.types.transfer.PortableType;
import org.dataportabilityproject.types.transfer.auth.AuthData;
import org.dataportabilityproject.types.transfer.models.DataModel;

public class PortabilityTransferServiceProviderRegistry implements TransferServiceProviderRegistry {

  public PortabilityTransferServiceProviderRegistry(List<String> enabledServices,
      Map<String, TransferServiceProvider> serviceProviderMap){
  }

  /**
   * Returns the exporter that supports the serviceId and transferDataType.
   *
   * @param serviceId the service id
   * @param transferDataType the transfer data type
   */
  @Override
  public Exporter<AuthData, DataModel> getExporter(String serviceId,
      PortableType transferDataType) {
    return null;
  }

  /**
   * Returns the exporter that supports the serviceId and transferDataType.
   *
   * @param serviceId the service id
   * @param transferDataType the transfer data type
   */
  @Override
  public Importer<AuthData, DataModel> getImporter(String serviceId,
      PortableType transferDataType) {
    return null;
  }
}
