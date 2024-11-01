package com.mmdev.dictionaryy.repository;

import com.mmdev.dictionaryy.entity.topics.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
	Optional<Topic> findFirstBySchoolId(Long schoolId);

	List<Topic> findBySchoolId(Long schoolId);
}

