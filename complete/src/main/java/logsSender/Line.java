package logsSender;

import java.util.Date;

public class Line {

	private final long id;
	private final Date dateTime;
	private final String severity;
	private final String message;

	public Line(long id, Date dateTime, String severity, String message) {
		this.id = id;
		this.dateTime = dateTime;
		this.severity = severity;
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public String getSeverity() {
		return severity;
	}

	public String getMessage() {
		return message;
	}
}
