package com.oocl.web.sampleWebApp.jpaSample.repository;

import com.oocl.web.sampleWebApp.jpaSample.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

//  @BeforeEach
//  void initData() {
//    userRepository.save(new User("test1", 18));
//    userRepository.save(new User("test2", 15));
//    userRepository.save( new User("test3", 38));
//    userRepository.save( new User("test4", 28));
//  }

  @Test
  public void test_should_return_user_when_the_user_exist() {
    //given
    User user = new User();
    user.setName("test");
    userRepository.save(user);

    //when
    List<User> userList = userRepository.findAll();

    //then
    Assertions.assertEquals(1, userList.size());
    Assertions.assertEquals("test", userList.get(0).getName());
  }

  @Test
  public void test_should_return_users_by_name(){
    //given
    User user = new User();
    user.setName("test1");
    userRepository.save(user);

    //when
    List<User> users = userRepository.findUsersByName("test1");

    //then
    Assertions.assertEquals("test1", users.get(0).getName());

  }

  @Test
  public void should_return_users_order_by_age_desc(){
    //given
    List<User> users = userRepository.findByOrderByAgeDesc();

    //when
    //then
    Assertions.assertEquals(38, users.get(0).getAge());
  }

  @Test
  public void should_delete_user_when_delete_user_by_id(){

    //given
    User user = userRepository.saveAndFlush(new User("test1", 18));

    //when
    userRepository.deleteById(user.getId());

    //then
    Assertions.assertFalse(userRepository.findById(user.getId()).isPresent());
  }

  @Test
  public void should_return_distinct_name_when_select_distinct_name(){

    //given
    User user = userRepository.save(new User("test1", 18));
    User user1 = userRepository.save(new User("test1", 18));

    //when
    List<String> names = userRepository.findDistinctName();

    //then
    Assertions.assertEquals("test1", String.join("," ,names));
  }

  @Test
  public void should_delete_user_when_delete_user_by_id_used_native_uery(){

    //given
    User user = userRepository.saveAndFlush(new User("test1", 18));

    //when
    userRepository.deleteById(user.getId());
//    userRepository.flush();

    //then
//    Assertions.assertNull(userRepository.getOne(user.getId()));
    Assertions.assertFalse(userRepository.findById(user.getId()).isPresent());

  }

}

