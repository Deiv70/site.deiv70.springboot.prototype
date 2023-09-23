package site.deiv70.springboot.prototype.infrastructure.primary.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.ApiErrorResponseDtoModel;

import java.util.List;

@Getter
public class ApiRequestException extends RuntimeException {

	private final HttpStatus httpStatus;
	private final Exception exception;
	private final List<String> messages;

	public ApiRequestException(HttpStatus httpStatus, List<String> messages) {
		super(messages.toString());
		this.httpStatus = httpStatus;
		this.exception = null;
		this.messages = messages;
	}

	public ApiRequestException(HttpStatus httpStatus, Exception exception, List<String> messages) {
		super(messages.toString());
		this.httpStatus = httpStatus;
		this.exception = exception;
		this.messages = messages;
	}

	public ApiRequestException(Exception exception, List<String> messages) {
		super(messages.toString());
		httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		this.exception = exception;
		this.messages = messages;
	}

	public final ApiErrorResponseDtoModel getApiErrorResponseDtoModel() {
		ApiErrorResponseDtoModel apiErrorResponseDtoModel = new ApiErrorResponseDtoModel();
		apiErrorResponseDtoModel.setCode(httpStatus.value());
		apiErrorResponseDtoModel.setType(httpStatus.name());
		apiErrorResponseDtoModel.setMessages(messages);
		return apiErrorResponseDtoModel;
	}

}
