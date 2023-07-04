package com.chablis.sanctified_journeys.service;

import com.chablis.sanctified_journeys.model.Church;
import com.chablis.sanctified_journeys.repository.ChurchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChurchService {
    private final ChurchRepository churchRepository;

    public Church createChurch(Church church) {
        return churchRepository.save(church);
    }
}
