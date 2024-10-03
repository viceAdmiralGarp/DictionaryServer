package com.mmdev.dictionaryy.repository;

import com.mmdev.dictionaryy.entity.topics.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {}

