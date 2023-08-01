package site.deiv70.springboot.prototype.infrastructure.primary.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import site.deiv70.springboot.prototype.common.TestAbstract;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.ApiErrorResponseDtoModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RestExceptionHandlerTest extends TestAbstract {
	private final RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

	@Test
	void handleApiRequestException() {
		// Given
		String message = "Invalid parameter";
		ApiRequestException exception = new ApiRequestException(ApiRequestException.Type.INVALID_FIELD_EXCEPTION, message);

		// When
		ResponseEntity<ApiErrorResponseDtoModel> responseEntity = restExceptionHandler.handleApiRequestException(exception);

		// Then
		assertNotNull(responseEntity);
		assertEquals(exception.getHttpResponse(), responseEntity.getBody());
		assertEquals(exception.getType().getHttpCode(), responseEntity.getStatusCode().value());
	}

}
