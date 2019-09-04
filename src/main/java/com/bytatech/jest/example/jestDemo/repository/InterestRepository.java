package com.bytatech.jest.example.jestDemo.repository;


import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.bytatech.jest.example.jestDemo.domain.Interest;





@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {

}
