package org.saxing.a0041_wemedia.logic.subtitles.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.joda.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class Subtitle {
    private LocalTime from;

    private LocalTime to;

    private List<String> lines;
}
