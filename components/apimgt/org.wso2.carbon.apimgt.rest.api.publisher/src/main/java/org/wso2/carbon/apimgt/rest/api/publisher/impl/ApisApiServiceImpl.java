package org.wso2.carbon.apimgt.rest.api.publisher.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
<<<<<<< HEAD
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.json.JSONException;
import org.json.XML;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.wso2.carbon.apimgt.api.APIDefinition;
import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.api.APIProvider;
import org.wso2.carbon.apimgt.api.FaultGatewaysException;
import org.wso2.carbon.apimgt.api.model.API;
import org.wso2.carbon.apimgt.api.model.APIIdentifier;
import org.wso2.carbon.apimgt.api.model.APIStatus;
import org.wso2.carbon.apimgt.api.model.Documentation;
import org.wso2.carbon.apimgt.api.model.DuplicateAPIException;
import org.wso2.carbon.apimgt.api.model.KeyManager;
import org.wso2.carbon.apimgt.api.model.Mediation;
import org.wso2.carbon.apimgt.api.model.ResourceFile;
import org.wso2.carbon.apimgt.api.model.Scope;
import org.wso2.carbon.apimgt.api.model.SubscribedAPI;
import org.wso2.carbon.apimgt.api.model.Tier;
import org.wso2.carbon.apimgt.api.model.URITemplate;
import org.wso2.carbon.apimgt.api.model.policy.PolicyConstants;
import org.wso2.carbon.apimgt.impl.APIConstants;
import org.wso2.carbon.apimgt.impl.definitions.APIDefinitionFromSwagger20;
import org.wso2.carbon.apimgt.impl.factory.KeyManagerHolder;
import org.wso2.carbon.apimgt.impl.utils.APIUtil;
import org.wso2.carbon.apimgt.rest.api.publisher.ApisApiService;
import org.wso2.carbon.apimgt.rest.api.publisher.dto.*;
import org.wso2.carbon.apimgt.rest.api.publisher.utils.RestApiPublisherUtils;
import org.wso2.carbon.apimgt.rest.api.publisher.utils.mappings.APIMappingUtil;
import org.wso2.carbon.apimgt.rest.api.publisher.utils.mappings.DocumentationMappingUtil;
import org.wso2.carbon.apimgt.rest.api.publisher.utils.mappings.MediationMappingUtil;
import org.wso2.carbon.apimgt.rest.api.util.RestApiConstants;
import org.wso2.carbon.apimgt.rest.api.util.utils.RestApiUtil;
import org.wso2.carbon.registry.api.RegistryException;
import org.wso2.carbon.registry.api.Resource;
import org.wso2.carbon.registry.core.RegistryConstants;

=======
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.apimgt.core.api.APIPublisher;
import org.wso2.carbon.apimgt.core.api.WSDLProcessor;
import org.wso2.carbon.apimgt.core.api.WorkflowResponse;
import org.wso2.carbon.apimgt.core.exception.APIManagementException;
import org.wso2.carbon.apimgt.core.exception.APIMgtResourceNotFoundException;
import org.wso2.carbon.apimgt.core.exception.ErrorHandler;
import org.wso2.carbon.apimgt.core.exception.ExceptionCodes;
import org.wso2.carbon.apimgt.core.impl.WSDLProcessFactory;
import org.wso2.carbon.apimgt.core.models.API;
import org.wso2.carbon.apimgt.core.models.DocumentContent;
import org.wso2.carbon.apimgt.core.models.DocumentInfo;
import org.wso2.carbon.apimgt.core.models.WSDLArchiveInfo;
import org.wso2.carbon.apimgt.core.models.WSDLInfo;
import org.wso2.carbon.apimgt.core.models.WorkflowStatus;
import org.wso2.carbon.apimgt.core.util.APIMgtConstants;
import org.wso2.carbon.apimgt.core.util.ETagUtils;
import org.wso2.carbon.apimgt.core.workflow.GeneralWorkflowResponse;
import org.wso2.carbon.apimgt.rest.api.common.RestApiConstants;
import org.wso2.carbon.apimgt.rest.api.common.dto.ErrorDTO;
import org.wso2.carbon.apimgt.rest.api.common.util.RestApiUtil;
import org.wso2.carbon.apimgt.rest.api.publisher.ApisApiService;
import org.wso2.carbon.apimgt.rest.api.publisher.NotFoundException;
import org.wso2.carbon.apimgt.rest.api.publisher.dto.APIDTO;
import org.wso2.carbon.apimgt.rest.api.publisher.dto.APIDefinitionValidationResponseDTO;
import org.wso2.carbon.apimgt.rest.api.publisher.dto.APIListDTO;
import org.wso2.carbon.apimgt.rest.api.publisher.dto.DocumentDTO;
import org.wso2.carbon.apimgt.rest.api.publisher.dto.DocumentListDTO;
import org.wso2.carbon.apimgt.rest.api.publisher.dto.FileInfoDTO;
import org.wso2.carbon.apimgt.rest.api.publisher.dto.WorkflowResponseDTO;
import org.wso2.carbon.apimgt.rest.api.publisher.utils.MappingUtil;
import org.wso2.carbon.apimgt.rest.api.publisher.utils.RestAPIPublisherUtil;
import org.wso2.carbon.lcm.core.impl.LifecycleState;
import org.wso2.msf4j.Request;
import org.wso2.msf4j.formparam.FileInfo;

import javax.ws.rs.core.HttpHeaders;
>>>>>>> upstream/master
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

@javax.annotation.Generated(value = "class org.wso2.maven.plugins.JavaMSF4JServerCodegen", date =
        "2016-11-01T13:47:43.416+05:30")
public class ApisApiServiceImpl extends ApisApiService {
    private static final Logger log = LoggerFactory.getLogger(ApisApiServiceImpl.class);

    /**
     * Deletes a particular API
     *
     * @param apiId             UUID of API
     * @param ifMatch           If-Match header value
     * @param ifUnmodifiedSince If-Unmodified-Since header value
     * @param request           msf4j request object
     * @return 200 OK if the opration was successful
     * @throws NotFoundException when the particular resource does not exist
     */
    @Override
    public Response apisApiIdDelete(String apiId, String ifMatch, String ifUnmodifiedSince, Request request)
            throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            String existingFingerprint = apisApiIdGetFingerprint(apiId, null, null, request);
            if (!StringUtils.isEmpty(ifMatch) && !StringUtils.isEmpty(existingFingerprint) && !ifMatch
                    .contains(existingFingerprint)) {
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
            }

            apiPublisher.deleteAPI(apiId);
            return Response.ok().build();
        } catch (APIManagementException e) {
            String errorMessage = "Error while deleting  API : " + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    /**
     * Retrieves the content of a particular document
     *
     * @param apiId           UUID of API
     * @param documentId      UUID of the document
     * @param ifNoneMatch     If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header value
     * @param request         msf4j request object
     * @return Content of the document
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisApiIdDocumentsDocumentIdContentGet(String apiId, String documentId,
            String ifNoneMatch, String ifModifiedSince, Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
<<<<<<< HEAD
            APIProvider apiProvider = RestApiUtil.getLoggedInUserProvider();
            String username = RestApiUtil.getLoggedInUsername();
            boolean isWSAPI = APIDTO.TypeEnum.WS == body.getType();

            // validate web socket api endpoint configurations
            if (isWSAPI) {
                if (!RestApiPublisherUtils.isValidWSAPI(body)) {
                    RestApiUtil.handleBadRequest("Endpoint URLs should be valid web socket URLs", log);
                }
            } else {
                if (body.getApiDefinition() == null) {
                    RestApiUtil.handleBadRequest("Parameter: \"apiDefinition\" cannot be null", log);
                }
            }

            if (body.getContext().endsWith("/")) {
                RestApiUtil.handleBadRequest("Context cannot end with '/' character", log);
            }

            //Get all existing versions of  api been adding
            List<String> apiVersions = apiProvider.getApiVersionsMatchingApiName(body.getName(), username);
            if (apiVersions.size() > 0) {
                //If any previous version exists
                for (String version : apiVersions) {
                    if (version.equalsIgnoreCase(body.getVersion())) {
                        //If version already exists
                        if (apiProvider.isDuplicateContextTemplate(body.getContext())) {
                            RestApiUtil.handleResourceAlreadyExistsError("Error occurred while " +
                                    "adding the API. A duplicate API already exists for "
                                    + body.getName() + "-" + body.getVersion(), log);
                        } else {
                            RestApiUtil.handleBadRequest("Error occurred while adding API. API with name " +
                                    body.getName() + " already exists with different " +
                                    "context", log);
                        }
                    }
                }
            } else {
                //If no any previous version exists
                if (apiProvider.isDuplicateContextTemplate(body.getContext())) {
                    RestApiUtil.handleBadRequest("Error occurred while adding the API. A duplicate API context " +
                                    "already exists for " + body.getContext(), log);
                }
=======
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            String existingFingerprint = apisApiIdDocumentsDocumentIdContentGetFingerprint(apiId, documentId,
                    ifNoneMatch, ifModifiedSince, request);
            if (!StringUtils.isEmpty(ifNoneMatch) && !StringUtils.isEmpty(existingFingerprint) && ifNoneMatch
                    .contains(existingFingerprint)) {
                return Response.notModified().build();
>>>>>>> upstream/master
            }

            DocumentContent documentationContent = apiPublisher.getDocumentationContent(documentId);
            DocumentInfo documentInfo = documentationContent.getDocumentInfo();
            if (DocumentInfo.SourceType.FILE.equals(documentInfo.getSourceType())) {
                String filename = documentInfo.getFileName();
                return Response.ok(documentationContent.getFileContent())
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_TYPE)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                        .header(HttpHeaders.ETAG, "\"" + existingFingerprint + "\"")
                        .build();
            } else if (DocumentInfo.SourceType.INLINE.equals(documentInfo.getSourceType())) {
                String content = documentationContent.getInlineContent();
                return Response.ok(content)
                        .header(RestApiConstants.HEADER_CONTENT_TYPE, MediaType.TEXT_PLAIN)
                        .header(HttpHeaders.ETAG, "\"" + existingFingerprint + "\"")
                        .build();
            } else if (DocumentInfo.SourceType.URL.equals(documentInfo.getSourceType())) {
                String sourceUrl = documentInfo.getSourceURL();
                return Response.seeOther(new URI(sourceUrl))
                        .header(HttpHeaders.ETAG, "\"" + existingFingerprint + "\"")
                        .build();
            }
        } catch (APIManagementException e) {
            String errorMessage = "Error while retrieving document " + documentId + " of the API " + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        } catch (URISyntaxException e) {
            String errorMessage = "Error while retrieving source URI location of " + documentId;
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(errorMessage, 900313L, errorMessage);
            log.error(errorMessage, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDTO).build();
        }
        return null;
    }

    /**
     * Retrives the fingerprint of a particular document content
     *
     * @param apiId           UUID of API
     * @param documentId      UUID of the document
     * @param ifNoneMatch     If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header value
     * @param request         msf4j request object
     * @return fingerprint of a particular document content
     */
    public String apisApiIdDocumentsDocumentIdContentGetFingerprint(String apiId, String documentId, String ifNoneMatch,
            String ifModifiedSince, Request request) {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            String lastUpdatedTime = RestAPIPublisherUtil.getApiPublisher(username)
                    .getLastUpdatedTimeOfDocumentContent(apiId, documentId);
            return ETagUtils.generateETag(lastUpdatedTime);
        } catch (APIManagementException e) {
            //gives a warning and let it continue the execution
            String errorMessage =
                    "Error while retrieving last updated time of content of document " + documentId + " of API "
                            + apiId;
            log.error(errorMessage, e);
            return null;
        }
    }

    /**
     * Uploads a document's content and attach to particular document
     *
     * @param apiId             UUID of API
     * @param documentId        UUID of the document
     * @param fileInputStream   file content stream
     * @param fileDetail        meta infomation about the file
     * @param inlineContent     inline documentation content
     * @param ifMatch           If-Match header value
     * @param ifUnmodifiedSince If-Unmodified-Since header value
     * @param request           msf4j request object
     * @return updated document meta information
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisApiIdDocumentsDocumentIdContentPost(String apiId, String documentId,
            InputStream fileInputStream, FileInfo fileDetail, String inlineContent, String ifMatch,
            String ifUnmodifiedSince, Request request) throws NotFoundException {
        try {
            String username = RestApiUtil.getLoggedInUsername(request);
            APIPublisher apiProvider = RestAPIPublisherUtil.getApiPublisher(username);
            String existingFingerprint = apisApiIdDocumentsDocumentIdContentGetFingerprint(apiId, documentId,
                    null, null, request);
            if (!StringUtils.isEmpty(ifMatch) && !StringUtils.isEmpty(existingFingerprint) && !ifMatch
                    .contains(existingFingerprint)) {
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
            }

            if (fileInputStream != null && inlineContent != null) {
                String msg = "Only one of 'file' and 'inlineContent' should be specified";
                log.error(msg);
                ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900314L, msg);
                log.error(msg);
                return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();
            }

            //retrieves the document and send 404 if not found
            DocumentInfo documentation = apiProvider.getDocumentationSummary(documentId);
            if (documentation == null) {
                String msg = "Documentation not found " + documentId;
                log.error(msg);
                ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900314L, msg);
                log.error(msg);
                return Response.status(Response.Status.NOT_FOUND).entity(errorDTO).build();
            }
            //add content depending on the availability of either input stream or inline content
            if (fileInputStream != null) {
                if (!documentation.getSourceType().equals(DocumentInfo.SourceType.FILE)) {
                    String msg = "Source type of document " + documentId + " is not FILE";
                    log.error(msg);
                    ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900314L, msg);
                    log.error(msg);
                    return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();
                }
                apiProvider.uploadDocumentationFile(documentId, fileInputStream, fileDetail.getContentType());
            } else if (inlineContent != null) {
                if (!documentation.getSourceType().equals(DocumentInfo.SourceType.INLINE)) {
                    String msg = "Source type of document " + documentId + " is not INLINE";
                    log.error(msg);
                    ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900976L, msg);
                    return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();
                }
                apiProvider.addDocumentationContent(documentId, inlineContent);
            } else {
                String msg = "Either 'file' or 'inlineContent' should be specified";
                log.error(msg);
                ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900976L, msg);
                return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();
            }
            String newFingerprint = apisApiIdDocumentsDocumentIdContentGetFingerprint(apiId, documentId,
                    null, null, request);
            return Response.status(Response.Status.CREATED)
                    .header(HttpHeaders.ETAG, "\"" + newFingerprint + "\"")
                    .build();
        } catch (APIManagementException e) {
            String errorMessage = "Error while adding content to document" + documentId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            paramList.put(APIMgtConstants.ExceptionsConstants.DOC_ID, documentId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    /**
     * Delete an API's document
     *
     * @param apiId             UUID of API
     * @param documentId        UUID of the document
     * @param ifMatch           If-Match header value
     * @param ifUnmodifiedSince If-Unmodified-Since header value
     * @param request           msf4j request object
     * @return 200 OK response if the deletion was successful
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisApiIdDocumentsDocumentIdDelete(String apiId, String documentId, String ifMatch,
            String ifUnmodifiedSince, Request request) throws
            NotFoundException {
        try {
            String username = RestApiUtil.getLoggedInUsername(request);
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            String existingFingerprint = apisApiIdDocumentsDocumentIdGetFingerprint(apiId, documentId, null, null,
                    request);
            if (!StringUtils.isEmpty(ifMatch) && !StringUtils.isEmpty(existingFingerprint) && !ifMatch
                    .contains(existingFingerprint)) {
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
            }
            apiPublisher.removeDocumentation(documentId);
            return Response.ok().build();
        } catch (APIManagementException e) {
            String errorMessage = "Error while deleting document" + documentId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            paramList.put(APIMgtConstants.ExceptionsConstants.DOC_ID, documentId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    /**
     * Retrives the document identified by the API's ID and the document's ID
     *
     * @param apiId           UUID of API
     * @param documentId      UUID of the document
     * @param ifNoneMatch     If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header value
     * @param request         msf4j request object
     * @return the document qualifying for the provided IDs
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisApiIdDocumentsDocumentIdGet(String apiId, String documentId, String ifNoneMatch,
            String ifModifiedSince, Request request) throws NotFoundException {
        try {
            String username = RestApiUtil.getLoggedInUsername(request);
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);

            String existingFingerprint = apisApiIdDocumentsDocumentIdGetFingerprint(apiId, documentId,
                    ifNoneMatch, ifModifiedSince, request);
            if (!StringUtils.isEmpty(ifNoneMatch) && !StringUtils.isEmpty(existingFingerprint) && ifNoneMatch
                    .contains(existingFingerprint)) {
                return Response.notModified().build();
            }

            DocumentInfo documentInfo = apiPublisher.getDocumentationSummary(documentId);
            if (documentInfo != null) {
                return Response.ok().header(HttpHeaders.ETAG, "\"" + existingFingerprint + "\"")
                        .entity(MappingUtil.toDocumentDTO(documentInfo)).build();
            } else {
                String msg = "Documntation not found " + documentId;
                log.error(msg);
                ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900314L, msg);
                log.error(msg);
                return Response.status(Response.Status.NOT_FOUND).entity(errorDTO).build();
            }
        } catch (APIManagementException e) {
            String errorMessage = "Error while getting document" + documentId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            paramList.put(APIMgtConstants.ExceptionsConstants.DOC_ID, documentId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    /**
     * Retrieves the fingerprint of a document
     *
     * @param apiId           UUID of API
     * @param documentId      UUID of the document
     * @param ifNoneMatch     If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header value
     * @param request         msf4j request object
     * @return fingerprint of the document
     */
<<<<<<< HEAD
    @Override
    public Response apisApiIdPoliciesMediationGet(String apiId, Integer limit, Integer offset,
                                                  String query, String accept, String ifNoneMatch) {
        //pre-processing
        //setting default limit and offset values if they are not set
        limit = limit != null ? limit : RestApiConstants.PAGINATION_LIMIT_DEFAULT;
        offset = offset != null ? offset : RestApiConstants.PAGINATION_OFFSET_DEFAULT;
        APIIdentifier apiIdentifier;
=======
    public String apisApiIdDocumentsDocumentIdGetFingerprint(String apiId, String documentId,
            String ifNoneMatch, String ifModifiedSince, Request request) {
        String username = RestApiUtil.getLoggedInUsername(request);
>>>>>>> upstream/master
        try {
            String lastUpdatedTime = RestAPIPublisherUtil.getApiPublisher(username)
                    .getLastUpdatedTimeOfDocument(documentId);
            return ETagUtils.generateETag(lastUpdatedTime);
        } catch (APIManagementException e) {
<<<<<<< HEAD
            //Auth failure occurs when cross tenant accessing APIs. Sends 404, since we don't need
            // to expose the existence of the resource
            if (RestApiUtil.isDueToResourceNotFound(e) || RestApiUtil.isDueToAuthorizationFailure(e)) {
                RestApiUtil.handleResourceNotFoundError(RestApiConstants.RESOURCE_API, apiId, e, log);
            } else {
                String errorMessage = "Error while retrieving all api specific mediation policies" +
                        " of API : " + apiId;
                RestApiUtil.handleInternalServerError(errorMessage, e, log);
            }
=======
            //gives a warning and let it continue the execution
            String errorMessage =
                    "Error while retrieving last updated time of document " + documentId + " of API " + apiId;
            log.error(errorMessage, e);
            return null;
>>>>>>> upstream/master
        }
    }

    /**
     * Updates an API's document
     *
     * @param apiId             UUID of API
     * @param documentId        UUID of the document
     * @param body              DTO object including the document's meta information
     * @param ifMatch           If-Match header value
     * @param ifUnmodifiedSince If-Unmodified-Since header value
     * @param request           msf4j request object
     * @return updated document meta info DTO as the response
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
<<<<<<< HEAD
    public Response apisApiIdPoliciesMediationMediationPolicyIdDelete(String apiId,
                                                                      String mediationPolicyId,
                                                                      String ifMatch,
                                                                      String ifUnmodifiedSince) {
        APIIdentifier apiIdentifier;
        try {
            String tenantDomain = RestApiUtil.getLoggedInUserTenantDomain();
            apiIdentifier = APIMappingUtil.getAPIIdentifierFromApiIdOrUUID(apiId,
                    tenantDomain);
            APIProvider apiProvider = RestApiUtil.getLoggedInUserProvider();
            String apiResourcePath = APIUtil.getAPIPath(apiIdentifier);
            //Getting the api base path out apiResourcePath
            apiResourcePath = apiResourcePath.substring(0, apiResourcePath.lastIndexOf("/"));
            boolean deletionStatus = apiProvider.deleteApiSpecificMediationPolicy(apiResourcePath,
                    mediationPolicyId);
            if (deletionStatus) {
                return Response.ok().build();
            } else {
                RestApiUtil.handleResourceNotFoundError(RestApiConstants.RESOURCE_POLICY, mediationPolicyId, log);
            }
        } catch (APIManagementException e) {
            //Auth failure occurs when cross tenant accessing APIs. Sends 404, since we don't need
            // to expose the existence of the resource
            if (RestApiUtil.isDueToResourceNotFound(e) || RestApiUtil.isDueToAuthorizationFailure(e)) {
                RestApiUtil.handleResourceNotFoundError(RestApiConstants.RESOURCE_API, apiId, e, log);
            } else {
                String errorMessage = "Error while deleting API specific mediation policy : " +
                        mediationPolicyId + "of API " + apiId;
                RestApiUtil.handleInternalServerError(errorMessage, e, log);
=======
    public Response apisApiIdDocumentsDocumentIdPut(String apiId, String documentId, DocumentDTO body, String ifMatch,
            String ifUnmodifiedSince, Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);

            String existingFingerprint = apisApiIdDocumentsDocumentIdGetFingerprint(apiId, documentId, null, null,
                    request);
            if (!StringUtils.isEmpty(ifMatch) && !StringUtils.isEmpty(existingFingerprint) && !ifMatch
                    .contains(existingFingerprint)) {
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
            }

            DocumentInfo documentInfoOld = apiPublisher.getDocumentationSummary(documentId);
            //validation checks for existence of the document
            if (documentInfoOld == null) {
                String msg = "Error while getting document";
                log.error(msg);
                ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900314L, msg);
                log.error(msg);
                return Response.status(Response.Status.NOT_FOUND).entity(errorDTO).build();
            }
            if (body.getType() == DocumentDTO.TypeEnum.OTHER && StringUtils.isBlank(body.getOtherTypeName())) {
                //check otherTypeName for not null if doc type is OTHER
                String msg = "otherTypeName cannot be empty if type is OTHER.";
                log.error(msg);
                ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900313L, msg);
                log.error(msg);
                return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();
            }
            if (body.getSourceType() == DocumentDTO.SourceTypeEnum.URL &&
                    (StringUtils.isBlank(body.getSourceUrl()) || !RestApiUtil.isURL(body.getSourceUrl()))) {
                //check otherTypeName for not null if doc type is OTHER
                String msg = "Invalid document sourceUrl Format";
                log.error(msg);
                ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900313L, msg);
                log.error(msg);
                return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();
>>>>>>> upstream/master
            }

            //overriding some properties
            body.setName(documentInfoOld.getName());
            body.setDocumentId(documentInfoOld.getId());

            DocumentInfo documentation = MappingUtil.toDocumentInfo(body);
            //this will fail if user does not have access to the API or the API does not exist
            apiPublisher.updateDocumentation(apiId, documentation);

            //retrieve the updated documentation
            DocumentInfo newDocumentation = apiPublisher.getDocumentationSummary(documentId);
            String newFingerprint = apisApiIdDocumentsDocumentIdGetFingerprint(apiId, documentId, null, null,
                    request);
            return Response.ok().header(HttpHeaders.ETAG, "\"" + newFingerprint + "\"")
                    .entity(MappingUtil.toDocumentDTO(newDocumentation)).build();
        } catch (APIManagementException e) {
            String errorMessage = "Error while updating the document " + documentId + " for API : " + apiId;
            log.error(errorMessage, e);
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            paramList.put(APIMgtConstants.ExceptionsConstants.DOC_ID, documentId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    /**
     * Retrieves a list of documents of an API
     *
     * @param apiId       UUID of API
     * @param limit       maximum documents to return
     * @param offset      starting position of the pagination
     * @param ifNoneMatch If-None-Match header value
     * @param request     msf4j request object
     * @return a list of document DTOs
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
<<<<<<< HEAD
    public Response apisApiIdPoliciesMediationMediationPolicyIdGet(String apiId,
                                                                   String mediationPolicyId,
                                                                   String accept, String ifNoneMatch,
                                                                   String ifModifiedSince) {
        APIIdentifier apiIdentifier;
        try {
            String tenantDomain = RestApiUtil.getLoggedInUserTenantDomain();
            apiIdentifier = APIMappingUtil.getAPIIdentifierFromApiIdOrUUID(apiId,
                    tenantDomain);
            APIProvider apiProvider = RestApiUtil.getLoggedInUserProvider();
            String apiResourcePath = APIUtil.getAPIPath(apiIdentifier);
            //Getting the api base path out of apiResourcePath
            apiResourcePath = apiResourcePath.substring(0, apiResourcePath.lastIndexOf("/"));
            //Getting specified mediation policy
            Mediation mediation = apiProvider.getApiSpecificMediationPolicy(apiResourcePath,
                    mediationPolicyId);
            if (mediation != null) {
                MediationDTO mediationDTO =
                        MediationMappingUtil.fromMediationToDTO(mediation);
                return Response.ok().entity(mediationDTO).build();
            } else {
                RestApiUtil.handleResourceNotFoundError(RestApiConstants.RESOURCE_POLICY, mediationPolicyId, log);
            }
        } catch (APIManagementException e) {
            //Auth failure occurs when cross tenant accessing APIs. Sends 404, since we don't need
            // to expose the existence of the resource
            if (RestApiUtil.isDueToResourceNotFound(e) || RestApiUtil.isDueToAuthorizationFailure(e)) {
                RestApiUtil.handleResourceNotFoundError(RestApiConstants.RESOURCE_API, apiId, e, log);
            } else {
                String errorMessage = "Error while getting mediation policy with uuid "
                        + mediationPolicyId + " of API " + apiId;
                RestApiUtil.handleInternalServerError(errorMessage, e, log);
            }
=======
    public Response apisApiIdDocumentsGet(String apiId, Integer limit, Integer offset, String ifNoneMatch,
            Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            List<DocumentInfo> documentInfos = apiPublisher.getAllDocumentation(apiId, offset, limit);
            DocumentListDTO documentListDTO = MappingUtil.toDocumentListDTO(documentInfos);
            return Response.status(Response.Status.OK).entity(documentListDTO).build();
        } catch (APIManagementException e) {
            String errorMessage = "Error while getting list of documents" + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
>>>>>>> upstream/master
        }
    }

    /**
     * Adds new document to an API
     *
     * @param apiId             UUID of API
     * @param body              DTO object including the document's meta information
     * @param ifMatch           If-Match header value
     * @param ifUnmodifiedSince If-Unmodified-Since header value
     * @param request           msf4j request object
     * @return newly added document meta info object
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
<<<<<<< HEAD
    public Response apisApiIdPoliciesMediationMediationPolicyIdPut(String apiId,
                                                                   String mediationPolicyId,
                                                                   MediationDTO body,
                                                                   String contentType,
                                                                   String ifMatch,
                                                                   String ifUnmodifiedSince) {
        InputStream contentStream = null;
        APIIdentifier apiIdentifier;
        Mediation updatedMediation;
=======
    public Response apisApiIdDocumentsPost(String apiId, DocumentDTO body, String ifMatch,
            String ifUnmodifiedSince, Request request) throws NotFoundException {
>>>>>>> upstream/master
        try {
            String username = RestApiUtil.getLoggedInUsername(request);
            APIPublisher apiProvider = RestAPIPublisherUtil.getApiPublisher(username);
            DocumentInfo documentation = MappingUtil.toDocumentInfo(body);
            if (body.getType() == DocumentDTO.TypeEnum.OTHER && StringUtils.isBlank(body.getOtherTypeName())) {
                //check otherTypeName for not null if doc type is OTHER
                RestApiUtil.handleBadRequest("otherTypeName cannot be empty if type is OTHER.", log);
            }
            String sourceUrl = body.getSourceUrl();
            if (body.getSourceType() == DocumentDTO.SourceTypeEnum.URL &&
                    (StringUtils.isBlank(sourceUrl) || !RestApiUtil.isURL(sourceUrl))) {
                RestApiUtil.handleBadRequest("Invalid document sourceUrl Format", log);
            }
            //this will fail if user does not have access to the API or the API does not exist
            String docid = apiProvider.addDocumentationInfo(apiId, documentation);
            documentation = apiProvider.getDocumentationSummary(docid);
            DocumentDTO newDocumentDTO = MappingUtil.toDocumentDTO(documentation);
            //Add initial inline content as empty String, if the Document type is INLINE
            if (body.getSourceType() == DocumentDTO.SourceTypeEnum.INLINE) {
                apiProvider.addDocumentationContent(docid, "");
                if (log.isDebugEnabled()) {
                    log.debug("The updated source type of the document " + body.getName() + " is: " + body.getSourceType());
                }
<<<<<<< HEAD
            } else {
                //If registry resource not exists
                RestApiUtil.handleResourceNotFoundError(RestApiConstants.RESOURCE_POLICY, mediationPolicyId, log);
=======
>>>>>>> upstream/master
            }
            return Response.status(Response.Status.CREATED).entity(newDocumentDTO).build();
        } catch (APIManagementException e) {
<<<<<<< HEAD
            //Auth failure occurs when cross tenant accessing APIs. Sends 404, since we don't need
            // to expose the existence of the resource
            if (RestApiUtil.isDueToResourceNotFound(e) || RestApiUtil.isDueToAuthorizationFailure(e)) {
                RestApiUtil.handleResourceNotFoundError(RestApiConstants.RESOURCE_API, apiId, e, log);
            } else {
                String errorMessage = "Error occurred while updating the mediation policy with uuid " +
                        mediationPolicyId + " of API " + apiId;
                RestApiUtil.handleInternalServerError(errorMessage, e, log);
            }
        } catch (URISyntaxException e) {
            String errorMessage = "Error while getting location header for uploaded " +
                    "mediation policy " + body.getName();
            RestApiUtil.handleInternalServerError(errorMessage, e, log);
        } catch (IOException e) {
            String errorMessage = " Error occurred while converting content stream in to string";
            RestApiUtil.handleInternalServerError(errorMessage, e, log);
        } catch (XMLStreamException e) {
            String errorMessage = " Error occurred while getting omelement out of content " +
                    "of mediation policy";
            RestApiUtil.handleInternalServerError(errorMessage, e, log);
        } catch (RegistryException e) {
            String errorMessage = " Error while getting content stream of the requested mediation" +
                    " policy";
            RestApiUtil.handleInternalServerError(errorMessage, e, log);
        } finally {
            IOUtils.closeQuietly(contentStream);
=======
            String errorMessage = "Error while create  document for api " + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
>>>>>>> upstream/master
        }
    }

    /**
     * Retrieve the gateway configuration of an API
     *
     * @param apiId           UUID of API
     * @param ifNoneMatch     If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header value
     * @param request         msf4j request object
     * @return gateway configuration
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
<<<<<<< HEAD
    public Response apisApiIdPoliciesMediationPost(MediationDTO body, String apiId, String contentType,
                                                   String ifMatch, String ifUnmodifiedSince) {
        APIIdentifier apiIdentifier;
        InputStream contentStream = null;
        Mediation createdMediation;
        try {
            String tenantDomain = RestApiUtil.getLoggedInUserTenantDomain();
            apiIdentifier = APIMappingUtil.getAPIIdentifierFromApiIdOrUUID(apiId,
                    tenantDomain);
            APIProvider apiProvider = RestApiUtil.getLoggedInUserProvider();
            String content = body.getConfig();
            //Getting the mediation policy config from body to create resource file
            contentStream = new ByteArrayInputStream(content.getBytes
                    (StandardCharsets.UTF_8));
            ResourceFile contentFile = new ResourceFile(contentStream, contentType);
            //Extracting the file name specified in the config
            String fileName = this.getMediationNameFromConfig(content);
            String apiResourcePath = APIUtil.getAPIPath(apiIdentifier);
            //Getting registry Api base path out of apiResourcePath
            apiResourcePath = apiResourcePath.substring(0, apiResourcePath.lastIndexOf("/"));
            //Constructing mediation resource path
            String mediationResourcePath = apiResourcePath + RegistryConstants.PATH_SEPARATOR +
                    body.getType() + RegistryConstants.PATH_SEPARATOR + fileName;
            if (apiProvider.checkIfResourceExists(mediationResourcePath)) {
                RestApiUtil.handleConflict("Mediation policy already " +
                        "exists in the given resource path, cannot create new", log);
            }
            //Adding api specific mediation policy
            String mediationPolicyUrl = apiProvider.addResourceFile(mediationResourcePath, contentFile);
            if (StringUtils.isNotBlank(mediationPolicyUrl)) {
                //Getting the uuid of created mediation policy
                String uuid = apiProvider.getCreatedResourceUuid(mediationResourcePath);
                //Getting created Api specific mediation policy
                createdMediation = apiProvider.getApiSpecificMediationPolicy
                        (apiResourcePath, uuid);
                MediationDTO createdPolicy =
                        MediationMappingUtil.fromMediationToDTO(createdMediation);
                URI uploadedMediationUri = new URI(mediationPolicyUrl);
                return Response.created(uploadedMediationUri).entity(createdPolicy).build();
=======
    public Response apisApiIdGatewayConfigGet(String apiId, String ifNoneMatch, String ifModifiedSince,
            Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            String existingFingerprint = apisApiIdGatewayConfigGetFingerprint(apiId, ifNoneMatch,
                    ifModifiedSince, request);
            if (!StringUtils.isEmpty(ifNoneMatch) && !StringUtils.isEmpty(existingFingerprint) && ifNoneMatch
                    .contains(existingFingerprint)) {
                return Response.notModified().build();
>>>>>>> upstream/master
            }
            String gatewayConfig = apiPublisher.getApiGatewayConfig(apiId);
            return Response.ok().header(HttpHeaders.ETAG, "\"" + existingFingerprint + "\"").entity(gatewayConfig)
                    .build();
        } catch (APIManagementException e) {
<<<<<<< HEAD
            //Auth failure occurs when cross tenant accessing APIs. Sends 404, since we don't need
            // to expose the existence of the resource
            if (RestApiUtil.isDueToResourceNotFound(e) || RestApiUtil.isDueToAuthorizationFailure(e)) {
                RestApiUtil.handleResourceNotFoundError(RestApiConstants.RESOURCE_API, apiId, e, log);
            } else {
                String errorMessage = "Error while adding the mediation policy : " + body.getName() +
                        "of API " + apiId;
                RestApiUtil.handleInternalServerError(errorMessage, e, log);
            }
        } catch (URISyntaxException e) {
            String errorMessage = "Error while getting location header for created " +
                    "mediation policy " + body.getName();
            RestApiUtil.handleInternalServerError(errorMessage, e, log);
        } finally {
            IOUtils.closeQuietly(contentStream);
=======
            String errorMessage = "Error while retrieving gateway config of API : " + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();

>>>>>>> upstream/master
        }
    }

    /**
     * Retrieves the fingerprint of a gateway config provided its API's UUID
     *
     * @param apiId           UUID of API
     * @param ifNoneMatch     If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header value
     * @param request         msf4j request object
     * @return fingerprint of the gateaway config
     */
    public String apisApiIdGatewayConfigGetFingerprint(String apiId, String ifNoneMatch, String ifModifiedSince,
            Request request) {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            String lastUpdatedTime = RestAPIPublisherUtil.getApiPublisher(username).getLastUpdatedTimeOfGatewayConfig(
                    apiId);
            return ETagUtils.generateETag(lastUpdatedTime);
        } catch (APIManagementException e) {
            //gives a warning and let it continue the execution
            String errorMessage = "Error while retrieving last updated time of gateway config of API " + apiId;
            log.error(errorMessage, e);
            return null;
        }
    }

    /**
     * Update an API's gateway configuration by its UUID
     *
     * @param apiId             UUID of API
     * @param gatewayConfig     gateway configuration
     * @param ifMatch           If-Match header value
     * @param ifUnmodifiedSince If-Unmodified-Since header value
     * @param request           msf4j request object
     * @return Updated gateway configuration
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisApiIdGatewayConfigPut(String apiId, String gatewayConfig, String ifMatch,
            String ifUnmodifiedSince, Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            String existingFingerprint = apisApiIdGatewayConfigGetFingerprint(apiId, null, null, request);
            if (!StringUtils.isEmpty(ifMatch) && !StringUtils.isEmpty(existingFingerprint) && !ifMatch
                    .contains(existingFingerprint)) {
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
            }

            apiPublisher.updateApiGatewayConfig(apiId, gatewayConfig);
            String apiGatewayConfig = apiPublisher.getApiGatewayConfig(apiId);
            String newFingerprint = apisApiIdGatewayConfigGetFingerprint(apiId, null, null, request);
            return Response.ok().header(HttpHeaders.ETAG, "\"" + newFingerprint + "\"").entity(apiGatewayConfig)
                    .build();
        } catch (APIManagementException e) {
            String errorMessage = "Error while gateway configuration update for api : " + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();

        }
    }

    /**
     * Retrives an API by UUID
     *
     * @param apiId           UUID of API
     * @param ifNoneMatch     If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header value
     * @param request         msf4j request object
     * @return API which is identified by the given UUID
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisApiIdGet(String apiId, String ifNoneMatch, String ifModifiedSince, Request request)
            throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            if (!RestAPIPublisherUtil.getApiPublisher(username).isAPIExists(apiId)) {
                String errorMessage = "API not found : " + apiId;
                APIMgtResourceNotFoundException e = new APIMgtResourceNotFoundException(errorMessage,
                        ExceptionCodes.API_NOT_FOUND);
                HashMap<String, String> paramList = new HashMap<String, String>();
                paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
                ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
                log.error(errorMessage, e);
                return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
            }

            String existingFingerprint = apisApiIdGetFingerprint(apiId, ifNoneMatch, ifModifiedSince,
                    request);
            if (!StringUtils.isEmpty(ifNoneMatch) && !StringUtils.isEmpty(existingFingerprint) && ifNoneMatch
                    .contains(existingFingerprint)) {
                return Response.notModified().build();
            }
            APIDTO apidto = MappingUtil.toAPIDto(RestAPIPublisherUtil.getApiPublisher(username).getAPIbyUUID(apiId));
            boolean isWSDLExists = RestAPIPublisherUtil.getApiPublisher(username).isWSDLExists(apiId);
            if (isWSDLExists) {
                String wsdlUri = RestApiConstants.WSDL_URI_TEMPLATE.replace(RestApiConstants.APIID_PARAM, apiId);
                apidto.setWsdlUri(wsdlUri);
            }
            return Response.ok().header(HttpHeaders.ETAG, "\"" + existingFingerprint + "\"").entity(apidto).build();
        } catch (APIManagementException e) {
            String errorMessage = "Error while retrieving API : " + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        } catch (IOException e) {
            String errorMessage = "Error while retrieving API : " + apiId;
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(errorMessage, 900313L, errorMessage);
            log.error(errorMessage, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDTO).build();
        }
    }

    /**
     * Returns the fingerprint of an API
     *
     * @param apiId           UUID of API
     * @param ifNoneMatch     If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header value
     * @param request         msf4j request object
     * @return fingerprint of the given API
     */
    public String apisApiIdGetFingerprint(String apiId, String ifNoneMatch, String ifModifiedSince,
                                          Request request) {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            String lastUpdatedTime = RestAPIPublisherUtil.getApiPublisher(username).getLastUpdatedTimeOfAPI(apiId);
            return ETagUtils.generateETag(lastUpdatedTime);
        } catch (APIManagementException e) {
            //gives a warning and let it continue the execution
            String errorMessage = "Error while retrieving last updated time of API " + apiId;
            log.error(errorMessage, e);
            return null;
        }
    }

    /**
     * Retrieves the possible lifecycle states of a given API
     *
     * @param apiId           UUID of API
     * @param ifNoneMatch     If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header value
     * @param request         msf4j request object
     * @return possible lifecycle states of a given API
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisApiIdLifecycleGet(String apiId, String ifNoneMatch, String ifModifiedSince,
            Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            LifecycleState lifecycleState = RestAPIPublisherUtil.getApiPublisher(username).getAPILifeCycleData(apiId);
            return Response.ok().entity(lifecycleState).build();
        } catch (APIManagementException e) {
            String errorMessage = "Error while retrieving Lifecycle state data for API : " + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();

        }
    }

    /**
     * Retrieves the lifecycle history of the API
     *
     * @param apiId           UUID of the API
     * @param ifNoneMatch     If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header value
     * @param request         msf4j request object
     * @return lifecycle history of the API
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisApiIdLifecycleHistoryGet(String apiId, String ifNoneMatch, String ifModifiedSince,
        Request request) throws NotFoundException {

        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            if (RestAPIPublisherUtil.getApiPublisher(username).isAPIExists(apiId)) {
                String lifecycleInstanceId =
                        RestAPIPublisherUtil.getApiPublisher(username).getAPIbyUUID(apiId).getLifecycleInstanceId();
                if (lifecycleInstanceId != null) {
                    List lifecyclestatechangehistory =
                            RestAPIPublisherUtil.getApiPublisher(username)
                                    .getLifeCycleHistoryFromUUID(lifecycleInstanceId);
                    return Response.ok().entity(lifecyclestatechangehistory).build();
                } else {
                    throw new APIManagementException("Could not find lifecycle information for the requested API"
                            + apiId, ExceptionCodes.APIMGT_LIFECYCLE_EXCEPTION);
                }
            } else {
                String errorMessage = "API Not found : " + apiId;
                APIMgtResourceNotFoundException e = new APIMgtResourceNotFoundException(errorMessage,
                        ExceptionCodes.API_NOT_FOUND);
                HashMap<String, String> paramList = new HashMap<String, String>();
                paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
                ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
                log.error(errorMessage, e);
                return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();

            }
        } catch (APIManagementException e) {
            String errorMessage = "Error while retrieving API : " + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();

        }
    }

    /**
     * Updates an API by UUID
     *
     * @param apiId             UUID of API
     * @param body              Updated API details
     * @param ifMatch           If-Match header value
     * @param ifUnmodifiedSince If-Unmodified-Since header value
     * @param request           msf4j request object
     * @return Updated API
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisApiIdPut(String apiId, APIDTO body, String ifMatch, String ifUnmodifiedSince,
            Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            String existingFingerprint = apisApiIdGetFingerprint(apiId, null, null, request);
            if (!StringUtils.isEmpty(ifMatch) && !StringUtils.isEmpty(existingFingerprint) && !ifMatch
                    .contains(existingFingerprint)) {
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
            }

            API.APIBuilder api = MappingUtil.toAPI(body).id(apiId);
            apiPublisher.updateAPI(api);

            String newFingerprint = apisApiIdGetFingerprint(apiId, null, null, request);
            APIDTO apidto = MappingUtil.toAPIDto(apiPublisher.getAPIbyUUID(apiId));
            return Response.ok().header(HttpHeaders.ETAG, "\"" + newFingerprint + "\"").entity(apidto).build();
        } catch (APIManagementException e) {
            String errorMessage = "Error while updating API : " + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();

        } catch (JsonProcessingException e) {
            String errorMessage = "Error while updating API : " + apiId;
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(errorMessage, 900313L, errorMessage);
            log.error(errorMessage, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDTO).build();
        } catch (IOException e) {
            String errorMessage = "Error while updating API : " + apiId;
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(errorMessage, 900313L, errorMessage);
            log.error(errorMessage, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDTO).build();
        }
    }

    /**
     * Retrieves the swagger definition of an API
     *
     * @param apiId           UUID of API
     * @param ifNoneMatch     If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header value
     * @param request         msf4j request object
     * @return swagger definition of an API
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisApiIdSwaggerGet(String apiId, String ifNoneMatch, String ifModifiedSince,
            Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            String existingFingerprint = apisApiIdSwaggerGetFingerprint(apiId, ifNoneMatch, ifModifiedSince,
                    request);
            if (!StringUtils.isEmpty(ifNoneMatch) && !StringUtils.isEmpty(existingFingerprint) && ifNoneMatch
                    .contains(existingFingerprint)) {
                return Response.notModified().build();
            }
            String swagger = apiPublisher.getApiSwaggerDefinition(apiId);
            return Response.ok().header(HttpHeaders.ETAG, "\"" + existingFingerprint + "\"").entity(swagger).build();
        } catch (APIManagementException e) {
            String errorMessage = "Error while retrieving swagger definition of API : " + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    /**
     * Retrieves the fingerprint of a swagger definition of an API
     *
     * @param apiId           UUID of API
     * @param ifNoneMatch     If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header value
     * @param request         msf4j request object
     * @return fingerprint of a swagger definition of an API
     */
    public String apisApiIdSwaggerGetFingerprint(String apiId, String ifNoneMatch, String ifModifiedSince,
            Request request) {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            String lastUpdatedTime = RestAPIPublisherUtil.getApiPublisher(username).getLastUpdatedTimeOfAPI(apiId);
            return ETagUtils.generateETag(lastUpdatedTime);
        } catch (APIManagementException e) {
            //gives a warning and let it continue the execution
            String errorMessage = "Error while retrieving last updated time of Swagger definition of API :" + apiId;
            log.error(errorMessage, e);
            return null;
        }
    }

    /**
     * Updates the swagger defnition of an API
     *
     * @param apiId             UUID of API
     * @param apiDefinition     updated swagger defintion
     * @param ifMatch           If-Match header value
     * @param ifUnmodifiedSince If-Unmodified-Since header value
     * @param request           msf4j request object
     * @return Updated swagger definition
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisApiIdSwaggerPut(String apiId, String apiDefinition, String ifMatch,
            String ifUnmodifiedSince, Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            String existingFingerprint = apisApiIdSwaggerGetFingerprint(apiId, null, null, request);
            if (!StringUtils.isEmpty(ifMatch) && !StringUtils.isEmpty(existingFingerprint) && !ifMatch
                    .contains(existingFingerprint)) {
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
            }
            apiPublisher.saveSwagger20Definition(apiId, apiDefinition);
            String apiSwagger = apiPublisher.getApiSwaggerDefinition(apiId);
            String newFingerprint = apisApiIdSwaggerGetFingerprint(apiId, null, null, request);
            return Response.ok().header(HttpHeaders.ETAG, "\"" + newFingerprint + "\"").entity(apiSwagger).build();
        } catch (APIManagementException e) {
            String errorMessage = "Error while put swagger for API : " + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    /**
     * Retrives the thumbnail of an API
     *
     * @param apiId           UUID of API
     * @param ifNoneMatch     If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header value
     * @param request         msf4j request object
     * @return the thumbnail image of an API
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisApiIdThumbnailGet(String apiId, String ifNoneMatch, String ifModifiedSince,
            Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            String existingFingerprint = apisApiIdThumbnailGetFingerprint(apiId, ifNoneMatch, ifModifiedSince,
                    request);
            if (!StringUtils.isEmpty(ifNoneMatch) && !StringUtils.isEmpty(existingFingerprint) && ifNoneMatch
                    .contains(existingFingerprint)) {
                return Response.notModified().build();
            }

            InputStream imageInputStream = apiPublisher.getThumbnailImage(apiId);
            if (imageInputStream != null) {
                return Response.ok(imageInputStream, MediaType.APPLICATION_OCTET_STREAM_TYPE)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"icon\"")
                        .header(HttpHeaders.ETAG, "\"" + existingFingerprint + "\"").build();
            } else {
                return Response.noContent().build();
            }
        } catch (APIManagementException e) {
            String errorMessage = "Error while retrieving thumbnail of API : " + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    /**
     * Retrives the current fingerprint of the thumbnail image of an API
     *
     * @param apiId           UUID of API
     * @param ifNoneMatch     If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header value
     * @param request         msf4j request object
     * @return current fingerprint of the thumbnail image of the API
     */
    public String apisApiIdThumbnailGetFingerprint(String apiId, String ifNoneMatch, String ifModifiedSince,
            Request request) {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            String lastUpdatedTime = RestAPIPublisherUtil.getApiPublisher(username)
                    .getLastUpdatedTimeOfAPIThumbnailImage(apiId);
            return ETagUtils.generateETag(lastUpdatedTime);
        } catch (APIManagementException e) {
            //gives a warning and let it continue the execution
            String errorMessage = "Error while retrieving last updated time of thumbnail of API " + apiId;
            log.error(errorMessage, e);
            return null;
        }
    }

    /**
     * Updates the thumbnail image of an API
     *
     * @param apiId             UUID of API
     * @param fileInputStream   Image data stream
     * @param fileDetail        meta information of the image
     * @param ifMatch           If-Match header value
     * @param ifUnmodifiedSince If-Unmodified-Since header value
     * @param request           msf4j request object
     * @return meta info about the updated thumbnail image
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisApiIdThumbnailPost(String apiId, InputStream fileInputStream, FileInfo fileDetail,
            String ifMatch, String ifUnmodifiedSince, Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            String existingFingerprint = apisApiIdThumbnailGetFingerprint(apiId, null, null, request);
            if (!StringUtils.isEmpty(ifMatch) && !StringUtils.isEmpty(existingFingerprint) && !ifMatch
                    .contains(existingFingerprint)) {
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
            }

            apiPublisher.saveThumbnailImage(apiId, fileInputStream, fileDetail.getFileName());
            String uriString = RestApiConstants.RESOURCE_PATH_THUMBNAIL
                    .replace(RestApiConstants.APIID_PARAM, apiId);

            FileInfoDTO infoDTO = new FileInfoDTO();
            infoDTO.setRelativePath(uriString);
            infoDTO.setMediaType(MediaType.APPLICATION_OCTET_STREAM);
            String newFingerprint = apisApiIdThumbnailGetFingerprint(apiId, null, null, request);
            return Response.status(Response.Status.CREATED).entity(infoDTO)
                    .header(HttpHeaders.ETAG, "\"" + newFingerprint + "\"").build();
        } catch (APIManagementException e) {
            String errorMessage = "Error while uploading image" + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    /**
     * Retrieves the WSDL of the particular API. If the WSDL is added as a single file/URL, the text content of the WSDL
     * will be retrived. If the WSDL is added as an archive, the binary content of the archive will be retrieved.
     *
     * @param apiId UUID of API
     * @param ifNoneMatch If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header value
     * @param request msf4j request
     * @return WSDL archive/file content
     * @throws NotFoundException
     */
    @Override
    public Response apisApiIdWsdlGet(String apiId, String ifNoneMatch, String ifModifiedSince,
            Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            InputStream wsdlStream = null;
            if (!apiPublisher.isWSDLExists(apiId)) {
                if (log.isDebugEnabled()) {
                    log.debug("WSDL has no content for API: " + apiId);
                }
                return Response.noContent().build();
            }
            boolean isWSDLArchiveExists = apiPublisher.isWSDLArchiveExists(apiId);
            if (log.isDebugEnabled()) {
                log.debug("API has WSDL archive?: " + isWSDLArchiveExists);
            }
            if (isWSDLArchiveExists) {
                wsdlStream = apiPublisher.getAPIWSDLArchive(apiId);
                API api = apiPublisher.getAPIbyUUID(apiId);
                String wsdlFileName =
                        api.getProvider() + "-" + api.getName() + "-" + api.getVersion() + "-wsdl-archive.zip";
                return Response.ok(wsdlStream)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_TYPE)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + wsdlFileName + "\"")
                        .build();
            } else {
                String wsdlText = apiPublisher.getAPIWSDL(apiId);
                //TODO need to use text/xml content type. It does not work due to an issue with MSF4J -malinthaa
                return Response.ok(wsdlText, MediaType.TEXT_PLAIN).build();
            }
        } catch (APIManagementException e) {
            String errorMessage = "Error while retrieving WSDL of API : " + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    /**
     * Updates the WSDL of an existing API. File can be uploaded as a single WSDL (.wsdl) or a zipped WSDL 
     * archive (.zip). 
     * 
     * @param apiId UUID of API
     * @param fileInputStream file content stream
     * @param fileDetail file details
     * @param ifMatch If-Match header value
     * @param ifUnmodifiedSince If-Unmodified-Since header value
     * @param request msf4j request
     * @return 200 OK if upadating was successful.
     * @throws NotFoundException
     */
    @Override
    public Response apisApiIdWsdlPut(String apiId, InputStream fileInputStream, FileInfo fileDetail,
            String ifMatch, String ifUnmodifiedSince, Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            if (fileDetail.getFileName().endsWith(".zip")) {
                apiPublisher.updateAPIWSDLArchive(apiId, fileInputStream);
                return Response.ok().build();
            } else if (fileDetail.getFileName().endsWith(".wsdl")) {
                String updatedWSDL = apiPublisher.updateAPIWSDL(apiId, fileInputStream);
                return Response.ok(updatedWSDL, MediaType.TEXT_PLAIN).build();
            } else {
                String msg = "Unsupported extension type of file: " + fileDetail.getFileName();
                log.error(msg);
                ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900700L, msg);
                return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();
            }
        } catch (APIManagementException e) {
            String errorMessage = "Error while updating WSDL of API : " + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    /**
     * Change the lifecycle state of an API
     *
     * @param action             lifecycle action
     * @param apiId              UUID of API
     * @param lifecycleChecklist lifecycle check list items
     * @param ifMatch            If-Match header value
     * @param ifUnmodifiedSince  If-Unmodified-Since header value
     * @param request            msf4j request object
     * @return 200 OK if the operation is succesful
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisChangeLifecyclePost(String action, String apiId, String lifecycleChecklist, String ifMatch,
                                            String ifUnmodifiedSince, Request request
    ) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        Map<String, Boolean> lifecycleChecklistMap = new HashMap<>();
        WorkflowResponseDTO response = null;
        try {
            if (lifecycleChecklist != null) {
                String[] checkList = lifecycleChecklist.split(",");
                for (String checkList1 : checkList) {
                    StringTokenizer attributeTokens = new StringTokenizer(checkList1, ":");
                    String attributeName = attributeTokens.nextToken();
                    Boolean attributeValue = Boolean.valueOf(attributeTokens.nextToken());
                    lifecycleChecklistMap.put(attributeName, attributeValue);
                }
            }
            if (action.trim().equals(APIMgtConstants.CHECK_LIST_ITEM_CHANGE_EVENT)) {
                RestAPIPublisherUtil.getApiPublisher(username).updateCheckListItem(apiId, action,
                        lifecycleChecklistMap);
                WorkflowResponse workflowResponse = new GeneralWorkflowResponse();
                //since workflows are not defined for checklist items
                workflowResponse.setWorkflowStatus(WorkflowStatus.APPROVED);
                response = MappingUtil.toWorkflowResponseDTO(workflowResponse);
                return Response.ok().entity(response).build();
            } else {
                WorkflowResponse workflowResponse = RestAPIPublisherUtil.getApiPublisher(username)
                        .updateAPIStatus(apiId, action, lifecycleChecklistMap);
                response = MappingUtil.toWorkflowResponseDTO(workflowResponse);
                //if workflow is in pending state or if the executor sends any httpworklfowresponse (workflow state can 
                //be in either pending or approved state) send back the workflow response 
                if (WorkflowStatus.CREATED == workflowResponse.getWorkflowStatus()) {
                    URI location = new URI(RestApiConstants.RESOURCE_PATH_APIS + "/" + apiId);
                    return Response.status(Response.Status.ACCEPTED).header(RestApiConstants.LOCATION_HEADER, location)
                            .entity(response).build();
                } else {                    
                    return Response.ok().entity(response).build();
                }                
            }
            
        } catch (APIManagementException e) {
            String errorMessage = "Error while updating lifecycle of API" + apiId + " to " + action;
            Map<String, String> paramList = new HashMap<>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        } catch (URISyntaxException e) {
            String errorMessage = "Error while adding location header in response for api : " + apiId;
            Map<String, String> paramList = new HashMap<>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorHandler errorHandler = ExceptionCodes.LOCATION_HEADER_INCORRECT;
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(errorHandler, paramList);
            log.error(errorMessage, e);
            return Response.status(errorHandler.getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    /**
     * Creates a new version of an API
     *
     * @param newVersion new version
     * @param apiId      UUID of API
     * @param request    msf4j request object
     * @return created new API
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisCopyApiPost(String newVersion, String apiId, Request request) throws NotFoundException {
        APIDTO newVersionedApi;
        String apiName, newApiVersion;
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
<<<<<<< HEAD
            APIProvider apiProvider = RestApiUtil.getLoggedInUserProvider();
            String tenantDomain = RestApiUtil.getLoggedInUserTenantDomain();
            //this will fail if user does not have access to the API or the API does not exist
            APIIdentifier apiIdentifier = APIMappingUtil.getAPIIdentifierFromApiIdOrUUID(apiId,
                    tenantDomain);
            String wsdlContent = apiProvider.getWsdl(apiIdentifier);
            WsdlDTO dto = new WsdlDTO();
            dto.setWsdlDefinition(wsdlContent);
            dto.setName(apiIdentifier.getProviderName() + "--" + apiIdentifier.getApiName() +
                    apiIdentifier.getVersion() + ".wsdl");
            return Response.ok().entity(dto).build();
=======
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            String newAPIVersionId = apiPublisher.createNewAPIVersion(apiId, newVersion);
            newVersionedApi = MappingUtil.toAPIDto(apiPublisher.getAPIbyUUID(newAPIVersionId));
            return Response.status(Response.Status.CREATED).entity(newVersionedApi).build();
>>>>>>> upstream/master
        } catch (APIManagementException e) {
            String errorMessage = "Error while create new API version " + apiId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            paramList.put(APIMgtConstants.ExceptionsConstants.API_VERSION, newVersion);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList, e);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        } catch (IOException e) {
            String errorMessage = "Error while create new API version " + apiId;
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(errorMessage, 900313L, errorMessage);
            log.error(errorMessage, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDTO).build();
        }
    }

    /**
<<<<<<< HEAD
     * 
     * @param apiId API Id
     * @param body WSDL DTO
     * @param contentType content type of the payload
     * @param ifMatch If-match header value
     * @param ifUnmodifiedSince If-Unmodified-Since header value
     * @return added wsdl 
     */
    @Override
    public Response apisApiIdWsdlPost(String apiId, WsdlDTO body, String contentType, String ifMatch,
            String ifUnmodifiedSince) {
        try {
            APIProvider apiProvider = RestApiUtil.getLoggedInUserProvider();
            String tenantDomain = RestApiUtil.getLoggedInUserTenantDomain();
            APIIdentifier apiIdentifier = APIMappingUtil.getAPIIdentifierFromApiIdOrUUID(apiId,
                    tenantDomain);
            String resourcePath = apiIdentifier.getProviderName() + APIConstants.WSDL_PROVIDER_SEPERATOR +
                    apiIdentifier.getApiName() + apiIdentifier.getVersion() +
                    APIConstants.WSDL_FILE_EXTENSION;
            resourcePath = APIConstants.API_WSDL_RESOURCE_LOCATION + resourcePath;
            if (apiProvider.checkIfResourceExists(resourcePath)) {
                RestApiUtil.handleConflict("wsdl resource already exists for the API " + apiId, log);
            }
            apiProvider.uploadWsdl(resourcePath, body.getWsdlDefinition());

            WsdlDTO wsdlDTO = new WsdlDTO();
            wsdlDTO.setWsdlDefinition(apiProvider.getWsdl(apiIdentifier));
            wsdlDTO.setName(apiIdentifier.getProviderName() + "--" + apiIdentifier.getApiName() +
                    apiIdentifier.getVersion() + ".wsdl");
            return Response.ok().entity(wsdlDTO).build();
        } catch (APIManagementException e) {
            //Auth failure occurs when cross tenant accessing APIs. Sends 404, since we don't need
            // to expose the existence of the resource
            if (RestApiUtil.isDueToResourceNotFound(e) || RestApiUtil.isDueToAuthorizationFailure(e)) {
                RestApiUtil.handleResourceNotFoundError(RestApiConstants.RESOURCE_API, apiId, e, log);
            } else {
                String errorMessage = "Error while uploading wsdl of API : " + apiId;
                RestApiUtil.handleInternalServerError(errorMessage, e, log);
=======
     * Retrives all APIs that qualifies for the given fitering attributes
     *
     * @param limit       maximum APIs to return
     * @param offset      starting position of the pagination
     * @param query       search query
     * @param ifNoneMatch If-None-Match header value
     * @param request     msf4j request object
     * @return a list of qualifying APIs
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisGet(Integer limit, Integer offset, String query, String ifNoneMatch, Request request)
            throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        APIListDTO apiListDTO = null;
        try {
            apiListDTO = MappingUtil
                    .toAPIListDTO(RestAPIPublisherUtil.getApiPublisher(username).searchAPIs(limit, offset, query));
            return Response.ok().entity(apiListDTO).build();
        } catch (APIManagementException e) {
            String errorMessage = "Error while retrieving APIs";
            HashMap<String, String> paramList = new HashMap<String, String>();
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList, e);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    /**
     * Check if an API available for the given query
     *
     * @param query       search query
     * @param ifNoneMatch If-None-Match header value
     * @param request     msf4j request object
     * @return 200 if an API is found for the query, 404 otherwise
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisHead(String query, String ifNoneMatch, Request request) throws NotFoundException {
        //TODO improve the query parameters searching options
        String username = RestApiUtil.getLoggedInUsername(request);
        String context = "context";
        String name = "name";
        boolean status;

        try {
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            String[] words = query.split(":");

            if (words.length > 1) {
                if (context.equalsIgnoreCase(words[0])) {
                    status = apiPublisher.checkIfAPIContextExists(words[1]);
                } else if (name.equalsIgnoreCase(words[0])) {
                    status = apiPublisher.checkIfAPINameExists(words[1]);
                } else {
                    status = false;
                }
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            if (status) {
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (APIManagementException e) {
            String errorMessage = "Error while checking status.";
            HashMap<String, String> paramList = new HashMap<String, String>();
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList, e);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    /**
     * Import an API from a Swagger or WSDL
     * 
     * @param type definition type. If not specified, default will be SWAGGER
     * @param fileInputStream file content stream, can be either archive or a single text file
     * @param fileDetail file details
     * @param url URL of the definition
     * @param additionalProperties Additional attributes specified as a stringified JSON with API's schema
     * @param ifMatch If-Match header value
     * @param ifUnmodifiedSince If-Unmodified-Since header value
     * @param implementationType WSDL based API implementation type (SOAP or HTTP_BINDING)
     * @param request msf4j request object
     * @return Imported API
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisImportDefinitionPost(String type, InputStream fileInputStream, FileInfo fileDetail,
            String url, String additionalProperties, String implementationType, String ifMatch,
            String ifUnmodifiedSince, Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            if (StringUtils.isBlank(type)) {
                type = APIDefinitionValidationResponseDTO.DefinitionTypeEnum.SWAGGER.toString();
            }

            Response response = buildResponseIfParamsInvalid(type, fileInputStream, url);
            if (response != null)
                return response;

            API.APIBuilder apiBuilder = null;
            APIDTO additionalPropertiesAPI = null;
            if (!StringUtils.isBlank(additionalProperties)) {
                if (log.isDebugEnabled()) {
                    log.debug("Deseriallizing additionalProperties: " + additionalProperties);
                }
                ObjectMapper mapper = new ObjectMapper();
                additionalPropertiesAPI = mapper.readValue(additionalProperties, APIDTO.class);
                apiBuilder = MappingUtil.toAPI(additionalPropertiesAPI);
                if (log.isDebugEnabled()) {
                    log.debug("Successfully deseriallized additionalProperties: " + additionalProperties);
                }
>>>>>>> upstream/master
            }

            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            String uuid = "";

            if (APIDefinitionValidationResponseDTO.DefinitionTypeEnum.SWAGGER.toString().equals(type)) {
                if (log.isDebugEnabled()) {
                    log.debug("Adding an API by importing a swagger.");
                }
                if (fileInputStream != null) {
                    uuid = apiPublisher.addApiFromDefinition(fileInputStream);
                } else {
                    URL swaggerUrl = new URL(url);
                    HttpURLConnection urlConn = (HttpURLConnection) swaggerUrl.openConnection();
                    uuid = apiPublisher.addApiFromDefinition(urlConn);
                }
            } else { // WSDL type

                if (log.isDebugEnabled()) {
                    log.debug("Adding an API by importing a WSDL.");
                }

                //In this case, additionalPropertiesAPI must not be null since we need attributes like name, 
                // context, version when creating an API from WSDL 
                if (additionalPropertiesAPI != null) {
                    final String soap = RestApiConstants.IMPORT_DEFINITION_WSDL_IMPL_TYPE_SOAP;
                    final String httpBinding = RestApiConstants.IMPORT_DEFINITION_WSDL_IMPL_TYPE_HTTP;
                    
                    if (implementationType != null && !soap.equals(implementationType) && !httpBinding
                            .equals(implementationType)) {
                        String msg =
                                "Invalid implementation type. Should be one of '" + soap + "' or '" + httpBinding + "'";
                        log.error(msg);
                        ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900700L, msg);
                        return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();                        
                    }

                    boolean isHttpBinding = httpBinding.equals(implementationType);

                    if (fileInputStream != null) {
                        if (fileDetail.getFileName() == null) {
                            String msg = "File name cannot be null.";
                            log.error(msg);
                            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900700L, msg);
                            return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();
                        }
                        
                        if (fileDetail.getFileName().endsWith(".zip")) {
                            uuid = apiPublisher.addAPIFromWSDLArchive(apiBuilder, fileInputStream, isHttpBinding);
                            if (log.isDebugEnabled()) {
                                log.debug("Successfully added API with WSDL archive " + fileDetail.getFileName());
                            }
                        } else if (fileDetail.getFileName().endsWith(".wsdl")) {
                            uuid = apiPublisher.addAPIFromWSDLFile(apiBuilder, fileInputStream, isHttpBinding);
                            if (log.isDebugEnabled()) {
                                log.debug("Successfully added API with WSDL file " + fileDetail.getFileName());
                            }
                        } else {
                            String msg = "Unsupported extension type of file: " + fileDetail.getFileName();
                            log.error(msg);
                            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900700L, msg);
                            return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();
                        }
                    } else {
                        uuid = apiPublisher.addAPIFromWSDLURL(apiBuilder, url, isHttpBinding);
                        if (log.isDebugEnabled()) {
                            log.debug("Successfully added API with WSDL URL " + url);
                        }
                    }
                } else {
                    String msg = "'additionalProperties' should be specified when creating an API from WSDL";
                    log.error(msg);
                    ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900700L, msg);
                    return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();
                }
            }
            API returnAPI = apiPublisher.getAPIbyUUID(uuid);
            return Response.status(Response.Status.CREATED).entity(MappingUtil.toAPIDto(returnAPI)).build();
        } catch (APIManagementException e) {
            String errorMessage = "Error while adding new API";
            HashMap<String, String> paramList = new HashMap<String, String>();
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        } catch (IOException e) {
            String errorMessage = "Error while adding new API";
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(errorMessage, 900313L, errorMessage);
            log.error(errorMessage, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDTO).build();
        }
    }

    /**
     * Creates a new API
     *
     * @param body        DTO model including the API details
     * @param request     msf4j request object
     * @return Newly created API
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisPost(APIDTO body, Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
<<<<<<< HEAD
            APIDefinition definitionFromSwagger20 = new APIDefinitionFromSwagger20();
            APIProvider apiProvider = RestApiUtil.getLoggedInUserProvider();
            String tenantDomain = RestApiUtil.getLoggedInUserTenantDomain();
            //this will fail if user does not have access to the API or the API does not exist
            API existingAPI = APIMappingUtil.getAPIFromApiIdOrUUID(apiId, tenantDomain);
            Set<URITemplate> uriTemplates = definitionFromSwagger20.getURITemplates(existingAPI, apiDefinition);
            Set<Scope> scopes = definitionFromSwagger20.getScopes(apiDefinition);
            existingAPI.setUriTemplates(uriTemplates);
            existingAPI.setScopes(scopes);

            //Update API is called to update URITemplates and scopes of the API
            apiProvider.updateAPI(existingAPI);
            apiProvider.saveSwagger20Definition(existingAPI.getId(), apiDefinition);
            //retrieves the updated swagger definition
            String apiSwagger = apiProvider.getSwagger20Definition(existingAPI.getId());
            return Response.ok().entity(apiSwagger).build();
=======
            API.APIBuilder apiBuilder = MappingUtil.toAPI(body);
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            apiPublisher.addAPI(apiBuilder);
            API returnAPI = apiPublisher.getAPIbyUUID(apiBuilder.getId());
            return Response.status(Response.Status.CREATED).entity(MappingUtil.toAPIDto(returnAPI)).build();
>>>>>>> upstream/master
        } catch (APIManagementException e) {
            String errorMessage = "Error while adding new API : " + body.getProvider() + "-" +
                    body.getName() + "-" + body.getVersion();
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_NAME, body.getName());
            paramList.put(APIMgtConstants.ExceptionsConstants.API_VERSION, body.getVersion());
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        } catch (JsonProcessingException e) {
            String errorMessage = "Error while adding new API";
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(errorMessage, 900313L, errorMessage);
            log.error(errorMessage, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDTO).build();
        } catch (IOException e) {
            String errorMessage = "Error while adding new API";
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(errorMessage, 900313L, errorMessage);
            log.error(errorMessage, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDTO).build();
        }
    }

    /**
     * Validates a provided API definition
     * 
     * @param type API definition type (SWAGGER or WSDL)
     * @param fileInputStream file content stream
     * @param fileDetail file details
     * @param url URL of the definition
     * @param request msf4j request
     * @return API definition validation information
     * @throws NotFoundException
     */
    @Override
    public Response apisValidateDefinitionPost(String type, InputStream fileInputStream, FileInfo fileDetail,
            String url, Request request) throws NotFoundException {
        String errorMessage = "Error while validating the definition";
        String username = RestApiUtil.getLoggedInUsername(request);
        try {
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            if (StringUtils.isBlank(type)) {
                type = APIDefinitionValidationResponseDTO.DefinitionTypeEnum.SWAGGER.toString();
            }

            Response responseIfParamsInvalid = buildResponseIfParamsInvalid(type, fileInputStream, url);
            if (responseIfParamsInvalid != null) {
                return responseIfParamsInvalid;
            }

            if (APIDefinitionValidationResponseDTO.DefinitionTypeEnum.SWAGGER.toString().equals(type)) {
                if (log.isDebugEnabled()) {
                    log.debug("Validating a swagger file.");
                }
                // TODO implement swagger validation
                return Response.noContent().build();
            } else { //WSDL type

                WSDLProcessor processor = null;
                WSDLInfo info = null;
                if (!StringUtils.isBlank(url)) {
                    processor = WSDLProcessFactory.getInstance().getWSDLProcessor(url);
                    info = processor.getWsdlInfo();
                    if (log.isDebugEnabled()) {
                        log.debug("Successfully validated WSDL URL " + url);
                    }
                } else {
                    if (fileDetail.getFileName().endsWith(".zip")) {
                        WSDLArchiveInfo archiveInfo = apiPublisher.extractAndValidateWSDLArchive(fileInputStream);
                        info = archiveInfo.getWsdlInfo();
                        if (log.isDebugEnabled()) {
                            log.debug("Successfully validated WSDL archive " + fileDetail.getFileName());
                        }
                    } else if (fileDetail.getFileName().endsWith(".wsdl")) {
                        byte[] wsdlContent = IOUtils.toByteArray(fileInputStream);
                        processor = WSDLProcessFactory.getInstance().getWSDLProcessor(wsdlContent);
                        info = processor.getWsdlInfo();
                        if (log.isDebugEnabled()) {
                            log.debug("Successfully validated WSDL file " + fileDetail.getFileName());
                        }
                    } else {
                        String msg = "Unsupported extension type of file: " + fileDetail.getFileName();
                        log.error(msg);
                        ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900700L, msg);
                        return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();
                    }
                }
                if (info != null) {
                    APIDefinitionValidationResponseDTO responseDTO = MappingUtil.toWSDLValidationResponseDTO(info);
                    return Response.ok(responseDTO).build();
                }
                APIDefinitionValidationResponseDTO responseDTO = new APIDefinitionValidationResponseDTO();
                responseDTO.isValid(false);
                return Response.ok().entity(responseDTO).build();
            }
<<<<<<< HEAD
        } catch (FaultGatewaysException e) {
            String errorMessage = "Error while updating API : " + apiId;
            RestApiUtil.handleInternalServerError(errorMessage, e, log);
=======
        } catch (APIManagementException e) {
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler());
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        } catch (IOException e) {
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(errorMessage, 900313L, errorMessage);
            log.error(errorMessage, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDTO).build();
>>>>>>> upstream/master
        }
    }

    /**
     * Remove pending lifecycle state change workflow tasks.
     * 
     * @param apiId api id
     * @param request     msf4j request object
     * @return Empty payload
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response apisApiIdLifecycleLifecyclePendingTaskDelete(String apiId, Request request)
            throws NotFoundException {
        try {
            String username = RestApiUtil.getLoggedInUsername(request);
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            apiPublisher.removePendingLifecycleWorkflowTaskForAPI(apiId);
            return Response.ok().build();
        } catch (APIManagementException e) {
            String errorMessage = "Error while removing pending task for API state change for api " + apiId;
            Map<String, String> paramList = new HashMap<>();
            paramList.put(APIMgtConstants.ExceptionsConstants.API_ID, apiId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    /**
     * Validate API deefinition import/validate parameters
     * 
     * @param type API definition type (SWAGGER or WSDL)
     * @param fileInputStream file content stream
     * @param url URL of the definition
     * @return Response if any parameter is invalid. Otherwise returns null.
     */
    private Response buildResponseIfParamsInvalid(String type, InputStream fileInputStream, String url) {
        final String SWAGGER = APIDefinitionValidationResponseDTO.DefinitionTypeEnum.SWAGGER.toString();
        final String WSDL = APIDefinitionValidationResponseDTO.DefinitionTypeEnum.WSDL.toString();
        if (!SWAGGER.equals(type) && !WSDL.equals(type)) {
            String errorMessage = "Unsupported definition type. Only SWAGGER or WSDL is allowed";
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(ExceptionCodes.UNSUPPORTED_API_DEFINITION_TYPE);
            log.error(errorMessage);
            return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();
        }

        if (url == null && fileInputStream == null) {
            String msg = "Either 'file' or 'url' should be specified";
            log.error(msg);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900700L, msg);
            return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();
        }

        if (fileInputStream != null && url != null) {
            String msg = "Only one of 'file' and 'url' should be specified";
            log.error(msg);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(msg, 900700L, msg);
            return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();
        }
        return null;
    }
}
