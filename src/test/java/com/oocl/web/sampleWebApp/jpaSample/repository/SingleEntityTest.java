package com.oocl.web.sampleWebApp.jpaSample.repository;

import com.oocl.web.sampleWebApp.jpaSample.entity.SingleEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SingleEntityTest {

    @Autowired
    private SingleEntityRepository singleEntityRepository;

    @Test
    public void should_return_single_entity_when_the_single_entity_exist(){
        //given
        SingleEntity singleEntity = new SingleEntity("single entity");
        singleEntityRepository.save(singleEntity);

        //when
        List<SingleEntity> singleEntities = singleEntityRepository.findAll();

        //then
        Assertions.assertEquals(1, singleEntities.size());
        Assertions.assertEquals("single entity", singleEntities.get(0).getName());
    }

    @Test
    public void should_throw_exception_when_name_is_larger_then_64(){
        //given
        String name = "single entity single entity single entity single entity single entity single entity";
        SingleEntity singleEntity = new SingleEntity(name);
        //when
        singleEntityRepository.save(singleEntity);

        //then
//        Assertions.assertEquals(0, singleEntities.size());
        Assertions.assertThrows(Exception.class, () -> singleEntityRepository.flush());
    }
}
