package com.mmdev.dictionaryy.repository;

import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.entity.topics.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
	Optional<Topic> findBySchoolId(Long schoolId);
}

