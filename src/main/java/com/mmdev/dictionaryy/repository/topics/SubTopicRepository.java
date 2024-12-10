package com.mmdev.dictionaryy.repository.topics;

import com.mmdev.dictionaryy.entity.topics.SubTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTopicRepository extends JpaRepository<SubTopic, Long> {
	Boolean existsSubTopicByName(String name);
}
