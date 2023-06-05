package com.example.postendpoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ShawnController {

    List<String> songs = new ArrayList<>();

    @PostMapping(path = "/shawn/songs")
    public ResponseEntity<ResponseBodyShawn> postSmth(@RequestBody RequestBodyShawn requestBodyShawn) {
        String songName = requestBodyShawn.songName();
        log.info(songName);
        songs.add(songName);
        return ResponseEntity.ok(new ResponseBodyShawn("Added new song!", List.of(songName)));
    }

    @GetMapping(path = "/shawn/songs")
    public ResponseEntity<ResponseBodyShawn> getAllSongs(@RequestHeader(required = false) String requestId) {
        String toStringWithIndexes = toStringWithIndexes(songs);
        System.out.println(songs);
        return ResponseEntity.ok(new ResponseBodyShawn(toStringWithIndexes + "requestId was: " + requestId, Collections.emptyList()));
    }

    @DeleteMapping(path = "/shawn/songs/{id}")
    public ResponseEntity<String> deleteShawn(@PathVariable int id) {
        songs.remove(id);
        return ResponseEntity.ok("U deleted shawn song with id: " + id);
    }

    private <T> String toStringWithIndexes(List<T> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(i).append(": ").append(list.get(i)).append(" ");
        }
        return sb.toString();
    }
}
