package com.mmdev.dictionaryy.entity.topics;

import com.mmdev.dictionaryy.entity.words.EngWord;
import com.mmdev.dictionaryy.entity.words.NativeWord;
import com.mmdev.dictionaryy.model.topics.subtopic.SubTopicDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "engWords")
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "sub_topic", catalog = "dictionary", schema = "public")
public class SubTopic {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "topic_id")
	private Topic topic;

	@Builder.Default
	@ManyToMany
	@JoinTable(name = "sub_topic_eng_word",
			joinColumns = @JoinColumn(name = "sub_topic_id"),
			inverseJoinColumns = @JoinColumn(name = "word_id")
	)
	private List<EngWord> engWords = new ArrayList<>();

	@Builder.Default
	@ManyToMany
	@JoinTable(name = "sub_topic_nat_word",
			joinColumns = @JoinColumn(name = "sub_topic_id"),
			inverseJoinColumns = @JoinColumn(name = "word_id")
	)
	private List<NativeWord> nativeWords = new ArrayList<>();

	public void addEngWord(EngWord word) {
		engWords.add(word);
		word.getSubTopics().add(this);
	}

	public void addNativeWord(NativeWord word){
		nativeWords.add(word);
		word.getSubTopics().add(this);
	}

	public SubTopicDto toDto(){
		return SubTopicDto.builder()
				.id(id)
				.name(name)
				.topicId(topic.getId())
				.build();
	}
}

