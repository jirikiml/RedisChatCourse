package cz.redis;


// TODO maybe copy redis docu as javadoc here
public interface IRedis extends IRedisBasic, IRedisHashes, IRedisSet, IRedisSortedSet, IRedisConnection
{
    // all method are inherited from "group like" interfaces
}
