package site.deiv70.springboot.prototype.infrastructure.primary.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.ApiErrorResponseDtoModel;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ApiRequestException.class)
	protected ResponseEntity<ApiErrorResponseDtoModel> handleApiRequestException(ApiRequestException ex) {
		ApiErrorResponseDtoModel apiErrorResponseDtoModel = ex.getApiErrorResponseDtoModel();

		return ResponseEntity
			.status(ex.getHttpStatus().value())
			.body(apiErrorResponseDtoModel);
	}

	/*
	@Override
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
	)  {
		ApiErrorResponseDtoModel apiErrorResponseDtoModel = new ApiErrorResponseDtoModel();
		//apiErrorResponseDtoModel.setSpringException(ex);

		ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
			apiErrorResponseDtoModel.addMessagesItem(fieldError.getDefaultMessage());
		});

		return new ResponseEntity<>(apiErrorResponseDtoModel, HttpStatus.BAD_REQUEST);
	}
	*/

}
