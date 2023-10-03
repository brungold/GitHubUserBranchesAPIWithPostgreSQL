package com.githubuserbranchesapi.repository;

import com.githubuserbranchesapi.domain.model.Repo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface GithubRepoRepository extends Repository<Repo, Long> {
    //@Query
    List<Repo> findAll(Pageable pageable);

    //@Query
    Optional<Repo> findById(Long id);

    @Modifying
        //@Query("UPDATE
    void updateById(Long id, Repo newRepo);


    Repo save(Repo repo);

    @Modifying
        //@Query
    void deleteById(Long id);

    boolean existsById(Long id);
}
