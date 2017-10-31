package org.wso2.carbon.apimgt.rest.api.store;

<<<<<<< HEAD
import org.wso2.carbon.apimgt.rest.api.store.dto.*;
import org.wso2.carbon.apimgt.rest.api.store.TagsApiService;
import org.wso2.carbon.apimgt.rest.api.store.factories.TagsApiServiceFactory;
=======
>>>>>>> upstream/master

import io.swagger.annotations.ApiParam;

import org.wso2.carbon.apimgt.rest.api.store.dto.ErrorDTO;
import org.wso2.carbon.apimgt.rest.api.store.dto.TagListDTO;

<<<<<<< HEAD
import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

=======
import org.wso2.msf4j.Microservice;
import org.wso2.msf4j.Request;
import org.wso2.msf4j.formparam.FileInfo;
import org.wso2.msf4j.formparam.FormDataParam;
import org.osgi.service.component.annotations.Component;

import java.io.InputStream;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
>>>>>>> upstream/master
import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Component(
    name = "org.wso2.carbon.apimgt.rest.api.store.TagsApi",
    service = Microservice.class,
    immediate = true
)
@Path("/api/am/store/v1.[\\d]+/tags")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@ApplicationPath("/tags")
@io.swagger.annotations.Api(description = "the tags API")
public class TagsApi implements Microservice  {
   private final TagsApiService delegate = TagsApiServiceFactory.getTagsApi();

    @OPTIONS
    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get all tags", notes = "Get a list of tags that are already added to APIs ", response = TagListDTO.class, tags={ "Retrieve", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK. Tag list is returned. ", response = TagListDTO.class),
        
        @io.swagger.annotations.ApiResponse(code = 304, message = "Not Modified. Empty body because the client has already the latest version of the requested resource. ", response = TagListDTO.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found. Requested API does not exist. ", response = TagListDTO.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "Not Acceptable. The requested media type is not supported ", response = TagListDTO.class) })
    public Response tagsGet(@ApiParam(value = "Maximum size of resource array to return. ", defaultValue="25") @DefaultValue("25") @QueryParam("limit") Integer limit
,@ApiParam(value = "Starting point within the complete list of items qualified. ", defaultValue="0") @DefaultValue("0") @QueryParam("offset") Integer offset
,@ApiParam(value = "Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec. " )@HeaderParam("If-None-Match") String ifNoneMatch
 ,@Context Request request)
    throws NotFoundException {
        return delegate.tagsGet(limit,offset,ifNoneMatch,request);
    }
}
