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

import java.time.LocalTime;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

/**
 *
 * @author Rostislav Svoboda
 */
@Singleton
@Path("beats")
public class BroadcastsResource {
    private Sse sse;
    private SseBroadcaster broadcaster;
    
    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void register(@Context Sse sse, @Context SseEventSink eventSink) {
        this.sse = sse;
        if (broadcaster == null) {
            broadcaster = sse.newBroadcaster();
        }
        broadcaster.register(eventSink);
    }
    
    @Schedule(second = "*/2", minute = "*", hour = "*")
    public void beat() {
        System.out.println(".");
        if (broadcaster != null) {
            broadcaster.broadcast(sse.newEvent("time: " + LocalTime.now().toString()));
        }
    }
    
}
