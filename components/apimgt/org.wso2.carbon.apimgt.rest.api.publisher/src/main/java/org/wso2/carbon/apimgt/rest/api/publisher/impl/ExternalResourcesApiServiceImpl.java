package org.wso2.carbon.apimgt.rest.api.publisher.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.apimgt.core.impl.ServiceDiscoverer;
import org.wso2.carbon.apimgt.core.configuration.models.ServiceDiscoveryConfigurations;
import org.wso2.carbon.apimgt.core.configuration.models.ServiceDiscoveryImplConfig;
import org.wso2.carbon.apimgt.core.exception.ServiceDiscoveryException;
import org.wso2.carbon.apimgt.core.impl.ServiceDiscoveryConfigBuilder;
import org.wso2.carbon.apimgt.core.models.Endpoint;
import org.wso2.carbon.apimgt.rest.api.common.dto.ErrorDTO;
import org.wso2.carbon.apimgt.rest.api.common.util.RestApiUtil;
import org.wso2.carbon.apimgt.rest.api.publisher.*;
import org.wso2.carbon.apimgt.rest.api.publisher.dto.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.wso2.carbon.apimgt.rest.api.publisher.NotFoundException;

import org.wso2.carbon.apimgt.rest.api.publisher.utils.MappingUtil;
import org.wso2.msf4j.Request;
import javax.ws.rs.core.Response;

public class ExternalResourcesApiServiceImpl extends ExternalResourcesApiService {
    private static final Logger log = LoggerFactory.getLogger(ExternalResourcesApiServiceImpl.class);

    /**
     * Retrieve all service endpoints via service discovery
     *
     * @param ifNoneMatch     If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header
     * @param request         msf4j request object
     * @return A list of service endpoints available in the cluster
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response externalResourcesServicesGet(String ifNoneMatch, String ifModifiedSince,
                                                 Request request) throws NotFoundException {
        try{
            EndPointListDTO endPointListDTO = new EndPointListDTO();
            ServiceDiscoveryConfigurations serviceDiscoveryConfig = ServiceDiscoveryConfigBuilder
                    .getServiceDiscoveryConfiguration();
            if (!serviceDiscoveryConfig.isServiceDiscoveryEnabled()) {
                String errorMessage = "Service discovery is not enabled";
                return handleInternalServerError(errorMessage, null);
            }
            List<ServiceDiscoveryImplConfig> implConfigsList = serviceDiscoveryConfig.getImplementationsList();
            for (ServiceDiscoveryImplConfig implConfig : implConfigsList) {
                String implClassName = implConfig.getImplClass();
                Class implClazz = ExternalResourcesApiServiceImpl.class.getClassLoader().loadClass(implClassName);
                ServiceDiscoverer serviceDiscoverer = (ServiceDiscoverer) implClazz.newInstance();
                serviceDiscoverer.init(implConfig.getImplParameters());

                String namespaceFilter = serviceDiscoverer.getNamespaceFilter();
                HashMap<String, String> criteriaFilter = serviceDiscoverer.getCriteriaFilter();
                List<Endpoint> discoveredEndpointList;
                if (namespaceFilter == null && criteriaFilter == null) {
                    discoveredEndpointList = serviceDiscoverer.listServices();
                } else if (namespaceFilter != null && criteriaFilter != null) {
                    discoveredEndpointList = serviceDiscoverer.listServices(namespaceFilter, criteriaFilter);
                } else if (namespaceFilter != null) {
                    discoveredEndpointList = serviceDiscoverer.listServices(namespaceFilter);
                } else {
                    discoveredEndpointList = serviceDiscoverer.listServices(criteriaFilter);
                }
                for (Endpoint endpoint : discoveredEndpointList) {
                    endPointListDTO.addListItem(MappingUtil.toEndPointDTO(endpoint));
                }
            }
            endPointListDTO.setCount(endPointListDTO.getList().size());
            return Response.ok().entity(endPointListDTO).build();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            String errorMessage = "Error while loading service discovery impl class";
            return handleInternalServerError(errorMessage, e);
        } catch (IOException e) {
            String errorMessage = "Error while Converting Endpoint Security Details in Endpoint";
            return handleInternalServerError(errorMessage, e);
        } catch (ServiceDiscoveryException e) {
            String errorMessage = "Error while Discovering Service Endpoints";
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler());
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    private Response handleInternalServerError(String errorMessage, Exception e){
        ErrorDTO errorDTO = RestApiUtil.getErrorDTO(errorMessage, 900313L, errorMessage);
        if ( e == null ) {
            log.error(errorMessage);
        } else {
            log.error(errorMessage, e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDTO).build();
    }
}