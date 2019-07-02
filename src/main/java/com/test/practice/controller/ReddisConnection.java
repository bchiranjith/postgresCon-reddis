package com.test.practice.controller;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.Jedis;

public class ReddisConnection {

	 public static void main( String[] args )
	    {
		 	Jedis jedis = new Jedis("redis://172.26.29.165:6379");
		      //check whether server is running or not 
		 	//172.26.29.165:6379
		      System.out.println("Server is running: "+jedis.ping());
		      
		/*
		 * JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		 * 
		 * redisConnectionFactory.setHostName("127.0.0.1");
		 * redisConnectionFactory.setPort(6379);
		 */
		      
		      
		 	
	    }
}
