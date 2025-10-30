package org.example.restexam.service;

import lombok.RequiredArgsConstructor;
import org.example.restexam.Repository.UserRepository;
import org.example.restexam.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return  userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")); // 옵셔널 객체를 사용 : 예외처리
    }

    @Override
    @Transactional  // 트랜잭셔널 반드시 추가
    public User addUser(User user) {
        try{
            return userRepository.save(user);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public User updateUser(User user) {

        if(user.getId() == null) {
            throw new IllegalArgumentException("사용자 아이디 필수");
        }

        User findUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 수정할 값을 매개변수로 전달 받았을 것이므로
        // 회원정보 수정 -
        // 이름, 비밀번호, 주소, 전화번호, 등록일... -- 원래 있던 정보
        // 사용자는 비밀번호와 주소를 바꾸고 싶다.
        // 위의 상황을 처리할 때 어떤 값을 전달해야할까

        if (user.getName() != null)
            findUser.setName(user.getName());   // 이름 수정을 원하는 경우에만 값 설정
        if (user.getEmail() != null)
            findUser.setEmail(user.getEmail());   // 이메일 수정을 원하는 경우에만 값 설정

                           // @Transactional 태그가 있으니까
        return findUser;   // 오류없이 여기까지 수행되면, commit이 실행, 오류가 난다면 해당 시점에서 rollback 실행

        // save() 호출할 필요 없음 : 이렇게 처리하는 것보다 변경을 감지해서 처리하는게 좋음
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
    }
}
