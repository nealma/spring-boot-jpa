package com.example;

import com.example.entity.UserMongo;
import com.example.mongodb.UserMongoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDbTest {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserMongoRepository userMongoRepository;

	@Before
	public void setUp(){

		Set<String> roles = new HashSet<>();
		roles.add("admin");

		UserMongo user = new UserMongo();
		user.setName("user");
		user.setCreatedate(new Date());

		user.setRoles(roles);

		userMongoRepository.save(user);
	}

	@Test
	public void  findAll(){
		List<UserMongo> userMongos = userMongoRepository.findAll();
		Assert.notNull(userMongos);
		for (UserMongo user : userMongos) {
			LOGGER.info("user {}, pass {}", user.getName(), user.getPassword());
		}
	}

}
