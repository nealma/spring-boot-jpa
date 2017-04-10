package com.example;

import com.example.dao.DepartmentRepository;
import com.example.dao.RoleRepository;
import com.example.dao.UserRepository;
import com.example.entity.Department;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.redis.UserRedis;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserRedis userRedis;

	@Before
	public void setUp(){

		Department department = new Department();
		department.setName("开发部");

		Role role = new Role();
		role.setName("admin");

		User user = new User();
		user.setName("user");
		user.setCreatedate(new Date());
		user.setDepartment(department);

		List<Role> roles = new ArrayList<>();
		roles.add(role);

		user.setRoles(roles);

		userRedis.delete(this.getClass().getName()+":userByName:"+user.getName());
		userRedis.add(this.getClass().getName()+":userByName:"+user.getName(), 10L, user);
	}

	@Test
	public void  get(){
		User user = userRedis.get(this.getClass().getName()+":userByName:user");
		Assert.notNull(user);
		LOGGER.info("user {}, department {}, role {}", user.getName(), user.getDepartment().getName(), user.getRoles().get(0).getName());
	}

}
