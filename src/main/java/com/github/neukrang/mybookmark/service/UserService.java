package com.github.neukrang.mybookmark.service;

import com.github.neukrang.mybookmark.config.TextConfig;
import com.github.neukrang.mybookmark.domain.section.Section;
import com.github.neukrang.mybookmark.domain.user.User;
import com.github.neukrang.mybookmark.domain.user.UserRepository;
import com.github.neukrang.mybookmark.web.dto.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    protected List<Section> getSections(Long userId) {
        User user = findEntityById(userId);
        return user.getSections();
    }

    protected User findEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(TextConfig.cantFindUserMsg(id)));
    }

    public UserResponseDto findById(Long userId) {
        return new UserResponseDto(findEntityById(userId));
    }
}
