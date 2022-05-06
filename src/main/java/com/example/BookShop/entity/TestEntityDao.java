package com.example.BookShop.entity;

import com.example.BookShop.dao.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

@Repository
public class TestEntityDao extends AbstractHibernateDao<TestEntity> {
    public TestEntityDao(){
        super();
        setClazz(TestEntity.class);
    }
}
