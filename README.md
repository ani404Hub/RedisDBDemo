# RedisDBDemo
Redis(Remote Dictionary Server) is an open-source in-memory data store and can be used for caching mechanism.
We are using Redis with Jedis version 3.3.0 which only supports Java 8, The above version of Java is incompatible with this version of Jedis.
Integrate with Redis database rather than using Oracle DB.
While saving data through post-mapping productId is the primary key which is auto-incremented and need not be required to put in Json file 
Further applied caching mechanism to reduce the number of DB calls while fetching data.
Simple project with added comments to have a better understanding of each line.
