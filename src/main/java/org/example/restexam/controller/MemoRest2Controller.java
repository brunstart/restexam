package org.example.restexam.controller;

import org.example.restexam.domain.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api2/memos")
public class MemoRest2Controller {
    private final Map<Long, Memo> memos = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    // http://localhost:8080/api2/memos         -- GET / 모든 메모를 조회          -- List<Memo>
    // http://localhost:8080/api2/memos/{id}    -- GET / id에 해당하는 메모 조회    -- Memo
    // http://localhost:8080/api2/memos         -- POST / 메모 생성               -- Long
    // http://localhost:8080/api2/memos/{id}    -- PUT / 메모 수정                -- 메시지 String or 수정된 Memo
    // http://localhost:8080/api2/memos/{id}    -- DELETE / 메모 삭제             -- String

    // 위에 제시한 요청이 들어왔을 때 이것을 모두 처리할 수 있는 RestController를 만들어 주세요

    @GetMapping
    public List<Memo> getMemos() {
        return new ArrayList<>(memos.values()); // 수정가능한 리스트 반환 (가변리스트)
        // return memos.values().stream().toList(); // 불변 리스트 반환
    }

    @GetMapping("/{id}")
    public Memo getMemo(@PathVariable("id") Long id) {
        Memo noMemo = new Memo();
        Long noMemoId = 0L;
        noMemo.setId(noMemoId);
        noMemo.setContent("메모를 찾을 수 없습니다.");

        return memos.getOrDefault(id, noMemo);
    }

    @PostMapping
    public Long createMemo(@RequestBody Memo memo) {  // id 반환
        Long id = counter.incrementAndGet();
        memo.setId(id);
        memos.put(id, memo);    // 지금은 map을 저장, 나중에는 service를 호출하는 로직이 여기 올것
        return id;
    }

    @PutMapping("/{id}")
    public String updateMemo(@PathVariable("id") Long id ,@RequestBody Memo memo) {
        if (!memos.containsKey(id)) {
            return "메모를 찾을 수 없습니다";
        }
        memo.setId(id);
        memos.put(memo.getId(), memo);

        return "메모 수정 완료";
    }

    @DeleteMapping("/{id}")
    public String deleteMemo(@PathVariable("id") Long id) {
        if (memos.remove(id) == null) {
            return "해당 메모를 찾을 수 없습니다.";
        }
        return "메모 삭제 성공";
    }

    //curl test

    //1. curl -X GET http://localhost:8080/api2/memos
    //2. curl -X GET http://localhost:8080/api2/memos/1
    //3. curl -X POST -H "Content-type: application/json" -d "{\"content\":\"첫번째 메모\"}"  http://localhost:8080/api2/memos
    //  맥  curl -X POST -H "Content-type: application/json" -d '{"content":"첫번째 메모"}' http://localhost:8080/api2/memos
    //4. curl -X PUT -H "Content-type: application/json" -d "{\"content\":\"수정된 메모\"}"  http://localhost:8080/api2/memos/1
    // 맥  curl -X PUT -H "Content-type: application/json" -d '{"content":"수정된 메모"}'  http://localhost:8080/api2/memos/1
    //5. curl -X DELETE http://localhost:8080/api2/memos/1

    @GetMapping(value = "/json/{id}", produces = "application/json")
    public Memo getMemoJson(@PathVariable("id") Long id) {
        Memo noMemo = new Memo();
        Long noMemoId = 0L;
        noMemo.setId(noMemoId);
        noMemo.setContent("메모를 찾을 수 없습니다.");

        return memos.getOrDefault(id, noMemo);
    }

    @GetMapping(value = "/xml/{id}", produces = "application/xml")
    public Memo getMemoXml(@PathVariable("id") Long id) {
        Memo noMemo = new Memo();
        Long noMemoId = 0L;
        noMemo.setId(noMemoId);
        noMemo.setContent("메모를 찾을 수 없습니다.");

        return memos.getOrDefault(id, noMemo);
    }
}
