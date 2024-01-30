package rs.ac.uns.ftn.informatika.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LocationSimulatorService {

    @Autowired
    private Producer producer;


    public void simulateRoute(String queue,int period) {
        List<Coordinate> simulatedRoute = generateSimulatedRoute();

        // Schedule sending coordinates with a specified frequency
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        AtomicInteger index = new AtomicInteger(0);

        executorService.scheduleAtFixedRate(() -> {
            if (index.get() < simulatedRoute.size()) {
                sendCoordinates(simulatedRoute.get(index.getAndIncrement()), queue);
            }
        }, 0, period, TimeUnit.SECONDS);
    }

    private List<Coordinate> generateSimulatedRoute() {
        // Your logic to generate a simulated route
        // ...

        // For simplicity, a hardcoded route is used in this example
        return new ArrayList<>(Arrays.asList(
                new Coordinate(40.7128, -74.0060),
                new Coordinate(34.0522, -118.2437),
                new Coordinate(41.8781, -87.6298),
                new Coordinate(40.7128, -74.0060),
                new Coordinate(34.0522, -118.2437),
                new Coordinate(41.8781, -87.6298),
                new Coordinate(40.7128, -74.0060),
                new Coordinate(34.0522, -118.2437),
                new Coordinate(41.8781, -87.6298),
                new Coordinate(40.7128, -74.0060),
                new Coordinate(34.0522, -118.2437),
                new Coordinate(41.8781, -87.6298),
                new Coordinate(40.7128, -74.0060),
                new Coordinate(34.0522, -118.2437),
                new Coordinate(41.8781, -87.6298),
                new Coordinate(40.7128, -74.0060),
                new Coordinate(34.0522, -118.2437),
                new Coordinate(41.8781, -87.6298),
                new Coordinate(40.7128, -74.0060),
                new Coordinate(34.0522, -118.2437),
                new Coordinate(41.8781, -87.6298)
        ));
    }

    private void sendCoordinates(Coordinate coordinate, String queue) {
        String message = coordinate.getLongitude() + "|" + coordinate.getLatitude();
        producer.sendTo(queue, message);
    }

}
