package com.mmdev.dictionaryy.repository;

import com.mmdev.dictionaryy.entity.topics.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

	List<Topic> findTopicsBySchoolId(Long schoolId);

	Boolean existsTopicByName(String name);
}

