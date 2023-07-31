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
		ApiErrorResponseDtoModel apiErrorResponseDtoModel = ex.getHttpResponse();
		return ResponseEntity.status(apiErrorResponseDtoModel.getCode()).body(apiErrorResponseDtoModel);
	}

}
