package site.deiv70.springboot.prototype.common;

import org.jeasy.random.EasyRandom;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class TestAbstract {

	protected final EasyRandom easyRandom = new EasyRandom();

}
