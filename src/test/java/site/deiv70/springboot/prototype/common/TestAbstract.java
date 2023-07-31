package site.deiv70.springboot.prototype.common;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class TestAbstract {

	protected final EasyRandom easyRandom = new EasyRandom();

}
