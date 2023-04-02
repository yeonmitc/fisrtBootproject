package com.boot.fisrt.domain;

import com.boot.fisrt.Form.StudyForm;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="study_id")
    private Long id;
    private LocalDate studyDay;
    private LocalTime startTime;
    private int studyMins;
    @Lob
    private String contents;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="member_id")
    private Member member;



    //==생성 메서드 ==
    public static StudyRecord createRecord(StudyForm form, Member member){
        StudyRecord study = new StudyRecord();
        study.member = member;
        study.studyDay =  LocalDate.parse(form.getStudyDay(), DateTimeFormatter.ofPattern("yyyy-MM-dd") );
        study.startTime = LocalTime.parse(form.getStartTime()+":00", DateTimeFormatter.ofPattern("HH:mm:ss") );
        study.studyMins = form.getStudyMins();
        study.contents = form.getContents();
        return study;
    }


    public static StudyRecord modyfiyRecord(StudyRecord record ,StudyForm form) {
        DateTimeFormatter strDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter strTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        record.studyDay =  LocalDate.parse(form.getStudyDay(), strDate );
        record.startTime = LocalTime.parse(form.getStartTime()+":00", strTime );
        record.studyMins = form.getStudyMins();
        record.contents = form.getContents();
        return record;
    }

    //==비즈니스 로직==//
    public String getEndStudyDay(){
        String pattern = "yyyy/MM/dd HH:mm";
        LocalDateTime endStudyTime = LocalDateTime.of(this.studyDay, this.startTime);
        endStudyTime = endStudyTime.plusMinutes(this.studyMins);
        String data =  endStudyTime.format(DateTimeFormatter.ofPattern(pattern));

        return data;
    }


}
