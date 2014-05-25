package io.github.uvpoblotzki.breaker;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * Facade für den {@link io.github.uvpoblotzki.breaker.ServiceImpl}, mit Timeout und Fallback.
 */
public class HystrixServiceImpl implements Service {

  private static final int THREAD_POOL_SIZE = 20;
  private static final int EXECUTION_TIMEOUT_MS = 2000;
  private final Service delegate;

  /**
   * Erzeugt einen Service mit Delegate.
   *
   * @param delegate Delegate
   */
  public HystrixServiceImpl(final Service delegate) {
    this.delegate = delegate;
  }

  @Override
  public boolean stopTheWorld() {
    return new StopTheWorldCommand().execute();
  }

  @SuppressWarnings("javadoc")
  public Service getDelegate() {
    return delegate;
  }

  private class StopTheWorldCommand extends HystrixCommand<Boolean> {

    protected StopTheWorldCommand() {
      super(Setter
          .withGroupKey(HystrixCommandGroupKey.Factory.asKey("Service"))
          .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
              .withCoreSize(THREAD_POOL_SIZE))
          .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
              .withExecutionIsolationThreadTimeoutInMilliseconds(EXECUTION_TIMEOUT_MS)));
    }

    @Override
    protected Boolean run() throws Exception {
      return getDelegate().stopTheWorld();
    }

    @Override
    protected Boolean getFallback() {
      return false;
    }
  }
}
