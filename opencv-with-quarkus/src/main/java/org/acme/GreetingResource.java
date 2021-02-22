package org.acme;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/img")
public class GreetingResource {

    private VideoCapture capture;
    private static final int webcamIndex = 0;

    @GET
    @Produces("image/png")
    public Response helloImg() {
        Mat frame = new Mat();
        MatOfByte buffer = new MatOfByte();

        capture = new VideoCapture();
        capture.open(webcamIndex);
        if (capture.isOpened()) {
            capture.read(frame);
            Imgcodecs.imencode(".png", frame, buffer);
        } else {
            return Response.serverError().build();
        }
        return Response.ok(buffer.toArray()).build();
    }
}