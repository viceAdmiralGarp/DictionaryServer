package com.mmdev.dictionaryy.entity.student;


import com.mmdev.dictionaryy.entity.school.School;
import com.mmdev.dictionaryy.entity.words.EngWord;
import com.mmdev.dictionaryy.entity.words.NativeWord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "Student", catalog = "dictionary", schema = "public")
public class Student {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;

	private String password;

	@Column(name = "group_name")
	private String groupName;

	@ManyToOne
	@JoinColumn(name = "school_id")
	private School school;

	@Builder.Default
	@ManyToMany
	@JoinTable(name = "eng_user_word",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "word_id"))
	private List<EngWord> engWords = new ArrayList<>();

	@Builder.Default
	@ManyToMany
	@JoinTable(name = "native_user_word",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "word_id"))
	private List<NativeWord> nativeWords = new ArrayList<>();

	public void addEngWord(EngWord word) {
		engWords.add(word);
		word.getStudents().add(this);
	}

	public void addNativeWord(NativeWord word) {
		nativeWords.add(word);
		word.getStudents().add(this);
	}
}
