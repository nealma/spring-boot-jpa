package com.example;

import com.example.dao.DepartmentRepository;
import com.example.dao.RoleRepository;
import com.example.dao.UserRepository;
import com.example.entity.Department;
import com.example.entity.Role;
import com.example.entity.User;
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

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void initData(){
		userRepository.deleteAll();
		roleRepository.deleteAll();
		departmentRepository.deleteAll();


		Department department = new Department();
		department.setName("开发部");
		departmentRepository.save(department);
		Assert.notNull(department.getId());

		Role role = new Role();
		role.setName("admin");
		roleRepository.save(role);
		Assert.notNull(role.getId());

		List<Role> roles = roleRepository.findAll();
		Assert.notNull(roles);
		User user = null;
		for(int i=0; i<20;i++){
			user = new User();
			user.setName("user");
			user.setCreatedate(new Date());
			user.setDepartment(department);
			user.setRoles(roles);
			userRepository.save(user);
		}
		Assert.notNull(user.getId());

	}

	@Test
	public void  findPage(){
		Pageable pageable = new PageRequest(0,10, new Sort(
										new Sort.Order(Sort.Direction.ASC, "name"),
										new Sort.Order(Sort.Direction.DESC, "id"))
		);
		Page<User> page = userRepository.findAll(pageable);
		Assert.notNull(page);
		for (User user : page.getContent()){
			LOGGER.info("id={}, name={}, department={}, role={}", user.getId(), user.getName(), user.getDepartment().getName(), user.getRoles().get(0).getName());
		}
	}
	@Test
	public void  findPageCriteria() throws Exception {
		User user1 = new User();
		user1.setName("1");
		user1.setParentId(2L);
		Page<User> page = findCriteria(user1, 1, 10);
		LOGGER.info("size={}", page.getContent());
		for (User user : page.getContent()){
			LOGGER.info("id={}, name={}, department={}, role={}", user.getId(), user.getName(), user.getDepartment().getName(), user.getRoles().get(0).getName());
		}
	}

	public Page<User> findCriteria(final User user, int page, int pageSize) throws Exception {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable  =  new PageRequest(page, pageSize, sort);

		return userRepository.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(null != user.getParentId()){
                predicates.add(builder.equal(root.get("parentId").as(Long.class), user.getParentId()));
            }
            if(null != user.getName()){
                predicates.add(builder.equal(root.get("name").as(String.class), user.getName()));
            }
            query.where(predicates.toArray(new Predicate[predicates.size()]));
            return query.getRestriction();
        }, pageable);
	}


	public Page<User> findCriteriaV2(final User user, int page, int pageSize) throws Exception {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable  =  new PageRequest(page, pageSize, sort);

		return userRepository.findAll((root, query, builder) -> {
			Predicate p1 = builder.equal(root.get("name").as(String.class), user.getName());
			Predicate p2 = builder.equal(root.get("parentId").as(Long.class), user.getParentId());

			query.where(builder.and(p1, p2));
			return query.getRestriction();
		}, pageable);
	}

	public User insert(){
		User user = new User();
		user.setName("neal");
		user.setParentId(121L);
		return userRepository.save(user);
	}

	public User updateByPK(){
		User user = new User();
        user.setId(31L);
		user.setName("lingyi");
		return userRepository.save(user);
	}

	public void deleteByPK(Long id){
		userRepository.delete(id);
	}

	public User selectByPK(Long id){
		return userRepository.findOne(id);
	}

}
