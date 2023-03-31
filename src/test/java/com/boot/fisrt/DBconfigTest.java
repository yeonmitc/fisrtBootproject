package com.boot.fisrt;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@SpringBootTest
public class DBconfigTest {
    @Autowired
    DataSource dataSource;  // db 객체
    @Autowired
    TransactionManager transactionManager; // db연결 객체

    @Test
    void db연동(){
        assertNotNull(dataSource);
        assertNotNull(transactionManager);
    }
}
