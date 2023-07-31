package site.deiv70.springboot.prototype.infrastructure.primary.exception;

import lombok.Getter;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.ApiErrorResponseDtoModel;

@Getter
public class ApiRequestException extends RuntimeException {

	@Getter
	public enum Type {
		INVALID_PARAMETER_EXCEPTION(400),
		INSUFFICIENT_PERMISSIONS_EXCEPTION(401),
		ENTITY_NOT_FOUND_EXCEPTION(404),
		SERVER_EXCEPTION(500),
		UNAVAILABLE_SERVICE_EXCEPTION(503),
		UNKNOWN_EXCEPTION(500);

		private final int httpCode;

		Type(int httpCode) {
			this.httpCode = httpCode;
		}
	}
	private final Type type;
	private final Exception exception;


	public ApiRequestException(Type type, String message) {
		super(message);
		this.type = type;
		this.exception = null;
	}

	public ApiRequestException(Type type, String message, Exception exception) {
		super(message);
		this.type = type;
		this.exception = exception;
	}

	public ApiRequestException(String message, Exception exception) {
		super(message);
		type = Type.UNKNOWN_EXCEPTION;
		this.exception = exception;
	}

	public final ApiErrorResponseDtoModel getHttpResponse() {
		ApiErrorResponseDtoModel apiErrorResponseDtoModel = new ApiErrorResponseDtoModel();
		apiErrorResponseDtoModel.setType(type.name());
		apiErrorResponseDtoModel.setCode(type.httpCode);
		apiErrorResponseDtoModel.setMessage(getMessage());
		return apiErrorResponseDtoModel;
	}

}
