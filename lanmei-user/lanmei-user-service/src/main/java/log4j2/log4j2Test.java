package log4j2;

import org.slf4j.*;

public class log4j2Test {
	
	private final static Logger logger = LoggerFactory.getLogger(log4j2Test.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		while(true) {
			logger.trace("trace print");
			logger.error("err print");
			logger.debug("err print");
		}
		
		
	}

}
