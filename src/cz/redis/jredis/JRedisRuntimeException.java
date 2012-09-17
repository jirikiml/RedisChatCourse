package cz.redis.jredis;

public class JRedisRuntimeException
        extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public JRedisRuntimeException(Exception cause)
    {
        super(cause);
    }
}
