package com.example;

import com.example.dao.UserRepository;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neal on 01/04/2017.
 */
@RestController
public class DemoController {
    @Autowired
    private UserRepository userRepository;
    @RequestMapping("/test/page")
    public Page<User> testPage(@PageableDefault(value = 15, sort = { "id" },direction = Sort.Direction.DESC) Pageable pageable,
                                User user) throws Exception{
//        return findCriteria(user, pageable.getPageNumber(), pageable.getPageSize());
        return findCriteriaPagable(user, pageable);
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

    public Page<User> findCriteriaPagable(final User user, Pageable pageable) throws Exception {

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
}
