package rs.ac.uns.ftn.informatika.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api")
public class ProducerController {

	@Autowired
	private LocationSimulatorService simulatorService;

	@PostMapping(value="/{queue}", consumes = "text/plain")
	public ResponseEntity<String> sendMessage(
			@PathVariable("queue") String queue,
			@RequestParam(value = "frequency", defaultValue = "30") int frequencyInSeconds
	) {
		simulatorService.simulateRoute(queue, frequencyInSeconds);
		return ResponseEntity.ok().build();
	}


}
