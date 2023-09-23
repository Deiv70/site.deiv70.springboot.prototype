package site.deiv70.springboot.prototype.infrastructure.primary.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import site.deiv70.springboot.prototype.common.TestAbstract;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiRequestExceptionTest extends TestAbstract {

	@BeforeEach
	void setUp() {
	}

	@Test
	void testApiRequestException_type_message() {
		// Given
		String message = "message";
		ApiRequestException.Type type = easyRandom.nextObject(ApiRequestException.Type.class);

		// When
		ApiRequestException apiRequestException = new ApiRequestException(type, message);

		// Then
		assertEquals(message, apiRequestException.getMessage());
		assertEquals(type, apiRequestException.getType());
		assertEquals(type.getHttpCode(), apiRequestException.getApiErrorResponseDtoModel().getCode());
	}

	@Test
	void testApiRequestException_type_message_exception() {
		// Given
		String message = "message";
		ApiRequestException.Type type = easyRandom.nextObject(ApiRequestException.Type.class);
		String exceptionMessage = "exception message";
		Exception exception = new Exception(exceptionMessage);

		// When
		ApiRequestException apiRequestException = new ApiRequestException(type, message, exception);

		// Then
		assertEquals(message, apiRequestException.getMessage());
		assertEquals(type, apiRequestException.getType());
		assertEquals(type.getHttpCode(), apiRequestException.getApiErrorResponseDtoModel().getCode());
		assertEquals(exceptionMessage, apiRequestException.getException().getMessage());
	}

	@Test
	void testApiRequestException_message_exception() {
		// Given
		String message = "message";
		ApiRequestException.Type type = ApiRequestException.Type.UNKNOWN_EXCEPTION;
		String exceptionMessage = "exception message";
		Exception exception = new Exception(exceptionMessage);

		// When
		ApiRequestException apiRequestException = new ApiRequestException(message, exception);

		// Then
		assertEquals(message, apiRequestException.getMessage());
		assertEquals(type, apiRequestException.getType());
		assertEquals(type.getHttpCode(), apiRequestException.getApiErrorResponseDtoModel().getCode());
		assertEquals(exceptionMessage, apiRequestException.getException().getMessage());
	}

}
