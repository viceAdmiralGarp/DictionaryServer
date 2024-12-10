package com.mmdev.dictionaryy.repository.words;

import com.mmdev.dictionaryy.entity.words.EngWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngWordRepository  extends JpaRepository<EngWord, Long> {
}
