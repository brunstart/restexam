package org.example.restexam.controller;

import org.example.restexam.domain.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/memos")
public class MemoRestController {
    private final Map<Long, String> memos = new HashMap<>();

    private final AtomicLong counter = new AtomicLong();    // memo id를 자동으로 발급받으려고 생성함

    // memo가 crud 될 수 있도록 메소드 정의

    @GetMapping
    public Map<Long, String> getMemos() {

        return memos;
    }

    @GetMapping("/{id}")
    public String getMemo(@PathVariable("id") Long id) {
        // return memos.get(id);
        return memos.getOrDefault(id, "해당 메모를 찾을 수 없습니다.");
    }

    @PostMapping
    public Long createMemo(@RequestBody String content) {  // id 반환
        Long id = counter.incrementAndGet();
        memos.put(id, content);
        return id;
    }

    @PutMapping("/{id}")
    public String updateMemo(@PathVariable("id") Long id, @RequestBody String content) {
        if(!memos.containsKey(id)) {
            return id + "메모를 찾을 수 없습니다.";
        }
        memos.put(id, content);
        return "메모 수정 완료";
    }

    @DeleteMapping("/{id}")
    public String deleteMemo(@PathVariable("id") Long id) {
        if (memos.remove(id) == null) {
            return "해당 메모를 찾을 수 없습니다.";
        }
        return "메모 삭제 성공";
    }
}
