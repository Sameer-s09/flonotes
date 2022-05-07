package com.flonotes.Data.Service;

import com.flonotes.Data.Repository.Domain.StickyNoteDomain;
import com.flonotes.Data.Repository.StickyNoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class NoteStoreService {

    @Autowired
    StickyNoteRepository stickyNoteRepository;

    public void persistStickyNote(StickyNoteDomain stickyNoteDomain){

        try{
            //stickyNoteRepository.insertNote(stickyNoteDomain);
            stickyNoteRepository.saveAndFlush(stickyNoteDomain);
            }
        catch(Exception e){
            log.error("Error creating note",e);
        }

    }
    public void updateStickyNote(StickyNoteDomain stickyNoteDomain){
        try{
            stickyNoteRepository.save(stickyNoteDomain);
        }catch(Exception e){
            log.error("Error updating note",e);
        }
    }

    public List<StickyNoteDomain> findNote(String noteUUID){
        return stickyNoteRepository.findBynoteUUID(noteUUID);
    }
}
