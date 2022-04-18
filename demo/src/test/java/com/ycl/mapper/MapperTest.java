package com.ycl.mapper;

import com.ycl.appstart.AppStartUp;
import com.ycl.dao.mapper.PersonMapper;
import com.ycl.dao.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author : YangChunLong
 * @date : Created in 2020/7/31 19:44
 * @description:
 * @modified By:
 * @version: :
 */
@SpringBootTest(classes = AppStartUp.class)
@RunWith(SpringRunner.class)
public class MapperTest {
    @Autowired
    private PersonMapper personMapper;
    @Test
    public void testPersonMapper (){
        Person person = new Person();
        person.setAge(19);
        person.setName("刘英");
        int i = personMapper.insertSelective(person);
        System.out.println(i);
    }
}
