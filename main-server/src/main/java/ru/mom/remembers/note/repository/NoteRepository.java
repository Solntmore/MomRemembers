package ru.mom.remembers.note.repository;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.mom.remembers.note.model.Note;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>, QuerydslPredicateExecutor<Note> {
    Optional<Note> findByIdAndUserLogin(Long id, String userLogin);

}
