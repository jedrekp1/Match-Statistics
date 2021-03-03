package pl.polsl.matchstatisticsweb.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resources class
 * 
 * @author Jedrzej Piaskowski
 * @version 4.0
 */
@Path("javaee8")
public class JavaEE8Resource {
    
    /**
     *
     * Ping method
     * 
     * @return Response response for ping
     */
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
}
