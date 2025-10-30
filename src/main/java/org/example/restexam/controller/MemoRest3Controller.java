package org.example.restexam.controller;

import org.example.restexam.domain.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api3/memos")
public class MemoRest3Controller {
    private final Map<Long, Memo> memos = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    @PostMapping
    public ResponseEntity<Long> createMemo(@RequestBody Memo memo)
    {
        Long id = counter.incrementAndGet();
        memo.setId(id);
        memos.put(id, memo);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping
    public ResponseEntity<List<Memo>> getMemos(){
        return ResponseEntity.ok(new ArrayList<>(memos.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Memo> getMemo(@PathVariable("id") Long id) {
        // id에 해당하는 메모가 없을때
        Memo memo = memos.get(id);

        if(memo == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(memo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMemo(@PathVariable("id") Long id, @RequestBody Memo memo) {
        if(!memos.containsKey(id))
            // return ResponseEntity.notFound().build();
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            // return ResponseEntity.status(404).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Memo not found"); // 네개 다 동일

        memo.setId(id);
        memos.put(id, memo);
        return ResponseEntity.ok("Memo updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMemo(@PathVariable("id") Long id) {
        if(memos.remove(id) == null)
            return ResponseEntity.status(404).body("Memo not found");

        return ResponseEntity.ok("Memo deleted");
    }
}
