package com.flonotes.Controller;

import com.flonotes.Data.Repository.Domain.StickyNoteDomain;
import com.flonotes.Data.Repository.StickyNoteRepository;
import com.flonotes.Data.Service.NoteStoreService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class restController {



    @Autowired
    NoteStoreService noteStoreService;
    @Autowired
    StickyNoteRepository stickyNoteRepository;

    @PostMapping(value = "new",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    private String createNote(@RequestBody StickyNoteDomain Note){
        //System.out.println("incoming value: " +Note.toString());
        StickyNoteDomain stickyNoteDomain = new StickyNoteDomain();
        stickyNoteDomain.setnoteUUID(UUID.randomUUID().toString());
        stickyNoteDomain.setStatus(Note.getStatus());
        stickyNoteDomain.setNotetext(Note.getNotetext());
        stickyNoteDomain.setLastupdated(Date.from(Instant.now()));
       System.out.println(stickyNoteDomain.toString());
        try{
            noteStoreService.persistStickyNote(stickyNoteDomain);

            return ("note created!");
        }catch(Exception e){
            e.printStackTrace();
            return("error creating new note"+e);
        }

    }


    @PostMapping(value = "updatenote",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    private String updateNote(@RequestBody StickyNoteDomain Note){
        //List<StickyNoteDomain> stickyNoteDomain = noteStoreService.findNote(Note.getNoteUUID());
        String noteUUID = Note.getnoteUUID();
        System.out.println("Note UUID: " + noteUUID);
        if(!stickyNoteRepository.existsBynoteUUID(noteUUID)){
            return ("note does not exists");
        }
        List<StickyNoteDomain> stickyNoteDomain = noteStoreService.findNote(noteUUID);

        System.out.println("stickyNoteDomain: " + stickyNoteDomain.get(0));
        stickyNoteDomain.get(0).setStatus(Note.getStatus());
        System.out.println("to be updated stickyNoteDomain: " + stickyNoteDomain.get(0));

        try{
            //noteStoreService.updateStickyNote(stickyNoteDomain.get(0));
           // stickyNoteRepository.updatedNote(stickyNoteDomain.get(0));
            stickyNoteRepository.saveAndFlush(stickyNoteDomain.get(0));
            System.out.println(stickyNoteDomain.toString());

            return ("note updated!");
        }catch(Exception e){
            e.printStackTrace();
            return("error updating  note"+e);
        }

    }

    @RequestMapping("/archiveNote")
    private String archiveNote(){

        return "note archived";
    }

    @RequestMapping("/moveNote")
    private String moveNote(){

        return "note moved";
    }

    @RequestMapping("/showDashboard")
    private String showDashboard(){

        return "Dashboard Displayed";
    }

    //to do
    /*@RequestMapping("/setNotification")
    private String setNotification(){

        return "Notification set";
    }*/
}
