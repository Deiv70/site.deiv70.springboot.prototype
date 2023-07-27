package site.deiv70.springboot.prototype.infrastructure.primary.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.ApiErrorResponseDtoModel;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ApiRequestException.class)
	public ResponseEntity<ApiErrorResponseDtoModel> handleApiRequestException(ApiRequestException ex) {
		ApiErrorResponseDtoModel apiErrorResponseDtoModel = new ApiErrorResponseDtoModel();
		apiErrorResponseDtoModel.setType(ex.getType());

		switch (ex.getType()) {
			case "InvalidParameterException" -> apiErrorResponseDtoModel.setCode(400);
			case "InsufficientPermissionsException" -> apiErrorResponseDtoModel.setCode(401);
			case "EntityNotFoundException" -> apiErrorResponseDtoModel.setCode(404);
			case "ServerException" -> apiErrorResponseDtoModel.setCode(500);
			case "UnavailableServiceException" -> apiErrorResponseDtoModel.setCode(503);
			default -> apiErrorResponseDtoModel.setCode(500);
		}

		apiErrorResponseDtoModel.setMessage(ex.getMessage());
		return ResponseEntity.status(apiErrorResponseDtoModel.getCode()).body(apiErrorResponseDtoModel);
	}
}
