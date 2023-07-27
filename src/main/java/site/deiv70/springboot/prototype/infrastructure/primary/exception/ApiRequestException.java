package site.deiv70.springboot.prototype.infrastructure.primary.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiRequestException extends RuntimeException {

	private final String type;

	public ApiRequestException(String type, String message) {
		super(message);
		this.type = type;
	}

	public ApiRequestException(String type, String message, Throwable throwable) {
		super(message, throwable);
		this.type = type;
	}

	public ApiRequestException(String message, Throwable throwable) {
		super(message, throwable);
		type = null;
	}

}
