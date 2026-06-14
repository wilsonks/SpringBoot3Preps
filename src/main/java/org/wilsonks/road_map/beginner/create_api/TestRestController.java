package org.wilsonks.road_map.beginner.create_api;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/*
If your goal is to return raw JSON or XML (e.g., for APIs) rather than a view, use @RestController. It bundles @Controller and @ResponseBody automatically.
 */
@RestController
@RequestMapping("/api/rooms")
public class TestRestController {

    @Autowired
    RoomService roomService;

    /**
     * A static nested class functions exactly like an ordinary top-level class, merely using the outer class name as a namespace delimiter. You can instantiate it anywhere independently using: new
     */

    public enum RoomType {
        SINGLE, DOUBLE, SUITE
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Room {
        private Integer id;
        private String roomNumber;
        private String roomName;
        private RoomType roomType;
        private Boolean available;
    }

    @Service
    public static class RoomService {
        private final List<Room> rooms = new ArrayList<>();

        public RoomService() {
            rooms.add(new Room(1, "101", "Single Room", RoomType.SINGLE, true));
            rooms.add(new Room(2, "102", "Double Room", RoomType.DOUBLE, false));
            rooms.add(new Room(3, "201", "Suite Room", RoomType.SUITE, true));
        }

        public Collection<Room> getAllRooms() {
            return rooms;
        }

        public Collection<Room> getRooms(Boolean available) {
            return rooms.stream()
                    .filter(room -> room.getAvailable() == available)
                    .toList();

        }

        public Optional<Room> getRoom(Integer id) {
            return rooms.stream()
                    .filter(room -> room.getId().equals(id))
                    .findFirst();
        }

        public boolean deleteRoom(Integer id) {
            Optional<Room> room = getRoom(id);
            if (room.isPresent()) {
                rooms.remove(room.get());
                return true;
            } else {
                return false;
            }

        }
    }

    //GET requests must be idempotent
    //Use @RequestParam for sorting, filtering, or pagination
    //Use Wrapper Classes
    //Set Default Values: Use the defaultValue attribute within @RequestParam to establish safe fallbacks
    // Use ResponseEntity: Wrap your responses in a ResponseEntity<T> to have precise control over HTTP status codes.
    @GetMapping("/")
    public ResponseEntity<Collection<Room>> getAllRooms(@RequestParam(value = "available", required = false, defaultValue = "true") Boolean available) {
        if(available == null) {
            Collection<Room> allRooms = roomService.getAllRooms();
            return ResponseEntity.ok(allRooms);
        }
        Collection<Room> allRooms = roomService.getRooms(available);
        return ResponseEntity.ok(allRooms);

    }

    //GET requests must be idempotent
    //Use @PathVariable only when fetching a specific, identifiable resource
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Integer id) {
        Optional<Room> room = roomService.getRoom(id);
        //200 OK
        //404 NOT FOUND
        return room.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //DELETE /api/rooms/{id}
    //Use Path Variables for IDs: Always extract the target identifier using @PathVariable in the URI.
    //Should return 200 OK or 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Integer id){
        boolean isDeleted = roomService.deleteRoom(id);
        if(isDeleted) {
            return ResponseEntity.noContent().build(); //204 NO CONTENT
        } else  {
            return ResponseEntity.notFound().build(); //404 NOT FOUND
        }
    }

}
