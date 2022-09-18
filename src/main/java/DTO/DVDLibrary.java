package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DVDLibrary {
    private String title;
    private LocalDate releaseDate;
    private String MPAARating;
    private String directorName;
    private String studio;
    private String note;
}
