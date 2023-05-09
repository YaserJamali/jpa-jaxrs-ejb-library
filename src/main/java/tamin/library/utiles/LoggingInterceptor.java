package tamin.library.utiles;


import javax.enterprise.inject.Produces;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Logger;

@Interceptor
@Loggable
public class LoggingInterceptor implements Serializable {
    @Produces
    private Logger logger;

    @AroundInvoke
    private Object intercept(InvocationContext ic) throws Exception {
        try {
            logger.info("> Entry{}" + ic.getMethod());
            return ic.proceed();
        } finally {
            logger.info("EXIT:" + ic.getMethod());
        }
    }

}
