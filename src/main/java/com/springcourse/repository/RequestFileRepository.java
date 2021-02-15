package com.springcourse.repository;

import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

 

@Repository
public class RequestFileRepository extends JpaRepositoryConfigExtension <RequestFile, Long>{

}
