package com.np.issue.tracker.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface PersonRepository extends JpaRepository<Person, UUID> {

    Optional<Person> findByLogin(String login);

}
