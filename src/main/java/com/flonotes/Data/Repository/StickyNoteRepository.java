package com.flonotes.Data.Repository;

import com.flonotes.Data.Repository.Domain.StickyNoteDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface StickyNoteRepository extends JpaRepository<StickyNoteDomain,String> {

    public List<StickyNoteDomain> findAll();

    @Transactional
    @Modifying
    @Query(value="insert into StickyNote (noteUUID,status,notetext,lastupdated)" +
            "VALUES(:#{#record.noteUUID},:#{#record.status},:#{#record.notetext},:#{#record.lastupdated})",nativeQuery = true)
     void insertNote(@Param("record") StickyNoteDomain record);

    @Transactional
    @Modifying
    //@Query(value="update STICKY_NOTE s set s.status = :#{#record.status}, s.notetext=:#{#record.notetext} and s.lastupdated = :#{#record.lastupdated} where s.noteUUID = :#{#record.noteUUID}"
    @Query(value="update STICKY_NOTE s set s.status = :#{#record.status} and s.lastupdated = :#{#record.lastupdated} where s.noteUUID = :#{#record.noteUUID}"
            ,nativeQuery = true)
     void updatedNote(@Param("record") StickyNoteDomain record);

     List<StickyNoteDomain> findBynoteUUID(String noteUUID);

     boolean existsBynoteUUID(String noteUUID);

}
