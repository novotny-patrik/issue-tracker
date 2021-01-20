package com.np.issue.tracker.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@Entity
class Task {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    /*@Column(updatable = false, nullable = false)*/
    private UUID id;

    private String title;
    private String description;

    public Task() {
    }

    TaskDto toDto() {
        return TaskDto.builder()
                .id(id)
                .title(title)
                .description(description)
                .build();
    }

}
