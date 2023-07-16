package site.deiv70.springboot.prototype.common;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestMethods {

	public static Stream<Arguments> testNullEmptyExecution() {
		return Stream.of(
			Arguments.of(null, false),
			Arguments.of("", false),
			Arguments.of("  ", true),
			Arguments.of("not blank", false)
		);
	}

}
