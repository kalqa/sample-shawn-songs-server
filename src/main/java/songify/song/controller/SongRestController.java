package songify.song.controller;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import songify.song.dto.SongRequestDto;
import songify.song.dto.SongResponseDto;

@RestController
@Log4j2
@RequestMapping("/songs")
public class SongRestController {

    List<String> songs = new ArrayList<>();

    @GetMapping
    public ResponseEntity<SongResponseDto> getAllSongs(@RequestHeader(required = false) String requestId, @RequestParam(required = false) Integer id) {
        if (id != null) {
            return getSongById(id);
        }
        return ResponseEntity.ok(new SongResponseDto(songs));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SongResponseDto> getSongById(@PathVariable Integer id) {
        String song = songs.get(id);
        return ResponseEntity.ok(new SongResponseDto(List.of(song)));
    }

    @PostMapping
    public ResponseEntity<SongResponseDto> postSong(@RequestBody @Valid SongRequestDto songRequest) {
        String songName = songRequest.songName();
        log.info(songName);
        songs.add(songName);
        return ResponseEntity.ok(new SongResponseDto(List.of(songName)));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteSongByIdUsingPathVariable(@PathVariable Integer id) {
        songs.remove(id);
        return ResponseEntity.ok("U deleted song with path variable id: " + id);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSongByIdUsingRequestParam(@RequestParam Integer id) {
        songs.remove(id);
        return ResponseEntity.ok("U deleted song using query param with id: " + id);
    }
}
