package org.selbowgreaser.employeeservice.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.selbowgreaser.employeeservice.model.entity.position.*;

@Getter
@RequiredArgsConstructor
public enum Position {
    DESIGNER(Designer.class), DEVELOPER(Developer.class), ENGINEER(Engineer.class), MANAGER(Manager.class), ANALYST(Analyst.class);

    private final Class<?> clazz;
}
