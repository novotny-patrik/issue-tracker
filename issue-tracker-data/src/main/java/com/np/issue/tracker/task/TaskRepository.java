package com.np.issue.tracker.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface TaskRepository extends JpaRepository<Task, UUID> {
}
