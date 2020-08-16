package com.meetme.repositories;

import com.meetme.models.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Person, String> {
    @Query(value = "SELECT * FROM meet_me.people p JOIN meet_me.details AS d on p.details_id=d.id WHERE d.email = :email", nativeQuery = true)
    Optional<Person> findOneByEmail(@Param("email") String email);

    Person findByUsername(String username);

    @Query(value = "SELECT * FROM meet_me.people p JOIN meet_me.details AS d on p.details_id=d.id WHERE d.email = :email", nativeQuery = true)
    Person findByEmail(@Param("email") String email);
}
