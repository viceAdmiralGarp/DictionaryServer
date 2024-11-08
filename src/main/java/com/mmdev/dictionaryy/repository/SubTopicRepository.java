package com.mmdev.dictionaryy.repository;

import com.mmdev.dictionaryy.entity.topics.SubTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubTopicRepository extends JpaRepository<SubTopic, Long> {
	Optional<Object> findTopicByName(String name);
}
