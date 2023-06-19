package songify.song.controller;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
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
import songify.song.dto.SingleSongResponseDto;
import songify.song.dto.SongRequestDto;
import songify.song.dto.SongResponseDto;

@RestController
@Log4j2
@RequestMapping("/songs")
public class SongRestController {

    Map<Integer, String> database = new HashMap<>();

    @GetMapping
    public ResponseEntity<SongResponseDto> getAllSongs(@RequestParam(required = false) Integer limit) {
        database.put(1, "shawnmendes song1");
        database.put(2, "ariana grande song2");
        database.put(3, "ariana grande song21123123");
        database.put(4, "ariana grande song12312314345cbvbcvb");
        if (limit != null) {
            Map<Integer, String> limitedMap = database.entrySet()
                    .stream()
                    .limit(limit)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            SongResponseDto response = new SongResponseDto(limitedMap);
            return ResponseEntity.ok(response);
        }
        SongResponseDto response = new SongResponseDto(database);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleSongResponseDto> getSongById(@PathVariable Integer id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        String song = database.get(id);
        if (song == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        SingleSongResponseDto response = new SingleSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SingleSongResponseDto> postSong(@RequestBody @Valid SongRequestDto songRequest) {
        String songName = songRequest.songName();
        log.info(songName);
        database.put(database.size() + 1, songName);
        return ResponseEntity.ok(new SingleSongResponseDto(songName));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteSongByIdUsingPathVariable(@PathVariable Integer id) {
        database.remove(id);
        return ResponseEntity.ok("U deleted song with path variable id: " + id);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSongByIdUsingRequestParam(@RequestParam Integer id) {
        database.remove(id);
        return ResponseEntity.ok("U deleted song using query param with id: " + id);
    }
}
