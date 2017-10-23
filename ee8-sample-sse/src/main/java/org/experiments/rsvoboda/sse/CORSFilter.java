/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2017, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.experiments.rsvoboda.sse;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Rostislav Svoboda
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {

     /**
     * See {@link ContainerResponseFilter}.
     *
     * @param request  Client request
     * @param response Client response
     * @throws IOException When something goes wrong
     */
    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        
        MultivaluedMap<String, Object> headers = response.getHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Max-Age", "1209600");
        headers.add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, ETag, if-none-match");
        headers.add("Access-Control-Expose-Headers",
                "origin, content-type, accept, authorization, ETag, if-none-match");
        headers.add("x-foo-by", "rs");
    }
}
