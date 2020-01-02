package logsSender;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineController {

	private final String[] severities = {"Info", "Warning", "Error"};
	private Map<String, List<Line>> logFiles =
		new HashMap<String, List<Line>>() {{
			put("file1", new ArrayList<>());
			put("file2", new ArrayList<>());
		}};

	@RequestMapping("/getFileNames")
	public Set<String> getFileNames() {
		return logFiles.keySet();
	}

	@RequestMapping("/getLogs/{fileName}/{beginFromMessageId}")
	/*
	Used to retrieve logs for a given file name
	Starting with the message ID passed in the parameter
	Assumption: each log file will contain log entries that
	start with an ID, the ID will be numeric, and it will
	be incremented for each message by 1
	 */
	public List<Line> getLogs(
			@PathVariable String fileName,
			@PathVariable int beginFromMessageId
	) {
		if(!logFiles.containsKey(fileName)) {
			return new ArrayList<>();
		}

		List<Line> linesForLogFile = logFiles.get(fileName);
		if(linesForLogFile == null) {
			return new ArrayList<>();
		}

		// Assume that the last line in the list is the latest
		long nextId = linesForLogFile.size() > 0? linesForLogFile.get(linesForLogFile.size() - 1).getId() + 1: 0;

		int numberOfLines = ThreadLocalRandom.current().nextInt(50, 500);
		for(int i = 0; i < numberOfLines; i++) {
			String severity = severities[ThreadLocalRandom.current().nextInt(0, severities.length)];
			linesForLogFile.add(new Line(nextId, new Date(),
					severity, "Test message"));

			nextId++;
		}

		if(nextId < beginFromMessageId) {
			return new ArrayList<>();
		}

		return linesForLogFile.subList(beginFromMessageId, linesForLogFile.size());
	}
}
