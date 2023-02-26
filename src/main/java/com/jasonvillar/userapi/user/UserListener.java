package com.jasonvillar.userapi.user;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.jasonvillar.userapi.sequence.PrimarySequenceService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserListener extends AbstractMongoEventListener<User> {
    private final PrimarySequenceService primarySequenceService;

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<User> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(primarySequenceService.getNextValue("userSequence"));
        }
    }
}
