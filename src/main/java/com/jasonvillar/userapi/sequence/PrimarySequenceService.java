package com.jasonvillar.userapi.sequence;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrimarySequenceService {
    private final PrimarySequenceRepository primarySequenceRepository;

    public long getNextValue(String primarySequenceString) {
        Optional<PrimarySequence> primarySequenceOptional = this.primarySequenceRepository.findById(primarySequenceString);

        PrimarySequence primarySequence;

        if (primarySequenceOptional.isPresent()) {
            primarySequence = primarySequenceOptional.get();
            primarySequence.setSeq(primarySequence.getSeq() + 1);
            primarySequence = this.primarySequenceRepository.save(primarySequence);
        } else {
            primarySequence = new PrimarySequence();
            primarySequence.setId(primarySequenceString);
            primarySequence.setSeq(1);
            primarySequence = this.primarySequenceRepository.insert(primarySequence);
        }

        return primarySequence.getSeq();
    }
}